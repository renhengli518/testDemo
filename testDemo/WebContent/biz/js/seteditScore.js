function validateNum(e){
	e.value=e.value.replace(/\D/g,'');
}

function setScore(){
	var s1 = $("#set1").val();
	var s2 = $("#set2").val();
	var s3 = $("#set3").val();
	var s4 = $("#set4").val();
	var s5 = $("#set5").val();
	var s6 = $("#set6").val();
	
	var sum1 = 0;
	var sum2 = 0;
	var sum3 = 0;
	var sum4 = 0;
	var sum5 = 0;
	var sum6 = 0;
	
	if(s1 == ""){
		//统计单选题分数
		sum1= getScoreCount("type1div");
		//$("#single [name ='score']").val(s1);
	}else{
		sum1=s1*type1;
	}
	if(s2 == ""){
		//统计多选题分数
		sum2= getScoreCount("type2div");
		//$("#multple [name ='score']").val(s2);
	}else{
		sum2=s2*type2;
	}
	if(s3 == ""){
		//统计填空题分数
		sum3= getScoreCount("type3div");
		//$("#fillin [name ='score']").val(s3);
	}else{
		sum3=s3*type3;
	}
	if(s4 == ""){
		//统计解答题分数
		sum4= getScoreCount("type4div");
		//$("#resolve [name ='score']").val(s4);
	}else{
		sum4=s4*type4;
	}
	if(s5 == ""){
		//统计解答题分数
		sum5= getScoreCount("type5div");
		//$("#resolve [name ='score']").val(s4);
	}else{
		sum5=s5*type5;
	}
	if(s6 == ""){
		//统计解答题分数
		sum4= getScoreCount("type6div");
		//$("#resolve [name ='score']").val(s4);
	}else{
		sum6=s6*type6;
	}
	
	if(sum1+sum2+sum3+sum4+sum5+sum6 > $("#scoreInput").val()){
		Win.alert("题目总分已超过");
	}else{
		if(s1 != ""){
			//设置单选题分数
			$("#type1div [name ='score']").val(s1);
		}
		if(s2 != ""){
			//设置多选题分数
			$("#type2div [name ='score']").val(s2);
		}
		if(s3 != ""){
			//设置填空题分数
			$("#type3div [name ='score']").val(s3);
		}
		if(s4 != ""){
			//设置解答题分数
			$("#type4div [name ='score']").val(s4);
		}
		if(s5 != ""){
			//设置解答题分数
			$("#type5div [name ='score']").val(s5);
		}
		if(s6 != ""){
			//设置解答题分数
			$("#type6div [name ='score']").val(s6);
		}
	}
	
	finishchangeScore();
}
               
function getScoreCount(type){
	//获取某一类型已设置的分数
	var sum=0;
	$("#"+type+" [name = 'score']").each(function(index,element){
		sum+=element.value*1;
	});
	return sum;
}

function finishchangeScore(){
	var sum = 0;
	sum = $("#scoreInput").val()*1;
	$("#totalscore").html(sum);
	$("#seted").html(getScoreCount("type1div")+getScoreCount("type2div")+getScoreCount("type3div")+getScoreCount("type4div")+getScoreCount("type5div")+getScoreCount("type6div"));
	$("#notset").html($("#scoreInput").val()-(getScoreCount("type1div")+getScoreCount("type2div")+getScoreCount("type3div")+getScoreCount("type4div")+getScoreCount("type5div")+getScoreCount("type6div")));
}

//dom上移下移
var upAndDown = {
	down: function() {
		var $thisDom = $(this).parents(".text-box");
		var nextDom = $(this).parents(".text-box").next(".text-box")[0];
		if (nextDom) {
			$thisDom.insertAfter(nextDom);
		}
		upAndDown.refresh($thisDom.parent());
	},
	up: function() {
		var $thisDom = $(this).parents(".text-box");
		var prevDom = $(this).parents(".text-box").prev(".text-box")[0];
		if (prevDom) {
			$thisDom.insertBefore(prevDom);
		}
		upAndDown.refresh($thisDom.parent());
	},
	refresh: function($box){
		var $move = $box.find('.move').removeClass('disabled');
		var len = $move.length;
		if (len > 0) {
			$($move[0]).addClass('disabled');
			$($move[len - 1]).addClass('disabled');
		}
	}
};
//upAndDown.refresh();
$(".text-box").on("click", ".move-down", upAndDown.down);
$(".text-box").on("click", ".move-up", upAndDown.up);

