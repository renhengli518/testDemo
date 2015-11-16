<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>

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

<script>
 $(function(){
	var scrollWidth=parseInt($('.scrollUL li').first().width()+12);
	var totalNum=$('.scrollUL li').size()-1;
	var index=$('.scrollUL li.active').index();
	if(!index){index=0};
	$(".scrollUL").css({"left":-$(".scrollUL li").eq(index).position().left})
	$(".scrollWrap .nextBtn").click(function(){
		$(".scrollUL").stop(true,true);
		if(index>=totalNum){return}
		else{
			index++;
			$(".scrollUL").animate({left:-$(".scrollUL li").eq(index).position().left},300)
		}
	});
	$(".scrollWrap .prevBtn").click(function(){
		$(".scrollUL").stop(true,true);
		if(index<1){return}
		else{
			index--;
			$(".scrollUL").animate({left:-$(".scrollUL li").eq(index).position().left},300)
		}
	})
})
</script>
</head>
<body class="mainIndex">
<%@include file="../../../common/topHeader.jsp" %>
<%@include file="../../../common/workNav.jsp" %>
	<div class="container clearfix w1200 bkgNone marginauto">
		<div class="content">
			<div class="content-in">
				<div class="breadCrumb">
					<span class="fr examOrder">
						<button class="btn prev">上一题</button>
						<button class="btn next">下一题</button>
					</span>
				<div class="gn-overflowHidden">
				<a href="${root}/teacherWork/toTeacherWork.do">作业</a><span class="nextlevel">></span><a href="${root}/homework/readOverHomework.do?homeworkId=${homeworkId}&classId=${classId}">作业批阅</a><span class="nextlevel">></span><span class="currentLevel">批阅 </span>
				</div>
				</div>
			<!-- 数据列表 -->
			<div class="borderBox gn-bgWhite borderBoxP40 paddingBottom0">
				<h3 class="titleOfCheckAnswer titleOfCheckAnswer1 clearfix">
 					 作业
				<span class="fr lastQues fz14">还有<i class="fontNormal"><font id="unReadNum">${unReadNum }</font>道题</i>未批阅</span>
				</h3>
				
			<div class="picScroll">
				<div class="tableWrap bkgWhite">
					<c:set var="questionIndex" value="-1"></c:set>
					<c:forEach items="${homework.questions }" var="que" varStatus="status">
						<div class="quesContent quesInfo quesContentSpecial ml30 mt30" id="question${status.index}" data-qType="${que.type}" data-qid="${que.workQuestionId}" data-type="que" <c:if test="${status.index != 0}">style="display:none;"</c:if> >
							<div class="quesTitle">
								<p class="fl fz20">${status.count }、</p>${que.content }
								<c:if test="${!empty que.contentVideo}">
									<p class="uploadMediaWrap mt20 mb20" id="contentVideo">
									<a href="javaScript:;"class="btn btnUpLoad uploadBox" onclick="showContentVideo('${que.contentVideo}','${que.workQuestionId}','contentVideo');">点击播放音视频</a>
									<span id="showContentVideo${que.workQuestionId}"></span>
									</p>
								</c:if>
							</div>
						<ul class="commonUL labelWidth70 mt10">
							<li>
								<label for="" class="text fontBold ">正确答案：</label>
									<span class="cont"><b>${que.resolve}</b></span>
									<c:if test="${!empty que.resolveVideo}">
							<p class="uploadMediaWrap mt20 mb20">
								<button class="btn playVideo" onclick="playVideo('${que.resolveVideo}','${que.workQuestionId}')">点播答案视频</button>
							</p>
							</c:if>
							</li>
							<li>
								<label for="" class="text fontBold ">难易度：</label>
								<span class="cont fontBold">
								<c:choose>
									<c:when test="${que.difficulty eq 'EASY' }">容易</c:when>
									<c:when test="${que.difficulty eq 'LITTLE_EASY' }">较容易</c:when>
									<c:when test="${que.difficulty eq 'NORMAL' }">一般</c:when>
									<c:when test="${que.difficulty eq 'LITTLE_DIFFICULT' }">较难</c:when>
									<c:otherwise>困难</c:otherwise>
								</c:choose>
								</span>
							</li>
						</ul>
						</div>
						<c:set var="questionIndex" value="${status.index }"></c:set>
					</c:forEach>
					<c:if test="${!empty homework.textQueContent }">
						<c:set var="questionIndex" value="${questionIndex +1 }"></c:set>
						<div class="quesContent quesInfo quesContentSpecial ml30 mt30" id="question${questionIndex}" data-type="text" <c:if test="${questionIndex != 0}">style="display:none;"</c:if> >
						<p class="quesTitle">
							${homework.textQueContent }
						</p>
					</div>	
					</c:if>
					<c:if test="${!empty homework.docs }">
						<c:set var="questionIndex" value="${questionIndex +1 }"></c:set>
						<div class="ml50 homeWorksList quesInfo" id="question${questionIndex}" data-type="file" <c:if test="${questionIndex != 0}">style="display:none;"</c:if> >
						<ul class="commonUL">
							<c:forEach items="${homework.docs }" var="doc">
								<li>
							<span class="mr20 lightBlue">${doc.docName }</span>
								 大小：<span class="mr20">
												<c:if test="${doc.docSize/1024/1024 >= 1}">
													<fmt:formatNumber type="number" value="${doc.docSize/1024/1024}" maxFractionDigits="2"/>M
												 </c:if>
												 <c:if test="${doc.docSize/1024/1024 < 1}">
													<fmt:formatNumber type="number" value="${doc.docSize/1024}" maxFractionDigits="2"/>K
												 </c:if>
											</span>
							</li>
							</c:forEach>
						</ul>
					</div>	
					</c:if>
				<div id="banner" class="scrollWrap clearfix">
				<a href="javascript:;" id="prevbt" class="prevBtn"><</a>
				<a href="javascript:;" id="nextbt" class="nextBtn">></a>
				<div id="scrollPic" class="clearfix">
				<ul class="scrollUL">
					<c:forEach items="${students }" var="student">
					<li data-userName="${student.realname }" data-workReceiveStuId="${student.workReceiveStuId }">
					<a href="javascript:;"><img src="${root }/images/${student.headPic}"></a>
					<span class="stuName">${student.realname }</span>
					</li>
					</c:forEach>
				</ul>
				</div>
				</div>
	
			<div class="quesContent quesContentSpecial newPiyueDesc  pb20">
			<p class="submiter" id="answerInfo"></p>
			<div class="stuAnswer changeColor" id="stuAnswerInfo"></div>
			<div class="teaComments ml50">
				<div class="teaComments bkgWhite">
	<div class="borderBox comments" style="display:block">
				<p class="commentsKinds">
					<a href="javascript:;" class="active">老师点评</a>
					</p>
					<div class="pd10 commentContent" id="workComment" style="display:none">
				</div>
				<div id="workCommentEdit">
					<script id="teacherComment" type="text/plain" style="height:210px;width:100%"></script>
				</div>
				</div>
			</div>
			</div>
			<p class="center mt20">
				<button id="submitBtn" class="btn btnSub mb30 bigColorfullBtn">提交并批阅下一个</button>
			</p>
			</div>
				</div>
			</div>
