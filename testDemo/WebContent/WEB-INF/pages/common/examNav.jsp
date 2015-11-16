<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.codyy.commons.utils.RedisCacheUtils" %>
<%@ page import="com.codyy.commons.CommonsConstant" %>
<%@ page import="com.codyy.commons.sso.SessionUser" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link media="all" type="text/css" rel="stylesheet" href="${root}/biz/css/showLoading.css"/>
<script src="${root}/biz/js/jquery.showLoading.js" type="text/javascript"></script>
<%
	Object sessionUser = request.getSession().getAttribute(CommonsConstant.SESSION_USER);
	if (sessionUser != null) {
		SessionUser user = (SessionUser) sessionUser;
		List<String> configNames = new ArrayList<String>();
		configNames.add("front.workspace.in.test");//测试
		if (CommonsConstant.USER_TYPE_AREA_USER.equals(user.getUserType())) {
			pageContext.setAttribute("configs", RedisCacheUtils.getFrontAreaConfigData(user.getAreaId(), configNames));
		} else {
			pageContext.setAttribute("configs", RedisCacheUtils.getFrontSchoolConfigData(user.getSchoolId(), configNames));
		}
	}
%>
<div class="header_down_wrap">
    <div class="header_down_inner_wrap">
        <ul class="_header">
            <li class="header_item header_item_first">
                <div class="title_wrap">
                    <div class="logo">
                    	<img id="myAvatar" src="${root}/images/${configs['front.workspace.in.test'].imagePath }" width="90" height="90">
                    </div>
	                <span class="title">${configs["front.workspace.in.test"].textContent }</span>
                </div>
            </li>
             <li class="header_item header_item_last">
                <nav class="nav" id="nav">
                  <c:if test="${sessionScope.SESSION_USER.userType  eq 'TEACHER'}">
               		<a class="nav_item <c:if test="${tabType=='exam'}">nav_item_clicked</c:if>" href="${root}/teacherTest/examList.html">我的试卷</a>
	               	<a class="nav_item <c:if test="${tabType=='real'}">nav_item_clicked</c:if>" href="${root}/teacherTest/toRealExam.html">真题试卷</a>
	               	<a class="nav_item <c:if test="${tabType=='classExam'}">nav_item_clicked</c:if>" href="${root}/teacherTest/classExamList.html">测试任务</a>
                  </c:if>
                  <c:if test="${sessionScope.SESSION_USER.userType  eq 'STUDENT'}">
                    <a class="nav_item <c:if test="${tabType=='studentTask'}">nav_item_clicked</c:if>" href="${root}/studentTest/studentTaskList.html">测试任务</a>
                    <a class="nav_item <c:if test="${tabType=='selfExam'}">nav_item_clicked</c:if>" href="${root}/studentTest/selfExamList.html">自主测试</a>
                  </c:if>
                  <c:if test="${sessionScope.SESSION_USER.userType  eq 'SCHOOL_USR'}">
                  	 <a class="nav_item <c:if test="${tabType=='exam'}">nav_item_clicked</c:if>" href="${root}/schoolTest/examList.html">年级统考</a>
                     <a class="nav_item <c:if test="${tabType=='task'}">nav_item_clicked</c:if>" href="${root}/schoolTest/classExamList.html">班级测试</a>
                  </c:if>
                </nav>
            </li>
            
        </ul>
    </div>
</div>
<div id="loading"></div>
