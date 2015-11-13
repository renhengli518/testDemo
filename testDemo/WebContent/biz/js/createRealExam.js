var emptyOpt = '<option value="0">请选择</option>';
//存放所有已选择的问题的数组
var type1 = 0;//单选
var type2 = 0;//多选
var type3 = 0;//判断
var type4 = 0;//填空
var type5 = 0;//问答
var type6 = 0;//计算
var questionCount=0;//添加题目个数
$(document).ready(function(){
	
	//初始化设置为新建真题试卷保存
	$("#saveOrEditType").val("save");
	
	$(".fixedRight").hide();
	//初始化页面，设置地区
	var area = ['河北省','山西省','辽宁省','吉林省','黑龙江省','江苏省','浙江省','安徽省','福建省','江西','山东省','河南省','湖北省','湖南省',
	            '广东省','海南省','四川省','贵州省','云南省','陕西省','甘肃省','青海省','台湾省','内蒙古','广西','西藏','宁夏','新疆'];
	var html = "<option value='0'>请选择</option>";
	area.forEach(function(value,index){
		html+='<option>'+value+'</option>';
	});
	$("#areaName").append(html);
	
	//绑定难易程度下拉框
	bindDifficulty();
	
	var uuid = 1;
	var qusetionIndex = 1;
	var deleQuestionStr="";
	UE.registerUI('blank', UE_UI_BLANK);
	var loadUEdit = function () {
		$('.edtContent').each(function () {
			if (!this.id) {
				var key = "UEdit_" + uuid++;
				this.id = key;
				UE.getEditor(key);

				if (!$(this).hasClass('edtContentQue')) {
					UE.getEditor(key).addListener('ready', function() {
						$("#"+  key + " .edui-for-blank > div").hide();								
					});
				} else {						
					UE.getEditor(key).addListener('ready', function() {
						if ('FILL_IN_BLANK' != $("input[name='questionTypeOpts']:checked").val()) {
							$("#"+  key + " .edui-for-blank > div").hide();
						}					
					});
					UE.getEditor(key).addListener('contentChange', function() {						
						var type = $("input[name='questionTypeOpts']:checked").val();
						if (type != "FILL_IN_BLANK")
							return;
						var $box = $('#' + key).parents('.questionItem');
						var questionIndex = $box.attr('data-qusetionindex');
						$box = $box.find('.fillIn-Answer');
						
						var content = this.getContent();
						var $tmp = $('<div>' + content + '</div>');
						var $fillChar = $tmp.find(".question-blank-space");
						var len = $fillChar.size();
						//$("#fillInAnswerCnt").val(len);//获得每道题空的个数     获得每道填空题的个数
						
						
						$("#fillInAnswerCnt0").val(len);
						
						//alert("填空的个数:"+len+"======题目="+questionIndex);
						
						
						$fillChar.each(function(index) {
							var $elm = $(this);
							var alt = index + 1;
							var uuid = $elm.attr("uuid");
							var $tr = $(".indep_answer tr.aTr" + uuid, $box);
							if ($tr.length == 0) {
								var html_indep = "<tr class=\"aTr" + uuid + "\" >";
								html_indep += "<td  style='font-weight:bolder;' class='indepBank table_th'>第" + formSeq(alt) + "空</td>";
								html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+'_'+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "</div></td>";
								html_indep += "<td  class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "</div></td>";
								html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "</div></td>";
								html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
								html_indep += "</div></td>";
								html_indep += "</tr>";
								$(".indep_answer tr:eq(" + index + ")", $box).after(html_indep);
							} else if ($tr.index() != index) {
								$tr.find('.indepBank').html('第' + formSeq((index + 1)) + '空');
								$tr.find('textarea').each(
										function(index) {
											this.name = (index % 2 == 0 ? 'indep_'
													: 'indep_txt_')
													+questionIndex+"_"+ alt + '_' + (((index + 2) / 2) >> 0);
										});
								$(".indep_answer tr:eq(" + index + ")", $box).after($tr);
							}
							var $tr = $(".comb_answer tr.aTr" + uuid, $box); 
							if ($tr.length == 0) {
								var html_comb = "<tr class=\"aTr" + uuid + "\" >";
								html_comb += "<td style='font-weight:bolder;' class='combBank table_th'>第" + formSeq(alt) + "空</td>";
								html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "</div></td>";
								html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "</div></td>";
								html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "</div></td>";
								html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
								html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
								html_comb += "</div></td>";
								html_comb += "</tr>";
								$(".comb_answer tr:eq(" + index + ")", $box).after(html_comb);
							} else if ($tr.index() != index) {
								$tr.find('.combBank').html('第' + formSeq((index + 1)) + '空');
								$tr.find('textarea').each(
												function(index) {
													this.name = (index % 2 == 0 ? 'comb_'
															: 'comb_txt_')
															+questionIndex+"_"
															+ alt
															+ '_'
															+ (((index + 2) / 2) >> 0);
												});
								$(".comb_answer tr:eq(" + index + ")", $box).after($tr);
							}
						});
						$(".indep_answer tr:gt(" + $fillChar.length + ")", $box).remove();
						$(".comb_answer tr:gt(" + $fillChar.length + ")", $box).remove();
						$tmp.remove();
						//如果空少于2个不显示答案类型和给分类型
						if (len <= 1) {
							$("input.rdo_answerType:first", $box).get(0).checked = true;
							$("input.rdo_scoreType:first", $box).get(0).checked = true;
							$(".comb_answer", $box).hide();
							$(".indep_answer", $box).show();
							$(".answerTypeDiv", $box).hide();
							$(".scoreTypeDiv", $box).hide();
						} else {
							$(".answerTypeDiv", $box).show();
							$(".scoreTypeDiv", $box).show();
						}
					});
				}
					
			}
		});
	};
	
	var changeQueType = function (ty, $item) {
		$('.fillIn-Answer', $item).hide();
		//$(".edtContentQue .edui-for-blank > div", parent).hide();

		var qusetionIndex = $item.attr('data-qusetionIndex');
		var questionType=$("input[name='questionTypeOpts']:checked").val();//获得题的类型 如果是判断题则限定输入的选项内容只有一个字符
		if (ty == 'SINGLE_CHOICE' || ty == 'MULTI_CHOICE' || ty == "JUDEMENT") {
			$('.optDiv', $item).each(function () {
				$(this).html('<li class="opt">\
						<div style="background-color: #eee; padding-left: 10px; width: 99.8%; position: relative;">\
						<input type="hidden" name="questionSubArra[' + qusetionIndex + '].opt_title" value="A." /> \
						<span class="opt_title">选项A：</span> \
						<span class="content_title"> </span> \
						<input type="hidden" class="content_title_input" name="questionSubArra[' + qusetionIndex + '].content_title" />\
						<textarea style="display: none" rows="0" cols="0" class="area" ></textarea>\
						<textarea style="display: none" rows="0" cols="0" ></textarea>\
						<input type="hidden" class="quesType" name="zzFlag" value='+questionType+'>\
						<a href="javascript:;" dir="0" class="delete red deleteItem" style="display:none" >删除</a>\
					</div>\
				</li>\
				<li class="opt">\
					<div style="background-color: #eee; padding-left: 10px; width: 99.8%; position: relative;">\
						<input type="hidden" name="questionSubArra[' + qusetionIndex + '].opt_title" value="B." /> \
						<span class="opt_title" >选项B：</span> \
						<span class="content_title" ></span> \
						<input type="hidden" class="content_title_input" name="questionSubArra[' + qusetionIndex + '].content_title"  />\
						<textarea style="display: none" rows="0" cols="0" class="area" ></textarea>\
						<textarea style="display: none" rows="0" cols="0" ></textarea>\
						<input type="hidden" class="quesType" name="zzFlag" value='+questionType+'>\
						<a href="javascript:;" dir="0" class="delete red deleteItem" style="display:none" >删除</a>\
					</div>\
				</li>' + (ty == "JUDEMENT" ? '' : '<li>\
					<a class="addOpt" href="javascript:;">+添加选项</a>\
				</li>'));
			});
			if (ty == 'SINGLE_CHOICE' || ty == "JUDEMENT") {
				$('.singleOpt-Answer', $item).html('<li>\
							<label>正确答案：</label>\
							<span class="opt_Answer">\
								<span class="opt_AnswerItem"><label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_singleOpt" value="A" /><span>选项A</span></label></span>\
								<span class="opt_AnswerItem"><label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_singleOpt" class="ml20" value="B" /><span>选项B</span></label></span>\
							</span>\
						</li>');
			} else {	
				$('.singleOpt-Answer', $item).html('<li>\
								<label>正确答案：</label>\
								<span class="opt_Answer">\
									<span class="opt_AnswerItem"><label><input type="checkbox" name="questionSubArra[' + qusetionIndex + '].chk_multOpt" value="A" /><span>选项A</span></label></span>\
									<span class="opt_AnswerItem"><label><input type="checkbox" name="questionSubArra[' + qusetionIndex + '].chk_multOpt" class="ml20" value="B" /><span>选项B</span></label></span>\
								</span>\
							</li>');
			}
		} else  {
			$('.optDiv', $item).html('');
			$('.singleOpt-Answer', $item).html('');
			if (ty == 'FILL_IN_BLANK') {
				$('.fillIn-Answer', $item).show();
				$(".edtContentQue .edui-for-blank > div", $item).show();
			}
		}
		if (ty == 'FILL_IN_BLANK') {
			$(".edtContentQue .edui-for-blank > div", $item).show();
		} else {
			$(".edtContentQue .edui-for-blank > div", $item).hide();
		}
	};
	
	$('.questionTypeOpts').on('click', 'input', function () {
		var ty = this.value;
		$('.questionItem').each(function () {
			changeQueType(ty, $(this));
		});
	});
	
	var indexTitle = 'A|B|C|D|E|F|G|H'.split('|');
	$('.mainUl').on('click', '.addOpt', function () {	
		var $elm = $(this);
		var $OptDiv=$elm.parents(".optDiv");
		if($OptDiv.find("li").size()>8){//选项多于8个将不能进行追加  隐藏添加选项链接
		   Win.alert("最多可添加8个选项!");
		   $elm.hide();
		    return;
		} 
		var ty = $("input[name='questionTypeOpts']:checked").val();
		var $elm = $(this);
		var $box = $elm.parents('.questionItem');
		var qusetionIndex = $box.attr('data-qusetionIndex');
		if (ty == 'SINGLE_CHOICE' || ty == 'MULTI_CHOICE') {
			$box.find('.optDiv .deleteItem').hide();
			var lis = $box.find('.optDiv li');
			var title = indexTitle[lis.length - 1];
			$(lis[lis.length - 2]).after('<li class="opt">\
								<div class="verticalMiddle" style="background-color: #eee; padding-left: 10px; width: 99.8%; position: relative;">\
									<input type="hidden" name="questionSubArra[' + qusetionIndex + '].opt_title" value="' + title + '." /> \
									<span class="opt_title" >选项' + title + '：</span> \
									<span class="content_title" ></span> \
									<input type="hidden" class="content_title_input" name="questionSubArra[' + qusetionIndex + '].content_title" />\
									<textarea style="display: none" rows="0" cols="0" class="area" ></textarea>\
									<textarea style="display: none" rows="0" cols="0" ></textarea>\
									<a href="javascript:;" dir="0" class="delete red deleteItem" >删除</a>\
								</div>\
							</li>');
			var lis = $box.find('.opt_Answer .opt_AnswerItem');
			if (ty == 'SINGLE_CHOICE') {
				$box.find('.opt_Answer').append('<span class="opt_AnswerItem"><label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_singleOpt" class="ml20" value="' + title + '" /><span>选项' + title + '</span></label></span>');
			} else {
				$box.find('.opt_Answer').append('<span class="opt_AnswerItem"><label><input type="checkbox" name="questionSubArra[' + qusetionIndex + '].chk_multOpt" class="ml20" value="' + title + '" /><span>选项' + title + '</span></label></span>');
			}
		}
		
		//如果选项数等于2则隐藏删除链接的显示(默认隐藏)，当选项数大于两个则显示(添加选项算一个li的个数)
		if($(".optDiv li").size()>3){
			//显示
			$(".optDiv li div a").css("display","block");
		}	
	}).on('click', '.optDiv li .deleteItem', function () {
		var $elm = $(this);
		var $OptDiv=$elm.parents(".optDiv");
		var $box = $elm.parents('.questionItem');
		var $item = $elm.parent().parent();
		$item.prev().find('.deleteItem').show();
		$item.remove();
		$box.find('.opt_AnswerItem:last').remove();
		
		if(3==$OptDiv.find("li").size()){
			//隐藏两个选项的删除链接
			$OptDiv.find(" li div a").css("display","none");
		}
		
		//删除之后对选项A,B,C,D,E,F,G,H进行重新排序(并同步更新隐藏域的对应选项)
		   //1.获得当前删除之后选项的个数(获得的选项数-1)  (1指的是添加选项的链接)
		   var currentCountNumber =$OptDiv.find("li").size()-1;
		   //alert("currentNum="+currentCountNumber);//数组的大小
		   var optionArr;
		   var optionValue;
		   if(7==currentCountNumber){
			   optionArr=["选项A：","选项B：","选项C：","选项D：","选项E：","选项F：","选项G："]; 
			   optionValue=["A.","B.","C.","D.","E.","F.","G."];
		   }
		   if(6==currentCountNumber){
			   optionArr=["选项A：","选项B：","选项C：","选项D：","选项E：","选项F："]; 
			   optionValue=["A.","B.","C.","D.","E.","F."];
		   }
		   if(5==currentCountNumber){
			   optionArr=["选项A：","选项B：","选项C：","选项D：","选项E："]; 
			   optionValue=["A.","B.","C.","D.","E."];
		   }
		   if(4==currentCountNumber){
			   optionArr=["选项A：","选项B：","选项C：","选项D："]; 
			   optionValue=["A.","B.","C.","D."];
		   }
		   if(3==currentCountNumber){
			   optionArr=["选项A：","选项B：","选项C："]; 
			   optionValue=["A.","B.","C."];
		   }
		   if(2==currentCountNumber){
			   optionArr=["选项A：","选项B："]; 
			   optionValue=["A.","B."];
		   }
		   
		   //重新设置选项与隐藏域的序号(添加选项的li不包含在内)
	 	   for(var i=0; i<currentCountNumber; i++){
			   
			$($OptDiv.find("li div")[i]).children("span").get(0).innerHTML=optionArr[i];
			$($OptDiv.find("li div")[i]).children("input").get(0).value=optionValue[i];
		   }
		 
		//显示添加选项
		$(".addOpt").show();
	}).on('click', '.opt', function () {
		optContentCell = this;
		Win.open({
			title : "输入选项内容",
			width : 850,
			height : 500,
			url : "/OnlineTest/questionLib/openueditpage.do",
			mask : true
		});
	}).on('click', '.opt .deleteItem', function (e) {
		return false;
	}).on('click', '.indep_answer .edit-pop-btn', function (e) {
		optAnswerCell = this;
		Win.open({
			title : "输入答案",
			width : 850,
			height : 500,
			url : "/OnlineTest/questionLib/openfillpage.do",
			mask : true
		});
	}).on('click', '.comb_answer .edit-pop-btn', function (e) {
		optAnswerCell = this;
		Win.open({
			title : "输入答案",
			width : 850,
			height : 500,
			url : "/OnlineTest/questionLib/openfillpage.do",
			mask : true
		});
	}).on('click', '.deleteQueSubItem', function () {
		//获得删除的子题的下标
		var num_index=$(this).parents('.questionSubItem').attr("data-qusetionIndex");
		deleQuestionStr+=num_index+",";
		//alert("deleIndex="+deleQuestionStr);
		$("#deleStr").val(deleQuestionStr);//将已删除的题目的下标进行记录
		$(this).parents('.questionSubItem').remove();
		
	}).on('click', '.rdo_answerType', function () {
		var type = this.value;
		var $box = $(this).parents('.questionItem');
		if (type == '1') {//独立答案
     		$(".indep_answer", $box).show();
     	    $(".comb_answer", $box).hide();
     	} else if(type == '2') {//组合答案
     		$(".indep_answer", $box).hide();
     	    $(".comb_answer", $box).show();
     	}
	});

	loadUEdit();
	$("input[name='questionTypeOpts']:eq(0)").click();
	
	
	
	//-------------------章节start---------------------
	//年级级联学段
	$("#semesterOption").change(function() { 
		//获得当前选中的学段的id
		var checkedOption = $("#semesterOption").val();
		$.post(root+"/commons/classLevelBySemId.do?semesterId=" + checkedOption, function(data) {
			//alert(data.length);
			var html = '<option value="0">请选择</option>';
			for (var i = 0; i < data.length; i++) {
				html += "<option value="+data[i].baseClasslevelId+">" + data[i].classlevelName + "</option>";
			}
			$("#classLevelOption").html(html);
		}, 'json');
		
		//将学科置为初始化状态
		$("#subjOption").empty();
		$("#subjOption").append(emptyOpt);
		
		$("#versionOption").empty();
		$("#versionOption").append(emptyOpt);
		
		$("#volumeOption").empty();
		$("#volumeOption").append(emptyOpt);
		
		$("#chapterOption").empty();
		$("#chapterOption").append(emptyOpt);
		
		$("#sectionOption").empty();
		$("#sectionOption").append(emptyOpt);
		
		//初始化知识点
	    $("#knowledgeOpts").empty();
   	    $("#knowledgeOpts").append(emptyOpt);
   	    
        $("#knowledgeOpts1").empty();
   	    $("#knowledgeOpts1").append(emptyOpt);
   	    $("#knowledgeOpts1").hide();
   	    
   	    $("#knowledgeOpts2").empty();
   	    $("#knowledgeOpts2").append(emptyOpt);
   	    $("#knowledgeOpts2").hide();
   	    
   	    $("#knowledgeOpts3").empty();
   	    $("#knowledgeOpts3").append(emptyOpt);
   	    $("#knowledgeOpts3").hide();
	
   	    $("#knowledgeOpts4").empty();
   	    $("#knowledgeOpts4").append(emptyOpt);
   	    $("#knowledgeOpts4").hide();
   	    
   	    $("#knowledgeOpts5").empty();
   	    $("#knowledgeOpts5").append(emptyOpt);
   	    $("#knowledgeOpts5").hide();
	});
	//学科级联年级   
	$("#classLevelOption").change(function() {
		//获得当前选中的年级的id
		var classLevelOption = $("#classLevelOption").val();
		$.post(root+"/commons/subjbyclassid.do?classLevelId=" + classLevelOption, function(data) {
			var html = '<option value="0">请选择</option>';
			for (var i = 0; i < data.length; i++) {
				html += "<option value="+data[i].baseSubjectId+">" + data[i].baseSubjectName + "</option>";
			}
			$("#subjOption").html(html);
		}, 'json');
		
		//初始化版本分册章节
		$("#versionOption").empty();
		$("#versionOption").append(emptyOpt);
		
		$("#volumeOption").empty();
		$("#volumeOption").append(emptyOpt);
		
		$("#chapterOption").empty();
		$("#chapterOption").append(emptyOpt);
		
		$("#sectionOption").empty();
		$("#sectionOption").append(emptyOpt);
		
		//初始化知识点
		$("#knowledgeOpts").empty();
   	    $("#knowledgeOpts").append(emptyOpt);
   	    
   	    $("#knowledgeOpts1").empty();
   	    $("#knowledgeOpts1").append(emptyOpt);
   	    $("#knowledgeOpts1").hide();
   	    
   	    $("#knowledgeOpts2").empty();
   	    $("#knowledgeOpts2").append(emptyOpt);
   	    $("#knowledgeOpts2").hide();
   	    
   	    $("#knowledgeOpts3").empty();
   	    $("#knowledgeOpts3").append(emptyOpt);
   	    $("#knowledgeOpts3").hide();
	
   	    $("#knowledgeOpts4").empty();
   	    $("#knowledgeOpts4").append(emptyOpt);
   	    $("#knowledgeOpts4").hide();
   	    
   	    $("#knowledgeOpts5").empty();
   	    $("#knowledgeOpts5").append(emptyOpt);
   	    $("#knowledgeOpts5").hide();
	});
	//学科级联版本
	$("#subjOption").change(
			function() {
				//获得当前选中学科id
				var subjOption = $("#subjOption").val();
				//获得当前选中的年级的id
				var classLevelOption = $("#classLevelOption").val();
				$.post(root+"/commons/versionbysuclaid.do?baseClassLevelId=" + classLevelOption
						+ "&baseSubjId=" + subjOption, function(data) {
					var html = '<option value="0">请选择</option>';
					for (var i = 0; i < data.length; i++) {
						html += "<option value="+data[i].baseVersionId+">" + data[i].versionName + "</option>";
					}
					$("#versionOption").html(html);
				}, 'json');
				
				//初始化知识点
				 //绑定首节知识点
		   	    var semesterId = $("#semesterOption").val();//获得学段
		   	    bindKnowledgeBySemesterAndDiscipline(semesterId,subjOption);
		   	    
		   	    
		   	//初始化分册章节
				$("#volumeOption").empty();
				$("#volumeOption").append(emptyOpt);
				
				$("#chapterOption").empty();
				$("#chapterOption").append(emptyOpt);
				
				$("#sectionOption").empty();
				$("#sectionOption").append(emptyOpt);
				
		   	    
		   	    $("#knowledgeOpts1").empty();
		   	    $("#knowledgeOpts1").append(emptyOpt);
		   	    $("#knowledgeOpts1").hide();
		   	    
		   	    $("#knowledgeOpts2").empty();
		   	    $("#knowledgeOpts2").append(emptyOpt);
		   	    $("#knowledgeOpts2").hide();
		   	    
		   	    $("#knowledgeOpts3").empty();
		   	    $("#knowledgeOpts3").append(emptyOpt);
		   	    $("#knowledgeOpts3").hide();
			
		   	    $("#knowledgeOpts4").empty();
		   	    $("#knowledgeOpts4").append(emptyOpt);
		   	    $("#knowledgeOpts4").hide();
		   	    
		   	    $("#knowledgeOpts5").empty();
		   	    $("#knowledgeOpts5").append(emptyOpt);
		   	    $("#knowledgeOpts5").hide();
		   	    
		   	    $("#selectedKnowledges span").remove();
		   	    $("#selectedChapters span").remove();
			});
	//版本级联分册
	$("#versionOption").change(
			function() {

				//获得当前选中学科id
				var subjOption = $("#subjOption").val();
				//获得当前选中的年级的id
				var classLevelOption = $("#classLevelOption").val();
				//获得当前选中的版本的id
				var versionOption = $("#versionOption").val();
				$.post(root+"/commons/volumeversionbyverId.do?baseSubjId=" + subjOption + "&baseClassLevId="
						+ classLevelOption + "&baseVersionId=" + versionOption, function(data) {
					var html = '<option value="0">请选择</option>';
					for (var i = 0; i < data.length; i++) {
						html += "<option value="+data[i].baseVolumeId+">" + data[i].volumeName + "</option>";
					}
					$("#volumeOption").html(html);
					//将章节置空    先清空再添加
					$("#chapterOption").empty();
					$("#chapterOption").append(emptyOpt);
					
					$("#sectionOption").empty();
					$("#sectionOption").append(emptyOpt);
				}, 'json');
				
			});

	//分册级联章节
	$("#volumeOption").change(
			function() {

				//获得当前选中学科id
				var subjOption = $("#subjOption").val();
				//获得当前选中的年级的id
				var classLevelOption = $("#classLevelOption").val();
				//获得当前选中的版本的id
				var versionOption = $("#versionOption").val();
				//获得当前选中的分册的id
				var volumeOption = $("#volumeOption").val();
				$.post(root+"/commons/chaptervolumebycid.do?baseVersionId=" + versionOption + "&baseSubjId="
						+ subjOption + "&baseClassLevId=" + classLevelOption + "&baseVolumeId=" + volumeOption,
						function(data) {
							var html = '<option value="0">请选择</option>';
							for (var i = 0; i < data.length; i++) {
								html += "<option value="+data[i].baseChapterId+">" + data[i].chapterName
										+ "</option>";
							}
							$("#chapterOption").html(html);
							$("#sectionOption").empty();
							$("#sectionOption").append(emptyOpt);
						}, 'json');
			});

	//节级联章
	$("#chapterOption").change(
			function() {

				//获得当前选中学科id
				var subjOption = $("#subjOption").val();
				//获得当前选中的年级的id
				var classLevelOption = $("#classLevelOption").val();
				//获得当前选中的版本的id
				var versionOption = $("#versionOption").val();
				//获得当前选中的分册的id
				var volumeOption = $("#volumeOption").val();
				//获得当前选中的章
				var chapterOption = $("#chapterOption").val();

				$.post(root+"/commons/sectionchapbycid.do?baseVersionId=" + versionOption + "&baseSubjId="
						+ subjOption + "&baseClassLevId=" + classLevelOption + "&baseChapterId="
						+ chapterOption + "&baseVolumeId=" + volumeOption, function(data) {
					var html = '<option value="0">请选择</option>';
					for (var i = 0; i < data.length; i++) {
						html += "<option value="+data[i].sectionId+">" + data[i].sectionName + "</option>";
					}
					$("#sectionOption").html(html);
				}, 'json');
			});

	//增加章节
	$("#addChapterBtn").click(
			function() {
				var chapterCnt = $("#selectedChapters span").size();//div中添加span标签来判断添加的章节的个数
				//alert("chapterCnt.size="+chapterCnt);
				
				if (chapterCnt >= 5) {
					Win.alert("最多只能添加五个关联章节！");
					return;
				}

				var versionId = $("#versionOption option:selected").val();//$("#versionOpts").val();  获得选中的版本对应的id
				if (versionId == 0 || versionId == "" || versionId == undefined) {
					Win.alert("请至少选择到版本！");
					return;
				}

				var semesterName = $("#semesterOption option:selected").html(); //学段名称
				var classLevelName = $("#classLevelOption option:selected").html(); //年级名称
				var disciplineName = $("#subjOption option:selected").html(); //学科名称 
				var versionName = $("#versionOption option:selected").html(); //版本名称
				var volumeName = $("#volumeOption option:selected").html(); //分册名称
				var chapterName = $("#chapterOption option:selected").html(); //章名称
				var sectionName = $("#sectionOption option:selected").html(); //节名称
				
				var versionId=$("#versionOption").val();//获得选中的版本的id
				var volumeId = $("#volumeOption").val();//获得选中分册的id
				var chapterId = $("#chapterOption").val();//获得选中的章的id
				var sectionId = $("#sectionOption").val();//获得选中的节的id
				/* alert("获得的版本分册章节的id="+versionId+"-->"+volumeId+"-->"+chapterId+"-->"+sectionId); */
				
				var connChar = "-->";
				var connChapterName =  versionName; //使用-->符号将学段-->年级-->学科-->版本  进行拼接成一个字符串
				/* alert("拼接好选中的从学段到节的名字字符串的拼接="+connChapterName); */
				
				var connChapterIds = versionId;
				if (volumeId != '0' && volumeId != '') {
					connChapterName += connChar + volumeName;
					connChapterIds += ':' + volumeId;
					endNodeId = volumeId;
				} else {
					connChapterIds += ':0';
				}
				;

				if (chapterId != '0' && chapterId != '') {
					connChapterName += connChar + chapterName;
					connChapterIds += ':' + chapterId;
					endNodeId = chapterId;
				} else {
					connChapterIds += ':0';
				}
				;

				if (sectionId != '0' && sectionId != '') {
					connChapterName += connChar + sectionName;
					connChapterIds += ':' + sectionId;
					endNodeId = sectionId;
				} else {
					connChapterIds += ':0';
				}
				;

				//判断该节点重复性
				var repFlag = false;
				$("#selectedChapters span").each(function(i, n) {
					var child = $(n).attr("child");
					if (connChapterIds == child) {
						repFlag = true;
					}
				});

				if (repFlag) {
					Win.alert("该章节已添加过！");
					return;
				}
				
				 var reg=new RegExp(":","g");
				 var valObj =connChapterIds.replace(reg,"");  //将逗号替换为

				 /* alert("connChapterIds="+connChapterIds); */   //获得添加的版本,分册,章,节  如果没有选择的用0代替   之间使用逗号隔开
				 //表明新增的san元素的child属性即是已选的所有章节的字符串(之间采用,隔开)   connChapterName为从学段到筛选的最后一个节点
				//增加该节点
				
				//每次在后面追加一个筛选好的版本，分册，章节的隐藏域,而springmvc会自动将他们封装成一个数组   每个数组元素是一个关联的对象
				var connHtml = "<span class=\"smallBlock chapBlock itemDel\" style=\"height: 28px;\" child ='"+connChapterIds+"' id='"+valObj+"'>"
						+ connChapterName + "<a href=\"javascript:remo('"+valObj+"')\"></a><input type=\"hidden\" name=\"ch_input\" value='"+connChapterIds+"'/></span>";
				$("#selectedChapters").append(connHtml);
			});
	//------------章节end--------------------------
	
});		

