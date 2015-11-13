<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp"%>
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
				console.log(text);
				var html = "";
				if (text == "习题解析") {
					$.post("${root}/teacherWork/getResolve.do", {
						"queId" : queId
					}, function(data) {
						var html = data;
						if (data != "" || data != null) {
							$this.parent().parent().find(".showResolveView").html(data.resolve);
						}
					});
				} else if (text.trim() == "知识点") {
					var html = "";
					$.post("${root}/teacherWork/getQuestionKnowLedge.do", {
						"queId" : queId
					}, function(data) {
						if (data.length > 0) {
							for (var i = 0; i < data.length; i++) {
								html += "<span>" + data[i].knowLedgeName + "</span>";
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
					<a href="${root}/teacherWork/toTeacherWork.do">作业</a><span class="nextlevel">></span><a href="javascript:history.go(-1);">作业统计</a><span class="nextlevel">></span><span
						class="currentLevel">查看批阅</span>
				</div>
				<div class="borderBox gn-bgWhite pd20 pb0">
					<c:if test="${homeWorkQuestionInfo.readOverType != 'TEACHER'}">
						<h3 class="MarginTitle" id="workTitle">
							答题人：<span class="coffeeColor">${userName}</span>&nbsp;&nbsp;&nbsp;&nbsp;批阅人：<span
								class="coffeeColor">${user.userName}</span>
						</h3>
					</c:if>
					<c:if test="${homeWorkQuestionInfo.readOverType == 'TEACHER'}">
						<h3 class="MarginTitle" id="workTitle">
							答题人：<span class="coffeeColor">${userName}</span>
						</h3>
					</c:if>
				</div>
				<c:set var="title1" value="0"></c:set>
				<c:set var="title2" value="0"></c:set>
				<c:set var="title3" value="0"></c:set>
				<c:set var="title4" value="0"></c:set>
				<c:set var="title5" value="0"></c:set>
				<c:set var="title6" value="0"></c:set>
				<c:set var="questionNo" value="1"></c:set>
				

					<div class="borderBox gn-bgWhite borderBoxP40">
						<h3 class="titleOfCheckAnswer titleOfCheckAnswer1 clearfix BorderBottomNone">
							习题作业</h3>
						<div class="checkAnswerWrap">

							<div class="testCon checkAnsweContent">
							<c:if test="${!empty homeWorkQuestionInfo.questionInfo}">
								<c:forEach var="ques"
									items="${homeWorkQuestionInfo.questionInfo}" varStatus="s">
								<%@include file="../../../common/workBodytop.jsp"%>
									<div class="borderBox pd20" id="${ques.workQuestionId }">
										<div class="quesContent verticalMiddle">
										<%@include file="../../../common/readOverView.jsp" %>
											<div class="teaComments">
												<div style="display: block" class="borderBox comments">
													<p class="commentsKinds">
														<c:if test="${homeWorkQuestionInfo.readOverType != 'TEACHER'}">
															<a href="javascript:;" class="active"
																pid="${ques.workQuestionId}">学生点评</a>
														</c:if>
														<c:if test="${homeWorkQuestionInfo.readOverType == 'TEACHER'}">
															<a href="javascript:;" class="active"
																pid="${ques.workQuestionId}">老师点评</a>
														</c:if>

														<a href="javascript:;" pid="${ques.workQuestionId}">习题解析</a>
														<a href="javascript:;" pid="${ques.workQuestionId}">知识点</a>
													</p>
													<div style="display: block" class="pd10 commentContent"
														id="showCommentView">
														<c:if test="${homeWorkQuestionInfo.readOverType != 'TEACHER'}">
															<c:choose>
																<c:when
																	test="${not empty ques.comment && ques.comment ne ' '}">
																	<div class="imageContent">${ques.comment}</div>
																</c:when>
																<c:otherwise>
																	<div>学生还没有点评！</div>
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${homeWorkQuestionInfo.readOverType == 'TEACHER'}">
															<c:choose>
																<c:when
																	test="${not empty ques.comment && ques.comment ne ' '}">
																	<div class="imageContent">${ques.comment}</div>
																</c:when>
																<c:otherwise>
																	<div>老师还没有点评！</div>
																</c:otherwise>
															</c:choose>
														</c:if>
													</div>
													<div class="pd10 commentContent showResolveView"></div>
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
								<c:if test="${!empty homeWorkQuestionInfo.textQueContent}">
									<div class="TaskWrap mt30">
										<h3 class="titleOfCheckAnswer clearfix">文本作业</h3>
										<ul class="commonUL">
											<li><label class="text  fontBold">文本作业：</label> <span
												class="cont"> ${homeWorkQuestionInfo.textQueContent}
											</span></li>
										</ul>
										<ul class="commonUL">
											<li><label class="text  fontBold">学生答案：</label> <span
												class="cont"> ${homeWorkQuestionInfo.textQueAnswer} </span></li>
										</ul>

										<div class="teaComments">
											<div style="display: block" class="borderBox comments">
												<p class="commentsKinds">
													<c:if test="${homeWorkQuestionInfo.readOverType != 'TEACHER'}">
														<a href="javascript:;" class="active"
															pid="${ques.workQuestionId}">学生点评</a>
													</c:if>
													<c:if test="${homeWorkQuestionInfo.readOverType == 'TEACHER'}">
														<a href="javascript:;" class="active"
															pid="${ques.workQuestionId}">老师点评</a>
													</c:if>

												</p>
												<div style="display: block" class="pd10 commentContent"
													id="showCommentView">
													<c:if test="${!empty user}">
														<c:choose>
															<c:when
																test="${not empty homeWorkQuestionInfo.textQueComment && homeWorkQuestionInfo.textQueComment ne ' '}">
																<div class="imageContent">${homeWorkQuestionInfo.textQueComment}</div>
															</c:when>
															<c:otherwise>
																<div>学生还没有点评！</div>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${empty user}">
														<c:choose>
															<c:when
																test="${not empty homeWorkQuestionInfo.textQueComment &&homeWorkQuestionInfo.textQueComment ne ' '}">
																<div class="imageContent">${homeWorkQuestionInfo.textQueComment}</div>
															</c:when>
															<c:otherwise>
																<div>老师还没有点评！</div>
															</c:otherwise>
														</c:choose>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</c:if>
								<!-- 附件作业 -->
								<c:if test="${!empty homeWorkQuestionInfo.workDocList }">
									<div class="TaskWrap teaComments mt30">
										<h3 class="titleOfCheckAnswer clearfix">附件作业</h3>
										<ul class="commonUL">
											<li><label class="text  fontBold">附件作业：</label> <span class="cont"> 
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
													<br/>
													</c:forEach>
											</span></li>

											<li>
											<label class="text  fontBold">学生答案：</label>
											<span class="cont"> 
												<c:forEach
														items="${homeWorkQuestionInfo.workReceiveDoc}" var="file">
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
											<div style="display: block" class="borderBox comments">
												<p class="commentsKinds">
													<c:if test="${homeWorkQuestionInfo.readOverType != 'TEACHER'}">
														<a href="javascript:;" class="active"
															pid="${ques.workQuestionId}">学生点评</a>
													</c:if>
													<c:if test="${homeWorkQuestionInfo.readOverType == 'TEACHER'}">
														<a href="javascript:;" class="active"
															pid="${ques.workQuestionId}">老师点评</a>
													</c:if>

												</p>
												<div style="display: block" class="pd10 commentContent"
													id="showCommentView">
													<c:if test="${homeWorkQuestionInfo.readOverType != 'TEACHER'}">
														<c:choose>
															<c:when
																test="${not empty homeWorkQuestionInfo.docQueComment && homeWorkQuestionInfo.docQueComment ne ' '}">
																<div class="imageContent">${homeWorkQuestionInfo.docQueComment}</div>
															</c:when>
															<c:otherwise>
																<div>学生还没有点评！</div>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${homeWorkQuestionInfo.readOverType == 'TEACHER'}">
														<c:choose>
															<c:when
																test="${not empty homeWorkQuestionInfo.docQueComment &&homeWorkQuestionInfo.docQueComment ne ' '}">
																<div class="imageContent">${homeWorkQuestionInfo.docQueComment}</div>
															</c:when>
															<c:otherwise>
																<div>老师还没有点评！</div>
															</c:otherwise>
														</c:choose>
													</c:if>
												</div>
											</div>
										</div>
										</div>
								</c:if>   
								<!-- 总体点评 -->
								<div class="TaskWrap totalEvaluation mt30">
									<h3 class="titleOfCheckAnswer center coffee">总体评价</h3>
									<p class="mt-10">对本次作业总体点评：</p>
									<c:choose>
										<c:when
											test="${not empty homeWorkQuestionInfo.summary && homeWorkQuestionInfo.summary !=''}">
											<div class="totalComment">${homeWorkQuestionInfo.summary}</div>
										</c:when>
										<c:otherwise>
											<c:if test="${empty user}">
												<div class="totalComment">老师没有总体点评！</div>
											</c:if>
											<c:if test="${not empty user}">
												<div class="totalComment">学生没有总体点评！</div>
											</c:if>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				
			</div>
		</div>
	</div>
</body>
</html>
