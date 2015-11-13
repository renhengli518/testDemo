<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>

<script type="text/javascript" src="${PUBLIC_PATH}/public/upload/uploadfile.js"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/js/extend.js"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/css/4.0/resplayer.css"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/player/videoplayer.js"></script>

<script type="text/javascript" src="${root}/biz/js/showVideo.js"></script>

<script>
 $(function(){
	 $(".chooseClass").on("click","a",function(){
		 var $this=$(this);
		 $this.addClass("active").siblings().removeClass("active");
	 })
 })
</script>
</head>
<body class="parOrChildRole">
     <%@include file="../../../common/topHeader.jsp"%>
     <%@include file="../../../common/nav.jsp"%>
     <%@include file="../../../common/fixedRight.jsp"%>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
                <a href="javascript:;">测试任务</a><span class="nextlevel">></span><span class="currentLevel">查看</span>
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite borderBoxP40">
                  <h3 class="titleOfCheckAnswer clearfix">
                   	  ${examTaskView.title}
                  </h3>
                 <ul class="commonUL gn-searchCondition specialSortBox" style="margin:0 -40px">
			    	 <li>
			    		 <label class="text gn-labelText"><b>年级：</b></label>
			    		 <span class="cont gn-sortBox  clearfix">
			    		   <span class="mr30">${examTaskView.classlevelName}</span>
			    		   学科：<span class="mr30">${examTaskView.subjectName}</span>试卷类型：<span class="mr30">${examTaskView.examTypeName}</span>测试人数：<span class="mr30"><span class="red">${examTaskView.finishedCount}</span>/${examTaskView.assignedCount}</span>
			    		 答题时长：<span class="mr30">${examTaskView.answerTime}分钟</span>试卷总分：<span class="mr30">${examTaskView.score}分</span>
			    		 </span>
			    	 </li>
			    	 <li>
			    		 <label class="text gn-labelText"><b>开始时间：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		   <span class="mr30"><fmt:formatDate value="${examTaskView.startTime}" pattern="yyyy-MM-dd HH:mm"/></span>
			    		   完成时间：<span class="mr30"><fmt:formatDate value="${examTaskView.finishedTime}" pattern="yyyy-MM-dd HH:mm"/></span>
			    		 </span>
			    	 </li>
			     </ul>
			     <div class="checkAnswerWrap">
			     
			         <div class="tableWrap mt30">
				        <h5>试卷统计</h5>
				        <div>
				          <table class="newTableStyle" id="statistics">
				             <thead>
					             <tr>
					               <th width="15%">来源</th>
					               <th width="15%">答题人数</th>
					               <th width="10%">最高得分</th>
					               <th width="10%">最低得分</th>
					               <th width="10%">平均得分</th>
					               <th width="10%">我的得分</th>
					               <th width="15%">正确率（客观题）</th>
					               <th width="15%">错题数（客观题）</th>
					             </tr>
					           </thead>
					           <tbody>
					             
					           </tbody>
					      </table>
				        </div>
				     </div>
			     <div class="line"></div>
					
					
			    <div class="checkAnswerWrap">
			       <c:forEach var="q" items="${examQuestions}" varStatus="status"> 
			           <%@include file="../../../common/stuExamQuestions.jsp"%>
			        	
                    
			    <div class="borderBox pd20">
			        <div class="quesContent">
			          <c:if test="${q.type == 'SINGLE_CHOICE' ||  q.type == 'MULTI_CHOICE' || q.type == 'JUDEMENT'}">
			               
			            <p class="quesTitle">
			              <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}</p>
			               
			               <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						          <br/>
						      </c:if>
			                 
			                  <c:forTokens items="${q.options}" delims="∷" var="name">
									${name}
								<br>
							  </c:forTokens>
					        
					      <span class="fontBold">  正确答案：  <span class="mr40"> ${q.answer} </span>  
					                    我的选择：<span class="mr40"> ${q.myAnswer} </span>         
					                     难易度： <span class="mr40 red">
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
					                        分值：  <span class="mr10 red">${q.score}</span>分   
					                         得分： <span class="mr10 red">${q.myScore}</span>分                      
			                </span>
			             </c:if>
			                
			             <c:if test="${q.type == 'FILL_IN_BLANK'}">
	                       <p class="quesTitle">
	                          <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}</p>
					       <span class="fontBold">  正确答案：
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
					                    我的答案：<span class="mr10"> 
					           <c:if test="${!empty q.myAnswer}">
						        <table class="anyTable">
							   
							     <tbody>
							      <c:forTokens items="${q.myAnswer}" delims="∷" begin="0" end="${fn:length(q.myAnswer)}" varStatus="status" var="name">
								   <tr>
									<td>第${status.index+1}空</td>
									<td class="edit-pop-btn">
									<div style="width: 166px">${name}</div>
								   </td>
								   </tr>
								  </c:forTokens>
							    </tbody>
							   </table>
						    </c:if>      
					       </span>         
					                     难易度： <span class="mr40 red">
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
					                        分值：  <span class="mr10 red">${q.score}</span>分   
					                         得分： <span class="mr10 red">${q.myScore}</span>分                      
			                </span>
			            </c:if>
			            
			             <c:if test="${q.type == 'ASK_ANSWER'}">
			               <p class="quesTitle">
			                  <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}</p>
					       <div class="teaComments">
				                <p class="fontBold">正确答案</p>
				                <div class="borderBox correctAnswer1">
				          		    ${q.answer}
				                </div>
				                <p class="fontBold">我的答案</p>
			                    <div class="borderBox hisAnswer1">
			                      <c:if test="${! empty q.myAnswer}">
			                       	   	${q.myAnswer}
			                       	</c:if>
			                       	<c:if test="${empty q.myAnswer}">
			                       	   	<br/>
			                       	</c:if>
			                    </div>
			                    
			                    <p class="stuHandleSources mt10">
			                       <c:if test="${! empty q.answerVideo}">
			                                                                    音视频:<span class="mr20 sourcesName">
			                        <span id='watch_play"${status.count}'><a style="color:green;"  href="javascript:playVideo('${q.answerVideo}', '${status.count}')">点击播放</a></span>
			                       </span>
			                     </c:if>
			                    
			                    </p>
			               <span class="fontBold">        
					                     难易度： <span class="mr40 red">
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
					                          分值：  <span class="mr10 red">${q.score}</span>分   
					                          得分： <span class="mr10 red">${q.myScore}</span>分
					                          平均得分： <span class="mr10 red">${q.avgScore}</span>分           
					        </span>                     
			              </div>
			            </c:if>
			            
			            <c:if test="${q.type == 'COMPUTING'}">
			               <p class="quesTitle">
			                <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}</p>
	                    
					       <div class="teaComments">
				                <p class="fontBold">正确答案</p>
				                <div class="borderBox correctAnswer1">
				          		    ${q.answer}
				                </div>
				                <p class="fontBold">我的答案</p>
			                    <div class="borderBox hisAnswer1">
			                       <c:if test="${! empty q.myAnswer}">
			                       	   	${q.myAnswer}
			                       	</c:if>
			                       	<c:if test="${empty q.myAnswer}">
			                       	   	<br/>
			                       	</c:if>
			                    </div>
			                     <p class="stuHandleSources mt10">
			                      <c:if test="${! empty q.answerVideo}">
			                                                                    音视频:<span class="mr20 sourcesName">
			                        <span id='watch_play"${status.count}'><a style="color:green;"  href="javascript:playVideo('${q.answerVideo}', '${status.count}')">点击播放</a></span>
			                       </span>
			                     </c:if>
			                    </p> 
			               <span class="fontBold">      
					                     难易度： <span class="mr40 red">
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
					                          分值：  <span class="mr10 red">${q.score}</span>分   
					                          得分： <span class="mr10 red">${q.myScore}</span>分
					                          平均得分： <span class="mr10 red">${q.avgScore}</span>分      
					        </span>                                   
			              </div>
			            </c:if>
			            
			            
			            <div class="teaComments">
			              <div class="borderBox comments" style="display:block">
			                <p class="commentsKinds">
				               <a href="javascript:;" class="active" id="tea_${status.index}">老师点评</a>
				               <a href="javascript:;" id="resolve_${status.index}">习题解析</a>
				               <a href="javascript:;" id="know_${status.index}" flag="${q.examQuestionId}">知识点</a>
				            </p>
				          <div>
				          <div class="pd10 commentContent" id="tea_${status.index}_box" style="display:block">
				             <c:if test="${q.teacherComment == null || q.teacherComment.trim() == ''}">
								 老师没有点评！
							 </c:if>
				                ${q.teacherComment}
				          </div>
				          <div class="pd10 commentContent" id="resolve_${status.index}_box" style="display:none">
				             ${q.resolve} <br/>
						      <c:if test="${q.resolveVideo != null }">
						         <p class="playVideoP"><button class="btn playVideo" onclick="playVideo('${q.resolveVideo}','${q.examQuestionId}')">点播解析视频</button></p>
						      </c:if>
				          </div>
				          <div class="pd10 commentContent" id="know_${status.index}_box" style="display:none">
				            
				          </div>
				     </div>
				                    
			       </div>
			      </div>
			     </div>
			    </div>
			        
			   </c:forEach>
			  </div>
			 </div>
			</div>
	     </div>
	     </div>
    </div>
   <script type="text/javascript">
      var user_schoolId = "${sessionScope.SESSION_USER.schoolId}";	<%-- 当前登录用户学校id --%>
      var studentId = "${sessionScope.SESSION_USER.userId}";  <%-- 当前登录用户id --%>
      $(document).ready(function(){
    	  var examTaskId = '${examTaskView.examTaskId}';
    	  var classlevelId = '${examTaskView.classlevelId}';
    	  var classId = '${examTaskView.classId}';
    	  //获取习题种类
    	  getQuestionTypeByTask(examTaskId);
    	 
    	  var url = "${root}/studentTest/getClassExamStatistics.do";
    	  var obj = {
    			examTaskId   : examTaskId,
    			studentId    : studentId,
    			classlevelId : classlevelId,
    			classId      : classId
    	  };
    	  $.post(url, obj, function(data){
    		  if (data != null){
    			  var html = '';
    			  html += '<tr>';
    			  html += '<td>本班</td>';
    			  html += '<td>'+data.commitCnt+'</td>';
    			  html += '<td>'+data.highestScore+'</td>';
    			  html += '<td>'+data.lowestScore+'</td>';
    			  html += '<td>'+data.avgScore+'</td>';
    			  html += '<td>'+data.myScore+'</td>';
    			  html += '<td>'+data.rightRate+'%</td>';
    			  html += '<td>'+data.mistakeCnt+'</td>';
    			  html += '</tr>'; 
    			  $("#statistics tbody").html(html);
    		  } 
    	  }, 'json');
    	  
    	  
    	 
          //切换页签
    	  $(".commentsKinds").on("click", "a", function(e) {
    		  var id = this.id;
    		  $(this).addClass("active");
    		  $(this).siblings().removeClass("active");
    		  $("#"+id+"_box").css("display", "block");
    		  $("#"+id+"_box").siblings().css("display", "none");
    		  
    		  var $elem = $(this);
			  var index = $elem.parent().find("a").index($elem);
			  var examQuestionId = $elem.attr("flag");
			  var $elemLoad = $elem.parent().parent().find(".commentContent:eq("+index+")");
    		  if(index == 2 && $(this).hasClass("active")){
    			 //获取知识点
				 bindKnowledgeInfoById($elemLoad,examQuestionId);
    		  }
    		   
    	    }); 

        });
           	       
		function bindKnowledgeInfoById($elem,examQuestionId){
    		$.ajax({                                                                                                                                            
    				url		: '${root}/studentTest/getExamQstRKnowledgeInfoByExamQstId.do',                                                                    
    				cache	:	false,                                                                                                                                     
    				data	:	{'examQuestionId':examQuestionId},
    				dataType: 'json',
    				success	:	function(data){
    				var html = "";
    				for(var i = 0; i< data.length; i++){
    					 var knowledgeObj = data[i];
    					 var knowledgeName = knowledgeObj.baseKnowledgeName;
    				     var subKnowledge1Id = knowledgeObj.baseSubKnowledge1Id;
    					 var subKnowledge2Id = knowledgeObj.baseSubKnowledge2Id;
    				     var subKnowledge3Id = knowledgeObj.baseSubKnowledge3Id;
    					 var subKnowledge4Id = knowledgeObj.baseSubKnowledge4Id;
    					 var subKnowledge5Id = knowledgeObj.baseSubKnowledge5Id;
    					 var subKnowledge1Name = knowledgeObj.baseSubKnowledge1Name;
    					 var subKnowledge2Name = knowledgeObj.baseSubKnowledge2Name;
    					 var subKnowledge3Name = knowledgeObj.baseSubKnowledge3Name;
    					 var subKnowledge4Name = knowledgeObj.baseSubKnowledge4Name;
    					 var subKnowledge5Name = knowledgeObj.baseSubKnowledge5Name;
    					 html += "<p>";
    					 html += knowledgeName;
    					 if (subKnowledge1Id != null && subKnowledge1Name != null){
    						 html += '-->' + subKnowledge1Name;
    					 }
    							
    					 if (subKnowledge2Id != null && subKnowledge2Name != null){
    					     html += '-->' + subKnowledge2Name;
    					 }
    							
    					 if(subKnowledge3Id != null  && subKnowledge3Name != null){
    						 html += '-->' + subKnowledge3Name;
    					 }
    							
    					 if(subKnowledge4Id != null  && subKnowledge4Name != null){
    						 html += '-->' + subKnowledge4Name;
    					 }
    							
    					 if(subKnowledge5Id != null  && subKnowledge5Name != null){
    						 html += '-->' + subKnowledge5Name;
    					 }
    					 html += "</p>";	
    					}
    					$elem.html(html);
    				 }
    	 });
     }
   
   </script>
</body>
</html>