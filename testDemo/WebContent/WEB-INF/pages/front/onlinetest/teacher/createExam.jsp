<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<!--#include virtual="../../common/meta.shtml"-->
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<script type="text/javascript" src="${root}/biz/js/teacreateExam.js"></script>
<script type="text/javascript" src="${root}/biz/js/pageNavigate.js"></script>
<script type="text/javascript" src="${root}/biz/js/setScore.js"></script>
<!-- <style>
.fixedBox {
		position: fixed;
		top:0;
		margin-top: 0;
 		width: 270px;
  		height: 150px;		
	}
	
</style> -->

</head>
<body class="SynchChapter">
	 <%@include file="../../../common/topHeader.jsp"%>
     <%@include file="../../../common/examNav.jsp"%>
	<div id="setques">
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
              	<c:if test="${sessionScope.SESSION_USER.userType  eq 'TEACHER'}">
              		<a href="javascript:;">我的试卷</a>
              	</c:if>
              	<c:if test="${sessionScope.SESSION_USER.userType  eq 'SCHOOL_USR'}">
              		<a href="javascript:;">年级统考</a>
              	</c:if>
                <span class="nextlevel">></span><span class="currentLevel">组建试卷</span>
              </div>
             <div class="borderBox bkgWhite pd20 mt20">
             	<form id="createExamForm">
	              <ul class="commonUL">
	                <li>
	                    <span class=" verticalMiddle"><b class="red">*</b>试卷名称：</span><input id="examTitle"  name="examTitle" type="text" class="bigInput"><span class="c666 ml10">您最多可输入30个字符</span>
	                </li>
	                <li class="mt10">
	                    <span class="mr50"><b class="red">*</b>学段</span>
					       <select class="newSel w160 mr30" id="semesterOption" name="baseSemesterId" >
					         <option value="0">-- 请选择 --</option>
					          <c:forEach items="${semesters}" var="semester">
					          <option value="${semester.baseSemesterId }">${semester.semesterName}</option>
							  </c:forEach> 
					       </select>
                        <b class="red">*</b> 年级：
                        <span class="fixedWidthSpan c888"> 
                            <select class="newSel" id="classLevelOption" name="baseClasslevelId">
			                   <option value="0">-- 请选择 --</option>
			              </select>
                        </span>
	                    <b class="red">*</b> 学科：
                         <span class="fixedWidthSpan c888"> 
                             <select class="newSel" id="subjOption" name="baseSubjectId">
				                   <option value="0">-- 请选择 --</option>
				              </select>
                         </span>
		                <b class="red">*</b>试卷类型：
		                <select class="newSel" id="examtypeOpts" name="examtype">
							<option value="0">-- 请选择 --</option>
					          <c:forEach items="${examTypes}" var="examType">
					          <option value="${examType.id }">${examType.name}</option>
							  </c:forEach> 
		                </select>
	                </li>
	                <li class="mt10">
	                    <b class="red">*</b>答题时长：
	                    <span class="fixedWidthSpan mr50">
	                       <input type="text" placeholder="请输入答卷时长" class="fixedWidthInput" id="answerTime"  name="answerTime">分钟
	                       <span class="c666 ml10">最多输入3位数字</span>
	                    </span>
	                          
		                <b class="red">*</b>试卷总分：
		                <span class="fixedWidthSpan">
		                   <input type="text" placeholder="请输入答卷时长" class="fixedWidthInput" id="scoreInput"  name ="scoreInput">
		                   <span class="c666 ml10">最多输入3位数字</span>
		                 </span>
	                </li>
	              </ul>
	              </form>
	          </div>
           <div class="bkgWhite pd20 mt20">
	              <div >
	                  <ul class="commonUL">
	                    <li class="black mt10" >
	                      <label class="text">选择习题：</label>
	                      <span class="cont">请选择需要生成试卷的习题</span>
	                    </li>
	                    <li class="mt10">
	                      <label class="text"><span class="red">*</span>选题方式：</label>
	                      <span class="cont noPd quesKinds select-exercise-style">
	                          <a href="javascript:;" class="active " index="0">知识点选题</a>
	                          <a href="javascript:;" index="1">同步章节选题</a>
	                          <a href="javascript:;" index="2">智能选题</a>
	                      </span>
	                    </li>
	                  </ul>
	              </div>
					<%@ include file="zj-box/zj-box-1.jsp"%>
					<%@ include file="zj-box/zj-box-2.jsp"%>
					<%@ include file="zj-box/zj-box-3.jsp"%>
              </div>
             
              <p class="center mt40">
                <button class="btn btnSearch nextStep" id="tosetscore">下一步，设置单题分值</button>
              </p>
			  
          </div>
       
    </div>

   <script type="text/javascript">
   //初始化题目小菜单
 /*   	 fixedBox.init({
		"id": "box1"
	}); 
   	 fixedBox.init({
		"id": "box2"
	}); 
   	 fixedBox.init({
		"id": "box3"
	});  */
   </script>
   </div>
    <%@ include file="setScore.jsp"%> 
</body>
</html>