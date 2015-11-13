<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../common/fixedRight.jsp"%>
<div class="mainIndex" id="setscore" style="display:none">
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
				<div>
					<a href="${root}/questionLib/toSharedLib.html">题库</a>
					<span class="nextArrow">&gt;</span>
					<a href="${root}/schoolTest/toRealExam.html">真题试卷</a>
					<span class="nextArrow">&gt;</span> 
					<c:if test="${pageName eq 'save'}">
						<a href="javaScript:;"  onclick="showCreateQuestion()" class="currentLevel">创建试卷</a>
					</c:if>
					<c:if test="${pageName eq 'edit'}">
						<a href="javaScript:;"  onclick="showCreateQuestion()" class="currentLevel">编辑试卷</a>
					</c:if>
					<span class="nextArrow">&gt;</span><span>设置分值</span>
				</div>
				<div class="borderBox bkgWhite pd20 mt20">
	              <ul class="commonUL c000">
	                 <li>
		                                                  试卷标题：<span class="fixedWidthSpan1 c888" id="examTitle2"></span>
		                                                  地区：<span class="fixedWidthSpan1 c888" id="areaName2"></span>
		                                                  年份：<span class="fixedWidthSpan1 c888" id="year2"></span>
	                </li>
	                <li class="mt10">
		                                                 试卷类型：<span class="fixedWidthSpan1 c888" id="examtypeOpts2"></span>
		                                                 学段：<span class="fixedWidthSpan1 c888" id="semesterOpts2"></span>
		                                                  年级：<span class="fixedWidthSpan1 c888" id="classLevelOpts2"></span>
		                                                  学科：<span class="fixedWidthSpan1 c888" id="subjOption2"></span>                                 
		                                                 答题时间：<span class="fixedWidthSpan1 c888" id="answerTime2"><b class="red">120</b>分钟</span>
	                </li>
	                <li class="mt10">
		                                                 批量设每题分值：  
						<span id="type1scoreshow" class="mr30">单选题(<span class="type1num" style="margin-right:0px;">0</span>题)：<input id="set1" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
						<span id="type2scoreshow" class="mr30">多选题(<span class="type2num" style="margin-right:0px;">0</span>题)：<input id="set2" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
						<span id="type3scoreshow" class="mr30">判断题(<span class="type3num" style="margin-right:0px;">0</span>题)：<input id="set3" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
						<span id="type4scoreshow" class="mr30">填空题(<span class="type4num" style="margin-right:0px;">0</span>题)：<input id="set4" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
						<span id="type5scoreshow" class="mr30">问答题(<span class="type5num" style="margin-right:0px;">0</span>题)：<input id="set5" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
						<span id="type6scoreshow" class="mr30">计算题(<span class="type6num" style="margin-right:0px;">0</span>题)：<input id="set6" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
		                <button class="btn btnOrange fr mr30" onclick="setScore();">设定</button>                            
	                </li> 
	              </ul>
             </div>
             <div class="borderBox bkgWhite pd10 mt20">
                                             试卷总分：<span class="mr30"><i class="red fontNormal" id="scoreInput2"></i>分</span>
                                             已设置 <span class="red" id="seted"></span>分，剩余 <span class="red" id="notset"></span>分
             </div>
			<div class="borderBox bkgWhite pl30 pr30 mt20">

				<div class="testCon testConLeastMargin typebox" id="type1show">
					<h3 class="testTitle">
						<span id="type1no"></span>、单选题(<span class="type1num quesNum">0</span>题)
					</h3>
					<div id="type1div"></div>
				</div>
				<div class="testCon testConLeastMargin typebox" id="type2show">
					<h3 class="testTitle">
						<span id="type2no"></span>、多选题(<span class="type2num quesNum">0</span>题)
					</h3>
					<div id="type2div"></div>
				</div>
				<div class="testCon testConLeastMargin typebox" id="type3show">
					<h3 class="testTitle">
						<span id="type3no"></span>、判断题(<span class="type3num quesNum">0</span>题)
					</h3>
					<div id="type3div"></div>
				</div>
				<div class="testCon testConLeastMargin typebox" id="type4show">
					<h3 class="testTitle">
						<span id="type4no"></span>、填空题(<span class="type4num quesNum">0</span>题)
					</h3>
					<div id="type4div"></div>
				</div>
				<div class="testCon testConLeastMargin typebox" id="type5show">
					<h3 class="testTitle">
						<span id="type5no"></span>、问答题(<span class="type5num quesNum">0</span>题)
					</h3>
					<div id="type5div"></div>
				</div>
				<div class="testCon testConLeastMargin typebox" id="type6show">
					<h3 class="testTitle">
						<span id="type6no"></span>、计算题(<span class="type6num quesNum">0</span>题)
					</h3>
					<div id="type6div"></div>
				</div>

				<br />
			</div>
				
			<form name="saveRealExamForm" id="saveRealExamForm" method="post" >
				<input type="hidden" id="save_examQueList" name="examQueList"></input>
				<input type="hidden" id="save_examId" name="examId"></input>
				<input type="hidden" id="save_title" name="title"></input>
				<input type="hidden" id="save_areaName" name="areaName"></input>
				<input type="hidden" id="save_year" name="year"></input>
				<input type="hidden" id="save_examTypeId" name="examTypeId"></input>
				<input type="hidden" id="save_baseSemesterId" name="baseSemesterId"></input>
				<input type="hidden" id="save_baseClasslevelId" name="baseClasslevelId"></input>
				<input type="hidden" id="save_baseSubjectId" name="baseSubjectId"></input>
				<input type="hidden" id="save_answerTime" name="answerTime"></input>
				<input type="hidden" id="save_score" name="score"></input>
				<input type="hidden" id="save_publicFlag" name="publicFlag"></input>
				<input type="hidden" id="saveOrEditType" name="saveOrEditType"></input>
			</form>
			<p class="center mt40">
				<button id="saveRealExam" class="btn btnSearch nextStep" onclick="saveRealExam()">提交保存</button>
			</p>
          </div>
       </div>
    </div>
</div>