function tosetScore(){
	//点击设置分数时的触发事件
		//$("#fixed-target").show();
		$("#examTitle2").html($("#examTitle").val().replace(/\</g,"&lt;").replace(/\>/g,"&gt;"));
		$("#semesterOpts2").html($("#semesterOption").find("option:selected").text());
		$("#classLevelOpts2").html($("#classLevelOption").find("option:selected").text());
		$("#subjOption2").html($("#subjOption").find("option:selected").text());
		$("#examtypeOpts2").html($("#examtypeOpts").find("option:selected").text().replace(/\</g,"&lt;").replace(/\>/g,"&gt;"));
		$("#answerTime2").html($("#answerTime").val());
		
		$(".fixedRight").show();
		
		/*if($("#examtypeOpts").find("option:selected").text() == '请选择'){
			$("#examtypeOpts2show").hide();
		}else{
			$("#examtypeOpts2show").show();
		}*/
		
		updateDivNum();
		
		if($("#scoreInput2").html() != $("#scoreInput").val()){
			//如果不相等则更新总分，并且清空已设置的分数
			$("#scoreInput2").html($("#scoreInput").val());
			$("#notset").html($("#scoreInput").val());
			$("#seted").html("0");
			
			$(".setall").val("");
			$("[name = 'score']").val("");
		}
		
	
		//为每道题绑定校验分数的时间
		$("[name = 'score']").unbind('input propertychange keyup');
		 $("[name = 'score']").on('input propertychange keyup', function (e) {
				 this.value=this.value.replace(/\D/g,'');
				if(getScoreCount("type1div")+getScoreCount("type2div")+getScoreCount("type3div")+getScoreCount("type4div")+getScoreCount("type5div")+getScoreCount("type6div")>$("#scoreInput").val()){
					Win.alert("总分超过设定，请重新输入");
					this.value="";
					return;
				}
				finishchangeScore();
			});
		 finishchangeScore();
}


//更新设置分值页面题目大序号和显示隐藏（父页面）
function updateDivNum(){
//	$(".type1num").html(type1);
//	$(".type2num").html(type2);
//	$(".type3num").html(type3);
//	$(".type4num").html(type4);
//	$(".type5num").html(type5);
//	$(".type6num").html(type6);
	window.parent.$(".type1num").html(window.parent.type1);
	window.parent.$(".type2num").html(window.parent.type2);
	window.parent.$(".type3num").html(window.parent.type3);
	window.parent.$(".type4num").html(window.parent.type4);
	window.parent.$(".type5num").html(window.parent.type5);
	window.parent.$(".type6num").html(window.parent.type6);
	var nownum = 0;
	//控制设置分数区域显示隐藏
	if(window.parent.type1 == 0){
		window.parent.$("#type1no").html(formSeq(nownum));
		window.parent.$("#type1scoreshow").hide();
		window.parent.$("#type1div").hide();
		window.parent.$("#type1target").hide();
	}else{
		nownum +=1;
		window.parent.$("#type1no").html(formSeq(nownum));
		window.parent.$("#type1scoreshow").show();
		window.parent.$("#type1div").show();
		window.parent.$("#type1target").show();
	}
	if(window.parent.type2 == 0){
		window.parent.$("#type2scoreshow").hide();
		window.parent.$("#type2div").hide();
		window.parent.$("#type2target").hide();
	}else{
		nownum +=1;
		window.parent.$("#type2no").html(formSeq(nownum));
		window.parent.$("#type2scoreshow").show();
		window.parent.$("#type2div").show();
		window.parent.$("#type2target").show();
	}if(window.parent.type3 == 0){
		window.parent.$("#type3scoreshow").hide();
		window.parent.$("#type3div").hide();
		window.parent.$("#type3target").hide();
	}else{
		nownum +=1;
		window.parent.$("#type3no").html(formSeq(nownum));
		window.parent.$("#type3scoreshow").show();
		window.parent.$("#type3div").show();
		window.parent.$("#type3target").show();
	}if(window.parent.type4 == 0){
		window.parent.$("#type4scoreshow").hide();
		window.parent.$("#type4div").hide();
		window.parent.$("#type4target").hide();
	}else{
		nownum +=1;
		window.parent.$("#type4no").html(formSeq(nownum));
		window.parent.$("#type4scoreshow").show();
		window.parent.$("#type4div").show();
		window.parent.$("#type4target").show();
	}if(window.parent.type5 == 0){
		window.parent.$("#type5scoreshow").hide();
		window.parent.$("#type5div").hide();
		window.parent.$("#type5target").hide();
	}else{
		nownum +=1;
		window.parent.$("#type5no").html(formSeq(nownum));
		window.parent.$("#type5scoreshow").show();
		window.parent.$("#type5div").show();
		window.parent.$("#type5target").show();
	}if(window.parent.type6 == 0){
		window.parent.$("#type6scoreshow").hide();
		window.parent.$("#type6div").hide();
		window.parent.$("#type6target").hide();
	}else{
		nownum +=1;
		window.parent.$("#type6no").html(formSeq(nownum));
		window.parent.$("#type6scoreshow").show();
		window.parent.$("#type6div").show();
		window.parent.$("#type6target").show();
	}
	
	
	window.parent.initCreateExamQuestionType();
	
}

