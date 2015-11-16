<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>

<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/js/homework/homeworkFile.js"></script>
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/upload/uploadfile.js"></script>

<!--  引入富文本框需要的插件-->
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

<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/js/homework/saveHomework.js"></script>
</head>
<body>
	<%@include file="../../../common/topHeader.jsp" %>
	<%@include file="../../../common/workNav.jsp" %>
	<div class="container clearfix w1200 bkgNone marginauto">
		<div class="content">
			<div class="content-in">
				<div class="breadCrumb">
					<a href="${root}/teacherWork/toTeacherWork.do">作业</a><span class="nextlevel">></span><span class="currentLevel">编辑作业</span>
				</div>
				<!-- 数据列表 -->
				<div class="borderBox gn-bgWhite borderBoxP40">
				<form id="myForm">
					<input type="hidden" name="workHomeworkId" id="workHomeworkId" value="${homework.workHomeworkId }"/>
					<input type="hidden" name="fileStrs" id="fileStrs"/>
					<input type="hidden" name="textQueContent" id="textQueContent"/>
					<input type="hidden" name="receiveStus" id="receiveStus"/>
					<input type="hidden" name="readStus" id="readStus"/>
					<input type="hidden" name="questions" id="questions"/>
					<input type="hidden" name="status" id="status"/>
					<input type="hidden" name="questionCount" id="questionCount"/>
					<ul class="commonUL giveHomework">
						<li>
							<label class="text" for="taskName">
								<span class="red">*</span>作业名称：
							</label>
							<span class="cont">
								<input value="${homework.workTitle }" type="text" maxlength="60" needcheck nullmsg="作业名称不能为空！" limit="1,60" limitmsg="作业名称最大长度为60个字！" id="taskName" name="workTitle" class="w400"><span class="ml10 coffeeColor">（作业内容可选择添加文本、附件、习题等任意一种作业类型，也可全部添加）</span>
							</span>
						</li>
						<li>
							<label class="text" for=""><span class="red">*</span>学科：</label>
							<span class="cont">
								<select name="baseSubjectId" value="${homework.baseSubjectId }" needcheck nullmsg="请选择学科！" class="newSel">
									<option value="">请选择</option>
									<c:forEach var="subject" items="${subjects }">
										<option <c:if test="${homework.baseSubjectId eq subject.id}">selected="selected"</c:if> value="${subject.id }">${subject.name }</option>
									</c:forEach>
								</select>
							</span>
						</li>
						<li>
							<label class="text" for="">习题调用：</label>
							<span class="cont">
								<a class="itemA mr30 selectQuestion" href="javascript:;">选择习题</a>
								<a class="itemA mr30 selectedQuestion" href="javascript:;">查看已选习题（<front id="selQueCount">0</front>） </a>
								<a class="itemA clearAll" href="javascript:;">清空</a>
							</span>
						</li>
						<li>
							<label class="text" for="">文本内容：</label>
							<span class="cont">
								<script id="description" type="text/plain" style="height:218px;width:880px"></script>
							</span>
						</li>
						<li>
							<label class="text" for="">作业附件：</label>
							<span class="cont">
								<div class="verticalMiddle">
									<a class="itemA uploadBox" href="javascript:;">上传作业附件<span id="uploadCont" class="uploadCont"></span></a><span class="ml10 coffeeColor">单个附件大小不能超过5M，最多可上传6个附件（附件格式支持.doc、.docx、.txt、.xls、.xlsx、.pdf）</span>
								</div>
								<div class="borderBox uploadEnclosure mt10">
									<ul id="uploadInfoBox" class="commonUL">
										<c:forEach items="${docs }" var="doc">
											<li class="verticalMiddle" filename="${doc.docName }" filesize="${doc.docSize }" id="${doc.workDocId }" filepath="${doc.docPath }">
												<span class="fileNames">${doc.docName }</span>
												<span class="fileSize">大小：
												<c:if test="${doc.docSize/1024/1024 >= 1}">
													<fmt:formatNumber type="number" value="${doc.docSize/1024/1024}" maxFractionDigits="2"/>M
												</c:if>
												<c:if test="${doc.docSize/1024/1024 < 1}">
													<fmt:formatNumber type="number" value="${doc.docSize/1024}" maxFractionDigits="2"/>K
												</c:if>
												</span>
												<span class="loadState">
													<span class="state green">上传完成！</span>
													<span class="uploadOperate"></span>
												</span>
												<a file="${doc.workDocId }" class="delTheEnclosure delUploadFile" href="javascript:;">删除</a>
											</li>
										</c:forEach>
									</ul>
								</div>
							</span>
						</li>
						<li>
							<label class="text" for=""><span class="red">*</span>接收人：</label>
							<span class="cont">
								<label>
									<input type="radio" name="assignType" <c:if test="${homework.assignType eq 'DEFAULT' }">checked</c:if>  value="DEFAULT">&nbsp;&nbsp;默认开放给当前老师所带班级
								</label><br/>
								<label class="piyueLabel">
									<input type="radio" name="assignType" <c:if test="${homework.assignType eq 'CUSTOM' }">checked</c:if> value="CUSTOM">&nbsp;&nbsp;自定义
								</label>
								<a class="itemA receiveStuSel <c:if test="${homework.assignType eq 'DEFAULT' }"> disabled</c:if>" href="javascript:;">选择学生</a>
								<span class="receiStu receiveStuSpan">
									<c:if test="${homework.assignType eq 'CUSTOM' }">
										<c:forEach items="${receiveStus }" var="stu">
											<label customdel="" class="itemDel">
												<button userId="${stu.userId }" classId="${stu.classId }" type="button">${stu.name }</button>
											</label>
										</c:forEach>
									</c:if>	
								</span>
							</span>
						</li>
						<li>
							<label class="text" for=""><span class="red">*</span>批阅方式：</label>
							<span class="cont">
								<label>
									<input type="radio" name="readOverType" value="TEACHER" <c:if test="${homework.readOverType eq 'TEACHER' }">checked</c:if> >&nbsp;&nbsp;仅学科教师批阅
								</label><br/>
								<label>
									<input type="radio" name="readOverType" value="STU_EACH_OTHER" <c:if test="${homework.readOverType eq 'STU_EACH_OTHER' }">checked</c:if>>&nbsp;&nbsp;学生参与互评，随机分配
								</label><br/>
								<label class="piyueLabel">
									<input type="radio" name="readOverType" value="STU_SPECIFIED" <c:if test="${homework.readOverType eq 'STU_SPECIFIED' }">checked</c:if>>&nbsp;&nbsp;指定学生参与批阅
								</label>
								<a class="itemA readStuSel <c:if test="${homework.readOverType eq 'TEACHER' || homework.readOverType eq 'STU_EACH_OTHER'}">disabled</c:if>" href="javascript:;">选择学生</a>
								<span class="receiStu readStuSpan">
									<c:if test="${homework.readOverType eq 'STU_SPECIFIED' }">
										<c:forEach items="${readStu }" var="stu">
											<label customdel="" class="itemDel">
												<button userId="${stu.userId }" type="button">${stu.name }</button>
											</label>
										</c:forEach>
									</c:if>	
								</span>
							</span>
						</li>
						<li>
							<label class="text" for=""><span class="red">*</span>完成时间：</label>
							
							<span class="cont">
								<input type="text" value="<fmt:formatDate value="${homework.finishTime }" pattern="yyyy-MM-dd HH:mm"/>" needcheck nullmsg="作业完成时间不能为空！" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d'});" name="finishTime" class="Wdate">
								<br/>
								<label>
								  <input id="isPublish" type="checkbox" checked />&nbsp;&nbsp;暂不布置给学生
								</label>
							</span>
						</li>
					</ul> 
					<div class="center btnContainer">
					   <button type="submit" class="btn btnCoffee fixedWidthBtn mr20">布置作业</button>
					   <button type="button" class="btn btnGray fixedWidthBtn">取消</button>
					</div>
					</form>
				</div>
			</div>
			
		</div>
	</div>
</body>
<script type="text/javascript">
var description = UE.getEditor("description");
description.ready(function() {
	description.setContent('${homework.textQueContent}');
});
var qIds = "${qIds}";
if(qIds){
	sqlist = qIds.split(",");
	$("#selQueCount").html(sqlist.length);
}

function ajaxReq(){
	var data = $("#myForm").serializeArray();
	$.post('${root}/homework/editHomework.do',data,function(d){
		if(d && d.result){
			location.href="${root}/teacherWork/toTeacherWork.html";
		}else{
			Win.alert(d.message);
		}
	});
	return false;
};
</script>
</html>