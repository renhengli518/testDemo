<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
</head>

<body class="mainIndex">
    <%@include file="../../../common/topHeader.jsp" %>
	<%@include file="../../../common/examNav.jsp" %>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
                <a href="${root}/teacherTest/classExamList.html">测试任务</a><span class="nextlevel">></span><span class="currentLevel">批阅</span>
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite pd20">
                  <h3 class="MarginTitleWithBg clearfix pl60">
                     <div class="chooseClass2">
                       <c:if test="${classLevelClassList!=null}"><!--默认选中第一个-->
                           <c:forEach items="${classLevelClassList}" var="classItem" varStatus="status">
                     		<a classId="${classItem.classId}" classLevelId="${classItem.classLevalId}" href="javascript:search()"  class="<c:if test="${(classFirstId eq classItem.classId) && (classLevelFirstId eq classItem.classLevalId)}">active</c:if>">${classItem.classLevalName}${classItem.className}</a>
                     	   </c:forEach>
                       </c:if>
				      </div>
                  </h3>
                  
			     <div class="w1080 marginauto  mt30" >
				     	<div class="tableWrap marginauto" id="subjQue">
					       
					    </div>
			     	
			       
				    <div class="tableWrap marginauto" style="margin-top:50px;">
				     <div style="background:white;">测试人数：<span id="countAllPerson"></span>人&nbsp;&nbsp;&nbsp;已提交：<span id="countAllSumbitPerson"></span>人&nbsp;&nbsp;&nbsp;未提交：<span id="countNoSumbitPerson"></span>人</div>
				        <h5 >按学生批阅</h5><span style="margin-left:850px;margin-top:-50px; position:absolute;"><input style="background:white;" type="text" id="stuName" value=""/><input id="searchByName" type="button" value="搜索"/></span>
				        <div style="height:840px; overflow-y:auto">
				          <table class="newTableStyle" >
				             <thead>
					             <tr>
					               <th width="35%">姓名</th>
					               <th width="35%">按时提交</th>
					               <th width="30%">操作</th>
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
var examTaskId='${requestScope.examTaskId}';
var mySplit;
var url = "${root}/teacherTest/showStuStatuPageList.do";

function showList(data,total){
    var html = "";
    if(data && data.length){
    	for(var i =0; i < data.length; i++){
    		var stuData = data[i];
            html += '<tr data-workReceiveStuId="'+stuData.baseUserId+'"><td>'+stuData.realName+'</td>'+
	               '<td>'+(stuData.lazyStatus == 'Y'?'是':'否') +'</td>'+
	               '<td><a href="javascript:;" id='+stuData.resultId+' stuName='+stuData.realName+' class="newHandle readStudent">在线批阅</a></td></tr>'
        }
    	$("#pageData").html(html);
    }else{
    	$("#pageData").html('<tr><td colspan="5" class="center">'+noRecord+'</td></tr>');
    }
}

	
	 $(".chooseClass2").on("click","a",function(){
		 var $this=$(this);
		 $this.addClass("active").siblings().removeClass("active");
	 });
	$(".sortArrow").click(function(){
		var $this=$(this);
		if($this.hasClass("up")){
			mySplit.search(url + "&sort=asc",{});
			
		}else{
			mySplit.search(url + "&sort=desc",{});
		}
		$this.hide().siblings().show();
	});

//跳转到主观题的页面
function goReview(obj){
	/* if(answerNum > 0){
		location.href="${root }/homework/readByQuestionPre.html?homeworkId=${homeworkId}&classId=${classId}";
	}else{
		Win.alert("暂无学生提交作业！");
	} */
	var classId =  $(".active").attr("classid");
	var classLevelId = $(".active").attr("classLevelId");
	location.href="${root }/teacherTest/readByQuestionPre.html?examTaskId="+examTaskId+"&classId="+classId+"&classLevelId="+classLevelId;
};

$("#pageData").on("click",".readStudent",function(){
	var resultId=$(this).attr("id");
	var stuName=$(this).attr("stuName");
	//alert("id="+resultId);
	location.href="${root }/teacherTest/readByStudentPre.html?stuName="+stuName+"&resultId="+resultId;
});

//按条件进行查询函数
function search(){
	console.log("查询");
	var classId =$(".active").attr("classId");
	var classLevelId =$(".active").attr("classLevelId");
	var name=$("#stuName").val();
	
	//统计本测试的未提交的主观题的个数
	countSubjectQuestionNum(examTaskId,classLevelId,classId);
	//获得测试的总人数  已提交  未提交
	countPerson(classLevelId,classId,'no',examTaskId);
	
	var url="${root}/teacherTest/showStuStatuPageList.do";
	var param  = {
	  realName:name,
	  examTaskId:examTaskId,
	  classLevId:classLevelId,
	  classId:classId
	};
	if(mySplit == null){
		mySplit = new SplitPage({
		    node : $id("pageNavi"),
		    url : url,
		    data :  param,
		    count : 10,
		    callback : showList,
		    type : 'get' //支持post,get,及jsonp
		});			
	}else{
		mySplit.search(url,param);
	}
}

//按姓名进行搜索操作
$("#searchByName").click(function(){
	search();
});

$(window).load(function(){ 
	  search();
	});


function countPerson(classLeveId,classId,status,examTaskId){ //统计测试的人数
	   var url = "${root}/teacherTest/countTestPerson.do";
		$.ajax({
			   type: "POST",
			   url: url,
			   data:{
				   'classLeveId':classLeveId,
				   'classId':classId,
				   'status':status,
				   'examTaskId':examTaskId
			   },
			   success: function(data){
			      $("#countAllPerson").html(data.allTestCountPer);
			      $("#countAllSumbitPerson").html(data.submitedCountPer);
			      $("#countNoSumbitPerson").html(data.initedCounterPer);
			   }
		 });
}


function countSubjectQuestionNum(examTaskId,classLevelId,classId){ //统计测试的人数
	   var url = "${root}/teacherTest/getsubjetivenum.do";

		$.ajax({
			   type: "POST",
			   url: url,
			   data:{
				   'taskExamId':examTaskId,
	               'classLevalId':classLevelId,
	               'classId':classId
			   },
			   success: function(data){
				   //获得主观题的未批阅数
				  var html = "";
			      if(data.allSubjectiveNum>0 && data.allSubjectiveNum>data.checkedNum){
			    	  html='<h5>按题批阅</h5>'+
				        '<table class="newTableStyle">'+
				           '<thead>'+
				             '<tr>'+
				               '<th width="35%">主观题数量</th>'+
				               '<th width="35%">已批阅数量</th>'+
				               '<th width="30%">操作</th>'+
				             '</tr>'+
				           '</thead>'+
				           '<tbody>'+
				             '<tr>'+
				               '<td id="allNum">'+data.allSubjectiveNum+'</td>'+
				               '<td id="checkNum">'+data.checkedNum+'</td>'+
				               '<td><a class="newHandle readByQuestion" href="javascript:;" onclick="goReview(this)">在线批阅</a></td>'+
				             '</tr>'+
				           '</tbody>'+
				        '</table>';
				        $("#subjQue").html(html);
			      }
			   }
		 });
  		
}

</script>
</html>