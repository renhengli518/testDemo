<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/js/video/showVideo.js"></script>
<style>
.text-cont{width:100%;margin:10px 0;resize:none;height:100px;}
</style>
<!--  引入富文本框需要的插件-->
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/third-party/blank/addBlankButton.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/third-party/blank/addBlankButton.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/js/video/showVideo.js"></script>
</head>
<body class="mainIndex">
    <%@include file="../../../common/topHeader.jsp" %>
	<%@include file="../../../common/workNav.jsp" %>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
          <form id="correcting" action="" method="post">
					<input type="hidden" name="homeworkReceiverId" value="${homework.workReceiveStuId }">
					<input type="hidden" id="ExercisesCommonets" name="ExercisesCommonets" value="">
					<input type="hidden" id="homeworkTextCommonet" name="homeworkTextCommonet" value="">
					<input type="hidden" id="homeworkAnnexCommonet" name="homeworkAnnexCommonet" value="">
					<input type="hidden" id="homeworkGeneralCommonet" name="homeworkGeneralCommonet" value="">
              <div class="breadCrumb">
               <c:if test="${work.readOverType == 'TEACHER'}">
               <a href="${root}/teacherWork/toTeacherWork.do">作业</a><span class="nextlevel">></span><a href="javascript:history.go(-1);">作业批阅</a><span class="nextlevel">></span><span class="currentLevel">批阅</span>
               </c:if>
                <c:if test="${work.readOverType != 'TEACHER'}">
                <a href="${root}/studentWork/myReadOver.do">作业</a><span class="nextlevel">></span><a href="javascript:history.go(-1);">作业批阅</a><span class="nextlevel">></span><span class="currentLevel">批阅</span>
                </c:if>
              </div>
              <div class="borderBox gn-bgWhite pd20 pb0">
              <c:if test="${work.readOverType == 'TEACHER'}">
                  <h3 class="MarginTitle BorderBottomNone">作业名称：<span>${homework.workTitle } </span>
                   	 答卷人：<span>${homework.answerUser }</span></h3> 
                   	 </c:if>
                   	 <c:if test="${work.readOverType != 'TEACHER'}">
                   	  <h3 class="MarginTitle BorderBottomNone">作业名称：<span>${homework.workTitle} </span>
                   	 批阅学生：<span>${userName}</span></h3>   
                   	 </c:if>  
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite borderBoxP40">
			  	<c:if test="${!empty homework.questions }">
			  		<h3 class="titleOfCheckAnswer titleOfCheckAnswer1 clearfix">
	                                         习题作业
	                </h3>
	                <div class="checkAnswerWrap">
				    	<c:set var="subjectiveIds" value=""></c:set>
				     	<c:set var="queTypeTitleIndexName" value="${fn:split('一,二,三,四,五,六',',')}" />
				     	<c:set var="se" value="${fn:split('A,B,C,D,E,F,G',',')}" />
				     	<c:set var="queTypeTitleIndex" value="0"></c:set>
				     	<c:set var="queType" value=""></c:set>
				     	
				     	<c:forEach items="${homework.questions }" var="que" varStatus="status">
				     		<c:if test="${queType ne que.type}">
				     			<c:if test="${!status.first}">
				     				</div>
				     			</c:if>
				     			<c:set var="queType" value="${que.type}"></c:set>
				     			<div class="testCon checkAnsweContent">
				     				<h4 class="quesKind">${queTypeTitleIndexName[queTypeTitleIndex]}、${queTypeTitleName[que.type] }</h4>
				     				<c:set var="queTypeTitleIndex" value="${queTypeTitleIndex +1 }"></c:set>
				     		</c:if>
				     		<div class="borderBox queDiv pd20" queId="${que.workQuestionId}" queType="${que.type}" queIndex="${status.count }">
					             <div class="quesContent verticalMiddle">
					             <div class="quesTitle">
					             	<p class="fl"><span class="fz20">${status.count }、</span>  
						            <c:if test="${que.type != 'ASK_ANSWER' && que.type !='COMPUTING'}">
						             	<c:choose>
											<c:when test="${que.correctFlag eq 'N'}">
												<span class="red">[答错]</span>
											</c:when>
											<c:otherwise>
												<span class="green">[答对]</span>
											</c:otherwise>
										</c:choose>
									</c:if>
					             	</p>${que.content }
								 </div>
					             <c:if test="${!empty que.contentVideo}">
									<p class="uploadMediaWrap mt20" id="contentVideoShow">
										<a href="javaScript:;" class="btn btnUpLoad uploadBox contentVideoShow" onclick="showContentVideo('${que.contentVideo}','${que.workQuestionId}','contentVideo');">点击播放音视频</a>
										<span id="showContentVideo${que.workQuestionId}"></span>
									</p>
								 </c:if>
								 <c:if test="${que.type eq 'SINGLE_CHOICE' || que.type eq 'JUDEMENT' || que.type eq 'MULTI_CHOICE'}">
						             <c:forTokens items="${que.options}" delims="∷" var="option" varStatus="opStatus">
						             	选项${option }<br/>
		                             </c:forTokens>
		                             <span class="fontBold">  正确答案：  <span class="mr40"> ${que.answer } </span> 
		                             <span class="fontBold">  学生答案：  <span class="mr40"> ${que.myAnswer } </span> 
				                     </span>
			                     </c:if>
			                     <c:if test="${que.type eq 'FILL_IN_BLANK'}">
			                     	<ul class="commonUL">
				                     <li>
				                       <label class="text fontBold left">正确答案：</label>
				                       <span class="font">
				                          <table collapse="" class="anyTable">
								           <thead>
								             <tr>
								               <th>
								                 <div class="specialTH">
								                   <span class="rightTop">答案容错</span>
								                   <span class="leftBottom">填空</span>
								                 </div> 
								              </th>
								              <c:if test="${que.fillInAnswerType eq 'INDEPENDENT'}">
												<th>答案1</th>
												<th>答案2</th>
												<th>答案3</th>
												<th>答案4</th>
											  </c:if>
											  <c:if test="${que.fillInAnswerType eq 'COMBINATION'}">
												<th>第一组</th>
												<th>第二组</th>
												<th>第三组</th>
												<th>第四组</th>
											  </c:if>
								            </tr>
								          </thead>
								          <tbody>
								            <c:forEach var="fillInAnswer" items="${que.fillInAnswers}" varStatus="fs">
												<tr>
													<td>第${fs.index +1}空</td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp1}</div></td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp2}</div></td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp3}</div></td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp4}</div></td>
												</tr>
											</c:forEach>
								          </tbody>
								        </table>
				                       </span>
				                     </li>
				                   </ul>
				                   <ul class="commonUL">
				                     <li>
				                       <label class="text fontBold left">学生答案：</label>
				                       <span class="font">
				                          <table collapse="" class="anyTable">
								          <tbody>
								          	<c:forEach items="${que.fillInAnswers}" varStatus="status" var="answer">
														<tr>
															<td>第${status.index+1}空</td>
													
															<td class="edit-pop-btn">
																<div class="answerCon">
																	${que.answers[status.index]}
																</div>
															</td>
														</tr>
											</c:forEach>
								          </tbody>
								        </table>
				                       </span>
				                     </li>
				                   </ul>
			                     </c:if>
			                     <c:if test="${que.type eq 'ASK_ANSWER' || que.type eq 'COMPUTING'}">
			                     	<div class="teaComments">
					                    <ul class="commonUL">
					                      
					                      <li>
					                        <label class="text fontBold left">学生答案：</label>
					                        <span class="cont">
					                            ${que.myAnswer}
					                        </span>
					                      </li>
					                      <c:if test="${!empty que.answerVideo}">
										<p class="uploadMediaWrap mt20" id="stuAnswerVideo">
										<a href="javaScript:;" class="btn btnUpLoad uploadBox stuAnswerVideo" onclick="showContentVideo('${que.answerVideo}','${que.workQuestionId}','answerVideo');">点击播放音视频</a>
										<span id="showAnswerVideo${que.workQuestionId}"></span>
									</p>
										</c:if>
					                    </ul>
					             	</div>    
			                     </c:if>
			                     <span class="fontBold"> 难易度： 
												<span class="mr40">
													<c:choose>
														<c:when test="${que.difficulty eq 'EASY'}">
															容易
														</c:when>
														<c:when test="${que.difficulty eq 'LITTLE_EASY'}">
															较易
														</c:when>
														<c:when test="${que.difficulty eq 'NORMAL'}">
															一般
														</c:when>
														<c:when test="${que.difficulty eq 'LITTLE_DIFFICULT'}">
															较难
														</c:when>
														<c:otherwise>
															困难
														</c:otherwise>
													</c:choose>
												</span>
											</span>
			                     <div class="teaComments">
			                        <div class="borderBox comments" style="display:block">
				                        <p class="commentsKinds">
				                        <c:if test="${work.readOverType == 'TEACHER'}">
					                      <a href="javascript:;" class="active">老师点评</a>
					                      </c:if>
					                      <c:if test="${work.readOverType != 'TEACHER'}">
					                      <a href="javascript:;" class="active">学生点评</a>
					                      </c:if>
					                    </p>
					                    <div class="pd10 commentContent commentContentQue" data-id="${que.workQuestionId}" style="display:block">
				                       		<script class="teacherComment" queId="${que.workQuestionId}" id="${que.workQuestionId}_comment" type="text/plain" style="height:210px;width:100%"></script>
				                        </div>
				                    </div>
				             	</div>
					        	</div>
					        </div>
		     				<c:if test="${status.last}">
			     				</div>
			     			</c:if>
				     	</c:forEach>
	                </div>
			  	</c:if>
			  	
			  	<c:if test="${!empty homework.textQueContent and (homework.textQueReadOverFlag eq 'N')}">
			  	
              <div class="TaskWrap mt30">
                 <h3 class="titleOfCheckAnswer clearfix">文本作业</h3>
                 <ul class="commonUL">
                   <li>
                     <label class="text  fontBold">文本作业：</label>
                     <span class="cont">
							${homework.textQueContent}
                     </span>
                   </li>
                   <li>
                     <label class="text  fontBold">学生答案：</label>
                     <span class="cont">
							${homework.textQueAnswer}
                     </span>
                   </li>
                 </ul>
                  <div class="teaComments">
			                        <div class="borderBox comments" style="display:block">
				                        <p class="commentsKinds">
					                     <c:if test="${work.readOverType == 'TEACHER'}">
					                      <a href="javascript:;" class="active">老师点评</a>
					                      </c:if>
					                      <c:if test="${work.readOverType != 'TEACHER'}">
					                      <a href="javascript:;" class="active">学生点评</a>
					                      </c:if>
					                    </p>
					                    <div class="pd10 commentContent" style="display:block">
				                       		<script id="textComment" type="text/plain" style="height:210px;width:100%"></script>
				                        </div>
				                    </div>
				             	</div>
              </div>
              </c:if>
            <c:if test="${fn:length(homework.docs)>0 and (homework.docQueReadOverFlag eq 'N')}">
              <div class="TaskWrap docTeaComments mt30">
                 <h3 class="titleOfCheckAnswer clearfix">附件作业</h3>
                 <ul class="commonUL">
                   <li>
                     <label class="text  fontBold">附件作业：</label>
                     <span class="cont">
                        <c:forEach items="${homework.docs}" var="doc">
							<span class="lightBlue">${doc.docName}</span>
							<span class="ml30">大小：
								<c:if test="${doc.docSize/1024/1024 >= 1}">
									<fmt:formatNumber type="number" value="${doc.docSize/1024/1024}" maxFractionDigits="2"/>M
								</c:if>
								<c:if test="${doc.docSize/1024/1024 < 1}">
									<fmt:formatNumber type="number" value="${doc.docSize/1024}" maxFractionDigits="2"/>K
								</c:if>
							</span>
							<a class="ml30 lightBlue" href="${root}/images/${doc.docPath}">下载</a>
							<br/>
						</c:forEach>
                     </span>
                   </li>
                   <li>
											<label class="text  fontBold">学生答案：</label>
											<span class="cont"> 
												<c:forEach
														items="${homework.receiveDocs}" var="file">
														<span class="lightBlue">${file.docName}</span>
												<span class="ml30">大小：
													<c:if test="${file.docSize/1024/1024 >= 1}">
										<fmt:formatNumber type="number" value="${file.docSize/1024/1024}" maxFractionDigits="2"/>M
													</c:if>
													<c:if test="${file.docSize/1024/1024 < 1}">
										<fmt:formatNumber type="number" value="${file.docSize/1024}" maxFractionDigits="2"/>K
													</c:if>
												</span>
												<a class="ml30 lightBlue" href="${root}/images/${file.docPath}">下载</a>
												<br/>
													</c:forEach>
											</span>
											</li>
                 </ul>
         			<div class="teaComments">
			            <div class="borderBox comments" style="display:block">
				            <p class="commentsKinds">
					          <c:if test="${work.readOverType == 'TEACHER'}">
					            <a href="javascript:;" class="active">老师点评</a>
					          </c:if>
					          <c:if test="${work.readOverType != 'TEACHER'}">
					           <a href="javascript:;" class="active">学生点评</a>
					          </c:if>
					        </p>
					    <div class="pd10 commentContent" style="display:block">
				          <script id="docComment" type="text/plain" style="height:210px;width:100%"></script>
				        </div>
				        </div>
				    </div>
              </div>
              </c:if>
              <div class="TaskWrap totalEvaluation mt30">
                <h3 class="titleOfCheckAnswer center coffee">总体评价</h3>
                
                <p class="mt-10">对本次作业总体点评：</p>
               
                <div class="totalComment">
                	<textarea class="text-cont" id="generalHomeworkComment"></textarea>
                	<div class="stuReview">
                		<span class="ml20" >常用评语：</span>
                		<select class="reviewWords" id="selectComment">
                			
                		</select>
                		<button type="button" class="btn btnGreen insertBtn" onclick="attachCommonent();">插入点评</button>
                		<button type="button" class="btn btnGreen insertBtn" onclick="saveAsCommonly();">插入常用评语</button>
                	</div> 
                </div>
              </div>
              <p class="center mt40">
                <button type="button" class="btn btnSearch nextStep" onclick="saveCheck();">完成批阅</button>
              </p>
              </form>
          </div>
       </div>
    </div>
