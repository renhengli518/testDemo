<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/meta.jsp"%>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<head>
</head>
<body class="mainIndex">
	<%@include file="../../common/topHeader.jsp"%>
	<%@include file="../../common/queNav.jsp"%>
	<div class="container w1200 marginauto bkgNone clearfix">
		<p class="uploadContainer">
			<span class="fr"> <a href="javascript:;" onclick="uploadQuestion();" class="btn btnUpload mr20" id="addRes">批量导入习题</a> <a
				href="${root}/questionLib/tocreatequestion.html" class="btn btnUpload" id="addRes">新建习题</a>
			</span>
		</p>
		<div class="content">
			<div class="content-in">
				<div class="borderBox bkgWhite mb10 criteria pd20">
					<ul class="commonUL gn-searchCondition gn-coffeeSort">
						<li id="semesterLi"><label class="text gn-labelText gn-coffeeBracket"><b>学段</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;" dir="0" id="0">全部</a>
								<span class="gn-sortKinds gn-overflowHidden"> <c:forEach items="${semesters}" var="semester">
										<a href="javascript:;" id="${semester.baseSemesterId }">${semester.semesterName }</a>
									</c:forEach>
							</span>
						</span></li>
						<li id="classlevelLi"><label class="text gn-labelText gn-coffeeBracket"><b>年级</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;" dir="0" id="0">全部</a>
								<span class="gn-sortKinds gn-overflowHidden"> <c:forEach items="${classes }" var="classes">
										<a href="javascript:;" dir="${classes.parentId }" id="${classes.id }">${classes.name }</a>
									</c:forEach>
							</span>
						</span></li>
						<li id="subjectLi"><label class="text gn-labelText gn-coffeeBracket"><b>学科</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a> <span
								class="gn-sortKinds gn-overflowHidden"> <c:forEach items="${subjects }" var="subjects">
										<a href="javascript:;" id="${subjects.id }">${subjects.name }</a>
									</c:forEach>
							</span>
						</span></li>
						<li id="versionLi"><label class="text gn-labelText gn-coffeeBracket"><b>版本</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a> <span
								class="gn-sortKinds gn-overflowHidden"> <c:forEach items="${versions}" var="versions">
										<a href="javascript:;" id="${versions.id }">${versions.name }</a>
									</c:forEach>
							</span>
						</span></li>
						<li id="typeLi"><label class="text gn-labelText gn-coffeeBracket"><b>题型</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a> <span
								class="gn-sortKinds gn-overflowHidden"> <a href="javascript:;" id="SINGLE_CHOICE">单选题</a> <a
									href="javascript:;" id="MULTI_CHOICE">多选题</a> <a href="javascript:;" id="JUDEMENT">判断题</a> <a
									href="javascript:;" id="FILL_IN_BLANK">填空题</a> <a href="javascript:;" id="ASK_ANSWER">问答题</a> <a
									href="javascript:;" id="COMPUTING">计算题</a>
							</span>
						</span></li>
						<li id="difficultyLi"><label class="text gn-labelText gn-coffeeBracket"><b>难易度</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a> <span
								class="gn-sortKinds gn-overflowHidden"> <a href="javascript:;" id="EASY">容易</a> <a href="javascript:;"
									id="LITTLE_EASY">较容易</a> <a href="javascript:;" id="NORMAL">一般</a> <a href="javascript:;" id="LITTLE_DIFFICULT">较难</a>
									<a href="javascript:;" id="DIFFICULT">困难</a>
							</span>
						</span></li>
					</ul>
				</div>
				<div class="borderBox bkgWhite ml10 mr10 pd10 mt20 mb10 clearfix">
					共计<span id="totalCnt">0</span>题（提示：点击题目内容即可查看到内容和解析内容） <span class="fr"> <label class="mr20">习题种类： <select
							id="relationalIndicator" class="w100" onchange="searchQuestions()">
								<option value="0">全部</option>
								<option value="TWINS">包含孪生题</option>
								<option value="EXTEND">包含衍生题</option>
						</select>
					</label>
					</span>
					<c:if test="${sessionScope.SESSION_USER.userType eq 'SCHOOL_USR'}">
						<span class="fr"> <label class="mr20">审核状态： <select id="auditStatus" class="w100"
								onchange="searchQuestions()">
									<option value="0">全部</option>
									<option value="PASSED">已通过</option>
									<option value="REJECTED">未通过</option>
									<option value="INIT">未处理</option>
							</select>
						</label>
						</span>
					</c:if>
				</div>

				<div class="clearfix floatWrap">
					<div class="mainLeft borderBox bkgWhite pd0">
						<div class="gn-sourceTabs  gn-redTopArrow clearfix">
							<a class="gn-TopArrowSelected" id="chapterTab" href="javascript:;">章节</a> <a id="klgTab" href="javascript:;">知识点</a>
						</div>
						<div class="pd10 gn-ChapterPointer"></div>

						<div class="pd10 gn-sourceContent gn-KnowledgePointer" style="display: none"></div>
					</div>
					<div class="mainRight1 ">
						<div id="questionList">
						</div>
						<div id="pageNavi"></div>
					</div>
					
					
					

				</div>
			</div>
		</div>
	</div>
	<script>
    var optSplitChar = '∷';
    $(document).ready(function(){
    	$("body").showLoading();
    	$(".wrap").css({
			"height" : ($(window).height() - 70) + 'px',
			"width" : $(window).width()
		});
		
    	//默认查询共享题库列表
		searchQuestions();
		//绑定点击事件
		 $("#quesContainer").on("click",".stem",function(){
			 if($(this).hasClass("show")){
				 $(this).siblings(".answer").hide();
				 $(this).siblings(".answer-analyze").hide();
				 $(this).addClass("hide");
				 $(this).removeClass("show");
			 } else {
				 $(this).siblings(".answer").show();
				 $(this).siblings(".answer-analyze").show();
				 $(this).addClassf("show");
				 $(this).removeClass("hide");
			 }
		 });
	});		 
    
    /* $("#pageNavi").on("click",function(){
    	window.scroll(0,0);
    }); */
	
	
	// 查询共享题库列表
	function searchQuestions() {
		var url  = '${root}/questionLib/searchShareList.do';
		var baseSemesterId = $("#semesterLi").find(".selected").prop("id");
		var baseClasslevelId = $("#classlevelLi").find(".selected").prop("id");
		var baseSubjectId = $("#subjectLi").find(".selected").prop("id");
		var baseVersionId = $("#versionLi").find(".selected").prop("id");
		var chapterIdStr = $(".gn-ChapterPointer .selected").attr("value");
		if(!chapterIdStr){
			chapterIdStr = '0,0,0';
		} 
		var knowledgeIdStr = $(".gn-KnowledgePointer .selected").attr("value");
		if(!knowledgeIdStr){
			knowledgeIdStr = '0,0,0,0,0,0';
		}
		var type = $("#typeLi").find(".selected").prop("id");
		var difficulty = $("#difficultyLi").find(".selected").prop("id");
		var relationalIndicator = $("#relationalIndicator").val();
		var auditStatus = $("#auditStatus").val();
		var param  = {
				baseSemesterId:baseSemesterId,
				baseClasslevelId:baseClasslevelId,
				baseSubjectId:baseSubjectId,
				baseVersionId:baseVersionId,
				chapterIdStr:chapterIdStr,
				knowledgeIdStr:knowledgeIdStr,
				type:type,
				difficulty:difficulty,
				relationalIndicator:relationalIndicator,
				auditStatus : auditStatus
		};
		 var mySplit = new SplitPage({
		        node : $id("pageNavi"),
		        url : url,
		        data :  param,
		        count : 10,
		        callback : formQuestionList,
		        type : 'get' //支持post,get,及jsonp
		    });			 
	
		 mySplit.search(url,param);
		
	}
	
	//审核
	function auditOperation(obj){
		var queQuestionId = $(obj).prop("value");
		var auditStatus = $(obj).prop("id");
		var url = "${root}/questionLib/auditShareQuestion.do";
		$.get(url, {
			"queQuestionId" : queQuestionId,
			"auditStatus" : auditStatus
		}, function(data) {
			if (data && data!=null) {
				Win.wins['resReviewWin'].close();
				var html = unionHtml(data);
				$("#"+queQuestionId).parent().replaceWith(html);
				Win.alert("审核习题成功！");
			} else {
				Win.alert("审核习题失败！");
			}
		});
		
	}
	
	//显示题目解析
	function showAnalytical(obj){
		var s = $(obj).parent("div").children(".Analytical").is(":visible");
		if (s) {
			$(obj).parent("div").children(".Analytical").hide();
		} else {
			$(obj).parent("div").children(".Analytical").show();
		}
	}
	
	//收藏习题
	function toCollection(obj){
		var queQuestionId = $(obj).prop("id");
		var url = "${root}/questionLib/toCollection.do";
		$.get(url,{"queQuestionId":queQuestionId},function(data){
			if(data&&data!=null){
				$(obj).text("取消收藏").addClass("cancelCollect").attr("onclick","cancelCollection(this)").attr("id",data.queFavoriteId);
				Win.alert("收藏习题成功！");
				//searchQuestions();
			}else {
				Win.alert(data.message);
			}
		});
	}
	
	//取消收藏
	function cancelCollection(obj){
		Win.confirm("确认取消收藏？",function(){
			var queFavoriteId = $(obj).prop("id");
			var queQuestionId = $(obj).parents(".quesDetail1").prop("id");
			var url = "${root}/questionLib/cancelCollection.do";
			$.get(url,{"queFavoriteId":queFavoriteId},function(data){
				if(data&&data.result){
					$(obj).text("收藏").removeClass("cancelCollect").attr("onclick","toCollection(this)").attr("id",queQuestionId);
					Win.alert("取消收藏成功！");
					//searchQuestions();
				}else {
					Win.alert(data.message);
				}
			});
		},function(){
			
		});
	}
	
	//审核
	function _auditing(obj){
		var queQuestionId = $(obj).prop("id");
		var html = "";
		html+='<div id="reviewBoxForm"><div class="wrap">'
			+'<p style="padding: 30px; color: #4dadc6;">该习题是否审核通过？</p>'
			+'</div>'
			+'<p class="center withBg" style="margin-top:15px;">'
			+'	<button value="'+queQuestionId+'" onclick="auditOperation(this)" class="btn  toSave" id="PASSED">通过</button>'
			+'	<button value="'+queQuestionId+'" onclick="auditOperation(this)" class="btn  toCancel" id="REJECTED">不通过</button>'
			+'</p></div>';
		Win.open({
			id:"resReviewWin",
			title : "提示信息",
			mask:true,
			width:300,
			height:200,
			html : html
		});
	}
	
	//自定义replaceAll 方法
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
	    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
	        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	    } else {  
	        return this.replace(reallyDo, replaceWith);  
	    }  
	};
	
	/**
	*	组装html
	*/
	function unionHtml(data){
		var qstHtml = '<div id="quesContainer" class="quesContainer posRelative">';
		var qstObj = data;
		var userType="${sessionScope.SESSION_USER.userType}";
		if(userType=="SCHOOL_USR"){
			if(qstObj.auditStatus=="INIT"){
				qstHtml +='<div class="handleState"></div>';
			}else if(qstObj.auditStatus=="PASSED"){
				qstHtml +='<div class="handleState passed"></div>';
			}else if(qstObj.auditStatus=="REJECTED"){
				qstHtml +='<div class="handleState notPassed"></div>';
			}
		}
		qstHtml += '<div id="'+qstObj.queQuestionId+'" class="quesDetail1">';
		qstHtml += '<p class="quesDesc">';
		qstHtml += '	<label>组卷次数：</label><span class="red">'+qstObj.useCount+'</span>';
		qstHtml += '	<label>知识点：</label><span class="c888">'+qstObj.endKonwledgeNames+'</span>';
		qstHtml += '	<label>难易度：</label><span class="red">'+switchDifficulty(qstObj.difficulty)+'</span>';
		qstHtml += '	<label>更新日期：</label><span>'+now('y-m-d h:i:s',qstObj.updateTime)+'</span>';
		//在题干后面添加收藏和审核按钮
		if(userType=="SCHOOL_USR"){
			if(qstObj.auditStatus=="INIT"){
				qstHtml +='	<a herf="javascript:;" class="checkQues" id="'+qstObj.queQuestionId+'" onclick="_auditing(this)">审核</a>';
			}
		}else if(userType=="TEACHER"){
			 if(qstObj.queFavoriteId!=null && qstObj.queFavoriteId!=""){
				qstHtml +='<span id="'+qstObj.queFavoriteId+'" onclick="cancelCollection(this)" class="toCollect cancelCollect">取消收藏</span>';
			} else {
				qstHtml +='<span id="'+qstObj.queQuestionId+'" onclick="toCollection(this)" class="toCollect">收藏</span>';
			}
		}
		qstHtml += '</p>';
		qstHtml +='<div class="quesBro">';
		qstHtml +='		<a href="javascript:;" class="active" onclick="switchQues(this)" id="'+qstObj.queQuestionId+'">母题</a>';
		var childrenQuestionList = qstObj.childrenQuestionList;
		if(childrenQuestionList.length>0){
			var m=1, n=1; 
			for(var j=0;j<childrenQuestionList.length;j++){
				if(childrenQuestionList[j].relationalIndicator=="TWINS"){
					qstHtml +='	<a href="javascript:;" onclick="switchQues(this)" id="'+childrenQuestionList[j].queQuestionId+'">孪生题'+m+'</a>';
					m++;
				}
				if(childrenQuestionList[j].relationalIndicator=="EXTEND"){
					qstHtml +='	<a href="javascript:;" onclick="switchQues(this)" id="'+childrenQuestionList[j].queQuestionId+'">衍生题'+n+'</a>';
					n++;
				}
			}
		}
		qstHtml +='</div>';
		qstHtml += '<div class="quesWrap show">';
		qstHtml += '<div style="cursor:pointer;" onclick="showAnalytical(this)">'+isEmpty(qstObj.content)+'</div>';
		if(qstObj.contentVideo && qstObj.contentVideo != '' &&  qstObj.contentVideo != null){
			qstHtml += '<p><a href="javascript:;" class="playVideoP" ><button class="btn playVideo" id="'+qstObj.contentVideo+'" onclick="playVideo(this)" >点击播放音视频</button><span id="watch_play0s"></span></a></p>';
		}
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
		if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE'|| type == 'JUDEMENT') {//单选和多选题目
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
			qstHtml += '<p><a href="javascript:;" class="playVideoP" ><button class="btn playVideo" id="'+qstObj.resolveVideo+'" onclick="playVideo(this)" >点击解析视频</button></a></p>';
		}
		qstHtml += '</div>';
		qstHtml += '</div>'; 
		qstHtml += '</div>';
		qstHtml += '</div>';
		qstHtml += '</div>';
		return qstHtml;
	}
	
	function formQuestionList(data,totalCnt){
		$("#totalCnt").html(totalCnt);
		var quesContainer = '';
		var len = data.length;
		if(len>0){
			for(var i = 0; i< len; i++){
				quesContainer += unionHtml(data[i]);
			}					
		}else{
			quesContainer = '<div id="quesContainer" style="padding-top:25px;" class="quesContainer" align="center">'+noRecord+'</div>';
		}
		$("#questionList").html(quesContainer);	
		window.scroll(0,0);
		$("body").hideLoading();
	}

	function uploadQuestion(){
		 Win.open({id:'uploadQuestion',width:500,height:520,title:"导入习题",url:"${root}/teacher/questionLib/uploadQuestionPage.do"});
	 }
	
    $('.criteria').on('click', 'a', function () {
		$(this).parents('li').find('a').removeClass('selected');
		$(this).addClass('selected');
		searchQuestions();
		var type = $(this).parents('li').attr("id");
		switch(type){
		  case 'classlevelLi':
		  case 'subjectLi':
			  $(".gn-ChapterPointer").empty();
			  $(".gn-KnowledgePointer").empty();
			  var classlevelId =  $("#classlevelLi").find(".selected").prop("id");
			  var subjectId = $("#subjectLi").find(".selected").prop("id");
			  var versionId = $("#versionLi").find(".selected").prop("id");
			  if(classlevelId != '0' && subjectId != '0' && versionId != '0'){
				  bindVolumeByClslevelSubjectVerionId(classlevelId,subjectId,versionId); 
			  }
			  
			  var semesterId = $("#classlevelLi").find(".selected").attr("dir");
			  if(semesterId != '0' && subjectId != '0'){
				  bindRootKnowledageBySemesterSubjectId(semesterId,subjectId); 
			  }
			  
			  break;
		  case 'versionLi':			  
			  $(".gn-ChapterPointer").empty();
			  
			  var classlevelId =  $("#classlevelLi").find(".selected").prop("id");
			  var subjectId = $("#subjectLi").find(".selected").prop("id");
			  var versionId = $("#versionLi").find(".selected").prop("id");
			  if(classlevelId != '0' && subjectId != '0' && versionId != '0'){
				  bindVolumeByClslevelSubjectVerionId(classlevelId,subjectId,versionId); 
			  }
			  break;			  
		};
	});
    
    
    
    function bindRootKnowledageBySemesterSubjectId(semesterId,subjectId){
    	var param = {
    			semesterId:semesterId,
    			subjectId:subjectId
    	};
    	$.post('${root}/commons/getRootKnowledgeViewBySemsterSubjectId.do', param, function (data) {	
    		var html = '<ul class="tree">';
			if(data != null && data.length > 0){				
				for(var i = 0; i < data.length; i++){
					var obj =  data[i];					
					html += "<li class=\"node-0\" onclick=\"getKLGChildren('"+obj.baseKnowledgeId+"', 0, this, event,"+obj.hasKnowledgeChild+")\" >";
					html += '<span class="klg-node" id="'+obj.baseKnowledgeId+'" value="'+obj.baseKnowledgeId+',0,0,0,0,0" >'+obj.knowledgeName+'</span>';
					html += '</li>';
				}				
			}
			html += '</ul>';
			$(".gn-KnowledgePointer").html(html); 
		}, 'json');
    }
    
    function bindSubKnowledageByParentId(pNode,parentId,nodeNumber){
    	
    	$.post('${root}/commons/getChildKnowledgeViewByParentId.do', {"parentId":parentId}, function (data) {	
    		var html = '<ul>';
    		var currentNodeNum = (nodeNumber+1);
			if(data != null && data.length > 0){				
				for(var i = 0; i < data.length; i++){
					var obj =  data[i];
					var cls = (currentNodeNum>=3?'close':'');
					var hasChild = obj.hasKnowledgeChild;					
					
					html += "<li class='node-"+currentNodeNum+"' ";
					html += "onclick=\"getKLGChildren('"+obj.baseKnowledgeId+"',"+currentNodeNum+", this, event,"+obj.hasKnowledgeChild+")\" ";
					html += ">";
					
					var pVal = $(pNode.getElementsByTagName("span")[0]).attr("value");					
					var pArr = pVal.split(",");
					pArr[currentNodeNum] = obj.baseKnowledgeId;					
					
					html += '<span class="klg-node" id="'+obj.baseKnowledgeId+'" value="'+pArr.join()+'" >';
					
					if(hasChild){
					  html += '<i class="dot '+cls+'"></i>';
					}					
					html += obj.knowledgeName;
					html += '</span>';
					html += "</li>";
				};				
			}
			html += '</ul>';
			pNode.innerHTML += html;				
		}, 'json');
    }
    
    function bindVolumeByClslevelSubjectVerionId(classlevelId,subjectId,versionId){
    	var param = {
   			classlevelId:classlevelId,
   			subjectId:subjectId,
   			versionId:versionId
    	};
    	$.post('${root}/commons/getAllVolumeByClslevelSubjectVerionId.do', param, function (data) {	
    		var html = '<ul class="tree">';
			if(data != null && data.length > 0){				
				for(var i = 0; i < data.length; i++){
					var obj =  data[i];					
					html += "<li class=\"node-0\" onclick=\"getChapterChildren('"+obj.baseVolumeId+"', 0, this, event,"+obj.hasChapterChild+")\" >";
					html += '<span class="chapter-node" id="'+obj.baseVolumeId+'" value="'+obj.baseVolumeId+',0,0" >'+obj.volumeName+'</span>';
					html += '</li>';
				}				
			}
			html += '</ul>';
			$(".gn-ChapterPointer").html(html); 
		}, 'json');
    }
    
    function bindChapterByVolumeId(pNode,volumeId,nodeNumber){
    	$.post('${root}/commons/getAllChapterViewByVolumeId.do', {volumeId:volumeId}, function (data) {	
			var html = '<ul>';
    		var currentNodeNum = (nodeNumber+1);
			if(data != null && data.length > 0){				
				for(var i = 0; i < data.length; i++){
					var obj =  data[i];
					var cls = (currentNodeNum>=3?'close':'');
					var hasChild = obj.hasSectionChild;					
					
					html += "<li class='node-"+currentNodeNum+"' ";
					html += "onclick=\"getChapterChildren('"+obj.baseChapterId+"',"+currentNodeNum+", this, event,"+obj.hasSectionChild+")\" ";
					html += ">";
					
					var pVal = $(pNode.getElementsByTagName("span")[0]).attr("value");	
					var pArr = pVal.split(",");
					pArr[currentNodeNum] = obj.baseChapterId;					
					
					html += '<span class="chapter-node" id="'+obj.baseChapterId+'" value="'+pArr.join()+'" >';
					
					if(hasChild){
					  html += '<i class="dot '+cls+'"></i>';
					}					
					html += obj.chapterName;
					html += '</span>';
					html += "</li>";
				};				
			}
			html += '</ul>';
			pNode.innerHTML += html;
		}, 'json');
    }

    //获取章节树
	function getChapterChildren(id, nodeNumber, currentNode, e,hasChild) {
		$(".gn-ChapterPointer .chapter-node").removeClass("selected").css({'background-color':'transparent'});
		var txt = currentNode.getElementsByTagName("span");
		$(txt[0]).addClass("selected").css({'background-color':'#f4f8fb'});
		searchQuestions();
		if(e.preventDefault) {
			e.stopPropagation();
			e.preventDefault();
		} else {
			window.event.cancelBubble = true;
			window.event.returnValue = false;
		}

		var nodes = currentNode.getElementsByTagName("ul");

		var exp = new RegExp("node-hide");

		if(nodes && nodes.length>0) {
			if(-1!=nodes[0].className.indexOf("node-hide")) {
				nodes[0].className = nodes[0].className.replace(exp, "");
				var dots = currentNode.getElementsByTagName("i");
				if(nodeNumber>2) dots[0].className = "open";
			} else {
				nodes[0].className = nodes[0].className+" node-hide";
				var dots = currentNode.getElementsByTagName("i");
				if(nodeNumber>2) dots[0].className = "close";
			}
		} else {
			var dots = currentNode.getElementsByTagName("i");
			if(nodeNumber>2 && dots.length >0 ) dots[0].className = "open";
			if(hasChild){
				switch(nodeNumber){
					case 0:
						bindChapterByVolumeId(currentNode,id,nodeNumber);
						break;
					case 1:
						bindSectionByChapterId(currentNode,id,nodeNumber);
						break;
				}
			};
		};
	}
    
    
	//获取知识点树
	function getKLGChildren(id, nodeNumber, currentNode, e,hasChild) {
		$(".gn-KnowledgePointer .klg-node").removeClass("selected").css({'background-color':'transparent'});
		var txt = currentNode.getElementsByTagName("span");
		$(txt[0]).addClass("selected").css({'background-color':'#f4f8fb'});
		searchQuestions();
		if(e.preventDefault) {
			e.stopPropagation();
			e.preventDefault();
		} else {
			window.event.cancelBubble = true;
			window.event.returnValue = false;
		}

		var nodes = currentNode.getElementsByTagName("ul");

		var exp = new RegExp("node-hide");

		if(nodes && nodes.length>0) {
			if(-1!=nodes[0].className.indexOf("node-hide")) {
				nodes[0].className = nodes[0].className.replace(exp, "");
				var dots = currentNode.getElementsByTagName("i");
				if(nodeNumber>2) dots[0].className = "open";
			} else {
				nodes[0].className = nodes[0].className+" node-hide";
				var dots = currentNode.getElementsByTagName("i");
				if(nodeNumber>2) dots[0].className = "close";
			}
		} else {
			var dots = currentNode.getElementsByTagName("i");
			if(nodeNumber>2 && dots.length >0 ) dots[0].className = "open";
			if(hasChild){
				bindSubKnowledageByParentId(currentNode,id,nodeNumber);
			};
		};
	}
	
	
    function bindSectionByChapterId(pNode,chapterId,nodeNumber){
    	$.post('${root}/commons/getAllSectionByChapterId.do', {chapterId:chapterId}, function (data) {	
			var html = '<ul>';
    		var currentNodeNum = (nodeNumber+1);
			if(data != null && data.length > 0){				
				for(var i = 0; i < data.length; i++){
					var obj =  data[i];				
					
					html += "<li class='node-"+currentNodeNum+"' ";
					html += "onclick=\"getChapterChildren('"+obj.baseSectionId+"',"+currentNodeNum+", this, event,false)\" ";
					html += ">";
					
					var pVal = $(pNode.getElementsByTagName("span")[0]).attr("value");					
					var pArr = pVal.split(",");
					pArr[currentNodeNum] = obj.baseSectionId;					
					
					html += '<span class="chapter-node" id="'+obj.baseSectionId+'" value="'+pArr.join()+'" >';					
					html += obj.sectionName;
					html += '</span>';
					html += "</li>";
				};				
			}
			html += '</ul>';
			pNode.innerHTML += html;
		}, 'json');    	
    }
    
    // 绑定母体与孪生题和衍生题切换事件
	function switchQues(obj){
		var index = $(obj).index();
		$(obj).siblings().removeClass("active");
		$(obj).parent(".quesBro").children("a").eq(index).addClass("active");
		//添加切换题目操作
		var queQuestionId = $(obj).prop("id");
		var url = "${root}/questionLib/getQuestionById.do";
		$.get(url,{"queQuestionId":queQuestionId},function(data){
			if(data){
				$(obj).parent(".quesBro").next(".quesWrap").empty();
				var qstObj = data;
				var qstHtml = "";
				qstHtml+='<div style="cursor:pointer;" onclick="showAnalytical(this)">'+isEmpty(qstObj.content)+'</div>';
				if(qstObj.contentVideo && qstObj.contentVideo != '' &&  qstObj.contentVideo != null){
					qstHtml += '<p><a href="javascript:;" class="playVideoP" ><button class="btn playVideo" id="'+qstObj.contentVideo+'" onclick="playVideo(this)" >点击播放音视频</button><span id="watch_play0s"></span></a></p>';
				}
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
				if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE' || type == 'JUDEMENT') {//单选和多选题目
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
					qstHtml += '<p><a href="javascript:;" class="playVideoP" ><button class="btn playVideo" id="'+qstObj.resolveVideo+'" onclick="playVideo(this)" >点击解析视频</button></a></p>';
				}
				$(obj).parent(".quesBro").next(".quesWrap").html(qstHtml);
			}
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
     //----------------------------------原始脚本
      $(function(){
    	 $(".gn-sourceTabs").on("click","a",function(){
    		 $(this).siblings().removeClass("gn-TopArrowSelected");
    		 $(this).addClass("gn-TopArrowSelected");
    		 
    		 var $elem = $(this);
    		 if($elem.attr("id") == 'chapterTab'){
    			 $(".gn-ChapterPointer").show();
    			 $(".gn-KnowledgePointer").hide();
    			 
    		 } else {
    			 $(".gn-KnowledgePointer").show();
    			 $(".gn-ChapterPointer").hide();
    		 }
    	 });
    	 
      });
     
      //打开导入习题的弹窗
  	 function uploadQuestion(){
 		 Win.open({id:'uploadQuestion',width:500,height:520,title:"导入习题",url:"${root}/questionLib/uploadQuestionPage.do"});
 	 }
      
  	function playVideo(obj){//点击播放视频
  		var videoPath = $(obj).prop("id");
  		var id="0s";
 		var index1=videoPath.lastIndexOf(".");
 		var index2=videoPath.length;
 		var postf=videoPath.substring(index1,index2);//获取文件后缀名
 		
 		var showClassId="watch_play"+id;
		var fileurl=API_PATH+"/Video/"+videoPath;
		
		
		//如果添加的音视频数目大于1则停止向下追加
		var showClassIndex="#"+showClassId;
		if($(obj).next(showClassIndex).find("audio").size()>=1){
			Win.alert("音频正在播放，请不要继续点击!");
			return;	
		}

 		//alert("postf="+postf);
 		if(postf.indexOf("mp3")>0){
 			//使用ajax获取本音频的据对路径，通过url
 			//http://172.17.54.4/public/Hair.mp3
          //alert("videoPath="+videoPath);
 		  $(obj).next(showClassIndex).append('&nbsp;&nbsp;&nbsp;<audio src="${root}/videos/'+videoPath+'" autoplay loop  controls></audio>'); 
					
 			
 		}else{
 			flashurl = '${PUBLIC_PATH}/public/flash/myflvPlayBack.swf?url='+fileurl+'&skin=${PUBLIC_PATH}/public/flash/MinimaFlatCustomColorAll.swf&autoplay=4';
 			Win.open({
 	        	title : "音视频播放窗口",
 	            mask : true,
 	            html : "<div id='myplayer'>&nbsp;</div>",
 	            width: 740,
 	            height: 510
 	        });
 			FlashPlayer($id('myplayer'),flashurl,{id:'player',"wh":[718,428]});
 			
 		}
	} 
    </script>
</body>
</html>