function remo(obj){//删除指定添加的章节
	var obj="#"+obj;
  	$(obj).next().remove();
  	$(obj).remove();
}

//进行添加知识点的操作
		 $("#addKnowledgeBtn").click(function(){
	           var knowledgeCnt = $("#selectedKnowledges span").size();
	           if(knowledgeCnt >= 5){
		           Win.alert("最多只能添加五个关联知识点！");
		           return;
	             }
	 
	          var knowledgeId = $("#knowledgeOpts").val();
	          
	           if(knowledgeId ==0 || knowledgeId == ""){
		                  Win.alert("请至少选择到第一节知识点！");
		                    return;
	                      } 
	 
	 //增加该节点
	 var semesterName = $("#semesterOption option:selected").html(); //学段名称
	 var classLevelName = $("#classLevelOption option:selected").html(); //年级名称
	 var disciplineName = $("#subjOption option:selected").html(); //学科名称 
	 var knowledgeName = $("#knowledgeOpts option:selected").html();//父级知识点名称
	 var subKnowledgeId1 = $("#knowledgeOpts1").val();
	 var subKnowledgeName1 = $("#knowledgeOpts1 option:selected").html();//一级子知识点
	 var subKnowledgeId2 = $("#knowledgeOpts2").val();
	 var subKnowledgeName2 = $("#knowledgeOpts2 option:selected").html();//二级子知识点
	 var subKnowledgeId3 = $("#knowledgeOpts3").val();
	 var subKnowledgeName3 = $("#knowledgeOpts3 option:selected").html();//三级子知识点
	 var subKnowledgeId4 = $("#knowledgeOpts4").val();
	 var subKnowledgeName4 = $("#knowledgeOpts4 option:selected").html();//四级子知识点
	 var subKnowledgeId5 = $("#knowledgeOpts5").val();
	 var subKnowledgeName5 = $("#knowledgeOpts5 option:selected").html();//五级子知识点
	 
	
	 var connChar = "-->";
	 var connKnowledgeName = knowledgeName;
	 var connKnowledgeIds = knowledgeId;
	 
	 if(subKnowledgeId1 != '0' && subKnowledgeId1 != ''){
		 connKnowledgeName += connChar + subKnowledgeName1;
		 connKnowledgeIds += ':'+ subKnowledgeId1;
	 } else {
		 connKnowledgeIds += ':0';
	 };
	 
	 if(subKnowledgeId2 != '0' && subKnowledgeId2 != ''){
		 connKnowledgeName += connChar + subKnowledgeName2;
		 connKnowledgeIds += ':'+ subKnowledgeId2;
	 } else {
		 connKnowledgeIds += ':0';
	 };
	 
	 if(subKnowledgeId3 != '0' && subKnowledgeId3 != ''){
		 connKnowledgeName += connChar + subKnowledgeName3;
		 connKnowledgeIds += ':'+ subKnowledgeId3;
	 } else {
		 connKnowledgeIds += ':0';
	 };
	 
	 if(subKnowledgeId4 != '0' && subKnowledgeId4 != ''){
		 connKnowledgeName += connChar + subKnowledgeName4;
		 connKnowledgeIds += ':'+ subKnowledgeId4;
	 } else {
		 connKnowledgeIds += ':0';
	 };
	 
	 if(subKnowledgeId5 != '0' && subKnowledgeId5 != ''){
		 connKnowledgeName += connChar + subKnowledgeName5;
		 connKnowledgeIds += ':'+ subKnowledgeId5;
	 } else {
		 connKnowledgeIds += ':0';
	 };
	 
	 //判断该节点重复性
	 var repFlag = false;
	 $("#selectedKnowledges span").each(function(i,n){
		 var child = $(n).attr("child");
		 if(connKnowledgeIds == child){
			 repFlag = true;
		 }
	 });
	 
	 if(repFlag){
		 Win.alert("该关联知识点已添加");
		 return;
	 } 
	  
	 var reg=new RegExp(":","g");
	 var valKnowObj =connKnowledgeIds.replace(reg,"");  //将逗号替换为
	 
	 var connHtml = "<span class=\"smallBlock chapBlock itemDel\" style=\"height: 28px;\" child ='"+connKnowledgeIds+"'  id='"+valKnowObj+"'>" + connKnowledgeName + "<a href=\"javascript:remoKnowledge('"+valKnowObj+"')\"></a><input type=\"hidden\" name=\"k_input\" value='"+connKnowledgeIds+"'/></span>";
	 $("#selectedKnowledges").append(connHtml);
 });
 $("select[id^='knowledgeOpts']").not("#knowledgeOpts").hide();//隐藏其他的知识点框
 
 
 //-------------知识点 start----------------------
 function remoKnowledge(obj){//删除指定添加的知识点
	var obj="#"+obj;
    $(obj).next().remove();
    $(obj).remove();
 }
 
