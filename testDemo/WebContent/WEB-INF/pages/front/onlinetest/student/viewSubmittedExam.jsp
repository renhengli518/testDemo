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

<style>
.for-answer {
	width: 166px;
}

.for-answer img {
	max-width: 166px;
}

</style>


</head>
<body class="parOrChildRole">
    <%@include file="../../../common/topHeader.jsp"%>
    <%@include file="../../../common/examNav.jsp"%>
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
                 <ul class="commonUL gn-searchCondition specialSortBox">
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
					        
					        
					      <span class="fontBold">我的选择：<span class="mr40"> ${q.myAnswer} </span>         
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
					                      分值：<span class="mr10 red">${q.score}</span>分                 
			                </span>
			             </c:if>
			                
			             <c:if test="${q.type == 'FILL_IN_BLANK'}">
	                       <p class="quesTitle">
	                         <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}</p>
					           <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						          <br/>
						      </c:if>
					       <span class="fontBold"> 
					                    我的答案：<span class="mr10">
					       <c:if test="${!empty q.myAnswer}">
					       
						    <table class="anyTable">
							   
							  <tbody>
							   <c:forTokens items="${q.myAnswer}" delims="∷" begin="0" end="${fn:length(q.myAnswer)}" varStatus="status" var="name">
								 <tr>
									<td>第${status.index+1}空</td>
									<td class="edit-pop-btn">
									  <div class="for-answer">${name}</div>
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
			                </span>
			            </c:if>
			            
			             <c:if test="${q.type == 'ASK_ANSWER'}">
			               <p class="quesTitle">
			                <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}</p>
					         <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						          <br/>
						      </c:if>
					       <div class="teaComments">
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
					       </span>                   
			              </div>
			            </c:if>
			            
			            <c:if test="${q.type == 'COMPUTING'}">
			               <p class="quesTitle">
			                 <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}</p>
	                          <c:if test="${q.contentVideo != null }">
						         <a href="javascript:;" class="movieAnalysisBtn btn bgBlue videoItem" onclick="showContentVideo('${q.contentVideo}','${q.examQuestionId}');">点击播放音视频</a>
						         <span id="showVideo${q.examQuestionId}"></span>
						          <br/>
						      </c:if>
					       <div class="teaComments">
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
					       </span>           
			              </div>
			            </c:if>
			           
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
 	   var examTaskId = '${examTaskView.examTaskId}';
 	   //获取习题种类
 	   getQuestionTypeByTask(examTaskId);
 	  
	  });
    </script>
</body>
</html>