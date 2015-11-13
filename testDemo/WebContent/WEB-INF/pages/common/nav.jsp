<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.codyy.commons.utils.RedisCacheUtils" %>
<%@ page import="com.codyy.commons.CommonsConstant" %>
<%@ page import="com.codyy.commons.sso.SessionUser" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
Object sessionUser = request.getSession().getAttribute(CommonsConstant.SESSION_USER);
if(sessionUser != null) {
	
	SessionUser user = (SessionUser) sessionUser;
	List<String> configNames = new ArrayList<String>();
	configNames.add("front.workspace.olclass");//在线课堂
	configNames.add("front.workspace.netclass");//名校网络课堂
	//在线课堂
	configNames.add("front.workspace.olclass.schedule");//课程表
	configNames.add("front.workspace.olclass.tschedule");//教师课表
	configNames.add("front.workspace.olclass.live");//实时直播
	configNames.add("front.workspace.olclass.replay");//往期录播
	configNames.add("front.workspace.olclass.broadcast");//远程导播
	configNames.add("front.workspace.olclass.task");//课堂作业
	configNames.add("front.workspace.olclass.tour");//课堂巡视
	//名校网络课堂
	configNames.add("front.workspace.netclass.class");//约课列表
	configNames.add("front.workspace.netclass.live");//实时直播
	configNames.add("front.workspace.netclass.replay");//往期录播
	configNames.add("front.workspace.netclass.broadcast");//远程导播
	configNames.add("front.workspace.netclass.task");//课堂作业
	configNames.add("front.workspace.netclass.tour");//课堂巡视
	//个人备课
	configNames.add("front.workspace.preparelesson.preparelesson");//教案
	configNames.add("front.workspace.preparelesson.prepareimage");//学科素材
	
	//图片
	configNames.add("front.workspace.in.olclass");//在线课堂
	configNames.add("front.workspace.in.netclass");//名校网络课堂
	configNames.add("front.workspace.in.preparelesson");//个人备课
	configNames.add("front.workspace.in.rethink");//教学反思
	
	configNames.add("front.workspace.in.schedule");//课程表
	
	configNames.add("front.workspace.schedule.personal");//个人课表
	configNames.add("front.workspace.schedule.class");//班级课表
	
	if(CommonsConstant.USER_TYPE_AREA_USER.equals(user.getUserType())){
		pageContext.setAttribute("configs", RedisCacheUtils.getFrontAreaConfigData(user.getAreaId(), configNames));
	} else if(CommonsConstant.USER_TYPE_SCHOOL_USER.equals(user.getUserType())){
		pageContext.setAttribute("configs", RedisCacheUtils.getFrontSchoolConfigData(user.getSchoolId(), configNames));
	}else if(CommonsConstant.USER_TYPE_TEACHER.equals(user.getUserType())){
		pageContext.setAttribute("configs", RedisCacheUtils.getFrontSchoolConfigData(user.getSchoolId(), configNames));
	}else if(CommonsConstant.USER_TYPE_PARENT.equals(user.getUserType())){
		pageContext.setAttribute("configs", RedisCacheUtils.getFrontSchoolConfigData(user.getSchoolId(), configNames));
	}else if(CommonsConstant.USER_TYPE_STUDENT.equals(user.getUserType())){
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
	                    <c:if test="${type == 'ONLINE_CLASS'}">
	                    	<img id="myAvatar" src="${root }/images/${configs['front.workspace.in.olclass'].imagePath }" width="90" height="90">
	                    </c:if>
	                    <c:if test="${type == 'LIVE'}">
	                    	<img id="myAvatar" src="${root }/images/${configs['front.workspace.in.netclass'].imagePath }" width="90" height="90">
	                    </c:if>
	                    <c:if test="${type == 'PREPARELESSON'}">
	                    	<img id="myAvatar" src="${root }/images/${configs['front.workspace.in.preparelesson'].imagePath }" width="90" height="90">
	                    </c:if>
	                    <c:if test="${type == 'RETHINK'}">
	                    	<img id="myAvatar" src="${root }/images/${configs['front.workspace.in.rethink'].imagePath }" width="90" height="90">
	                    </c:if>
	                    <c:if test="${type == 'SCHEDULE'}">
	                    	<img id="myAvatar" src="${root }/images/${configs['front.workspace.in.schedule'].imagePath }" width="90" height="90">
	                    </c:if>
                    </div>
                    <c:if test="${type == 'ONLINE_CLASS'}">
	                    <span class="title">${configs["front.workspace.in.olclass"].textContent }</span>
                    </c:if>
                    <c:if test="${type == 'LIVE'}">
	                    <span class="title">${configs["front.workspace.in.netclass"].textContent }</span>
                    </c:if>
                    <c:if test="${type == 'PREPARELESSON'}">
	                   <span class="title">${configs["front.workspace.in.preparelesson"].textContent }</span>
	                 </c:if>
                    <c:if test="${type == 'RETHINK'}">
	                   <span class="title">${configs["front.workspace.in.rethink"].textContent }</span>
	                 </c:if>
	                 <c:if test="${type == 'SCHEDULE'}">
	                   <span class="title">${configs["front.workspace.in.schedule"].textContent }</span>
	                 </c:if>
                </div>
            </li>
            <li class="header_item header_item_last">
                <nav class="nav" id="nav">
               		 <c:if test="${sessionScope.SESSION_USER.userType == 'AREA_USR'
               		 					or sessionScope.SESSION_USER.userType == 'SCHOOL_USR'  }">
               		 	<c:if test="${type == 'ONLINE_CLASS'}">
	                    	<a class="nav_item <c:if test="${submenu=='ONLINE_CLASS_LIST'}">nav_item_clicked</c:if>" href="${root}/monitor/schedule/statisticsSchedulePage.html">${configs["front.workspace.olclass.schedule"].textContent }</a>
               		 	</c:if>
               		 	<c:if test="${type == 'LIVE'}">
	                    	<a class="nav_item <c:if test="${submenu=='LIVE_LIST'}">nav_item_clicked</c:if>" href="${root}/monitor/schoolNet/appointment/list.html">${configs["front.workspace.netclass.class"].textContent }</a>
               		 	</c:if>
               		 </c:if>
               		 
                	 <c:if test="${sessionScope.SESSION_USER.userType == 'TEACHER' }">
                	 	<c:if test="${type == 'ONLINE_CLASS'}">
                    		<a class="nav_item <c:if test="${submenu=='TEACHER_CLASS_LIST'}">nav_item_clicked</c:if>" href="${root}/monitor/schedule/browse.html">${configs["front.workspace.olclass.tschedule"].textContent }</a>
                    	</c:if>
                    	<c:if test="${type == 'LIVE'}">
	                    	<a class="nav_item <c:if test="${submenu=='LIVE_LIST'}">nav_item_clicked</c:if>" href="${root}/monitor/schoolNet/appointment/list.html">${configs["front.workspace.netclass.class"].textContent }</a>
               		 	</c:if>
                    </c:if>
                    
                    <c:if test="${sessionScope.SESSION_USER.userType == 'TEACHER' 
                    					or sessionScope.SESSION_USER.userType == 'STUDENT' 
                    					or sessionScope.SESSION_USER.userType == 'PARENT'
                    					or sessionScope.SESSION_USER.userType == 'AREA_USR'
                    					or sessionScope.SESSION_USER.userType == 'SCHOOL_USR'}">
        				<c:if test="${type == 'ONLINE_CLASS'}">
	                    	<a class="nav_item <c:if test="${submenu=='LIVE_BROADCAST'}">nav_item_clicked</c:if>" href="${root }/monitor/broadcast/browser.do?broadcastType=LIVE&studentUserId=${studentUserId}">${configs["front.workspace.olclass.live"].textContent }</a>
	                    </c:if>
	                    <c:if test="${type == 'LIVE'}">
	                    	<a class="nav_item <c:if test="${submenu=='LIVE_LIVE'}">nav_item_clicked</c:if>" href="${root}/monitor/schoolNet/appointment/live/list.html<c:if test="${sessionScope.SESSION_USER.userType == 'PARENT' }">?studentUserId=${studentId }</c:if>">${configs["front.workspace.netclass.live"].textContent }</a>
	                    </c:if>
              		 </c:if>
              		 
                    <c:if test="${sessionScope.SESSION_USER.userType == 'TEACHER' 
                    					or sessionScope.SESSION_USER.userType == 'STUDENT'
                    					or sessionScope.SESSION_USER.userType == 'AREA_USR'
                    					or sessionScope.SESSION_USER.userType == 'SCHOOL_USR'}">
         					<c:if test="${type == 'ONLINE_CLASS'}">
	                    		<a class="nav_item <c:if test="${submenu=='HISTORY_BROADCAST'}">nav_item_clicked</c:if>" href="${root }/monitor/broadcast/browser.do?broadcastType=HISTORY">${configs["front.workspace.olclass.replay"].textContent }</a>
         					</c:if>
         					<c:if test="${type == 'LIVE'}">
	                    		<a class="nav_item <c:if test="${submenu=='LIVE_RECORD'}">nav_item_clicked</c:if>" href="${root}/monitor/schoolNet/appointment/record/list.html">${configs["front.workspace.netclass.replay"].textContent }</a>
         					</c:if>
                   	 </c:if>
                   	 
                   	 <c:if test="${sessionScope.SESSION_USER.userType == 'TEACHER' }">
                   	 	<c:if test="${type == 'ONLINE_CLASS'}">
	                    	<a class="nav_item <c:if test="${submenu=='REMOTE_BROADCAST'}">nav_item_clicked</c:if>" href="${root }/monitor/broadcast/browser.do?broadcastType=REMOTE">${configs["front.workspace.olclass.broadcast"].textContent }</a>
                   		</c:if>
                   		<c:if test="${type == 'LIVE'}">
	                    	<a class="nav_item <c:if test="${submenu=='LIVE_REMOTE'}">nav_item_clicked</c:if>" href="${root}/monitor/schoolNet/appointment/remote/list.html">${configs["front.workspace.netclass.broadcast"].textContent }</a>
                   		</c:if>
                   	 </c:if>
                   	 <c:if test="${sessionScope.SESSION_USER.userType == 'TEACHER' 
                   	 					or sessionScope.SESSION_USER.userType == 'SCHOOL_USR'}">
                   	 	<c:if test="${type == 'ONLINE_CLASS'}">
	                   	 	<a class="nav_item <c:if test="${submenu=='CLASS_WORK'}">nav_item_clicked</c:if>" href="${root}/monitor/classWork/toScheduleDetailList.do?type=ONLINE_CLASS">${configs["front.workspace.olclass.task"].textContent }</a>
	                    </c:if>
	                    <c:if test="${type == 'LIVE'}">
	                   	 	<a class="nav_item <c:if test="${submenu=='CLASS_WORK'}">nav_item_clicked</c:if>" href="${root}/monitor/classWork/toLiveList.do?type=LIVE">${configs["front.workspace.netclass.task"].textContent }</a>
	                    </c:if>
	                    	
                    </c:if>
                    
                   	<c:if test="${sessionScope.SESSION_USER.userType == 'AREA_USR' 
                   						or sessionScope.SESSION_USER.userType == 'SCHOOL_USR'}">
                   		<c:if test="${type == 'ONLINE_CLASS'}">			
                   			<a class="nav_item <c:if test="${submenu=='ONLINE_CLASS_TOUR'}">nav_item_clicked</c:if>" href="${root}/monitor/classroom/tourIndex.html">${configs["front.workspace.olclass.tour"].textContent }</a>
                   		</c:if>
                   		<c:if test="${type == 'LIVE'}">			
                   			<a class="nav_item <c:if test="${submenu=='LIVE_TOUR'}">nav_item_clicked</c:if>" href="${root}/monitor/schoolNet/appointment/tour/list.html">${configs["front.workspace.netclass.tour"].textContent }</a>
                   		</c:if>
                   	</c:if>
                   	<c:if test="${sessionScope.SESSION_USER.userType == 'TEACHER'}" >
	               		<c:if test="${type == 'PREPARELESSON'}">			
	               			<a class="nav_item <c:if test="${submenu=='SHOW_PREPARE_LESSON'}">nav_item_clicked</c:if>" href="${root}/prepare/lessonPlan/lessonPlanList.html">${configs["front.workspace.preparelesson.preparelesson"].textContent }</a>&nbsp;
	               			<a class="nav_item <c:if test="${submenu=='SHOW_PREPARE_IMAGE'}">nav_item_clicked</c:if>" href="${root}/prepareImage/showImage.html">${configs["front.workspace.preparelesson.prepareimage"].textContent }</a>
	               		</c:if>
	               	</c:if>
	               	<c:if test="${sessionScope.SESSION_USER.userType == 'TEACHER'}" >
	               		<c:if test="${type == 'SCHEDULE'}">			
	               			<a class="nav_item <c:if test="${submenu=='personalSchedule'}">nav_item_clicked</c:if>" href="${root}/myHome/toSchedule.html?scheduleType=personalSchedule">${configs["front.workspace.schedule.personal"].textContent }</a>&nbsp;
	               			<a class="nav_item <c:if test="${submenu=='classSchedule'}">nav_item_clicked</c:if>" href="${root}/myHome/toSchedule.html?scheduleType=classSchedule">${configs["front.workspace.schedule.class"].textContent }</a>
	               		</c:if>
	               	</c:if>
                </nav>
            </li>
        </ul>
    </div>
</div>
