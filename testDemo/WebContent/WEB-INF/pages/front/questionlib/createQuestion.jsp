<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/meta.jsp"%>
<script type="text/javascript" src="${PUBLIC_PATH}/public/upload/uploadfile.js"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/js/extend.js"></script>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<link href="${PUBLIC_PATH}/public/css/reset.css" rel="stylesheet" type="text/css" media="all">
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
	text-align: center;
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


</head>
<body class="editQuestion">
	<%@include file="../../common/topHeader.jsp"%>
	<%@include file="../../common/queNav.jsp"%><!--加入题库面包屑-->
	<script src="${root}/biz/questionlib/public.js"></script>

	<!--  引入富文本框需要的插件-->
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/third-party/blank/addBlankButton.js"></script>
	<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
	<div class="editQues">
		<div>
			<a href="${root}/questionLib/toSharedLib.html">题库</a><span class="nextArrow">></span><a href="${root}/questionLib/toOwnLib.html">我的习题</a> <span class="nextArrow">></span>
			<span class="currentLevel">新建习题</span>
		</div>
		
		<!-- <a href="javascript:;" class="uploadBox btn" onclick="return false;">
	                               上传<span id="uploadCont" class="uploadCont"> </span>
        </a>
        <ul id="uploadInfoBox_content0" class="commonUL">
			
		 </ul> -->
		 
		 

		<form name="addForm" id="addForm" method="post" class="mainUl">
			<!-- 内容区 -->
			<div class="editQuesContent mt20">
				<span class="mr50"><b class="red">*</b>学段</span> <select  class="newSel w160 mr30" id="semesterOption"
					name="semesterOpts">
					<option value="0">请选择</option>
					<c:forEach items="${semesters}" var="semester">
						<option value="${semester.baseSemesterId }">${semester.semesterName}</option>
					</c:forEach>
				</select> <span class="mr50"><b class="red">*</b>年级</span> <select class="newSel w160 mr30" id="classLevelOption"
					name="classlevelOpts">
					<option value="0">请选择</option>
				</select> <span class="mr50"><b class="red">*</b>学科</span> <select class="newSel w160" id="subjOption" name="disciplineOpts">
					<option value="0">请选择</option>
				</select>
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
					<li style="line-height: normal"><a id="addChapterBtn" href="javascript:;" style="margin-top:-25px;" class="btn addChapter">增加章节</a></li>
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
						<li style="line-height:normal"><a id="addKnowledgeBtn" href="javascript:;" style="margin-top:-25px;" class="btn addChapter">增加知识点</a></li>
						<li>
							<div id="selectedKnowledges">
							</div>
							<input type="hidden" id="kn_input" name="kn_input">
						</li>
					</ul>
				</div>
			
	
			<ul class="commonUL  questionItem" data-qusetionIndex = '0'>
				<li><label class="text left">来源</label> 
					<span class="cont"> 
						<input id="Resource" type="text" style="width: 470px" name="source">
					</span>
				</li>
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
							<script  class="edtContent edtContentQue" name="questionSubArra[0].edt_content" type="text/plain" style="height: 200px; width: 99.8%"></script>
						<a href="javascript:;" class="changeBtn uploadBox btn" onclick="return false;">
	                                                     上传音视频<span id="uploadCont_content0" class="uploadCont"> </span>
                        </a>
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
						<div class="answerTypeDiv" style="margin-left:20px;">
							<label><input type="radio" name="questionSubArra[0].rdo_answerType" class="rdo_answerType" checked="checked" value="1" />独立答案</label>
							<label><input type="radio" name="questionSubArra[0].rdo_answerType" class="rdo_answerType ml20" value="2" />组合答案</label>
						</div>
						<input type="hidden" class="fillCount" id="fillInAnswerCnt0" name="questionSubArra[0].fillInAnswerCnt" value="0" />
						<table class="normalTable indep_answer anyTable">
						   <thead>
						     <tr>
								<th>
									<div class="specialTH">
										<span class="leftBottom">填空</span> <span class="rightTop">答案容错</span>
									</div>
								</th>
								<th>答案1</th>
								<th>答案2</th>
								<th>答案3</th>
								<th>答案4</th>
							</tr>
						   </thead>   
						</table>
						<table class="normalTable comb_answer anyTable" style="display: none">
						   <thead>
							<tr>
								<th>
									<div class="specialTH">
										<span class="leftBottom">填空</span> <span class="rightTop">答案容错</span>
									</div>
								</th>
								<th>第一组</th>
								<th>第二组</th>
								<th>第三组</th>
								<th>第四组</th>
							</tr>
						  </thead>
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
						<div>
							<script class="edtContent" name="questionSubArra[0].edt_resolve" type="text/plain" style="height: 200px; width: 99.8%"></script>
						</div>
						<a href="javascript:;" class="uploadBox btn" onclick="return false;">
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
			<div id="childrenQue"></div>
			<ul>
				<li><label class="text left"> <a href="javascript:;" class="addQuestionItem">+添加子题</a></label></li>
				<li><label class="text left"><span class="red">*</span>权限</label> 
					<span class="cont">
					   <c:if test="${requestScope.userType!=null}">
					       <c:if test="${requestScope.userType=='TEACHER'}">
					       <label class="mr20"><input name="usableType" value="SCHOOL" type="radio">&nbsp;&nbsp;仅供学校使用</label>
					       <label class="mr20"><input name="usableType" value="PUBLIC" type="radio">&nbsp;&nbsp;分享到平台</label>
					       <label class="mr20"><input name="usableType" value="PRIVATE" type="radio" checked="checked">&nbsp;&nbsp;仅自己使用</label>
					       </c:if>
					   </c:if>
					    <c:if test="${requestScope.userType!=null}">
					       <c:if test="${requestScope.userType=='SCHOOL_USR'}">
					       <label class="mr20"><input name="usableType" value="SCHOOL" type="radio" checked="checked">&nbsp;&nbsp;仅供学校使用</label>
					       <label class="mr20"><input name="usableType" value="PUBLIC" type="radio">&nbsp;&nbsp;分享到平台</label>
					       </c:if>
					   </c:if>	
					    <c:if test="${requestScope.userType!=null}">
					       <c:if test="${requestScope.userType=='AREA_USR'}">
					       <label class="mr20"><input name="usableType" value="AREA" type="radio" >&nbsp;&nbsp;仅供辖区内使用</label>
					       <label class="mr20"><input name="usableType" value="PUBLIC" type="radio">&nbsp;&nbsp;分享到平台</label>
					       </c:if>
					   </c:if>	
					</span>
				</li>
			</ul>
		</form>
		<li class="submitBtn" style="margin-left:450px; margin-top:30px;"><a class="btn bigBtn saveAndSubmit mr20" href="javascript:;" id="submitBtn">提交保存</a> 
		<a class="toClear" href="javascript:;" style="color: #54bdff"  id="cleanBtn">清空</a>
		</li>
		</ul>
	</div>
	<script type="text/javascript">
	
	var emptyOpt = '<option value="0">请选择</option>';
	$(function () {
		var undefined;
		var uuid = 1;
		var qusetionIndex = 1;
		var deleQuestionStr="";
		var emptyOpt = '<option value="0">请选择</option>';//通用成员变量
		//绑定难易程度下拉框
		bindDifficulty();
		
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
						UE.getEditor(key).addListener('ready', function() {
							if ('FILL_IN_BLANK' != $("input[name='questionTypeOpts']:checked").val()) {
								$("#"+  key + " .edui-for-blank > div").hide();
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
									html_indep += "<td style=\'font-weight:bolder;\' class=\'indexBank table_th\'>第" + formSeq(alt) + "空</td>";
									html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
									html_indep += "<span></span><textarea style=\"display:none\" name=\"indep_"+questionIndex+'_'+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "<textarea style=\"display:none\" name=\"indep_txt_"+questionIndex+"_"+alt+"_1\" rows=\"0\" cols=\"0\"></textarea>";
									html_indep += "</div></td>";
									html_indep += "<td class=\"edit-pop-btn table_th\"><div class=\"for-answer\">";
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
									$tr.find('.indexBank').html('第' + formSeq((index + 1)) + '空');
									$tr.find('textarea').each(
											function(index) {
												this.name = (index % 2 == 0 ? 'indep_'
														: 'indep_txt_')
														+questionIndex+"_"+ alt + '_' + (((index + 2) / 2) >> 0);
											})
									$(".indep_answer tr:eq(" + index + ")", $box).after($tr);
							
								}
								var $tr = $(".comb_answer tr.aTr" + uuid, $box);
								if ($tr.length == 0) {
									var html_comb = "<tr class=\"aTr" + uuid + "\" >";
									html_comb += "<td style=\'font-weight:bolder;\' class=\'combBank table_th\'>第" + formSeq(alt) + "空</td>";
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
									$tr.find('textarea')
											.each(
													function(index) {
														this.name = (index % 2 == 0 ? 'comb_'
																: 'comb_txt_')
																+questionIndex+"_"
																+ alt
																+ '_'
																+ (((index + 2) / 2) >> 0);
													})
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
			if (ty == 'FILL_IN_BLANK') {
				$(".edtContentQue .edui-for-blank > div", $item).show();
			} else {
				$(".edtContentQue .edui-for-blank > div", $item).hide();
			}
		};
		$('.questionTypeOpts').on('click', 'input', function () {
			var ty = this.value;
			$('.questionItem').each(function () {
				changeQueType(ty, $(this));
			})
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
										<textarea style="display: none" rows="0" cols="0" class="area" ></textarea>\
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
	        var cdf = "uploadInfoBox_content"+qusetionIndex+"flag";
	        var sdf="uploadInfoBox_resolve"+qusetionIndex+"flag";
			var singlehtml = '<ul class="questionSubItem questionItem commonUL" data-qusetionIndex = ' + qusetionIndex + '>\
								<li>\
									<label class="text left"><span class="red">*</span>子题种类</label>\
									<span class="cont" style="position: relative;display:inline-block;width:1095px;">\
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
											<a href="javascript:;" class="uploadBox changeBtn btn" onclick="return false;">\
                                                                                                                上传音视频<span id="uploadCont_content' + qusetionIndex + '" class="uploadCont"> </span>\
                                            </a>\
                                            <span id="'+cdf+'">\
                                            <ul id="'+cd+'" class="commonUL"></ul>\
                                            </span>\
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
										<input type="hidden" class="fillCount" id="fillInAnswerCnt'+qusetionIndex+'" name="questionSubArra[' + qusetionIndex + '].fillInAnswerCnt" value="0" />\
										<table class="normalTable indep_answer anyTable">\
										  <thead>\
											<tr>\
												<th>\
													<div class="specialTH">\
														<span class="leftBottom">填空</span> <span class="rightTop">答案容错</span>\
													</div>\
												</th>\
												<th>答案1</th>\
												<th>答案2</th>\
												<th>答案3</th>\
												<th>答案4</th>\
											</tr>\
										 </thead>\
										 <tbody>\
										 </tbody>\
										</table>\
										<table class="normalTable comb_answer anyTable" style="display: none">\
										  <thead>\
											<tr>\
												<th>\
													<div class="specialTH">\
														<span class="leftBottom">填空</span> <span class="rightTop">答案容错</span>\
													</div>\
												</th>\
												<th>第一组</th>\
												<th>第二组</th>\
												<th>第三组</th>\
												<th>第四组</th>\
											</tr>\
										  </thead>\
										  <tbody>\
										  </tbody>\
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
										<a href="javascript:;" class="uploadBox btn" onclick="return false;">\
                                                                                                        上传视频解析<span id="uploadCont_resolve' + qusetionIndex + '" class="uploadCont"></span>\
                                        </a>\
                                        <span id="'+sdf+'">\
                                        <ul id="'+sd+'" class="commonUL"></ul>\
                                        </span>\
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
		$("input[name='questionTypeOpts']:eq(0)").click();

	});
	
	
	
		var i = 0;
		$(function() {
			//年级级联学段    将学科,版本，分册，章节全置空
			$("#semesterOption").change(function() {
				//获得当前选中的学段的id
				var checkedOption = $("#semesterOption").val();
				$.post("${root}/commons/classLevelBySemId.do?semesterId=" + checkedOption, function(data) {
					//alert(data.length);
					var html = '<option value="0">请选择</option>';
					for (var i = 0; i < data.length; i++) {
						html += "<option value="+data[i].baseClasslevelId+">" + data[i].classlevelName + "</option>";
					}
					$("#classLevelOption").html(html);
					//将学科置为初始化状态
					$("#subjOption").empty();
					$("#subjOption").append(emptyOpt);
					
					$("#versionOption").empty();
					$("#versionOption").append(emptyOpt);
					
					$("#volumeOption").empty();
					$("#volumeOption").append(emptyOpt);
					
					$("#chapterOption").empty();
					$("#chapterOption").append(emptyOpt);
					
					$("#sectionOption").empty();
					$("#sectionOption").append(emptyOpt);
					
				}, 'json');
				
				//初始化知识点
				    $("#knowledgeOpts").empty();
			   	    $("#knowledgeOpts").append(emptyOpt);
			   	    
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
			});
			
			
			//学科级联年级   
			$("#classLevelOption").change(function() {
				//获得当前选中的年级的id
				var classLevelOption = $("#classLevelOption").val();
				//alert("classLevelOption="+classLevelOption);
				$.post("${root}/commons/subjbyclassid.do?classLevelId=" + classLevelOption, function(data) {
					var html = '<option value="0">请选择</option>';
					for (var i = 0; i < data.length; i++) {
						html += "<option value="+data[i].baseSubjectId+">" + data[i].baseSubjectName + "</option>";
					}
					$("#subjOption").html(html);
					//初始化版本分册章节
					$("#versionOption").empty();
					$("#versionOption").append(emptyOpt);
					
					$("#volumeOption").empty();
					$("#volumeOption").append(emptyOpt);
					
					$("#chapterOption").empty();
					$("#chapterOption").append(emptyOpt);
					
					$("#sectionOption").empty();
					$("#sectionOption").append(emptyOpt);
				}, 'json');
				
				//初始化知识点
				$("#knowledgeOpts").empty();
		   	    $("#knowledgeOpts").append(emptyOpt);
		   	    
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
			});
			
			//学科级联版本
			$("#subjOption").change(
					function() {
						//获得当前选中学科id
						var subjOption = $("#subjOption").val();
						//获得当前选中的年级的id
						var classLevelOption = $("#classLevelOption").val();
						$.post("${root}/commons/versionbysuclaid.do?baseClassLevelId=" + classLevelOption
								+ "&baseSubjId=" + subjOption, function(data) {
							var html = '<option value="0">请选择</option>';
							for (var i = 0; i < data.length; i++) {
								html += "<option value="+data[i].baseVersionId+">" + data[i].versionName + "</option>";
							}
							$("#versionOption").html(html);
							//初始化分册章节
							$("#volumeOption").empty();
							$("#volumeOption").append(emptyOpt);
							
							$("#chapterOption").empty();
							$("#chapterOption").append(emptyOpt);
							
							$("#sectionOption").empty();
							$("#sectionOption").append(emptyOpt);
							
						}, 'json');
						
				
						//初始化知识点
						 //绑定首节知识点
				   	    var semesterId = $("#semesterOption").val();//获得学段
				   	    bindKnowledgeBySemesterAndDiscipline(semesterId,subjOption);
				   	    
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
					});
			//版本级联分册      并将章节置空
			$("#versionOption").change(
					function() {

						//获得当前选中学科id
						var subjOption = $("#subjOption").val();
						//获得当前选中的年级的id
						var classLevelOption = $("#classLevelOption").val();
						//获得当前选中的版本的id
						var versionOption = $("#versionOption").val();
						$.post("${root}/commons/volumeversionbyverId.do?baseSubjId=" + subjOption + "&baseClassLevId="
								+ classLevelOption + "&baseVersionId=" + versionOption, function(data) {
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

			//分册级联章节  将节置空
			$("#volumeOption").change(
					function() {

						//获得当前选中学科id
						var subjOption = $("#subjOption").val();
						//获得当前选中的年级的id
						var classLevelOption = $("#classLevelOption").val();
						//获得当前选中的版本的id
						var versionOption = $("#versionOption").val();
						//获得当前选中的分册的id
						var volumeOption = $("#volumeOption").val();
						$.post("${root}/commons/chaptervolumebycid.do?baseVersionId=" + versionOption + "&baseSubjId="
								+ subjOption + "&baseClassLevId=" + classLevelOption + "&baseVolumeId=" + volumeOption,
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

						//获得当前选中学科id
						var subjOption = $("#subjOption").val();
						//获得当前选中的年级的id
						var classLevelOption = $("#classLevelOption").val();
						//获得当前选中的版本的id
						var versionOption = $("#versionOption").val();
						//获得当前选中的分册的id
						var volumeOption = $("#volumeOption").val();
						//获得当前选中的章
						var chapterOption = $("#chapterOption").val();

						$.post("${root}/commons/sectionchapbycid.do?baseVersionId=" + versionOption + "&baseSubjId="
								+ subjOption + "&baseClassLevId=" + classLevelOption + "&baseChapterId="
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

						var semesterName = $("#semesterOption option:selected").html(); //学段名称
						var classLevelName = $("#classLevelOption option:selected").html(); //年级名称
						var disciplineName = $("#subjOption option:selected").html(); //学科名称 
						var versionName = $("#versionOption option:selected").html(); //版本名称
						var volumeName = $("#volumeOption option:selected").html(); //分册名称
						var chapterName = $("#chapterOption option:selected").html(); //章名称
						var sectionName = $("#sectionOption option:selected").html(); //节名称
						
						var versionId=$("#versionOption").val();//获得选中的版本的id
						var volumeId = $("#volumeOption").val();//获得选中分册的id
						var chapterId = $("#chapterOption").val();//获得选中的章的id
						var sectionId = $("#sectionOption").val();//获得选中的节的id
						
						//如果章没有被选择 则节一定不能被
						if('0'==chapterId && '0'!=sectionId){
							Win.alert("请按要求选择章节！");
							return;
						}
						/* alert("获得的版本分册章节的id="+versionId+"-->"+volumeId+"-->"+chapterId+"-->"+sectionId); */
						
						var connChar = "-->";
						/* var connChapterName = semesterName + connChar + classLevelName + connChar + disciplineName
								+ connChar + versionName; */ //使用-->符号将学段-->年级-->学科-->版本  进行拼接成一个字符串
						/* alert("拼接好选中的从学段到节的名字字符串的拼接="+connChapterName); */
						var connChapterName =versionName;//不用显示上面公用的学段，年级，学科
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
						var connHtml = "<span class=\"smallBlock chapBlock itemDel\" style=\"height:28px;\" child ='"+connChapterIds+"' id='"+valObj+"'>"
								+ connChapterName + "<a href=\"javascript:remo('"+valObj+"')\"></a><input type=\"hidden\" name=\"ch_input\" value='"+connChapterIds+"'/></span>";
						$("#selectedChapters").append(connHtml);
					});
			
			
			


			//提交新建题库的表单
        	$("#submitBtn").click(function(){
        		
        		//增加校验  //如果音视频正在上传进行提交验证
        		var flag = true;
        		$(".uploaded").each(function(){
        			var text = $(this).text();
        			if(text!=""||text!=null){
        				Win.alert("视频正在上传中，请稍等!");
        				flag = false;
        				return false;
        			}
        		});
        		if(!flag){
        			return false;
        		}

        		//验证学段，年级，学科是否已选
        		var semster= $("#semesterOption").val();
	            var classLevel=$("#classLevelOption").val();
	            var subjectOpt=$("#subjOption").val();
        		if("0"==semster){
        			Win.alert("请选择学段！");
        			return;
        		}
        		if("0"==classLevel){
        			Win.alert("请选择年级！");
        			return;
        		}
        		if("0"==subjectOpt){
        			Win.alert("请选择学科！");
        			return;
        		}
        		
        		var Resource =$("#Resource").val();//对输入的来源进行验证
        		if(Resource.length>30){
        			Win.alert("您最多可输入30字符！");
        			return;
        		}
        	    //对题干内容大小和解析的内容大小进行判断
        	    var editorArr = $(".edtContent");
        	    for (var i=0,j=editorArr.length;i<j;i++) {
        	    	var id =$(editorArr[i]).attr("id")
        	    	 var numIndex=id.substring(id.length-1);// 奇数表示题干   偶数表示解析
        	    	 var questionContent=$.trim(UE.getEditor(id).getContentTxt());//获得纯文本内容
        	    	 //console.log("现在:"+UE.getEditor(id).getContentLength(true));
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
        	    			return;
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
        	    			Win.alert("请选择答案选项!");
        	    			return;
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
    	    			Win.alert("答案至少选择两项!");
    	    			return;
    	    		}
 
    	    	}
        	    }
          
        		
        	 //对填空题进行校验
        	    if(questionType=="FILL_IN_BLANK"){
        	    	   //1.获取所有填空题的空的个数
          	      var FillCountArr=$(".fillCount");
          	    //2.获得每道填空题是独立答案还是组合答案的数组
          	      var answerTypeArr=$(".answerTypeDiv label input:checked");
          	
         	          //var scoreTypeArr=$(".scoreTypeDiv label input:checked");
          	    
          	      for(var i=0; i<FillCountArr.length; i++){//题目的个数和填空题数组的大小一致(因为一道题一个隐藏域)
           	    	 var questionCount =$(FillCountArr[i]).val();//获得目前每道填空题的空的个数 
           	    	 var answerTypeValue=$(answerTypeArr[i]).val();//答案类型
           	    	 var questionIndex=$(FillCountArr[i]).attr("id");
           	    	 questionIndex=questionIndex.substring(questionIndex.length-1);//获得每道题的下标
           	    	 //alert("第"+(i+1)+"题:"+questionCount+"==="+answerTypeValue+"====index="+questionIndex);
           	    	if(questionCount==0){//表示题干中未填充填空题
           	    		Win.alert("第"+(i+1)+"题请至少设置一个填空位！");
      	    			return;
           	    	}else{//表示题干中含有填空题
           	    	
           	     if("1"==answerTypeValue){//表示是独立答案
           	      	 for(var j=1; j<=questionCount; j++){
            	    		
               	    		 // 获得每行的所有值
            	    		  var indep_1 = $.trim($("textarea[name='indep_"+questionIndex+"_"+j+"_1']").text());
         					  var indep_2 = $.trim($("textarea[name='indep_"+questionIndex+"_"+j+"_2']").text());
         					  var indep_3 = $.trim($("textarea[name='indep_"+questionIndex+"_"+j+"_3']").text());
         					  var indep_4 = $.trim($("textarea[name='indep_"+questionIndex+"_"+j+"_4']").text());
         					  //alert(indep_1+"=="+indep_2+"=="+indep_3+"=="+indep_4);
         					  if(""==indep_1 && ""==indep_2 && ""==indep_3 && ""==indep_4){//当每行全空的时候进行提示(每行至少有一个答案，不能全空)
         						Win.alert("第"+(i+1)+"题每空至少设置一个答案!");
           	    			      return;
         					  }
         					  if(""!=indep_4){//如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
         						  if(""==indep_3 || ""==indep_2 || ""==indep_1){
         							Win.alert("第"+(i+1)+"题请按顺序填写答案!");
               	    			return;
         						  }
         					  }
         					if(""!=indep_3 && ""==indep_4){// 如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
       						  if(""==indep_2 || ""==indep_1){
       							Win.alert("第"+(i+1)+"题请按顺序填写答案!");
             	    			return;
       						  }
       					  }
         					if(""!=indep_2 && ""==indep_3 && ""==indep_4 ){// 如果每行的最后一个数据不为空则它所在行的之前的所有数据都不能为空
       						  if(""==indep_1){
       							Win.alert("第"+(i+1)+"题请按顺序填写答案!");
             	    			return;
       						  }
       					  }
         					
               	    	  }
            	    	 }
           	    	
           	    	 if("2"==answerTypeValue){//组合答案
           	    		   
           	    		 //alert("组合");
           	    		var recoredCloumStatus="";
           	    		var count;
        	    		  for(var k=1; k<5; k++){
        	    			 count=0;//来与总的空的个数进行比较,如果小于总空的个数则表示不是满列
        	    			 for(var r=1; r<=questionCount; r++){
        	    			  var comb = $.trim($("textarea[name='comb_"+questionIndex+"_"+r+"_"+k+"']").text());//每列循环遍历
  	      	    			  if(""!=comb){
  	      	    				  count++;
  	      	    			  }
        	    			 }
        	    	 		  if(1==k){
        	    				if(count<questionCount){//表示第一列未满列
        	    					Win.alert("请按顺序将第"+(i+1)+"题组合答案填全!");
               	    			   return;
           	    			 }
        	    			 } 
        	    			  if(2==k){
        	    				 if(count<questionCount && count>0){
        	    					recoredCloumStatus+="0"+",";
        	    				 }else if(count==questionCount){
        	    					recoredCloumStatus+="1"+",";
        	    				 }else if(count==0){//表示是空列
        	    					recoredCloumStatus+="n"+",";
        	    				 }
        	    			 }
        	    			 if(3==k){
        	    				 if(count<questionCount && count>0){
         	    					recoredCloumStatus+="0"+",";
         	    				 }else if(count==questionCount){
         	    					recoredCloumStatus+="1"+",";
         	    				 }else if(count==0){//表示是空列
        	    					recoredCloumStatus+="n"+",";
        	    				 }
         	    			 }
        	    			 if(4==k){
        	    				 if(count<questionCount && count>0){
          	    					recoredCloumStatus+="0"+",";
          	    				 }else if(count==questionCount){
          	    					recoredCloumStatus+="1";
          	    				 }else if(count==0){//表示是空列
        	    					recoredCloumStatus+="n";
        	    				 }
          	    			 }   
        	    		 
        	    	   }
        	    		  //获得满列以及判断满列与首列之间是非存在非满列   若存在则表示不合法
        	    		  // alert("recoredCloumStatus="+recoredCloumStatus);
        	    		  //获得最后一个满列  如果第一个是满列则直接通过(因为第一列一定是满列) 从第二列开始算起
        	    		  var flagArr=recoredCloumStatus.split(",");//只有三个数字
        	    		  //alert("flagArr="+flagArr.length);
                              
        	    			  if(flagArr[0]=="0" || flagArr[1]=="0" || flagArr[2]=="0"){//将所有的未满列拦截并提示
        	    				  Win.alert("请将第"+(i+1)+"题组合答案填全!");
                 	    		  return;
        	    			  }
        	    		  
        	    		     if(flagArr[0]=="1" && flagArr[1]=="n" && flagArr[2]=="1" || flagArr[0]=="n" && flagArr[1]=="1" && flagArr[2]=="1"){
        	    		    	 Win.alert("请按顺序填写第"+(i+1)+"题的组合答案!");
                	    		  return;
        	    		     }
        	    		     
        	    		  
        	    		  
        	    			  if(flagArr[2]=="1"){//表示最后一个是满列
        	    				  if(flagArr[0]=="0" || flagArr[1]=="0" || flagArr[0]=="n" || flagArr[1]=="n"){//表示中间出现未满列或空列
        	    						Win.alert("请按顺序填写第"+(i+1)+"题的组合答案!");
                       	    			return;
        	    				  }
        	    			  }
        	    		     
        	    		      if(flagArr[1]=="1" && flagArr[2]=="n"){//表示倒数第二个是满列而最后一列是空列
        	    		    	  if(flagArr[0]=="0" || flagArr[0]=="n" ){//若倒数第三个是未满列则不满足要求
        	    		    		  Win.alert("请按顺序填写第"+(i+1)+"题的组合答案!");
                     	    			return;
        	    		    	  }
        	    		      }

           	    	   }
     	    	 
           	    	}
           	
           	    	 
           	      }
        	    	
        	    }
        	   
        
	    //表单提交
        	   // edt_content.sync();
        	    var formData = $("#addForm").serialize();
        	   
        	 	$.post("${root}/createquestionlib/createquestion.do", formData,
        				function(res) {
        					if(res=="yes"){
        						Win.alert("提交成功");
        						document.location.href = "${root}/questionLib/toOwnLib.html";
        					} else if(res=="no"){
        						Win.alert("提交失败");
        					}
        				}
        			);  
        	}); 
	 
		});
		
		function remo(obj){//删除指定添加的章节
	      	$("#"+obj).remove();
		}
		
		
		
		
//进行添加知识点的操作
				 $("#addKnowledgeBtn").click(function(){
			           var knowledgeCnt = $("#selectedKnowledges span").size();
			           if(knowledgeCnt >= 5){
				           Win.alert("最多只能添加五个关联知识点！");
				           return;
			             }
			 
			          var knowledgeId = $("#knowledgeOpts").val();
			          
			           if(knowledgeId ==0 || knowledgeId == ""){
				                  Win.alert("请至少选择到第一节知识点！");
				                    return;
			                      } 
			 
			 //增加该节点
			 var semesterName = $("#semesterOption option:selected").html(); //学段名称
			 var classLevelName = $("#classLevelOption option:selected").html(); //年级名称
			 var disciplineName = $("#subjOption option:selected").html(); //学科名称 
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
			 // var connKnowledgeName = semesterName + connChar + classLevelName +connChar+ disciplineName + connChar +knowledgeName;
			 var connKnowledgeName =knowledgeName;//只显示相同学段 年级  学科下的知识点项目即可
			 var connKnowledgeIds = knowledgeId;
			 
			 if(subKnowledgeId1 != '0' && subKnowledgeId1 != ''){
				 connKnowledgeName += connChar + subKnowledgeName1;
				 connKnowledgeIds += ':'+ subKnowledgeId1;
			 } else {
				 connKnowledgeIds += ':0';
			 };
			 
			 if(subKnowledgeId2 != '0' && subKnowledgeId2 != ''){
				 connKnowledgeName += connChar + subKnowledgeName2;
				 connKnowledgeIds += ':'+ subKnowledgeId2;
			 } else {
				 connKnowledgeIds += ':0';
			 };
			 
			 if(subKnowledgeId3 != '0' && subKnowledgeId3 != ''){
				 connKnowledgeName += connChar + subKnowledgeName3;
				 connKnowledgeIds += ':'+ subKnowledgeId3;
			 } else {
				 connKnowledgeIds += ':0';
			 };
			 
			 if(subKnowledgeId4 != '0' && subKnowledgeId4 != ''){
				 connKnowledgeName += connChar + subKnowledgeName4;
				 connKnowledgeIds += ':'+ subKnowledgeId4;
			 } else {
				 connKnowledgeIds += ':0';
			 };
			 
			 if(subKnowledgeId5 != '0' && subKnowledgeId5 != ''){
				 connKnowledgeName += connChar + subKnowledgeName5;
				 connKnowledgeIds += ':'+ subKnowledgeId5;
			 } else {
				 connKnowledgeIds += ':0';
			 };
			 
			 //判断该节点重复性
			 var repFlag = false;
			 $("#selectedKnowledges span").each(function(i,n){
				 var child = $(n).attr("child");
				 if(connKnowledgeIds == child){
					 repFlag = true;
				 }
			 });
			 
			 if(repFlag){
				 Win.alert("该关联知识点已添加");
				 return;
			 } 
			  
			 var reg=new RegExp(":","g");
			 var valKnowObj =connKnowledgeIds.replace(reg,"");  //将逗号替换为
			 
			 var connHtml = "<span class=\"smallBlock chapBlock itemDel\" style=\"height:28px;\" child ='"+connKnowledgeIds+"'  id='"+valKnowObj+"'>" + connKnowledgeName + "<a href=\"javascript:remoKnowledge('"+valKnowObj+"')\"></a><input type=\"hidden\" name=\"k_input\" value='"+connKnowledgeIds+"'/></span>";
			 $("#selectedKnowledges").append(connHtml);
		 });
		 $("select[id^='knowledgeOpts']").not("#knowledgeOpts").hide();//隐藏其他的知识点框
		 
		 
		 
		 function remoKnowledge(obj){//删除指定添加的知识点
			 
			 $("#"+obj).remove();
		 }
		 
//选择不同的知识点事件(根据)
		 $("#knowledgeOpts").change(function(){
			 var knowledgeId = this.value;
			 bindSubKnowledgeByParentId(this.value,function(data){
				    var optObj = $("#knowledgeOpts1");
					if(data.length > 0){					
						optObj.show();
					} else {
						optObj.hide();
					}
					bindSubKnowledgeData(optObj,data);         //------------------
					
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
		 
		 
		 $("#knowledgeOpts1").change(function(){
			 var knowledgeId = this.value;
			 bindSubKnowledgeByParentId(this.value,function(data){
				    var optObj = $("#knowledgeOpts2");
					if(data.length > 0){						
						optObj.show();
					} else {
						optObj.hide();
					}
					bindSubKnowledgeData(optObj,data);
			   	    
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
		 
		 $("#knowledgeOpts2").change(function(){
			 var knowledgeId = this.value;
			 bindSubKnowledgeByParentId(this.value,function(data){
					var optObj = $("#knowledgeOpts3");
					if(data.length > 0){
						optObj.show();
					} else {
						optObj.hide();
					}
					bindSubKnowledgeData(optObj,data);
					
				
			   	    $("#knowledgeOpts4").empty();
			   	    $("#knowledgeOpts4").append(emptyOpt);
			   	    $("#knowledgeOpts4").hide();
			   	    
			   	    $("#knowledgeOpts5").empty();
			   	    $("#knowledgeOpts5").append(emptyOpt);
			   	    $("#knowledgeOpts5").hide();				
			 });
		 });
		 
		 $("#knowledgeOpts3").change(function(){
			 var knowledgeId = this.value;
			 bindSubKnowledgeByParentId(this.value,function(data){
				    var optObj = $("#knowledgeOpts4");
					if(data.length > 0){						
						optObj.show();
					} else {
						optObj.hide();
					}
					bindSubKnowledgeData(optObj,data);
			   	    
			   	    $("#knowledgeOpts5").empty();
			   	    $("#knowledgeOpts5").append(emptyOpt);
			   	    $("#knowledgeOpts5").hide();				
			 });
		 });
		 
		 $("#knowledgeOpts4").change(function(){
			 var knowledgeId = this.value;
			 bindSubKnowledgeByParentId(this.value,function(data){//-----------------------------------
					var optObj = $("#knowledgeOpts5");
					if(data.length > 0){
						optObj.show();
					} else {
						optObj.hide();
					}
					bindSubKnowledgeData(optObj,data);				
			 });
		 });
		 
		 $('#selectedKnowledges').on('click','span a', function(e){
				$(this).parent().remove();//移除页面中显示的知识点元素
		  });
		 	 
	</script>
	
<script>

/**********上传 *********/
 function initSwf(id){
	 var params="";
	 var index=id.substring(id.length-1);
	 var uId="#uploadCont"+id+"flag";
	 var spanJudge="#uploadInfoBox"+id+"flag";//每个视频，音频只能添加一个
	 var contentIndex=index+"content";
	 
	 if(id.indexOf("content")>0){//题干既可以播放视频也可以播放音频
		 params= {
		         debug : 1,
		         fileType : "*.mp4;*.flv;*.mp3;",
		         typeDesc : "音频",
		         sizeLimit : 500*1024*1024,
		         autoStart : true,
		         multiSelect : 0,  //表示每次只能上传一个     1 表示可以进行批量上传
		         server: encodeURIComponent("${LOCAL_PATH}/videoUpload")
		 	}; 
	 }else{//解析不能播放音频
		 
		 params= {
		         debug : 1,
		         fileType : "*.mp4;*.flv;",
		         typeDesc : "音频",
		         sizeLimit : 500*1024*1024,
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
	  	 		//对音频进行处理   如果大于100M则不能进行上传操作
	  	 		var videoName=info.name;
	  	 		var index1=videoName.lastIndexOf(".");
 		        var index2=videoName.length;
 		        var postf=videoName.substring(index1,index2);//获取文件后缀名
 		       if($(""+spanJudge+" ul li").size()>=1){
	  	 			
	  	 			Win.alert("请删除已有的视频才可上传新的视频文件!");
					return;
	  	 		} 
 		       
 		        if(postf.indexOf("mp3")>0){//表示是音频
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


	  	  	window["uploadSwf"+id].uploadEvent.add("noticeSizeError",function(){ //不会执行oncomplete事件
	  	 		//alert("noticeSizeError");
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
	
	
	

	$("#cleanBtn").click(function() {//清空所有的基础化信息
		//基础信息初始化
		
		//1.将学段，知识点，章节全部初始化为请选择状态    将新添加的章节和知识点清空
		 $("#semesterOption").val("0");
	
	     $("#classLevelOption").empty();
	     $("#classLevelOption").append(emptyOpt);
	    
	     $("#subjOption").empty();
	     $("#subjOption").append(emptyOpt);
	     
	     $("#versionOption").empty();
	     $("#versionOption").append(emptyOpt);
	     
	     $("#volumeOption").empty();
	     $("#volumeOption").append(emptyOpt);
	     
	     $("#chapterOption").empty();
	     $("#chapterOption").append(emptyOpt);
	     
	     $("#sectionOption").empty();
	     $("#sectionOption").append(emptyOpt);
	     
	     $("#selectedChapters span").remove();//移出所有添加的章节
	     
	     $("#knowledgeOpts").empty();//初始化知识点
	     $("#knowledgeOpts").append(emptyOpt);
	     
	     $("#knowledgeOpts").trigger("change");
	     $("#selectedKnowledges span").remove();//移除所有添加的知识点
	     
	     $("#Resource").val("");// 将填写的来源进行清空
	     $("#difficultyOpts").val("EASY");//将难易度默认选择为容易
	     
	 
	    var editorArr = $(".edtContent");
	 // 将题干和解析进行清空操作  清空富文本框的内容UEdit_+i
	     for (var i=0,j=editorArr.length;i<j;i++) {
	    	 var idFlag =$(editorArr[i]).attr("id")
	    	 UE.getEditor(idFlag).setContent("");//获取最上面的div的id即为他的容器id 
	     }
	 
	    //清空所有选项内容    如果是单选题或多选题或判断题则清除所有选项的内容
	    var questionType=$("input[name='questionTypeOpts']:checked").val();
	    if(questionType=="SINGLE_CHOICE" || questionType=="MULTI_CHOICE" || questionType=="JUDEMENT"){
	    	
	    	  var editOptionArr=$(".optDiv li div ");
	 	     for(var i=0; i<editOptionArr.length; i++){
	 	    	$(editOptionArr[i]).children("span").get(1).innerHTML="";
	 	    	$(editOptionArr[i]).children("input").get(1).value="";
	 	    	$(editOptionArr[i]).children("textarea").get(0).innerHTML="";
	 	    	$(editOptionArr[i]).children("textarea").get(1).innerHTML="";
	 	    } 
	    }

	});
		
</script>

</body>
</html>