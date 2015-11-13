<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.codyy.commons.utils.RedisCacheUtils" %>
<%@ page import="com.codyy.commons.CommonsConstant" %>
<%@ page import="com.codyy.commons.sso.SessionUser" %>
<link media="all" type="text/css" rel="stylesheet" href="${root}/biz/css/showLoading.css"/>
<script src="${root}/biz/js/jquery.showLoading.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	Object sessionUser = request.getSession().getAttribute(CommonsConstant.SESSION_USER);
	if (sessionUser != null) {
		SessionUser user = (SessionUser) sessionUser;
		List<String> configNames = new ArrayList<String>();
		configNames.add("front.workspace.in.questionlib");//题库
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
                    	<img id="myAvatar" src="${root}/images/${configs['front.workspace.in.questionlib'].imagePath }" width="90" height="90">
                    </div>
	                <span class="title">${configs["front.workspace.in.questionlib"].textContent }</span>
                </div>
            </li>
             <li class="header_item header_item_last">
                <nav class="nav" id="nav">
               		<a class="nav_item <c:if test="${tabType=='share'}">nav_item_clicked</c:if>" href="${root}/questionLib/toSharedLib.html">共享题库</a>
               		<a class="nav_item <c:if test="${tabType=='own'}">nav_item_clicked</c:if>" href="${root}/questionLib/toOwnLib.html">我的习题</a>
               		<c:if test="${sessionScope.SESSION_USER.userType  eq 'TEACHER'}">
	               		<a class="nav_item <c:if test="${tabType=='favorite'}">nav_item_clicked</c:if>" href="${root}/questionLib/toFavoriteLib.html">收藏的习题</a>
               		</c:if>
               		<c:if test="${sessionScope.SESSION_USER.userType  eq 'SCHOOL_USR'}">
	               		<a class="nav_item <c:if test="${tabType=='real'}">nav_item_clicked</c:if>" href="${root}/schoolTest/toRealExam.html">真题试卷</a>
               		</c:if>
                </nav>
            </li>
        </ul>
    </div>
</div>
<div id="loading"></div>
