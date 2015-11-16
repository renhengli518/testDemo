<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp"%>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<head>
</head>
<body class="mainIndex">
	<%@include file="../../../common/topHeader.jsp"%>
    <%@include file="../../../common/examNav.jsp"%>
	<div class="container w1200 marginauto posRelative bkgNone clearfix">
		<div class="content">
			<div class="content-in">

				<div class="borderBox bkgWhite mb10 criteria pd20">
					<ul class="commonUL gn-searchCondition gn-blueSort">
						<li id="areaNameLi">
							<label class="text gn-labelText gn-blueBracket"><b>地区：</b></label> 
							<span class="cont gn-sortBox clearfix">
								<a id="0"  href="javascript:;" class="selected gn-sortAll fl">全部</a> 
								<span class="gn-sortKinds gn-overflowHidden">
								</span>
							</span>
						</li>
						<li id="yearLi"><label class="text gn-labelText gn-blueBracket"><b>年份：</b></label> <span class="cont gn-sortBox clearfix">
								<a id="0"  href="javascript:;" class="selected gn-sortAll fl">全部</a> <span
								class="gn-sortKinds gn-overflowHidden">
								<c:forEach items="${years}" var="year">
									<a href="javascript:;">${year}</a>
								</c:forEach>
								<a href="javascript:;" id ="other">其他</a>
							</span>
						</span></li>
						<li id="examTypeLi"><label class="text gn-labelText gn-blueBracket"><b>试卷类型：</b></label> <span
							class="cont gn-sortBox clearfix"> <a id="0"  href="javascript:;" class="selected gn-sortAll fl">全部</a>
								<span class="gn-sortKinds gn-overflowHidden"> 
								<c:forEach items="${examTypes}" var="examType">
									<a href="javascript:;" id="${examType.id}">${examType.name}</a>
								</c:forEach>
							</span>
						</span></li>
						<li id="semesterLi"><label class="text gn-labelText gn-coffeeBracket"><b>学段</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;"  id="0">全部</a>
								<span class="gn-sortKinds gn-overflowHidden"> <c:forEach items="${semesters}" var="semester">
										<a href="javascript:;" id="${semester.baseSemesterId }">${semester.semesterName }</a>
									</c:forEach>
							</span>
						</span></li>
						<li id="classlevelLi"><label class="text gn-labelText gn-coffeeBracket"><b>年级</b></label> <span
							class="cont gn-sortBox clearfix"> <a class="selected gn-sortAll fl" href="javascript:;"  id="0">全部</a>
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
					</ul>
				</div>

				<div class="borderBox bkgWhite pd20">
					<p class="sortKinds">
						<span class="blackBracket">排序：</span> <span id="createTimeSort" class="withSortArrow up">最新</span> <span id="useCountSort" class="withSortArrow up">使用次数</span>
					</p>
				</div>
				 <div class="gn-main mt30 clearfix pl10 pr10" id="pageBody">
		         
		 		 </div>
				<div id="pageNavi"></div>

			</div>
		</div>
	</div>
	<script>
	var createTimeSort = '';//时间排序
	var useCountSort = '';//使用次数排序
	
    $(document).ready(function(){
    	//初始化页面，设置地区
    	var area = ['河北省','山西省','辽宁省','吉林省','黑龙江省','江苏省','浙江省','安徽省','福建省','江西','山东省','河南省','湖北省','湖南省',
    	            '广东省','海南省','四川省','贵州省','云南省','陕西省','甘肃省','青海省','台湾省','内蒙古','广西','西藏','宁夏','新疆'];
    	var html = "";
    	area.forEach(function(value,index){
    		html+='<a href="javascript:;">'+value+'</a>';
    	});
    	$("#areaNameLi").find(".gn-sortKinds").append(html);
    	
    	//默认查询收藏题库列表
		searchQuestions();
	});		 
	
	
	// 查询真题试卷列表
	function searchQuestions() {
		var url  = '${root}/teacherTest/searchRealExamList.do';
		var baseSemesterId = $("#semesterLi").find(".selected").prop("id");
		var baseClasslevelId = $("#classlevelLi").find(".selected").prop("id");
		var baseSubjectId = $("#subjectLi").find(".selected").prop("id");
		var examTypeId = $("#examTypeLi").find(".selected").prop("id");
		var year = $("#yearLi").find(".selected").text();
		var areaName = $("#areaNameLi").find(".selected").text();
		var className1 = $("#createTimeSort").prop("class");
		var className2 = $("#useCountSort").prop("class");
    	if(className1.indexOf("up")!=-1){
    		createTimeSort = 'up';
    	}else {
    		createTimeSort = 'down';
    	}
    	if(className2.indexOf("up")!=-1){
    		useCountSort = 'up';
    	}else {
    		useCountSort = 'down';
    	}
		var param  = {
				baseSemesterId:baseSemesterId,
				baseClasslevelId:baseClasslevelId,
				baseSubjectId:baseSubjectId,
				examTypeId : examTypeId,
				year : year,
				areaName : areaName,
				createTimeSort : createTimeSort,
				useCountSort : useCountSort
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
	
	
	//编辑真题试卷
	function toEditRealExam(obj){
		var examId = $(obj).prop("id");
		//window.location.href="${root}/questionLib/toeditquestion.html?questionId="+queQuestionId;
	}
	
	//删除真题试卷
	function toDeleteRealExam(obj){
		var examId = $(obj).prop("id");
		Win.confirm("确认要删除该真题试卷吗？", function(){
	 		if(examId==null || examId==""){
				Win.alert("删除真题试卷失败！");
			} else {
				$.get("${root}/teacherTest/deleteRealExamById.do",{"examId":examId},function(data){
					if(data&&data.result){
						Win.alert("删除真题试卷成功！");
						searchQuestions();
					}else{
						Win.alert(data.message);
					}
				});
			} 
		}, function(){
				
		});
	}
	
	function formQuestionList(data,total){
		//去掉正在加载层      		
      	$("body").hideLoading();
		
		if(total || total != 0){
    		if(data){
    			var html = '';
    			var taskHtml = '';
    			var task;
    			for (var i = 0; i < data.length; i++){
    				task = data[i];
     				taskHtml += '<div class="borderBox pl40 testWithLabel" id='+i+'>';
    				taskHtml += '<h4 class="examTitle"><a href="${root}/teacherTest/viewExam/real/'+task.examId+'.html" target="_blank">'+task.title+'</a></h4>';
    				taskHtml += '<div class="frBtnWrap verticalMiddle clearfix">';
    				taskHtml += '<div class="examDesc">'
    					   +'学段：<span>'+task.semesterName+'</span>'
    					   +'年级：<span>'+task.classlevelName+'</span>'
    				       +'学科：<span>'+task.subjectName+'</span>'
                           +'试卷类型：<span>'+task.examTypeName+'</span><br/>'
                           +'答题时长：<span>'+task.answerTime+'分钟</span>'
                           +'试卷总分：<span>'+task.score+'分</span>'
                           +'题量：<span class="fontNormal">'+task.questionCount+'题</span>'
                           +'使用次数：<span class="fontNormal">'+task.useCount+'次</span>'
                           +'</div>';
                       taskHtml += '<a href="${root}/teacherTest/editExam/real/'+task.examId+'.do" class="examState twoItem" id='+task.status+' eid='+task.examId+'>加入我的试卷</a>';
                    taskHtml += '</div></div>';  
    			}
                  
    			html += taskHtml;
    			$("#pageBody").html(html);
    		}else{
    			$("#pageBody").html('<div class="borderBox pl40 testWithLabel align="center">抱歉，未查询到相关记录!</div>');
    		}
    	}else{
    		$("#pageBody").html('<div class="borderBox pl40 testWithLabel align="center">抱歉，未查询到相关记录!</div>');
    	}
	}

	function uploadQuestion(){
		 Win.open({id:'uploadRealExam',width:500,height:520,title:"导入真题试卷",url:""});
	 }
	
    $('.criteria').on('click', 'a', function () {
		$(this).parents('li').find('a').removeClass('selected');
		$(this).addClass('selected');
		searchQuestions();
	});
    
    $(".withSortArrow").on("click",function(){
    	$(this).toggleClass("up");
    	searchQuestions();
		
    });
    
  //显示正在加载层
    $(document).ready(function(){
    	$("body").showLoading();
    	$(".wrap").css({
    		"height" : ($(window).height() - 70) + 'px',
    		"width" : $(window).width()
    	});
    });
    </script>
</body>
</html>