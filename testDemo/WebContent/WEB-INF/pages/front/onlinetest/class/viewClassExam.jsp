<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>


<script type="text/javascript" src="${PUBLIC_PATH}/public/css/4.0/resplayer.css"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/player/videoplayer.js"></script>

<script type="text/javascript" src="${root}/biz/js/showVideo.js"></script>

</head>
<body class="mainIndex">
     <c:set var="menuTag" value="TEST"/>
     <%@include file="../../../common/topHeader.jsp"%>
     <%@ include file="../../../common/classroomHeader.jsp" %>
     <%@include file="../../../common/fixedRight.jsp"%>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
         
              <div class="breadCrumb">
                <a href="javascript:;">班级测试</a><span class="nextlevel">></span><span class="currentLevel">查看测试 </span>
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite pd20">
                  <h3 class="MarginTitle">${examTaskView.title}</h3>
                  <ul class="commonUl descCommon clearfix">
                    <li>
                      <label>学科：</label><span class="c888">${examTaskView.subjectName}</span>
                    </li>
                    <li>
                      <label>试卷类型：</label><span class="c888">${examTaskView.examTypeName}</span>
                    </li>
                    <li>
                      <label>答题时长：</label><span class="c888">${examTaskView.answerTime}分钟</span>
                    </li>
                    <li>
                      <label>试卷总分：</label><span class="c888">${examTaskView.score}分</span>
                    </li>
                    <li>
                      <label>测试人数：</label><span class="c888"><span class="red">${examTaskView.finishedCount}</span>/${examTaskView.assignedCount}</span>
                    </li>
                    <li>
                      <label>开始时间：</label><span class="c888"><fmt:formatDate value="${examTaskView.startTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </li>
                    <li>
                      <label>结束时间：</label><span class="c888"><fmt:formatDate value="${examTaskView.finishedTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </li>
                  </ul>
              </div>
              <div class="borderBox gn-bgWhite bigPd mt17">
                
                
                 <c:set var="title1" value="0"></c:set>
				 <c:set var="title2" value="0"></c:set>
				 <c:set var="title3" value="0"></c:set>
				 <c:set var="title4" value="0"></c:set>
				 <c:set var="title5" value="0"></c:set>
				 <c:set var="title6" value="0"></c:set>
				<c:set var="questionNo" value="1"></c:set>
				
                
                
                <c:forEach var="q" items="${examQuestions}" varStatus="status">
                   <c:if test="${q.type == 'SINGLE_CHOICE' && title1 == 0}">
                     <div class="testCon" id="choice">
                        <c:set var="title1" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">一、单选题  <span class="quesNum">（${q.num}题）</span></h3>
                   
                   </c:if>
                   
                   <c:if test="${q.type == 'MULTI_CHOICE' && title2 == 0}">
                     <div class="testCon" id="multi_choice">
                        <c:set var="title2" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle"> <c:if test="${title1 == 0 }">一</c:if>
						   <c:if test="${title1 == 1 }">二</c:if>
						   、多选题<span class="quesNum">（${q.num}题）</span></h3>
                   
                   </c:if>
                   
                   <c:if test="${q.type == 'JUDEMENT' && title3 == 0}">
                     <div class="testCon" id="file_in_blank">
                        <c:set var="title3" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">
                            <c:if test="${(title1 + title2) == 0 }">一</c:if>
						   <c:if test="${(title1 + title2) == 1 }">二</c:if>
						   <c:if test="${(title1 + title2) == 2 }">三</c:if>
						 、判断题
                         <span class="quesNum">（${q.num}题）</span></h3>
                   
                   </c:if>
                   
                   <c:if test="${q.type == 'FILL_IN_BLANK' && title4 == 0}">
                     <div class="testCon" id="judement">
                        <c:set var="title4" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">
                            <c:if test="${(title1 + title2 + title3) == 0 }">一</c:if>
						   <c:if test="${(title1 + title2 + title3) == 1 }">二</c:if>
						   <c:if test="${(title1 + title2 + title3) == 2 }">三</c:if>
						   <c:if test="${(title1 + title2 + title3) == 3 }">四</c:if>
						   、填空题
                         <span class="quesNum">（${q.num}题）</span></h3>
                                                                     
                   </c:if>
                   
                    <c:if test="${q.type == 'ASK_ANSWER' && title5 == 0}">
                     <div class="testCon" id="essay">
                        <c:set var="title5" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">
                          <c:if test="${(title1 + title2 + title3 + title4) == 0 }">一</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 1 }">二</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 2 }">三</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 3 }">四</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 4 }">五</c:if>
						 、问答题
                        <span class="quesNum">（${q.num}题）</span></h3>
                   
                   </c:if>
                   
                    <c:if test="${q.type == 'COMPUTING' && title6 == 0}">
                    <div class="testCon" id="computing">
			          <c:set var="title6" value="1"></c:set>
					  <c:set var="questionNo" value="1"></c:set>	
					  <h3 class="testTitle">
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 0 }">一</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 1 }">二</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 2 }">三</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 3 }">四</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 4 }">五</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 5 }">六</c:if>
						、计算题
						<span class="quesNum">（${q.num}题）</span></h3>
				     
				</c:if>
                 
                    <div class="quesDetail">
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
	                    <span class="fr mr0"><label>分值：</label><span class="red">${q.score}</span></span>
	                    </p>
                        
                          <div class="text" style="cursor:pointer">
	                        <div class="question">
	                         
	                          <p class="fl fz10">${status.count}、</p>
	                           <p>${q.content}</p>
	                         
	                         <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						       </c:if>
	                       </div>
	                        <c:if test="${q.type == 'SINGLE_CHOICE' ||  q.type == 'MULTI_CHOICE' || q.type == 'JUDEMENT'}">
	                           <c:forTokens items="${q.options}" delims="∷" var="name">
									${name}
								<br>
							   </c:forTokens>
					        </c:if>
	                         
	                       </div>
	                      <p class="answer" style="display:none"><b>【答案】</b><br/>
							 <span style="margin-left:20px;">${q.answer}</span>
						  </p>
						  <ul class="answer-analyze" style="display:none">
							 <c:if test="${q.type == 'FILL_IN_BLANK' }">
							   <c:if test="${q.fillInAnswers.size()>1 }">
								 <li>
									<label>给分类型：</label><b>${q.fillInAnswerType eq 'INDEPENDENT' ? '独立答案':'组合答案'}</b>
								    <label>答案类型：</label><b>${q.fillInScoreType eq 'COMBINATION' ? '全对给分':'按空给分'}</b>
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
									<c:if test="${q.fillInAnswerType eq 'INDEPENDENT' }">
									  <th>答案1</th>
									  <th>答案2</th>
									  <th>答案3</th>
									  <th>答案4</th>
									</c:if>
								    <c:if test="${q.fillInAnswerType eq 'COMBINATION' }">
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
				          <span class="mr20">解：</span><br/>
				          ${q.resolve}<br/>
						   <c:if test="${q.resolveVideo != null }">
						    <p class="playVideoP"><button class="btn playVideo" onclick="playVideo('${q.resolveVideo}','${q.examQuestionId}')">点播解析视频</button></p>
						  </c:if>
					    </ul>
                      </div>
                    
                    </c:forEach>
                    </div>
                  
                </div>
              </div>
			  
          </div>
     
  
    <script type="text/javascript">
    $(document).ready(function(){
      var examTaskId = '${examTaskView.examTaskId}';
      //获取习题种类
      getQuestionTypeByTask(examTaskId);
      
     
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
    
    </script>
   
</body>
</html>