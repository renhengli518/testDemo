<%@page import="com.codyy.commons.sso.SessionUser"%>
<%@page import="com.codyy.commons.CommonsConstant"%>
<%@page import="com.codyy.commons.utils.RedisCacheUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(CommonsConstant.SESSION_USER);
	String roleTitle = "";
	String userTitle = "";
	String teacherTitle = "";
	String studentTitle = "";
	String patriarchTitle = "";
	String classroomTitle = "";
	String clazzTitle = "";
	String scheduleTitle = "";
	String personalTitle = "";
	String imagePath = "";
	String baseTitle = "";
	String lowerUserTitle = "";
	String schoolTitle = "";
	if(sessionUser != null && CommonsConstant.USER_TYPE_SCHOOL_USER.equals(sessionUser.getUserType())){
		roleTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.role").get("textContent");
		userTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.user").get("textContent");
		teacherTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.teacher").get("textContent");
		studentTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.student").get("textContent");
		patriarchTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.patriarch").get("textContent");
		classroomTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.classroom").get("textContent");
		clazzTitle =RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.class").get("textContent");
		scheduleTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.schedule").get("textContent");
		personalTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.base.personal").get("textContent");
		imagePath = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.in.base").get("imagePath");
		baseTitle = RedisCacheUtils.getFrontSchoolConfigData(sessionUser.getSchoolId(), "front.workspace.in.base").get("textContent");
	}
	if(sessionUser != null && CommonsConstant.USER_TYPE_AREA_USER.equals(sessionUser.getUserType())){
		roleTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.role").get("textContent");
		userTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.user").get("textContent");
		teacherTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.teacher").get("textContent");
		studentTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.student").get("textContent");
		patriarchTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.patriarch").get("textContent");
		classroomTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.classroom").get("textContent");
		clazzTitle =RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.class").get("textContent");
		scheduleTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.schedule").get("textContent");
		personalTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.personal").get("textContent");
		imagePath = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.in.base").get("imagePath");
		baseTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.in.base").get("textContent");
		lowerUserTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.junior").get("textContent");
		schoolTitle = RedisCacheUtils.getFrontAreaConfigData(sessionUser.getAreaId(), "front.workspace.base.school").get("textContent");
	}
%>
<div class="header_down_wrap">
    <div class="header_down_inner_wrap">
        <ul class="_header">
            <li class="header_item header_item_first">
                <div class="title_wrap">
                    <div class="logo"><img src="${root }/images/<%=imagePath %>" width="90" height="90"/></div>
                    <span class="title"><%=baseTitle %></span>
                </div>
            </li>
            <li class="header_item header_item_last">
                <ul class="nav ml50 " id="nav">
					<li class="dropdown">
						<a href="javascript:;" class="dropdown-toggle nav_item_base <c:if test="${submenu=='studentList'||submenu=='parentList'||submenu=='roleList'||submenu=='userList'||submenu=='teacher'}">nav_item_clicked</c:if>">账号管理</a>
						<ul class="dropdown-menu">
							<c:if test="${sessionScope.SESSION_USER.userType == 'AREA_USR' or sessionScope.SESSION_USER.userType == 'SCHOOL_USR'  }">
								<li><a href="${root}/role/roleInfoListPage.html" ><%=roleTitle %></a></li>
								<li><a href="${root}/user/userInfoListPage.html"><%=userTitle %></a></li>
							</c:if>
							<c:if test="${sessionScope.SESSION_USER.userType == 'SCHOOL_USR'  }">
								<li><a href="${root}/base/user/teacher/userList.html"><%=teacherTitle %></a></li>
								<li><a href="${root}/base/studentManager/studentList.html"><%=studentTitle %></a></li>
								<li><a href="${root}/base/parentManager/parentList.html"><%=patriarchTitle %></a></li>
							</c:if>
						</ul>
					</li>
					<c:if test="${sessionScope.SESSION_USER.userType == 'AREA_USR' }">
						<li class="child nav_item_base">
							<a  href="${root}/user/lowerUserInfoListPage.html" class="nav_item_base <c:if test="${submenu=='loweruser'}">nav_item_clicked</c:if>"><%=lowerUserTitle %></a>
						</li>
						<li class="child nav_item_base">
							<a  href="${root}/base/schoolManager/clsschoollist.html" class="nav_item_base <c:if test="${submenu=='school'}">nav_item_clicked</c:if>"><%=schoolTitle %></a>
						</li>
					</c:if>
					<c:if test="${sessionScope.SESSION_USER.userType == 'SCHOOL_USR'  }">
						<li class="child nav_item_base">
							<a  href="${root}/base/classroomManager/classroomList.html" class="nav_item_base <c:if test="${submenu=='classroomList'}">nav_item_clicked</c:if>"><%=classroomTitle %></a>
						</li>
						<li class="child nav_item_base">
							<a href="${root}/base/classManager/classList.html" class="nav_item_base <c:if test="${submenu=='classList'}">nav_item_clicked</c:if>"><%=clazzTitle %></a>
						</li>
						<li class="child nav_item_base">
							<a href="${root}/base/class/schedule/listClassPage.do" class="nav_item_base <c:if test="${submenu=='schedule'}">nav_item_clicked</c:if>"><%=scheduleTitle %></a>
						</li>
					</c:if>
                </ul>
            </li>
        </ul>
    </div>
</div>

<script type="text/javascript">
	$(".dropdown,.dropdown-menu").bind("mouseover",function(){
		var cls=$(this).attr("class");
		if(cls.search("dropdown")!=-1){
			$(this).addClass("open");
		}
		else{
			$(this).parent().addClass("open");
		}
	});
	$(".dropdown,.dropdown-menu").bind("mouseout",function(){
		var cls=$(this).attr("class");
		if(cls.search("dropdown")!=-1){
			$(this).removeClass("open");
		}
		else{
			$(this).parent().removeClass("open");
		}
	});
</script>