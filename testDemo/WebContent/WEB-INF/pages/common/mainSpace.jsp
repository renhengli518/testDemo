<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.codyy.commons.CommonsConstant" %>
<%@ page import="com.codyy.commons.context.SpringContext" %>
<%@ page import="com.codyy.commons.HostConfig" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
.coffee_nav li a{padding:5px 28px;}
.coffee_nav li a:hover, .coffee_nav li a.visited {border-bottom: 2px solid #7e532c;color: #7e532c;font-weight: bold;}
</style>
<c:if test="${empty baseUserId}">
	<c:set var="baseUserId" value="${sessionScope.SESSION_USER.userId }"/>
</c:if>
<div class="header_down_wrap header_dw2">
    <div class="header_down_inner_wrap">
        <div class="_header">
            <div class="title_wrap_p">
                
            </div>
			<div class="my_use" id="my_use" >
				<p class="myuse_p">我的应用</p>
				<ul id="yylb" class="myuse_ul">
					<li id="gd_li" class="myuse_li01 use_li_last">
						<a href='${sessionScope.SESSION_USER.homeHost}/myHome/toMyApplicationConfig.html'><img src="${PUBLIC_PATH}/public/img/workplace/myuse_11.png" alt=""><span>更多</span></a>
					</li>
				</ul>
			</div>
        </div>
    </div>
</div>

<div id="navbar" class="w1200 clearfix marginauto mt70 mb20">
	<ul class="weibo_nav mb30 clearfix">
		<li><a id="mainPage" href="javaScript:;" class="visited">个人主页</a></li>
		<li><a id="miblog" href="javaScript:;">微博</a></li>
		<li><a id="blog" href="javaScript:;">博文</a></li>
		<li><a id="prepare" href="javaScript:;">个人备课</a></li>
		<!-- <li><a id="photo" href="javaScript:;">相册</a></li>
		<li><a id="topic" href="javaScript:;">话题</a></li>
		<li><a id="resource" href="javaScript:;">优课资源</a></li> -->
	</ul>
</div>
<script type="text/javascript">

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
		"home":"${HOME_PATH}",//"${sessionScope.hostDomain}",
		"base":"<%=SpringContext.getBean(HostConfig.class).getBase()%>",
		"evaluation":"<%=SpringContext.getBean(HostConfig.class).getEvaluation()%>",
		"usercenter":"<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>",
		"schedule.id":"${sessionScope.hostDomain}",
		"questionlib.id":"<%=SpringContext.getBean(HostConfig.class).getOnlinetest()%>",
		"test.id":"<%=SpringContext.getBean(HostConfig.class).getOnlinetest()%>"
		
};

var navigationBar ={
		"mainPage":projectMap["usercenter"]+"/toUserIndex/${baseUserId}.html",//主页
		"miblog":projectMap["home"]+"/home/dynamic/toHomeDynamicIndex.html?baseUserId=${baseUserId}",//微博
		"blog":projectMap["home"]+"/blog/toFirstBlog.html?visitedUserId=${baseUserId}",//博文
		"photo":"",//相册
		"topic":"",//话题
		"resource.id":"",//优课资源  resource.id
		"prepare.lesson.plan.id":projectMap["home"]+"/prepare/lessonPlan/tea/browser/${baseUserId}.html",//个人备课 prepare.lesson.plan.id
	
};

function getSchoolMenuForSpace(baseUserId){
	$.ajax({
		url: projectMap["home"]+'/myHome/schoolMenuForSpace.do',
		data:{baseUserId : baseUserId},
		type:'get',
		dataType:'jsonp',
		jsonpCallback:'SchoolMenuForSpace',
		cache: false,
		async: false,
		success:function(data){
			var html = "";
			/* $.each(data,function(i,item){
				switch (item.menuI){
					 case '微博' :
						html += item.menuName;
					case '博文' :
						html += item.menuName;
					case '相册' :
						html += item.menuName;
					case '话题' :
						html += item.menuName;
					case 'resource.id' :
						html += item.menuName;
					case 'prepare.lesson.plan.id' :
						html += '<li><a id="prepare" href="'+navigationBar[+item.menuI+]+'">'+item.menuName+'</a></li>'; 
				}
			}); */
			//TODO 添加导航
			
		}
		
	})
}

