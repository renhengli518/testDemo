<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.codyy.commons.HostConfig" %>
<%@ page import="com.codyy.commons.context.SpringContext" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.gn-area .gn-nowCity{border-left:1px solid #34a150;padding:0 8px}
.gn-area.active{border:1px solid #34a150}
.gn-area.active .gn-nowCity{border:none}
</style>
<div class="header_up_wrap">
    <div class="header_up_inner_wrap">
    
        <ul class="_header">
            <li class="header_item header_item_title">
                <span class="header_title"><font class="weight">教育资源</font><font>公共服务平台</font></span>
            </li>
			<c:if test="${hasAreaSelection}">
				<li class="header_item header_item_title" style="width:110px;">
					<span class="gn-area gn-area1" style='height:44px;line-height:55px;'>
						<span class="gn-nowCity" title="${sessionScope.selectedName }" style='color:black;background-image:none'>${fn:substring(sessionScope.selectedName,0,4) }</span>
						
						<div class="gn-citySearch" id="changeCurArea">
							${sessionScope.selectedAreaHtml }
							<button class="btn btnGreen ml10" id="changeArea">确定</button>
						</div>
					</span>
				</li>
			</c:if>
            <li class="header_item header_item_nav">
                <ul class="nav" id="menuList">
                	<c:forEach items="${sessionScope.homePageMenu}" var="menuList">
                		<a class="nav_item" href="${root}/${menuList.url}">${menuList.menuName}</a>
                	</c:forEach>
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
           
           <c:choose>
           		<c:when test="${sessionScope.SESSION_USER eq null}">
           			<!-- 添加一个空的li 占位 -->
           			<li class="header_item header_item_user "> 
		            </li>
	        		<li class="header_item header_item_quit">
	              		<a class="quit" href="javascript:void(0);" id="topLoginBtn">登录</a>
	          		</li>
           		</c:when>
           		<c:otherwise>
	           		<li class="header_item header_item_user "> 
	           			<a href="<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>/toUserIndex/${sessionScope.SESSION_USER.userId}.html">
		                <div class="user_center mc_kzkd">
		                    <span title="${sessionScope.SESSION_USER.realname}的空间">${sessionScope.SESSION_USER.realname}的空间</span>
		                </div>
		                </a>
		            </li>
		            <li class="header_item header_item_quit">
		                <a class="quit" href="<%=SpringContext.getBean(HostConfig.class).getUserCenter()%>/user/Logout.html">退出</a>
		            </li>
           		</c:otherwise>
           </c:choose>
			
        </ul>
    </div>
</div>
<textarea id="loginBoxBody" class="newBoxBody hidden">
	<form id="loginForm">
		<ul class="commonUL">
			<li><h4>登录</h4></li>
			<li>
				 <input type="text" name="uid" id="username" class="w300" placeholder="用户名" needcheck nullmsg="请输入用户名" limit="6,18" limitmsg="用户名长度不对！" />
			</li>
			<li>
				<input type="password" name="psw" class="w300"  placeholder="密码" id="password" needcheck nullmsg="请输入密码" />
			</li>
			<li>
				<label class="fr"><input type="checkbox" name="rember" id="loginRember"/>下次自动登录（7天内有效）</label><input type="submit" class="submit btn btnBig" value="登录" />
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

function getCurrentPathName(){
	return window.location.pathname;
}
function getCurrentSearch(){
	return window.location.search;
}
//切换行政区域
$("#changeArea").click(function(){
	var areas = $("#changeCurArea select");
	var areaLength = areas.length;
	var baseAreaId = "";
	var baseAreaId1 = "";
	var value;
	var type;
	if (areaLength == 1) {
		var $selected1 = $(areas[0]);
		type = $selected1.attr("data-type");
		value = $selected1.val();
		if ("-1" == value) {
			Win.alert("请选择跳转的区域");
			return ;
		} else {
			if ("school" == type) {
				window.location.href='${root}/search/toIndex.html?schoolId='+value+'&pathName='+getCurrentPathName()+'&search='+getCurrentSearch();
			} else {
				window.location.href='${root}/search/toIndex.html?baseAreaId='+value+'&pathName='+getCurrentPathName()+'&search='+getCurrentSearch();
			}
		}
	} else {
		var $selected1 = $(areas[areaLength-1]);
		var $selected2 = $(areas[areaLength-2]);
		type = $selected1.attr("data-type");
		var val1 = $selected1.val();
		var val2 = $selected2.val();
		if ("-1" == val1) {
			if ("school" == type) {
				if (areaLength > 2) {
					if ("-2" == val2) {
						window.location.href='${root}/search/toIndex.html?baseAreaId='+$(areas[areaLength-3]).val()+'&pathName='+getCurrentPathName()+'&search='+getCurrentSearch();
					} else {
						window.location.href='${root}/search/toIndex.html?baseAreaId='+val2+'&pathName='+getCurrentPathName()+'&search='+getCurrentSearch();
					}
					
				} else {
					Win.alert("请选择跳转的区域");
					return ;
				}
			} else {
				window.location.href='${root}/search/toIndex.html?baseAreaId='+val2+'&pathName='+getCurrentPathName()+'&search='+getCurrentSearch();
			}
		} else {
			if ("school" == type) {
				window.location.href='${root}/search/toIndex.html?schoolId='+val1+'&pathName='+getCurrentPathName()+'&search='+getCurrentSearch();
			} else {
				window.location.href='${root}/search/toIndex.html?baseAreaId='+val1+'&pathName='+getCurrentPathName()+'&search='+getCurrentSearch();
			}
		}
	}
});

$("#changeCurArea").on('change','select',function(){
	var $this = $(this);
	var selectValue = $this.val();
	var $selctedOption = $this.find("option:selected");
	$this.nextAll().not("button").remove();
	if ("-1" != selectValue){
		var type = $selctedOption.attr('data-type');
		if ('selectSchool' == type) {
			buildSchoolSelectHtml($selctedOption, function (html) {
				$this.after(html);
			});
		} else if ('school' == type) {
			return;
		} else {
			buildAreaSelectHtml($selctedOption, function (html) {
				$this.after(html);
			})
		}
	}
});
function buildAreaSelectHtml($selctedOption, ck){
	var baseAreaId = $selctedOption.attr("value");
	$.post('${root}/commons/getAreaByParentId.do',{'parentId':baseAreaId},function(data){
		var html = '';
		data = data.list;
		if (data && data.length >0) {
			var areaView;
			html += '<select data-type="area" autocomplete="off"><option value="-1">请选择</option>';
			for(var i=0,j=data.length;i<j;i++) {
				areaView = data[i];
				html +='<option value="'+areaView.baseAreaId+'">'+areaView.areaName+'</option>';
			}
			html += '<option data-type="selectSchool" data-areaId="'+baseAreaId+'" value="-2">直属校</option></select>'
			ck(html);
		} else {
			$.post('${root}/commons/getSchoolListByAreaId.do',
					{'baseAreaId':baseAreaId},function(data){
						var html = '';
						html += '<select data-type="school" autocomplete="off"><option value="-1">请选择</option>';
						if (data && data.length > 0) {
							var areaView;
							for(var i = 0,j=data.length;i<j;i++){
								areaView = data[i];
								html +='<option data-type="school" value="'+areaView.schoolId+'">'+areaView.schoolName+'</option>';
							}
						}
						html +='</select>';
						ck(html);
			});
		}
	});
}

function buildSchoolSelectHtml($selctedOption, ck) {
	var baseAreaId = $selctedOption.attr("data-areaId");
	$.post('${root}/commons/getSchoolListByAreaId.do',
			{'baseAreaId':baseAreaId},function(data){
				var html = '';
				html += '<select data-type="school" autocomplete="off"><option value="-1">请选择</option>';
				if (data && data.length > 0) {
					var areaView;
					for(var i = 0,j=data.length;i<j;i++){
						areaView = data[i];
						html +='<option data-type="school" value="'+areaView.schoolId+'">'+areaView.schoolName+'</option>';
					}
					html +='</select>';
				}
				ck(html);
	});
}
	
</script>