<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css" />
</head>
<body class="mainIndex">
<%@include file="../../../common/topHeader.jsp"%>
<%@include file="../../../common/workNav.jsp"%>
	<div class="container clearfix w1200 bkgNone marginauto">
		<div class="content">
			<div class="content-in">
				<div class="breadCrumb">
					<a href="${root}/studentWork/myReadOver.do">作业</a><span class="nextlevel">></span><span class="currentLevel">作业批阅</span>
				</div>
				<div class="borderBox gn-bgWhite pd20">
					<table class="newTableStyle" >
						<thead>
							<tr>
								<th width="20%">姓名</th>
								<th width="30%">
									<div class="verticalMiddle">
										提交时间
										<span class="arrowWrap">
											<b class="sortArrow"></b>
										</span>
									</div>
								</th>
								<th width="10%">按时提交</th>
								<th width="25%">答题数目</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody id="pageBody">
						
						</tbody>
					</table>
				<div class="pageNavi" id="pager"></div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	var splitPage ;
	var type='desc';
	listHomeWork(type) ;
}) ;
$("b").click( function () {
	var classType = $(this).attr("class").indexOf('up');
	var type='';
	if(classType != (-1)){//原来排序为升序，现在要进行降序
		$(this).removeClass("up");
	
		type='desc';
	
	}else{
		$(this).addClass("up"); //原来为降序，现在要进行升序
		type='asc';
	}
	var splitPage ;
		listHomeWork(type) ;
});
function listHomeWork (type){
var workId="${workId}";
var userId="${userId}";
var params = { 
		userId				:	userId,
		workId				:	workId,
		type				:	type
} ;

var config = {
		node:$id("pager"),
		url:"${root}/studentWork/queryReadOverList.do?r="+(+new Date),
		count :20,
		type :"post",
		callback : myReadOverListResult,
		data:params
	};
	splitPage = new SplitPage(config); 
}
function myReadOverListResult(data,total){
		if(total && data){
			var html ='';
			var ReceiveStu;
			var submitTime='';
			var finishTime='';
			for(var i = 0,j = data.length;i<j;i++){
				ReceiveStu = data[i];
				console.log(ReceiveStu);
				if(ReceiveStu.submitTime==null){
					submitTime='';
				}else{
					submitTime=ReceiveStu.submitTime;
				}
				if(ReceiveStu.finishTime==null){
					finishTime='';
				}else{
					finishTime=ReceiveStu.finishTime;
				}
				
					html+='<tr>';
					html+='<td>'+ReceiveStu.realName+'</td>';
					html+= '<td>'+submitTime+'</td>';
					if(ReceiveStu.submitTime>ReceiveStu.finishTime){
						html+='<td>'+'否'+'</td>';
					}else{
						html+='<td>'+'是'+'</td>';
					}
					html+='<td>'+ReceiveStu.answerCount+'</td>';
					html+='<td><a href="${root}/studentWork/queryReadOverWorkView/'+"${workId}"+'/'+ReceiveStu.baseUserId+'.do"'+'class="newHandle"'+'>查看批阅</a></td>';
					$("#pageBody").html(html);
		}
		
		}else{
			html = '<tr><td colspan="5" class="center">';
			html+="抱歉，暂时没有批阅数据！</td></tr>";
			$("#pageBody").html(html);
		}	

}
</script>
</html>