//选择不同的知识点事件(根据)
 $("#knowledgeOpts").change(function(){
	 var knowledgeId = this.value;
	 bindSubKnowledgeByParentId(this.value,function(data){
		    var optObj = $("#knowledgeOpts1");
			if(data.length > 0){					
				optObj.show();
			} else {
				optObj.hide();
			}
			bindSubKnowledgeData(optObj,data);         //------------------
			
		    $("#knowledgeOpts2").empty();
	   	    $("#knowledgeOpts2").append(emptyOpt);
	   	    $("#knowledgeOpts2").hide();
	   	    
	   	    $("#knowledgeOpts3").empty();
	   	    $("#knowledgeOpts3").append(emptyOpt);
	   	    $("#knowledgeOpts3").hide();
		
	   	    $("#knowledgeOpts4").empty();
	   	    $("#knowledgeOpts4").append(emptyOpt);
	   	    $("#knowledgeOpts4").hide();
	   	    
	   	    $("#knowledgeOpts5").empty();
	   	    $("#knowledgeOpts5").append(emptyOpt);
	   	    $("#knowledgeOpts5").hide();				
	 });
	 
 });
 
 
 $("#knowledgeOpts1").change(function(){
	 var knowledgeId = this.value;
	 bindSubKnowledgeByParentId(this.value,function(data){
		    var optObj = $("#knowledgeOpts2");
			if(data.length > 0){						
				optObj.show();
			} else {
				optObj.hide();
			}
			bindSubKnowledgeData(optObj,data);
	   	    
	   	    $("#knowledgeOpts3").empty();
	   	    $("#knowledgeOpts3").append(emptyOpt);
	   	    $("#knowledgeOpts3").hide();
		
	   	    $("#knowledgeOpts4").empty();
	   	    $("#knowledgeOpts4").append(emptyOpt);
	   	    $("#knowledgeOpts4").hide();
	   	    
	   	    $("#knowledgeOpts5").empty();
	   	    $("#knowledgeOpts5").append(emptyOpt);
	   	    $("#knowledgeOpts5").hide();		
	 });
 });
 
 $("#knowledgeOpts2").change(function(){
	 var knowledgeId = this.value;
	 bindSubKnowledgeByParentId(this.value,function(data){
			var optObj = $("#knowledgeOpts3");
			if(data.length > 0){
				optObj.show();
			} else {
				optObj.hide();
			}
			bindSubKnowledgeData(optObj,data);
			
		
	   	    $("#knowledgeOpts4").empty();
	   	    $("#knowledgeOpts4").append(emptyOpt);
	   	    $("#knowledgeOpts4").hide();
	   	    
	   	    $("#knowledgeOpts5").empty();
	   	    $("#knowledgeOpts5").append(emptyOpt);
	   	    $("#knowledgeOpts5").hide();				
	 });
 });
 
 $("#knowledgeOpts3").change(function(){
	 var knowledgeId = this.value;
	 bindSubKnowledgeByParentId(this.value,function(data){
		    var optObj = $("#knowledgeOpts4");
			if(data.length > 0){						
				optObj.show();
			} else {
				optObj.hide();
			}
			bindSubKnowledgeData(optObj,data);
	   	    
	   	    $("#knowledgeOpts5").empty();
	   	    $("#knowledgeOpts5").append(emptyOpt);
	   	    $("#knowledgeOpts5").hide();				
	 });
 });
 
 $("#knowledgeOpts4").change(function(){
	 var knowledgeId = this.value;
	 bindSubKnowledgeByParentId(this.value,function(data){//-----------------------------------
			var optObj = $("#knowledgeOpts5");
			if(data.length > 0){
				optObj.show();
			} else {
				optObj.hide();
			}
			bindSubKnowledgeData(optObj,data);				
	 });
 });
 
