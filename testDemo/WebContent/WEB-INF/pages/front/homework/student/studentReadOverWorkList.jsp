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
</head>
<body class="mainIndex">
<%@include file="../../../common/topHeader.jsp"%>
	<%@include file="../../../common/workNav.jsp"%>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
			  <div class="borderBox gn-bgWhite pd20">
				 <ul class="commonUL gn-searchCondition">
				   <li>
						<label class="text gn-labelText"><b>布置时间：</b></label>
						<span class="cont gn-sortBox clearfix gn-containInput">
							<input type="text" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'});" name="startTime" class="Wdate mr10" id="startTime">
							至
							<input type="text" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startTime\')}'});" name="endTime" class="Wdate ml10" id="endTime">
					</span>
					</li>
				<li id="subjectLi">
					<label class="text gn-labelText"><b>学科：</b></label>
						<span class="cont gn-sortBox clearfix">
							<a href="javascript:;" class="selected gn-sortAll fl" id="">全部</a>
								<c:forEach items="${subjects}" var="sub">
									<a class="ml10" href="javascript:;" id="${sub.subjectId}">${sub.subjectName}</a>
								</c:forEach>
						</span>
				</li>
				<li id="status">
					<label class="text gn-labelText"><b>状态：</b></label>
					<span class="cont gn-sortBox clearfix">
					<a href="javascript:;" class="selected gn-sortAll fl" id="">全部</a>
					<a href="javascript:;" id="END">待批阅</a>
					<a href="javascript:;" id="CHECKED">已批阅</a>
					</span>
				</li>
				<li>
						<label class="text gn-labelText"><b>试卷名称：</b></label>
						<span class="cont gn-sortBox clearfix gn-containInput">
							<input type="text" name="workName" id="workName">
							<button class="btn btnColorSearch ml20" onclick="listHomeWork();">搜索</button>
						</span>
					</li>
				</ul>
			</div>
		</div>
	<div class="gn-main mt30 clearfix pl10 pr10" id="pageBody">
	</div>
</div>
	<div class="pageNavi" id="pager"></div>
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
	var userId = "${userId}";
	var params = {
			assignStartTime     :   startTime,
			assignEndTime       :   endTime,
			subjectId			:	subjectId ,
			homeWorkName		:	homeWorkName ,
			status				:	status,
			userId				:	userId
	} ;
	
	var config = {
			node:$id("pager"),
			url:"${root}/studentWork/getReadOverWorkList.do?r="+(+new Date),
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
			var status='';
			var assignTime='';
			var finishTime='';
			var subjectName='';
			var summary='';
			var finishTime='';
			var submitTime='';
			
				if(homeWork.countAll==homeWork.countReadOver){
					status='已批阅';
				}
				if(homeWork.countAll>homeWork.countReadOver){
					status='待批阅';
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
			console.log(homeWorkId);

				html+=' <div class="borderBox pl40 testWithLabel verticalMiddle">';
				html+='<h4 class="examTitle">'+homeWork.workTitle+'</h4>';
				html+=' <div class="examDesc">';
				html+= '布置时间：<span>'+assignTime+'</span>';
				html+='学科：<span>'+subjectName+'</span><br/>';
				
				html+='完成时间：<span>'+finishTime+'</span>';
				html+='提交人数：<span><span class="red">'+homeWork.countSubmit+'</span>/'+homeWork.countAll+'</span>';
				html+='状态：<span>'+status+'</span>';
				
				html+='</div>';
				if(homeWork.countAll==homeWork.countReadOver){
					html+='<a href="${root}/studentWork/toQueryReadOverView/'+homeWorkId+'/'+"${userId}"+'.do"'+'class="examState threeItem">'+'查看批阅</a>';
					
				}
				if(homeWork.countAll>homeWork.countReadOver){
					html+='<a href="${root}/studentWork/toReadOverView/'+homeWorkId+'/'+"${userId}"+'.do"'+'class="examState threeItem">'+'批阅</a>';
					html+='<a href="${root}/studentWork/toQueryReadOverView/'+homeWorkId+'/'+"${userId}"+'.do"'+'class="examState threeItem">'+'查看批阅</a>';
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