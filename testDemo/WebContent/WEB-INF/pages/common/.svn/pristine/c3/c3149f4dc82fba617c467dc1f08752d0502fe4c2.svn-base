<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.codyy.commons.HostConfig" %>
<%@ page import="com.codyy.commons.context.SpringContext" %>
<div class="w1200 marginauto clearfix mt55">
	<div class="ft16 mb20 bd1">
		<p class="pl10 blue_lightBg ft18">我的应用
			<c:if test="${SESSION_USER.userType =='TEACHER' or SESSION_USER.userType =='STUDENT' }">
				<a href="javascript:;" class="fr cyan mr30" onclick="page.spaceShortConfig()">我的应用快捷入口配置</a>
			</c:if>
		</p>
		<ul class="pd20 clearfix bdB_dot" id ="CLASS_GROUP">
		</ul>
		<ul class="pd20 clearfix bdB_dot" id ="ACTIVITY_GROUP">
		</ul>
		<ul class="pd20 clearfix bdB_dot" id ="TEACH_GROUP">
		</ul>
		<ul class="pd20 clearfix bdB_dot" id ="RESOURCE_GROUP">
		</ul>
		<ul class="pd20 clearfix bdB_dot" id ="BASE_GROUP">
		</ul>
	</div>
	<div class="ft16 mb20 bd1">
		<p class="pl10 blue_lightBg ft18" >第三方应用</p>
		<ul class="pd20 clearfix bdB_dot no_line" id="OTHER_APP">
			
		</ul>
	</div>
</div>
<div id="APP_list" class="hidden">
	<div style="min-height:556px;">
		<p class="ft16 right">已经加入的第三方应用</p>
		<ul class="pd20 clearfix bdB1 alreadyAddApp" >
		</ul>
		<p class="ft16 mt10 right">应用库</p>
		<ul class="pd20 clearfix bdB1 no_line notAddAppDiv" >
			
		</ul>
	</div>
	<div class="bottomBtnWrap"><a href="javascript:;" class="btn btnBlue mr20" onclick= "page.addAppSave()">确定</a><a href="javascript:;" class="btn btnGray" onclick="page.closeWin()">取消</a></div>
</div>
<div id="APPLICATION_list" class="hidden">
	<div style="min-height:556px;">
		<p class="ft16 right">已加入的快捷应用</p>
		<ul class="pd20 clearfix bdB1 alreadyAddApplication" >
		
		</ul>
		<p class="ft16 mt10 right">我的应用库</p>
		<ul class="pd20 clearfix bdB1 no_line notAddApplicationDiv" >
			
		</ul>
	</div>
	<div class="bottomBtnWrap"><a href="javascript:;" class="btn btnBlue mr20" onclick= "page.addApplicationSave()">确定</a><a href="javascript:;" class="btn btnGray" onclick="page.closeWin()">取消</a></div>
</div>