/*  $('#selectedKnowledges').on('click','span a', function(e){
		$(this).parent().remove();//移除页面中显示的知识点元素
  }); */
//-------------知识点 end----------------------

//题目难易度转换
function switchDifficulty(str){
	switch (str){
		case "EASY" : return "容易"; break;
		case "LITTLE_EASY" : return "较容易"; break ;
		case "NORMAL" : return "一般" ; break ;
		case "LITTLE_DIFFICULT" : return "较难" ; break ;
		case "DIFFICULT" : return "困难" ; break;
		default : return "";
	}
}
//添加试题
$("#addExamQue").on("click",function(){
	if(questionCount >= 100){
		Win.alert("真题试卷最多添加100道题！");
		return false;
	}
	// 增加校验
	var flag = true;
	$(".uploaded").each(function() {
		var text = $(this).text();
		if (text != "" || text != null) {
			Win.alert("视频正在上传中，请稍等!");
			flag = false;
			return false;
		}
	});
	if (!flag) {
		return false;
	}

	// 对题干内容大小和解析的内容大小进行判断
	var editorArr = $(".edtContent");
	for (var i = 0, j = editorArr.length; i < j; i++) {
		var id = $(editorArr[i]).attr("id");
		var numIndex = id.substring(id.length - 1);// 奇数表示题干 偶数表示解析
		var questionContent = $.trim(UE.getEditor(id).getContentTxt());// 获得纯文本内容
		var contentLen = UE.getEditor(id).getContentLength(true);
		if (numIndex % 2 != 0) {// 表示是题干
			if ("" == questionContent && contentLen == 0) {
				Win.alert("请输入题干！");
				return;
			}
			if (contentLen > 1000) {
				Win.alert("题干内容不能超过1000字符！");
				return;
			}
		} else {// 表示是解析
			if ("" == questionContent && contentLen == 0) {
				Win.alert("请输入习题解析！");
				return;
			}
			if (contentLen > 1000) {
				Win.alert("解析内容不能超过1000字符！");
				return;
			}
		}
	}

	// 对多选，单选，判断题的选项内容进行验证
	var questionType = $("input[name='questionTypeOpts']:checked").val();
	if (questionType == "SINGLE_CHOICE" || questionType == "MULTI_CHOICE" || questionType == "JUDEMENT") {

		var editOptionArr = $(".optDiv li div ");
		for (var i = 0; i < editOptionArr.length; i++) {
			var optText = $(editOptionArr[i]).children("input").get(0).value;
			optText = optText.substring(0, optText.length - 1);
			optContent = $(editOptionArr[i]).children("span").get(1).innerHTML;

			if (" " == optContent || "" == optContent) {
				Win.alert("请填写选项" + optText + "!");
				return;
			}
		}
	}

	// 单选题 ，判断题的正确答案必须有一个选中的
	if (questionType == "SINGLE_CHOICE" || questionType == "JUDEMENT") {
		var optionArr = $(".opt_Answer");
		var flages = false;// 默认验证不通过
		for (var i = 0; i < optionArr.length; i++) {
			var optSonObjArr = $(optionArr[i]).children("span").children("label").children("input");// 获得每个opt_Answer对象
			for (var j = 0; j < optSonObjArr.length; j++) {
				if (optSonObjArr[j].checked == true) {
					flages = true;// 如果有一个正确答案被选中则通过验证(循环遍历每道题)
					break;// 如果有一个选中则跳出当前循环
				} else {
					flages = false;// 若没选中则重新赋值
				}
			}

			if (flages == false) {
				Win.alert("还有题目的正确答案没有进行选择!");
				return;
			}

		}

	}

	// 多选题的正确答案至少有两个选中的
	if (questionType == "MULTI_CHOICE") {

		var optionArr = $(".opt_Answer");
		var flages = false;// 默认验证不通过
		for (var i = 0; i < optionArr.length; i++) {
			var optSonObjArr = $(optionArr[i]).children("span").children("label").children("input");// 获得每个opt_Answer对象
			var count = 0;// 判断每道多选题选中的个数 每次初始化每道题的多选答案选中的个数
			for (var j = 0; j < optSonObjArr.length; j++) {
				if (optSonObjArr[j].checked == true) {
					count++;
					if (count >= 2) {// 至少有两道多选题答案
						flages = true;// 如果有一个正确答案被选中则通过验证(循环遍历每道题)
						break;// 如果有一个选中则跳出当前循环
					}
				} else {
					flages = false;// 若没选中则重新赋值
				}
			}

			if (flages == false) {
				Win.alert("多选题的答案未选或选择的答案少于两种!");
				return;
			}

		}
	}

  	 //对填空题进行校验
    if (questionType == "FILL_IN_BLANK") {
	// 1.获取所有填空题的空的个数
	var FillCountArr = $(".fillCount");
	// 2.获得每道填空题是独立答案还是组合答案的数组
	var answerTypeArr = $(".answerTypeDiv label input:checked");

	for (var i = 0; i < FillCountArr.length; i++) {// 题目的个数和填空题数组的大小一致(因为一道题一个隐藏域)
		var questionCount_1 = $(FillCountArr[i]).val();// 获得目前每道填空题的空的个数
		var answerTypeValue = $(answerTypeArr[i]).val();// 答案类型
		var questionIndex = $(FillCountArr[i]).attr("id");
		questionIndex = questionIndex.substring(questionIndex.length - 1);// 获得每道题的下标
		if (questionCount_1 == 0) {// 表示题干中未填充填空题
			Win.alert("请至少设置一个填空位！");
			return;
		} else {// 表示题干中含有填空题

			if ("1" == answerTypeValue) {// 表示是独立答案
				for (var j = 1; j <= questionCount_1; j++) {

					// 获得每行的所有值
					var indep_1 = $.trim($("textarea[name='indep_" + questionIndex + "_" + j + "_1']")
							.text());
					var indep_2 = $.trim($("textarea[name='indep_" + questionIndex + "_" + j + "_2']")
							.text());
					var indep_3 = $.trim($("textarea[name='indep_" + questionIndex + "_" + j + "_3']")
							.text());
					var indep_4 = $.trim($("textarea[name='indep_" + questionIndex + "_" + j + "_4']")
							.text());
					if ("" == indep_1 && "" == indep_2 && "" == indep_3 && "" == indep_4) {// 当每行全空的时候进行提示(每行至少有一个答案，不能全空)
						Win.alert("每空至少设置一个答案!");
						return;
					}
					if ("" != indep_4) {// 如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
						if ("" == indep_3 || "" == indep_2 || "" == indep_1) {
							Win.alert("请按顺序填写答案!");
							return;
						}
					}
					if ("" != indep_3 && "" == indep_4) {// 如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
						if ("" == indep_2 || "" == indep_1) {
							Win.alert("请按顺序填写答案!");
							return;
						}
					}
					if ("" != indep_2 && "" == indep_3 && "" == indep_4) {// 如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
						if ("" == indep_1) {
							Win.alert("请按顺序填写答案!");
							return;
						}
					}

				}
			}

			if ("2" == answerTypeValue) {// 组合答案

				var recoredCloumStatus = "";
				var count;
				for (var k = 1; k < 5; k++) {
					count = 0;// 来与总的空的个数进行比较,如果小于总空的个数则表示不是满列
					for (var r = 1; r <= questionCount_1; r++) {
						var comb = $.trim($(
								"textarea[name='comb_" + questionIndex + "_" + r + "_" + k + "']").text());// 每列循环遍历
						if ("" != comb) {
							count++;
						}
					}
					if (1 == k) {
						if (count < questionCount_1_1) {// 表示第一列未满列
							Win.alert("请将按顺序将组合答案填全!");
							return;
						}
					}
					if (2 == k) {
						if (count < questionCount_1 && count > 0) {
							recoredCloumStatus += "0" + ",";
						} else if (count == questionCount_1) {
							recoredCloumStatus += "1" + ",";
						} else if (count == 0) {// 表示是空列
							recoredCloumStatus += "n" + ",";
						}
					}
					if (3 == k) {
						if (count < questionCount_1 && count > 0) {
							recoredCloumStatus += "0" + ",";
						} else if (count == questionCount_1) {
							recoredCloumStatus += "1" + ",";
						} else if (count == 0) {// 表示是空列
							recoredCloumStatus += "n" + ",";
						}
					}
					if (4 == k) {
						if (count < questionCount_1 && count > 0) {
							recoredCloumStatus += "0" + ",";
						} else if (count == questionCount_1) {
							recoredCloumStatus += "1";
						} else if (count == 0) {// 表示是空列
							recoredCloumStatus += "n";
						}
					}

				}
				// 获得满列以及判断满列与首列之间是非存在非满列 若存在则表示不合法
				// 获得最后一个满列 如果第一个是满列则直接通过(因为第一列一定是满列) 从第二列开始算起
				var flagArr = recoredCloumStatus.split(",");// 只有三个数字
				if (flagArr[0] == "0" || flagArr[1] == "0" || flagArr[2] == "0") {// 将所有的未满列拦截并提示
					Win.alert("请将组合答案填全!");
					return;
				}

				if (flagArr[0] == "1" && flagArr[1] == "n" && flagArr[2] == "1" || flagArr[0] == "n"
						&& flagArr[1] == "1" && flagArr[2] == "1") {
					Win.alert("请按顺序填写组合答案!");
					return;
				}

				if (flagArr[2] == "1") {// 表示最后一个是满列
					if (flagArr[0] == "0" || flagArr[1] == "0" || flagArr[0] == "n" || flagArr[1] == "n") {// 表示中间出现未满列或空列
						Win.alert("请按顺序填写的组合答案!");
						return;
					}
				}

				if (flagArr[1] == "1" && flagArr[2] == "n") {// 表示倒数第二个是满列而最后一列是空列
					if (flagArr[0] == "0" || flagArr[0] == "n") {//若倒数第三个是未满列则不满足要求
						Win.alert("请按顺序填写的组合答案!");
						return;
					}
				}
			}
		}
	}
}
   
	
	var html = "";
	var formData = $("#addForm").serialize();
    //保存临时习题
	$.post(root+"/schoolTest/createTempoparyQuestion.do", formData,
			function(data) {
				//首先清空之前的题目
				if(data && data!=null){
					var value = data;
					//用来添加设置分数页面的临时题目
					addSetScoreQuestion(value);
					
					html+='<div class="quesContainer">'
						+'<div class="quesDetail1" align="left" id="'+value.examQuestionId+'"></input>'
						+' <p class="quesDesc">'
						+'   <label>组卷次数：</label><span id="useCount" class="red">0</span>'
						+'   <label>知识点：</label><span>'+value.endKonwledges+'</span>'
						+'   <label>难易度：</label><span id="difficultyOpts">'+switchDifficulty(value.difficulty)+'</span>'
						+'   <label>更新时间：</label><span id="createTime">'+now('y-m-d h:i:s',value.updateTime)+'</span>'
						+'   <span class="fr editOrDel">'
						+'     <span onclick="editQuestion(this)" id="'+value.examQuestionId+'">编辑</span>'
						+'     <b class="c888">|</b>'
						+'     <span class="del" id="'+value.examQuestionId+'" onclick="deleteQuestion(this,\''+value.type+'\')">删除</span>'
						+'   </span>'
						+' </p>'
						+'   <div class="quesWrap show">' 
						+ value.content;
					if(value.type == "SINGLE_CHOICE" || value.type == "MULTI_CHOICE" || value.type == "JUDEMENT"){
						var optionContent = value.options.split("∷");
						optionContent.forEach(function(value,index){
							html+=value+"</br>"; 
						});
					}
					html+=' </div>'
						+'</div>'
						+'</div>';
					$("#questionList").append(html);
					//清空富文本框信息
					clearInfo();
					
					questionCount++;
					
					//修改设置分数页面的值
					var type = $("input[name='questionTypeOpts']:checked").val();
					if(type == "SINGLE_CHOICE"){
						type1+=1;
					}else if(type == "MULTI_CHOICE"){
						type2+=1;
					}else if ( type == "JUDEMENT"){
						type3 +=1;
					}else if( type == "FILL_IN_BLANK"){
						type4 +=1;
					}else if( type == "ASK_ANSWER"){
						type5 +=1;
					}else if( type == "COMPUTING"){
						type6 +=1;
					}
				}
			}
		);  
    
});