var baseUserId = "${baseUserId}";
//获取被访问者ID
	function getVisitedId(){
		var params = getParams();
		console.log(params);
		var uId = params["baseUserId"]||params["visitedUserId"];
		return uId;
	}
$(document).ready(function() {
	if(baseUserId==null){
		baseUserId = getVisitedId();
	}
	//暂时使用该导航条
	$.ajax({
		url : '${HOME_PATH}/myHome/getBaseUserInfoById.do',
		data : {
			baseUserId : baseUserId
		},
		type : 'get',
		dataType : 'jsonp',
		jsonp : 'callback',
		jsonpCallback : 'idealUserInfo',
		cache : false,
		success : function(data) {
			var userType = data.userType;
			showUserInfo(data.userType);
			$("#mainPage").attr("href", navigationBar["mainPage"]);
			$("#miblog").attr("href",navigationBar["miblog"]+"&userType="+userType );
			$("#blog").attr("href", navigationBar["blog"]+"&userType="+userType);
			$("#prepare").attr("href", navigationBar["prepare.lesson.plan.id"]+"?userType="+userType);
		}
	});
	//显示菜单导航栏
	showMenuBar();
	//获取应用列表
	selectSchoolMenuListByUserId(baseUserId);	 
	//getSchoolMenuForSpace(baseUserId);
});

//显示菜单导航栏
function showMenuBar() {
	$.ajax({
		url : projectMap["home"]+'/myHome/selectSchoolMenuListByUserId.do',
		data : {
			baseUserId : '${baseUserId}'
		},
		success : function(data) {
			var html = '';
			//是否显示个人备课等菜单
			var prepare = false;
			for(var i = 0; i < data.length; i++) {
				if(data[i].baseMenuId == 'prepare.lesson.plan.id') {
					$('#prepare').html(data[i].menuName);
					prepare = true;
				}
			}
			
			if(!prepare) {
				$('#prepare').hide();
			}
		},
		type : 'get',
		dataType : 'jsonp',
		jsonp : 'callback',
		jsonpCallback : 'menus',
		cache : false
	});
}

function showUserInfo(userType) {

	// 根据baseUserId 获取个人资料信息
	if(userType == 'TEACHER') {
		//获取教师信息
		getTeacherInfo(baseUserId);
	} else if (userType == 'STUDENT'){
		$("#prepare").hide();
		//获取学生信息
		getStudentInfo(baseUserId);
	}
}

//获取老师信息
function getTeacherInfo(baseUserId) {
	
	$.ajax({
		url:projectMap["home"]+"/myHome/getTeacherInfoForHomePage.do",
		data:{"baseUserId" : baseUserId},
		type:'get',
		dataType:'jsonp',
		jsonpCallback:'TeacherInfo',
		cache: false,
		async: false,
		success:function(data) {
			if (!data) {
				return;
			}
			selfInfo = data;
			var currentUser = "${SESSION_USER.userId}";
			var html = '';
			if (currentUser == baseUserId) {
				html += '<div class="logo_s"><a href="'+projectMap["base"]+'/personalInfo/index.html"><img src="'+projectMap["home"]+'/images/' + selfInfo.HEADPIC + '" width="120" height="120"></a></div>' + '<div  class="title_s"><span>' + selfInfo.REALNAME;
				html += '<a href="'+projectMap["base"]+'/personalInfo/index.html"><img src="${PUBLIC_PATH}/public/img/workplace/bj_title.png"></a>';
			} else {
				html += '<div class="logo_s"><img src="'+projectMap["home"]+'/images/' + selfInfo.HEADPIC + '" width="120" height="120"></div>' + '<div  class="title_s"><span>' + selfInfo.REALNAME;
			}
			html += '</span><i>';
			if (selfInfo.SCHOOLNAME) {
				if (selfInfo.SCHOOLNAME.length >= 10) {
					html += "<i title='" + selfInfo.SCHOOLNAME + "'>" + selfInfo.SCHOOLNAME.substring(0, 9) + "</i>";
				} else {
					html += "<i title='" + selfInfo.SCHOOLNAME + "'>" + selfInfo.SCHOOLNAME + "</i>";
				}
			}
			html += '</i>';
			html += '<div style="font-size: 16px;font-style: normal;margin-top:20px;">';
			
			for(var i = 0; i < data.clsRoomList.length; i++) {
				selfInfo = data.clsRoomList[i];
				html += '<a href="'+projectMap["home"]+'/space/classroom/index/'+selfInfo.baseClassId+'.html" style="margin-right:10px;cursor:pointer;">'+ selfInfo.classlevelName+selfInfo.baseClassName+'</a>';
			}
			html += '<div></div>';
			$(".title_wrap_p").append(html);
		}
	});
}

