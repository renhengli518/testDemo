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
<style type="text/css">
 .btnGray{
 	background-color: #999 !important;
 }
</style>
</head>
<body class="mainIndex">
    <%@include file="../../../common/topHeader.jsp" %>
	<%@include file="../../../common/examNav.jsp" %>
	<div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
              <span class="fr examOrder">
                   <button class="btn prev">上一题</button>
                   <button class="btn next">下一题</button>
                 </span>
                <div class="gn-overflowHidden">
                  <a href="${root}/teacherTest/classExamList.html">测试任务</a><span class="nextlevel">></span><a href="${root}/teacherTest/readOverOnLineTest/${examTaskId}.html">批阅</a><span class="nextlevel">></span><span class="currentLevel">按题批阅 </span>
                </div>
                
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite borderBoxP40 paddingBottom0">
			      <h3 class="titleOfCheckAnswer titleOfCheckAnswer1 clearfix">
                                               题目
                   <span class="fr lastQues fz14">本套试卷还有<i class="fontNormal"><font id="unReadNum">${fn:length(questions)}</font>道题</i>未批阅</span>
                  </h3>
                  
			     <div class="picScroll">
			        <div class="tableWrap bkgWhite">
			        	<c:set var="questionIndex" value="-1"></c:set>
			        	<c:forEach items="${questions}" var="que" varStatus="status">
			        		<div class="quesContent quesInfo quesContentSpecial ml30 mt30" id="question${status.index}" data-qType="${que.type}" data-qid="${que.examQuestionId}" data-type="que" <c:if test="${status.index != 0}">style="display:none;"</c:if> >
			        			<p class="quesTitle">
			        				<p class="fl fz20">${status.count }、</p>${que.content }
				        			 <c:if test="${!empty que.contentVideo}">
										<p class="uploadMediaWrap mt20">
											<button  class="btn playVideo" data-id="${que.contentVideo}"  onclick="playVideo(this);">点击播放音视频</button>
											<span id="watch_play0s"></span>
										</p>
									 </c:if>
			        			</p>
			        			<ul class="commonUL labelWidth70 mt10">
			        				<li>
				                 		<label for="" class="text fontBold ">正确答案：</label>
					       				<span class="cont"><b>${que.resolve }</b></span>
				                 	</li>
			        			</ul>
				       		</div>
				       		<c:set var="questionIndex" value="${status.index }"></c:set>
				       	</c:forEach>
			          	<div id="banner" class="scrollWrap clearfix">
			          	    <a href="javascript:;" id="prevbt" class="prevBtn"><</a>
			          	    <a href="javascript:;" id="nextbt" class="nextBtn">></a>
			          	    <div id="scrollPic" class="clearfix">
			          	    <ul class="scrollUL">
			          	    	<c:forEach items="${students}" var="student">
			          	    	  <li data-userName="${student.realName }" data-testReceiveStuId="${student.baseUserId }">
				          	         <a href="javascript:;"><img src="${root }/images/${student.headPic}"></a>
				          	         <span class="stuName">${student.realName }</span>
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
			                 	<div style="margin-top:20px;">
			                 		<span style="color:red;">*</span>得分：
			                 		<span>
			                 			<input id="score" type="text" onkeyup="validateNum(this)" name="score"  required="required"></input>分 
			                 		</span>
			                 		<span style="margin-left:20px;">该道题满分为<span id="question_score"></span>分，老师打分不能大于该分值。</span>
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
var questionList ="${questions}";
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
			getIndex: function () {
				return index;
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
				if(len==1){//增加如果只有一个学生就把切换按钮置灰
					$prev.addClass('btnGray');
					$next.addClass('btnGray');
				}
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
				$students.removeClass('active');
				$($students[index]).addClass('active');
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
				$students.on('click', function () {
					students.select($(this).index());
				});
				$prev.on('click', function () {
					if (!$(this).hasClass('btnGray')) {
						students.prev();
						students.select(index);
					}
				});
				$next.on('click', function () {
					if (!$(this).hasClass('btnGray')) {
						students.next();
						students.select(index);
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
				students.go(students.getIndex());
			};
			students.onSelect = function ($elm) {
				var $qu = questions.getQuestion();
				var homeWorkType = $qu.attr('data-type');
				var testReceiveStuId= $elm.attr('data-testReceiveStuId');
				var questionId= $qu.attr('data-qid');
				var qType = $qu.attr('data-qType');
				var askUser = $elm.attr('data-userName');
				if(homeWorkType == 'que'){
					if(page.isFirst !=1){
						testHomeWork(testReceiveStuId,questionId,askUser);
					}
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
	function testHomeWork(testReceiveStuId,questionId,askUser){
		$.post("${root}/teacherTest/getQuestionAnswer.do",  {
			examTaskId: '${examTaskId}',
			studentId:testReceiveStuId,
			classId:'${param.classId}',
			questionId: questionId
		}, function (data) {
			if(data){
				type = 'que';
				setData(data,askUser);
			}
		}, 'json');
	};
	
	
	function setData(data,askUser){
		var score = '';
		//情非得已才这么写
	 	<c:forEach items="${questions}" var="que" varStatus="status">
			if(Number("${status.index}")==questions.getIndex()){
				score = "${que.score}";
			}
		</c:forEach> 
		$("#question_score").text(score);
		//workReceiveStuId = data.workReceiveStuId;
		//workRecStuQueAnswerId = data.workRecStuQueAnswerId;
		var html = '<p class="submiter">学生答案：'+data.answer;
		if(data.answerVideo && data.answerVideo != ""){
			html+='<span class="uploadMediaWrap mt20 ml20">'
				+'<button href="javaScript:;" class="btn playVideo" data-id="'+data.answerVideo+'"'
				+'	onclick="playVideo(this);">点击播放音视频</button> <span'
				+'	id="watch_play0s"></span>'
				+'</span>';
		}
				
            html+='<span class="fr lastQues">'
        	+'本道题还有<i>'+data.noReviewCount+'</i>人未批阅  '
			+'</span>'
			+'</p><input type="hidden" id ="examQuestionResultId" value="'+data.examQuestionResultId+'" />';
		$("#answerInfo").html(html);
		
		$("#workComment").html("");
		if(data.result && data.result!=null){
			$("#submitBtn").hide();
			$("#workCommentEdit").hide();
			$("#workComment").show();
			if(!data.teacherComment){
				$("#workComment").html("没有老师评论");
			}else{
				$("#workComment").html(data.teacherComment);
			}
			$("#score").val(data.score).attr("readOnly","readOnly");
		}else{
			$("#submitBtn").show();
			$("#workCommentEdit").show();
			$("#workComment").hide();
			editor.ready(function() {
			     editor.setContent('');
			});
			$("#score").val("").removeAttr("readOnly");
		}
	}
	
	//校验输入的分值
	/* $("#score").on("change",function(){
		var score = $("#score").val();
		if(score==""){
			Win.alert("得分不能为空！");
		}else if(isNaN(score)){
			Win.alert("得分必须是数字！");
		}else if(score<0){
			Win.alert("得分不能小于0！");
		}else if(score>Number($("#question_score").text())){
			Win.alert("得分不能超过该题分值！");
		}
	}); */
	
 

	$("#submitBtn").on('click', function () {
		var teacherComment=editor.getContent();
		/* if(!teacherComment){
			Win.alert("文本批语不能为空！");
			return false;
		}else */ 
		if(teacherComment && editor.getContentTxt().length>500){
			Win.alert("文本批语不能大于500字！");
			return false;
		}else if($("#score").val()==""){
			Win.alert("得分不能为空！");
			return false;
		}else if(isNaN($("#score").val())){
			Win.alert("得分必须是数字！");
			return false;
		}else if($("#score").val()<0){
			Win.alert("得分不能小于0！");
			return false;
		}else if($("#score").val()>Number($("#question_score").text())){
			Win.alert("得分不能超过该题分值！");
			return false;
		}
		var result = $("#score").val()==$("#question_score").text()?1:0;
		var studentId = $(".active").attr("data-testReceiveStuId");
		var $qu = questions.getQuestion();
		var questionId= $qu.attr('data-qid');
		$.post("${root}/teacherTest/saveReadByQuestionComment.do",  {
				examQuestionResultId : $("#examQuestionResultId").val(),
				result : result,
				teacherComment : teacherComment,
				score : $("#score").val(),
				examTaskId : '${examTaskId}',
				studentId : studentId,
				classId : '${param.classId}',
				classLevelId : '${param.classLevelId}',
				questionId : questionId
			}, function(data) {
				if (data && data.result) {
					var array = data.message.split(",");
					var questionCount = array[0];
					var noReviewCount = array[1];
					$("#unReadNum").html(questionCount);
					$("#answerInfo").find(".lastQues li").html(noReviewCount);
					$('#nextbt').click();
					var $students = $('#banner li');
					var len = $students.length;
					if(students.getIndex() == len-1 ){
						students.select(students.getIndex());
					}
					//students.next();
					//students.go(students.getIndex() + 1, true);
				}
			}, 'json');
		});

	})();
	

	function validateNum(e) {
		e.value = e.value.replace(/\D/g, '');
		var queValue = $(e).val();
		var qScore = parseFloat($("#question_score").text());
		var score = parseFloat(queValue);
		if (score > qScore) {
			Win.alert("输入的分值超过了本题分值!");
			$(e).val("");
			return false;
		}
	}
	
    
  	function playVideo(obj){//点击播放视频
  		var videoPath = $(obj).attr("data-id");
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

 		if(postf.indexOf("mp3")>0){
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
</html>