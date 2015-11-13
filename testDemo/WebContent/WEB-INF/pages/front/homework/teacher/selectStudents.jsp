<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/eTree/eTree.js"></script>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/eTree/eTree.css"/>
<style>
  body{background:#fff;}
  .tableCell{display:table-cell;width:100%; min-height:600px; border:1px solid red}
  .bigBtn{padding:5px 50px; color:#fff;font-size:20px; border-radius:3px;}
  .bigBtn.toSave{background:#7e532c; border-bottom:1px solid #4dadc6;}
  .bigBtn.toCancel{ background:#999; border-bottom:1px solid #7b7a7a}
  .withBg{height:100px;line-height:100px;position:fixed; width:100%; left:0;bottom:0; margin-top:50px; background:#f6f6f6}
  .wrap {
	display:table-cell;
	vertical-align:middle; 
	text-align:center;
	margin:10px;
	text-index:2em;
	line-height:1.8
  }
  .ulWrap{width:284px;height:270px; border:1px solid #ddd; margin-left: auto; margin-right:auto; overflow:auto}
  .chooseStu ,.chooseStu li{list-style:none;text-align:left}
  .chooseStu li{padding-left:20px; line-height:32px;}
  .chooseStu  input{display:inline-block; margin-right:5px}
</style>
<script>
   var domid = frameElement.getAttribute("domid");
   var selectedElement = [];
   var CALL_BACK = "${param.callBack}";
   if(CALL_BACK == 'getReceiveStu'){
	   selectedElement = parent.getSelectedReceiveStu();
   }else if(CALL_BACK == 'getReadStu'){
	   selectedElement = parent.getSelectedReadStu();
   }
   $(function(){
	   var et;
	   $(".wrap").css({"height":($(window).height()-70)+'px',"width":$(window).width()});
	   $.get("${root}/commons/selectStudentByTeacher.do?userId=fbe1910647504713bdfe043084de7014",function(treeData){
		   et = eTree.init({
				"id": "easyTree",
				"data": treeData,
				"showParam": "name",
				"checkbox": true,
				"selected": selectedElement,
				"select": function(d) {}
			});
	   });
	   
	   $(".toSave").click(function(){
		   if (CALL_BACK && parent[CALL_BACK]) {
           		parent[CALL_BACK](et.getSelected());
           } 
		   parent.Win.wins[domid].close();
	   });
	   
	   $(".toCancel").click(function(){
		   parent.Win.wins[domid].close();
	   });
   })
</script>
</head>
<body class="mainIndex">
   <div class="wrap">
      <div id="easyTree" class="ulWrap">
      </div>
  </div>
  <p class="center mt40 withBg">
    <button class="btn bigBtn toSave mr20">确定</button>
    <button class="btn bigBtn toCancel">取消</button>
  </p>         
</body>

</html>