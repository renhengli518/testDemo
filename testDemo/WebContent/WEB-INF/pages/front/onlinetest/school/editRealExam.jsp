<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp"%>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/reset.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/workplace.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/workflat.css"/>
<script src="${root}/biz/questionlib/public.js"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/upload/uploadfile.js"></script>
<script type="text/javascript" src="${root}/biz/js/pageNavigate.js"></script>
<script type="text/javascript" src="${root}/biz/js/realExamSetScore.js"></script>
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
<script type="text/javascript" charset="utf-8" src="${root}/plug  in/ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
<head>
<style>
.edit-cont {
	display: none;
}

.delete {
	position: absolute;
	right: 15px;
	top: 0
}

.optDiv li {
	margin-bottom: 5px;
}

.optDiv .opt {
	cursor: pointer
}

.optDiv .content_title {
	width: 85%;
	display: inline-block;
	vertical-align: top;
	word-break: break-all;
	line-height: 24px;
}

.optDiv .content_title img {
	max-width: 100%;
}

td {
	text-align: left;
}

.selectBox ul li {
	line-height: 40px;
}

.opt_title {
	display: inline-block;
	line-height: 24px;
	vertical-align: top;
}

.for-answer {
	width: 154px;
}

.for-answer img {
	max-width: 154px;
}
.fixedWidthSpan1{display: inline-block;min-width: 130px;}
 .testCon.testConLeastMargin .quesDesc .editOrDel span {
    color: #4dadc6;
    cursor: pointer;
    display: inline-block;
    margin: 0;
    margin-right:0
}
 .testCon.testConLeastMargin .quesDesc .editOrDel span.red{color:#e53333}
 .delete{width:40px; height:20px; line-height:20px;padding-left:17px;top:10px;background-position: 0 4px;}
 
 .table_th {
  text-align: center;
  border: 1px solid #e0e0e0;
  min-width: 110px;
  line-height: 32px;
  }
</style>
</head>
<body class="editQuestion">
    <%@include file="../../../common/topHeader.jsp"%>
	<%@include file="../../../common/queNav.jsp"%>
     <div class="editQues">
        <div>
          <a href="${root}/questionLib/toSharedLib.html">题库</a><span class="nextArrow">&nbsp;&gt;&nbsp;</span><a href="${root}/schoolTest/toRealExam.html">真题试卷</a>
           <span class="nextArrow">&nbsp;&gt;&nbsp;</span>
           <span class="currentLevel">编辑试卷</span>
        </div>
        
        <!-- 内容区 -->
        <form name="addForm" id="addForm" method="post" >
        	<!-- <input type="hidden" id="examQueList" name="examQueList"></input> -->
        	<input type="hidden" id="examId" name="examId" value="${examId}"></input>
	         <div class="editQuesContent mt20">
			     <p class="LH36">
			       <span class="W90"><b class="red">*</b>试卷名称：</span><input id="examTitle" value="${examExam.title}" name="examTitle" type="text" class="W470"><br/>
			       <span class="W90"><b class="red">*</b>地区</span>
			       <select class="newSel w160 mr30" id="areaName" name="areaName" disabled="disabled">
			       		<option selected="selected">${examExam.areaName}</option>   
			        </select>
			        <span class="W90"><b class="red">*</b>年份</span>
			        <select class="newSel w160 mr30" id="year" name="year" disabled="disabled" >
			          <option value="0">请选择</option>
			          <c:forEach items="${years}" var="year" >
			          	<option <c:if test='${year eq examExam.year}'>selected="selected"</c:if>>${year}</option>
			          </c:forEach>
			          <option>其他</option>
			        </select>
			       <span class="W90"><b class="red">*</b>试卷类型</span> 
			       <select class="newSel w160 mr30" id="examtypeOpts" name="examtype" disabled="disabled">
						<option value="0">请选择 </option>
						<c:forEach items="${examTypes}" var="examType">
							<option value="${examType.id }" selected="<c:if test='${examType.id eq examExam.examTypeId}'></c:if>" >${examType.name}</option>
						</c:forEach>
				   </select>
					<br/>
			       <span class="W90"><b class="red">*</b>学段</span>
			       <select class="newSel w160 mr30" id="semesterOption"  name="semesterOpts" disabled="disabled">
						<option value="0">请选择 </option>
						<c:forEach items="${semesters}" var="semester">
							<option value="${semester.baseSemesterId }" <c:if test='${semester.baseSemesterId eq examExam.baseSemesterId }'>selected="selected"</c:if> >${semester.semesterName}</option>
						</c:forEach>
					</select>
			        <span class="W90"><b class="red">*</b>年级</span>
			       <select class="newSel w160 mr30" id="classLevelOption" name="classlevelOpts" disabled="disabled">
			         <option value="0">请选择 </option>
			       </select>
			        <span class="W90"><b class="red">*</b>学科</span>
			       <select class="newSel w160 mr30" id="subjOption" name="disciplineOpts" disabled="disabled">
			         <option value="0">-- 请选择 --</option>
			       </select><br/>
			       <span class="W90"><b class="red">*</b>答题时长</span>
			       <input type="text" placeholder="请输入答题时长" id="answerTime"  name="answerTime" value="${examExam.answerTime}">&nbsp;分钟<span class="c888 ml10">最多输入三位数</span>
			       <br/>
			       <span class="W90"><b class="red">*</b>试卷总分</span>
			       <input type="text" placeholder="请输入试卷总分" id="scoreInput"  name ="scoreInput" value="${examExam.score}">&nbsp;分<span class="c888 ml10">最多输入三位数</span><br/>
			       <span class="W90"><b class="red"></b>权限</span>
			       <label >
			         <input type="checkbox" id="publicFlag" name="publicFlag" value="N">&nbsp;仅供学校内使用 
			         <span class="ml40">注：勾选此选项，则试卷只能在学校使用</span>
			       </label>
			      
			     </p>
			 </div>
			 <h3 class="inputQues">试题录入</h3>
			 <div id="question_content">
				<p class="associate">关联章节</p>
				<div class="borderBox pd20">
					版本：<select class="newSel w160 mr20" id="versionOption">
						<option value="0">请选择</option>
					</select> 分册：<select class="newSel w160 mr20" id="volumeOption">
						<option value="0">请选择</option>
					</select> 章：<select class="newSel w160 mr20" id="chapterOption">
						<option value="0">请选择</option>
					</select> 节：<select class="newSel w160 mr20" id="sectionOption">
						<option value="0">请选择</option>
					</select>
					<ul class="commonUL ml30 mt20">
						<li style="line-height: normal"><a id="addChapterBtn" href="javascript:;" style="margin-top: -25px;"
							class="btn addChapter">增加章节</a></li>
						<li>
							<div id="selectedChapters"></div>
						</li>
					</ul>
				</div>
		
				<p class="associate">关联知识点</p>
				<div class="selectBox borderBox pd20">
					<ul class="commonUL questionItem mainUl">
						<li><label>知识点：</label> <select id="knowledgeOpts" class="newSel w160 mr20">
								<option value="0">请选择</option>
						</select> <select id="knowledgeOpts1" class="newSel w160 mr20">
								<option value="">请选择</option>
						</select> <select id="knowledgeOpts2" class="newSel w160 mr20">
								<option value="">请选择</option>
						</select> <select id="knowledgeOpts3" class="newSel w160 mr20">
								<option value="">请选择</option>
						</select> <select id="knowledgeOpts4" class="newSel w160 mr20">
								<option value="">请选择</option>
						</select> <select id="knowledgeOpts5" class="newSel w160 mr20">
								<option value="">请选择</option>
						</select></li>
						<li style="line-height: normal"><a id="addKnowledgeBtn" href="javascript:;" style="margin-top: -25px;"
							class="btn addChapter">增加知识点</a></li>
						<li>
							<div id="selectedKnowledges"></div> <input type="hidden" id="kn_input" name="kn_input">
						</li>
					</ul>
				</div>
		
				<ul class="commonUL questionItem mainUl" data-qusetionIndex = '0'>
					    <li>
					      <label class="text left"><span class="red">*</span>难易度</label>
					      <span class="cont">
								<select id="difficultyOpts" name="difficultyOpts" class="newSel w160">
									<option value="">请选择</option>
								</select>
							</span>
					    </li>
					    <li>
					    	<label class="text left"><span class="red">*</span>习题类型</label>
					    	<span class="cont questionTypeOpts">
								<label class="mr20"><input type="radio" name="questionTypeOpts" index="1" checked="checked" value="SINGLE_CHOICE" />单选题</label>
								<label class="mr20"><input type="radio" name="questionTypeOpts" index="2" class="ml20" value="MULTI_CHOICE" />多选题</label>
								<label class="mr20"><input type="radio" name="questionTypeOpts" index="6" class="ml20" value="JUDEMENT" />判断题</label> 
								<label class="mr20"><input type="radio" name="questionTypeOpts" index="3" class="ml20" value="FILL_IN_BLANK" />填空题</label>
								<label class="mr20"><input type="radio" name="questionTypeOpts" index="4" class="ml20" value="ASK_ANSWER" />问答题</label>
								<label class="mr20"><input type="radio" name="questionTypeOpts" index="5" class="ml20" value="COMPUTING" />计算题</label>
							</span>
					    </li>
					  <li >
					<label class="text left"><span class="red">*</span>习题题干</label>
					<!-- 获得已经删除的题目的下标组成的字符串-->
					<input type="hidden" id="deleStr" name="deleteFlagStr"/>
					<span class="cont verticalMiddle"> <!--  富文本框-->
						<div class="cCont">
							<script class="edtContent edtContentQue" name="questionSubArra[0].edt_content" type="text/plain" style="height: 200px; width: 99.8%"></script>
						<a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:20px;">
	                                                     上传音视频<span id="uploadCont_content0" class="uploadCont"> </span>
                        </a>
                        <!-- 已上传文件的显示区域-->
                        <!-- 上传文件显示区域-->
                        <span id="uploadInfoBox_content0flag">
                          <ul id="uploadInfoBox_content0" class="commonUL">
			
		                  </ul>
		                 </span>
							
							<ul class="optDiv"></ul>
							<ul class="singleOpt-Answer"></ul>
						</div>
					</span>
				</li>
				<li class="clearfix fillIn-Answer" >
					<label style="float: left; margin-left: 30px;">答案：</label>
					<div class="cCont multi-choice-answer ofH">
						<div class="answerTypeDiv ">
							<label><input type="radio" name="questionSubArra[0].rdo_answerType" class="rdo_answerType" checked="checked" value="1" />独立答案</label>
							<label><input type="radio" name="questionSubArra[0].rdo_answerType" class="rdo_answerType ml20" value="2" />组合答案</label>
						</div>
						<input type="hidden" class="fillCount" id="fillInAnswerCnt0" name="questionSubArra[0].fillInAnswerCnt" value="0" />
						<table class="normalTable indep_answer anyTable">
							<thead>
								<tr>
									<th>
										<div class="specialTH">
											<span class="rightTop">答案容错</span>
											<span class="leftBottom">填空</span> 
										</div>
									</th>
									<th>答案1</th>
									<th>答案2</th>
									<th>答案3</th>
									<th>答案4</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
						<table class="normalTable comb_answer anyTable" style="display: none">
							<thead>
								<tr>
									<th>
										<div class="specialTH">
											<span class="rightTop">答案容错</span>
											<span class="leftBottom">填空</span> 
										</div>
									</th>
									<th>第一组</th>
									<th>第二组</th>
									<th>第三组</th>
									<th>第四组</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
						<div class="scoreTypeDiv">
							<label><input type="radio" name="questionSubArra[0].rdo_scoreType" class="rdo_scoreType" checked="checked" value="ALL_CORRECT" />全对给分</label>
							<label><input type="radio" name="questionSubArra[0].rdo_scoreType" class="ml20 rdo_scoreType" value="SCORE_BY_FILL" /> 按空给分</label>
						</div>
					</div>
					<br>
				</li>
				<li>
					<label class="text left"><span class="red">*</span>习题解析</label>
					
					<span class="cont">
						<div >
							<script class="edtContent" name="questionSubArra[0].edt_resolve" type="text/plain" style="height: 200px; width: 99.8%"></script>
						</div>
						<a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:20px;">
	                                                     上传视频解析<span id="uploadCont_resolve0" class="uploadCont"> </span>
                        </a>
                       <!-- 上传文件显示区域-->
                        <span id="uploadInfoBox_resolve0flag">
                          <ul id="uploadInfoBox_resolve0" class="commonUL">
			
		                  </ul>
		                </span>
						<br/>
					</span>
				</li> 	
				</ul>
			 </div>
			
			
	 
			
        </form>
		<ul>
			<li><label class="text left"> <a href="javascript:;" id="addExamQue">+添加试题</a>
			</label></li>
			<li class="center mt30">
				<button class="btn bigBtn saveAndSubmit mr20" onclick="tosetscore()" style="margin-bottom: 20px;">编辑题目分值</button> 
				<label class="text">&nbsp;&nbsp;</label> <span class="cont" id="questionList"> </span>
			</li>
		</ul>

	</div>
     <script type="text/javascript">
		var public_path = "${PUBLIC_PATH}";
		var root = "${root}";
		var examId = "${examId}";
		var baseClasslevelId = "${examExam.baseClasslevelId}";
		var baseSubjectId = "${examExam.baseSubjectId}";
		var score = "${examExam.score}";
	</script>
     <script type="text/javascript" src="${root}/biz/js/editRealExam.js" ></script>
     <script type="text/javascript"> 
     /***进行音视频的上传操作**/
     function initSwf(id){
     	 var params="";
     	 var index=id.substring(id.length-1);
     	 var spanJudge="#uploadInfoBox"+id+"flag";//每个视频，音频只能添加一个
     	 if(id.indexOf("content")>0){//题干既可以播放视频也可以播放音频
     		 params= {
     		         debug : 1,
     		         fileType : "*.mp4;*.flv;*.mp3;",
     		         typeDesc : "音频",
     		         sizeLimit : 100*1024*1024,
     		         autoStart : true,
     		         multiSelect : 0,  //表示每次只能上传一个     1 表示可以进行批量上传
     		         server: encodeURIComponent("${LOCAL_PATH}/videoUpload")
     		 	}; 
     	 }else{//解析不能播放音频
     		 
     		 params= {
     		         debug : 1,
     		         fileType : "*.mp4;*.flv;",
     		         typeDesc : "音频",
     		         sizeLimit : 100*1024*1024,
     		         autoStart : true,
     		         multiSelect : 0,  //表示每次只能上传一个     1 表示可以进行批量上传
     		         server: encodeURIComponent("${LOCAL_PATH}/videoUpload")
     		 	}; 
     	 }

     	 window["uploadSwf"+id] = new UploadFile($id("uploadCont"+id), "uploadSwf"+id, "${PUBLIC_PATH}/public/upload/uploadFile.swf",params);
     	  	 	
     	  	 	
     	 window["uploadSwf"+id].uploadEvent.add("onComplete",function(){
     	  	 		var elm = arguments[0].message[0],
      	 			data = arguments[0].message[1];
      			var myProgressBox = $class("progressBox",$id(elm))[0],
      				myUploadOperate = $class("uploadOperate",$id(elm))[0];
      			
     	  	 		if(id.indexOf("content")>0){
     	  				myProgressBox.innerHTML = "上传完成!<input type ='hidden' name='questionSubArra["+index+"].contentVideo' id="+index+"contentVideo  value="+JSON.parse(data).message+">";	
     	  			    myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";

     	  			}else{
     	  				myProgressBox.innerHTML = "上传完成!<input type ='hidden' name='questionSubArra["+index+"].resolveVideo' id="+index+"resolveVideo  value="+JSON.parse(data).message+">";	
     	  			    myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
     	  			}

     	  	 	});
     	  	 	

     	  	 	events.delegate($id("uploadInfoBox"+id),".delUploadFile","click",function(){
     	  	 		//删除
     	  	 		var e = arguments[0] || window.event,
     	  				target = e.srcElement || e.target,
     	  				fileIndex = target.getAttribute("file"),
     	  				liElm = $id(fileIndex);
     	  	 		liElm.parentNode.removeChild(liElm);
     	  	 	});
     	  	 	
     	  	 	//显示上传的文件信息
     	  	 	window["uploadSwf"+id].uploadEvent.add("onOpen",function(){
     	  	 	var elm = arguments[0].message[0],
	  	 			info = arguments[0].message[1];
     	  	 	var videoName=info.name;
	  	 		var index1=videoName.lastIndexOf(".");
 		        var index2=videoName.length;
 		        var postf=videoName.substring(index1,index2); //获取文件后缀名
 		       if($(""+spanJudge+" ul li").size()>=1){
	  	 			//alert("size="+$(""+spanJudge+" ul").size());
	  	 			Win.alert("请删除已有的视频才可上传新的视频文件!");
					return;
	  	 		} 
 		       
 		        if(postf.indexOf("mp3")>0){//表示是音频
 		        	
 		        	//alert("size="+info.size);
 		            if(info.size>100*1024*1024){
 		            	
 		            	Win.alert("上传的音频大小必须在100M以内才可上传!");
 						 return;
 		            }
 		        }else{//表示进行视频上传
 		        	
                      if(info.size>500*1024*1024){
 		            	
 		            	Win.alert("上传的视频大小必须在500M以内才可上传!");
 						 return;
 		            }
 		        }
	        
	  	 		if($id(elm)) return false;
	  	 		$id("uploadInfoBox"+id).innerHTML += "<li id='"+elm+"'><span class='infoLabel'><b class='fileName mr10' title='"+info.name+"'>"+info.name+"</b><b class='fileSize mr10'></b></span><span class='progressBox mr20'><b class='progressBar mr10'><em class='progressRate'>&nbsp;</em></b>已上传<b class='uploaded'>0%</b></span><span class='uploadOperate'><a class='cancelUpload' href='javascript:;' file='"+elm+"'>取消上传</a></span></li>";
     	  	 	});
             
     	  	 	window["uploadSwf"+id].uploadEvent.add("onProgress",function(){
     	  	 		var elm = arguments[0].message[0],
     	  	 			c = arguments[0].message[1],
     	  	 			t = arguments[0].message[2];
     	  			var myProgress = $class("progressRate",$id(elm))[0],
     	  				myUploaded = $class("uploaded",$id(elm))[0];
     	  			myProgress.style.width = (c/t*100 >>0)+"%";
     	  			myUploaded.innerHTML = (c/t*100 >>0)+"%";
     	  	 	});


     	  	 	window["uploadSwf"+id].uploadEvent.add("noticeSizeError",function(){
     	  	 		var elm = arguments[0].message[0],
     	  	 			limite = arguments[0].message[1],
     	  	 			info =  arguments[0].message[2];
     	  	 		if($id(elm)) return false;
     	  	 	    Win.alert("上传的文件必须在500M以内方可上传!");
     	  	 		//$id("uploadInfoBox"+id).innerHTML += "<li id='"+elm+"'><span class='infoLabel'><b class='fileName mr10' title='"+info.name+"'>"+info.name+"</b><b class='fileSize mr10'>"+(info.size/1024/1024).toFixed(2)+"MB</b></span><span class='mr20'>上传文件过大，限制大小"+(limite/1024/1024)+"MB</span><span class='uploadOperate'><a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a></span></li>";
     	  	 	});
     	  	 	
     	  	 window["uploadSwf"+id].uploadEvent.add("onFail",function(){
 	  	 		var elm = arguments[0].message[0],
 	  	 			data = arguments[0].message;
 	  			var myProgressBox = $class("progressBox",$id(elm))[0],
 	  				myUploadOperate = $class("uploadOperate",$id(elm))[0];
 	  			myProgressBox.innerHTML = "上传失败！";	
 	  			myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
 	  			console.log(data);
 	  	 	});
 	  	 	
 	  	 	events.delegate($id("uploadInfoBox"+id),".cancelUpload","click",function(){
 	  	 		//取消（单个）上传
 	  	 		//alert("delegate");
 	  	 		var e = arguments[0] || window.event,
 	  				target = e.srcElement || e.target,
 	  				fileIndex = target.getAttribute("file");
 	  	 		window["uploadSwf"+id].cancelUpload(fileIndex);
 	  	 	});
 	  	 	
 	  	 	window["uploadSwf"+id].uploadEvent.add("onStop",function(){//暂停上传 并可删除
 	  	 		var elm = arguments[0].message[0];
 	  	 		var myUploadOperate = $class("uploadOperate",$id(elm))[0];
 	  	 		myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
 	  	 	});

     }
	   //初始化母题上传文件的题干和解析要上传的视频准备
	   	initSwf("_resolve"+0);//初始化解析上传
	   	initSwf("_content"+0);//初始化题干
	   	
		
     </script>
  <%@ include file="setScore.jsp"%> 
</body>
</html>