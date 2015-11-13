<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css"/>
</head>
<body>
	<div class="borderBox bkgWhite pd20">
		<div class="searchCondition">
			<form id="searchForm">
			<ul class="commonUL">
				<li>
					<label class="text">学段：</label>
					<span class="cont">
						<select id="semesterSelect" name="baseSemesterId">
							<option value="">请选择</option>
					        <c:forEach items="${semesters}" var="semester">
					          <option value="${semester.baseSemesterId }">${semester.semesterName}</option>
							</c:forEach> 
						</select>
					</span>
				</li>
				<li>
					<label class="text">年级：</label>
					<span class="cont">
						<select id="classLevelSelect" name="baseClasslevelId">
							<option value="">请选择</option>
						</select>
					</span>
				</li>
				<li>
					<label class="text">学科：</label>
					<span class="cont">
						<select id="subjectSelect" name="baseSubjectId">
							<option value="">请选择</option>
						</select>
					</span>
				</li>
				<li>
					<label class="text">版本：</label>
					<span class="cont">
						<select id="versionSelect" name="baseVersionId">
							<option value="">请选择</option>
							<c:forEach items="${versions}" var="version">
					          <option value="${version.id }">${version.name}</option>
							</c:forEach> 
						</select>
					</span>
				</li>
				<li>
					<label class="text">分册：</label>
					<span class="cont">
						<select id="volumeSelect" name="baseVolumeId">
							<option value="">请选择</option>
						</select>
					</span>
				</li>
				<li>
					<label class="text">章：</label>
					<span class="cont">
						<select id="chapterSelect" name="baseChapterId">
							<option value="">请选择</option>
						</select>
					</span>
				</li>
				<li>
					<label class="text">习题类型：</label>
					<span class="cont">
						<select id="questionType" name="type">
							<option value="">请选择</option>
							<option value="SINGLE_CHOICE">单选题</option>
							<option value="MULTI_CHOICE">多选题</option>
							<option value="JUDEMENT">判断题</option>
							<option value="FILL_IN_BLANK">填空题</option>
							<option value="ASK_ANSWER">问答题</option>
							<option value="COMPUTING">计算题</option>
						</select>
					</span>
				</li>
				<li class="verticalMiddle">
					<button type="button" id="searchBtn" class="btn btnOrange fr">搜索</button>
				</li>
			</ul>
			</form>
		</div>
		<div class="testCon testConLittleMargin" id="queChoice">			
		</div>
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
		var formdata = $("#searchForm").serialize();
		var url = "${root}/homework/selectQuestion.do?"+formdata;
		
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
		formQuestionListCommon(data,'#queChoice');
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