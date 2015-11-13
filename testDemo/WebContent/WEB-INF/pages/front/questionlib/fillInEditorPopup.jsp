<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../../common/meta.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/reset.css"/>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/third-party/blank/addBlankButton.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/third-party/blank/addBlankButton.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
<script src="${root}/biz/questionlib/public.js"></script>
<title>${sessionScope.deploySysConfig.bannerPlatformName }</title>
<script type="text/javascript">
  $(document).ready(function(){
	 var editor = UE.getEditor('fillIn_editor',{toolbars:[['undo', 'redo', '|', 'cleardoc','kityformula']]}); 
	 var content = "";
	 //content = $(window.parent.optAnswerCell).find("textarea:first").val();
	 	
	 content =$(window.parent.optAnswerCell).find("textarea:first")[0].defaultValue;
	 editor.addListener( 'ready', function() {
		  this.setContent(content);
		  this.focus();
	  });
	  
	  $("#saveBtn").click(function(){
		  var contentLen=editor.getContentLength(true);
		  var fillInTxt = $.trim(editor.getContentTxt());
		  if(contentLen>500){
			  Win.alert({html:"<span class=\"dialog_Inner\">答案不能超过500字符！</span>",width:"200px"});
		    	return;
		  }	  
		  var $cell = $(window.parent.optAnswerCell);
    	  $cell.find("span").html(editor.getContent().replacePTag());
		  $cell.find("textarea:first").text(editor.getContent().replacePTag());
		  $cell.find("textarea:last").text(editor.getContent().replacePTag());
		  $cell.find("textarea:first")[0].value = editor.getContent().replacePTag();
		  $cell.find("textarea:last")[0].value = editor.getContent().replacePTag();
		  $cell.find("textarea:first")[0].innerHTML = editor.getContent().replacePTag();
		  $cell.find("textarea:last")[0].innerHTML = editor.getContent().replacePTag();
		  var domid = frameElement.getAttribute("domid");
		  parent.Win.wins[domid].close();
	  });
	  
   });
</script>
</head>
<body>
<div style="margin: 5px 6px 5px 5px;">
   <script id="fillIn_editor" type="text/plain" style="height:300px;width:100%"></script>
</div>
<br>
<div align="center">
    <a class="btn mr20" id="saveBtn">确认</a>
</div>
</body>
</html>