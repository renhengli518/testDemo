<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<script type="text/javascript" src="${root}/biz/js/editExam.js"></script>
<script type="text/javascript" src="${root}/biz/js/seteditScore.js"></script>
<script type="text/javascript" src="${root}/biz/js/teaeditExam.js"></script>
<script type="text/javascript">
	//存放所有已选择的问题的数组(编辑页面统一放在父页面中)
	var qlist = {};//题目列表
	var mlist = {};//题目对应的母题列表
	var type1 = 0;//单选
	var type2 = 0;//多选
	var type3 = 0;//判断
	var type4 = 0;//填空
	var type5 = 0;//问答
	var type6 = 0;//计算
	
  	

</script>
</head>
<body class="mainIndex">
     <%@include file="../../../common/topHeader.jsp"%>
     <c:choose>
		<c:when test="${menuName eq 'schoolTest'}">
		    <%@include file="../../../common/queNav.jsp"%>
		</c:when>
		<c:otherwise>
		    <%@include file="../../../common/examNav.jsp"%>
		</c:otherwise>
	 </c:choose>
     <%@include file="../../../common/fixedRight.jsp"%>
     
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
              	<c:if test="${tabType=='exam'}">
              		<c:if test="${sessionScope.SESSION_USER.userType  eq 'SCHOOL_USR'}">
	                	<a href="javascript:;">年级统考</a>
              		</c:if>
              		<c:if test="${sessionScope.SESSION_USER.userType  eq 'TEACHER'}">
	                	<a href="javascript:;">我的试卷</a>
              		</c:if>
                </c:if>
                <c:if test="${tabType=='real'}">
                	<a href="javascript:;">真题试卷</a>
               </c:if>
				<c:choose>
					<c:when test="${menuName eq 'schoolTest'}">
						<span class="nextlevel">></span><span class="currentLevel">试卷详情 </span>
					</c:when>
					<c:otherwise>
						<span class="nextlevel">></span><span class="currentLevel">编辑试卷 </span>
					</c:otherwise>
				</c:choose>
				
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite pd20">
	              <ul class="commonUL">
                  <form id="examForm">
	                <li>
	                	<input type="hidden" name="examId" value="${examView.examId}">
	                	<input type="hidden" name="realExamId" value="${examView.realExamId}">
	                	<input type="hidden" name="baseSemesterId" value="${examView.baseSemesterId}">
	                	<input type="hidden" name="baseClasslevelId" value="${examView.baseClasslevelId}">
	                	<input type="hidden" name="baseSubjectId" value="${examView.baseSubjectId}">
	                    <span class=" verticalMiddle"><b class="red">*</b>试卷名称：</span><input id="examTitle"  name="examTitle" type="text" class="bigInput" value="${examView.title}"><span class="c666 ml10">您最多可输入30个字符</span>
	                </li>
	                <li class="mt10">
	                    <span class="mr50">
					    <label>学段：</label><span class="c888">${examView.semesterName}</span>
                        <label>年级：</label><span class="c888">${examView.classlevelName}</span>
                        <label>学科：</label><span class="c888">${examView.subjectName}</span>
		                <b class="red">*</b>试卷类型：
		                <select class="newSel" id="examtypeOpts" name="examtype">
							<option value="0">-- 请选择 --</option>
					          <c:forEach items="${examTypes}" var="examType">
					          <c:if test="${examView.examTypeId eq examType.id}">
					          	<option value="${examType.id }" selected>${examType.name}</option>
					          </c:if>
					          <c:if test="${examView.examTypeId ne examType.id}">
					          	<option value="${examType.id }">${examType.name}</option>
					          </c:if>
							  </c:forEach> 
							  
		                </select>
	                </li>
	                <li class="mt10">
	                    <b class="red">*</b>答题时长：
	                    <span class="fixedWidthSpan mr50">
	                       <input value="${examView.answerTime}" type="text" placeholder="请输入答卷时长" class="fixedWidthInput" id="answerTime"  name="answerTime">分钟
	                       <span class="c666 ml10">最多输入3位数字</span>
	                    </span>
	                          
		                <b class="red">*</b>试卷总分：
		                <span class="fixedWidthSpan">
		                   <input value="${examView.score}" type="text" placeholder="请输入答卷时长" class="fixedWidthInput" id="scoreInput"  name ="scoreInput">
		                   <span class="c666 ml10">最多输入3位数字</span>
		                 </span>
	                </li>
	              </form>
	                <li class="mt10">
	                                                 批量设每题分值：  <br>
					<span id="type1scoreshow" class="mr10">单选题(<span class="type1num" style="margin-right:0px;">0</span>题)：<input id="set1" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
					<span id="type2scoreshow" class="mr10">多选题(<span class="type2num" style="margin-right:0px;">0</span>题)：<input id="set2" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
					<span id="type3scoreshow" class="mr10">判断题(<span class="type3num" style="margin-right:0px;">0</span>题)：<input id="set3" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
					<span id="type4scoreshow" class="mr10">填空题(<span class="type4num" style="margin-right:0px;">0</span>题)：<input id="set4" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
					<span id="type5scoreshow" class="mr10">问答题(<span class="type5num" style="margin-right:0px;">0</span>题)：<input id="set5" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
					<span id="type6scoreshow" class="mr10">计算题(<span class="type6num" style="margin-right:0px;">0</span>题)：<input id="set6" class="setall littleInput" type="text"  onkeyup="return validateNum(this);" /> 分</span>
	                <button class="btn btnOrange fr mr30" onclick="setScore();">设定</button>                            
	                </li>
	              </ul>
              </div>
              
              <div class="borderBox gn-bgWhite bigPd mt17">
              	 <ul class="commonUl descCommon clearfix">
              		<li>
                      <label>试卷总分：</label><span class="c888"><span id="totalscore">${examView.score}</span>分</span>
                    </li>
                    <li>
                      <label>已设置：</label><span class="c888"><span id="seted">${examView.score}分</span></span>
                    </li>
                    <li style="width: 50%">
                      <label>未设置：</label><span class="c888"><span id="notset">0</span>分</span>
	                    	<button class="btn btnOrange fr mr30" onclick="displayDel(this);">隐藏已删除试题</button>  
	                    	<button class="btn btnOrange fr mr30" onclick="selectQuestions();">加入试题</button>
                    </li>
              	</ul>
			  </div>	
			  
              <div class="borderBox gn-bgWhite bigPd mt17" id="queBody">
			  <form id="queForm">              
                 <c:set var="title1" value="0"></c:set>
				 <c:set var="title2" value="0"></c:set>
				 <c:set var="title3" value="0"></c:set>
				 <c:set var="title4" value="0"></c:set>
				 <c:set var="title5" value="0"></c:set>
				 <c:set var="title6" value="0"></c:set>
				 <c:set var="questionNo" value="1"></c:set>
                 
                 <c:forEach var="q" items="${examQuestions}" varStatus="status">
                   <c:if test="${q.type == 'SINGLE_CHOICE'}">
					    <script type="text/javascript">
					    	$(".type1num").html("${q.num}");
							type1+=1;
							qlist['${q.queQuestionId }'] = '${q.type}';
							mlist['${q.motherId != null?q.motherId:q.queQuestionId}'] = '${q.queQuestionId }';
					    </script>
                   	 <c:if test="${title1 == 0}">
                     <div class="testCon" id="type1div">
                        <c:set var="title1" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle"><span id="type1no">一</span>、单选题  <span class="quesNum">（<span class="type1num" style="margin-right:0px;">${q.num}</span>题）</span></h3>
                   	  </c:if>
                   </c:if>
                   
                   <c:if test="${q.type == 'MULTI_CHOICE'}">
                   		<script type="text/javascript">
					    	$(".type2num").html("${q.num}");
					    	type2+=1;
					    	qlist['${q.queQuestionId }'] = '${q.type}';
							mlist['${q.motherId != null?q.motherId:q.queQuestionId}'] = '${q.queQuestionId }';
					    </script>
					   <c:if test="${title2 == 0}">
					   	 <c:if test="${title1 != 0 }"></div></c:if>
	                     <div class="testCon" id="type2div">
	                        <c:set var="title2" value="1"></c:set>
						    <c:set var="questionNo" value="1"></c:set>
	                        <h3 class="testTitle"> 
	                          <span id="type2no">
	                           <c:if test="${title1 == 0 }">一</c:if>
							   <c:if test="${title1 == 1 }">二</c:if>
							  </span>
							   、多选题<span class="quesNum">（<span class="type2num" style="margin-right:0px;">${q.num}</span>题）</span></h3>
                   		</c:if>
                   </c:if>
                   
                   
                    <c:if test="${q.type == 'JUDEMENT'}">
                	   <script type="text/javascript">
					    	$(".type3num").html("${q.num}");
					    	type3+=1;
					    	qlist['${q.queQuestionId }'] = '${q.type}';
							mlist['${q.motherId != null?q.motherId:q.queQuestionId}'] = '${q.queQuestionId }';
					    </script>
					<c:if test="${title3 == 0}">
					 
					  <c:if test="${(title1 + title2) != 0 }"></div></c:if>
                     <div class="testCon" id="type3div">
                        <c:set var="title3" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">
                        	<span id="type3no">
	                            <c:if test="${(title1 + title2) == 0 }">一</c:if>
								<c:if test="${(title1 + title2) == 1 }">二</c:if>
								<c:if test="${(title1 + title2) == 2 }">三</c:if>
							</span>
						 、判断题
                         <span class="quesNum">（<span class="type3num" style="margin-right:0px;">${q.num}</span>题）</span></h3>
                   	</c:if>
                   </c:if>
                   
                   
                   <c:if test="${q.type == 'FILL_IN_BLANK'}">
                   		<script type="text/javascript">
					    	$(".type4num").html("${q.num}");
					    	type4+=1;
					    	qlist['${q.queQuestionId }'] = '${q.type}';
							mlist['${q.motherId != null?q.motherId:q.queQuestionId}'] = '${q.queQuestionId }';
					    </script>
					    <c:if test="${title4 == 0}">
					    <c:if test="${(title1 + title2 + title3) != 0 }"></div></c:if>
	                     <div class="testCon" id="type4div">
	                        <c:set var="title3" value="1"></c:set>
						    <c:set var="questionNo" value="1"></c:set>
	                        <h3 class="testTitle">
	                        	<span id="type4no">
								   <c:if test="${(title1 + title2 + title3) == 0 }">一</c:if>
								   <c:if test="${(title1 + title2 + title3) == 1 }">二</c:if>
								   <c:if test="${(title1 + title2 + title3) == 2 }">三</c:if>
								   <c:if test="${(title1 + title2 + title3) == 3 }">四</c:if>
								</span>
							 、填空题
	                         <span class="quesNum">（<span class="type4num" style="margin-right:0px;">${q.num}</span>题）</span></h3>
                   		</c:if>
                   </c:if>
                   
                  
                   
                    <c:if test="${q.type == 'ASK_ANSWER'}">
                     <script type="text/javascript">
					    	$(".type5num").html("${q.num}");
					    	type5+=1;
					    	qlist['${q.queQuestionId }'] = '${q.type}';
							mlist['${q.motherId != null?q.motherId:q.queQuestionId}'] = '${q.queQuestionId }';
					 </script>
					 <c:if test="${title5 == 0}">
					 <c:if test="${(title1 + title2 + title3 + title4) != 0 }"></div></c:if>
                     <div class="testCon" id="type5div">
                        <c:set var="title5" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">
                        	<span id="type5no">
	                          <c:if test="${(title1 + title2 + title3 + title4) == 0 }">一</c:if>
							  <c:if test="${(title1 + title2 + title3 + title4) == 1 }">二</c:if>
							  <c:if test="${(title1 + title2 + title3 + title4) == 2 }">三</c:if>
							  <c:if test="${(title1 + title2 + title3 + title4) == 3 }">四</c:if>
							  <c:if test="${(title1 + title2 + title3 + title4) == 4 }">五</c:if>
						  	</span>
						 、问答题
                        <span class="quesNum">（<span class="type5num" style="margin-right:0px;">${q.num}</span>题）</span></h3>
                   	</c:if>
                   </c:if>
                   
                    <c:if test="${q.type == 'COMPUTING'}">
                    <script type="text/javascript">
					    	$(".type6num").html("${q.num}");
					    	type6+=1;
							qlist['${q.queQuestionId }'] = '${q.type}';
							mlist['${q.motherId != null?q.motherId:q.queQuestionId}'] = '${q.queQuestionId }';
					</script>
					<c:if test="${title6 == 0}">
					<c:if test="${(title1 + title2 + title3 + title4 + title5) != 0 }"></div></c:if>
                    <div class="testCon" id="type6div">
			          <c:set var="title6" value="1"></c:set>
					  <c:set var="questionNo" value="1"></c:set>	
					  <h3 class="testTitle">
					  	<span id="type6no">
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 0 }">一</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 1 }">二</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 2 }">三</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 3 }">四</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 4 }">五</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 5 }">六</c:if>
						</span>
						、计算题
						<span class="quesNum">（<span class="type6num" style="margin-right:0px;">${q.num}</span>题）</span></h3>
				     </c:if>
					</c:if>
                 
                    <div class="quesDetail  ${q.queQuestionId}_div">
                      <input type="hidden" value="${q.queQuestionId}" name="questionIds">
                      <p class="quesDesc">
	                  <label>组卷次数：</label><span class="red">${q.examTimes}</span>
	                  <label>知识点：</label><span class="c888">${q.knowledgeEndNames}</span>
	                  <label>难易度：</label>
	                  <span class="red">
					     <c:if test="${q.difficulty == 'EASY'}">
								      容易
					     </c:if>
					     <c:if test="${q.difficulty == 'LITTLE_EASY'}">
								      较易
						 </c:if>
						 <c:if test="${q.difficulty == 'NORMAL'}">
								      一般
						 </c:if>
						 <c:if test="${q.difficulty == 'LITTLE_DIFFICULT'}">
								       较难
						 </c:if>
						 <c:if test="${q.difficulty == 'DIFFICULT'}">
								      困难
						 </c:if>
					  </span>
	                 
	                    <label>更新时间：</label><span class="c888"><fmt:formatDate value="${q.updateTime}" pattern="yyyy-MM-dd HH:mm"/> </span>
	                    
	                    <span class="fr mr0">
	                    <label>分值：</label><span class="c888 spanDate"><input type="text" class="littleInput" name="score" value="${q.score}">分</span>
	                    <button class='move move-up btn removeUp mr10' onclick="return false">上移</button>
						<button class='move move-up btn removeDown mr10' onclick="return false">下移</button>
						<button class="fr  btn removeFromPaper removeQuestion ${q.queQuestionId}_btn" qid ="${q.queQuestionId}" mid ="${q.motherId != null?q.motherId:q.queQuestionId}" qtype="${q.type}" onclick="return false">移出试卷</button></p>
	                    </span>
                        
                          <div class="text" style="cursor:pointer">
	                        <div class="question">${status.count}.${q.content}（<input type="text" class="testQues choiceInput" />）</div>
	                      </div>
	                      <c:if test="${q.contentVideo !=null && q.contentVideo != ''}">
								<p>
									<a href="javascript:;" class="playVideoP" >
									<button class="btn playVideo" id="${q.contentVideo}" onclick="playVideo(this)" >点击播放音视频</button>
									<span id="watch_play0s"></span>
									</a>
								</p>
							</c:if>
	                        <c:if test="${q.type == 'SINGLE_CHOICE' ||  q.type == 'MULTI_CHOICE' || q.type == 'JUDEMENT'}">
	                           <ul class="select">
					             <div class="options">${q.options}</div>
					           </ul>
					        </c:if>
	                         
	                      <p class="answer" style="display:none"><b>【答案】</b><br/>
							 <span style="margin-left:20px;">${q.answer}</span>
						  </p>
						  <ul class="answer-analyze" style="display:none">
							 <c:if test="${q.type == 'FILL_IN_BLANK' }">
							   <c:if test="${q.fillInAnswers.size()>1 }">
								 <li>
									<label>给分类型：</label><b>${q.fillInAnswerType eq '1' ? '独立答案':'组合答案'}</b>
								    <label>答案类型：</label><b>${q.fillInScoreType eq '1' ? '全对给分':'按空给分'}</b>
							     </li>
							   </c:if>
							 </c:if>
							 <c:if test="${q.type == 'FILL_IN_BLANK'  }">
							   <li>
								 <label>正确答案：</label>
								 <c:if test = "${not empty q.fillInAnswers}">
								 <div class="cCont multi-choice-answer">
								 <table class="anyTable">
								  <thead>
								   <tr>
									  <th class="special">
									   <div class="specialTH">
									    <span class="leftBottom">填空</span>
                                        <span class="rightTop">答案容错</span>
                                        
                                       </div>
									 
									</th>
									<c:if test="${q.fillInAnswerType eq '1' }">
									  <th>答案1</th>
									  <th>答案2</th>
									  <th>答案3</th>
									  <th>答案4</th>
									</c:if>
								    <c:if test="${q.fillInAnswerType eq '2' }">
									  <th>第一组</th>
									  <th>第二组</th>
									  <th>第三组</th>
									  <th>第四组</th>
									</c:if>
								  </tr>
								  </thead>
								  <tbody>
							     <c:forEach var="fillInAnswer" items="${q.fillInAnswers}" varStatus="fs">
								    <tr>
								     <td>第${fs.index +1 }空</td>
								     <td class="edit-pop-btn"><div class="for-answer">${fillInAnswer.answerGrp1}</div></td>
								     <td class="edit-pop-btn"><div class="for-answer">${fillInAnswer.answerGrp2}</div></td>
								     <td class="edit-pop-btn"><div class="for-answer">${fillInAnswer.answerGrp3}</div></td>
								     <td class="edit-pop-btn"><div class="for-answer">${fillInAnswer.answerGrp4}</div></td>
								   </tr>
								</c:forEach>
								</tbody>
							  </table>
						    </div>
						   </c:if>
						  </li>
					     </c:if>
				         <b>【解析】</b><br/>
				                        解：${q.resolve}
						  <br/>
						  <c:if test="${q.resolveVideo != null }">
						    <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" id="${q.resolveVideo}" onclick="playVideo(this)" >点击解析视频</a>
						  </c:if>
					    </ul>
                      </div>
                    
                    </c:forEach>
                    
                    </form>
                    
                    </div>
                  	<input id="bzids" type="hidden">
                     <p class="center mt40">
                		<button id="editexam" class="btn btnSearch nextStep" onclick="editexam()">保存</button>
                		<button id="editbzexam" class="btn btnSearch nextStep" onclick="editbzexam()">保存并布置</button>
              		 </p>
                </div>
              </div>
			  
          </div>
                 
    <script type="text/javascript">
    	function editexam(){
    		
    		var examTitle = $("#examTitle").val().replace(/(^\s*)|(\s*$)/g, "");
    		if (examTitle == '') {
    			Win.alert("请输入试卷名！");
    			return;
    		}
    		
    		if(examTitle.length > 30){
    			Win.alert("试卷名不得超过30个字符！");
    			return;
    		}
    		
    		
    		var sum = 0;
    		sum = $("#scoreInput").val()*1;
    		if($("#seted").html()*1 > sum){
    			Win.alert("总分不得小于已设分值，请重新输入");
    			return;
    		}
    		
    		
    		if($("#examtypeOpts").val() == 0){
    			Win.alert("请选择试卷类型！");
    			return;
    		}
    			
    		
    		var answerTime = $("#answerTime").val();
    		if (answerTime == '' ) {
    			Win.alert("请输入答题时间！");
    			return;
    		}else if  (answerTime  == '0') {
    			Win.alert("答题时间不能为0！");
    			return;
    		}else if  (answerTime  > 999) {
    			Win.alert("答题时间不能大于999！");
    			return;
    		}
    		
    		var scoreInput = $("#scoreInput").val();
    		if (scoreInput == '') {
    			Win.alert("请输入试卷总分！");
    			return;
    		}else if(scoreInput=="0"){
    			Win.alert("试卷总分不能为0！");
    			return;
    		}else if(scoreInput >999){
    			Win.alert("试卷总分不能大于999！");
    			return;
    		}
    		
    		
    		if(Object.keys(qlist).length < 1){
    			Win.alert("没有习题，请返回添加习题");
    			return false;
    		}
    		
    		$("[name = 'score']").each(function(index,e){
    			if(e.value == "" || e.value*1 == 0){
    				Win.alert("请为所有题目设置分数");
    				return false;
    			}
    		});
    		
    		if($("#notset").html() != "0"){
    			Win.alert("未设置所有分数");
    			return false;
    		}
    		
    		
    			var f=$("#examForm").serializeArray();
    			var q=$("#queForm").serializeArray();
    			var bzparam="?"+$("#bzids").val();
    			
    			console.log(f);
    			console.log(q);
    			//如果校验通过，则提交
    			var url = "../../../teacherTest/finishEditExam.do"+bzparam;
    			$.ajax({
    				type: 'POST',
    				url: url,
    				data: f.concat(q),
    				success : function(d){
    					Win.alert("编辑成功!");
    					window.setTimeout(function(){
    						window.location.href='../../examList.html';
    					}, 2000);
    				}
    			});
    	}
    	
    	
    	//保存并布置
    	function editbzexam(){
    		console.log("保存并布置");
    		var $elm = "${examView.examId}";
    		
    		//如果是教师
    		if("${sessionScope.SESSION_USER.userType eq 'TEACHER'}" == "true"){
	    		Win.open({
					title : "布置试卷",
					width : 520,
					height : 570,
					url : "../../selectStudentsPre.do?isedit=true&examId="+$elm
				});
    		}else{
	    		Win.open({
					title : "布置试卷",
					width : 520,
					height : 570,
					url : "../../selectClassPre.do?isedit=true&examId="+$elm
				});
    			
    		}
    		
    	}
    	
    	
    	var str = '∷';
    	$(document).ready(function(){
    		//补全div
    	  completQuesDiv();
    		//刷新显示数字
    	  updateDivNumSelf();
    		
      	  var examId = '${examView.examId}';
          //获取习题种类
      	  getQuestionTypeByExam(examId);
      
     
      //绑定点击事件
      $(".text").unbind('click');
      $(".text").click(function(){
       if($(this).hasClass("show")){
    	 $(this).siblings(".answer").hide();
		 $(this).siblings(".answer-analyze").hide();
    	 $(this).removeClass("show");
       } else {
    	 $(this).siblings(".answer").show();
    	 $(this).siblings(".answer-analyze").show();
    	 $(this).addClass("show");
    	 $(this).removeClass("hide");
      }
     });
    	
     });
    
    $(".options").each(function(){
    	$(this).html(convertToOptions($(this).html()));
    });
    
    function convertToOptions(optStr) {
    	var opts = optStr.split(str);
    	var result = "";
    	for (var i=0; i < opts.length ; i++) {//结尾有;;,opts最后有一个空字串需要丢弃
    		result = result + "<li><div style='display:inline-block;vertical-align: top;width: 91%;'>" + opts[i] + "</div><li>";
    	}
    	return result;
    }
    
    function playVideo(obj){//点击播放视频
  		var videoPath = $(obj).prop("id");
  		var id="0s";
 		var index1=videoPath.lastIndexOf(".");
 		var index2=videoPath.length;
 		var postf=videoPath.substring(index1,index2);//获取文件后缀名
 		
 		var showClassId="watch_play"+id;
		var fileurl=API_PATH+"/Video/"+videoPath;
		
		//如果添加的音视频数目大于1则停止向下追加
		var showClassIndex="#"+showClassId;
		if($(obj).next(showClassIndex).find("audio").size()>=1){
			Win.alert("音频正在播放，请不要继续点击!");
			return;	
		}

 		if(postf.indexOf("mp3")>0){
 			//使用ajax获取本音频的据对路径，通过url
 		    $(obj).next(showClassIndex).append('&nbsp;&nbsp;&nbsp;<audio src="${root}/videos/'+videoPath+'" autoplay loop  controls></audio>'); 
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

    
    //从题库中删选题目
    function selectQuestions(){
    	console.log("添加习题");
    	Win.open({id:'uploadExam',width:1380,height:1120,title:"选择习题",url:"../../editExamAdd.do?semesterId=${examView.baseSemesterId}&subjectId=${examView.baseSubjectId}"});
    }	
    </script>
   
</body>
</html>