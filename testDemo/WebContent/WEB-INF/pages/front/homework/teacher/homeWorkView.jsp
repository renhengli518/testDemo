<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css" />
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/js/video/showVideo.js"></script>
<style type="text/css">
#showInfo {
	width: 250px;
	height: 100px;
	border: 1px solid #f00;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".checkAnswerWrap").on("click", ".commentsKinds a", function() {
			var $this = $(this);
			$this.addClass("active").siblings().removeClass("active");
			var index = $this.index();
			$this.parent(".commentsKinds").siblings(".commentContent").eq(index).show().siblings(".commentContent").hide();
			if ($this.hasClass("showOne")) {
				$this.parent(".commentsKinds").siblings(".commentContent").eq(index).show().siblings(".commentContent").hide();
			} else {
				$this.addClass("showOne")
				var text = $this.html();
				var queId = $this.attr("pid");
				if (text.trim() == "知识点") {
					var html = "";
					$.post("${root}/teacherWork/getQuestionKnowLedge.do", {
						"queId" : queId
					}, function(data) {
						var knowLedgeName = "";
						if (data.length > 0) {
							for (var i = 0; i < data.length; i++) {
								if (data[i].knowLedgeName == null) {
									knowLedgeName = "";
								} else {
									knowLedgeName = data[i].knowLedgeName;
								}
								html += "<span>" + knowLedgeName + "</span><br/>";
							}
							$this.parent().parent().find(".showKnowLedgeView").html(html);
						}
					});
				}
			}
		})
	})
