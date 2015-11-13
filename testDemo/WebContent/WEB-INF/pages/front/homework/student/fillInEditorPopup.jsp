<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>

<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/ueditor/third-party/blank/addBlankButton.js"> </script>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var editor = UE.getEditor('fillIn_editor',{toolbars:[['undo', 'redo', '|', 'cleardoc','kityformula']]});
	var content = $("#"+window.parent.fillId, window.parent.document);
	editor.addListener('ready', function() {
		this.setContent(content.html());
		this.focus();
	});
	$("#saveBtn").click(function(){
		var domid = frameElement.getAttribute("domid");
		if(editor.getContentTxt().length > 500){
			Win.alert("填空题答案不能超过500字！");
		}else{
			var show = editor.getContent().replace("&lt;","<");
			show = editor.getContent().replace("&gt;",">");
			show = editor.getContent().replace("<p>","");
			content.html(show);
			parent.Win.wins[domid].close();
		}
	});
});
</script>
</head>
<body>
<div style="margin: 5px 6px 5px 5px;">
	<script id="fillIn_editor" type="text/plain" style="height:340px;width:100%"></script>
</div>
<br>
<div align="center">
	<a class="btn mr20" id="saveBtn">确认</a>
</div>
</body>
</html>