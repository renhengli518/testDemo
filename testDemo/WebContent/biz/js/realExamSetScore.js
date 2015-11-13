function validateNum(e) {
	e.value = e.value.replace(/\D/g, '');
}

// 点击上传试卷返回
function showCreateQuestion() {
	$(".editQues").show();
	$("#setscore").hide();
	$(".fixedRight").hide();
}

function setScore() {
	var s1 = $("#set1").val();
	var s2 = $("#set2").val();
	var s3 = $("#set3").val();
	var s4 = $("#set4").val();
	var s5 = $("#set5").val();
	var s6 = $("#set6").val();

	var sum1 = 0;
	var sum2 = 0;
	var sum3 = 0;
	var sum4 = 0;
	var sum5 = 0;
	var sum6 = 0;

	if (s1 == "") {
		// 统计单选题分数
		sum1 = getScoreCount("type1div");
	} else {
		sum1 = s1 * type1;
	}
	if (s2 == "") {
		// 统计多选题分数
		sum2 = getScoreCount("type2div");
	} else {
		sum2 = s2 * type2;
	}
	if (s3 == "") {
		// 统计填空题分数
		sum3 = getScoreCount("type3div");
	} else {
		sum3 = s3 * type3;
	}
	if (s4 == "") {
		// 统计解答题分数
		sum4 = getScoreCount("type4div");
	} else {
		sum4 = s4 * type4;
	}
	if (s5 == "") {
		// 统计解答题分数
		sum5 = getScoreCount("type5div");
	} else {
		sum5 = s5 * type5;
	}
	if (s6 == "") {
		// 统计解答题分数
		sum4 = getScoreCount("type6div");
	} else {
		sum6 = s6 * type6;
	}

	if (sum1 + sum2 + sum3 + sum4 + sum5 + sum6 > $("#scoreInput").val()) {
		Win.alert("题目总分已超过");
	} else {
		if (s1 != "") {
			// 设置单选题分数
			$("#type1div [name ='score']").val(s1);
		}
		if (s2 != "") {
			// 设置多选题分数
			$("#type2div [name ='score']").val(s2);
		}
		if (s3 != "") {
			// 设置填空题分数
			$("#type3div [name ='score']").val(s3);
		}
		if (s4 != "") {
			// 设置解答题分数
			$("#type4div [name ='score']").val(s4);
		}
		if (s5 != "") {
			// 设置解答题分数
			$("#type5div [name ='score']").val(s5);
		}
		if (s6 != "") {
			// 设置解答题分数
			$("#type6div [name ='score']").val(s6);
		}
	}

	finishchangeScore();
}

function getScoreCount(type) {
	// 获取某一类型已设置的分数
	var sum = 0;
	$("#" + type + " [name = 'score']").each(function(index, element) {
		sum += element.value * 1;
	});
	return sum;
}

function finishchangeScore() {
	$("#seted").html( getScoreCount("type1div") + getScoreCount("type2div") + getScoreCount("type3div")
					+ getScoreCount("type4div") + getScoreCount("type5div") + getScoreCount("type6div"));
	$("#notset").html( $("#scoreInput").val() - (getScoreCount("type1div") + getScoreCount("type2div") 
					+ getScoreCount("type3div") + getScoreCount("type4div") + getScoreCount("type5div") + getScoreCount("type6div")));
}

// dom上移下移
var upAndDown = {
	down : function() {
		var $thisDom = $(this).parents(".text-box");
		var nextDom = $(this).parents(".text-box").next(".text-box")[0];
		if (nextDom) {
			$thisDom.insertAfter(nextDom);
		}
		upAndDown.refresh($thisDom.parent());
	},
	up : function() {
		var $thisDom = $(this).parents(".text-box");
		var prevDom = $(this).parents(".text-box").prev(".text-box")[0];
		if (prevDom) {
			$thisDom.insertBefore(prevDom);
		}
		upAndDown.refresh($thisDom.parent());
	},
	refresh : function($box) {
		var $move = $box.find('.move').removeClass('disabled');
		var len = $move.length;
		if (len > 0) {
			$($move[0]).addClass('disabled');
			$($move[len - 1]).addClass('disabled');
		}
	}
};
// upAndDown.refresh();
$(".text-box").on("click", ".move-down", upAndDown.down);
$(".text-box").on("click", ".move-up", upAndDown.up);

