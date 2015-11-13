<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<!--#include virtual="../../common/meta.shtml"-->
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<script type="text/javascript" src="${root}/biz/js/stucreateExam.js"></script>
<script type="text/javascript" src="${root}/biz/js/pageNavigate.js"></script>
<%-- <script type="text/javascript" src="${root}/biz/js/setScore.js"></script> --%>
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
              	<a href="selfExamList.html">自主测试</a>
                <span class="nextlevel">></span><span class="currentLevel">自测试卷</span>
              </div>
             <div class="borderBox bkgWhite pd20 mt20">
             	<form id="createExamForm">
	              <ul class="commonUL">
	                <li>
	                    <span class=" verticalMiddle"><b class="red">*</b>试卷名称：</span><input id="examTitle"  name="examTitle" type="text" class="bigInput"><span class="c666 ml10">您最多可输入30个字符</span>
	                </li>
	                <li class="mt10">
	                   <input type="hidden" id="semesterOption" name="baseSemesterId" value="${stuinfo.semesterId }">
	                   <input type="hidden" id="classLevelOption" name="baseClasslevelId" value="${stuinfo.classlevelId }">
	                   
	                   <%--  <span class="mr50"><b class="red">*</b>学段</span>
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
                        </span> --%>
	                    <b class="red">*</b> 学科：
                         <span class="fixedWidthSpan c888"> 
                             <select class="newSel" id="subjOption" name="baseSubjectId">
				                   <option value="0">-- 请选择 --</option>
				              </select>
                         </span>
		                <%--<b class="red">*</b>试卷类型：
		                 <select class="newSel" id="examtypeOpts" name="examtype">
							<option value="0">-- 请选择 --</option>
					          <c:forEach items="${examTypes}" var="examType">
					          <option value="${examType.id }">${examType.name}</option>
							  </c:forEach> 
		                </select> --%>
	                </li>
	                <li class="mt10">
	                    <b class="red">*</b>答题时长：
	                    <span class="fixedWidthSpan mr50">
	                       <input type="text" placeholder="请输入答卷时长" class="fixedWidthInput" id="answerTime"  name="answerTime">分钟
	                       <span class="c666 ml10">最多输入3位数字</span>
	                    </span>
	                          
		               <!--  <b class="red">*</b>试卷总分：
		                <span class="fixedWidthSpan">
		                   <input type="text" placeholder="请输入答卷时长" class="fixedWidthInput" id="scoreInput"  name ="scoreInput">
		                   <span class="c666 ml10">最多输入3位数字</span>
		                 </span> -->
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
	                  </ul>
	              </div>
					<%@ include file="zj-box/zj-box-3.jsp"%>
              </div>
             
              <p class="center mt40">
                <button class="btn btnSearch nextStep saveExam-btn" >提交保存</button>
                <button class="btn btnSearch nextStep saveExam-btn toanswer" id="tosetscore">提交并开始答题</button>
              </p>
			  
          </div>
       
    </div>

   <script type="text/javascript">
   var  semesterId = '${stuinfo.semesterId }';//学段ID
   var  subjectId;//学科ID
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
</body>
</html>