//编辑习题保存后显示
function getRealExamQuestions(value){
	//用来修改设置分数页面的临时题目
	editSetScoreQuestion(value);
	var html="";
	//首先清除原来的习题，添加编辑后的习题
	html+='<div class="quesDetail1" align="left" id="'+value.examQuestionId+'"></input>'
		+' <p class="quesDesc">'
		+'   <label>组卷次数：</label><span id="useCount" class="red">0</span>'
		+'   <label>知识点：</label><span>'+value.endKonwledges+'</span>'
		+'   <label>难易度：</label><span id="difficultyOpts">'+switchDifficulty(value.difficulty)+'</span>'
		+'   <label>更新时间：</label><span id="createTime">'+now('y-m-d h:i:s',value.updateTime)+'</span>'
		+'   <span class="fr editOrDel">'
		+'     <span onclick="editQuestion(this)" id="'+value.examQuestionId+'">编辑</span>'
		+'     <b class="c888">|</b>'
		+'     <span class="del" id="'+value.examQuestionId+'" onclick="deleteQuestion(this,\''+value.type+'\')">删除</span>'
		+'   </span>'
		+' </p>'
		+'   <div class="quesWrap show">' 
		+ value.content;
	if(value.type == "SINGLE_CHOICE" || value.type == "MULTI_CHOICE" || value.type == "JUDEMENT"){
			var optionContent = value.options.split("∷");
			optionContent.forEach(function(value,index){
				html+=value+"</br>"; 
			});
	}
	html+=' </div>'
		+'</div>';
	$("#"+value.examQuestionId).parent().append(html);
	$("#"+value.examQuestionId).eq(0).remove();
}

