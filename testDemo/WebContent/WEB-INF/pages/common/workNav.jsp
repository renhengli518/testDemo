<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.codyy.commons.utils.RedisCacheUtils" %>
<%@ page import="com.codyy.commons.CommonsConstant" %>
<%@ page import="com.codyy.commons.sso.SessionUser" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	Object sessionUser = request.getSession().getAttribute(CommonsConstant.SESSION_USER);
	if (sessionUser != null) {
		SessionUser user = (SessionUser) sessionUser;
		List<String> configNames = new ArrayList<String>();
		configNames.add("front.workspace.in.homework");//测试
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
                    	<img id="myAvatar" src="${root}/images/${configs['front.workspace.in.homework'].imagePath }" width="90" height="90">
                    </div>
	                <span class="title">${configs["front.workspace.in.homework"].textContent }</span>
                </div>
            </li>
             <li class="header_item header_item_last">
                <nav class="nav" id="nav">
                
                  <c:if test="${sessionScope.SESSION_USER.userType  eq 'STUDENT'}">
                    <a class="nav_item <c:if test="${type=='studentWork'}">nav_item_clicked</c:if>" href="${root}/studentWork/toStudentWork.html">我的作业</a>
                    <a class="nav_item <c:if test="${type=='studentRead'}">nav_item_clicked</c:if>" href="${root}/studentWork/myReadOver.html">我的批阅</a>
                  </c:if>
                
                </nav>
            </li>
            
        </ul>
    </div>
</div>
