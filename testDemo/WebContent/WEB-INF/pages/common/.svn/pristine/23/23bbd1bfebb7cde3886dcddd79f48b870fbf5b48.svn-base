<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="menu fl">
	<ul class="mainMenu fl">
		<c:forEach items="${menuList}" var="menu" varStatus="status">
			<li class="${menu.clazz}" data-group="${menu.group}" data-floor="${status.index}" ><a href="javascript:;">${menu.title}</a></li>
		</c:forEach>
	</ul>
	<div class="secondMenu fl">
		<c:forEach items="${menuList}" var="menu">
		<ul class="secondMenu-${menu.group}" data-group="${menu.group}">
			<c:forEach items="${menu.subMenu}" var="sub">
				<li id="${sub.id}"><a href="${sub.href}">${sub.title}</a></li>
			</c:forEach>
		</ul>
		</c:forEach>
	</div>
</div>