//修改设置分数页面的临时题目
function editSetScoreQuestion(data){
	var qstHtml = '';
	var qstObj = data;
	var type = qstObj.type;
	//首先清除原来的习题信息，重新添加
	$("#"+qstObj.examQuestionId+"_div").empty();
	qstHtml += "<input type='hidden'  name='questionIds' value='"+qstObj.examQuestionId+"'/>";
	qstHtml += '<p class="quesDesc">';
	qstHtml += '	<label>组卷次数：</label><span class="red">0</span>';
	qstHtml += '	<label>知识点：</label><span class="c888">'+qstObj.endKonwledges+'</span>';
	qstHtml += '	<label>难易度：</label><span class="red">'+switchDifficulty(qstObj.difficulty)+'</span>';
	qstHtml += '	<label>更新日期：</label><span>'+now('y-m-d h:i:s',qstObj.updateTime)+'</span>';
	qstHtml += '	<label>分值：</label><span class="c888 spanDate"><input type="text" class="littleInput" name="score">分</span>';
	qstHtml += "<button class='move move-up btn removeUp mr10'>上移</button>";
	qstHtml += "<button class='move move-up btn removeDown mr10'>下移</button>";
	qstHtml += '<button  class="fr  btn removeFromPaper removeQuestion '+qstObj.examQuestionId+'_btn" qid = '+qstObj.examQuestionId+'  qtype='+type+'>移出试卷</button></p>';
	qstHtml +='<div class="quesBro quesKinds">';
	qstHtml +='</div>';
	qstHtml += '<div class="quesWrap show question" >';
	qstHtml += '<div style="cursor:pointer;" onclick="showAnalytical(this)">'+isEmpty(qstObj.content)+'</div>';
	if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE' || type == 'JUDEMENT') {//单选和多选题目
		var options = qstObj.options;
		if(options){
			options = qstObj.options.split("∷");
		    for (var j = 0; j<options.length;j++){
		    	qstHtml += '<p>'+options[j]+'</p>';
		    } 
		} 
	} 
	qstHtml += '<div class="Analytical" style="display:none"><div class="pd10 commentContent show"><b>【答案】</b>';
	if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE') {//单选和多选题目
		qstHtml +=isEmpty(qstObj.answer);
	}else if(type == 'FILL_IN_BLANK'){//填空题
	    var answerType = qstObj.fillInAnswerType;
	    var typeHtml = "<p>" +(answerType == 'INDEPENDENT' ? "独立答案" : "组合答案") +"</p>";
	    var scoreType = qstObj.fillInScoreType;
	    var scoreHtml =  "<p>" +(scoreType == 'ALL_CORRECT' ? "全对给分" : "按空给分") +"</p>";
	    var answerList = qstObj.fillInAnswers;
    	var headHtml = "";
	    if(answerList.length > 0){
	    	headHtml += "<table class=\"anyTable\" collapse>";
	    	headHtml += "<thead><tr>";
	    	headHtml += "<th><div class=\"specialTH\" >";
	    	headHtml += "<span class=\"rightTop\">答案容错</span>";
	    	headHtml += "<span class=\"leftBottom\">填空</span>";
	    	headHtml += "</div></th>";
           if(answerType == 'INDEPENDENT'){//独立答案
        	    headHtml += "<th>答案1</th>";
					headHtml += "<th>答案2</th>";
					headHtml += "<th>答案3</th>";
					headHtml += "<th>答案4</th>";
	    		
	    	} else if(answerType == 'COMBINATION'){//组合答案
	    	    headHtml += "<th>第一组</th>";
					headHtml += "<th>第二组</th>";
					headHtml += "<th>第三组</th>";
					headHtml += "<th>第四组</th>";
	    	}				    	
			headHtml += "</tr></thead>";
			for(var k = 0 ; k< answerList.length; k++){
				var answerObj = answerList[k];
				headHtml += "<tr>";
				headHtml += "<td>第"+formSeq((k+1))+"空</td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml +=  isEmpty(answerObj.answerGrp1);
				headHtml += "</div></td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml += isEmpty(answerObj.answerGrp2);
				headHtml += "</div></td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml +=  isEmpty(answerObj.answerGrp3);
				headHtml += "</div></td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml +=  isEmpty(answerObj.answerGrp4);
				headHtml += "</div></td>";
				headHtml += "</tr>";
			}
	    	headHtml += "</table>";
	    }
	    if(answerList.length > 1){
	    	qstHtml += typeHtml + headHtml + scoreHtml;
	    } else {
	    	qstHtml += headHtml;
	    }				    
	}else{//其他题型
		qstHtml +=isEmpty(qstObj.answer);
	}	
	qstHtml += '<br/><b>【解析】</b>';
	qstHtml +=isEmpty(qstObj.resolve);
	if(qstObj.resolveVideo && qstObj.resolveVideo != '' &&  qstObj.resolveVideo != null){
		qstHtml += '<p><a href="javascript:;" class="playVideoP" ><button id="'+qstObj.resolveVideo+'" onclick="playVideo(this)" class="btn playVideo">点击解析视频</button></a></p>';
	}
	qstHtml += '</div>';
	qstHtml += '</div>';
	$("#"+qstObj.examQuestionId+"_div").append(qstHtml);
}