$(document).ready(function() {
	//getQuestionTypeByExam($("#examId").val());
	var getMoveFn = function(opt) {
		return function() {
			var $parent = $(this).parents(".quesDetail");
			var $elm = $parent[opt.find](".quesDetail");
			if ($elm.length > 0) {
				$parent[opt.move]($elm);
			}
			return false;
		};
	};

	$(".borderBox").on("click", ".removeDown", getMoveFn({
		find : 'next',
		move : 'insertAfter'
	}));
	$(".borderBox").on("click", ".removeUp", getMoveFn({
		find : 'prev',
		move : 'insertBefore'
	}));

});

function tosetScore() {
	// 点击设置分数时的触发事件
	// $("#fixed-target").show();
	$("#examTitle2").html($("#examTitle").val().replace(/\</g, "&lt;").replace(/\>/g, "&gt;"));
	$("#semesterOpts2").html($("#semesterOption").find("option:selected").text());
	$("#classLevelOpts2").html($("#classLevelOption").find("option:selected").text());
	$("#subjOption2").html($("#subjOption").find("option:selected").text());
	$("#examtypeOpts2").html($("#examtypeOpts").find("option:selected").text().replace(/\</g, "&lt;").replace(/\>/g, "&gt;"));
	$("#answerTime2").children("b").html($("#answerTime").val());
	$("#areaName2").html($("#areaName").val());
	$("#year2").html($("#year").val());
	$(".fixedRight").show();

	updateDivNum();

	if ($("#scoreInput2").html() != $("#scoreInput").val()) {
		// 如果不相等则更新总分，并且清空已设置的分数
		$("#scoreInput2").html($("#scoreInput").val());
		$("#notset").html($("#scoreInput").val());
		$("#seted").html("0");
		$(".setall").val("");
		$("[name = 'score']").val("");
	}

	// 为每道题绑定校验分数的时间
	$("[name = 'score']").unbind('input propertychange keyup');
	$("[name = 'score']").on(
			'input propertychange keyup',
			function(e) {
				this.value = this.value.replace(/\D/g, '');
				if (getScoreCount("type1div") + getScoreCount("type2div") + getScoreCount("type3div")
						+ getScoreCount("type4div") + getScoreCount("type5div") + getScoreCount("type6div") > $(
						"#scoreInput").val()) {
					Win.alert("总分超过设定，请重新输入");
					this.value = "";
					return;
				}
				finishchangeScore();
			});
	finishchangeScore();
}

