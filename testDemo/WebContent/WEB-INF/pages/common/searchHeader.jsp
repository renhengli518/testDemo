<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navigation">
	<div class="w1200 marginauto navWrap">
		<span class="navTabs clearfix">
			<c:forEach items="${sessionScope.homePageMenu }" var="menu">
				<a href="${root }/${menu.url}" id="${menu.baseHomePageMenuId }">${menu.menuName }</a>
			</c:forEach>
		</span>
		<span class="gn-city">${selectedAreaName }</span>
		<c:if test="${empty sessionScope.SESSION_USER}"><button class="btn gn-toplogin fr" id="topLoginBtn">登录</button></c:if>
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
	<script>
		$("#topLoginBtn").click(function(){
			window.loginWin = Win.open({
				id : "loginBox",
				title : "",
				width : 550,
				mask : true,
				html : $id("loginBoxBody").value
			});
			new BasicCheck({
				form : $id("loginForm"),
				ajaxReq : function(){
					$.post("${root}/user/Login.html", {
						username:$("#username").val(), 
						password:$("#password").val(),
						rember: $("#loginRember")[0].checked
					}, function(r){
						if (r.result){
							loginWin.close();
							window.location.reload();
						} else {
							if (r.code == 1){
								Win.alert("用户名或密码错误!");
							} else if (r.code == 2){
								Win.alert("用户已经被锁定!");
							} else if(r.code == 3) {
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
	</script>

<script type="text/javascript">
if('${menuLoginStatus}'){
	$jsonp('${menuLoginStatus}', '', function(result){
		if (result == 'false'){
			window.location = '${menuLogout}';
		}
	});
}
</script>