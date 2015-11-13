//隐藏已删除试题
function displayDel(obj){
	if($(obj).html() == "隐藏已删除试题"){
		//隐藏
		$(obj).html("显示已删除试题");
		$(".deletedQues").hide();
	}else{
		//显示
		$(".deletedQues").show();
		$(obj).html("隐藏已删除试题");
	}
}

//补全的题目div(初始化时用)
function completQuesDiv(){
	if($("#type1div").length <= 0){
		//如果没有div1
		$("#queBody").prepend('<div class="testCon" id="type1div" style="display: none;"><h3 class="testTitle"><span id="type1no">一</span>、单选题  <span class="quesNum">（<span class="type1num" style="margin-right:0px;">0</span>题）</span></h3></div>');
	}
	
	//如果没有div2
	if($("#type1div").next("#type2div").length <=0){
		$("#type1div").after('<div class="testCon" id="type2div" style="display: none;"><h3 class="testTitle"><span id="type2no">一</span>、多选题  <span class="quesNum">（<span class="type2num" style="margin-right:0px;">0</span>题）</span></h3></div>');
	}
	
	//如果没有div3
	if($("#type2div").next("#type3div").length <=0){
		$("#type2div").after('<div class="testCon" id="type3div" style="display: none;"><h3 class="testTitle"><span id="type3no">一</span>、判断题  <span class="quesNum">（<span class="type3num" style="margin-right:0px;">0</span>题）</span></h3></div>');
	}
	
	//如果没有div4
	if($("#type3div").next("#type4div").length <=0){
		$("#type3div").after('<div class="testCon" id="type4div" style="display: none;"><h3 class="testTitle"><span id="type4no">一</span>、填空题  <span class="quesNum">（<span class="type4num" style="margin-right:0px;">0</span>题）</span></h3></div>');
	}
	
	//如果没有div5
	if($("#type4div").next("#type5div").length <=0){
		$("#type4div").after('<div class="testCon" id="type5div" style="display: none;"><h3 class="testTitle"><span id="type5no">一</span>、问答题  <span class="quesNum">（<span class="type5num" style="margin-right:0px;">0</span>题）</span></h3></div>');
	}
	
	//如果没有div6
	if($("#type5div").next("#type6div").length <=0){
		$("#type5div").after('<div class="testCon" id="type6div" style="display: none;"><h3 class="testTitle"><span id="type6no">一</span>、计算题  <span class="quesNum">（<span class="type6num" style="margin-right:0px;">0</span>题）</span></h3></div>');
	}
}




																										