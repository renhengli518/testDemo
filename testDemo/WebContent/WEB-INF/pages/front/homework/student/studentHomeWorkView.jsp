<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/js/video/showVideo.js"></script>

<style type="text/css">
#showInfo {
	width:250px;
	height:100px;
	border:1px solid #f00;
}
</style>
</head>
<body class="mainIndex">
<%@include file="../../../common/topHeader.jsp"%>
<%@include file="../../../common/workNav.jsp"%>
<div class="container clearfix w1200 bkgNone marginauto">
	<div class="content">
		<div class="content-in">
			<div class="breadCrumb">
				<a href="${root}/studentWork/toStudentWork.do">作业</a><span class="nextlevel">></span><span class="currentLevel">查看作业 </span>
			</div>
			<div class="borderBox gn-bgWhite pd20 pb0">
				<h3 class="MarginTitle BorderBottomNone" id="workTitle">作业名称：<span class="coffeeColor">${workQuestionInfo.workTitle}</span></h3>
			</div>
			<!-- 数据列表 -->
				<c:set var="title1" value="0"></c:set>
				<c:set var="title2" value="0"></c:set>
				<c:set var="title3" value="0"></c:set>
				<c:set var="title4" value="0"></c:set>
				<c:set var="title5" value="0"></c:set>
				<c:set var="title6" value="0"></c:set>
				<c:set var="questionNo" value="1"></c:set>
				
				
				<div class="borderBox gn-bgWhite borderBoxP40">
				<div class="checkAnswerWrap">
				<h3 class="titleOfCheckAnswer titleOfCheckAnswer1 clearfix">
					习题作业
				</h3>
			<div class="testCon checkAnsweContent">
				<c:if test="${!empty workQuestionInfo.questionInfo}">
					<c:forEach var="ques" items="${workQuestionInfo.questionInfo}" varStatus="s">
						<%@include file="../../../common/workBodytop.jsp"%>		
			<div class="borderBox pd20">
				<div class="quesContent verticalMiddle">
					<div class="quesTitle">
						<p class="fl fz20">${questionNo}、</p>
							${ques.content}
					</div>
					
					<c:if test="${!empty ques.contentVideo}">
						<p class="uploadMediaWrap mt20 mb20">
							<a href="javaScript:;"class="btn btnUpLoad uploadBox" onclick="showContentVideo('${ques.contentVideo}','${ques.workQuestionId}','contentVideo');">点击播放音视频</a>
						<span id="showContentVideo${ques.workQuestionId}"></span>
						</p>
					</c:if>
					<c:set value="${questionNo + 1}" var="questionNo"></c:set>
						<c:forTokens items="${ques.options}" delims="∷" var="name">
							选项${name}<br>
						</c:forTokens>
					<c:if test="${status != 'PROGRESS'}">
						
								<c:if test="${ques.type == 'FILL_IN_BLANK'}">
										<ul class="commonUL">
									<li>
										<label class="text fontBold left">学生答案：</label>
												<span class="fontBold"> 
												<span class="mr40">
												<c:if test="${ques.type == 'FILL_IN_BLANK'}">
													<c:if test="${!empty ques.myAnswer}">
														<table class="anyTable">
														<tbody>
															<c:forEach items="${ques.fillInQuesAnswer}" varStatus="status" var="answer">
																<tr>
																	<td>第${status.index+1}空</td>
													
																	<td class="edit-pop-btn">
																		<div class="answerCon">
																				${ques.answers[status.index]}
																		</div>
																	</td>
													
																</tr>
															</c:forEach>
														</tbody>
													</table>
													</c:if>
												</c:if>
											</span>
										</span>
										</li>
										</ul>
								</c:if>
						<c:if test="${ques.type != 'FILL_IN_BLANK'}">
							<c:if test="${ques.type == 'ASK_ANSWER' || ques.type =='COMPUTING'}">
								<div class="teaComments">
							 <ul class="commonUL">
								<li>
									<label class="text left fontBold">学生答案:</label>
									<span class="cont"> ${ques.myAnswer} </span> 
								</li>
							</ul>
							 </div>
							 <c:if test="${!empty ques.answerVideo}">
								<p class="uploadMediaWrap mt20 mb20">
									<a href="javaScript:;" class="btn btnUpLoad uploadBox" onclick="showContentVideo('${ques.answerVideo}','${ques.workQuestionId}','answerVideo');">点击播放音视频</a>
									<span id="showAnswerVideo${ques.workQuestionId}"></span>
								</p>
								</c:if>
							</c:if>
							<c:if test="${ques.type != 'ASK_ANSWER' && ques.type !='COMPUTING'}">
								<span class="fontBold">  学生答案:
								<span class="mr40"> ${ques.myAnswer}
								</span> 
								</span> 
							</c:if>
						</c:if>
				</c:if>
			</div>
		</div>
	</c:forEach>
			</c:if>  
			<c:if test="${!empty workQuestionInfo.textQueContent}">
				<div class="TaskWrap mt30">
					<h3 class="titleOfCheckAnswer clearfix">文本作业</h3>
						<ul class="commonUL">
							<li>
								<label class="text  fontBold">文本作业：</label>
								<span class="cont">
								${workQuestionInfo.textQueContent}
								</span>
							</li>
							<c:if test="${status != 'PROGRESS'}">
							<li>
								<label class="text fontBold">学生答案：</label> 
								<span class="cont"> ${workQuestionInfo.textQueAnswer} </span>
							</li>
							</c:if>					
						</ul>
				</div>
			</c:if>
			<!-- 附件作业 -->
				<c:if test="${not empty workQuestionInfo.workDocList}">
					<div class="TaskWrap teaComments mt30">
						<h3 class="titleOfCheckAnswer clearfix">附件作业</h3>
							<ul class="commonUL">
								<li><label class="text  fontBold">附件作业：</label> 
									<span class="cont"> 
										<c:forEach items="${workQuestionInfo.workDocList}" var="file">
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
									<c:if test="${status != 'PROGRESS'}">
									<li>
										<label class="text  fontBold">学生答案：</label>
											<span class="cont"> 
												<c:forEach items="${workQuestionInfo.workReceiveDoc}" var="file">
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
									</c:if>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>