</body>
<script type="text/javascript">
var commentResult = {};
var textComment;
var docComment;
var homeWorkId = "${homework.homeworkId}";
var haveDoc = ${!empty homework.docs && homework.docQueReadOverFlag eq 'N'};
var haveText = ${!empty homework.textQueContent && homework.textQueReadOverFlag eq 'N'};
$(function(){
	$(".commentContentQue").each(function(){
		var queId = $(this).attr("data-id");
		commentResult[queId] = UE.getEditor(queId+"_comment");
	});
	
	if(haveDoc){
		docComment = UE.getEditor("docComment");
	}
	var textQueContent = "${homework.textQueContent}";
	var textQueReadOverFlag = "${homework.textQueReadOverFlag}";
	if(haveText){
		textComment = UE.getEditor("textComment");
	}
	/* 老师评论下拉框绑定 */
	bindSelectComments();
	
});


/* 将评论加入常用评论 */
function saveAsCommonly(){
	var generalHomeworkComment = $("#generalHomeworkComment").val();
	if(generalHomeworkComment.length <= 0){
		Win.alert("评论不可为空！");
		return false;
	}
	if(generalHomeworkComment.length > 150){
		Win.alert("常用评语不可超过150字！");
		return false;
	}
	$.post("${root}/homework/addComment.do",{"comment":generalHomeworkComment},function(data){
		if(data && data.result){
			Win.alert("添加成功！");
			//刷新下拉框
			bindSelectComments();
		}else if(data && !data.result){
			Win.alert(data.message);
		}else{
			Win.alert("添加失败！");
		}
	});
}
/* 老师评论下拉框绑定 */
function bindSelectComments(){
	var url = "${root}/homework/findComments.do";
	$.get(url , function(result){
		$("#selectComment").empty();
		result.unshift({"id":"", "commentContent":""});
		$.each(result, function(index, json){
			$("#selectComment").append("<option title='"+json.commentContent+"' value='" + json.commentContent + "'>" + splitNormalString(json.commentContent,26) + "</option>");
		});
	}, "json");
}
function splitNormalString(value, size) {
	
	if((value)  && (value.length > size)) {
		
		return value.substring(0,size) + "..." ;
	}else if(value == null || value == 'undefined'){
		return "-";
	}else{
		return value;
	}
}
/* 插入点评 */
function attachCommonent(){
	if($("#selectComment").val() == null){
		Win.alert("点评不可为空！");
		return false;
	}
	var generalHomeworkComment = $("#generalHomeworkComment").val();
	var selectedComment = generalHomeworkComment + $("#selectComment").val();
	if(selectedComment.length > 150){
		selectedComment = selectedComment.substr(0,150);
	}
	
	$("#generalHomeworkComment").val(selectedComment);
	
}
/*提交*/
function saveCheck(){
	var textContent = "${homework.textQueContent}";
	var textAnswer = "${homework.textQueAnswer}";
	var docContent = "${homework.docs}";
	var docAnswer = "${homework.receiveDocs}";
	var commentsInfo = new Array();
	var number = 0;
	$(".teacherComment").each(function(index, element){
		var queId = $(this).parent("div").attr("data-id");
		var sa = commentResult[queId].getContent();
		if(commentResult[queId].getContentTxt().length > 500){
			number = index + 1;
		}
		var answerTemp = {};
		
		answerTemp["id"] = queId;
		answerTemp["comment"] = sa;
	
		commentsInfo.push(answerTemp);
		console.log("commentsInfo "+commentsInfo);
		
	});
	if(number > 0){
		Win.alert("评语不能超过500字！");
		return ;
	}
	
	$("#ExercisesCommonets").val(JSON.stringify(commentsInfo));
	if(textContent != null && textAnswer != null){
		if(textComment){
			var result = textComment.getContent();
			$("#homeworkTextCommonet").val(result);
			
			if(textComment.getContentTxt().length > 500){
				Win.alert("评语不能超过500字！");
				return false;
			}
		}
	}
		
	if(docContent != null && docAnswer != null){
		if(docComment){
			var result = docComment.getContent();
			$("#homeworkAnnexCommonet").val(result);
			
			if(docComment.getContentTxt().length > 500){
				Win.alert("评语不能超过500字！");
				return false;
			}
		}
	}
		
	var generalHomeworkComment = $("#generalHomeworkComment").val();
	if(generalHomeworkComment == "" || generalHomeworkComment == null || generalHomeworkComment.length <= 0){
		Win.alert("必须填写总体评论!");
		return false;
	}else if(generalHomeworkComment.length > 150){
		Win.alert("总体评论不能超过150字!");
		return false;
	}
	$("#homeworkGeneralCommonet").val(generalHomeworkComment);
	
	Win.confirm({html : '<span class="">确认要提交该批阅吗？</span>',mask:true}, function () {
		submitComments();
	});
}

function submitComments() {
	var type = "${work.readOverType}";
	$.post("${root}/homework/correctHomework.do?workId=${homework.homeworkId}",$("#correcting").serialize(), 
		function(data){
			if(data && data.result){
				if(type == 'TEACHER'){
					window.parent.location.href = "${root}/homework/readOverHomework.html?homeworkId=${homework.homeworkId}";
				}else{
					window.parent.location.href = "${root}/studentWork/toReadOverView/${homework.homeworkId}/${userId}.html";
				}
				
			}else if(data && !data.result){
				Win.alert(data.message);
			}else{
				Win.alert("失败！");
			}
		}
	);
}

</script>
</html>