//添加设置分数页面临时习题
function addSetScoreQuestion(data){
	//首先清空所有的div内容
	var qstHtml = '';
	var qstObj = data;
	var type = qstObj.type;
	qstHtml += '<div class="quesDetail '+qstObj.examQuestionId+'_div" id="'+qstObj.examQuestionId+'_div">';
	qstHtml += "<input type='hidden'  name='questionIds' value='"+qstObj.examQuestionId+"'/>";
	qstHtml += '<p class="quesDesc">';
	qstHtml += '	<label>组卷次数：</label><span class="red">0</span>';
	qstHtml += '	<label>知识点：</label><span class="c888">'+qstObj.endKonwledges+'</span>';
	qstHtml += '	<label>难易度：</label><span class="red">'+switchDifficulty(qstObj.difficulty)+'</span>';
	qstHtml += '	<label>更新日期：</label><span>'+now('y-m-d h:i:s',qstObj.updateTime)+'</span>';
	qstHtml += '	<label>分值：</label><span class="c888 spanDate"><input type="text" class="littleInput" name="score">分</span>';
	qstHtml += "<button class='move move-up btn removeUp mr10'>上移</button>";
	qstHtml += "<button class='move move-up btn removeDown mr10'>下移</button>";
	qstHtml += '<button  class="fr  btn removeFromPaper removeQuestion '+qstObj.examQuestionId+'_btn" qid = '+qstObj.examQuestionId+'  qtype='+type+'>移出试卷</button></p>';
	qstHtml +='<div class="quesBro quesKinds">';
	qstHtml +='</div>';
	qstHtml += '<div class="quesWrap show question" >';
	qstHtml += '<div style="cursor:pointer;" onclick="showAnalytical(this)">'+isEmpty(qstObj.content)+'</div>';
	if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE' || type == 'JUDEMENT') {//单选和多选题目
		var options = qstObj.options;
		if(options){
			options = qstObj.options.split("∷");
		    for (var j = 0; j<options.length;j++){
		    	qstHtml += '<p>'+options[j]+'</p>';
		    } 
		} 
	} 
	qstHtml += '<div class="Analytical" style="display:none"><div class="pd10 commentContent show"><b>【答案】</b>';
	if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE') {//单选和多选题目
		qstHtml +=isEmpty(qstObj.answer);
	}else if(type == 'FILL_IN_BLANK'){//填空题
	    var answerType = qstObj.fillInAnswerType;
	    var typeHtml = "<p>" +(answerType == 'INDEPENDENT' ? "独立答案" : "组合答案") +"</p>";
	    var scoreType = qstObj.fillInScoreType;
	    var scoreHtml =  "<p>" +(scoreType == 'ALL_CORRECT' ? "全对给分" : "按空给分") +"</p>";
	    var answerList = qstObj.fillInAnswers;
    	var headHtml = "";
	    if(answerList.length > 0){
	    	headHtml += "<table class=\"anyTable\" collapse>";
	    	headHtml += "<thead><tr>";
	    	headHtml += "<th><div class=\"specialTH\" >";
	    	headHtml += "<span class=\"rightTop\">答案容错</span>";
	    	headHtml += "<span class=\"leftBottom\">填空</span>";
	    	headHtml += "</div></th>";
           if(answerType == 'INDEPENDENT'){//独立答案
        	    headHtml += "<th>答案1</th>";
					headHtml += "<th>答案2</th>";
					headHtml += "<th>答案3</th>";
					headHtml += "<th>答案4</th>";
	    		
	    	} else if(answerType == 'COMBINATION'){//组合答案
	    	    headHtml += "<th>第一组</th>";
					headHtml += "<th>第二组</th>";
					headHtml += "<th>第三组</th>";
					headHtml += "<th>第四组</th>";
	    	}				    	
			headHtml += "</tr></thead>";
			for(var k = 0 ; k< answerList.length; k++){
				var answerObj = answerList[k];
				headHtml += "<tr>";
				headHtml += "<td>第"+formSeq((k+1))+"空</td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml +=  isEmpty(answerObj.answerGrp1);
				headHtml += "</div></td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml += isEmpty(answerObj.answerGrp2);
				headHtml += "</div></td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml +=  isEmpty(answerObj.answerGrp3);
				headHtml += "</div></td>";
				headHtml += "<td><div class=\"for-answer\"'>";
				headHtml +=  isEmpty(answerObj.answerGrp4);
				headHtml += "</div></td>";
				headHtml += "</tr>";
			}
	    	headHtml += "</table>";
	    }
	    if(answerList.length > 1){
	    	qstHtml += typeHtml + headHtml + scoreHtml;
	    } else {
	    	qstHtml += headHtml;
	    }				    
	}else{//其他题型
		qstHtml +=isEmpty(qstObj.answer);
	}	
	qstHtml += '<br/><b>【解析】</b>';
	qstHtml +=isEmpty(qstObj.resolve);
	if(qstObj.resolveVideo && qstObj.resolveVideo != '' &&  qstObj.resolveVideo != null){
		qstHtml += '<p><a href="javascript:;" class="playVideoP"  ><button id="'+qstObj.resolveVideo+'" onclick="playVideo(this)" class="btn playVideo">点击解析视频</button></a></p>';
	}
	qstHtml += '</div>';
	qstHtml += '</div>'; 
	qstHtml += '</div>';
	if(type == "SINGLE_CHOICE"){
		//单选题
		$("#type1div").html($("#type1div").html()+qstHtml);
	}else if(type == "MULTI_CHOICE"){
		//多选题
		$("#type2div").html($("#type2div").html()+qstHtml);
	}else if ( type == "JUDEMENT"){
		//判断题
		$("#type3div").html($("#type3div").html()+qstHtml);
	}else if( type == "FILL_IN_BLANK"){
		//填空题 
		$("#type4div").html($("#type4div").html()+qstHtml);
	}else if( type == "ASK_ANSWER"){
		//问答题
		$("#type5div").html($("#type5div").html()+qstHtml); 
	}else if( type == "COMPUTING"){
		//计算题
		$("#type6div").html($("#type6div").html()+qstHtml);
	}
}