</div>
<h3 class="clearfix mt10">
<span class="fr examOrder mr20">
<button class="btn prev mr20">上一题</button>
<button class="btn next">下一题</button>
</span>
</h3>
</div>
</div>
</div>
</body>
<script type="text/javascript">
var editor = UE.getEditor("teacherComment");

(function(){
	var questions = window.questions = (function () {
		var index = 0;
		var $questions = $('.quesInfo');
		var len = $questions.length;
		var $prev = $('.examOrder .prev');
		var $next = $('.examOrder .next');
		return {
			getQuestion: function () {
				return $($questions[index]);
			},
			html: function (i, stuIndex) {
				$prev.removeClass('btnGray');
				$next.removeClass('btnGray');
				if (index < 0) {
					index = 0;
				}
				 if (index > len-1) {
					index = len-1;
				}
				if (index == 0) {
					$prev.addClass('btnGray');
				}
				if (index == len-1) {
					$next.addClass('btnGray');
					index = len-1;
				}
				$questions.hide();
				$questions[index].style.display = "block";
				if (this.change) {
					this.change(index, stuIndex);
				}
			},
			prev: function () {
				this.html(--index);
			},
			next: function () {
				this.html(++index);
			},
			go: function (i, stuIndex) {
				index = i;
				this.html(i, stuIndex);
			},
			init: function () {
				this.html();
				$prev.on('click', function () {
					if (!$(this).hasClass('btnGray')) {
						questions.prev();
					}
				});
				$next.on('click', function () {
					if (!$(this).hasClass('btnGray')) {
						questions.next();
					}
				});
			}
		};	
	})();



	var students = window.students = (function () {
		var liWidth = 74;
		var index = 0;
		var showLen = 10;
		var $box = $('#banner ul');
		var $students = $('#banner li');
		var len = $students.length;
		var $prev = $('#prevbt');
		var $next = $('#nextbt');
		return {
			getIndex: function () {
				return index;
			},
			getSelIndex: function () {
				return $box.find('.active').index();
			},
			html: function () {
				$prev.removeClass('btnGray');
				$next.removeClass('btnGray');
				if (index == 0) {
					$prev.addClass('btnGray');
				} else if (index < 0) {
					index = 0;
				} else if (index == len - 1) {
					$next.addClass('btnGray');
					index = len -1;
				} else if (index > len-1) {
					index = len-1;
				}
				if (index <= len - showLen) {
					var margentLeft = liWidth * index * -1;
					$box.animate({marginLeft: margentLeft}, 300);
				}
				//$students.removeClass('selected');
				//$($students[index]).addClass('selected');
				if (this.change) {
					this.change(index);
				}
			},
			go: function (n, flag) {
				index = n;
				this.html();
				flag = flag || false;
				if (flag) {
					if (n < 0) n = 0;
					if (n > len -1) n = len -1;
					this.select(n);
				} else {
					if (0 <= n && n <= len-1) {
						this.select(n);
					}
				}
				
			},
			prev: function () {
				this.html(--index);
			},
			next: function () {
				this.html(++index);
			},
			select: function (i) {
				$students.removeClass('active');
				var $elm = $($students[i]).addClass('active');
				if (this.onSelect) {
					this.onSelect($elm);
				}
			},
			init: function () {
				this.html();
				//this.select(0);
				$students.on('click', function () {
					students.select($(this).index());
				});
				$prev.on('click', function () {
					if (!$(this).hasClass('btnGray')) {
						students.prev();
					}
				});
				$next.on('click', function () {
					if (!$(this).hasClass('btnGray')) {
						students.next();
					}
				});
			}
		};		
	})();

	var page = {
		isFirst : 1,
		init: function () {
			questions.change = function (index, stuIndex) {
				stuIndex = stuIndex ||0;
				students.go(stuIndex);
			};
			students.onSelect = function ($elm) {
				var $qu = questions.getQuestion();
				var homeWorkType = $qu.attr('data-type');
				var workReceiveStuId= $elm.attr('data-workReceiveStuId');
				var questionId= $qu.attr('data-qid');
				var qType = $qu.attr('data-qType');
				var askUser = $elm.attr('data-userName');
				if(homeWorkType == 'que'){
					if(page.isFirst !=1){
						testHomeWork(workReceiveStuId,questionId,askUser);
					}
				}else if(homeWorkType == 'text'){
					textHomeWork(workReceiveStuId,askUser);
				}else if(homeWorkType == 'file'){
					annexHomeWork(workReceiveStuId,askUser);
				}
				page.isFirst = 0;
			};
			students.init();
			questions.init();
			questions.go(0,0);
		}
	};
	page.init();
	
	var type = '';
	var workRecStuQueAnswerId = '';
	var workReceiveStuId = '';
	function testHomeWork(workReceiveStuId,questionId,askUser){
		$.post("${root}/homework/getQuestionAnswer.do",  {
			homeworkId: '${homework.workHomeworkId}',
			workReceiveStuId:workReceiveStuId,
			classId:'${param.classId}',
			workQueId: questionId
		}, function (data) {
			if(data){
				type = 'que';
				setData(data,askUser);
			}
		}, 'json');
	};
	
	function textHomeWork(workReceiveStuId,askUser){
		$.post("${root}/homework/getTextHomeWorkAnswer.do",  {
			homeworkId: '${homework.workHomeworkId}',
			workReceiveStuId:workReceiveStuId,
			classId:'${param.classId}'
		}, function (data) {
			if(data){
				type = 'text';
				setData(data,askUser);
			}
		}, 'json');
	};
	
	function annexHomeWork(workReceiveStuId,askUser){
		$.post("${root}/homework/getFileHomeWorkAnswer.do",  {
			homeworkId: '${homework.workHomeworkId}',
			workReceiveStuId:workReceiveStuId,
			classId:'${param.classId}'
		}, function (data) {
			if(data){
				type = 'file';
				setData(data,askUser);
			}
		}, 'json');
	};
	
	function setData(data,askUser){
		
		workReceiveStuId = data.workReceiveStuId;
		workRecStuQueAnswerId = data.workRecStuQueAnswerId;
		
		var html='答题人：'+askUser+'<span class="fr lastQues"> 本道题还有<i>'+data.unReadNum+'</i>人未批阅  </span>';
		$("#answerInfo").html(html);
		var answerHtml = '';
		if(type == 'que'){
			answerHtml = data.answer;
			var answerVideo = data.answerVideo;
			var $qu = questions.getQuestion();
			var questionId= $qu.attr('data-qid');
			if(answerVideo !=null){
				
				answerHtml+='<p class="uploadMediaWrap mt20 mb20">';
				answerHtml+='<a href="javaScript:;" data-answerVideo="'+answerVideo+'" data-questionId="'+questionId+'" class="btn btnUpLoad uploadBox answerVideo">点击播放音视频</a>';
				answerHtml+='<span id="showAnswerVideo'+questionId+'"></span>';
				answerHtml+='</p>';
			}
			
		}else if(type == 'text'){
			answerHtml = data.answer;
		}else if(type == 'file'){
			var docs = data.docs;
			if(docs && docs.length){
				answerHtml += '<div class="ml50  homeWorksList"><ul class="commonUL">';
				for(var i = 0;i<docs.length;i++){
					var doc = docs[i];
					var fileSize = (doc.docSize/1024/1024>=1)?((doc.docSize/1024/1024).toFixed(2)+'M'):((doc.docSize/1024).toFixed(2)+'K');
					answerHtml += '<li><span class="mr20 lightBlue">'+doc.docName+'</span>大小：<span class="mr40">'+fileSize+'</span><a class="red" href="${root }/images/'+doc.docPath+'">下载</a></li>';
				}
				answerHtml += '</ul></div>';
			}
		}
		
		$("#stuAnswerInfo").html(answerHtml);
		$("#workComment").html("");
		if(data.readFlag == 'Y'){
			$("#submitBtn").hide();
			$("#workCommentEdit").hide();
			$("#workComment").show();
			if(!data.comment){
				$("#workComment").html("没有老师评论");
			}else{
				$("#workComment").html(data.comment);
			}
		}else{
			$("#submitBtn").show();
			$("#workCommentEdit").show();
			$("#workComment").hide();
			editor.ready(function() {
			     editor.setContent('');
			});
		}
	}

	$("#stuAnswerInfo").on("click",".answerVideo",function(){
		var answerVideo = $(this).attr("data-answerVideo");
		var questionId = $(this).attr("data-questionId");
		var type="answerVideo"
		showContentVideo(answerVideo,questionId,type);
	});
	
	$("#submitBtn").on('click', function () {
		var teacherComment=editor.getContent();
		if(!teacherComment){
			Win.alert("文本批语不能为空！"); 
			return false;
		}else if(editor.getContentTxt().length>500){
			Win.alert("文本批语不能大于500字！");
			return false;
		}
		
		$.post("${root}/homework/readByQuestion.do",  {
			homeworkId: '${homework.workHomeworkId}',
			classId: '${param.classId}',
			workRecStuQueAnswerId: workRecStuQueAnswerId,
			teacherComment:teacherComment,
			workReceiveStuId:workReceiveStuId,
			type:type
		}, function (data) {
			if(data && data.result){
				$("#unReadNum").html(data.message);
				students.next();
				students.go(students.getSelIndex() + 1, true);
			}
		}, 'json');
	});
	
})();


</script>
</html>