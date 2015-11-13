<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.codyy.commons.HostConfig"%>
<%@page import="com.codyy.commons.context.SpringContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String baseUserId=(String)request.getAttribute("baseUserId");
	if(StringUtils.isEmpty(baseUserId)){
		baseUserId=request.getParameter("baseUserId");
	}
	request.getSession().setAttribute("baseUserId", baseUserId);
%>
<c:if test="${empty baseUserId}">
	<c:set var="baseUserId" value="${sessionScope.SESSION_USER.userId }"/>
</c:if>
<div class="header_down_wrap">
    <div class="header_down_inner_wrap">
        <div class="header_down_inner_wrap">
	        <div class="_header">
	            <div class="title_wrap_p"> 
		            <div class="logo_s">
			            <c:choose>
			            	<c:when test="${baseUserId==SESSION_USER.userId}">
			            		<a href="<%=SpringContext.getBean(HostConfig.class).getBase()%>/personalInfo/index.html"><img id="headPic" src="" width="120" height="120"></a>
			            	</c:when>
			            	<c:otherwise>
			            		<img id="headPic" src="" width="120" height="120">
			            	</c:otherwise>
		            	</c:choose>
		            </div>
		            <div class="title_s">
		            	<span id="parentName"></span>
		            	<div id="sch" class="w380 clearfix" >
		            		
		            	</div>
		            	<p id="cls">
		            		
		            	</p>
		            </div>
	            </div>
	            <c:if test="${baseUserId==SESSION_USER.userId}">
					<div class="my_use" id="my_use">
						<div id="stu" class="my_use_img" >
							
						</div>
						<input type="hidden" id="studentId" value="${studentId}"/>
						<ul id="yylb" class="myuse_ul">
							
						</ul>
					</div>
				</c:if>
	        </div>
	    </div>
	</div>
</div>
<div class="w1200 clearfix marginauto mt70">
  	<ul class="weibo_nav mb30 clearfix">
		<li><a id="navMain" href="${HOME_PATH}/myHome/PARENT/index/${baseUserId}.html" class="visited">个人主页</a></li>
		<li><a id="navMiblog" href="${HOME_PATH}/home/dynamic/toHomeDynamicIndex.html?baseUserId=${baseUserId}&userType=PARENT">微博</a></li>
		<li><a id="navBlog" href="${HOME_PATH}/blog/toFirstBlog.html?visitedUserId=${baseUserId}&userType=PARENT">博文</a></li>
		<c:if test="${SESSION_USER.userType=='PARENT'}">
			<li><a id="navGroup" href="<%=SpringContext.getBean(HostConfig.class).getCommunity()%>/group/toGroupList.html?baseUserId=${baseUserId}">圈组</a></li>
		</c:if>
		<!-- <li><a id="photo" href="javaScript:;">相册</a></li>
		<li><a id="topic" href="javaScript:;">话题</a></li> -->
		<%-- <li><a id="prepare" href="${HOME_PATH}/prepare/lessonPlan/tea/browser/${baseUserId}.html">个人备课</a></li> --%>
		<li><a id="navResource" href="${HOME_PATH}/resource/resourceList.html">优课资源</a></li>
		<!-- <li><a id="question" href="javaScript:void();">问答</a></li> -->
	</ul>
