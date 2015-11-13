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
<script type="text/javascript" charset="utf-8" src="${PUBLIC_PATH}/public/js/video/showVideo.js"></script>
<style>
.fillAnswer {border-bottom: 1px solid #ccc;cursor: default;display: inline-block;margin: 0 10px;padding:3px 5px;min-width: 70px;vertical-align:bottom;word-break: break-all;overflow:hidden;}
</style>
</head>
<body class="mainIndex">
	<%@include file="../../../common/topHeader.jsp" %>
	<%@include file="../../../common/workNav.jsp" %>
<div class="container clearfix w1200 bkgNone marginauto">
	<div class="content">
		<div class="content-in">
			<div class="breadCrumb">
				<a href="${root}/studentWork/toStudentWork.do">作业</a><span class="nextlevel">></span><span class="currentLevel">在线作业</span>
			</div>
		<div class="borderBox gn-bgWhite pd20 pb0">
		<h3 class="MarginTitle">作业名称：<span>${homework.workTitle } </span></h3>   
		</div>
			<!-- 数据列表 -->
			<c:if test="${!empty homework.questions }">
				<div class="borderBox gn-bgWhite borderBoxP40">
				<h3 class="titleOfCheckAnswer titleOfCheckAnswer1 clearfix">
				习题作业
				</h3>
				<div class="checkAnswerWrap">
					<c:set target="${queTypeTitleName }" property="SINGLE_CHOICE" value="单选题"></c:set>
					<c:set target="${queTypeTitleName }" property="MULTI_CHOICE" value="多选题"></c:set>
					<c:set target="${queTypeTitleName }" property="JUDEMENT" value="判断题"></c:set>
					<c:set target="${queTypeTitleName }" property="FILL_IN_BLANK" value="填空题"></c:set>
					<c:set target="${queTypeTitleName }" property="ASK_ANSWER" value="问答题"></c:set>
					<c:set target="${queTypeTitleName }" property="COMPUTING" value="计算题"></c:set>
					<c:set var="subjectiveIds" value=""></c:set>
					<c:set var="queTypeTitleIndexName" value="${fn:split('一,二,三,四,五,六',',')}" />
					<c:set var="se" value="${fn:split('A,B,C,D,E,F,G',',')}" />
					<c:set var="queTypeTitleIndex" value="0"></c:set>
					<c:set var="queType" value=""></c:set>
					<c:forEach items="${homework.questions }" var="que" varStatus="status">
						<c:if test="${queType ne que.type}">
							<c:if test="${!status.first}">
								</div>
							</c:if>
							<c:set var="queType" value="${que.type}"></c:set>
							<div class="testCon checkAnsweContent">
								<h4 class="quesKind">${queTypeTitleIndexName[queTypeTitleIndex]}、${queTypeTitleName[que.type] }</h4>
								<c:set var="queTypeTitleIndex" value="${queTypeTitleIndex +1 }"></c:set>
						</c:if>
						<div class="borderBox queDiv pd20" queId="${que.workQuestionId}" queType="${que.type}" queIndex="${status.count }">
						<c:if test="${que.type eq 'SINGLE_CHOICE' || que.type eq 'JUDEMENT'}">
							<div class="quesContent verticalMiddle">
							<div class="quesTitle"><p class="fl fz20">${status.count }、</p>${que.content }</div>
							<c:if test="${!empty que.contentVideo}">
								<p class="uploadMediaWrap mt20">
									<a href="javaScript:;" class="btn btnUpLoad uploadBox"  onclick="showContentVideo('${que.contentVideo}','${que.workQuestionId}','contentVideo');">点击播放音视频</a>
									<span id="showContentVideo${que.workQuestionId}"></span>
								</p>
							</c:if>
					<c:forTokens items="${que.options}" delims="∷" var="option" varStatus="opStatus">
						<label class="choiceLable"><input type="radio" optId="${se[opStatus.index] }" name="${que.workQuestionId}"></label>  选项${option }<br/>
					</c:forTokens>
							</div>
						</c:if>
				<c:if test="${que.type eq 'MULTI_CHOICE'}">
					<div class="quesContent verticalMiddle">
						<div class="quesTitle"><p class="fl fz20">${status.count }、</p>${que.content }</div>
						<c:if test="${!empty que.contentVideo}">
							<p class="uploadMediaWrap mt20">
								<a href="javaScript:;"class="btn btnUpLoad uploadBox"  onclick="showContentVideo('${que.contentVideo}','${que.workQuestionId}');">点击播放音视频</a>
								<span id="showVideo${que.workQuestionId}"></span>
							</p>
						</c:if>
					<c:forTokens items="${que.options}" delims="∷" var="option" varStatus="opStatus">
						<label class="choiceLable"><input type="checkbox" optId="${se[opStatus.index]}" name="ques1"></label>  选项${option }<br/>
					</c:forTokens>
					</div>
				</c:if>
						<c:if test="${que.type eq 'FILL_IN_BLANK'}">
					<div class="quesContent1 verticalMiddle fillInBlank">
						<div class="quesTitle"><p class="fl fz20">${status.count }、</p>${que.content }</div>
							<c:if test="${!empty que.contentVideo}">
									<p class="uploadMediaWrap mt20">
										<a href="javaScript:;"class="btn btnUpLoad uploadBox"  onclick="showContentVideo('${que.contentVideo}','${que.workQuestionId}');">点击播放音视频</a>
									<span id="showVideo${que.workQuestionId}"></span>
									</p>
								</c:if>
					</div>
						</c:if>
						<c:if test="${que.type eq 'ASK_ANSWER' || que.type eq 'COMPUTING'}">
							<c:set var="subjectiveIds" value="${subjectiveIds }${','}${que.workQuestionId}"></c:set>
					<div class="quesContent verticalMiddle">
						<div class="quesTitle"><p class="fl fz20">${status.count }、</p>${que.content }</div>
							<c:if test="${!empty que.contentVideo}">
									<p class="uploadMediaWrap mt20 mb20">
										<a href="javaScript:;"class="btn btnUpLoad uploadBox"  onclick="showContentVideo('${que.contentVideo}','${que.workQuestionId}');">点击播放音视频</a>
									<span id="showVideo${que.workQuestionId}"></span>
									</p>
								</c:if>
					<script id="${que.workQuestionId}" type="text/plain" style="height:210px;width:100%"></script>
				
				<p class="uploadMediaWrap mt20 ">
					<a href="javascript:;" class="btn btnUpLoad uploadBox" id="answerVideo">上传音视频<span id="uploadCont_${que.workQuestionId}" class="uploadCont"> </span></a>
						<span id="uploadVedioInfoBox">
						<ul id="uploadInfo_${que.workQuestionId}" class="commonUL">
							&nbsp;
						</ul>
						</span>
					</p>
					</div>
						</c:if>
						</div>
						<c:if test="${status.last}">
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			</c:if>
<c:if test="${!empty homework.textQueContent }">
	<div class="TaskWrap mt30">
<h3 class="titleOfCheckAnswer clearfix">文本作业</h3>
<p class="fontBold fl">作业内容：</p>

${homework.textQueContent}

<p class="fontBold mt10 mb10">提交作业：</p>
<script id="txtResult" type="text/plain" style="height:210px;width:100%"></script>

</div>
</c:if>
	<c:if test="${!empty homework.docs}">
		<div class="TaskWrap  mt30">
			<h3 class="titleOfCheckAnswer clearfix">附件作业</h3>
		<ul class="commonUL">
			<li>
				<label class="text  fontBold">附件作业：</label>
					<span class="cont">
						<c:forEach items="${homework.docs }" var="doc">
							<span class="lightBlue">${doc.docName }</span>
								<span class="ml30">大小：
									<c:if test="${doc.docSize/1024/1024 >= 1}">
										<fmt:formatNumber type="number" value="${doc.docSize/1024/1024}" maxFractionDigits="2"/>M
									</c:if>
									<c:if test="${doc.docSize/1024/1024 < 1}">
										<fmt:formatNumber type="number" value="${doc.docSize/1024}" maxFractionDigits="2"/>K
									</c:if>
								</span>
								<a href="${root }/images/${doc.docPath }" class="ml30 lightBlue">下载</a> <br/>                                   
						</c:forEach>
					</span>
				</li>
				<li>
					<label class="text  fontBold">上传作业：</label>
						<span class="cont">
							<a class="btn btnGreen insertBtn ml200 posRelative">上传作业<span id="uploadCont" class="uploadCont"></span></a>
								<div class="borderBox uploadEnclosure mt10">
									<ul id="uploadInfoBox" class="commonUL">
									</ul> 
								</div>
						</span>
				</li>
		</ul>
	</div>
</c:if>
<form id="myForm">
<input type="hidden" name="homeworkId" value="${homework.workHomeworkId }"/>
	<input type="hidden" name="questionResult" id="questionResult"/>
	<input type="hidden" name="txtResult" id="txtResultForm"/>
	<input type="hidden" name="fileResult" id="fileResult"/>
	<input type="hidden" name="answerCount" id="answerCount"/>
	<p class="center mt40">
	<button type="submit" class="btn btnSearch nextStep">提交作业</button>
	</p>
</form>
</div>

</body>
<script type="text/javascript">
	var subjectiveIds = '${subjectiveIds }';
	var subjTxtResult = {};
	var fillId;//填空题空格Id
	var txtResult;
	var isHaveTextWork = ${!empty homework.textQueContent };
	var isHaveQueWork = ${!empty homework.questions };
	var isHaveDocWork = ${!empty homework.docs };
	var optSplitChar = '∷';
	var showStr = "";
	var video="";
$(function(){
	if(subjectiveIds){
		subjectiveIds = subjectiveIds.substr(1,subjectiveIds.length);
		var subjectiveIdList = subjectiveIds.split(",");
		for(var i = 0;i<subjectiveIdList.length;i++){
			subjTxtResult[subjectiveIdList[i]] = UE.getEditor(subjectiveIdList[i]);
		}
	}
	
	if(isHaveTextWork){
		txtResult = UE.getEditor("txtResult");
	}
	
	/* 填空题，空格初始化 */
	$(".fillInBlank .question-blank-space.edui-faked-music").each(function(index, element){
		var queId = $(this).parents(".queDiv").attr("queId");
		var id = queId + "_" + index;
		var html = '<div id="'+id+'" style="min-height:21px;" class="fillAnswer"></div>';
		$(this).replaceWith(html);
	});
	
	$(".checkAnswerWrap").on("click",".fillAnswer",function(){
		fillId = $(this).attr("id");
		var url = "${root}/homework/fillInEditorPopup.html";
		Win.open({
			id:fillId+"_Win",
			title:"输入答案",
			width: 800,
			height: 500,
			url: url
		});
	});
	

	new BasicCheck({
		form: $id("myForm"),
		addition : addition,
		ajaxReq : ajaxReq,
		warm : function(o,msg){
			Win.alert(msg);
		}
	});
	function addition(){
		var questionResult = [];
		var answerCount = 0;
		showStr = "";
		if(isHaveQueWork){
			var unAnswerCount = 0;
			var subjectiveOutTxt = {"ASK_ANSWER":[],"COMPUTING":[]};
			$(".checkAnswerWrap .queDiv").each(function(){
				var $queDiv = $(this);
				var queId = $queDiv.attr("queId");
				var queType = $queDiv.attr("queType");
				var queIndex = $queDiv.attr("queIndex");
				var answerObj = {"wQueId":queId,"answer":"","answerVideo":""};
				var isAnswer = true;
				if(queType == 'SINGLE_CHOICE' || queType == 'JUDEMENT'){
					var $redioAnswer = $queDiv.find(":radio:checked");
					if($redioAnswer[0]){
						answerObj.answer = $redioAnswer.attr("optId");
					}else{
						isAnswer = false;
					}
				}else if(queType == 'MULTI_CHOICE'){
					var $checkAnswer = $queDiv.find(":checked");
					var checkAnswer = '';
					if($checkAnswer[0]){
						$checkAnswer.each(function(){
							checkAnswer += $(this).attr("optId");
						});
						answerObj.answer = checkAnswer;
					}else{
						isAnswer = false;
					}
				}else if(queType == 'FILL_IN_BLANK'){
					var $fillAnswer = $queDiv.find(".fillAnswer");
					if($fillAnswer[0]){
						var fillAnswer = '';
						$fillAnswer.each(function(index,element){
							var value = $(this).html().replacePTag();
							if(index == 0){
								fillAnswer = value;
							}else{
								fillAnswer += optSplitChar + value;
							}
							if(!value){
								isAnswer = false;
							}
						});
						answerObj.answer = fillAnswer;
					}
				}else{
					var subjectiveAnswer = subjTxtResult[queId].getContent();
					var conVideo = $("#uploadInfo_"+queId).attr("answerVideo");
					if(!conVideo){
						conVideo = '';
					}
					if(subjTxtResult[queId].getContentTxt().length > 500){
						subjectiveOutTxt[queType].push(queIndex);
					}
					if(subjectiveAnswer.length > 0 || conVideo){
						answerObj.answer = subjectiveAnswer;
						answerObj.answerVideo = conVideo;
					}else{
						isAnswer = false;
					}
				}
				questionResult.push(answerObj);
				if(isAnswer){
					answerCount = answerCount + 1;
				}else{
					unAnswerCount = unAnswerCount + 1;
				}
			});
			var subjectiveAlertString = "";
			if(subjectiveOutTxt.ASK_ANSWER && subjectiveOutTxt.ASK_ANSWER.length>0){
				subjectiveAlertString +="、问答题:"+subjectiveOutTxt.ASK_ANSWER.join(",");
			}
			if(subjectiveOutTxt.COMPUTING && subjectiveOutTxt.COMPUTING.length>0){
				subjectiveAlertString +="、计算题:"+subjectiveOutTxt.COMPUTING.join(",");
			}
			if(subjectiveAlertString){
				Win.alert(subjectiveAlertString.substr(1,subjectiveAlertString.length) + "，答题不能超出500字！");
				return false;
			}
			if(unAnswerCount >0){
				showStr = showStr + "习题作业未答完,";
			}
			$("#questionResult").val(JSON.stringify(questionResult));
		}
		
		if(isHaveTextWork){
			var result = txtResult.getContent();
			if($.trim(result) == ''){
				showStr = showStr + "文本作业未答,";
			}else{
				answerCount = answerCount + 1;
			}
			if(txtResult.getContentTxt().length > 500){
				Win.alert("文本作业，答题不能超出500字！");
				return false;
			}
			$("#txtResultForm").val(result);
		}
		
		if(isHaveDocWork){
			var files = getFiles();
			if(!files || files.length == 0){
				showStr = showStr + "附件作业未答,";
			}else{
				answerCount = answerCount + 1;
			}
			$("#fileResult").val(JSON.stringify(files));
		}
		$("#answerCount").val(answerCount);
		return true;
	}
	
	function ajaxReq(){
		var data = $("#myForm").serializeArray();
		var confirmStr = showStr + "确认提交吗？";
		Win.confirm(
			{id:"commitExam",
			title:"确认消息",
			html:"<div class=\"dialog_Inner\">" + confirmStr + "</div>",
			width: 300}, 
			function(){
				$.post('${root}/homework/answerHomework.do',data,function(d){
					if(d && d.result){
						location.href="${root}/studentWork/toStudentWork.html";
					}else{
					   	Win.alert(d.message);
					}
				});
			 },function(){});
		return false;
	};
});

String.prototype.replacePTag = function(){
	var str = this;
	str =  str.replace(/<img /g, 'a3907d77279e4028a4603b4b5582454f').replace(/<\/img>/g,'f2ddbf4b86de4269af2ff3794757160c');
	str = filterSpecChar(str);
	str = str.replace(/a3907d77279e4028a4603b4b5582454f/g, '<img ').replace(/f2ddbf4b86de4269af2ff3794757160c/g, '</img>')
	return str;
}

function filterSpecChar(str){
	str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
	str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
	str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
	str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
	return str; 
}

$(function(){
	var params= {
	debug : 1,
	fileType : "*.mp4;*.flv;*.mp3;",
	typeDesc : "音频",
	sizeLimit : 500*1024*1024,
	autoStart : false,
	multiSelect : 0,  //表示每次只能上传一个     1 表示可以进行批量上传
	server: encodeURIComponent("${LOCAL_PATH}/videoUpload")
		}; 
	$(".checkAnswerWrap .uploadCont").each(function(){
		var $this = $(this);
		var queId =$this.parents(".queDiv").attr("queId");
		window["upload_"+queId] = new UploadFile($id("uploadCont_"+queId), "upload_"+queId, "${PUBLIC_PATH}/public/upload/uploadFile.swf",params);
		
		window["upload_"+queId].uploadEvent.add("noticeNumberError",function(){
			var num = arguments[0].message[0];
			alert("num"+num);
			console.log("您最多只能同时上传"+num+"个文件！");
		});
		window["upload_"+queId].uploadEvent.add("noticeTypeError",function(){
			var elm = arguments[0].message[0],
				fileType = arguments[0].message[1],
				info = arguments[0].message[2];
			if($id(elm)) return false;
			$id("uploadVedioInfoBox").innerHTML += "<li id='"+elm+"'><span class='infoLabel'><b class='fileName mr10' title='"+info.name+"'>"+info.name+"</b><b class='fileSize mr10'>"+(info.size/1024/1024).toFixed(2)+"MB</b></span><span class='mr20'>不支持的文件类型,仅支持"+fileType+"</span><span class='uploadOperate'><a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a></span></li>";
		});
		window["upload_"+queId].uploadEvent.add("noticeSizeError",function(){
			var elm = arguments[0].message[0],
				limite = arguments[0].message[1],
				info =  arguments[0].message[2];
			if($id(elm)) return false;
			$id("uploadVedioInfoBox").innerHTML += "<li id='"+elm+"'><span class='infoLabel'><b class='fileName mr10' title='"+info.name+"'>"+info.name+"</b><b class='fileSize mr10'>"+(info.size/1024/1024).toFixed(2)+"MB</b></span><span class='mr20'>上传文件过大，限制大小"+(limite/1024/1024)+"MB</span><span class='uploadOperate'><a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a></span></li>";
		});
		window["upload_"+queId].uploadEvent.add("onSelect",function(){
			var elm = arguments[0].message[0],
				info = arguments[0].message[1];
			
			var liList=document.getElementById("uploadInfo_"+queId).getElementsByTagName("li").length;
			if(liList>0){
				Win.alert("文件已经上传，不能继续上传");
				return false;
			}else{
				 if($id(elm)) return false;
				$id("uploadInfo_"+queId).innerHTML += "<li id='"+elm+"'><span class='infoLabel'><b class='fileName mr10' title='"+info.name+"'>"+info.name+"</b><b class='fileSize mr10'>"+(info.size/1024/1024).toFixed(2)+"MB</b></span><span class='progressBox mr20'><b class='progressBar mr10'><em class='progressRate'>&nbsp;</em></b>已上传<b class='uploaded'>0%</b></span><span class='uploadOperate'><a class='startUpload' href='javascript:;' file='"+elm+"'>开始上传</a></span></li>";
				window["upload_"+queId].startUpload();
			}
	
		});
		window["upload_"+queId].uploadEvent.add("onCancel",function(){
			var data = arguments[0].message;
			console.log("取消选择框",data);
		});
		window["upload_"+queId].uploadEvent.add("onOpen",function(){
			var elm = arguments[0].message[0],
				info = arguments[0].message[1];
			var myUploadOperate = $class("uploadOperate",$id(elm))[0];
			myUploadOperate.innerHTML = "<a class='cancelUpload' href='javascript:;' file='"+elm+"'>取消上传</a>";
		});
		
		window["upload_"+queId].uploadEvent.add("onStop",function(){
			var elm = arguments[0].message[0];
			var myUploadOperate = $class("uploadOperate",$id(elm))[0];
			myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
		});
		window["upload_"+queId].uploadEvent.add("onProgress",function(){
			var elm = arguments[0].message[0],
				c = arguments[0].message[1],
				t = arguments[0].message[2];
			var myProgress = $class("progressRate",$id(elm))[0],
				myUploaded = $class("uploaded",$id(elm))[0];
			myProgress.style.width = (c/t*100 >>0)+"%";
			myUploaded.innerHTML = (c/t*100 >>0)+"%";
		});
		window["upload_"+queId].uploadEvent.add("onComplete",function(){
			var elm = arguments[0].message[0],
				data = arguments[0].message[1];
			var myProgressBox = $class("progressBox",$id(elm))[0],
				myUploadOperate = $class("uploadOperate",$id(elm))[0];
			data = JSON.parse(data);
			if(data && data.result){
				myProgressBox.innerHTML = "上传完成！";	
				myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
				$("#uploadInfo_"+queId).attr("answerVideo",data.message);
			}else{
				myProgressBox.innerHTML = "上传失败！";	
				myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
			}
		});
		window["upload_"+queId].uploadEvent.add("onFail",function(){
			var elm = arguments[0].message[0],
				data = arguments[0].message;
			var myProgressBox = $class("progressBox",$id(elm))[0],
				myUploadOperate = $class("uploadOperate",$id(elm))[0];
			myProgressBox.innerHTML = "上传失败！";	
			myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
			console.log(data);
		});
		
		events.delegate($id("uploadCont_"+queId),".startUpload","click",function(){
			//手动开始（单个）上传
			var e = arguments[0] || window.event,
				target = e.srcElement || e.target,
				fileIndex = target.getAttribute("file");
			window["upload_"+queId].startUpload("",fileIndex); //第一个参数可配置上传serve地址，留空则使用params中配置的server url
		});
		events.delegate($id("uploadInfo_"+queId),".cancelUpload","click",function(){
			//取消（单个）上传
			var e = arguments[0] || window.event,
	
				target = e.srcElement || e.target,
				
				fileIndex = target.getAttribute("file");

			window["upload_"+queId].cancelUpload(fileIndex);
		});
		events.delegate($id("uploadInfo_"+queId),".delUploadFile","click",function(){
			//删除
			var e = arguments[0] || window.event,
				target = e.srcElement || e.target,
				fileIndex = target.getAttribute("file"),
				liElm = $id(fileIndex);
			liElm.parentNode.removeChild(liElm);
		});
	});
});

</script>
</html>