<script>
(function(){
	var wJs = function(){
		return new wJs.fn.init();
	}
	//菜单与应用对应关系
	var projectMapAppPage ={
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
			"multimedia.id":"<%=SpringContext.getBean(HostConfig.class).getOnlineClass()%>",
			"group.id":"<%=SpringContext.getBean(HostConfig.class).getCommunity()%>",
			"schedule.id":"${sessionScope.hostDomain}",
			"class.member.id":"${sessionScope.hostDomain}",
			"questionlib.id":"<%=SpringContext.getBean(HostConfig.class).getOnlinetest()%>",
			"test.id":"<%=SpringContext.getBean(HostConfig.class).getOnlinetest()%>",
			"blog.id":"<%=SpringContext.getBean(HostConfig.class).getCommunity()%>",
			"online.tutor.id":"<%=SpringContext.getBean(HostConfig.class).getMeeting()%>",
			"homework.id":"<%=SpringContext.getBean(HostConfig.class).getOnlinetest()%>"
	};
		
	wJs.fn = wJs.prototype = {
		//配置快捷应用入口
		thirdAppConfig: function(){
			$.post('${root}/open/selectNotAddOpenApp.do',function(data){
				var html ="";
				if(data && data.length > 0){
					for(var i = 0;i< data.length; i++ ){
						var openApp = data[i];
						html +='<li class="fl ml40 w95 center" value="'+openApp.openAppId+'">'
								+'<img src="<%=SpringContext.getBean(HostConfig.class).getOpen()%>/images/'+openApp.iconLarge+'" alt="" class="fl img_92">'
								+'<i class="addImg fr DwImg notAddApp"></i>'
								+'<span class="ellipsis clear">'+openApp.appName+'</span>'
							+'</li>';
					}
				}
				$(".notAddAppDiv").html(html);
				
				window.appAddWin = Win.open({
					id : "APP_third",
					title : "配置第三方应用",
					width : 770,
					height:750,
					mask : true,
					html : $("#APP_list").html()
				});
			});
		},
		//绑定事件
		bindEvents: function (){
			var seft = this;
			$(document).on('click',"#APP_add",function(){
				seft.thirdAppConfig();
			});
			//取消此第三方应用
			$(document).on("click",".alreadyAddApp .deleImg",function(){
				var parentLi = $(this).parent("li");
				$(this).attr("class","addImg fr DwImg notAddApp");
				$(".notAddAppDiv").append(parentLi.clone());
				parentLi.remove();
			});
			//添加此第三方应用
			$(document).on("click",".notAddAppDiv .addImg",function(){
				$(this).attr("class","deleImg fr DwImg appNew");
				var parentLi = $(this).parent("li");
				$(".alreadyAddApp").append(parentLi.clone());
				parentLi.remove();
			});
			
			//取消此快捷入口
			$(document).on("click",".alreadyAddApplication .deleImg",function(){
				var parentLi = $(this).parent("li");
				$(this).attr("class","addImg fr DwImg ");
				$(".notAddApplicationDiv").append(parentLi.clone());
				parentLi.remove();
			});
			//添加此快捷入口
			$(document).on("click",".notAddApplicationDiv .addImg",function(){
				var areadyNum = $(".alreadyAddApplication").eq(1).find("li").length;
				if(areadyNum>=4){
					Win.alert("最多可配置4个快捷入口！");
					return;
				}
				$(this).attr("class","deleImg fr DwImg ");
				var parentLi = $(this).parent("li");
				$(".alreadyAddApplication").append(parentLi.clone());
				parentLi.remove();
			});
		},
		//点击配置应用快捷入口触发函数
		spaceShortConfig: function (){
			var userType = '${SESSION_USER.userType}';
			var param = {
					baseUserId:"${baseUserId}"
			}
			$.post('${root}/shortcut/menu/getMenuWithoutShortcut.do',param,function(data){
					var html ="";
					if(data && data.length > 0){
						for(var i = 0;i< data.length; i++ ){
							var obj = data[i];
							if(userType != "AREA_USR" && userType != "SCHOOL_USR" ){//非省市县、学校以下菜单忽略
								if(obj.baseMenuId=="announcement.id"||obj.baseMenuId=="information.id"||obj.baseMenuId=="statistic.id"||obj.baseMenuId=="basicInfo.id"){
									continue ;
								}
							}
							html +='<li class="fl ml40 w95 center" value="'+obj.baseAreaRMenuId+'">'
									+'<img src="${root}/images/'+obj.imagePath+'" alt="" class="fl img_92">'
									+'<i class="addImg fr DwImg "></i>'
									+'<span class="ellipsis clear">'+obj.menuName+'</span>'
								+'</li>';
						}
					}
					$(".notAddApplicationDiv").html(html);//我的应用
					
					$.post('${root}/shortcut/menu/getShortcutMenu.do',param,function(data){//获取已经添加的快捷入口
						var areadyAddHtml ="";
						if(data && data.length > 0){
							for(var i = 0;i< data.length; i++ ){
								var obj = data[i];
								areadyAddHtml +='<li class="fl ml40 w95 center" value="'+obj.baseAreaRMenuId+'">'
										+'<img src="${root}/images/'+obj.imagePath+'" alt="" class="fl img_92">'
										+'<i class="deleImg fr DwImg "></i>'
										+'<span class="ellipsis clear">'+obj.menuName+'</span>'
									+'</li>';
							}
						}
					$(".alreadyAddApplication").html(areadyAddHtml);//已经添加的快捷入口
					
					window.appAddWin = Win.open({//弹窗
						id : "Application_shortcut",
						title : "配置快捷入口应用",
						width : 770,
						height:750,
						mask : true,
						html : $("#APPLICATION_list").html()
					});
				});
			});
		},
		
		//个人第三方的应用
		getOtherApp: function (){
			$("#OTHER_APP").html("");//重置第三方应用div
			$.post('${root}/open/selectOpenApp.do',function(data){
				var html ='<li class="fl ml30 w95"><div class="blue_lightBg thirdApp App_add" id="APP_add">+</div></li>';
				var alreadyAddHtml="";
				if(data && data.length > 0){
					for(var i = 0;i< data.length; i++ ){
						var openApp = data[i];
						html +='<li class="fl ml30 w95 center">'
							+'<a href="'+openApp.url+'">'
							+'<img src="<%=SpringContext.getBean(HostConfig.class).getOpen()%>/images/'+openApp.iconLarge+'" alt="" class="img_92">'
							+'<span class="ellipsis black">'+openApp.appName+'</span>'
							+'</a>'
						+'</li>';
						alreadyAddHtml+='<li class="fl ml40 w95 center" value="'+openApp.openAppId+'">'
									+'<img src="<%=SpringContext.getBean(HostConfig.class).getOpen()%>/images/'+openApp.iconLarge+'" alt="" class="fl img_92">'
									+'<i class="deleImg fr DwImg appNew"></i>'
									+'<span class="ellipsis clear">'+openApp.appName+'</span>'
								+'</li>';
					}
				}
				$("#OTHER_APP").html(html);//第三方应用html
				$(".alreadyAddApp").html(alreadyAddHtml);//已经添加的第三方应用（用于弹出层）
				//wJs.bindAddEvent();//绑定添加应用事件
			});
		},
		
		//第三方应用保存
		addAppSave: function (){
			var seft = this;
			var addOpenAppIds = "";
			$(".alreadyAddApp").eq(1).find("li").each(function(){
				if(addOpenAppIds ==""){
					addOpenAppIds = $(this).attr("value");
				} else {
					addOpenAppIds += "," + $(this).attr("value");
				}
			})
			var param = {
				addOpenAppIds:addOpenAppIds
			};
			$.post('${root}/open/user/openAppUpdate.do',param,function(data){
				if(data.result){
					Win.alert(data.message);
					window.appAddWin.close();
					seft.getOtherApp();
				}
			},'json');
		},
		//快捷入口配置保存
		addApplicationSave: function (){
			var newApplicationArray = [];//用于保存新的快捷入口
			$(".alreadyAddApplication").eq(1).find("li").each(function(i,obj){
				newApplicationArray.push({
					"baseAreaRMenuId": $(obj).attr("value"),
					"sort":i+1
				});
			})
			var param = {
				jsonStr:JSON.stringify(newApplicationArray)
			};
			$.post('${root}/shortcut/menu/shortcutMenuUpdate.do',param,function(data){
				if(data.result){
					Win.alert("配置成功");
					window.appAddWin.close();
					window.location.reload();
				}
			},'json');
		},
		//关闭弹窗
		closeWin: function (){
			if(window.appAddWin){
				window.appAddWin.close();
			}
		},
		init: function(){
			var userType = '${SESSION_USER.userType}';
			//获取应用列表
			var menuList = ${menuList};
			var array = new Array(menuList);
			var list = eval(array[0]);
			if(list&&list.length>0){
				list.forEach(function(value,index){
					var html ='<li class="fl ml30 w95 center" >'
								+'<a href="'+projectMapAppPage[value.baseMenuId]+value.url+'">'
								+'<img src="${root}/images/'+value.imagePath+'" alt="" class="img_92">'
								+'<span class="ellipsis black">'+value.menuName+'</span>'
								+'</a>'
							+'</li>';
					var groupName = value.groupName;
					
					if(userType == "AREA_USR"){//省市县用户组合并处理
						if(groupName == "RESOURCE_GROUP"){
							groupName = "TEACH_GROUP";
						}
					} 
					if(userType != "AREA_USR" && userType != "SCHOOL_USR" ){//非省市县、学校以下菜单忽略
						if(value.baseMenuId=="announcement.id"||value.baseMenuId=="information.id"||value.baseMenuId=="statistic.id"||value.baseMenuId=="basicInfo.id"){
							return ;
						}
					}
					if(userType == "STUDENT"){//学生没有题库
						if(value.baseMenuId == "questionlib.id"){
							return ;
						}
					}
					if(userType == "AREA_USR" || userType == "SCHOOL_USR" ){//省市县用户，忽略课程表
						if(value.baseMenuId == "schedule.id"||value.baseMenuId == "class.member.id"){
							return ;
						}
					}
					
					$("#"+groupName).append(html);
				});
				
			}
			$("ul[id$='_GROUP']").each(function(i,obj){//去除空的组
				if($(this).find("li").length==0){
					$(this).remove();
				}
			});
			//获取第三方运用
			this.getOtherApp();
			this.bindEvents();
			return this;
		}
	}
	wJs.fn.init.prototype = wJs.fn;
	window.wJs = wJs;
	
})();
var page = wJs();//初始化
</script>