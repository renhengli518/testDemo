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
   var selectedElement = [{"id":'${clId}'}];
   $(function(){
	   var et;
	   var userId = "${SESSION_USER.userId}";
	   $(".wrap").css({"height":($(window).height()-70)+'px',"width":$(window).width()});
	   $.get("${root}/commons/selectClassBySchool.do?userId="+userId,function(treeData){
		   et = eTree.init({
				"id": "stuTree",
				"data": treeData,
				"showParam": "name",
				"selected": selectedElement,
				"select": function(d) {},
				"checkbox" : true
			});
	   });
	   
	   $(".toSave").click(function(){
		   if($("#beginTime").val() == ''){
			   Win.alert("请输入开始时间");
			   return;
		   }
		   
		   if($("#endTime").val() == ''){
			   Win.alert("请输入结束时间");
			   return;
		   }
		   
		   var param = $("#form").serialize();
		   var clsnum = 0;
		   $.each(et.getSelected(), function(i, value) {
			   if(value.parentId){
				 clsnum += 1;
			     param += "&classIds="+value.id;
			   }
			});
		   
		   if(clsnum == 0){
			   Win.alert("请至少选择一个班级");
			   return;
		   }
		   
		   if("${isedit}" == "true"){
			   //如果是编辑试卷时的保存并布置
			   console.log("保存并布置");
			   parent.$("#bzids").val(param);
			   parent.editexam();
		   }else{
			   $.post("${root}/schoolTest/finishCreateTask.do",param,function(data){
					   parent.Win.wins[domid].close();
			   });
		   }
	   });
	   
	   $(".toCancel").click(function(){
		   parent.Win.wins[domid].close();
	   });
   })
</script>
</head>
<body class="mainIndex">
	<form id="form">
		<div class="wrap">
			<div id="stuTree" class="ulWrap"></div>
			<div class="lH3">
				<input type="hidden" name="examId" value="${examId}">
				<p class="center">
					<label><span class="red">*</span>开始时间：</label> <input
						id="beginTime" type="text" class="Wdate" name="beginTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'});"
						value="" />
				</p>
				<p class="center">
					<label><span class="red">*</span>结束时间：</label> <input id="endTime"
						type="text" class="Wdate" name="endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginTime\')}'});"
						value="" />
				</p>
			</div>
		</div>
	</form>
	<p class="center mt40 withBg">
    <button class="btn bigBtn toSave mr20">确定</button>
    <button class="btn bigBtn toCancel">取消</button>
  </p>         
</body>

</html>