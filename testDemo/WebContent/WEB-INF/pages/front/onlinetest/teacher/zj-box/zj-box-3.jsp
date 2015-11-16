<%@ page contentType="text/html; charset=UTF-8"%>
<!-- 智能选题 --> 
    <div class="clearfix floatWrap withBorderTop select-exercise-box"  style="border-top-color:#d5f2df;display:none;" id="box3">
                <div class="mainLeft">
                  <div class="leftContent bkgWhite">
                  
                     <div class="leftSection1 borderBox gn-overflowHidden pd0 " id="box3">
                        <%@ include file="qtab.jsp"%>
                    </div>
                  </div>
                </div>
                <div class="mainRight1 bkgWhite">
                   <div class="borderBox pd20" id="step1">
                   	<form id="znzjform">
                      <ul id="box3ul" class="commonUL gn-searchCondition gn-coffeeSort smartUL mt20">
				    	 <li>
				    		 <label class="text gn-labelText  gn-coffeeBracket"><b>选择试题类型</b></label>
				    		 <span class="cont gn-sortBox clearfix">
				    		 	<span class="gn-sortKinds gn-overflowHidden">
				    		 	    <label class="mr20"><input type="checkbox" name="quesKind" qid="q1"  checked >&nbsp;&nbsp;单选题</label>
				    		 	    <label class="mr20"><input type="checkbox" name="quesKind" qid="q2"  checked >&nbsp;&nbsp;多选题</label>
				    		 	    <label class="mr20"><input type="checkbox" name="quesKind" qid="q3"  checked >&nbsp;&nbsp;判断题</label>
				    		 	    <label class="mr20"><input type="checkbox" name="quesKind" qid="q4" >&nbsp;&nbsp;填空题</label>
				    		 	    <label class="mr20"><input type="checkbox" name="quesKind" qid="q5" >&nbsp;&nbsp;问答题</label>
				    		 	    <label class="mr20"><input type="checkbox" name="quesKind" qid="q6" >&nbsp;&nbsp;计算题</label>
				    		 	</span>
				    		 </span>
				    	 </li>
				    	 <li>
				    		 <label class=" gn-labelText  gn-coffeeBracket"><b>选择题型数量：</b></label>
				    		 <div class="setQuesNum ml10">
				    		   <p id="q1">
				    		      单选题：
				    		    <label class="mr30 ">
				    		                 容易
				    		   	   <select name="qlist" class="qtype">
				    		   	   </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较易
				    		   	   <select name="qlist" class="qtype">
				    		   	   </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 一般
				    		   	   <select name="qlist" class="qtype">
				    		   	   </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较难
				    		   	   <select name="qlist" class="qtype">
				    		   	   </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 困难
				    		   	   <select name="qlist" class="qtype">
				    		   	   </select>
				    		    </label>
				    		   </p>
				    		    <p id="q2">
				    		      多选题：
				    		    <label class="mr30">
				    		                 容易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 一般
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 困难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		   </p>
				    		    <p id="q3">
				    		      判断题：
				    		    <label class="mr30">
				    		                 容易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 一般
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 困难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		   </p>
				    		   
				    		   <p id="q4" style="display:none;">
				    		      填空题：
				    		    <label class="mr30">
				    		                 容易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 一般
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 困难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		   </p>
				    		   
				    		    <p id="q5" style="display:none;">
				    		      问答题：
				    		    <label class="mr30">
				    		                 容易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 一般
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 困难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		   </p>
				    		   
				    		    <p id="q6" style="display:none;">
				    		      计算题：
				    		    <label class="mr30">
				    		                 容易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较易
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 一般
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 较难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		    <label class="mr30">
				    		                 困难
				    		   	  <select name="qlist" class="qtype">
				    		   	  </select>
				    		    </label>
				    		   </p>
				    		 </div>
				    	 </li>
				    	 <li>
				    		 <label class="text gn-labelText  gn-coffeeBracket"><b>选题范围：</b></label>
				    		 <span class="cont  gn-containInput">
				    		    <select class="newSel versionsel" onchange="selectChapter()" name="version" id="selectversion">
				                   <option value="0">-- 请选择 --</option>
				                   <c:forEach items="${versions}" var="versions">
										<option value ="${versions.id }">${versions.name }</option>
							  		</c:forEach>
				                </select>
				    		 </span>
				    	 </li>
				     </ul>
                  	</form>
				     
				     <ul>
				     	<li>
				     		 <p class="center mt40 mb20">
				    		      <button class="btn bigBtn bgCoffee" onclick="getznxt()">确定</button>
				    		   </p>
				     	</li>
				     </ul>
				     
                   </div>
                   
                   <div class="borderBox pd20" id="step2" style="display:none;">
	                   	<p class="quesNum clearfix">共计<span id="totalCnt3">0</span>题（提示：点击题目内容即可查看到内容和解析内容） <span class="fr">
						</p>
                   		<div class="mainRight1 borderBox testCon testConLittleMargin" id="choice3">
                 		</div>
                 		<div id="pageNaviZn"></div>
                   		<ul>
					     	<li>
					     		 <p class="center mt40 mb20">
					    		      <button class="btn bigBtn bgCoffee" onclick="backzn()">返回上一步</button>
					    		   </p>
					     	</li>
				     	</ul>
                   </div>
                   
                </div>
              </div>
              </div>
