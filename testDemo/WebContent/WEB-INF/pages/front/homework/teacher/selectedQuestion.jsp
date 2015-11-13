<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css"/>
</head>
<body>
	<div class="borderBox bkgWhite pd20">
		<div class="testCon testConLittleMargin" id="queSelected"></div>
		<div id="pageNavi"></div>
		<div class="center btnContainer1">
		   <button class="btn btnCoffee fixedWidthBtn saveSelected mr20">确定</button>
		   <button class="btn btnGray fixedWidthBtn cancelSelected">取消</button>
		</div>
	</div>
</body>
<script type="text/javascript">
var domid = frameElement.getAttribute("domid");
var yqlist = "${param.qlist }";
$(function(){
	// 查询共享题库列表
	function searchQuestions() {
		var url = "${root}/homework/selectedQuestion.do?queIds="+yqlist;
		
		var mySplit = new SplitPage({
		        node : $id("pageNavi"),
		        url : url,
		        data :  {},
		        count : 10,
		        callback : formQuestionListKnowledge,
		        type : 'get' //支持post,get,及jsonp
		 });			 
		 mySplit.search(url,{});
	}
	
	searchQuestions();
	
	$("#searchBtn").click(function(){
		searchQuestions();
	});
	
	function formQuestionListKnowledge(data,totalCnt){
		formSelectedQuestionListCommon(data,'#queSelected');
	}
	
	$(".saveSelected").click(function(){
		parent.getSelectedQue(qlist);
		parent.Win.wins[domid].close();
	});
	
	$(".cancelSelected").click(function(){
		parent.Win.wins[domid].close();
	});
});
</script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/js/homework/selectQuestion.js"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/js/homework/homework.js"></script>
</html>