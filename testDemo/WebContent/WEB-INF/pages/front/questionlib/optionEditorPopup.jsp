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
	 var editor = UE.getEditor('opt_editor'); 
	 var content = "";
	 /* alert("defaultValue="+$(window.parent.optContentCell).find("textarea:first")[0].defaultValue);
	 alert("now="+$(window.parent.optContentCell).find("textarea:first").val()); */
	 content =$(window.parent.optContentCell).find("textarea:first")[0].defaultValue;
	 var questionType = $(window.parent.optContentCell).find(".quesType").val();
	 
	 editor.addListener( 'ready', function() {
		  this.setContent(content);
		  this.focus();
	  });
	  
	  $("#saveBtn").click(function(){
		  var optionTxt = $.trim(editor.getContentTxt());
		  var contentLen=editor.getContentLength(true);
		  if("JUDEMENT"==questionType){//如果是判断题只能输入一个字符
			  if(contentLen>1){
				  Win.alert({html:"<span class=\"dialog_Inner\">选项内容不能超过1字符！</span>",width:"200px"});
			    	return;  
			  } 
          }else {//单选或多选题
        	  if(contentLen>500){
			  Win.alert({html:"<span class=\"dialog_Inner\">选项内容不能超过500字符！</span>",width:"200px"});
		    	return;
		       } 
            }
		  
		  	  
		  var $cell =  $(window.parent.optContentCell);
		  $cell.find(".content_title").html(editor.getContent().replacePTag());
		  $cell.find(".content_title_input").val(editor.getContent().replacePTag());
		  $cell.find("textarea:first").text(editor.getContent().replacePTag());
		  $cell.find("textarea:last").text(optionTxt);
		  var domid = frameElement.getAttribute("domid");
		  parent.Win.wins[domid].close();
	  });
	  
   });
</script>
</head>
<body>
<div style="margin: 5px 6px 5px 5px;">
   <script id="opt_editor" type="text/plain" style="height:300px;width:100%"></script>
</div>
<br>
<div align="center">
    <a class="btn mr20" id="saveBtn">确认</a>
</div>
</body>
</html>