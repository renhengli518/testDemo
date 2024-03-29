<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.codyy.commons.HostConfig"%>
<%@ page import="com.codyy.commons.context.SpringContext"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/micblog.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/group.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/blogarticle.css"/>

<div class="header_down_wrap">
    <div class="header_down_inner_wrap">
        <div class="_header">
            <div class="title_wrap_p">
                <div class="logo_s">
                	<a href="${HOME_PATH}/space/classroom/classroomBaseInfo.do?baseClassId=${classId}">
                		<img id="classroomPic" src="${HOME_PATH}/images/${headerInfo.THUMB}" width="120" height="120">
                	</a>
                </div>
                <div  class="title_s title_stu">
                	<input type="hidden" id="classroomId" value="${classId }">
	                <span id="className">${headerInfo.CLASSLEVELNAME }${headerInfo.CLASSNAME }</span>
	                <span id="schoolName">${headerInfo.SCHOOLNAME }</span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="w1200 marginauto mt70">
	<ul class="weibo_nav coffee_nav mb30 clearfix">
		<li><a href="${HOME_PATH}/space/classroom/index/${classId }.do" class="<c:if test='${menuTag eq "MAIN"}'>visited</c:if>">主页</a></li>
		<!-- <li><a href="#">优课资源</a></li> -->
		<c:choose>
			<c:when test="${not empty sessionScope.SESSION_USER }">
				<li><a href="<%=SpringContext.getBean(HostConfig.class).getCommunity()%>/blog/class/toClassBlog.do?baseClassId=${classId }" class="<c:if test='${menuTag eq "BLOG"}'>visited</c:if>">博文</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${HOME_PATH}/blog/class/toClassBlog.do?baseClassId=${classId }" class="<c:if test='${menuTag eq "BLOG"}'>visited</c:if>">博文</a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="${HOME_PATH}/space/classroom/showClassSchedule.do?baseClassId=${classId }" class="<c:if test='${menuTag eq "SCHEDULE"}'>visited</c:if>">课程表</a></li>
		<li><a href="<%=SpringContext.getBean(HostConfig.class).getOnlinetest()%>/classTest/classExamList/${classId}.html" class="<c:if test='${menuTag eq "TEST"}'>visited</c:if>">测试</a></li>
		<li><a href="<%=SpringContext.getBean(HostConfig.class).getOnlinetest()%>/classWork/toClassWorkList.do?baseClassId=${classId }" class="<c:if test='${menuTag eq "CLASS"}'>visited</c:if>">作业</a></li>
		<!--<li><a href="#">作业</a></li>
		<li><a href="#">黑板报</a></li>
		<li><a href="#">荣誉榜</a></li>
		<li><a href="#">相册</a></li>
		<li><a href="#">话题</a></li> -->
		<li><a href="${HOME_PATH}/space/classroom/browser/${classId }.do" class="<c:if test='${menuTag eq "CLASS_STUDENT"}'>visited</c:if>">班级成员</a></li>
	</ul>
</div>
<script type="text/javascript">
	// 初始化默认头部数据
	$(function(){
		$.getJSON("${HOME_PATH}/space/classroom/getTopData.do?classroomId=${classId}&callback=?",  
	    function(data) {  
			$(".logo_s > a > img").attr("src", "${HOME_PATH}/images/" + data.THUMB) ;
			$("#classroomId").val(data.CLASSID) ;
			$("#className").text(data.CLASSLEVELNAME + data.CLASSNAME) ;
			$("#schoolName").text(data.SCHOOLNAME) ;
			if(data.isManager) {
				$(".logo_s > a").attr("href", "${HOME_PATH}/space/classroom/classroomBaseInfo.do?baseClassId=${classId}") ;
			} else {
				$(".logo_s > a").attr("href", "javascript:;") ;
			}
	    });  
	}) ;
</script>