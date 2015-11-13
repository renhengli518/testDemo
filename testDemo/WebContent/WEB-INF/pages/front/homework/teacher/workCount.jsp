<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<script>
	$(function(){
		$(".chooseClass2").on("click","a",function(){
		var $this=$(this);
		$this.addClass("active").siblings().removeClass("active");
	});
var pid='submitTime';
var type='down';
answer(pid,type);
});

</script>
</head>
<body class="mainIndex">
	<%@include file="../../../common/topHeader.jsp" %>
	<%@include file="../../../common/workNav.jsp" %>
<div class="container clearfix w1200 bkgNone marginauto">
	<div class="content">
		<div class="content-in">
			<div class="breadCrumb">
				<a href="${root}/teacherWork/toTeacherWork.do">作业 </a><span class="nextlevel">></span><span class="currentLevel">作业统计 </span>
			</div>
			<div class="borderBox gn-bgWhite pd20">
				<h3 class="MarginTitle">${homework.workTitle}</h3>
			<div class="chooseClass borderNone">
				<c:forEach items="${classList }" var="classItem" varStatus="status">
					<a href="${root}/homework/workCount.html?workId=${workId}&classId=${classItem.id}" class="<c:if test="${classId eq classItem.id }">active</c:if>">${classItem.name }</a>
				</c:forEach>
			</div>
		<h3 class="MarginTitle1 MarginTitle2 fz14">布置总人数：<span class="mr70"><b class="fontNormal red ">${workCountView.stuAllCount}</b>人</span>
			已提交：<span class="mr70"><b class="fontNormal red ">${workCountView.submitCount+workCountView.readOverCount}</b>人</span> 
			未提交：<span class="mr70"><b class="fontNormal red ">${workCountView.notSubmitCount}</b>人</span> </h3>
			<div class="w1080 marginauto  mt30">
				<div class="tableWrap withBorder marginauto">
					<h5>作业题量统计</h5>
					<table class="newTableStyle">
						<thead>
							<tr>
							<th width="20%">作业题量</th>
							<th width="20%">调用题库数量</th>
							<th width="20%">客观题数量</th>
							<th width="20%">主观题数量</th>
							<th width="20%">已批阅人数</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<td>${workCountView.allCount}</td>
							<td>${workCountView.queCount}</td>
							<td>${workCountView.objectQueCount}</td>
							<td>${workCountView.allCount-workCountView.objectQueCount}</td>
							<td>${workCountView.stuAllCount-workCountView.submitCount-workCountView.notSubmitCount}</td>
							</tr>
						</tbody>
				</table>
			</div>
			<div class="tableWrap withBorder mt30 marginauto">
				<h5 class="mb0">正确率统计</h5>
				<p class="LH3">
					总正确率：<span class="coffeeColor">
					<c:if test="${workCountView.correctCountList[0].checkedStuCount != '0'}">
					${workCountView.averageCorrect}
					</c:if>
					
					<c:if test="${workCountView.correctCountList[0].checkedStuCount == '0'}">
					
					</c:if>
					</span>
				</p>
				<div class="statifics">
					<ul class="correctRate clearfix">
						<li>
							<span class="quesNo">题号</span>
							<span class="theRate">正确率</span>
						</li>
				 
					
					<c:forEach items="${workCountView.correctCountList}" var="correctCount">
						<li>
							<span class="quesNo">${correctCount.rn}</span>
					<c:if test="${workCountView.correctCountList[0].checkedStuCount != '0'}">
						<c:if test="${correctCount.quePercent<'60%'}">
							<span class="theRate red">
							${correctCount.quePercent}
							</span>
						</c:if>
						<c:if test="${correctCount.quePercent>='60%'}">
							<span class="theRate">
							${correctCount.quePercent}
							</span>
						</c:if>
					</c:if>
					<c:if test="${workCountView.correctCountList[0].checkedStuCount == '0'}">
					
					<span class="theRate" >
						
					</span>
					</c:if>
						</li>
				
					</c:forEach>
				
					</ul>
				</div>
			</div>
				<div class="tableWrap mt30 withBorder marginauto">
					<h5>学生统计</h5>
					<div style="height:506px; overflow-y:auto">
						<table class="newTableStyle" >
							<thead>
								<tr class="trr">
									<th width="15%">姓名</th>
									<th width="15%" class="showResult">
										<div class="verticalMiddle ">
										提交时间
											<span class="arrowWrap" id="submitTime">
											<b id="submitTime" class="sortArrow up hide" pid="up" style="display: none;"></b>	
											<b id="submitTime" class="sortArrow" pid="down"></b>
											
											</span>
										</div>
									</th>
									<th width="10%">按时提交</th>
									<th width="20%">总评</th>
									<th width="10%">答题数</th>
									<th width="15%" class="showResult">
										<div class="verticalMiddle">
											正确率
										<span class="arrowWrap" id="correct">
										<b id="correct" class="sortArrow up " pid="up"></b>
										<b id="correct" class="sortArrow" pid="down"></b>
										</span>
										</div>
									</th>
									<th width="15%">操作</th>
								</tr>
						</thead>
						<tbody id="pageBody">
						
					
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
<script type="text/javascript">

$(".newTableStyle").on("click","b",function () {
	var classType = $(this).attr("pid");
	var id = '';
	var t = '';
	var $this = $(this);
		if(($this).siblings().hasClass("hide")){
			$this.hide().siblings().show();
			$this.addClass("hide");
			$this.siblings().removeClass("hide");
			id =$(this).siblings().attr("id");
			t =$(this).siblings().attr("pid");
		}else{
			$this.siblings().hide();
			$this.siblings().addClass("hide");
			id = $(this).attr("id");
			 t = $(this).attr("pid");
		}
			
		$this.parents("th").siblings().find("b").hide();
		$this.parents("th").siblings().find("b").addClass("hide");
		$this.parents("th").siblings(".showResult").find(".arrowWrap b").show();
		$this.parents("th").siblings(".showResult").find(".arrowWrap b").removeClass("hide");
	
	 answer(id,t);
	});
	
function answer(id,t){
	var pid = id;
	var type = t;
	$.ajax({
		type: "post",
		url: '${root}/homework/getReceiveStu.do?workId='+"${workId}"+'&classId='+"${classId}",
		data: {"pid":pid,"type":type},
		dataType: "json",
        success: function(data){
        	var html='';
        	if(data && data.length){
        		for(var i=0;i<data.length;i++){
                  	var stu=data[i];
                    html+='<tr>';
                    html+='<td>'+stu.realName+'</td>';
                    html+='<td>'+stu.submitTime+'</td>';
                   if(stu.submitTime >stu.finishTime){
                	   html+='<td><span class="red">否</span></td>';
                   }else{
                	   html+='<td><span >是</span></td>';
                   }
                    html+='<td>'+stu.summary+'</td>';
                    html+='<td><span class="red">'+stu.answerCount+'</span>/'+${workCountView.allCount}+'</td>';
                    html+='<td>'+stu.correctPercent+'</td>';
                    html+='<td><a href="${root}/teacherWork/toReadOverWorkView/${workId}/'+stu.workReceiveStuId+'.do" class="newhandle">查看批阅</a></td>';
    			    html+='</tr>';
        		}
           }else{
        	   html+='<tr><td colspan="7">抱歉，暂时没有批阅数据！</td></tr>';
           }
        	$("#pageBody").html(html);
        }
    });
}



</script>
</body>
</html>