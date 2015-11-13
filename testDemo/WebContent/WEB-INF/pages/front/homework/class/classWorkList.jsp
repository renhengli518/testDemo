<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>
</head>
<body class="mainIndex">

     <%@include file="../../../common/topHeader.jsp" %>
	 <%@include file="../../../common/nav.jsp" %>
<div class="container clearfix w1200 bkgNone marginauto">
<%@include file="../../../common/teaWorkList.jsp" %>
</div> 
</body>
<script type="text/javascript">

$(document).ready(function(){
	var splitPage ;
	listHomeWork() ;
}) ;
$('#subjectLi,#status').on('click', 'a', function () {
	$(this).addClass('selected').siblings().removeClass('selected');
}) ;
$('#subjectLi,#status').on('click', 'a', function () {
	listHomeWork() ;
}) ;

function listHomeWork() { 
	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var subjectId = $("#subjectLi a.selected").attr("id") ;
	var status=$("#status a.selected").attr("id");
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
			url:"/OnlineTest/classWork/getClassWorkList.do?r="+(+new Date),
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
			var status='';
			var assignTime='';
			var finishTime='';
			var subjectName='';
			if(homeWork.status=='PROGRESS'){
				status='待批阅';
			}else if(homeWork.status=='END'){
				status='已批阅';
			}else if(homeWork.status=='INIT'){
				status='待布置';
			}
			if(homeWork.assignTime==null){
				assignTime='';
			}else{
				assignTime=homeWork.assignTime;
			}
			if(homeWork.finishTime==null){
				finishTime='';
			}else{
				finishTime=homeWork.finishTime;
			}
			if(homeWork.subjectName==null){
				subjectName='';
			}else{
				subjectName=homeWork.subjectName;
			}
			var homeWorkId=homeWork.homeWorkId;
			console.log(homeWorkId);

				if(homeWork.readOverType != 'TEACHER'){
					html+=' <div class="borderBox pl40 testWithLabel withLabel1 verticalMiddle">';
				}else{
					html+=' <div class="borderBox pl40 testWithLabel verticalMiddle">';
				}
				html+='<h4 class="examTitle"><a href="${root}/classWork/toHomeWorkView/'+homeWorkId+'.do"' +'target="_blank" >'+homeWork.workTitle+'</a></h4>';
				html+=' <div class="examDesc">';
				html+= '布置时间：<span>'+assignTime+'</span>';
				html+='学科：<span>'+subjectName+'</span><br/>';
				html+='完成时间：<span>'+finishTime+'</span>';
				html+='提交人数：<span><span class="red">'+homeWork.subWorkStuCount+'</span>/'+homeWork.stuCount+'</span>';
				html+='状态：<span>'+status+'</span>';
				html+='</div>';
		    if(homeWork.status!='INIT'){
				
				html+='<a href="javascript:;" class="examState threeItem">统计</a>';
				
			}
				html+='</div>';
				$("#pageBody").html(html);
			}
	}else{
	
		html='<div class="borderBox pl40 testWithLabel center">'; 
		html+="抱歉，没有搜索到您想要的信息！</div>";
		$("#pageBody").html(html);
	}
	}	
</script>

</html>