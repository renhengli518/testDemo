<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<script>
 $(function(){
	 $(".chooseClass2").on("click","a",function(){
		 var $this=$(this);
		 $this.addClass("active").siblings().removeClass("active");
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
                <a href="${root}/teacherWork/toTeacherWork.do">作业</a><span class="nextlevel">></span><span class="currentLevel">作业批阅</span>
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite pd20">
                  <h3 class="MarginTitleWithBg clearfix pl60">
                     <div class="chooseClass2">
                     	<c:forEach items="${classList }" var="classItem" varStatus="status">
                     		<a href="${root }/homework/readOverHomework.html?homeworkId=${homeworkId}&classId=${classItem.id}" class="<c:if test="${classId eq classItem.id }">active</c:if>">${classItem.name }</a>
                     	</c:forEach>
				      </div>
                  </h3>
                  
			     <div class="w1080 marginauto  mt30">
			     	<c:if test="${answerNum ne 0 && totalNum ne checkedNum}">
				     	<div class="tableWrap marginauto">
					        <h5>按习题批阅</h5>
					        <table class="newTableStyle">
					           <thead>
					             <tr>
					               <th width="35%">习题数量</th>
					               <th width="35%">已批阅数量</th>
					               <th width="30%">操作</th>
					             </tr>
					           </thead>
					           <tbody>
					             <tr>
					               <td>${totalNum }</td>
					               <td><c:if test="${answerNum > 0}">${checkedNum }</c:if><c:if test="${answerNum eq 0}">0</c:if></td>
					               <td><a class="newHandle readByQuestion" href="javascript:;">在线批阅</a></td>
					             </tr>
					           </tbody>
					        </table>
					     </div>
			     	</c:if>
			        
				    <div class="tableWrap marginauto">
				        <h5 >按学生批阅</h5>
				        <div style="height:840px; overflow-y:auto">
				          <table class="newTableStyle" >
				             <thead>
					             <tr>
					               <th width="20%">姓名</th>
					               <th width="30%">
					                 <div class="verticalMiddle">
					                                                    提交时间
					                    <span class="arrowWrap">
					                        <b class="sortArrow up" style="display: none;"></b>
					                        <b class="sortArrow"></b>                           
                                        </span>
					                 </div>
					               </th>
					               <th width="10%">按时提交</th>
					               <th width="25%">答题数目</th>
					               <th width="15%">操作</th>
					             </tr>
					           </thead>
					           <tbody id="pageData"></tbody>
					      </table>
					      <div id="pageNavi"></div>
				        </div>
				     </div>
			     </div>
              </div>
          </div>
       </div>
    </div>
   
</body>
<script type="text/javascript">
var answerNum = '${answerNum}';
$(function(){
	var url = "${root }/homework/readOverStudent.do?homeworkId=${homeworkId}&classId=${classId}";
	var mySplit = new SplitPage({
	    node : $id("pageNavi"),
	    url : url + "&sort=desc",
	    data :  {},
	    count : 10,
	    callback : showList,
	    type : 'get' //支持post,get,及jsonp
	});			 
	
	function showList(data,total){
        var html = "";
        if(data && data.length){
        	for(var i =0; i < data.length; i++){
        		var stuData = data[i];
                html += '<tr data-workReceiveStuId="'+stuData.receiveStuId+'"><td>'+stuData.name+'</td>'+
		               '<td>'+stuData.submitTime+'</td>'+
		               '<td>'+(stuData.isSubmitOnTime == 'Y'?'是':'否') +'</td>'+
		               '<td>'+stuData.answerCount+'/'+stuData.questionCount+'</td>'+
		               '<td><a href="javascript:;" class="newHandle readStudent">批阅</a></td></tr>'
            }
        	$("#pageData").html(html);
        }else{
        	$("#pageData").html('<tr><td colspan="5" class="center">'+noRecord+'</td></tr>');
        }
    }
	
	$(".sortArrow").click(function(){
		var $this=$(this);
		if($this.hasClass("up")){
			mySplit.search(url + "&sort=desc",{});
			
		}else{
			mySplit.search(url + "&sort=asc",{});
		}
		$this.hide().siblings().show();
	});
	
	$(".readByQuestion").click(function(){
		if(answerNum > 0){
			location.href="${root }/homework/readByQuestionPre.html?homeworkId=${homeworkId}&classId=${classId}";
		}else{
			Win.alert("暂无学生提交作业！");
		}
	});
	
	$("#pageData").on("click",".readStudent",function(){
		var workReceiveStuId = $(this).parents("tr").attr("data-workReceiveStuId");
		location.href="${root }/homework/readByStudentPre.html?homeworkId=${homeworkId}&workReceiveStuId="+workReceiveStuId;
	});
});

</script>
</html>