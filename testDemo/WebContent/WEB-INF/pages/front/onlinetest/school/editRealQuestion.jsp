<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp"%>
<script type="text/javascript" src="${PUBLIC_PATH}/public/upload/uploadfile.js"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/css/4.0/resplayer.css"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/player/videoplayer.js"></script>
<%-- <script type="text/javascript" src="${PUBLIC_PATH}/public/css/index.css"></script> --%>
<script type="text/javascript" src="${PUBLIC_PATH}/public/js/extend.js"></script>
<link href="${PUBLIC_PATH}/public/css/reset.css" rel="stylesheet" type="text/css" media="all">
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/reset.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/workplace.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/workflat.css"/>

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
 .delete{width:40px; height:20px; line-height:20px;padding-left:17px;top:10px;background-position: 0 4px;}
  .table_th {
  text-align: center;
  border: 1px solid #e0e0e0;
  min-width: 110px;
  line-height: 32px;
  }
</style>
<script>
			 var semesterId = '${questionObj.baseSemesterId}';
			 var classLvlId = '${questionObj.baseClasslevelId}';
			 var disciplineId = '${questionObj.baseSubjectId}';

    $(
    		function(){
    			//初始化版本内容
    			onloadVersion();
    			function onloadVersion(){
    						
    						$.post("${root}/commons/versionbysuclaid.do?baseClassLevelId=" + '${questionObj.baseClasslevelId}'
    								+ "&baseSubjId=" + '${questionObj.baseSubjectId}', function(data) {
    							var html = '<option value="0">请选择</option>';
    							for (var i = 0; i < data.length; i++) {
    								html += "<option value="+data[i].baseVersionId+">" + data[i].versionName + "</option>";
    							}
    							$("#versionOption").html(html);
    							
    						}, 'json');
    						
    						//初始化知识点
    						 //绑定首节知识点
    				   	    bindKnowledgeBySemesterAndDiscipline('${questionObj.baseSemesterId}','${questionObj.baseSubjectId}');
    				   	    
    				   	    $("#knowledgeOpts1").empty();
    				   	    $("#knowledgeOpts1").append(emptyOpt);
    				   	    $("#knowledgeOpts1").hide();
    				   	    
    				   	    $("#knowledgeOpts2").empty();
    				   	    $("#knowledgeOpts2").append(emptyOpt);
    				   	    $("#knowledgeOpts2").hide();
    				   	    
    				   	    $("#knowledgeOpts3").empty();
    				   	    $("#knowledgeOpts3").append(emptyOpt);
    				   	    $("#knowledgeOpts3").hide();
    					
    				   	    $("#knowledgeOpts4").empty();
    				   	    $("#knowledgeOpts4").append(emptyOpt);
    				   	    $("#knowledgeOpts4").hide();
    				   	    
    				   	    $("#knowledgeOpts5").empty();
    				   	    $("#knowledgeOpts5").append(emptyOpt);
    				   	    $("#knowledgeOpts5").hide();
    				   	    
    				   	    $("#selectedKnowledges span").remove();
    				   	    $("#selectedChapters span").remove();	
    					}
    			showEditchapterContent();//显示已有的章节内容
    			showEditKnowContent();//显示知已有的知识点内容
    		}
     )