//编辑习题弹出框
function editQuestion(obj){
	var id = $(obj).attr("id");
	Win.open({
		id:"resReviewWin",
		title : "编辑习题",
		mask:true,
		width:1200,
		height:900,
		url : root+"/schoolTest/editRealQuestion.do?examQuestionId="+id
	});
}

//删除习题
function deleteQuestion(obj,type){
	var examQuestionId = $(obj).prop("id");
	Win.confirm("确认要删除该题吗？", function(){
 		if(examQuestionId==null || examQuestionId==""){
			Win.alert("删除习题失败！");
		} else {
			$.get(root+"/schoolTest/deleteRealQueByExamQueId.do",{"examQuestionId":examQuestionId},function(data){
				if(data&&data.result){
					questionCount--;
					//修改分数设置页面的值
					if(type == "SINGLE_CHOICE" ){
						type1 -=1;
						$(".type1num").html($(".type1num").html()-1);
					}else if( type == "MULTI_CHOICE"){
						type2 -=1;
						$(".type2num").html($(".type2num").html()-1);
					}else if ( type == "JUDEMENT"){
						type3 -=1;
						$(".type3num").html($(".type3num").html()-1);
					}else if( type == "FILL_IN_BLANK"){
						type4 -=1;
						$(".type4num").html($(".type4num").html()-1);
					}else if( type == "ASK_ANSWER"){
						type5 -=1;
						$(".type5num").html($(".type5num").html()-1);
					}else if( type == "COMPUTING"){
						type6 -=1;
						$(".type6num").html($(".type6num").html()-1);
					}
					//删除习题剩余0  隐藏习题大标题
					updateDivNum();
					
					//重新查询临时习题列表
					Win.alert("删除习题成功！");
					//同时删除页面的习题
					$("#"+examQuestionId).parent(".quesContainer").remove();
					//同时删除设置分数页面的对应习题
					$("#"+examQuestionId+"_div").remove();
					
				}else{
					Win.alert("删除习题失败！");
				}
				
			});
		} 
	}, function(){
			
	});
}


function tosetscore(){
	//校验输入的信息
	var examTitle = $("#examTitle").val().replace(/(^\s*)|(\s*$)/g, "");
	if (examTitle == '') {
		Win.alert("请输入试卷名！");
		return;
	}
	
	if(examTitle.length > 30){
		Win.alert("试卷名不得超过30个字符！");
		return;
	}
	
	var areaName = $("#areaName").val();
	if(areaName==null || areaName=="" || areaName==0){
		Win.alert("请选择地区！");
		return;
	}
	
	var year = $("#year").val();
	if(year==null || year=="" || year==0){
		Win.alert("请选择年份！");
		return;
	}
	
	if($("#examtypeOpts").val() == 0){
		Win.alert("请选择试卷类型！");
		return;
	}
	
	var seid = $("#semesterOption").val();
	if (seid == '' || seid == 0) {
		Win.alert("请选择学段！");
		return;
	}
	
	var classlevel = $("#classLevelOption").val();
	if (classlevel == '' || classlevel == 0) {
		Win.alert("请选择年级！");
		return;
	}
	
	var did = $("#subjOption").val();
	if (did == '' || did == 0) {
		Win.alert("请选择学科！");
		return;
	}
	
		
	
	var answerTime = $("#answerTime").val();
	if (answerTime == '' ) {
		Win.alert("请输入答题时间！");
		return;
	}else if  (answerTime  == '0') {
		Win.alert("答题时间不能为0！");
		return;
	}else if  (answerTime  > 999) {
		Win.alert("答题时间不能大于999！");
		return;
	}
	
	var scoreInput = $("#scoreInput").val();
	if (scoreInput == '') {
		Win.alert("请输入试卷总分！");
		return;
	}else if(scoreInput=="0"){
		Win.alert("试卷总分不能为0！");
		return;
	}else if(scoreInput >999){
		Win.alert("试卷总分不能大于999！");
		return;
	}
	
	if(questionCount < 1){
		Win.alert("请至少添加一道题目");
		return;
	} 
	$(".editQues").hide();
	tosetScore();
	$(".fixedRight").show();
	$("#setscore").show();
};

//显示题目解析
function showAnalytical(obj){
	var s = $(obj).parent("div").children(".Analytical").is(":visible");
	if (s) {
		$(obj).parent("div").children(".Analytical").hide();
	} else {
		$(obj).parent("div").children(".Analytical").show();
	}
}
	 
