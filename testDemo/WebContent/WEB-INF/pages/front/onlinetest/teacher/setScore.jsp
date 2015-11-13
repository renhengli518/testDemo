<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/fixedRight.jsp"%>
 <script>
 $(function(){
	  	var getMoveFn = function (opt) {
	  		return function () {
	  			var $parent = $(this).parents(".quesDetail");
	  		    var $elm = $parent[opt.find](".quesDetail");
		  		if ($elm.length > 0) {
		  			$parent[opt.move]($elm);
				}
				return false;
	  		}
	  	}

	  	$(".borderBox").on("click", ".removeDown", getMoveFn({
	  		find: 'next',
	  		move: 'insertAfter'
	  	}));
	  	$(".borderBox").on("click", ".removeUp", getMoveFn({
	  		find: 'prev',
	  		move: 'insertBefore'
	  	}));


	  })
</script> 
<div class="mainIndex" id="setscore" style="display:none">
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
                <a href="javascript:;">我的试卷</a><span class="nextlevel">></span><a href="javascript:;" id="tosetques" >新建试卷</a><span class="nextlevel">></span><span class="currentLevel">设置分值</span>
              </div>
              <div class="borderBox bkgWhite pd20 mt20">
	              <ul class="commonUL c000">
	                <li>
	                                                  试卷标题：<span class="fixedWidthSpan c888" id="examTitle2"></span>
	                                                  学段：<span class="fixedWidthSpan c888" id="semesterOpts2"></span>
	                                                  年级：<span class="fixedWidthSpan c888" id="classLevelOpts2"></span>
	                                                  学科：<span class="fixedWidthSpan c888" id="subjOption2"></span>
	                </li>
	                <li class="mt10">
	                                                 试卷类型：<span class="fixedWidthSpan c888" id="examtypeOpts2"></span>答题时间：<span class="fixedWidthSpan c888" id="answerTime2"><b class="red">120</b>分钟</span>
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
                                             试卷总分：<span class="mr30"><i class="red fontNormal" id="scoreInput2"></i>分</span>     已设置 <span class="red" id="seted"></span>分，剩余 <span class="red" id="notset"></span>分
             </div>
              <form id="selectedQuestions">
              <div class="borderBox bkgWhite pl30 pr30 mt20">
				
                   <div class="testCon testConLeastMargin typebox" id="type1show">
                        <h3 class="testTitle"><span id="type1no"></span>、单选题(<span class="type1num quesNum" >0</span>题)</h3>
                        <div id = "type1div">
						</div>
                   </div>
                   <div class="testCon testConLeastMargin typebox" id="type2show">
                        <h3 class="testTitle"><span id="type2no"></span>、多选题(<span class="type2num quesNum" >0</span>题)</h3>
                        <div id = "type2div">
						</div>
                   </div>
                   <div class="testCon testConLeastMargin typebox" id="type3show">
                        <h3 class="testTitle"><span id="type3no"></span>、判断题(<span class="type3num quesNum" >0</span>题)</h3>
                        <div id = "type3div">
						</div>
                   </div>
                   <div class="testCon testConLeastMargin typebox" id="type4show">
                        <h3 class="testTitle"><span id="type4no"></span>、填空题(<span class="type4num quesNum" >0</span>题)</h3>
                        <div id = "type4div">
						</div>
                   </div>
                   <div class="testCon testConLeastMargin typebox" id="type5show">
                        <h3 class="testTitle"><span id="type5no"></span>、问答题(<span class="type5num quesNum" >0</span>题)</h3>
                        <div id = "type5div">
						</div>
                   </div>
                   <div class="testCon testConLeastMargin typebox" id="type6show">
                        <h3 class="testTitle"><span id="type6no"></span>、计算题(<span class="type6num quesNum" >0</span>题)</h3>
                        <div id = "type6div">
						</div>
                   </div>
                  
                   <br/>
              </div>
              </form>
              <p class="center mt40">
                <button id="createexam" class="btn btnSearch nextStep" >提交保存</button>
              </p>
			  
          </div>
       </div>
    </div>
   
</div>
