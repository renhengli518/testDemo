var optSplitChar = '∷';



$(function(){
	 $(".fixedRight").hide();
	 var h=parseInt($(window).height()-48+25)/2;
	 /*$(".leftContent .leftSection ").css("height",h);*/
	 $(".quesKinds").on("click","a",function(){
		 $(this).addClass("active").siblings().removeClass("active")
	 })
	$('.select-exercise-style a').click(function(){
			var index = $(this).attr('index');
			$('.select-exercise-style a').removeClass('selected');
			$(this).addClass('selected');
			$('.select-exercise-box').hide().eq(index).show();
			fixedBox.init({
				"id": "box2"
			});
	});
	 
	//获得当前选中的年级的id
	var classLevelOption=$("#classLevelOption").val();
	//alert("classLevelOption="+classLevelOption);
	$.post("../commons/subjbyclassid.do?classLevelId="+classLevelOption,function(data){
		var html = '<option value="0">-- 请选择 --</option>';
		for(var i=0; i<data.length; i++){
			html+="<option value="+data[i].baseSubjectId+">"+data[i].baseSubjectName+"</option>";
		}
		$("#subjOption").html(html); 
	},'json'); 
 	
 	$("#subjOption").change(
        		function(){
        		 subjectId=$("#subjOption").val();
//       		 initKnowledge();//初始按化知识点选题
//    			 initChapter();//初始化按章节选题
    			 bindChapterByVersion();//初始化智能选题
    			 //initAllQuestions();//获取所有的题目并显示
        		}
        	);
 	
 	//绑定事件
 	$("#tosetques").click(function(){
		//设置分数页面返回按钮点击事件
//		$("#fixed-target").hide();
//		$("#select-wrap").removeClass("hidden");
//		$("#setup-wrap").addClass("hidden");
 		$(".fixedRight").hide();
 		$("#setques").show();
 		$("#setscore").hide();
	});
	$(".saveExam-btn").click(function(){
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
		
		var did = $("#disciplineOpts").val();
		if (did == '' || did == 0) {
			Win.alert("请选择学科！");
			return;
		}
		
		
		var answerTime = $("#answerTime").val();
		if  (answerTime  > 999) {
			Win.alert("答题时间不能大于999！");
			return;
		}
		
		if(Object.keys(qlist).length < 1){
			Win.alert("请至少选择一道题目");
			return;
		}
		
		if($(this).hasClass("toanswer")){
			//提交并答题
			create(true);
		}else{
			//只是提交
			create(false);
		}
		//create();
	});
 	
	});