// 更新设置分值页面题目大序号和显示隐藏
function updateDivNum() {
	$(".type1num").html(type1);
	$(".type2num").html(type2);
	$(".type3num").html(type3);
	$(".type4num").html(type4);
	$(".type5num").html(type5);
	$(".type6num").html(type6);
	var nownum = 0;
	// 控制设置分数区域显示隐藏
	if (type1 == 0) {
		$("#type1no").html(formSeq(nownum));
		$("#type1scoreshow").hide();
		$("#type1show").hide();
		$("#type1target").hide();
	} else {
		nownum += 1;
		$("#type1no").html(formSeq(nownum));
		$("#type1scoreshow").show();
		$("#type1show").show();
		$("#type1target").show();
	}
	if (type2 == 0) {
		$("#type2scoreshow").hide();
		$("#type2show").hide();
		$("#type2target").hide();
	} else {
		nownum += 1;
		$("#type2no").html(formSeq(nownum));
		$("#type2scoreshow").show();
		$("#type2show").show();
		$("#type2target").show();
	}
	if (type3 == 0) {
		$("#type3scoreshow").hide();
		$("#type3show").hide();
		$("#type3target").hide();
	} else {
		nownum += 1;
		$("#type3no").html(formSeq(nownum));
		$("#type3scoreshow").show();
		$("#type3show").show();
		$("#type3target").show();
	}
	if (type4 == 0) {
		$("#type4scoreshow").hide();
		$("#type4show").hide();
		$("#type4target").hide();
	} else {
		nownum += 1;
		$("#type4no").html(formSeq(nownum));
		$("#type4scoreshow").show();
		$("#type4show").show();
		$("#type4target").show();
	}
	if (type5 == 0) {
		$("#type5scoreshow").hide();
		$("#type5show").hide();
		$("#type5target").hide();
	} else {
		nownum += 1;
		$("#type5no").html(formSeq(nownum));
		$("#type5scoreshow").show();
		$("#type5show").show();
		$("#type5target").show();
	}
	if (type6 == 0) {
		$("#type6scoreshow").hide();
		$("#type6show").hide();
		$("#type6target").hide();
	} else {
		nownum += 1;
		$("#type6no").html(formSeq(nownum));
		$("#type6scoreshow").show();
		$("#type6show").show();
		$("#type6target").show();
	}
	initCreateExamQuestionType();
}

function formSeq(seqIndex) {
	var x = "一";
	switch (seqIndex) {
	case 1:
		x = "一";
		break;
	case 2:
		x = "二";
		break;
	case 3:
		x = "三";
		break;
	case 4:
		x = "四";
		break;
	case 5:
		x = "五";
		break;
	case 6:
		x = "六";
		break;
	case 7:
		x = "七";
		break;
	case 8:
		x = "八";
		break;
	case 9:
		x = "九";
		break;
	case 10:
		x = "十";
		break;
	}
	return x;
}

function isEmpty(str) {
	if (str == null || str == "") {
		return "";
	} else {
		return str;
	}
}

// 移出试卷
$(document).on('click', '.removeQuestion', function(event) {
	if (questionCount == 1) {
		Win.alert("真题试卷至少包含一题题目！");
		return false;
	}
	var type = $(this).attr("qtype");
	var examQuestionId = $(this).attr("qid");
	if (examQuestionId == null || examQuestionId == "") {
		Win.alert("删除习题失败！");
	} else {
		$.get(root + "/schoolTest/deleteRealQueByExamQueId.do", {
			"examQuestionId" : examQuestionId
		}, function(data) {
			if (data && data.result) {
				questionCount--;
				// 修改分数设置页面的值
				if (type == "SINGLE_CHOICE") {
					type1 -= 1;
					$(".type1num").html($(".type1num").html() - 1);
				} else if (type == "MULTI_CHOICE") {
					type2 -= 1;
					$(".type2num").html($(".type2num").html() - 1);
				} else if (type == "JUDEMENT") {
					type3 -= 1;
					$(".type3num").html($(".type3num").html() - 1);
				} else if (type == "FILL_IN_BLANK") {
					type4 -= 1;
					$(".type4num").html($(".type4num").html() - 1);
				} else if (type == "ASK_ANSWER") {
					type5 -= 1;
					$(".type5num").html($(".type5num").html() - 1);
				} else if (type == "COMPUTING") {
					type6 -= 1;
					$(".type6num").html($(".type6num").html() - 1);
				}
				// 删除习题剩余0 隐藏习题大标题
				updateDivNum();
				updateCount();
				// 同时删除页面的习题
				$("#" + examQuestionId).parent(".quesContainer").remove();
				// 同时删除设置分数页面的对应习题
				$("#" + examQuestionId + "_div").remove();
			} else {
				Win.alert("移出习题失败！");
			}
		});
	}
});

// 更新题目小表格数据
function updateCount() {
	$(".type1").html(type1);
	$(".type2").html(type2);
	$(".type3").html(type3);
	$(".type4").html(type4);
	$(".type5").html(type5);
	$(".type6").html(type6);
}