</script>
</head>
<body class="mainIndex">
	<%@include file="../../../common/topHeader.jsp"%>
	<%@include file="../../../common/workNav.jsp"%>
	<div class="container clearfix w1200 bkgNone marginauto">
		<div class="content">
			<div class="content-in">
				<div class="breadCrumb">
					<a href="${root}/teacherWork/toTeacherWork.do">作业</a><span class="nextlevel">></span>
					<span class="currentLevel">查看作业 </span>
				</div>
				<div class="borderBox gn-bgWhite pd20 pb0">
					<h3 class="MarginTitle BorderBottomNone" id="workTitle">
						作业名称：<span class="coffeeColor">${homeWorkQuestionInfo.workTitle}</span>
					</h3>
				</div>
			<c:set var="title1" value="0"></c:set>
				<c:set var="title2" value="0"></c:set>
				<c:set var="title3" value="0"></c:set>
				<c:set var="title4" value="0"></c:set>
				<c:set var="title5" value="0"></c:set>
				<c:set var="title6" value="0"></c:set>
				<c:set var="questionNo" value="1"></c:set>

				<div class="borderBox gn-bgWhite borderBoxP40">
					<h3 class="titleOfCheckAnswer clearfix">习题作业</h3>
					<div class="checkAnswerWrap" id="pageBody">
							<div class="testCon checkAnsweContent">
							<c:if test="${!empty homeWorkQuestionInfo.questionInfo}">
								<c:forEach var="ques"
									items="${homeWorkQuestionInfo.questionInfo}" varStatus="s">
					<%@include file="../../../common/workBodytop.jsp"%>
									<div class="borderBox pd20">
										<div class="quesContent verticalMiddle">
											<div class="quesTitle">
											<p class="fl fz20">${questionNo}、</p>
											${ques.content}
											</div>
											<c:if test="${!empty ques.contentVideo}">
											<p class="uploadMediaWrap mt20 mb20">
												<a href="javaScript:;"class="btn btnUpLoad uploadBox"  onclick="showContentVideo('${ques.contentVideo}','${ques.workQuestionId}','contentVideo');">点击播放音视频</a>
												<span id="showContentVideo${ques.workQuestionId}"></span>
											</p>
											</c:if>
											<c:set value="${questionNo + 1}" var="questionNo"></c:set>
												<c:forTokens items="${ques.options}" delims="∷" var="name">
													
													选项${name}
													<br>
												</c:forTokens>
								<c:if test="${ques.type == 'FILL_IN_BLANK'}">
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
														<c:if test="${ques.fillInAnswerType eq 'INDEPENDENT'}">
															<th>答案1</th>
															<th>答案2</th>
															<th>答案3</th>
															<th>答案4</th>
														</c:if>
														<c:if test="${ques.fillInAnswerType eq 'COMBINATION'}">
															<th>第一组</th>
															<th>第二组</th>
															<th>第三组</th>
															<th>第四组</th>
														</c:if>
													</tr>
												</thead>
											<tbody>
												<c:forEach var="fillInAnswer" items="${ques.fillInQuesAnswer}" varStatus="fs">
													<tr>
														<td>第${fs.index +1}空</td>
														<td class="edit-pop-btn">
															<div class="answerCon"">${fillInAnswer.answerGrp1}</div>
														</td>
														<td class="edit-pop-btn">
															<div class="answerCon">${fillInAnswer.answerGrp2}</div>
														</td>
														<td class="edit-pop-btn">
															<div class="answerCon">${fillInAnswer.answerGrp3}</div>
														</td>
														<td class="edit-pop-btn">
															<div class="answerCon">${fillInAnswer.answerGrp4}</div>
														</td>
													</tr>
											</c:forEach>
										</tbody>
									</table>
								</span>
							</li>
						</ul>
					</c:if>
											<c:if test="${ques.type != 'FILL_IN_BLANK'}">
												<c:if test="${ques.type != 'ASK_ANSWER' && ques.type !='COMPUTING'}">
													<span class="fontBold"> 正确答案： 
														<span class="mr40">${ques.answer}</span>
													</span>
												</c:if>
											
											</c:if>
											<span class="fontBold"> 难易度： 
												<span class="mr40">
													<c:choose>
														<c:when test="${ques.difficulty eq 'EASY'}">
															容易
														</c:when>
														<c:when test="${ques.difficulty eq 'LITTLE_EASY'}">
															较易
														</c:when>
														<c:when test="${ques.difficulty eq 'NORMAL'}">
															一般
														</c:when>
														<c:when test="${ques.difficulty eq 'LITTLE_DIFFICULT'}">
															较难
														</c:when>
														<c:otherwise>
															困难
														</c:otherwise>
													</c:choose>
												</span>
											</span>
											
											<div class="teaComments">
												<div style="display: block" class="borderBox comments">
													<p class="commentsKinds">
														<a href="javascript:;" class="active" pid="${ques.workQuestionId}">
															习题解析
														</a>
														<a href="javascript:;" pid="${ques.workQuestionId}">
															知识点
														</a>
													</p>
													<div style="display: block" class="pd10 commentContent showResolveView">
														${ques.resolve}
													</div>
													<div class="pd10 commentContent showKnowLedgeView"></div>
													
														<c:if test="${!empty ques.resolveVideo}">
															<p class="playVideoP">
																<button class="btn playVideo" onclick="playVideo('${ques.resolveVideo}','${ques.workQuestionId}')">点播解析视频</button>
															</p>
														</c:if>
													</div>
												</div>
										</div>
									</div>
								</c:forEach>
								</c:if>
							</div>
						
						<c:if test="${!empty homeWorkQuestionInfo.textQueContent}">
							<div class="TaskWrap mt30">
								<h3 class="titleOfCheckAnswer clearfix">文本作业</h3>
								<ul class="commonUL">
									<li><label class="text  fontBold">文本作业：</label> 
									<span class="cont"> ${homeWorkQuestionInfo.textQueContent} </span></li>
								</ul>
							</div>
						</c:if>
						<!-- 附件作业 -->
						<c:if test="${!empty homeWorkQuestionInfo.workDocList }">
							<div class="TaskWrap teaComments mt30">
								<h3 class="titleOfCheckAnswer clearfix">附件作业</h3>
								<ul class="commonUL">
									<li>
										<label class="text  fontBold">附件作业：</label> 
									
											<span class="cont"> 
											<c:forEach items="${homeWorkQuestionInfo.workDocList}" var="file">
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
													<br />
											</c:forEach>
											</span>
									</li>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>




