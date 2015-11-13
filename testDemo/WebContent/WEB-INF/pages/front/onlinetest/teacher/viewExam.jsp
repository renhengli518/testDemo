<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
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
						<span class="nextlevel">></span><span class="currentLevel">查看试卷 </span>
					</c:otherwise>
				</c:choose>
				
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite pd20">
                  <h3 class="MarginTitle">${examView.title}</h3>
                  <ul class="commonUl descCommon clearfix">
                  	<c:if test="${tabType=='exam'}">
                    <li>
                      <label>更新时间：</label><span class="c888"><fmt:formatDate value="${examView.updateTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                    </li>
                    </c:if>
                    <c:if test="${tabType=='real'}">
                    <li>
                      <label>地区：</label><span class="c888">${examView.areaName}</span>
                    </li>
                    <li>
                      <label>年份：</label><span class="c888">${examView.year}</span>
                    </li>
                    </c:if>
                    <li>
                      <label>学段：</label><span class="c888">${examView.semesterName}</span>
                    </li>
                    <li>
                      <label>年级：</label><span class="c888">${examView.classlevelName}</span>
                    </li>
                    <li>
                      <label>学科：</label><span class="c888">${examView.subjectName}</span>
                    </li>
                    <li>
                      <label>试卷类型：</label><span class="c888">${examView.examTypeName}</span>
                    </li>
                    <li>
                      <label>答题时长：</label><span class="c888">${examView.answerTime}分钟</span>
                    </li>
                    <li>
                      <label>题量：</label><span class="c888">${examView.questionCount}</span>
                    </li>
                    <c:if test="${tabType=='real'}">
	                    <li>
	                      <label>使用次数：</label><span class="c888">${examView.useCount}</span>
	                    </li>
	                    <li>
	                      <label>试卷总分：</label><span class="c888">${examView.score}</span>
	                    </li>
                    </c:if>
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
                   
                   <c:if test="${q.type == 'FILL_IN_BLANK' && title3 == 0}">
                     <div class="testCon" id="file_in_blank">
                        <c:set var="title3" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">
                            <c:if test="${(title1 + title2) == 0 }">一</c:if>
						   <c:if test="${(title1 + title2) == 1 }">二</c:if>
						   <c:if test="${(title1 + title2) == 2 }">三</c:if>
						 、填空题
                         <span class="quesNum">（${q.num}题）</span></h3>
                   
                   </c:if>
                   
                   <c:if test="${q.type == 'JUDEMENT' && title4 == 0}">
                     <div class="testCon" id="judement">
                        <c:set var="title4" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h3 class="testTitle">
                            <c:if test="${(title1 + title2 + title3) == 0 }">一</c:if>
						   <c:if test="${(title1 + title2 + title3) == 1 }">二</c:if>
						   <c:if test="${(title1 + title2 + title3) == 2 }">三</c:if>
						   <c:if test="${(title1 + title2 + title3) == 3 }">四</c:if>
						 、判断题
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
                    </div>
                  
                </div>
              </div>
			  
          </div>
                 
    <script type="text/javascript">
    	var str = '∷';
    	$(document).ready(function(){
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
    </script>
   
</body>
</html>