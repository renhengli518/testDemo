<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.codyy.commons.CommonsConstant" %>
<%@ page import="com.codyy.commons.sso.SessionUser" %>
<%@ page import="com.codyy.commons.utils.RedisCacheUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.codyy.commons.HostConfig" %>
<%@ page import="com.codyy.commons.context.SpringContext" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<!-- jspfilepath:<%=application.getRealPath(request.getRequestURI())%> -->
	<meta charset="utf-8"/>
	<meta name="renderer" content="webkit">
	<%-- <title><%=com.codyy.commons.bean.TitleBean.readTitle() %></title> --%>
	<%
		String title = null;//网站title
		String baseAreaId = null, schoolId = null;//区域ID/学校ID
		String configName = "front.homepage.sys.title";//网站title配置名称
		
		Map<String, String> configTitle = null;//网站title配置信息
		
		//获取Home模块切换的学校ID
		schoolId = (String)session.getAttribute("selectedSchoolId");
		if (StringUtils.isNotEmpty(schoolId)) {
			configTitle = RedisCacheUtils.getFrontSchoolConfigData(schoolId, configName);
		} else {
			//获取Home模块切换的区域ID
			baseAreaId = (String)session.getAttribute("selectedAreaId");
			if (StringUtils.isNotEmpty(baseAreaId)) {
				configTitle = RedisCacheUtils.getFrontAreaConfigData(baseAreaId, configName);
			}
		}
		
		//不是在门户(Home)模块
		if (configTitle == null) {
			//从session中获取登录用户
			SessionUser userForTitle = (SessionUser)session.getAttribute(CommonsConstant.SESSION_USER);
			if (userForTitle != null) {
				schoolId = userForTitle.getSchoolId();
				if (StringUtils.isNotEmpty(schoolId)) {
					configTitle = RedisCacheUtils.getFrontSchoolConfigData(schoolId, configName);
				} else {
					baseAreaId = userForTitle.getAreaId();
					//不对areaID是否存在校验，如果不存在，则取redis中的默认值
					configTitle = RedisCacheUtils.getFrontAreaConfigData(baseAreaId, configName);
				}
			}
		}

		if (configTitle!=null) {
			title = configTitle.get("textContent");
		}
	%>
	<title><%=title == null ? "教育资源公共服务平台" : title %></title>
	<%
		String contextPath = request.getContextPath();
		if (contextPath.equals("/"))
			request.getSession().setAttribute("root", "");
		else
			request.getSession().setAttribute("root", contextPath);
		request.setAttribute("mailReg", "^[\\w\\.]+@[a-zA-Z_0-9]+?\\.[a-zA-Z]{2,3}$");
		request.setAttribute("userRealNameReg", "^[\\u4E00-\\u9FA5]+$");
		request.setAttribute("telephoneReg", "^(0[1-9]\\d{1,2}\\-)?[1-9]\\d{6,7}$");
		request.setAttribute("mobileReg", "^1[3|4|5|8][0-9]\\d{8}$");
		request.setAttribute("userIdReg", "^[0-9a-zA-Z]{6,20}$");
		request.setAttribute("passwordReg", "^.{6,18}$");
		String str = request.getHeader("User-Agent");
		if(str.indexOf("Android")>0 || str.indexOf("iPad")>0 || str.indexOf("Macintosh")>0){
			request.setAttribute("systype", "pad");
		}else{
			request.setAttribute("systype", "pc");
		}
		if(str.indexOf("Android")>0){
			request.setAttribute("phoneType", "Android");
		}else if(str.indexOf("iPad")>0 || str.indexOf("Macintosh")>0){
			request.setAttribute("phoneType", "ios");
		}else{
			request.setAttribute("phoneType", "all");
		}
	%>
	<c:choose>
		<c:when test="${not empty sessionScope.hostDomain }">
			<c:set var="LOCAL_PATH" value="${sessionScope.hostDomain}"/>
			<c:set var="HOME_PATH" value="${sessionScope.hostDomain}"/>
		</c:when>
		<c:otherwise>
			<c:set var="LOCAL_PATH" value="<%=SpringContext.getBean(HostConfig.class).getLocal()%>"/>
			<c:set var="HOME_PATH" value="${sessionScope.SESSION_USER.homeHost}"/>
		</c:otherwise>
	</c:choose>
	<c:set var="PUBLIC_PATH" value="<%=SpringContext.getBean(HostConfig.class).getPublic()%>"></c:set>
	<c:if test="${isUE == 1}">
		<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/resetUE.css"/>
	</c:if>
	<c:if test="${isUE != 1}">
		<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/reset.css"/>
	</c:if>
	<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/workplace.css"/>
	<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/workflat.css"/>
	<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
	<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/space.css">
	<script src="${PUBLIC_PATH}/public/js/jquery.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/js/extend.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/js/litewin.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/js/common.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/js/bingo.min.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/js/photoScroll.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/js/jquery.cxscroll.js"></script>
	<link type="image/x-icon" rel="shortcut icon" href="${PUBLIC_PATH}/public/img/common/codyy.ico" />
	<script src="${PUBLIC_PATH}/public/js/splitpage.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/js/basiccheck.js" type="text/javascript"></script>
	<script src="${PUBLIC_PATH}/public/calendar/WdatePicker.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script type="text/javascript">
		(function() {
			var e = "abbr,article,aside,audio,canvas,datalist,details,dialog,eventsource,figure,footer,header,hgroup,mark,menu,meter,nav,output,progress,section,time,video".split(',');
			var l= e.length;
			while (l--){
				document.createElement(e[l]);
			} 
		})();
	</script>
	<![endif]-->
	<script type="text/javascript"><%-- 检测session是否过期，如果过期，则跳转到登录页面 --%>
		var ROOT = '${root}';
		var	API_PATH = '${LOCAL_PATH}';  //接口请求路径
		var PUBLIC_PATH = '${PUBLIC_PATH}';
		var MEETING_PATH = '<%=SpringContext.getBean(HostConfig.class).getMeeting()%>';
		var loginFlag = "${not empty sessionScope.SESSION_USER}" == "true" ? true:false;
		var noRecord = '抱歉，未查询到相关记录!';
		var noCssRecord = '<div align="center"><h7 class="cyan">抱歉，未查询到相关记录</h7></div>';
		var noBlogRecord = '<div class="module_emptyres"></div>';
		$(document).on('ajaxComplete', function (jqXHR, xhr) {
			var responseText = xhr.responseText;
			if (responseText && responseText.indexOf) {
				var start = responseText.indexOf("<script>top.location='");
				if(start>-1 && start < 10){
					top.location="${sessionScope.SESSION_USER.homeHost}/index.html";
				}
			}
		});
	</script>
