/************************** 页面验证处理  表单提交***********************************/
    var fillId;//填空题空格Id
	var fillInAnswerSplitChar = '∷';
    
  	function finishExamining(isauto){
  	   var backfunction = finishExamining;
	   finishExamining = function(){};
	   
	   var answerInfo = new Array();
	   
		/* 单选题 */
		var answeredSingles = new Array();
		var answerSingleNo = new Array();
		$("div .single").each(function(index, element){
			var eqid = $(this).attr("eqid");
			var no = $(this).attr("no");
			var answerSingleTemp = {};
			$(this).find(":radio:checked").each(function(){
				answeredSingles[index] = eqid;
				answerSingleNo[index] = no;
				answerSingleTemp["examQuestionId"] = eqid;
				answerSingleTemp["answer"] = $(this).attr("optId");
				answerInfo.push(answerSingleTemp);
			});
			//如果单选未答，则添加null值
			if(!contain(answeredSingles,eqid)){
				answerSingleTemp["examQuestionId"] = eqid;
				answerSingleTemp["answer"] = "";
				answerInfo.push(answerSingleTemp);
			}
		});
		
		/* 单选题未答 */
		var unAnsweredSingleIds = new Array();
		for (var i=0; i < singleArray.length; i++) {
			if (contain(answerSingleNo, singleArray[i]) == false) {
				unAnsweredSingleIds.push(singleArray[i]);
				
			}
		}
		
		/* 多选题 */
		var answeredMultis = new Array();
		var answerMultisNo = new Array();
		$("div.multi").each(function(index, element){
			var answerMultisTemp = {};
			var eqid = $(this).attr("eqid");
			var no = $(this).attr("no");
			var flag = false;
			$(this).find(":checked").each(function(){
				flag = true;
				if (typeof(answerMultisTemp["answer"]) == "undefined"){
					answerMultisTemp["answer"] = $(this).attr("optId");
					if(answerMultisTemp["answer"] != ''){
						answerMultisNo.push(no);
					}
				}else{
					if(answerMultisTemp["answer"] != ''){
						answerMultisTemp["answer"] += $(this).attr("optId");	
					}
				}
				
			});
		
			var isContained = contain(answeredMultis, eqid);
			if (isContained==false && flag) {
				answeredMultis.push(eqid);
			}else{
				//如果多选未答，则填充null值
				answerMultisTemp["answer"] = "";
				
			}
			answerMultisTemp["examQuestionId"] = eqid;
			answerInfo.push(answerMultisTemp);
		});
			
		/* 多选题未答 */
		var unAnsweredMultiIds = new Array();
		for (var i=0; i < multiArray.length; i++) {
			if (contain(answerMultisNo, multiArray[i]) == false) {
				unAnsweredMultiIds.push(multiArray[i]);
			}
		}
		
		
		/* 判断题 */
		var answeredJudement = new Array();
		var answerJudementNo = new Array();
		$("div.judement").each(function(index, element){
			var eqid = $(this).attr("eqid");
			var no = $(this).attr("no");
			var answerJudementTemp = {};
			$(this).find(":radio:checked").each(function(){
				answeredJudement[index] = eqid;
				answerJudementNo[index] = no;
				answerJudementTemp["examQuestionId"] = eqid;
				answerJudementTemp["answer"] = $(this).attr("optId");
				answerInfo.push(answerJudementTemp);
			});
			//如果判断题未答，则添加null值
			if(!contain(answeredJudement,eqid)){
				answerJudementTemp["examQuestionId"] = eqid;
				answerJudementTemp["answer"] = "";
				answerInfo.push(answerJudementTemp);
			}
		});
		
		/* 判断题未答 */
		var unAnsweredJudement = new Array();
		for (var i=0; i < judementArray.length; i++) {
			if (contain(answerJudementNo, judementArray[i]) == false) {
				unAnsweredJudement.push(judementArray[i]);
			}
		}
		
		
		/* 填空题 */
		var answeredFill = new Array();
		var answerFillNo = new Array();
		$("div .fillInBlank").each(function(index, element){
			var eqid = $(this).attr("eqid");
			var no = $(this).attr("no");
			var flag = true;
			var answerFillTemp = {};
			answerFillTemp["examQuestionId"] = eqid;
			/*填空题的空数*/
			var fillNo = 0;//填空题没填的空数
			$(this).find(".fillAnswer").each(function(){
				var value = $(this).html().replacePTag();
				if(value == null || value == ""){
					flag = false;
					fillNo++;
					
				}
				if(typeof(answerFillTemp["answer"]) == "undefined"){
					answerFillTemp["answer"] = value;
				}else{
					if(value != null && value != ''){
					   answerFillTemp["answer"] += (fillInAnswerSplitChar + value); 
					}
				}
			});
			if(fillNo < 1){
				answerFillNo[index] = no;
			}
			answerInfo.push(answerFillTemp);
			var isContained = contain(answeredFill, eqid);
			if (isContained==false && flag) {
				answeredFill.push(eqid);
			}
			
		});
		
		/* 填空题未答 */
		var unAnsweredFill = new Array();
		for (var i=0; i < fillArray.length; i++) {
			if (contain(answerFillNo, fillArray[i]) == false) {
				 unAnsweredFill.push(fillArray[i]);
			}
		}
		
		/* 问答题 */
		var answeredSubjs = new Array();
		var answerSubjsNo = new Array();
		$("div .answer").each(function(index, element){
			var eqid = $(this).attr("eqid");
			var no = $(this).attr("no");
			var sa = subjTxtResult[eqid].getContent();
			
			if($.trim(sa) != ''){
				answerSubjsNo[index] = no;
			}
			if(subjTxtResult[eqid].getContentTxt().length > 500){
				Win.alert("问答题，答题不能超出500字！");
				return false;
			}
			
			var answerTemp = {};
			answeredSubjs.push(eqid);
			answerTemp["examQuestionId"] = eqid;
			answerTemp["answer"] = sa;
			answerInfo.push(answerTemp);
		});
		
		/* 问答题未答 */
		var unAnsweredSubjIds = new Array();
		for (var i=0; i < answerArray.length; i++) {
			var video = $("#uploadInfoBox"+answerArray[i]+"").children().length;
			if (contain(answerSubjsNo, answerArray[i]) == false 
					&& video < 1) {
				unAnsweredSubjIds.push(answerArray[i]);
			}
		}
		
		
		/* 计算题 */
		var computingSubjs = new Array();
		var computingSubjsNo = new Array();
		$("div .computing").each(function(index, element){
			var eqid = $(this).attr("eqid");
			var no = $(this).attr("no");
			var sa = subjTxtResult[eqid].getContent();
			
			if($.trim(sa) != ''){
				computingSubjsNo[index] = no;
			}
			
			if(subjTxtResult[eqid].getContentTxt().length > 500){
				Win.alert("计算题，答题不能超出500字！");
				return false;
			}
			
			var computingTemp = {};
			computingSubjs.push(eqid);
			computingTemp["examQuestionId"] = eqid;
			computingTemp["answer"] = sa;
			answerInfo.push(computingTemp);
		});
	
		/* 计算题未答 */
		var uncomputingSubjIds = new Array();
		for (var i=0; i < compArray.length; i++) {
			var video = $("#uploadInfoBox"+compArray[i]+"").children().length;
			if (contain(computingSubjsNo, compArray[i]) == false 
					&& video < 1) {
				uncomputingSubjIds.push(compArray[i]);
			}
		}
		
	
		/* 未答题号提示 */
		var confirmStr = "";
		if (unAnsweredSingleIds.length > 0) {
			confirmStr = "单选题：" + unAnsweredSingleIds.join("、");
		}
		
		if (unAnsweredMultiIds.length > 0) {
			confirmStr = confirmStr + " 多选题：" + unAnsweredMultiIds.join("、");
		}
		
		if (unAnsweredJudement.length > 0) {
			confirmStr = confirmStr + " 判断题：" + unAnsweredJudement.join("、");
		}
		
		if (unAnsweredFill.length > 0) {
			confirmStr = confirmStr + " 填空题：" + unAnsweredFill.join("、");
		}
		
		if (unAnsweredSubjIds.length > 0){
			confirmStr = confirmStr + " 问答题：" + unAnsweredSubjIds.join("、");
		}
		
		if (uncomputingSubjIds.length > 0){
			confirmStr = confirmStr + " 计算题：" + uncomputingSubjIds.join("、");
		}
		
		if (confirmStr == "") {
			confirmStr = "习题已答完，确认提交吗？";
		} else {
			confirmStr = confirmStr + "，没有答完，确认要提交吗？";
		}
	    var answerInfoStr = "";
		for (id in answerInfo) {
			answerInfoStr = answerInfoStr + id + ":" + answerInfo[id] + "_";
		}
		$("#answerInfo").val(JSON.stringify(answerInfo));
		if(isauto=='yes'){
			//如果是时间到自动提交
			submitExamineAsync();
		}else{
			Win.confirm(
					{id:"commitExam",
					title:"确认消息",
					html:"<div class=\"dialog_Inner\">" + confirmStr + "</div>",
					width: 300}, 
					function() {
						submitExamineAsync();
					}
			,function(){
				finishExamining=backfunction;
			}, "确认提交","稍后提交");
		}
	   
  	}
  	
   
  	
  	
  	function contain(array, element) {
		var isContained =false;
		for (var i=0; i<array.length; i++) {
			if (array[i] == element) {
				isContained = true;
				break;
			}
		}
		return isContained;
	}
  	
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
