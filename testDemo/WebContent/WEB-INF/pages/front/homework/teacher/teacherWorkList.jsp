<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>
</head>
<body class="mainIndex">

<%@include file="../../../common/topHeader.jsp" %>
<%@include file="../../../common/workNav.jsp" %>
<div class="container clearfix w1200 bkgNone marginauto">
	<button class="fr btn btnOrange topBtn publishWork">布置作业</button>
<%@include file="../../../common/teaWorkList.jsp" %>
</div> 
</body>
<script type="text/javascript">
$(document).ready(function(){
	var splitPage ;
	listHomeWork() ;
}) ;
$(".publishWork").click(function(){
	location.href="${root}/homework/createHomeworkPre.html";
});

$('#classlevelLi,#subjectLi,#status').on('click', 'a', function () {
	$(this).addClass('selected').siblings().removeClass('selected');
}) ;
$('#classlevelLi,#subjectLi,#status').on('click', 'a', function () {
	listHomeWork() ;
}) ;

function listHomeWork() { 
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var subjectId = $("#subjectLi a.selected").attr("id") ;
	var status = $("#status a.selected").attr("id");
	var homeWorkName = $("#workName").val();
	var userId="${userId}";
	var params = {
			assignStartTime     :   startTime,
			assignEndTime       :   endTime,
			subjectId			:	subjectId ,
			status              :   status,
			homeWorkName		:	homeWorkName, 
			userId				:	userId
	} ;
	
	var config = {
			node:$id("pager"),
			url:"${root}/teacherWork/getTeacherHomeWorkList.do?r="+(+new Date),
			count :20,
			type :"post",
			callback : homeWorkListResult,
			data:params
		};
		splitPage = new SplitPage(config); 
}

//把返回的数据进行渲染
function homeWorkListResult(data,total){
	
	if(total && data){
		var html ='';
		var homeWork;
		for(var i = 0,j = data.length;i<j;i++){
			homeWork = data[i];
			console.log(homeWork);
			var status = '';
			var assignTime = '';
			var finishTime = '';
			var subjectName = '';
			if(homeWork.status=='PROGRESS'){
				status = '待批阅';
			}else if(homeWork.status=='END'){
				status = '已批阅';
			}else if(homeWork.status=='INIT'){
				status = '待布置';
			}
			if(homeWork.assignTime==null){
				assignTime = '';
			}else{
				assignTime=homeWork.assignTime;
			}
			if(homeWork.finishTime==null){
				finishTime = '';
			}else{
				finishTime=homeWork.finishTime;
			}
			if(homeWork.subjectName==null){
				subjectName = '';
			}else{
				subjectName = homeWork.subjectName;
			}
			var homeWorkId = homeWork.homeWorkId;
			
				if(homeWork.readOverType != 'TEACHER'){
					html+=' <div homeworkId="'+homeWorkId+'" class="borderBox pl40 testWithLabel verticalMiddle withLabel1">';
				}else{
					html+=' <div homeworkId="'+homeWorkId+'" class="borderBox pl40 testWithLabel verticalMiddle">';
				}
				html+='<h4 class="examTitle"><a href="${root}/teacherWork/toHomeWorkView/'+homeWorkId+'.do"' +'target="_blank" >'+homeWork.workTitle+'</a></h4>';
				html+=' <div class="examDesc">';
				html+= '布置时间：<span>'+assignTime+'</span>';
				html+='学科：<span>'+subjectName+'</span><br/>';
				html+='完成时间：<span>'+finishTime+'</span>';
				html+='提交人数：<span><span class="red">'+homeWork.subWorkStuCount+'</span>/'+homeWork.stuCount+'</span>';
				html+='状态：<span class="">'+status+'</span>';
				html+='</div>';
				if(homeWork.readOverType == 'TEACHER'){
					if(homeWork.status=='PROGRESS'){
						html+='<a href="javascript:;" class="marking threeItem readHomework">批 阅</a>';
						html+='<a href="javascript:;" class="examState threeItem countWork">统计</a>';
					}else if(homeWork.status=='END'){
						html+='<a href="javascript:;" class="examState threeItem countWork">统计</a>';
						
					}else if(homeWork.status=='INIT'){
						
						html+='<a  href="javascript:void(0);" class="marking threeItem arrangeWork">布置</a>'; 
						html+='<a href="javascript:;" class="examState threeItem editHomework">编辑</a>';	
					}
				}else{
						if(homeWork.status=='INIT'){
						
						html+='<a href="javascript:void(0);" class="marking threeItem arrangeWork" >布置</a>'; 
						html+='<a href="javascript:;" class="examState threeItem editHomework">编辑</a>';	
					}else{
						html+='<a href="javascript:;" class="examState threeItem countWork">统计</a>';
					}
				}
				
				html+='<a href="javascript:void(0);" class="marking threeItem red deleteWork" >删除</a>'; 
				html+='</div>';
				$("#pageBody").html(html);
			}
	}else{		
		html = '<div class="borderBox pl40 testWithLabel center">'; 
		html+="抱歉，没有搜索到您想要的信息！</div>";
		$("#pageBody").html(html);
	}	
}	

	
	$("#pageBody").on("click",".arrangeWork",function(){
		var workId = $(this).parents(".borderBox").attr("homeworkId");
		Win.confirm({html : '<span class="">确认布置作业吗？</span>',mask:true}, function () {
			$.post('${root}/teacherWork/arrangeWork.do', {"workId" : workId}, function(result){
				if(result.result) {
					Win.alert("布置作业成功！");
					listHomeWork();
				} else {
					Win.alert(result.message);
				}
			}, "json");
		},true
		);
	});
	$("#pageBody").on("click",".deleteWork",function(){
		var workId = $(this).parents(".borderBox").attr("homeworkId");
		Win.confirm({html : '<span class="">确认要删除该作业吗？</span>',mask:true}, function () {
			$.post('${root}/teacherWork/deleteWork.do', {"workId" : workId}, function(result){
				if(result.result) {
					Win.alert("删除作业成功！");
					listHomeWork();
				} else {
					Win.alert(result.message);
				}
			}, "json");
		},true
		);
	});
	
	$("#pageBody").on("click",".editHomework",function(){
		var homeworkId = $(this).parents(".borderBox").attr("homeworkId");
		location.href = "${root}/homework/editHomeworkPre.html?homeworkId="+homeworkId;
	});
	
	$("#pageBody").on("click",".readHomework",function(){
		var homeworkId = $(this).parents(".borderBox").attr("homeworkId");
		location.href="${root}/homework/readOverHomework.html?homeworkId="+homeworkId;
	});
	
	$("#pageBody").on("click",".countWork",function(){
		var homeworkId = $(this).parents(".borderBox").attr("homeworkId");
		var objType="TEACHER";
		location.href="${root}/homework/workCount.html?workId="+homeworkId+"&objType="+objType;
		
	});

</script>
</html>