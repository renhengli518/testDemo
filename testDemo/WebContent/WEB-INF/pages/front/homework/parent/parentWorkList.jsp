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
	<%@include file="../../../common/stuWorkList.jsp" %>

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
			url:"${root}/parentWork/getParentWorkList.do?r="+(+new Date),
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
			var summary='';
			var finishTime='';
			var submitTime='';
			if(homeWork.status=='END'){
				status='待批阅';
			}else if(homeWork.status=='CHECKED'){
				status='已完成';
			}else if(homeWork.status=='PROGRESS'){
				status='未完成';
			}
			if(homeWork.assignTime==null){
				assignTime='';
			}else{
				assignTime=homeWork.assignTime;
			}
			if(homeWork.finishTime==null){
				finishTime='';
			}else{
				homeWork.finishTime;
			}
			if(homeWork.subjectName==null){
				subjectName='';
			}else{
				subjectName=homeWork.subjectName;
			}
			if(homeWork.summary==null){
				summary=''
			}else{
				summary=homeWork.summary;
			}
			if(homeWork.finishTime==null){
				finishTime='';
			}else{
				finishTime=homeWork.finishTime;
			}
			if(homeWork.submitTime==null){
				submitTime='';
			}else{
				submitTime=homeWork.submitTime;
			}
			var homeWorkId=homeWork.homeWorkId;
				if(homeWork.readOverType == 'TEACHER'){
					html+=' <div class="borderBox pl40 testWithLabel verticalMiddle">';
				}else{
					html+=' <div class="borderBox pl40 testWithLabel withLabel1">';
				}
				html+='<h4 class="examTitle"><a href="${root}/parentWork/parentHomeWorkView/'+homeWorkId+'/'+"${userId}"+'/'+homeWork.status+'.do"' +'target="_blank" >'+homeWork.workTitle+'</a></h4>';
				html+=' <div class="examDesc">';
				html+= '布置时间：<span>'+assignTime+'</span>';
				html+='学科：<span>'+subjectName+'</span>';
				html+='总评：<span>'+summary+'</span><br/>';
				html+='完成时间：<span>'+finishTime+'</span>';
				html+='答题数：<span><span class="red">'+homeWork.answerCount+'</span>/'+homeWork.queCount+'</span>';
				html+='状态：<span>'+status+'</span>';
				html+='提交时间：<span>'+submitTime+'</span>';
				html+='</div>';
			if(homeWork.status=='CHECKED'){
				html+='<a href="${root}/parentWork/toParentReadOverWorkView/'+homeWorkId+'/'+"${userId}"+'.do"'+'class="examState threeItem">'+'查看批阅</a>';
			}
				html+='</div>';
				$("#pageBody").html(html);
			}
	}else{
		html=' <div class="borderBox pl40 testWithLabel center">'; 
		html+="抱歉，没有搜索到您想要的信息！</div>";
		$("#pageBody").html(html);
	}
	}	
	
</script>
</html>