</div>
<script>
var projectMap = {
		"onlineclass.id":"<%=SpringContext.getBean(HostConfig.class).getOnlineClass()%>",
		"liveclass.id":"<%=SpringContext.getBean(HostConfig.class).getOnlineClass()%>",
		"prepare.lession.id":"<%=SpringContext.getBean(HostConfig.class).getMeeting()%>",
		"interact.lession.id":"<%=SpringContext.getBean(HostConfig.class).getMeeting()%>",
		"evaluation.meeting.id":"<%=SpringContext.getBean(HostConfig.class).getEvaluation()%>",
		"video.meeting.id":"<%=SpringContext.getBean(HostConfig.class).getMeeting()%>",
		"information.id":"<%=SpringContext.getBean(HostConfig.class).getBase()%>",
		"resource.id":"<%=SpringContext.getBean(HostConfig.class).getResource()%>",
		"statistic.id":"<%=SpringContext.getBean(HostConfig.class).getOnlineClass()%>",
		"basicInfo.id":"<%=SpringContext.getBean(HostConfig.class).getBase()%>",
		"prepare.lesson.plan.id":"<%=SpringContext.getBean(HostConfig.class).getEvaluation()%>",
		"onlineedit.id":"<%=SpringContext.getBean(HostConfig.class).getResource()%>",
		"rethink.id":"<%=SpringContext.getBean(HostConfig.class).getEvaluation()%>",
		"announcement.id":"<%=SpringContext.getBean(HostConfig.class).getBase()%>",
		"home":"${HOME_PATH}",
		"base":"<%=SpringContext.getBean(HostConfig.class).getBase()%>",
		"evaluation":"<%=SpringContext.getBean(HostConfig.class).getEvaluation()%>",
		"usercenter":"<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>"
		
};
	$(function(){
		$(".weibo_nav li").on("click","a",function(){
			if(!$(this).hasClass("visited")) {
				$(this).addClass("visited").parent(".weibo_nav li").siblings().find("a").removeClass("visited");
			}
		});
		setVisited();
		getParentInfo();
		getStudentByParent();
	});
	
	function setVisited(){
		var href = window.location.href;
		var start = href.lastIndexOf("/")+1;
		var end = href.lastIndexOf(".");
		var page = href.substring(start, end);
		var chatSplit = href.split('toChatting');
		$(".weibo_nav").find("a").removeClass("visited");
		if("toDynamicIndex"==page||chatSplit.length==2||"toMyFans"==page||"toMyFriends"==page||"toMessageList"==page){
			$("#navMiblog").addClass("visited");
		}
		else if("toFirstBlog"==page||"editPublishBlog"==page||"toWriteBlog"==page||"blogDetail"==page){
			$("#navBlog").addClass("visited");
		}
		else if("toGroupList"==page){
			$("#navGroup").addClass("visited");
		}
		else if("resourceList"==page){
			$("#navResource").addClass("visited");
		}
		else{
			$("#navMain").addClass("visited");
		}
	}
	
	// 家长信息
	function getParentInfo(){
		var parentId="${baseUserId}";
		var currentUserId="${SESSION_USER.userId}";
		var url="${HOME_PATH}/myHome/getParentSelfInfo.do?callback=?";
		$.get(url,{parentUserId:parentId},function(data){
			$("#headPic").attr("src","${HOME_PATH}/images/"+data.headPic);
			$("#parentName").text(data.realname);
		},"jsonp");
	}
	
	// 根据家长找学生
	function getStudentByParent(){
		var parentId="${baseUserId}";
		var url="${HOME_PATH}/myHome/getParentChildrenListInfo.do?callback=?";
		$.get(url,{parentUserId:parentId},function(data){
			var schHtml = "";// 学校
			var clsHtml = "";// 班级
			var stuHtml = "";// 学生
			var schArray = new Array();
			var clsArray = new Array();
			$.each(data,function(i,o){
				if($.inArray(o.clsSchoolId,schArray)==-1){
					schArray.push(o.clsSchoolId);
					schHtml+='<a href="'+o.hostDomain+'/index.html" target="_blank"><i title="'+o.schoolName+'" class="fl mr20 ellipsis w150" style="color:#000">'+o.schoolName+'</i></a>';
				}
				if($.inArray(o.baseClassId,clsArray)==-1){
					clsArray.push(o.baseClassId);
					clsHtml+='<a href="${HOME_PATH}/space/classroom/index/'+o.baseClassId+'.html"><i class="mr20" style="color:#000">'+o.classlevelName+'('+o.baseClassName+')</i></a>';
				}
				stuHtml+='<a id="'+o.baseUserId+'" href="javascript:;" onclick="getMenu(this,\''+o.baseUserId+'\',\''+o.clsSchoolId+'\',\''+parentId+'\')"><img src="${HOME_PATH}/images/'+o.headPic+'" alt="" class="fl tou_img mr10" title="'+o.realname+'"/></a>';
			});
			$("#sch").html(schHtml);
			$("#cls").html(clsHtml);
			$("#stu").html(stuHtml);
			// 加载我的应用
			var studentId=$("#studentId").val();
			if(studentId==""){
				$('#stu').find("img").first().parent().click();
			} else{
				$.each(	$('#stu').find("a"),function(i,o){
					if(studentId==o.id){
						$(o).click();
					}
				});
			}
		},"jsonp");
	 }
	
	//我的应用
	function getMenu(obj,baseUserId,schoolId,parentId){
		var url="${HOME_PATH}/myHome/getMenu.do?callback=?";
		$.get(url,{baseUserId:baseUserId,schoolId:schoolId,parentId:parentId},function(data){
			var html='';
			var count=0;
			$.each(data,function(i,o){
				if(o.baseMenuId == "prepare.lesson.plan.id" || o.baseMenuId == "rethink.id" || o.baseMenuId == "onlineedit.id"
					|| o.baseMenuId == "prepare.lession.id" || o.baseMenuId == "interact.lession.id" || o.baseMenuId == "evaluation.meeting.id"
					|| o.baseMenuId == "video.meeting.id" || o.baseMenuId == "basicInfo.id" || o.baseMenuId == "multimedia.id" || o.baseMenuId == "multimedia.id"
					|| o.baseMenuId=="announcement.id"||o.baseMenuId=="information.id"||o.baseMenuId=="statistic.id"){
					return true;
				}
				html+='<li class="myuse_li01 "><a href="'+projectMap[o.baseMenuId]+o.url+'"><img src="${HOME_PATH}/images/'+o.imagePath+'" alt=""><span>'+o.menuName+'</span></a></li>';
				if(count==3){
					return false;
				}
				count++
			});
			html+='<li id="gd_li" class="myuse_li01 use_li_last"><a href="${HOME_PATH}/myHome/toMyApplicationConfig.html?studentId='+baseUserId+'"><img src="${PUBLIC_PATH}/public/img/workplace/myuse_11.png" alt=""><span>更多</span></a></li>';
			$("#yylb").html(html);
			$("#studentId").val(baseUserId);
			var studentName=$(obj).find("img").attr("title");
			$("#studentId").attr("lang",studentName);
			$("#studentName").text($("#studentId").attr("lang"));
			$("#stu").find("img").removeClass("Timg_visited");
			$(obj).find("img").addClass("Timg_visited");
			if(typeof reloadConfig=="function"){
				reloadConfig();	
			}
		},"jsonp");
	}
</script>