function create(toAnswer){
	//var t = create;
	//create = function(){};
	var f=$("#createExamForm").serializeArray();
	
	//依次加入题目
	//单选题
	$.each(qlist, function (k, v) {
		if(v== 'SINGLE_CHOICE'){
			f=f.concat({name:'questionIds',value:k})
		}	
	});
	//多选题
	$.each(qlist, function (k, v) {
		if(v=='MULTI_CHOICE'){
			f=f.concat({name:'questionIds',value:k})
		}	
	});
	//判断题
	$.each(qlist, function (k, v) {
		if(v=='JUDEMENT'){
			f=f.concat({name:'questionIds',value:k})
		}	
	});
	//填空题
	$.each(qlist, function (k, v) {
		if(v=='FILL_IN_BLANK'){
			f=f.concat({name:'questionIds',value:k})
		}	
	});
	//问答题
	$.each(qlist, function (k, v) {
		if(v=='ASK_ANSWER'){
			f=f.concat({name:'questionIds',value:k})
		}	
	});
	//计算题
	$.each(qlist, function (k, v) {
		if(v=='COMPUTING'){
			f=f.concat({name:'questionIds',value:k})
		}	
	});
		//如果校验通过，则提交
		var url = 'finishCreateExam.do';
		$.ajax({
			type: 'POST',
			url: url,
			data: f,
			success : function(d){
				if(toAnswer){
					//如果直接测试
					window.location.href='studentAnswerExam/'+d.examTaskId+'/'+d.baseUserId+'.html';
				}else{
					//否则的话跳转到列表中
					window.location.href='selfExamList.html';
				}
			},
			error : function (){
				//create = t;
				Win.alert("操作失败");
			}
		});
}


	function formQuestionListCommon(data,totalCnt,initname,totalname){
		$(totalname).html(totalCnt);
		var quesContainer = '';
		var len = data.length;
		if(len>0){
			for(var i = 0; i< len; i++){
				var qstHtml = '';
				var qstObj = data[i];
				qstHtml += '<div class="quesDetail ">';
				qstHtml += '<p class="quesDesc">';
				qstHtml += '	<label>组卷次数：</label><span class="red">'+isEmpty(qstObj.useCount)+'</span>';
				qstHtml += '	<label>知识点：</label><span class="c888">'+qstObj.endKonwledgeNames+'</span>';
				qstHtml += '	<label>难易度：</label><span class="red">'+switchDifficulty(qstObj.difficulty)+'</span>';
				qstHtml += '	<label>更新日期：</label><span>'+now('y-m-d h:i:s',qstObj.updateTime)+'</span>';
				qstHtml += '</p>';
				qstHtml +='<div class="quesBro quesKinds">';
				
				var hasadded = '';
				if(qstObj.queQuestionId in qlist){
					hasadded = 'added';
				}
				
				qstHtml +='		<a href="javascript:;" class="active '+qstObj.queQuestionId+' '+hasadded+'" onclick="switchQues(this)" id="'+qstObj.queQuestionId+'">母题</a>';
				var childrenQuestionList = qstObj.childrenQuestionList;
				if(childrenQuestionList.length>0){
					var m=1, n=1; 
					for(var j=0;j<childrenQuestionList.length;j++){
						
						hasadded = '';
						if(childrenQuestionList[j].queQuestionId in qlist){
							hasadded = 'added';
						}
						
						
						
						if(childrenQuestionList[j].relationalIndicator=="TWINS"){
							qstHtml +='	<a href="javascript:;" onclick="switchQues(this)" id="'+childrenQuestionList[j].queQuestionId+'" class="'+childrenQuestionList[j].queQuestionId+' '+hasadded+'">孪生题'+m+'</a>';
							m++;
						}
						if(childrenQuestionList[j].relationalIndicator=="EXTEND"){
							qstHtml +='	<a href="javascript:;" onclick="switchQues(this)" id="'+childrenQuestionList[j].queQuestionId+'" class="'+childrenQuestionList[j].queQuestionId+' '+hasadded+'">衍生题'+n+'</a>';
							n++;
						}
					}
				}
				qstHtml +='</div>';
				qstHtml += '<div class="quesWrap show question" >';
				qstHtml += '<p style="cursor:pointer;">'+isEmpty(qstObj.content);
				qstHtml += getBtn(qstObj.queQuestionId,qstObj.queQuestionId,qstObj.type);
				qstHtml += '</p>';
				//qstHtml += '<button class="fr  btn addToPaper addQuestion '+qstObj.queQuestionId+'_btn" qid = '+qstObj.queQuestionId+' mid ='+qstObj.queQuestionId+' qtype='+qstObj.type+'>加入试卷</button></p>';
				var type = qstObj.type;
				if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE' || type == 'JUDEMENT') {//单选和多选题目
					var options = qstObj.options;
					if(options){
						options = qstObj.options.split(optSplitChar);
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
					qstHtml += '<p><a href="javascript:;" class="playVideoP" videoUrl="../ImageServlet/'+qstObj.resolveVideo+'" ><button class="btn playVideo">点击解析视频</button></a></p>';
				}
				qstHtml += '</div>';
				qstHtml += '</div>'; 
				qstHtml += '</div>';
				//qstHtml += '</div>';
				quesContainer += qstHtml+'</div>';
			}					
		}else{
			quesContainer = '<div id="quesContainer" style="padding-top:25px;" class="quesContainer" align="center">'+noRecord+'</div>';
		}
		//window.scroll(0,0);
		$(initname).html(quesContainer);
		
		chooseQuestion(initname);
		
		events.addEvent($class("playVideoP"),"click",function(){
			var fileurl = this.getAttribute("videoUrl"),
				flashurl = '../public/flash/player/myflvPlayBack.swf?url='+fileurl+'&skin=../public/flash/player/MinimaFlatCustomColorAll.swf&autoplay=4';
				Win.open({
	            	title : "视频解析",
	                mask : true,
	                html : "<div id='myplayer'>&nbsp;</div>",
	                width: 740,
	                height: 510
	            });
				FlashPlayer($id('myplayer'),flashurl,'player',[700,430]);
		});	
	}
		
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
    
    function formSeq(seqIndex){
      	 var x="一";
      	 switch (seqIndex)
      	 {
         	 case 1:
         	   x="一";
         	   break;
         	 case 2:
         	   x="二";
         	   break;
         	 case 3:
         	   x="三";
         	   break;
         	 case 4:
         	   x="四";
         	   break;
         	 case 5:
         	   x="五";
         	   break;
         	 case 6:
         	   x="六";
         	   break;
         	 case 7:
         	   x="七";
         	   break;
         	 case 8:
         	   x="八";
         	   break;
         	 case 9:
       	   x="九";
       	   break;
         	 case 10:
       	   x="十";
       	   break;
      	 }
      	 return x;
      }
       
       function isEmpty(str){
       	if(str===null || str===""){
       		return "";
       	}else {
       		return str;
       	}
       }
       
    // 绑定母体与孪生题和衍生题切换事件
   	function switchQues(obj){
   		
   		var index = $(obj).index();
   		console.log(index);
   		$(obj).siblings().removeClass("active");
   		$(obj).parent(".quesBro").children("a").eq(index).addClass("active");
   		
   		var mid = $(obj).parent(".quesBro").children("a").eq(0).attr("id");
   		//添加切换题目操作
   		var queQuestionId = $(obj).prop("id");
   		var url = "../questionLib/getQuestionById.do";
   		$.get(url,{"queQuestionId":queQuestionId},function(data){
   			if(data){
   				$(obj).parent(".quesBro").next(".quesWrap").empty();
   				var qstObj = data;
   				var qstHtml = "";
   				qstHtml += '<p style="cursor:pointer;">'+isEmpty(qstObj.content);
   				//qstHtml +='<button class="fr  btn addToPaper addQuestion '+qstObj.queQuestionId+'_btn" qid = '+qstObj.queQuestionId+' mid='+mid+' qtype='+qstObj.type+'>加入试卷</button>'
				qstHtml += getBtn(mid,qstObj.queQuestionId,qstObj.type);
				qstHtml += '</p>';
   				var type = qstObj.type;
				if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE' || type == 'JUDEMENT') {//单选和多选题目
					var options = qstObj.options;
					if(options){
						options = qstObj.options.split(optSplitChar);
					    for (var j = 0; j<options.length;j++){
					    	qstHtml += '<p>'+options[j]+'</p>';
					    } 
					} 
				} 
				
				
   				qstHtml += '<div class="Analytical" style="display:none"><div class=" pd10 commentContent show multi-choice-answer"><b>【答案】</b>';
   				if(type == 'SINGLE_CHOICE' || type == 'MUlTI_CHOICE') {//单选和多选题目
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
   								headHtml +=  isEmpty(answerObj.answerGrp2);
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
   				}else{
   					qstHtml +=isEmpty(qstObj.answer);
   				}	
   				qstHtml += '<br/><b>【解析】</b>';
   				qstHtml +=isEmpty(qstObj.resolve);
   				if(qstObj.resolveVideo && qstObj.resolveVideo != '' &&  qstObj.resolveVideo != null){
   					qstHtml += '<p><a href="javascript:;" class="playVideoP" videoUrl="../ImageServlet/'+qstObj.resolveVideo+'" ><button class="btn playVideo">点击解析视频</button></a></p>';
   				}
   				$(obj).parent(".quesBro").next(".quesWrap").html(qstHtml);
   				
   				
   			}
   		});
   		
   	}

	$(document).on('click', '.quesWrap', function (event) {
		if ($(event.target).hasClass('addToPaper')) return;
		if ($(event.target).hasClass('removeFromPaper')) return;
		var $elm = $(".Analytical", this);
		if ($elm.is(":visible")) {
			$elm.hide();
		} else {
			$elm.show();
		}
	})
	
	
	//加入试卷处理事件
	$(document).on('click', '.addQuestion', function (event) {
		$elm = $(this);
		if($elm.hasClass("grayBtn")){
			return false;
		}
		addQuestion($elm.attr("mid"),$elm.attr("qid"),$elm.attr("qtype"));
	})
	
	//移出试卷处理事件
	$(document).on('click', '.removeQuestion', function (event) {
		$elm = $(this);
		removeQuestion($elm.attr("qid"),$elm.attr("qtype"));
	})
	
	$(document).on('click', '.deltype', function (event) {
		deleteType($(this).attr("qtype"));
	})
	
	
	//删除指定类型的所有试题
	function deleteType(type){
		$.each(qlist, function (k, v) {
			if(v==type){
				removeQuestion(k,v);
			}	
		});
	}
	
	
	var fixedBox = {};
