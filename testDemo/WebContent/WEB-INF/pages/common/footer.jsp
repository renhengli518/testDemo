<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.codyy.commons.utils.RedisCacheUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	List<String> footerConfigNames = new ArrayList<String>();
	footerConfigNames.add("front.homepage.siteFiling");//网站备案号
	footerConfigNames.add("front.homepage.siteFilingUrl");//备案号链接
	footerConfigNames.add("front.homepage.companyInfo");//公司信息
	//获取前台切换的学校ID
	String footerSchoolId = (String)request.getSession().getAttribute("selectedSchoolId");
	if(footerSchoolId != null && footerSchoolId != ""){
		pageContext.setAttribute("configs", RedisCacheUtils.getFrontSchoolConfigData(footerSchoolId, footerConfigNames));
	} else {
		//获取前台切换的区域ID，如果baseAreaID也不存在，则取默认值
		String footerBaseAreaId = (String)request.getSession().getAttribute("selectedAreaId");
		pageContext.setAttribute("configs", RedisCacheUtils.getFrontAreaConfigData(footerBaseAreaId, footerConfigNames));
	}
%>
<div class="footers" style="padding-top:40px;">
  <div style="padding:2px;"><c:if test="${configs['front.homepage.siteFiling'].display == 'Y' }">${configs['front.homepage.siteFiling'].textContent }</c:if></div>
  <div style="padding:2px;"><c:if test="${configs['front.homepage.siteFilingUrl'].display == 'Y' }">${configs['front.homepage.siteFilingUrl'].textContent }</c:if></div>
  <div style="padding:2px;"><c:if test="${configs['front.homepage.companyInfo'].display == 'Y' }">${configs['front.homepage.companyInfo'].textContent }</c:if></div>
</div>