<script type="text/javascript">
	var pageSize = 10; 
	var pageNo = 0;
	var currPage = 1;

	var options = "<option value='0' selected>0</option>";
	options += "<option value='1'>1</option>";
	options += "<option value='2'>2</option>";
	options += "<option value='3'>3</option>";
	options += "<option value='4'>4</option>";
	options += "<option value='5'>5</option>";
	options += "<option value='6'>6</option>";
	options += "<option value='7'>7</option>";
	options += "<option value='8'>8</option>";
	options += "<option value='9'>9</option>";
	options += "<option value='10'>10</option>";
	options += "<option value='20'>20</option>";
	options += "<option value='30'>30</option>";
	options += "<option value='40'>40</option>";
	options += "<option value='50' >50</option>";

	function bindChapterByVersion() {
		console.log("初始智能选题数据");
		console.log(semesterId + "***" + subjectId);
	}

	$(document).ready(function() {
		$(".qtype").append(options);
	});

	$(document).on('click', "input[name='quesKind']", function(event) {
		var $elm = $(this);
		if ($elm.prop("checked")) {
			$("#" + $elm.attr("qid")).show();
		} else {
			$("#" + $elm.attr("qid") + " .qtype").empty();
			$("#" + $elm.attr("qid") + " .qtype").append(options);
			$("#" + $elm.attr("qid")).hide();
		}
	})

	//根据版本加载章节
	function selectChapter() {
		var versionId = $(".versionsel").val();
		console.log(semesterId + "***" + subjectId);

		$(".cli").remove();
		$.post("../commons/getAllVCByClslevelSubjectVerionId.do?subjectId="+ subjectId + "&versionId=" + versionId,
						function(data) {
							for (var i = 0; i < data.length; i++) {
								var html = '<li class="cli"><div class="clearfix"><div class="borderBox pd20 mt20"><h4 class="marginTitle"><input class="checkv" value="'+data[i].id+'" type="checkbox">';
								html += data[i].name
								html += '</h4><div class="LH3 c000 mt10">';

								for (var j = 0; j < data[i].list.length; j++) {
									html += '<label class="mr20">';
									html += '<input type="checkbox" name="chapters" vid="'+data[i].id+'"   value="'+data[i].list[j].id+'">'
											+ data[i].list[j].name;
									html += '</label>';
								}
								html += '</div></div></li>';

								$("#box3ul").append(html);
							}
							//			$("#subjOption").html(html);  
							//		console.log(data);
						}, 'json');

	}

	$(document).on('click', ".checkv", function(event) {
		var $elm = $(this);
		if ($elm.prop("checked")) {
			$("input[vid='" + $elm.val() + "']").prop("checked", true);
		} else {
			$("input[vid='" + $elm.val() + "']").prop("checked", false);
		}
	})

	$(document).on('click',"input[name='chapters']",function(event) {
					var $elm = $(this);
						if ($elm.prop("checked")) {
							/* var len = $("input[vid='" + $elm.attr("vid") + "']")
									.not(":checked").length;
							if (len == 0) { */
								//如果没有没选中的
								$("input[value='" + $elm.attr("vid") + "']").prop("checked", true);
							/* } */
						} else {
							var len = $("input[vid='" + $elm.attr("vid")
									+ "']:checked").length;
							if (len == 0) {
								//如果没有选中的
								$("input[value='" + $elm.attr("vid") + "']").prop("checked", false);
							}
						}
				})
					
	//获取智能选题并加载到页面				
	function getznxt(){
		if($(".qtype :selected").not("[value=0]").length == 0){
			//如果没有选题目
			Win.alert("请您编辑题型数量！");
			return;
		}
		
		if($("#selectversion").val() == 0){
			Win.alert("前选择选题范围！");
			return;
		}
		
		if($("input[name='chapters']:checked").length == 0){
			Win.alert("请勾选章数！");
			return;
		}
		
		var param = $("#znzjform").serialize();
		
		/* $.get(url , function(result){
			formZnList(result,result.length);
			createPageNumbers(pageNo, currPage, "pageNaviZn", goToPage);
		}, "json"); */
		
		$.post("searchIntelligenceList.do?subjectId="+ subjectId + "&semesterId=" + semesterId+"&"+param,function(result) {
					//			$("#subjOption").html(html);  
							$("#step1").hide();
							$("#step2").show();
							formZnList(result,result.length);
							createPageNumbers(pageNo, currPage, "pageNaviZn", goToPage);
				}, 'json');
	}
	
	
	function backzn(){
		$("#step1").show();
		$("#step2").hide();
	}
	
	
	function goToPage(page) {
		currPage = page;
		$("div.quesPage").each(function(){
			if($(this).attr("pageNo") == page) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
		window.scrollTo(0,0);
		createPageNumbers(pageNo, currPage, "pageNaviZn", goToPage);
	}

	function formZnList(data,totalCntZn){
		console.log("智能生成试卷");
		pageNo = 0;
		currPage = 1;
		$("#totalCnt3").html(totalCntZn);
		var qstContentZn = "";
		var len = data.length;
		if(len>0){
			for(var i = 0; i< len; i++){
				var qstHtml = '';
				var qstObj = data[i];
				
				if(i % pageSize == 0){
					pageNo += 1;
					qstHtml += '<div class="quesPage"';
					if(pageNo > 1){
						qstHtml += 'style="display:none"';
					}
					qstHtml += " pageNo="+pageNo+">";
				}
				
				qstHtml += '<div class="quesDetail ">';
				qstHtml += '<p class="quesDesc">';
				qstHtml += '	<label>组卷次数：</label><span class="red">'+isEmpty(qstObj.useCount)+'</span>';
				qstHtml += '	<label>知识点：</label><span class="c888">'+isEmpty(qstObj.endKonwledgeNames)+'</span>';
				qstHtml += '	<label>难易度：</label><span class="red">'+switchDifficulty(qstObj.difficulty)+'</span>';
				qstHtml += '	<label>更新日期：</label><span>'+now('y-m-d h:i:s',qstObj.updateTime)+'</span>';
				qstHtml += '</p>';
				qstHtml +='<div class="quesBro quesKinds">';
				qstHtml +='		<a href="javascript:;" class="active" onclick="switchQues(this)" id="'+qstObj.queQuestionId+'">母题</a>';
				var childrenQuestionList = qstObj.childrenQuestionList;
				if(childrenQuestionList.length>0){
					var m=1, n=1; 
					for(var j=0;j<childrenQuestionList.length;j++){
						if(childrenQuestionList[j].relationalIndicator=="TWINS"){
							qstHtml +='	<a href="javascript:;" onclick="switchQues(this)" id="'+childrenQuestionList[j].queQuestionId+'">孪生题'+m+'</a>';
							m++;
						}
						if(childrenQuestionList[j].relationalIndicator=="EXTEND"){
							qstHtml +='	<a href="javascript:;" onclick="switchQues(this)" id="'+childrenQuestionList[j].queQuestionId+'">衍生题'+n+'</a>';
							n++;
						}
					}
				}
				qstHtml +='</div>';
				qstHtml += '<div class="quesWrap show question" >';
				qstHtml += '<p style="cursor:pointer;">'+isEmpty(qstObj.content);
				qstHtml += getBtn(qstObj.queQuestionId,qstObj.queQuestionId,qstObj.type);
				qstHtml += '</p>';
				var type = qstObj.type;
				if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE' || type == 'JUDEMENT') {//单选和多选题目
					var options = qstObj.options;
					if(options){
						options = qstObj.options.split(optSplitChar);
					    for (var j = 0; j<options.length;j++){
					    	qstHtml += '<p>'+options[j]+'</p>';
					    } 
					} 
				} 
				qstHtml += '<div class="Analytical" style="display:none"><div class="pd10 commentContent show"><b>【答案】</b>';
				if(type == 'SINGLE_CHOICE' || type == 'MULTI_CHOICE') {//单选和多选题目
					qstHtml +=isEmpty(qstObj.answer);
				}else if(type == 'FILL_IN_BLANK'){//填空题
				    var answerType = qstObj.fillInAnswerType;
				    var typeHtml = "<p>" +(answerType == 'INDEPENDENT' ? "独立答案" : "组合答案") +"</p>";
				    var scoreType = qstObj.fillInScoreType;
				    var scoreHtml =  "<p>" +(scoreType == 'ALL_CORRECT' ? "全对给分" : "按空给分") +"</p>";
				    var answerList = qstObj.fillInAnswers;
			    	var headHtml = "";
				    if(answerList.length > 0){
				    	headHtml += "<table class=\"anyTable\" collapse>";
				    	headHtml += "<thead><tr>";
				    	headHtml += "<th><div class=\"specialTH\" >";
				    	headHtml += "<span class=\"rightTop\">答案容错</span>";
				    	headHtml += "<span class=\"leftBottom\">填空</span>";
				    	headHtml += "</div></th>";
                       if(answerType == 'INDEPENDENT'){//独立答案
                    	    headHtml += "<th>答案1</th>";
	   						headHtml += "<th>答案2</th>";
	   						headHtml += "<th>答案3</th>";
	   						headHtml += "<th>答案4</th>";
				    		
				    	} else if(answerType == 'COMBINATION'){//组合答案
				    	    headHtml += "<th>第一组</th>";
	   						headHtml += "<th>第二组</th>";
	   						headHtml += "<th>第三组</th>";
	   						headHtml += "<th>第四组</th>";
				    	}				    	
						headHtml += "</tr></thead>";
						for(var k = 0 ; k< answerList.length; k++){
							var answerObj = answerList[k];
							headHtml += "<tr>";
							headHtml += "<td>第"+formSeq((k+1))+"空</td>";
							headHtml += "<td><div class=\"for-answer\"'>";
							headHtml +=  isEmpty(answerObj.answerGrp1);
							headHtml += "</div></td>";
							headHtml += "<td><div class=\"for-answer\"'>";
							headHtml += isEmpty(answerObj.answerGrp2);
							headHtml += "</div></td>";
							headHtml += "<td><div class=\"for-answer\"'>";
							headHtml +=  isEmpty(answerObj.answerGrp3);
							headHtml += "</div></td>";
							headHtml += "<td><div class=\"for-answer\"'>";
							headHtml +=  isEmpty(answerObj.answerGrp4);
							headHtml += "</div></td>";
							headHtml += "</tr>";
						}
				    	headHtml += "</table>";
				    }
				    if(answerList.length > 1){
				    	qstHtml += typeHtml + headHtml + scoreHtml;
				    } else {
				    	qstHtml += headHtml;
				    }				    
				}else{//其他题型
					qstHtml +=isEmpty(qstObj.answer);
				}	
				qstHtml += '<br/><b>【解析】</b>';
				qstHtml +=isEmpty(qstObj.resolve);
				if(qstObj.resolveVideo && qstObj.resolveVideo != '' &&  qstObj.resolveVideo != null){
					qstHtml += '<p><a href="javascript:;" class="playVideoP" videoUrl="../ImageServlet/'+qstObj.resolveVideo+'" ><button class="btn playVideo">点击解析视频</button></a></p>';
				}
				qstHtml += '</div>';
				qstHtml += '</div>'; 
				qstHtml += '</div>';
				//qstHtml += '</div>';
				qstHtml +='</div>';
				if((i+1) % pageSize == 0 || (i+1) == totalCntZn){
					qstHtml += "</div>  " ;
				}
				
				qstContentZn += qstHtml;
			}					
		}else{
			qstContentZn = '<div id="quesContainer" style="padding-top:25px;" class="quesContainer" align="center">'+noRecord+'</div>';
		}
		$("#choice3").empty();
		$("#choice3").html(qstContentZn);		
	}
</script>