</script>
</head>
<body class="editQuestion">
	<script src="${root}/biz/questionlib/public.js"></script>
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
		<form name="editForm" id="editForm" method="post" class="mainUl">
		    <!-- 将要进行修改的习题的id作为隐藏域-->
		    <input type="hidden" id="currentQuestionId" value="${questionObj.examQuestionId}" name="currentQuestionId"/>
		    <!-- 将要编辑的题的所属类型(母题，衍生题或孪生题)-->
		    <input type="hidden" value="${questionObj.relationalIndicator}" name="relationalIndicator"/>
		    <!-- 将本题的motherId作为一个隐藏域-->
		    <input type="hidden" value="${questionObj.motherId}" name="currentQuestionIdMotherId"/>
		    <!-- 将要编辑的题的所属真题试卷ID-->
		    <input type="hidden" value="${questionObj.examId}" name="examId"/>
			<!-- 内容区 -->
			<div class="editQuesContent mt20">
				<span class="mr50"><b class="red">*</b>学段:</span>
				<span style="margin-left:-45px;">${requestScope.semerName}</span>
				
				 <span class="mr50" style="margin-left:200px;"><b class="red">*</b>年级:</span> 
				 <span style="margin-left:-45px;">${requestScope.classLevelName}</span>
			
				<span class="mr50" style="margin-left:200px;" ><b class="red">*</b>学科:</span> 
				<span style="margin-left:-45px;">${subjectName}</span>
				
			</div>
			<p class="associate"></p>
			<h5>关联章节：</h5>
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
					<li style="line-height: normal"><a id="addChapterBtn" href="javascript:;" class="btn addChapter">增加章节</a></li>
					<li>
						<div id="selectedChapters">
						</div> 
					</li>
				</ul>
			</div>
			
			<h5>关联知识点：</h5>
					<div class="selectBox borderBox pd20">
					<ul>
						<li>
							<label >知识点：</label>
							<select id="knowledgeOpts">
							    <option value="0">请选择</option>
							</select>
									
							<select id="knowledgeOpts1">
							    <option value="">请选择</option>
							</select>
							
							<select id="knowledgeOpts2">
							    <option value="">请选择</option>
							</select>
		
							<select id="knowledgeOpts3">
							    <option value="">请选择</option>
							</select>
							
							<select id="knowledgeOpts4">
							    <option value="">请选择</option>
							</select>
							
							<select  id="knowledgeOpts5">
							    <option value="">请选择</option>
							</select>
						</li>
						<li style="line-height:normal"><a id="addKnowledgeBtn" href="javascript:;" class="btn addChapter">增加知识点</a></li>
						<li>
							<div id="selectedKnowledges">
							</div>
							<input type="hidden" id="kn_input" name="kn_input">
						</li>
					</ul>
				</div>
			
	
			<ul class="commonUL  questionItem" data-qusetionIndex = '0'>
				<li><label class="text left"><span class="red">*</span>难易度</label>
					<span class="cont">
						<select id="difficultyOpts" name="difficultyOpts" class="newSel w160">
							<option value="">请选择</option>
						</select>
					</span>
				</li>
				<li><label class="text left"><span class="red">*</span>习题类型</label>
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
							<script class="edtContent edtContentQue edtContentMain" name="questionSubArra[0].edt_content" type="text/plain" style="height: 200px; width: 99.8%">${questionObj.content}</script>
							<a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:80px;">
	                                                             上传音视频<span id="uploadCont_content0" class="uploadCont"> </span>
                            </a>
                            
                           <span id="uploadCont_content0flag">
                              <c:if test="${questionObj.contentVideo!=null}">
                                  <p class="0contentVideo">${questionObj.contentVideo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:removeHidden("0contentVideo")'  class='delUploadFile' >删除</a>&nbsp;&nbsp;&nbsp;<a style='color:green;' class="watch_play0c"  href='javascript:playVideo("${questionObj.contentVideo}","0c")'>点击播放</a><span id='watch_play0c'></span></p> <input type ='hidden' name='questionSubArra[0].contentVideo' id="0contentVideo" value="${questionObj.contentVideo}"/>
                              </c:if>
                                   <!-- 上传文件显示区域-->
                               <span id="uploadInfoBox_content0flag">
		                             <ul id="uploadInfoBox_content0" class="commonUL">
					
				                     </ul>
				               </span>     
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
							<label><input type="radio" id="combine1" name="questionSubArra[0].rdo_answerType" class="rdo_answerType" checked="checked" value="1" />独立答案</label>
							<label><input type="radio" id="combine2" name="questionSubArra[0].rdo_answerType" class="rdo_answerType ml20" value="2" />组合答案</label>
						</div>
						
						<input type="hidden" class="fillCount" id="fillInAnswerCnt0" name="questionSubArra[0].fillInAnswerCnt" value="0" />
						
						<table class="normalTable indep_answer anyTable">
							<thead>
								<tr>
									<th>
										<div class="specialTH">
											<span class="leftBottom">填空</span> 
											<span class="rightTop">答案容错</span>
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
											<span class="leftBottom">填空</span> 
											<span class="rightTop">答案容错</span>
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
							<label><input type="radio" id="radio1" name="questionSubArra[0].rdo_scoreType" class="rdo_scoreType" checked="checked" value="ALL_CORRECT" />全对给分</label>
							<label><input type="radio" id="radio2" name="questionSubArra[0].rdo_scoreType" class="ml20 rdo_scoreType" value="SCORE_BY_FILL" /> 按空给分</label>
						</div>
					</div>
					<br>
				</li>
				<li>
					<label class="text left"><span class="red">*</span>习题解析</label>
					<span class="cont">
						<div>
							<script class="edtContent" name="questionSubArra[0].edt_resolve" type="text/plain" style="height: 200px; width: 99.8%">${questionObj.resolve}</script>
						</div>
						<a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:80px;">
	                                                     上传视频解析<span id="uploadCont_resolve0" class="uploadCont"> </span>
                        </a>
                        <span id="uploadCont_resolve0flag">
                         <c:if test="${questionObj.resolveVideo!=null}">
                             <p class="0resolveVideo">${questionObj.resolveVideo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:removeHidden("0resolveVideo")'  class='delUploadFile'>删除</a>&nbsp;&nbsp;&nbsp;<a style='color:green;' class="watch_play0s"  href='javascript:playVideo("${questionObj.resolveVideo}","0s")'>点击播放</a><span id='watch_play0s'></span></p> <input type ='hidden' name='questionSubArra[0].resolveVideo' id="0resolveVideo" value="${questionObj.resolveVideo}"/>
                         </c:if>
                         <!-- 上传文件显示区域-->
                         <span id="uploadInfoBox_resolve0flag">
	                          <ul id="uploadInfoBox_resolve0" class="commonUL">
				
			                  </ul>
		                  </span>
					     </span>
                        </span>
						<br/>
						 
				</li>				
			</ul>
		</form>
		 <div class="center mt30"> <button id="saveEdit" class="btn bigBtn saveAndSubmit mr20" >保存修改</button></div>
		
	<script type="text/javascript">
	var domid = frameElement.getAttribute("domid");
	var emptyOpt = '<option value="0">请选择</option>';
	var motherId='${questionObj.motherId}';//如果motherId为''则表示是母题显示添加子项    如果motherId!=''则表示是子题，隐藏添加子项的链接
	if(''!=motherId){
		//隐藏添加子项
		$(".addQuestionItem").hide();
	}
	$(function () {
		var undefined;
		var uuid = 1;
		var qusetionIndex = 1;
		var deleQuestionStr="";
		
		var emptyOpt = '<option value="0">请选择</option>';//通用成员变量
		//绑定难易程度下拉框
		bindDifficulty();
		
		 var questionId = '${questionObj.examQuestionId}';
		 var $difficulty = '${questionObj.difficulty}'; 
		 var questionType='${questionObj.type}'; 
		 var content= '${questionObj.content}';
		 var resolve='${questionObj.resolve}';
		 var answer='${questionObj.answer}';
		 var $fillInScoreType='${questionObj.fillInScoreType}';
		 var resolveVideo='${questionObj.resolveVideo}';
		 var conentVideo='${questionObj.contentVideo}';
		 var motherId='${questionObj.motherId}';
		 var relationalIndicator='${questionObj.relationalIndicator}';
		 var options='${questionObj.options}';
		 var answerStr='${questionObj.fillAnstwerStr}';
		 var fillInAnswerType=('${questionObj.fillInAnswerType}'=='COMBINATION'?2:1);
		 
		 //将难易度进行填充  difficultyOpts来绑定指定的select
		 $("#difficultyOpts option[value="+$difficulty+"]").attr("selected","selected");
			 
	     //全对给分还是按空给分
	      if("ALL_CORRECT"==$fillInScoreType){
	    	 //全对给分
	    	 $("#radio1").attr("checked","checked");
	     }else{
	    	 $("#radio2").attr("checked","checked");//按空给分
	     }
		 
		UE.registerUI('blank', UE_UI_BLANK);
		var loadUEdit = function () {
			$('.edtContent').each(function () {
				if (!this.id) {
					var key = "UEdit_" + uuid++;
					this.id = key;
					UE.getEditor(key);

					if (!$(this).hasClass('edtContentQue')) {
						UE.getEditor(key).addListener('ready', function() {
							$("#"+  key + " .edui-for-blank > div").hide();								
						});
					} else {
						var isMain = $(this).hasClass('edtContentMain');
						UE.getEditor(key).addListener('ready', function() {
							if ('FILL_IN_BLANK' != $("input[name='questionTypeOpts']:checked").val()) {
								$("#"+  key + " .edui-for-blank > div").hide();
							}
							if (isMain) {
								var $box = $('.questionItem:eq(0)');
								$box.find('.rdo_answerType:eq(' + (fillInAnswerType - 1) + ')').click();
								var $tab = $box.find('.indep_answer');
								if (fillInAnswerType == 2) {
									$tab = $box.find('.comb_answer');
								}
								var trs = answerStr.split(':::::::::::::');
								for (var i = 0, len = trs.length; i < len; i++) {
									var cols = trs[i].split('∷');//ssssssssss
									var $tr = $tab.find('tr:eq(' + (i + 1) + ')');
									for (var j = 0, l = cols.length; j < l; j++) {
										if (cols[j]) {
											$tr.find('.edit-pop-btn:eq(' + j + ') span').html(cols[j]);
											$tr.find('.edit-pop-btn:eq(' + j + ') textarea')[0].value = cols[j];
											$tr.find('.edit-pop-btn:eq(' + j + ') textarea')[1].value = cols[j];
											$tr.find('.edit-pop-btn:eq(' + j + ') textarea')[0].innerHTML = cols[j];
											$tr.find('.edit-pop-btn:eq(' + j + ') textarea')[1].innerHTML = cols[j];
										}
									}
								}
							}
						});
						UE.getEditor(key).addListener('contentChange', function() {						
							var type = $("input[name='questionTypeOpts']:checked").val();
							if (type != "FILL_IN_BLANK")
								return;
							var $box = $('#' + key).parents('.questionItem');
							var questionIndex = $box.attr('data-qusetionindex');
							
							$box = $box.find('.fillIn-Answer');
							
							var content = this.getContent();
							var $tmp = $('<div>' + content + '</div>');
							var $fillChar = $tmp.find(".question-blank-space");
							var len = $fillChar.size();
							//$("#fillInAnswerCnt").val(len);//获得每道题空的个数     获得每道填空题的个数
							
							
							$("#fillInAnswerCnt" + questionIndex).val(len);
							
							//alert("填空的个数:"+len+"======题目="+questionIndex);
							
							
							$fillChar.each(function(index) {
								var $elm = $(this);
								var alt = index + 1;
								var uuid = $elm.attr("uuid");
								var $tr = $(".indep_answer tr.aTr" + uuid, $box);
								if ($tr.length == 0) {
									var html_indep = "<tr class=\"aTr" + uuid + "\" >";
									html_indep += "<td  style='font-weight:bolder;' class='indepBank table_th'>第" + formSeq(alt) + "空</td>";
									html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+'_'+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "</div></td>";
									html_indep += "<td  class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "</div></td>";
									html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "</div></td>";
									html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "</div></td>";
									html_indep += "</tr>";
									$(".indep_answer tr:eq(" + index + ")", $box).after(html_indep);
								} else if ($tr.index() != index) {
									$tr.find('.indepBank').html('第' + formSeq((index + 1)) + '空');
									$tr.find('textarea').each(
											function(index) {
												this.name = (index % 2 == 0 ? 'indep_'
														: 'indep_txt_')
														+questionIndex+"_"+ alt + '_' + (((index + 2) / 2) >> 0);
											});
									$(".indep_answer tr:eq(" + index + ")", $box).after($tr);
								}
								var $tr = $(".comb_answer tr.aTr" + uuid, $box); 
								if ($tr.length == 0) {
									var html_comb = "<tr class=\"aTr" + uuid + "\" >";
									html_comb += "<td style='font-weight:bolder;' class='combBank table_th'>第" + formSeq(alt) + "空</td>";
									html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "</div></td>";
									html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_2\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "</div></td>";
									html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_3\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "</div></td>";
									html_comb += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_comb += "<span></span><textarea style=\"display:none\" name=\"comb_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "<textarea style=\"display:none\" name=\"comb_txt_"+questionIndex+"_"+alt+"_4\" rows=\"0\" cols=\"0\"></textarea>";
									html_comb += "</div></td>";
									html_comb += "</tr>";
									$(".comb_answer tr:eq(" + index + ")", $box).after(html_comb);
								} else if ($tr.index() != index) {
									$tr.find('.combBank').html('第' + formSeq((index + 1)) + '空');
									$tr.find('textarea').each(
													function(index) {
														this.name = (index % 2 == 0 ? 'comb_'
																: 'comb_txt_')
																+questionIndex+"_"
																+ alt
																+ '_'
																+ (((index + 2) / 2) >> 0);
													});
									$(".comb_answer tr:eq(" + index + ")", $box).after($tr);
								}
							});
							$(".indep_answer tr:gt(" + $fillChar.length + ")", $box).remove();
							$(".comb_answer tr:gt(" + $fillChar.length + ")", $box).remove();
							$tmp.remove();
							//如果空少于2个不显示答案类型和给分类型
							if (len <= 1) {
								$("input.rdo_answerType:first", $box).get(0).checked = true;
								$("input.rdo_scoreType:first", $box).get(0).checked = true;
								$(".comb_answer", $box).hide();
								$(".indep_answer", $box).show();
								$(".answerTypeDiv", $box).hide();
								$(".scoreTypeDiv", $box).hide();
							} else {
								$(".answerTypeDiv", $box).show();
								$(".scoreTypeDiv", $box).show();
							}
						});
					}
						
				}
			});
		};
		
		
		
			
			
		  
		var changeQueType = function (ty, $item) {
			$('.fillIn-Answer', $item).hide();
			//$(".edtContentQue .edui-for-blank > div", parent).hide();

			var qusetionIndex = $item.attr('data-qusetionIndex');
			var questionType=$("input[name='questionTypeOpts']:checked").val();//获得题的类型 如果是判断题则限定输入的选项内容只有一个字符
			if (ty == 'SINGLE_CHOICE' || ty == 'MULTI_CHOICE' || ty == "JUDEMENT") {
				$('.optDiv', $item).each(function () {
					$(this).html('<li class="opt">\
							<div style="background-color: #eee; padding-left: 10px; width: 99.8%; position: relative;">\
							<input type="hidden" name="questionSubArra[' + qusetionIndex + '].opt_title" value="A." /> \
							<span class="opt_title">选项A：</span> \
							<span class="content_title"> </span> \
							<input type="hidden" class="content_title_input" name="questionSubArra[' + qusetionIndex + '].content_title" />\
							<textarea style="display: none" rows="0" cols="0" class="area" ></textarea>\
							<textarea style="display: none" rows="0" cols="0" ></textarea>\
							<input type="hidden" class="quesType" name="zzFlag" value='+questionType+'>\
							<a href="javascript:;" dir="0" class="delete red deleteItem" style="display:none" >删除</a>\
						</div>\
					</li>\
					<li class="opt">\
						<div style="background-color: #eee; padding-left: 10px; width: 99.8%; position: relative;">\
							<input type="hidden" name="questionSubArra[' + qusetionIndex + '].opt_title" value="B." /> \
							<span class="opt_title" >选项B：</span> \
							<span class="content_title" ></span> \
							<input type="hidden" class="content_title_input" name="questionSubArra[' + qusetionIndex + '].content_title"  />\
							<textarea style="display: none" rows="0" cols="0" class="area" ></textarea>\
							<textarea style="display: none" rows="0" cols="0" ></textarea>\
							<input type="hidden" class="quesType" name="zzFlag" value='+questionType+'>\
							<a href="javascript:;" dir="0" class="delete red deleteItem" style="display:none" >删除</a>\
						</div>\
					</li>' + (ty == "JUDEMENT" ? '' : '<li>\
						<a class="addOpt" href="javascript:;">+添加选项</a>\
					</li>'));
				});
				if (ty == 'SINGLE_CHOICE' || ty == "JUDEMENT") {
					$('.singleOpt-Answer', $item).html('<li>\
								<label>正确答案：</label>\
								<span class="opt_Answer">\
									<span class="opt_AnswerItem"><label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_singleOpt" value="A" /><span>选项A</span></label></span>\
									<span class="opt_AnswerItem"><label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_singleOpt" class="ml20" value="B" /><span>选项B</span></label></span>\
								</span>\
							</li>')
				} else {	
					$('.singleOpt-Answer', $item).html('<li>\
									<label>正确答案：</label>\
									<span class="opt_Answer">\
										<span class="opt_AnswerItem"><label><input type="checkbox" name="questionSubArra[' + qusetionIndex + '].chk_multOpt" value="A" /><span>选项A</span></label></span>\
										<span class="opt_AnswerItem"><label><input type="checkbox" name="questionSubArra[' + qusetionIndex + '].chk_multOpt" class="ml20" value="B" /><span>选项B</span></label></span>\
									</span>\
								</li>')
				}
			} else  {
				$('.optDiv', $item).html('');
				$('.singleOpt-Answer', $item).html('');
				if (ty == 'FILL_IN_BLANK') {
					$('.fillIn-Answer', $item).show();
					$(".edtContentQue .edui-for-blank > div", $item).show();
				}
			}
			
			if (ty == 'FILL_IN_BLANK') {//
				$(".edtContentQue .edui-for-blank > div", $item).show();
			} else {
				$(".edtContentQue .edui-for-blank > div", $item).hide();
			}
		};
		$('.questionTypeOpts').on('click', 'input', function () {
			var ty = this.value;
			$('.questionItem').each(function () {
				changeQueType(ty, $(this));
			});
			if (questionType != 'FILL_IN_BLANK' ) {
				if (options) {
					var $box = $('.questionItem:eq(0)');
					var tab = options.split('∷');
					for (var i = 2, len = tab.length; i < len; i++) {
						$box.find('.addOpt').click();
					}
					
					var $lis = $box.find('.optDiv .opt');
					for (var i = 0, len = tab.length; i < len; i++) {
						var val = tab[i].replace(/^[^\.]+\./, '');
						var $li = $lis.eq(i);
						$li.find('.content_title').html(val);
						//给隐藏的文本域赋值
						$li.find('.area').html(val);
						$li.find('.content_title_input').val(val);
						$li.find('textarea').val(val);
					}
					
					var tab = answer.split(/\B/);
					var ACode = 'A'.charCodeAt();
					for (var i = 0, len = tab.length; i < len; i++) {
						$box.find('.opt_AnswerItem:eq(' + (tab[i].charCodeAt() - ACode) + ') input').click();
					}

					options = null;
				}	
			} else {
					
			}
		});
		
		var indexTitle = 'A|B|C|D|E|F|G|H'.split('|');
		$('.mainUl').on('click', '.addOpt', function () {	
			var $elm = $(this);
			var $OptDiv=$elm.parents(".optDiv");
			if($OptDiv.find("li").size()>8){//选项多于8个将不能进行追加  隐藏添加选项链接
			   Win.alert("最多可添加8个选项!");
			   $elm.hide();
			    return;
			} 
			var ty = $("input[name='questionTypeOpts']:checked").val();
			var $elm = $(this);
			var $box = $elm.parents('.questionItem');
			var qusetionIndex = $box.attr('data-qusetionIndex');
			if (ty == 'SINGLE_CHOICE' || ty == 'MULTI_CHOICE') {
				$box.find('.optDiv .deleteItem').hide();
				var lis = $box.find('.optDiv li');
				var title = indexTitle[lis.length - 1];
				$(lis[lis.length - 2]).after('<li class="opt">\
									<div class="verticalMiddle" style="background-color: #eee; padding-left: 10px; width: 99.8%; position: relative;">\
										<input type="hidden" name="questionSubArra[' + qusetionIndex + '].opt_title" value="' + title + '." /> \
										<span class="opt_title" >选项' + title + '：</span> \
										<span class="content_title" ></span> \
										<input type="hidden" class="content_title_input" name="questionSubArra[' + qusetionIndex + '].content_title" />\
										<textarea style="display: none" rows="0" cols="0" class="area"></textarea>\
										<textarea style="display: none" rows="0" cols="0" ></textarea>\
										<a href="javascript:;" dir="0" class="delete red deleteItem" >删除</a>\
									</div>\
								</li>');
				var lis = $box.find('.opt_Answer .opt_AnswerItem');
				if (ty == 'SINGLE_CHOICE') {
					$box.find('.opt_Answer').append('<span class="opt_AnswerItem"><label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_singleOpt" class="ml20" value="' + title + '" /><span>选项' + title + '</span></label></span>');
				} else {
					$box.find('.opt_Answer').append('<span class="opt_AnswerItem"><label><input type="checkbox" name="questionSubArra[' + qusetionIndex + '].chk_multOpt" class="ml20" value="' + title + '" /><span>选项' + title + '</span></label></span>');
				}
			}
			
			//如果选项数等于2则隐藏删除链接的显示(默认隐藏)，当选项数大于两个则显示(添加选项算一个li的个数)
			if($(".optDiv li").size()>3){
				//显示
				$(".optDiv li div a").css("display","block");
			}	
		}).on('click', '.optDiv li .deleteItem', function () {
			var $elm = $(this);
			var $OptDiv=$elm.parents(".optDiv");
			var $box = $elm.parents('.questionItem');
			var $item = $elm.parent().parent();
			$item.prev().find('.deleteItem').show();
			$item.remove();
			$box.find('.opt_AnswerItem:last').remove();
			
			if(3==$OptDiv.find("li").size()){
				//隐藏两个选项的删除链接
				$OptDiv.find(" li div a").css("display","none");
			}
			
			//删除之后对选项A,B,C,D,E,F,G,H进行重新排序(并同步更新隐藏域的对应选项)
			   //1.获得当前删除之后选项的个数(获得的选项数-1)  (1指的是添加选项的链接)
			   var currentCountNumber =$OptDiv.find("li").size()-1;
			   //alert("currentNum="+currentCountNumber);//数组的大小
			   var optionArr;
			   var optionValue;
			   if(7==currentCountNumber){
				   optionArr=["选项A：","选项B：","选项C：","选项D：","选项E：","选项F：","选项G："]; 
				   optionValue=["A.","B.","C.","D.","E.","F.","G."];
			   }
			   if(6==currentCountNumber){
				   optionArr=["选项A：","选项B：","选项C：","选项D：","选项E：","选项F："]; 
				   optionValue=["A.","B.","C.","D.","E.","F."];
			   }
			   if(5==currentCountNumber){
				   optionArr=["选项A：","选项B：","选项C：","选项D：","选项E："]; 
				   optionValue=["A.","B.","C.","D.","E."];
			   }
			   if(4==currentCountNumber){
				   optionArr=["选项A：","选项B：","选项C：","选项D："]; 
				   optionValue=["A.","B.","C.","D."];
			   }
			   if(3==currentCountNumber){
				   optionArr=["选项A：","选项B：","选项C："]; 
				   optionValue=["A.","B.","C."];
			   }
			   if(2==currentCountNumber){
				   optionArr=["选项A：","选项B："]; 
				   optionValue=["A.","B."];
			   }
			   
			   //重新设置选项与隐藏域的序号(添加选项的li不包含在内)
		 	   for(var i=0; i<currentCountNumber; i++){
				   
				$($OptDiv.find("li div")[i]).children("span").get(0).innerHTML=optionArr[i];
				$($OptDiv.find("li div")[i]).children("input").get(0).value=optionValue[i];
			   }
			 
			//显示添加选项
			$(".addOpt").show();
		}).on('click', '.addQuestionItem', function () {
			var index = $('.questionSubItem').length;
			if(index >= 10) {Win.alert("最多添加10题子题！"); return;}
			
		    var cd = "uploadInfoBox_content"+qusetionIndex;
	        var sd="uploadInfoBox_resolve"+qusetionIndex;
	        
	        var cdf = "uploadCont_content"+qusetionIndex+"flag";
		    var sdf="uploadCont_resolve"+qusetionIndex+"flag";
		        
			var singlehtml = '<ul class="questionSubItem questionItem" data-qusetionIndex = ' + qusetionIndex + '>\
								<li>\
									<label class="text left"><span class="red">*</span>子题种类</label>\
									<span class="cont" style="position: relative;">\
										<label class="mr20"><input type="radio" name="questionSubArra[' + qusetionIndex + '].questionSubTypeOpts" checked="checked" value="EXTEND" />衍生题</label>\
										<label class="mr20"><input type="radio" name="questionSubArra[' + qusetionIndex + '].questionSubTypeOpts" class="ml20" value="TWINS" />孪生题</label>\
										<a class="delete red deleteQueSubItem" href="javascript:;">删除</a>\
									</span>\
								</li>\
								<li>\
									<label class="text left"><span class="red">*</span>习题题干</label>\
									<span class="cont verticalMiddle">\
										<div class="cCont">\
											<script class="edtContent edtContentQue" name="questionSubArra[' + qusetionIndex + '].edt_content" type="text/plain" style="height: 200px; width: 99.8%"></sc' + 'ript>\
											<br>\
											<a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:80px;">\
                                                                                                                  上传音视频<span id="uploadCont_content' + qusetionIndex + '" class="uploadCont"> </span>\
                                                </a>\
                                                 <ul id="'+cd+'" class="commonUL"></ul>\
											<ul class="optDiv"></ul>\
											<ul class="singleOpt-Answer">\
										</div>\
									</span>\
								</li>\
								<li class="clearfix fillIn-Answer" >\
									<label style="float: left; margin-left: 30px;">答案：</label>\
									<div class="cCont multi-choice-answer ofH">\
										<div class="answerTypeDiv ">\
											<label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_answerType" class="rdo_answerType" checked="checked" value="1" />独立答案</label>\
											<label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_answerType" class="rdo_answerType ml20" value="2" />组合答案</label>\
										</div>\
										<input type="hidden" id="fillInAnswerCnt'+qusetionIndex+'" name="questionSubArra[' + qusetionIndex + '].fillInAnswerCnt" value="0" />\
										<table class="normalTable indep_answer">\
											<tr>\
												<th class="special">\
													<div style="width: 123px;">\
														<span class="lb">填空</span> <span class="rt">答案容错</span>\
													</div>\
												</th>\
												<th>答案1</th>\
												<th>答案2</th>\
												<th>答案3</th>\
												<th>答案4</th>\
											</tr>\
										</table>\
										<table class="normalTable comb_answer" style="display: none">\
											<tr>\
												<th class="special">\
													<div style="width: 123px;">\
														<span class="lb">填空</span> <span class="rt">答案容错</span>\
													</div>\
												</th>\
												<th>第一组</th>\
												<th>第二组</th>\
												<th>第三组</th>\
												<th>第四组</th>\
											</tr>\
										</table>\
										<div class="scoreTypeDiv">\
											<label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_scoreType" class="rdo_scoreType" checked="checked" value="ALL_CORRECT" />全对给分</label>\
											<label><input type="radio" name="questionSubArra[' + qusetionIndex + '].rdo_scoreType" class="ml20 rdo_scoreType" value="SCORE_BY_FILL" /> 按空给分</label>\
										</div>\
									</div>\
									<br>\
								</li>\
								<li>\
									<label class="text left"><span class="red">*</span>习题解析</label>\
									<span class="cont">\
										<div>\
											<script class="edtContent" name="questionSubArra[' + qusetionIndex + '].edt_resolve" type="text/plain" style="height: 200px; width: 99.8%"></scr' + 'ipt>\
										</div>\
										<a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:80px;">\
                                                                                                          上传视频解析<span id="uploadCont_resolve' + qusetionIndex + '" class="uploadCont"></span>\
                                        </a>\
                                        <ul id="'+sd+'" class="commonUL"></ul>\
									</span>\
								</li>\
							</ul>';
			var $item = $(singlehtml).appendTo("#childrenQue");
			var ty = $("input[name='questionTypeOpts']:checked").val();
			loadUEdit();
			changeQueType(ty, $item);
			initSwf("_resolve"+qusetionIndex);//初始化解析上传   当页面添加成功之后对音频进行初始化操作
			initSwf("_content"+qusetionIndex);//初始化题干
			qusetionIndex++;
		}).on('click', '.opt', function () {
			optContentCell = this;
			Win.open({
				title : "输入选项内容",
				width : 850,
				height : 500,
				url : "/OnlineTest/questionLib/openueditpage.do",
				mask : true
			});
		}).on('click', '.opt .deleteItem', function (e) {
			return false;
		}).on('click', '.indep_answer .edit-pop-btn', function (e) {
			optAnswerCell = this;
			Win.open({
				title : "输入答案",
				width : 850,
				height : 500,
				url : "/OnlineTest/questionLib/openfillpage.do",
				mask : true
			});
		}).on('click', '.comb_answer .edit-pop-btn', function (e) {
			optAnswerCell = this;
			Win.open({
				title : "输入答案",
				width : 850,
				height : 500,
				url : "/OnlineTest/questionLib/openfillpage.do",
				mask : true
			});
		}).on('click', '.deleteQueSubItem', function () {
			//获得删除的子题的下标
			var num_index=$(this).parents('.questionSubItem').attr("data-qusetionIndex");
			deleQuestionStr+=num_index+",";
			//alert("deleIndex="+deleQuestionStr);
			$("#deleStr").val(deleQuestionStr);//将已删除的题目的下标进行记录
			$(this).parents('.questionSubItem').remove();
			
		}).on('click', '.rdo_answerType', function () {
			var type = this.value;
			var $box = $(this).parents('.questionItem');
			if (type == '1') {//独立答案
	     		$(".indep_answer", $box).show();
	     	    $(".comb_answer", $box).hide();
	     	} else if(type == '2') {//组合答案
	     		$(".indep_answer", $box).hide();
	     	    $(".comb_answer", $box).show();
	     	}
		});

		loadUEdit();
		
		if (questionId) {
			$("input[name='questionTypeOpts']").each(function () {
				if (this.value == questionType)	{
					this.click();
				} else {
					this.disabled = true;
				}
			})
		} else {
			$("input[name='questionTypeOpts']:eq(0)").click();
		}
	});
	
	
	
		var i = 0;
		$(function() {
 
			//版本级联分册
			$("#versionOption").change(
					function() {

						//获得当前选中的版本的id
						var versionOption = $("#versionOption").val();
						$.post("${root}/commons/volumeversionbyverId.do?baseSubjId=" + disciplineId + "&baseClassLevId="
								+ classLvlId + "&baseVersionId=" + versionOption, function(data) {
							var html = '<option value="0">请选择</option>';
							for (var i = 0; i < data.length; i++) {
								html += "<option value="+data[i].baseVolumeId+">" + data[i].volumeName + "</option>";
							}
							$("#volumeOption").html(html);
							//将章节置空    先清空再添加
							$("#chapterOption").empty();
							$("#chapterOption").append(emptyOpt);
							
							$("#sectionOption").empty();
							$("#sectionOption").append(emptyOpt);
						}, 'json');
					});

			//分册级联章节
			$("#volumeOption").change(
					function() {

						//获得当前选中的版本的id
						var versionOption = $("#versionOption").val();
						//获得当前选中的分册的id
						var volumeOption = $("#volumeOption").val();
						$.post("${root}/commons/chaptervolumebycid.do?baseVersionId=" + versionOption + "&baseSubjId="
								+ disciplineId + "&baseClassLevId=" + classLvlId + "&baseVolumeId=" + volumeOption,
								function(data) {
									var html = '<option value="0">请选择</option>';
									for (var i = 0; i < data.length; i++) {
										html += "<option value="+data[i].baseChapterId+">" + data[i].chapterName
												+ "</option>";
									}
									$("#chapterOption").html(html);
									$("#sectionOption").empty();
									$("#sectionOption").append(emptyOpt);
								}, 'json');
					});

			//节级联章
			$("#chapterOption").change(
					function() {
						//获得当前选中的版本的id
						var versionOption = $("#versionOption").val();
						//获得当前选中的分册的id
						var volumeOption = $("#volumeOption").val();
						//获得当前选中的章
						var chapterOption = $("#chapterOption").val();

						$.post("${root}/commons/sectionchapbycid.do?baseVersionId=" + versionOption + "&baseSubjId="
								+ disciplineId + "&baseClassLevId=" + classLvlId + "&baseChapterId="
								+ chapterOption + "&baseVolumeId=" + volumeOption, function(data) {
							var html = '<option value="0">请选择</option>';
							for (var i = 0; i < data.length; i++) {
								html += "<option value="+data[i].sectionId+">" + data[i].sectionName + "</option>";
							}
							$("#sectionOption").html(html);
						}, 'json');
					});

			//增加章节
			$("#addChapterBtn").click(
					function() {
						var chapterCnt = $("#selectedChapters span").size();//div中添加span标签来判断添加的章节的个数
						//alert("chapterCnt.size="+chapterCnt);
						
						if (chapterCnt >= 5) {
							Win.alert("最多只能添加五个关联章节！");
							return;
						}

						var versionId = $("#versionOption option:selected").val();//$("#versionOpts").val();  获得选中的版本对应的id
						if (versionId == 0 || versionId == "" || versionId == undefined) {
							Win.alert("请至少选择到版本！");
							return;
						}

						/* var semesterName = $("#semesterOption option:selected").html(); //学段名称
						var classLevelName = $("#classLevelOption option:selected").html(); //年级名称
						var disciplineName = $("#subjOption option:selected").html(); //学科名称  */
						var versionName = $("#versionOption option:selected").html(); //版本名称
						var volumeName = $("#volumeOption option:selected").html(); //分册名称
						var chapterName = $("#chapterOption option:selected").html(); //章名称
						var sectionName = $("#sectionOption option:selected").html(); //节名称
						
						var versionId=$("#versionOption").val();//获得选中的版本的id
						var volumeId = $("#volumeOption").val();//获得选中分册的id
						var chapterId = $("#chapterOption").val();//获得选中的章的id
						var sectionId = $("#sectionOption").val();//获得选中的节的id
						/* alert("获得的版本分册章节的id="+versionId+"-->"+volumeId+"-->"+chapterId+"-->"+sectionId); */
						
						var connChar = "-->";
						var connChapterName =versionName; //使用-->符号将学段-->年级-->学科-->版本  进行拼接成一个字符串
						/* alert("拼接好选中的从学段到节的名字字符串的拼接="+connChapterName); */
						
						var connChapterIds = versionId;
						if (volumeId != '0' && volumeId != '') {
							connChapterName += connChar + volumeName;
							connChapterIds += ':' + volumeId;
							endNodeId = volumeId;
						} else {
							connChapterIds += ':0';
						}
						;

						if (chapterId != '0' && chapterId != '') {
							connChapterName += connChar + chapterName;
							connChapterIds += ':' + chapterId;
							endNodeId = chapterId;
						} else {
							connChapterIds += ':0';
						}
						;

						if (sectionId != '0' && sectionId != '') {
							connChapterName += connChar + sectionName;
							connChapterIds += ':' + sectionId;
							endNodeId = sectionId;
						} else {
							connChapterIds += ':0';
						}
						;

						//判断该节点重复性
						var repFlag = false;
						$("#selectedChapters span").each(function(i, n) {
							var child = $(n).attr("child");
							if (connChapterIds == child) {
								repFlag = true;
							}
						});

						if (repFlag) {
							Win.alert("该章节已添加过！");
							return;
						}
						
						 var reg=new RegExp(":","g");
						 var valObj =connChapterIds.replace(reg,"");  //将逗号替换为

						 /* alert("connChapterIds="+connChapterIds); */   //获得添加的版本,分册,章,节  如果没有选择的用0代替   之间使用逗号隔开
						 //表明新增的san元素的child属性即是已选的所有章节的字符串(之间采用,隔开)   connChapterName为从学段到筛选的最后一个节点
						//增加该节点
						
						//每次在后面追加一个筛选好的版本，分册，章节的隐藏域,而springmvc会自动将他们封装成一个数组   每个数组元素是一个关联的对象
						var connHtml = "<span class=\"smallBlock chapBlock itemDel\" child ='"+connChapterIds+"' style=\"height:28px;\" id='"+valObj+"'>"
								+ connChapterName + "<a href=\"javascript:remo('"+valObj+"')\">x</a><input type=\"hidden\" name=\"ch_input\" value='"+connChapterIds+"'/></span>";
						$("#selectedChapters").append(connHtml);
					});
			
			
			


			 //提交编辑题库的表单
        	$("#saveEdit").click(function(){
        		
        		  //对题干内容大小和解析的内容大小进行判断
        	    var editorArr = $(".edtContent");
        	    for (var i=0,j=editorArr.length;i<j;i++) {
        	    	var id =$(editorArr[i]).attr("id");
        	    	 var numIndex=id.substring(id.length-1);// 奇数表示题干   偶数表示解析
        	    	 var questionContent=$.trim(UE.getEditor(id).getContentTxt());//获得纯文本内容
        	    	 var contentLen=UE.getEditor(id).getContentLength(true);
        	    	 if(numIndex%2!=0){//表示是题干
        	    		 if(""==questionContent && contentLen==0){
        	    			 Win.alert({html:"<span class=\"dialog_Inner\">请输入题干！</span>",width:"200px"});
        				     return; 
        	    		 }
        	    		 if(contentLen>1000){
        	    			 Win.alert({html:"<span class=\"dialog_Inner\">题干内容不能超过1000字符！</span>",width:"200px"});
        				     return; 
        	    		 }
        	    	 }else{//表示是解析 
        	    		 if(""==questionContent && contentLen==0){
        	    			 Win.alert({html:"<span class=\"dialog_Inner\">请输入习题解析！</span>",width:"200px"});
        				     return; 
        	    		 }
        	    		 if(contentLen>1000){
        	    			 Win.alert({html:"<span class=\"dialog_Inner\">解析内容不能超过1000字符！</span>",width:"200px"});
        				     return; 
        	    		 } 
        	    	 }
        	    }
        	    
        		    //对多选，单选，判断题的选项内容进行验证
        	    var questionType=$("input[name='questionTypeOpts']:checked").val();
        	    if(questionType=="SINGLE_CHOICE" || questionType=="MULTI_CHOICE" || questionType=="JUDEMENT"){
        	    	
        	    	 var editOptionArr=$(".optDiv li div ");
        	 	     for(var i=0; i<editOptionArr.length; i++){
        	 	        var optText=$(editOptionArr[i]).children("input").get(0).value;
        	 	        optText=optText.substring(0,optText.length-1);
        	 	        optContent=$(editOptionArr[i]).children("span").get(1).innerHTML;
        	 	    	
        	 	    	if(" "==optContent || ""==optContent){
        	 	    		Win.alert("请填写选项"+optText+"!");
        	    			return false;
        	 	    	}
        	 	    } 
        	    }
        	    
        	    //单选题 ，判断题的正确答案必须有一个选中的
        	    if(questionType=="SINGLE_CHOICE" || questionType=="JUDEMENT"){
        	    	var optionArr=$(".opt_Answer");
        	    	var flages=false;//默认验证不通过
        	    	for(var i=0; i<optionArr.length; i++){
        	    		//alert($(optionArr[i]).children("span").length);
        	    		var optSonObjArr=$(optionArr[i]).children("span").children("label").children("input");// 获得每个opt_Answer对象
        	    		//alert("optSonObjArr="+optSonObjArr.length);
        	    		for(var j=0; j<optSonObjArr.length; j++){
        	    			if(optSonObjArr[j].checked==true){
        	    				flages=true;// 如果有一个正确答案被选中则通过验证(循环遍历每道题)
        	    				break;//如果有一个选中则跳出当前循环
        		    		}else{
        		    			flages=false;//若没选中则重新赋值
        		    		}
        	    		} 
        	    		
        	    		if(flages==false){
        	    			Win.alert("还有题目的正确答案没有进行选择!");
        	    			return false;
        	    		}

        	    	}
        	    	
        	    }
        	    
        	    
        	    //多选题的正确答案至少有两个选中的
        	    if(questionType=="MULTI_CHOICE"){
        	     	    	
        	    	    	var optionArr=$(".opt_Answer");
        	 	    	var flages=false;//默认验证不通过
        	 	    	for(var i=0; i<optionArr.length; i++){
        	 	    		//alert($(optionArr[i]).children("span").length);
        	 	    		var optSonObjArr=$(optionArr[i]).children("span").children("label").children("input");// 获得每个opt_Answer对象
        	 	    		//alert("optSonObjArr="+optSonObjArr.length);
        	 	    		var count=0;// 判断每道多选题选中的个数  每次初始化每道题的多选答案选中的个数
        	 	    		for(var j=0; j<optSonObjArr.length; j++){
        	 	    			if(optSonObjArr[j].checked==true){
        	 	    				count++;
        	 	    				if(count>=2){//至少有两道多选题答案
        	 	    				   flages=true;// 如果有一个正确答案被选中则通过验证(循环遍历每道题)
        	     	    				break;//如果有一个选中则跳出当前循环 
        	 	    				}	
        	     	    		}else{
        	     	    			flages=false;//若没选中则重新赋值
        	     	    		}
        	 	    		} 
        	 	    		
        	 	    		if(flages==false){
        	 	    			Win.alert("多选题的答案未选或选择的答案少于两种!");
        	 	    			return false;
        	 	    		}

        	 	    	}
        	     }
        	       
      	//对填空题进行校验
		if (questionType == "FILL_IN_BLANK") {
				//1.获取所有填空题的空的个数
				var FillCountArr = $(".fillCount");
				//2.获得每道填空题是独立答案还是组合答案的数组
				var answerTypeArr = $(".answerTypeDiv label input:checked");

				for (var i = 0; i < FillCountArr.length; i++) {//题目的个数和填空题数组的大小一致(因为一道题一个隐藏域)
					var questionCount = $(FillCountArr[i]).val();//获得目前每道填空题的空的个数 
					var answerTypeValue = $(answerTypeArr[i]).val();//答案类型
					var questionIndex = $(FillCountArr[i]).attr("id");
					questionIndex = questionIndex.substring(questionIndex.length - 1);//获得每道题的下标
					if (questionCount == 0) {//表示题干中未填充填空题
						Win.alert("第" + (i + 1) + "题请至少设置一个填空位！");
						return;
					} else {//表示题干中含有填空题

						if ("1" == answerTypeValue) {//表示是独立答案
							for (var j = 1; j <= questionCount; j++) {

								// 获得每行的所有值
								var indep_1 = $.trim($(
										"textarea[name='indep_" + questionIndex + "_" + j + "_1']")
										.text());
								var indep_2 = $.trim($(
										"textarea[name='indep_" + questionIndex + "_" + j + "_2']")
										.text());
								var indep_3 = $.trim($(
										"textarea[name='indep_" + questionIndex + "_" + j + "_3']")
										.text());
								var indep_4 = $.trim($(
										"textarea[name='indep_" + questionIndex + "_" + j + "_4']")
										.text());
								//alert(indep_1+"=="+indep_2+"=="+indep_3+"=="+indep_4);
								if ("" == indep_1 && "" == indep_2 && "" == indep_3
										&& "" == indep_4) {//当每行全空的时候进行提示(每行至少有一个答案，不能全空)
									Win.alert("每空至少设置一个答案!");
									return;
								}
								if ("" != indep_4) {//如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
									if ("" == indep_3 || "" == indep_2 || "" == indep_1) {
										Win.alert("请按顺序填写答案!");
										return;
									}
								}
								if ("" != indep_3 && "" == indep_4) {// 如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
									if ("" == indep_2 || "" == indep_1) {
										Win.alert("请按顺序填写答案!");
										return;
									}
								}
								if ("" != indep_2 && "" == indep_3 && "" == indep_4) {// 如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
									if ("" == indep_1) {
										Win.alert("请按顺序填写答案!");
										return;
									}
								}

							}
						}

						if ("2" == answerTypeValue) {//组合答案

							//alert("组合");
							var recoredCloumStatus = "";
							var count;
							for (var k = 1; k < 5; k++) {
								count = 0;//来与总的空的个数进行比较,如果小于总空的个数则表示不是满列
								for (var r = 1; r <= questionCount; r++) {
									var comb = $.trim($(
											"textarea[name='comb_" + questionIndex + "_" + r + "_"
													+ k + "']").text());//每列循环遍历
									if ("" != comb) {
										count++;
									}
								}
								if (1 == k) {
									if (count < questionCount) {//表示第一列未满列
										Win.alert("请将组合答案填全!");
										return;
									}
								}
								if (2 == k) {
									if (count<questionCount && count>0) {
										recoredCloumStatus += "0" + ",";
									} else if (count == questionCount) {
										recoredCloumStatus += "1" + ",";
									} else if (count == 0) {//表示是空列
										recoredCloumStatus += "n" + ",";
									}
								}
								if (3 == k) {
									if (count<questionCount && count>0) {
										recoredCloumStatus += "0" + ",";
									} else if (count == questionCount) {
										recoredCloumStatus += "1" + ",";
									} else if (count == 0) {//表示是空列
										recoredCloumStatus += "n" + ",";
									}
								}
								if (4 == k) {
									if (count<questionCount && count>0) {
										recoredCloumStatus += "0" + ",";
									} else if (count == questionCount) {
										recoredCloumStatus += "1";
									} else if (count == 0) {//表示是空列
										recoredCloumStatus += "n";
									}
								}

							}
							//获得满列以及判断满列与首列之间是非存在非满列   若存在则表示不合法
							// alert("recoredCloumStatus="+recoredCloumStatus);
							//获得最后一个满列  如果第一个是满列则直接通过(因为第一列一定是满列) 从第二列开始算起
							var flagArr = recoredCloumStatus.split(",");//只有三个数字
							//alert("flagArr="+flagArr.length);

							if (flagArr[0] == "0" || flagArr[1] == "0" || flagArr[2] == "0") {//将所有的未满列拦截并提示
								Win.alert("请将组合答案填全!");
								return;
							}

							if (flagArr[0] == "1" && flagArr[1] == "n" && flagArr[2] == "1"
									|| flagArr[0] == "n" && flagArr[1] == "1" && flagArr[2] == "1") {
								Win.alert("请按顺序填写组合答案!");
								return;
							}

							if (flagArr[2] == "1") {//表示最后一个是满列
								if (flagArr[0] == "0" || flagArr[1] == "0" || flagArr[0] == "n"
										|| flagArr[1] == "n") {//表示中间出现未满列或空列
									Win.alert("请将组合答案填全!");
									return;
								}
							}

							if (flagArr[1] == "1" && flagArr[2] == "n") {//表示倒数第二个是满列而最后一列是空列
								if (flagArr[0] == "0" || flagArr[0] == "n") {//若倒数第三个是未满列则不满足要求
									Win.alert("请按顺序填写组合答案!");
									return;
								}
							}
						}
					}
				}
			}

			//表单提交
			// edt_content.sync();
			var formData = $("#editForm").serialize();
			$.post("${root}/schoolTest/updateRealQuestion.do?semesterOpts=" + semesterId
					+ "&classlevelOpts=" + classLvlId + "&disciplineOpts=" + disciplineId,
					formData, function(data) {
						if (data && data != null) {
							//$("#cleanBtn").trigger("click");
							//Win.close();
							parent.getRealExamQuestions(data);
							parent.Win.wins[domid].close();
						} else if (res == "no") {
							Win.alert("提交失败");
						}
					});
			});

			//基础信息初始化

			$("#cleanBtn").click(function() {
				//基础信息初始化
				$("#semesterOpts").val("0");
				$("#semesterOpts").trigger("change");

				$("#selectedChapters").empty();
				$("#selectedKnowledges").empty();

				$("input[name='questionTypeOpts']:first").click();
				$("#difficultyOpts option:first").get(0).selected = true;

				$("input[name='chk_multOpt']:checked").each(function() {
					$(this).get(0).checked = false;
				})

				$("input[name='rdo_singleOpt']:checked").each(function() {
					$(this).get(0).checked = false;
				})

				$("#video").val('');
				$("#videoOrgName").val('');

				$("#videouploading").html('');
				UE.getEditor('edt_content').setContent("");
				UE.getEditor('edt_resolve').setContent("");

			});

		});

		function remo(obj) {//删除指定添加的章节
			$("#" + obj).remove();
		}

		//进行添加知识点的操作
		$("#addKnowledgeBtn")
				.click(
						function() {
							var knowledgeCnt = $("#selectedKnowledges span").size();
							if (knowledgeCnt >= 5) {
								Win.alert("最多只能添加五个关联知识点！");
								return;
							}

							var knowledgeId = $("#knowledgeOpts").val();

							if (knowledgeId == 0 || knowledgeId == "") {
								Win.alert("请至少选择到第一节知识点！");
								return;
							}

							//增加该节点
							/* var semesterName = $("#semesterOption option:selected").html(); //学段名称
							var classLevelName = $("#classLevelOption option:selected").html(); //年级名称
							var disciplineName = $("#subjOption option:selected").html(); //学科名称  */
							var knowledgeName = $("#knowledgeOpts option:selected").html();//父级知识点名称
							var subKnowledgeId1 = $("#knowledgeOpts1").val();
							var subKnowledgeName1 = $("#knowledgeOpts1 option:selected").html();//一级子知识点
							var subKnowledgeId2 = $("#knowledgeOpts2").val();
							var subKnowledgeName2 = $("#knowledgeOpts2 option:selected").html();//二级子知识点
							var subKnowledgeId3 = $("#knowledgeOpts3").val();
							var subKnowledgeName3 = $("#knowledgeOpts3 option:selected").html();//三级子知识点
							var subKnowledgeId4 = $("#knowledgeOpts4").val();
							var subKnowledgeName4 = $("#knowledgeOpts4 option:selected").html();//四级子知识点
							var subKnowledgeId5 = $("#knowledgeOpts5").val();
							var subKnowledgeName5 = $("#knowledgeOpts5 option:selected").html();//五级子知识点

							var connChar = "-->";
							var connKnowledgeName = knowledgeName;
							var connKnowledgeIds = knowledgeId;

							if (subKnowledgeId1 != '0' && subKnowledgeId1 != '') {
								connKnowledgeName += connChar + subKnowledgeName1;
								connKnowledgeIds += ':' + subKnowledgeId1;
							} else {
								connKnowledgeIds += ':0';
							}
							;

							if (subKnowledgeId2 != '0' && subKnowledgeId2 != '') {
								connKnowledgeName += connChar + subKnowledgeName2;
								connKnowledgeIds += ':' + subKnowledgeId2;
							} else {
								connKnowledgeIds += ':0';
							}
							;

							if (subKnowledgeId3 != '0' && subKnowledgeId3 != '') {
								connKnowledgeName += connChar + subKnowledgeName3;
								connKnowledgeIds += ':' + subKnowledgeId3;
							} else {
								connKnowledgeIds += ':0';
							}
							;

							if (subKnowledgeId4 != '0' && subKnowledgeId4 != '') {
								connKnowledgeName += connChar + subKnowledgeName4;
								connKnowledgeIds += ':' + subKnowledgeId4;
							} else {
								connKnowledgeIds += ':0';
							}
							;

							if (subKnowledgeId5 != '0' && subKnowledgeId5 != '') {
								connKnowledgeName += connChar + subKnowledgeName5;
								connKnowledgeIds += ':' + subKnowledgeId5;
							} else {
								connKnowledgeIds += ':0';
							}
							;

							//判断该节点重复性
							var repFlag = false;
							$("#selectedKnowledges span").each(function(i, n) {
								var child = $(n).attr("child");
								if (connKnowledgeIds == child) {
									repFlag = true;
								}
							});

							if (repFlag) {
								Win.alert("该关联知识点已添加");
								return;
							}

							var reg = new RegExp(":", "g");
							var valKnowObj = connKnowledgeIds.replace(reg, ""); //将逗号替换为

							var connHtml = "<span class=\"smallBlock chapBlock itemDel\" style=\"height:28px;\" child ='"+connKnowledgeIds+"'  id='"+valKnowObj+"'>"
									+ connKnowledgeName
									+ "<a href=\"javascript:remoKnowledge('"
									+ valKnowObj
									+ "')\"></a><input type=\"hidden\" name=\"k_input\" value='"+connKnowledgeIds+"'/></span>";
							$("#selectedKnowledges").append(connHtml);
						});
		$("select[id^='knowledgeOpts']").not("#knowledgeOpts").hide();//隐藏其他的知识点框

		function remoKnowledge(obj) {//删除指定添加的知识点

			$("#" + obj).remove();
		}

		//选择不同的知识点事件(根据)
		$("#knowledgeOpts").change(function() {
			var knowledgeId = this.value;
			bindSubKnowledgeByParentId(this.value, function(data) {
				var optObj = $("#knowledgeOpts1");
				if (data.length > 0) {
					optObj.show();
				} else {
					optObj.hide();
				}
				bindSubKnowledgeData(optObj, data); //------------------

				$("#knowledgeOpts2").empty();
				$("#knowledgeOpts2").append(emptyOpt);
				$("#knowledgeOpts2").hide();

				$("#knowledgeOpts3").empty();
				$("#knowledgeOpts3").append(emptyOpt);
				$("#knowledgeOpts3").hide();

				$("#knowledgeOpts4").empty();
				$("#knowledgeOpts4").append(emptyOpt);
				$("#knowledgeOpts4").hide();

				$("#knowledgeOpts5").empty();
				$("#knowledgeOpts5").append(emptyOpt);
				$("#knowledgeOpts5").hide();
			});

		});

		$("#knowledgeOpts1").change(function() {
			var knowledgeId = this.value;
			bindSubKnowledgeByParentId(this.value, function(data) {
				var optObj = $("#knowledgeOpts2");
				if (data.length > 0) {
					optObj.show();
				} else {
					optObj.hide();
				}
				bindSubKnowledgeData(optObj, data);

				$("#knowledgeOpts3").empty();
				$("#knowledgeOpts3").append(emptyOpt);
				$("#knowledgeOpts3").hide();

				$("#knowledgeOpts4").empty();
				$("#knowledgeOpts4").append(emptyOpt);
				$("#knowledgeOpts4").hide();

				$("#knowledgeOpts5").empty();
				$("#knowledgeOpts5").append(emptyOpt);
				$("#knowledgeOpts5").hide();
			});
		});

		$("#knowledgeOpts2").change(function() {
			var knowledgeId = this.value;
			bindSubKnowledgeByParentId(this.value, function(data) {
				var optObj = $("#knowledgeOpts3");
				if (data.length > 0) {
					optObj.show();
				} else {
					optObj.hide();
				}
				bindSubKnowledgeData(optObj, data);

				$("#knowledgeOpts4").empty();
				$("#knowledgeOpts4").append(emptyOpt);
				$("#knowledgeOpts4").hide();

				$("#knowledgeOpts5").empty();
				$("#knowledgeOpts5").append(emptyOpt);
				$("#knowledgeOpts5").hide();
			});
		});

		$("#knowledgeOpts3").change(function() {
			var knowledgeId = this.value;
			bindSubKnowledgeByParentId(this.value, function(data) {
				var optObj = $("#knowledgeOpts4");
				if (data.length > 0) {
					optObj.show();
				} else {
					optObj.hide();
				}
				bindSubKnowledgeData(optObj, data);

				$("#knowledgeOpts5").empty();
				$("#knowledgeOpts5").append(emptyOpt);
				$("#knowledgeOpts5").hide();
			});
		});

		$("#knowledgeOpts4").change(function() {
			var knowledgeId = this.value;
			bindSubKnowledgeByParentId(this.value, function(data) {//-----------------------------------
				var optObj = $("#knowledgeOpts5");
				if (data.length > 0) {
					optObj.show();
				} else {
					optObj.hide();
				}
				bindSubKnowledgeData(optObj, data);
			});
		});

		$('#selectedKnowledges').on('click', 'span a', function(e) {
			$(this).parent().remove();//移除页面中显示的知识点元素
		});

		//将本题目的知识点在对应的显示知识点位置进行显示
		function showEditKnowContent() {

			var rKnowIdStr = '${rKnowIdStr}'; //没有逗号分隔的知识点id
			var rKnowledgeIdStr = '${rKnowledgeIdStr}';//有逗号分隔的知识点id
			var rKnowledgeNameStr = '${rKnowledgeNameStr}';//有特殊字符分隔的知识点内容
			if ("" != rKnowIdStr || null != rKnowIdStr && "" != rKnowledgeIdStr || null != rKnowledgeIdStr
					&& "" != rKnowledgeNameStr || null != rKnowledgeNameStr) {
				var rKnowIdStrArr = rKnowIdStr.split(";");
				var rKnowledgeIdStrArr = rKnowledgeIdStr.split(";");
				var rKnowledgeNameStrArr = rKnowledgeNameStr.split(";");//三个数组的长度一定一致
				for (var i = 0; i < rKnowIdStrArr.length; i++) {

					if ("" != rKnowIdStrArr[i] && "" != rKnowledgeIdStrArr[i] && "" != rKnowledgeNameStrArr[i]) {

						var connHtml = "<span class=\"smallBlock chapBlock itemDel\" style=\"height:28px;\" child ='"+rKnowledgeIdStrArr[i]+"'  id='"+rKnowIdStrArr[i]+"'>"
								+ rKnowledgeNameStrArr[i]
								+ "<a href=\"javascript:remoKnowledge('"
								+ rKnowIdStrArr[i]
								+ "')\"></a><input type=\"hidden\" name=\"k_input\" value='"+rKnowledgeIdStrArr[i]+"'/></span>";
						$("#selectedKnowledges").append(connHtml);
					}
				}
			}
		}

		function showEditchapterContent() {//本题目关联的所有版本分册章节在页面进行显示

			var chapterSecNameStr = '${chapterSecNameStr}';//有特殊分隔符的章节内容
			var chapSecVerStr = '${chapSecVerStr}';//带有逗号的版本章节id
			var chapSecIdStr = '${chapSecIdStr}'; //不含有逗号的版本章节分册id

			if ("" != chapterSecNameStr || null != chapterSecNameStr && "" != chapSecVerStr || null != chapSecVerStr
					&& "" != chapSecIdStr || null != chapSecIdStr) {
				var chapterSecNameStrArr = chapterSecNameStr.split(";");
				var chapSecVerStrArr = chapSecVerStr.split(";");
				var chapSecIdStrArr = chapSecIdStr.split(";");//三个数组的长度一定一致
				for (var i = 0; i < chapterSecNameStrArr.length; i++) {

					if ("" != chapterSecNameStrArr[i] && "" != chapSecVerStrArr[i] && "" != chapSecIdStrArr[i]) {

						var connHtml = "<span class=\"smallBlock chapBlock itemDel\" style=\"height:28px;\" child ='"+chapSecVerStrArr[i]+"' id='"+chapSecIdStrArr[i]+"'>"
								+ chapterSecNameStrArr[i]
								+ "<a href=\"javascript:remo('"
								+ chapSecIdStrArr[i]
								+ "')\"></a><input type=\"hidden\" name=\"ch_input\" value='"+chapSecVerStrArr[i]+"'/></span>";
						$("#selectedChapters").append(connHtml);
					}
				}
			}
		}
	</script>
	
	<script>
/***进行音视频的上传操作**/
		
function initSwf(id){
	 var params="";
	 var index=id.substring(id.length-1);
	 var uId="#uploadCont"+id+"flag";
	 var contentIndex=index+"content";
	 var spanJudge="#uploadInfoBox"+id+"flag";//每个视频，音频只能添加一个
	 
	 if(id.indexOf("content")>0){//题干既可以播放视频也可以播放音频
		 params= {
		         debug : 1,
		         fileType : "*.mp4;*.flv;*.mp3;",
		         typeDesc : "音频",
		         sizeLimit : 100*1024*1024,
		         autoStart : true,
		         multiSelect : 0,  //表示每次只能上传一个     1 表示可以进行批量上传
		         server: encodeURIComponent("http://localhost:8080${root}/videoUpload")
		 	}; 
	 }else{//解析不能播放音频
		 
		 params= {
		         debug : 1,
		         fileType : "*.mp4;*.flv;",
		         typeDesc : "音频",
		         sizeLimit : 100*1024*1024,
		         autoStart : true,
		         multiSelect : 0,  //表示每次只能上传一个     1 表示可以进行批量上传
		         server: encodeURIComponent("http://localhost:8080${root}/videoUpload")
		 	}; 
	 }

	 window["uploadSwf"+id] = new UploadFile($id("uploadCont"+id), "uploadSwf"+id, "${PUBLIC_PATH}/public/upload/uploadFile.swf",params);
	  	 	
	  	 	
	 window["uploadSwf"+id].uploadEvent.add("onComplete",function(){
	  	 		//alert("onComplete");
	  	 		//alert("id="+id);
	  	 		var elm = arguments[0].message[0],
  	 			data = arguments[0].message[1];
  			var myProgressBox = $class("progressBox",$id(elm))[0],
  				myUploadOperate = $class("uploadOperate",$id(elm))[0];
  			
	  	 		if(id.indexOf("content")>0){
	  				//alert("content");
	  				myProgressBox.innerHTML = "上传完成!<input type ='hidden' name='questionSubArra["+index+"].contentVideo' id="+index+"contentVideo  value="+JSON.parse(data).message+">";	
	  			    myUploadOperate.innerHTML = "&nbsp;&nbsp;&nbsp;<a style='color:green;' class='watch_play"+index+"c' href='javascript:playVideo(\""+JSON.parse(data).message+"\",\""+index+"c\")'>点击播放</a>&nbsp;&nbsp;&nbsp;<span id='watch_play"+index+"c'><a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";

	  			}else{
	  				//alert("resolve");
	  				myProgressBox.innerHTML = "上传完成!<input type ='hidden' name='questionSubArra["+index+"].resolveVideo' id="+index+"resolveVideo  value="+JSON.parse(data).message+">";	
	  			    myUploadOperate.innerHTML = "&nbsp;&nbsp;&nbsp;<a style='color:green;' class='watch_play"+index+"s' href='javascript:playVideo(\""+JSON.parse(data).message+"\",\""+index+"s\")'>点击播放</a>&nbsp;&nbsp;&nbsp;<span id='watch_play"+index+"s'><a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
	  			}

	  	 	});
	  	 	

	  	 	events.delegate($id("uploadInfoBox"+id),".delUploadFile","click",function(){
	  	 		//删除
	  	 		//alert("delegate");
	  	 		var e = arguments[0] || window.event,
	  				target = e.srcElement || e.target,
	  				fileIndex = target.getAttribute("file"),
	  				liElm = $id(fileIndex);
	  	 		liElm.parentNode.removeChild(liElm);
	  	 	});
	  	 	
	  	 	//var orginalFileName='';
	  	 	                                           //显示上传的文件信息
	  	 	window["uploadSwf"+id].uploadEvent.add("onOpen",function(){
	  	 		//alert("onOpen");
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
	  	 		//alert("onProgress");
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


}
  
	//初始化母题上传文件的题干和解析要上传的视频准备
	initSwf("_resolve"+0);//初始化解析上传
	initSwf("_content"+0);//初始化题干
			
	
	
 	function playVideo(videoPath,id){//点击播放视频

 		var index1=videoPath.lastIndexOf(".");
 		var index2=videoPath.length;
 		var postf=videoPath.substring(index1,index2);//获取文件后缀名
 		
 		var showClassId="watch_play"+id;
		var fileurl=API_PATH+"/Video/"+videoPath;
		
		
		//如果添加的音视频数目大于1则停止向下追加
		var showClassIndex="#"+showClassId;
		if($(""+showClassIndex+" audio").size()>=1){
			Win.alert("音频正在播放，请不要继续点击!");
			return;	
		}

 		//alert("postf="+postf);
 		if(postf.indexOf("mp3")>0){
 			//使用ajax获取本音频的据对路径，通过url
 			//http://172.17.54.4/public/Hair.mp3
          //alert("videoPath="+videoPath);
 		  $("#"+showClassId).append('&nbsp;&nbsp;&nbsp;<audio src="${root}/videos/'+videoPath+'" autoplay loop  controls></audio>'); 
					
 			
 		}else{
 			flashurl = '${PUBLIC_PATH}/public/flash/myflvPlayBack.swf?url='+fileurl+'&skin=${PUBLIC_PATH}/public/flash/MinimaFlatCustomColorAll.swf&autoplay=4';
 			Win.open({
 	        	title : "音视频播放窗口",
 	            mask : true,
 	            html : "<div id='myplayer'>&nbsp;</div>",
 	            width: 740,
 	            height: 510
 	        });
 			FlashPlayer($id('myplayer'),flashurl,{id:'player',"wh":[718,428]});
 			
 		}
	} 
	
 	function removeHidden(id){//删除上传的音视频
		//alert("id=="+id);
	    
		var idIndex="#"+id;
	    var idShow="."+id;
	    $(idShow).remove();
		//删除input隐藏域
		$(idIndex).remove();
	}
</script>

</body>
</html>