fixedBox.init = function(options) {
	if(!options.id) return ;
	
	var _fb = function(options) {
		this.obj = options.obj;
		this.initOpt = options.obj.getBoundingClientRect();
		this.wheelEvent();
	};

	_fb.prototype = {
		"constructor": this,
		"wheelEvent": function() {
			var self = this;

			var so = function() {
				var e = arguments[0] || window.event;
				var _bs = self.BS();
				if(_bs.top>=self.initOpt.top) {
					if(self.obj.className.indexOf("fixedBox")==-1) {
						self.obj.style.left = self.initOpt.left + "px";
						self.obj.className = self.obj.className.replace(/(^\s*)|(\s*$)/g, "") +  " fixedBox";
					}
				} else {
					self.obj.className = self.obj.className.replace("fixedBox", "").replace(/(^\s*)|(\s*$)/g, "");
				}
			};

			events.addEvent(document, "scroll", so);
		},
		"BS":function(){
			var db=document.body,
				dd=document.documentElement,
				top = db.scrollTop+dd.scrollTop;
				left = db.scrollLeft+dd.scrollLeft;
			return {'top':top , 'left':left};
		}
	};

	var obj = $id(options.id) || options.id;

	if(!obj) return ;

	options.obj = obj;

	return new _fb(options);
};