//更新设置分值页面题目大序号和显示隐藏（本页面）
function updateDivNumSelf(){
//	$(".type1num").html(type1);
//	$(".type2num").html(type2);
//	$(".type3num").html(type3);
//	$(".type4num").html(type4);
//	$(".type5num").html(type5);
//	$(".type6num").html(type6);
	$(".type1num").html(type1);
	$(".type2num").html(type2);
	$(".type3num").html(type3);
	$(".type4num").html(type4);
	$(".type5num").html(type5);
	$(".type6num").html(type6);
	var nownum = 0;
	//控制设置分数区域显示隐藏
	if(type1 == 0){
		$("#type1no").html(formSeq(nownum));
		$("#type1scoreshow").hide();
		$("#type1div").hide();
		$("#type1target").hide();
	}else{
		nownum +=1;
		$("#type1no").html(formSeq(nownum));
		$("#type1scoreshow").show();
		$("#type1div").show();
		$("#type1target").show();
	}
	if(type2 == 0){
		$("#type2scoreshow").hide();
		$("#type2div").hide();
		$("#type2target").hide();
	}else{
		nownum +=1;
		$("#type2no").html(formSeq(nownum));
		$("#type2scoreshow").show();
		$("#type2div").show();
		$("#type2target").show();
	}if(type3 == 0){
		$("#type3scoreshow").hide();
		$("#type3div").hide();
		$("#type3target").hide();
	}else{
		nownum +=1;
		$("#type3no").html(formSeq(nownum));
		$("#type3scoreshow").show();
		$("#type3div").show();
		$("#type3target").show();
	}if(type4 == 0){
		$("#type4scoreshow").hide();
		$("#type4div").hide();
		$("#type4target").hide();
	}else{
		nownum +=1;
		$("#type4no").html(formSeq(nownum));
		$("#type4scoreshow").show();
		$("#type4div").show();
		$("#type4target").show();
	}if(type5 == 0){
		$("#type5scoreshow").hide();
		$("#type5div").hide();
		$("#type5target").hide();
	}else{
		nownum +=1;
		$("#type5no").html(formSeq(nownum));
		$("#type5scoreshow").show();
		$("#type5div").show();
		$("#type5target").show();
	}if(type6 == 0){
		$("#type6scoreshow").hide();
		$("#type6div").hide();
		$("#type6target").hide();
	}else{
		nownum +=1;
		$("#type6no").html(formSeq(nownum));
		$("#type6scoreshow").show();
		$("#type6div").show();
		$("#type6target").show();
	}
	
	
	initCreateExamQuestionType();
	
}


function formSeq(seqIndex){
	 var x="一";
	 switch (seqIndex)
	 {
  	 case 1:
  	   x="一";
  	   break;
  	 case 2:
  	   x="二";
  	   break;
  	 case 3:
  	   x="三";
  	   break;
  	 case 4:
  	   x="四";
  	   break;
  	 case 5:
  	   x="五";
  	   break;
  	 case 6:
  	   x="六";
  	   break;
  	 case 7:
  	   x="七";
  	   break;
  	 case 8:
  	   x="八";
  	   break;
  	 case 9:
	   x="九";
	   break;
  	 case 10:
	   x="十";
	   break;
	 }
	 return x;
}