//获取学生信息
function getStudentInfo(baseUserId){
	
	$.ajax({
		url:projectMap["home"]+"/myHome/getStudentSelfInfo.do",
		data:{"baseUserId" : baseUserId},
		type:'get',
		dataType:'jsonp',
		jsonpCallback:'studentInfo',
		cache: false,
		async: false,
		success:function(data) {
			if (data) {
				selfInfo = data;
				baseClassId = selfInfo.baseClassId;
				var currentUser = "${SESSION_USER.userId}";
				var html = '';
				html += '<div class="logo_s"><img src="${root}/images/' + selfInfo.headPic + '" width="120" height="120"></div>' + '<div  class="title_s title_stu"><span>' + selfInfo.realname;
				if (currentUser == baseUserId) {
					$("#resource").hide();
					html += '<a href="'+projectMap["base"]+'/personalInfo/index.html"><img src="${PUBLIC_PATH}/public/img/workplace/bj_title.png"></a>';
				}
				html += '</span>';
				if (selfInfo.schoolName) {
					html += '<i><a href="'+projectMap["home"]+'/index.html" id="schoolName">' + selfInfo.schoolName + '</a></i><br />';
				}
				if (selfInfo.baseClassName) {
					html += '<i><a href="'+projectMap["home"]+'/space/classroom/index/'+selfInfo.baseClassId+'.html" id="className">' + selfInfo.classlevelName+selfInfo.baseClassName + '</a></i>';
				}
				html += '</div>';

				$(".title_wrap_p").append(html);
			} else {
				// not get self info
			}
		}
	});		
}
//获取应用列表
function selectSchoolMenuListByUserId(baseUserId) {
	var currentUser = "${SESSION_USER.userId}";
	if (currentUser == baseUserId) {
		$("#my_use").show();
		$("#prepare").hide();
		$.ajax({
			url:projectMap["home"]+"/myHome/selectDefaultMenuListByUserId.do",
			data:{"baseUserId" : baseUserId},
			type:'get',
			dataType:'jsonp',
			jsonpCallback:'appInfo',
			cache: false,
			async: false,
			success:function(data) {
				var html1 = '';
				if (data && data.length > 0) {
					data.forEach(function(value, index) {
						// 学生展示默认应用
						if (value.baseMenuId == "onlineclass.id") {
							value.url = "/monitor/broadcast/browser.html?broadcastType=LIVE";
						}
						if (index == data.length - 1) {
							html1 += '<li class="myuse_li01 use_li_last"><a  href="' + projectMap[value.baseMenuId] + value.url + '"><img src="${root}/images/' + value.imagePath + '" alt=""><span >' + value.menuName + '</span></a></li>';
						} else {
							html1 += '<li class="myuse_li01 "><a  href="' + projectMap[value.baseMenuId] + value.url + '"><img src="${root}/images/' + value.imagePath + '" alt=""><span >' + value.menuName + '</span></a></li>';
						}
						$(".more_block").show();
					});
					$("#yylb").prepend(html1);
				} else {
					//noApp
				}
			}
		});
	} else {
		$("#my_use").remove();//访客游客不展示应用
	};
}
</script>

	