//存放所有已选择的问题的数组
var qlist = {};//题目列表
var mlist = {};//题目对应的母题列表
var type1 = 0;//单选
var type2 = 0;//多选
var type3 = 0;//判断
var type4 = 0;//填空
var type5 = 0;//问答
var type6 = 0;//计算
function addQuestion(mid,id,type){
	if(Object.keys(qlist).length >= 100){
		Win.alert('试卷总题数不得超过100题');
		return ;
	}
	
	if(id in qlist){
		Win.alert('请勿重复添加!');
		return;
	}
	
	if(mid in mlist){
		Win.alert('请勿重复添加!');
		return;
	}
	
	if(type == "SINGLE_CHOICE"){
		type1+=1;
	}else if(type == "MULTI_CHOICE"){
		type2+=1
	}else if ( type == "JUDEMENT"){
		type3 +=1;
	}else if( type == "FILL_IN_BLANK"){
		type4 +=1;
	}else if( type == "ASK_ANSWER"){
		type5 +=1;
	}else if( type == "COMPUTING"){
		type6 +=1;
	}
	qlist[id] = type;
	mlist[mid] = id;
	//Object.keys(qlist).length;//长度
	
	updateCount();
	
	
	
	$("."+id+"_btn").removeClass("addQuestion");
	$("."+id+"_btn").addClass("removeQuestion");
	
	$("."+id+"_btn").addClass("removeFromPaper");
	$("."+id+"_btn").removeClass("addToPaper");
	
	$("."+id+"_btn").html("移出试卷");
	
	$("."+id).addClass("added");
}


//删除题目
function removeQuestion(id,type, flag){
	flag = flag || false;
	delete qlist[id];
	
	$.each(mlist, function (k, v) {
		if(id == v){
			delete mlist[k];
		}	
	});
	
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
	
	updateCount();
	
	var $box = $("#"+id).parent();
	
	
	$("."+id+"_btn").html("加入试卷"); 
	$("."+id+"_btn").removeClass("removeQuestion");
	$("."+id+"_btn").addClass("addQuestion");
	
	
	$("."+id+"_btn").removeClass("removeFromPaper");
	$("."+id+"_btn").addClass("addToPaper");
	
	$("."+id).removeClass("added");
	
	$("."+id+"_div").remove();
	
	//finishchangeScore();
	if (flag) {
		upAndDown.refresh($box);
	}
}

//更新题目小表格数据
function updateCount(){
	var total = Object.keys(qlist).length;//长度
	$(".type1").html(type1);
	$(".type2").html(type2);
	$(".type3").html(type3);
	$(".type4").html(type4);
	$(".type5").html(type5);
	$(".type6").html(type6);
	
	$(".type1per").html((type1==0?0:(type1/total*100).toFixed(0))+'%');
	$(".type2per").html((type2==0?0:(type2/total*100).toFixed(0))+'%');
	$(".type3per").html((type3==0?0:(type3/total*100).toFixed(0))+'%');
	$(".type4per").html((type4==0?0:(type4/total*100).toFixed(0))+'%');
	$(".type5per").html((type5==0?0:(type5/total*100).toFixed(0))+'%');
	$(".type6per").html((type6==0?0:(type6/total*100).toFixed(0))+'%');
	
	$(".selNum").html(total);
	
}

//加载按钮 加入试卷，加入试卷（灰色），移出试卷
function getBtn(mid,id,type){
	var qstHtml = '';
	
	if(id in qlist){
		//移出试卷
		qstHtml += '<button class="fr  btn removeFromPaper removeQuestion '+id+'_btn" qid = '+id+' mid ='+mid+' qtype='+type+'>移出试卷</button></p>';
	}else if(mid in mlist){
		//加入试卷（灰色）
		qstHtml += '<button class="grayBtn fr  btn addToPaper addQuestion '+id+'_btn" qid = '+id+' mid ='+mid+' qtype='+type+'>加入试卷</button></p>';
	}else{
		//加入试卷
		qstHtml += '<button class="fr  btn addToPaper addQuestion '+id+'_btn" qid = '+id+' mid ='+mid+' qtype='+type+'>加入试卷</button></p>';
	}
	
	return qstHtml;
}

//加载题目时选中已选题目
function chooseQuestion(id){
	$.each(qlist, function (k, v) {
		$(id+" ."+k).click();
	});
}