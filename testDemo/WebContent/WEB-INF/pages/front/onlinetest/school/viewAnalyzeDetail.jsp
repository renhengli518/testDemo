<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>


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
<body class="mainIndex">
    <%@include file="../../../common/topHeader.jsp"%>
    <%@include file="../../../common/nav.jsp"%>
    <%@include file="../../../common/fixedRight.jsp"%>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
                <a href="javascript:;">班级测试</a><span class="nextlevel">></span><a href="javascript:;">统计</a><span class="nextlevel">></span><span class="currentLevel">详细统计</span>
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite borderBoxP40">
			     
			     <c:set var="title1" value="0"></c:set>
				 <c:set var="title2" value="0"></c:set>
				 <c:set var="title3" value="0"></c:set>
				 <c:set var="title4" value="0"></c:set>
				 <c:set var="title5" value="0"></c:set>
				 <c:set var="title6" value="0"></c:set>
				 <c:set var="questionNo" value="1"></c:set>
               
			     
			     
			     
			  
			     <div class="checkAnswerWrap">
			         <c:forEach var="q" items="${examQuestions}" varStatus="status"> 
			         
			            <%@include file="../../../common/stuExamQuestions.jsp"%> 
			         
			    
				
				<div class="borderBox pd20">
			             <div class="quesContent">
			            <c:if test="${q.type == 'SINGLE_CHOICE' ||  q.type == 'MULTI_CHOICE' || q.type == 'JUDEMENT'}">
			               
			               <p class="quesTitle">
			               
			                 <p class="fl fz10">${status.count}、</p>${q.content}</p>
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
					       &nbsp; 正确率： <span>${q.rightRate}%</span>                      
					       &nbsp;分值：  <span class="mr40 red">${q.score}</span>分   
					                    
			                </span>
			             </c:if>
			                
			             <c:if test="${q.type == 'FILL_IN_BLANK'}">
	                       <p class="quesTitle">
	                       
	                           <p class="fl fz10">${status.count}、</p>${q.content}</p>
	                            <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						          <br/>
						        </c:if>
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
					        &nbsp;正确率： <span>${q.rightRate}%</span>                      
					        &nbsp;分值：  <span class="mr40 red">${q.score}</span>分 
					       </span>                        
			               
			            </c:if>
			            
			             <c:if test="${q.type == 'ASK_ANSWER'}">
			               <p class="quesTitle">
			               
			                <p class="fl fz10">${status.count}、</p>${q.content}</p>
	                        <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						          <br/>
						    </c:if>
					       <div class="teaComments">
				                <p class="fontBold">正确答案</p>
				                <div class="borderBox correctAnswer1">
				          		    ${q.answer}
				                </div>
				                
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
					       &nbsp;分值：  <span class="mr40 red">${q.score}</span>分   
					       &nbsp; 平均得分： <span class="mr40 red">${q.avgScore}</span>分 
					       </span>                     
			              </div>
			            </c:if>
			            
			             <c:if test="${q.type == 'COMPUTING'}">
			               <p class="quesTitle">
			                  <p class="fl fz10">${status.count}、</p>${q.content}</p>
	                           <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						          <br/>
						       </c:if>
					       <div class="teaComments">
				                <p class="fontBold">正确答案</p>
				                <div class="borderBox correctAnswer1">
				          		    ${q.answer} 
				                </div>
				               
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
					       &nbsp;分值：  <span class="mr40 red">${q.score}</span>分   
					       &nbsp; 平均得分： <span class="mr40 red">${q.avgScore}</span>分
					       </span>                      
			              </div>
			            </c:if>
			            
			            
			            
			                <div class="teaComments">
			                  <div class="borderBox comments" style="display:block">
			                        <p class="commentsKinds">
				                      <a href="javascript:;" class="active" id="resolve_${status.index}">习题解析</a>
				                      <a href="javascript:;" id="know_${status.index}" flag="${q.examQuestionId}">知识点</a>
				                      <a href="javascript:;" id="statis_${status.index}" flag="${q.examQuestionId}">统计</a>
				                    </p>
				                    <div>
				                    	<div class="pd10 commentContent" id="resolve_${status.index}_box" style="display:block">
				                       		  ${q.resolve} <br/>
				                       		 <c:if test="${q.resolveVideo != null }">
						                       <p class="playVideoP"><button class="btn playVideo" onclick="playVideo('${q.resolveVideo}','${q.examQuestionId}')">点播解析视频</button></p>
						                     </c:if>
				                        </div>
				                        <div class="pd10 commentContent" id="know_${status.index}_box" style="display:none">
				                       		  
				                        </div>
				                        <div class="pd10 commentContent" id="statis_${status.index}_box" style="display:none">
				                        
				                        
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
    <script type="text/javascript">
      $(document).ready(function(){
    	  var examTaskId = '${examTaskId}';
    	  //获取习题种类
    	  getQuestionTypeByTask(examTaskId);
    	  
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
    		  if(index == 1 && $(this).hasClass("active")){
    			 //获取知识点
				 bindKnowledgeInfoById($elemLoad,examQuestionId);
    		  }
    		  
    		  if(index == 2){
				  //获取单题统计信息
				  bindSpecifyOptionSatistics($elemLoad,examTaskId,examQuestionId);
			  }
    		  
    		 
    	  });
    	  
	   });
       
      
      //获取知识点
      function bindKnowledgeInfoById($elem,examQuestionId){
			$.ajax({                                                                                                                                            
				url		: '${root}/schoolTest/getExamQstRKnowledgeInfoByExamQstId.do',                                                                    
				cache	:	false,
				type    :   "post",
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
   
      //获取统计
      function bindSpecifyOptionSatistics($elem,examTaskId,examQstId){
 		var classId = '${classId}';
 		 $.ajax({                                                                                                                                            
 				url		:	'${root}/schoolTest/getExamQstOptionStatisticsByExamQstId.do',
 				cache	:	false, 
 				type    :   "post",
 				data	:	{
 					         'examTaskId'      : examTaskId,
 					         'examQuestionId'  : examQstId,
 					         'classId'         : classId
 					        },
 				dataType: 'json',
 				success	:	function(data){
 					var html = "";
 					for(var i=0; i < data.length; i++){
 						var obj =  data[i];
 						var objArr = obj.split('-');
 						if(objArr[2] == 'SINGLE_CHOICE' || objArr[2] == 'JUDEMENT' || 
 								objArr[2] == 'MULTI_CHOICE'){
 					      html += '<p>选择'+objArr[0]+'的人数：<b class="red person-nums">'+objArr[1]+'</b>人</p>';	
 						}else{
 						  html += '<p>得'+objArr[0]+'分的人数：<b class="red person-nums">'+objArr[1]+'</b>人</p>';	
 						}
 					}
 					$elem.html(html);
 				}
 		 });
 		 
      }
      
						
    </script>
</body>
</html>