function clearInfo() {// 清空所有的基础化信息
	// 1.将学段，知识点，章节全部初始化为请选择状态 将新添加的章节和知识点清空
	$("#versionOption").val("0");
    $("#volumeOption").empty();
    $("#volumeOption").append(emptyOpt);
    $("#chapterOption").empty();
    $("#chapterOption").append(emptyOpt);
    $("#sectionOption").empty();
    $("#sectionOption").append(emptyOpt);
    $("#selectedChapters span").remove();//移出所有添加的章节
    $("#knowledgeOpts").empty();//初始化知识点
    $("#knowledgeOpts").append(emptyOpt);
    $("#knowledgeOpts").trigger("change");
    $("#selectedKnowledges span").remove();//移除所有添加的知识点
    $("#difficultyOpts").val("EASY");//将难易度默认选择为容易     
	var editorArr = $(".edtContent");
	// 将题干和解析进行清空操作 清空富文本框的内容UEdit_+i
	for (var i = 0, j = editorArr.length; i < j; i++) {
		var idFlag = $(editorArr[i]).attr("id");
		UE.getEditor(idFlag).setContent("");// 获取最上面的div的id即为他的容器id
	}

	// 清空所有选项内容 如果是单选题或多选题或判断题则清除所有选项的内容
	var questionType = $("input[name='questionTypeOpts']:checked").val();
	if (questionType == "SINGLE_CHOICE" || questionType == "MULTI_CHOICE" || questionType == "JUDEMENT") {
		var editOptionArr = $(".optDiv li div ");
		for (var i = 0; i < editOptionArr.length; i++) {
			$(editOptionArr[i]).children("span").get(1).innerHTML = "";
			$(editOptionArr[i]).children("input").get(1).value = "";
			$(editOptionArr[i]).children("textarea").get(0).innerHTML = "";
			$(editOptionArr[i]).children("textarea").get(1).innerHTML = "";
		}
	}
};

// 保存真题试卷
function saveRealExam() {
	if (questionCount < 1) {
		Win.alert("没有习题，请返回添加习题");
		return false;
	}

	$("[name = 'score']").each(function(index, e) {
		if ($(this).attr("id") != "save_score") {
			if (e.value == "" || e.value * 1 == 0) {
				Win.alert("请为所有题目设置分数");
				return false;
			}
		}
	});

	if ($("#notset").html() != "0") {
		Win.alert("未设置所有分数");
		return false;
	}

	var examQueList = "";
	$("input[name='questionIds']").each(function() {
		var examQuestionId = $(this).val();
		var score = $(this).next(".quesDesc").find("input[name='score']").val();
		examQueList += examQuestionId + "," + score + ";";
	});
	examQueList = examQueList.substr(0, examQueList.length - 1);

	$("#save_examQueList").val(examQueList);
	$("#save_examId").val($("#examId").val());
	$("#save_title").val($("#examTitle").val());
	$("#save_areaName").val($("#areaName option:selected").val());
	$("#save_year").val($("#year option:selected").val());
	$("#save_examTypeId").val($("#examtypeOpts option:selected").val());
	$("#save_baseSemesterId").val($("#semesterOption  option:selected").val());
	$("#save_baseClasslevelId").val($("#classLevelOption  option:selected").val());
	$("#save_baseSubjectId").val($("#subjOption  option:selected").val());
	$("#save_answerTime").val($("#answerTime").val());
	$("#save_score").val($("#scoreInput").val());
	$("#save_publicFlag").val($("#publicFlag").val());
	var formData = $("#saveRealExamForm").serialize();
	var url = root + "/schoolTest/saveRealExam.do";
	$.ajax({
		type : 'POST',
		url : url,
		data : formData,
		success : function(d) {
			Win.alert("上传真题试卷成功!");
			window.setTimeout(function() {
				window.location.href = root + '/schoolTest/toRealExam.html';
			}, 2000);
		}
	});

}