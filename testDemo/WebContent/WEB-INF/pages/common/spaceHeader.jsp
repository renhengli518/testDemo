<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.codyy.commons.HostConfig" %>
<%@ page import="com.codyy.commons.context.SpringContext" %>
<%@ page import="com.codyy.commons.utils.RedisCacheUtils" %>
<%@ page import="com.codyy.commons.CommonsConstant" %>
<%@ page import="com.codyy.commons.sso.SessionUser" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.gn-area .gn-nowCity{border-left:1px solid #34a150;padding:0 8px}
.gn-area.active{border:1px solid #34a150}
.gn-area.active .gn-nowCity{border:none}
</style>
<%
Object sessionUserSpaceHeader = request.getSession().getAttribute(CommonsConstant.SESSION_USER);
if(sessionUserSpaceHeader != null) {
	SessionUser userSpaceHeader = (SessionUser) sessionUserSpaceHeader;
	if(CommonsConstant.USER_TYPE_AREA_USER.equals(userSpaceHeader.getUserType())){
		pageContext.setAttribute("headerName", RedisCacheUtils.getFrontAreaConfigData(userSpaceHeader.getAreaId(), "front.homepage.name"));
	} else {
		pageContext.setAttribute("headerName", RedisCacheUtils.getFrontSchoolConfigData(userSpaceHeader.getSchoolId(), "front.homepage.name"));
	}
}
%>
<div class="header_up_wrap">
    <div class="header_up_inner_wrap">
        <ul class="_header">
            <li class="header_item header_item_title">
			 <h3 class="gn-logoText" style="font-weight: normal;">
			 	<c:choose>
			 		<c:when test="${not empty headerName}">
			 			<c:if test="${headerName.display eq 'Y' }">
				 			${headerName.textContent }
				 		</c:if>
			 		</c:when>
			 		<c:otherwise>
			 			互动学习平台
			 		</c:otherwise>
			 	</c:choose>
			 </h3>
            </li>
			
			<%-- <li class="header_item header_item_title" style="width:110px;">
				<c:if test="${hasAreaSelection}">
					<span class="gn-area gn-area1" style='height:44px;line-height:55px;'>
						<span class="gn-nowCity" title="${sessionScope.selectedName }" style='color:black;background-image:none'>${fn:substring(sessionScope.selectedName,0,4) }</span>
						
						<div class="gn-citySearch" id="changeCurArea">
							${sessionScope.selectedAreaHtml }
							<button class="btn btnGreen ml10" id="changeArea">确定</button>
						</div>
					</span>
				</c:if>
			</li> --%>
			
            <li class="header_item header_item_nav">
                <ul class="nav" id="menuList">
	                <c:if test="${ not empty sessionScope.homePageMenu}">
	                	<c:forEach items="${sessionScope.homePageMenu}" var="menuList">
	                		<a class="nav_item" href="${root}/${menuList.url}">${menuList.menuName}</a>
	                	</c:forEach>
	                </c:if>
                </ul>
            </li>
            <li class="header_item header_item_search">
                <span class="search_wrap" id="search_box_id">
                </span>
                <div class="search_box hide" id="searchInfo">
                    <select name="" class="search_type">
                        <option value="info">资讯</option>
                        <option value="video">视频</option>
                        <option value="doc">文档</option>
                    </select>
                    <input type="text" name="" class="search_fileName">
                    <span class="search_btn">
                    </span>
                </div>
            </li>
		    <c:if test="${empty sessionScope.SESSION_USER}">
				<!-- 添加一个空的li 占位 -->
				<li class="header_item header_item_user "> 
				</li>
				<li class="header_item header_item_quit" style="width:70px">
					<a class="quit" href="javascript:void(0);" id="topLoginBtn" style="background: url('${PUBLIC_PATH}/public/img/common/user.png') no-repeat scroll 0px center; padding-left:26px;display:inline-block;">登录</a>
				</li>
			</c:if>
			<c:if test="${not empty sessionScope.SESSION_USER}">
				<li class="header_item header_item_user "> 
					<c:if test="${sessionScope.SESSION_USER.userType eq 'AREA_USR' || sessionScope.SESSION_USER.userType eq 'SCHOOL_USR' }">
						<a href="<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>/bench/index.html">
							<div class="user_center mc_kzkd">
								<span title="${sessionScope.SESSION_USER.realname}的工作台">工作台主页</span>
							</div>
						</a>
					</c:if>
					<c:if test="${sessionScope.SESSION_USER.userType eq 'TEACHER' || sessionScope.SESSION_USER.userType eq 'STUDENT' || sessionScope.SESSION_USER.userType eq 'PARENT' }">
						<a href="<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>/toUserIndex/${sessionScope.SESSION_USER.userId}.html">
							<div class="user_center mc_kzkd">
								<span title="${sessionScope.SESSION_USER.realname}的空间">空间主页</span>
							</div>
						</a>
					</c:if>
				</li>
				<li class="header_item header_item_user "><a href="<%=SpringContext.getBean(HostConfig.class).getBase()%>/personalInfo/index.html">
	                <div class="mc_name w80" style="color:#fff;text-align:left;"><span title="${sessionScope.SESSION_USER.realname}">${sessionScope.SESSION_USER.realname}</span></div>
	               	</a>
	            </li>
				<li class="header_item header_item_quit">
					<a class="quit" href="<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>/user/Logout.html">退出</a>
				</li>
			</c:if>
        </ul>
    </div>
</div>
<textarea id="loginBoxBody" class="newBoxBody hidden">
	<form id="loginForm">
		<ul class="commonUL">
			<li>
				 <input type="text" name="uid" id="username" class="w300" placeholder="用户名" needcheck nullmsg="请输入用户名" limit="6,18" limitmsg="用户名长度不对！" />
			</li>
			<li>
				<input type="password" name="psw" class="w300"  placeholder="密码" id="password" needcheck nullmsg="请输入密码" />
			</li>
			<li>
				 <label class="fr"><input type="checkbox" name="rember" id="loginRember"/>下次自动登录（7天内有效）</label>
				 <input type="submit" class="submit btn btnBig" value="登录" />
			</li>
		</ul>
	</form>
</textarea>
<script type="text/javascript">
var userHost = "<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>";
var loginStatusUrl = userHost + "/user/LoginStatus.html?callback=?";
var loginUrl = userHost + "/user/Login.html?callback=?";
var host = "http://" + window.location.hostname;
if (window.location.port != 80 && window.location.port != ""){
	host = host + ":" + window.location.port;
}
host = host + "${root}";
var home = host + "/index.html";
var synchUrl = host + "/SessionSynch";
var logout = host + "/Logout";

$("#topLoginBtn").click(function(){
	window.loginWin = Win.open({
		id : "loginBox",
		title : "登录",
		width : 415,
		top	:303,
		mask : true,
		html : $id("loginBoxBody").value
	});
	new BasicCheck({
		form : $id("loginForm"),
		ajaxReq : function(){
			jQuery.getJSON(loginUrl, {
				username:$("#username").val(), 
				password:$("#password").val(),
				rember: $("#loginRember")[0].checked,
				home:home,
				logout:logout
			}, function(r){
				if (r.result){
					loginWin.close();
					//登录完成后使home模块与usercenter模块session同步
					window.location = synchUrl + "?url=" + encodeURIComponent(window.location);
					$(".gn-area").addClass("gn-area1");
				} else {
					if (r.code == 1){
						Win.alert("用户名或密码错误!");
					} else if (r.code == 2){
						Win.alert("用户已经被锁定!");
					} else if (r.code == 3){
						Win.alert("该学生所属班级已被删除，无法登录！");
					}
				}
			}, 'json');
		},
		warm : function(o,msg){ 
			Win.alert(msg);
		}
	});
});

var sessionStatus = true;
<c:if test="${empty sessionScope.SESSION_USER}">
	sessionStatus = false;
</c:if>
jQuery.getJSON(loginStatusUrl, {}, function(r){
	if (r.result == false){
		if (sessionStatus){
			jQuery.getJSON(logout + "?callback=?", {}, function(r){
				window.location.reload(true);
			});
		}
	} else {
		//UserCenter session有效, home模块session无效, 使home模块与UserCenter保持一致
		if (sessionStatus == false){
			window.location = synchUrl + "?url=" + encodeURIComponent(window.location);
		} else {
			//两边session都有效，检查两边randomId是否相同
			var homeRandomId = "${sessionScope.SESSION_USER.randomId}";
			console.log("center:" + r.randomId);
			console.log("home" + homeRandomId);
			if (homeRandomId != r.randomId){
				console.log("not equals, reload");
				jQuery.getJSON(logout + "?callback=?", {}, function(r){
					window.location = synchUrl + "?url=" + encodeURIComponent(window.location);
				});
			}
		}
	}
});


//搜索功能的添加
$('.search_btn').click(function(){
	var pageType = $('.search_type').val();
	var key = $('.search_fileName').val();
	window.location.href = '${root}/search/changeSearchPage.html?pageType='+pageType+"&key="+encodeURIComponent(key);
});
$(function(){
	var menuHtml =$("#menuList").html();
	if($.trim(menuHtml).length!=0){
		return;
	}
	$.ajax({
		url : '${HOME_PATH}/myHome/homeMenuList.do',
		data : {
			baseUserId : '${baseUserId}'
		},
		type : 'get',
		dataType : 'jsonp',
		jsonp : 'callback',
		jsonpCallback : 'homeMenuList',
		cache : false,
		success:function(data){
			var html = '';
			$.each(data,function(i,item){
				html += '<a class="nav_item" href="${HOME_PATH}/'+item.url+'">'+item.menuName+'</a>';
			});
			$("#menuList").html(html);
		}	
	})
});

	$(function(){//换肤
		var baseUserId = "${baseUserId}"||"";
		if(baseUserId ==""){
			baseUserId = getVisitedId();
			if(!baseUserId){
				return ;
			}
		}
		$.ajax({
			url : '${HOME_PATH}/myHome/getBaseUserInfoById.do',
			data : {
				baseUserId : baseUserId
			},
			type : 'get',
			dataType : 'jsonp',
			jsonp : 'callback',
			cache : false,
			success : function(data) {
				var userType = data.userType;
				changeSkin(userType);//根据角色变更皮肤
			}
		});
	});
</script>
<script>
	function changeSkin(userType){
		//判断角色类型，添加body class
		if (userType == "TEACHER") {
			$("#navbar>ul").toggleClass('coffee_nav');
			$("body").addClass("teacherRole");
		} else if (userType == "SCHOOL_USR") {
			$("body").addClass("schoolRole");
		} else if (userType == "PARENT"
				|| userType == "STUDENT") {
			$("body").addClass("parOrChildRole");
		} else if (userType == "AREA_USR") {
			$("body").addClass("management");
		}
	}
	//获取被访问者ID
	function getVisitedId(){
		var params = getParams();
		return params["baseUserId"];
	}
</script>

