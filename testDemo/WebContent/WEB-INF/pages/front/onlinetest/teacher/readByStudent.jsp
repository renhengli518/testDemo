<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/homework.css"/>

<script type="text/javascript" src="${PUBLIC_PATH}/public/css/4.0/resplayer.css"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/player/videoplayer.js"></script>

<!--  引入富文本框需要的插件-->
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/third-party/blank/addBlankButton.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/third-party/blank/addBlankButton.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/plugin/ueditor/kityformula-plugin/defaultFilterFix.js"></script>

</head>
<body class="mainIndex">
    <%@include file="../../../common/topHeader.jsp" %>
	<%@include file="../../../common/examNav.jsp" %>
	
<form id="comentForm">
     <input type="hidden" value="${resultId}" name="examResultId"/><!--本学生测试结果的id-->
     <input type="hidden" value="${examTaskId}" name="examTaskId"/>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
                <a href="${root}/teacherTest/classExamList.html">测试任务</a><span class="nextlevel">></span><a href="${root}/teacherTest/readOverOnLineTest/${examTaskId}.html" class="currentLevel">批阅</a><span class="nextlevel">></span><span class="currentLevel">按学生批阅</span>
              </div>
              <div class="borderBox gn-bgWhite pd20 pb0">
                   	<h3> 答卷人：<span>${requestScope.stuName}</span></h3>   
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite borderBoxP40">
			  	<c:if test="${!empty requestScope.examQuestionList}">
	                <div class="checkAnswerWrap">
				    	<c:set var="subjectiveIds" value=""></c:set>
				     	<c:set var="queTypeTitleIndexName" value="${fn:split('一,二,三,四,五,六',',')}" />
				     	<c:set var="se" value="${fn:split('A,B,C,D,E,F,G',',')}" />
				     	<c:set var="queTypeTitleIndex" value="0"></c:set>
				     	<c:set var="queType" value=""></c:set>
				     	<c:forEach items="${requestScope.examQuestionList}" var="que" varStatus="status">
				     		<c:if test="${queType ne que.type}">
				     			<c:if test="${!status.first}">
				     				</div>
				     			</c:if>
				     			<c:set var="queType" value="${que.type}"></c:set>
				     			<div class="testCon checkAnsweContent">
				     				<h4 class="quesKind">${queTypeTitleIndexName[queTypeTitleIndex]}、
				     				<c:if test="${que.type eq 'SINGLE_CHOICE'}">单选题</c:if>
				     				<c:if test="${que.type eq 'MULTI_CHOICE'}">多选题</c:if>
				     				<c:if test="${que.type eq 'JUDEMENT'}">判断题</c:if>
				     				<c:if test="${que.type eq 'FILL_IN_BLANK'}">填空题</c:if>
				     				<c:if test="${que.type eq 'ASK_ANSWER'}">问答题</c:if>
				     				<c:if test="${que.type eq 'COMPUTING'}">计算题</c:if>
				     				</h4>
				     				<c:set var="queTypeTitleIndex" value="${queTypeTitleIndex +1 }"></c:set>
				     		</c:if>
				     		<!-- 将每道题的本身的分值传到后台-->
				     		<input type="hidden" name="standardScore_${que.examQuestionResultId}" value="${que.score}"/>
				     		<!-- 将每道题的id传递过去-->
				     		<input type="hidden" name="examResultQuestionId" value="${que.examQuestionResultId}"/>
				     		<div class="borderBox queDiv pd20" queId="${que.examQuestionId}" queType="${que.type}" queIndex="${status.count }">
					             <div class="quesContent verticalMiddle">
					             <div class="quesTitle">
					             	<p class="fl"><span class="fz20">${status.count }、</span>  
						             	<c:choose>
											<c:when test="${que.result eq 0}">
												<span class="red">[答错]</span>
											</c:when>
											<c:when test="${que.result eq null}">
												<span class="red"></span>
											</c:when>
											<c:otherwise>
												<span class="green">[答对]</span>
											</c:otherwise>
										</c:choose>
					             	</p>${que.content }
								 </div>
					             <c:if test="${!empty que.contentVideo}">
									<p class="uploadMediaWrap mt20">
										<a href="javaScript:;" class="btn btnUpLoad uploadBox"   onclick="playVideo('${que.contentVideo}','${que.examQuestionId}');">点击播放音视频</a>
									</p>
								 </c:if>
								 <c:if test="${que.type eq 'SINGLE_CHOICE' || que.type eq 'JUDEMENT' || que.type eq 'MULTI_CHOICE'}">
						             <c:forTokens items="${que.options}" delims="∷" var="option" varStatus="opStatus">
						             	选项${option }<br/>
		                             </c:forTokens>
		                             <span class="fontBold">  正确答案：  <span class="mr40"> ${que.answer } </span> <span class="fontBold"> 
		                             <c:if test="${que.answerVideo!=null}">
				                            <p class="uploadMediaWrap mt20">
										    <a href="javaScript:;" class="btn btnUpLoad uploadBox"   onclick="playVideo('${que.answerVideo}','${que.examQuestionId}');">答题音视频</a>
									        </p>
				                     </c:if>   
		                                                                      学生答案：  <span class="mr40"> ${que.myAnswer } </span>  
		                               <span class="fontBold"> 难易度：
		                                 <span class="mr40"> 
		                                 	  <c:if test='${que.difficulty eq "EASY"}'>
					                                                                               容易
					                          </c:if>
					                          <c:if test='${que.difficulty eq "LITTLE_EASY"}'>
					                                                                                较容易
					                          </c:if>
					                          <c:if test='${que.difficulty eq "NORMAL"}'>
					                                                                                 一般
					                          </c:if>
					                          <c:if test='${que.difficulty eq "LITTLE_DIFFICULT"}'>
					                                                                                 较难
					                          </c:if>
					                          <c:if test=" ${que.difficulty eq 'DIFFICULT'}">
					                                                                                  难
					                          </c:if> 
		                                 
		                                 </span>     
				                     </span><span class="fontBold"> 分值：<span class="mr40">${que.score}</span></span>
				                     <span class="fontBold"> 得分：<span class="mr40">${que.myScore}</span></span>
			                     </c:if>
			                     <c:if test="${que.type eq 'FILL_IN_BLANK'}">
			                     	<ul class="commonUL">
				                     <li>
				                       <label class="text fontBold left">正确答案：</label>
				                       <span class="font">
				                          <table collapse="" class="anyTable">
								           <thead>
								             <tr>
								               <th>
								                 <div class="specialTH">
								                   <span class="rightTop">答案容错</span>
								                   <span class="leftBottom">填空</span>
								                 </div> 
								              </th>
								              <c:if test="${que.fillInAnswerType eq 'INDEPENDENT'}">
												<th>答案1</th>
												<th>答案2</th>
												<th>答案3</th>
												<th>答案4</th>
											  </c:if>
											  <c:if test="${que.fillInAnswerType eq 'COMBINATION'}">
												<th>第一组</th>
												<th>第二组</th>
												<th>第三组</th>
												<th>第四组</th>
											  </c:if>
								            </tr>
								          </thead>
								          <tbody>
								            <c:forEach var="fillInAnswer" items="${que.fillList}" varStatus="fs">
												<tr>
													<td>第${fs.index +1}空</td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp1}</div></td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp2}</div></td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp3}</div></td>
													<td class="edit-pop-btn"><div class="answerCon">${fillInAnswer.answerGrp4}</div></td>
												</tr>
											</c:forEach>
								          </tbody>
								        </table>
				                       </span>
				                     </li>
				                   </ul>
				                   <ul class="commonUL">
				                     <li>
				                       <label class="text fontBold left">学生答案:</label>  
				                       <span class="font">
				                          <table collapse="" class="anyTable">
								          <tbody>
								          	<c:set var="myFillAnswer" value="${fn:split(que.myAnswer,'∷')}"></c:set>
								          	<c:if test="${que.fillList!=null}">
								          	   <c:forEach var="fillInAnswer" items="${que.fillList}" varStatus="fs">
												<tr>
													<td>第${fs.index+1}空</td>
													<td class="edit-pop-btn">
														<div class="answerCon">${myFillAnswer[fs.index]}</div>
													</td>
												</tr>
											</c:forEach>	
								          </c:if>	
								          </tbody>
								        </table>
								        <c:if test="${que.answerVideo!=null}">
				                            <p class="uploadMediaWrap mt20">
										    <a href="javaScript:;" class="btn btnUpLoad uploadBox"   onclick="playVideo('${que.answerVideo}','${que.examQuestionId}');">答题音视频</a>
									        </p>
				                        </c:if>
				                         <span class="fontBold"> 难易度：
		                                   <span class="mr40"> 
		                                 	  <c:if test='${que.difficulty eq "EASY"}'>
					                                                                               容易
					                          </c:if>
					                          <c:if test='${que.difficulty eq "LITTLE_EASY"}'>
					                                                                                较容易
					                          </c:if>
					                          <c:if test='${que.difficulty eq "NORMAL"}'>
					                                                                                 一般
					                          </c:if>
					                          <c:if test='${que.difficulty eq "LITTLE_DIFFICULT"}'>
					                                                                                 较难
					                          </c:if>
					                          <c:if test=" ${que.difficulty eq 'DIFFICULT'}">
					                                                                                  难
					                          </c:if> 
		                                 </span>     
				                     </span>
				                       </span><span class="fontBold"> 分值：<span class="mr40">${que.score}</span></span>
				                       <span class="fontBold"> 得分：<span class="mr40">${que.myScore}</span></span>
				                     </li>
				                   </ul>
			                     </c:if>
			                     <c:if test="${que.type eq 'ASK_ANSWER' || que.type eq 'COMPUTING'}">
			                     	<div class="teaComments">
					                    <ul class="commonUL">
					                      <li>
                                         <label class="text">正确答案：</label>  
					                        <span class="cont">
					                          ${que.resolve}
					                        </span>
					                      </li>
					                      <li>
					                        <label class="text">学生答案：</label>  
					                        <span class="cont red">
					                            ${que.myAnswer}
					                        </span>
					                        <c:if test="${que.answerVideo!=null}">
				                              <p class="uploadMediaWrap mt20">
										        <a href="javaScript:;" class="btn btnUpLoad uploadBox"   onclick="playVideo('${que.answerVideo}','${que.examQuestionId}');">答题音视频</a>
									          </p>
				                            </c:if>
					                      </li>
					                      <li>
					                        <span class="fontBold"> 难易度：
		                                   <span class="mr40"> 
		                                 	  <c:if test='${que.difficulty eq "EASY"}'>
					                                                                               容易
					                          </c:if>
					                          <c:if test='${que.difficulty eq "LITTLE_EASY"}'>
					                                                                                较容易
					                          </c:if>
					                          <c:if test='${que.difficulty eq "NORMAL"}'>
					                                                                                 一般
					                          </c:if>
					                          <c:if test='${que.difficulty eq "LITTLE_DIFFICULT"}'>
					                                                                                 较难
					                          </c:if>
					                          <c:if test=" ${que.difficulty eq 'DIFFICULT'}">
					                                                                                  难
					                          </c:if> 
		                                 </span>     
				                     </span><span class="fontBold"> 分值：<span class="mr40">${que.score}</span></span>				           
					                      </li>
					                    </ul>
					             	</div>    
			                     </c:if>
			                     <div class="teaComments">
			                        <div class="borderBox comments" style="display:block">
				                        <p class="commentsKinds">
					                      <a href="javascript:;" class="active">老师点评</a>
					                    </p>
					                    <div class="pd10 commentContent commentContentQue" data-id="${que.examQuestionResultId}" style="display:block">
				                       		<script id="${que.examQuestionResultId}_comment" name="teacherComment_${que.examQuestionResultId}" type="text/plain" style="height:210px;width:100%"></script>
				                        </div>
				                    </div>
				             	</div>
				             	<c:if test="${que.type eq 'ASK_ANSWER' || que.type eq 'COMPUTING'}">
				             	    <span class="fontBold"> 得分：</span><input class="checkInput" onkeyup="return validateNum(this);" queNum="${status.count}" queScore="${que.score}" style="background:white; width:45px; height:25px;" type="text" name="getScore_${que.examQuestionResultId}"/>&nbsp;&nbsp;<span>该道题满分为${que.score}分，老师打分不能大于该分值</span>
				             	</c:if>
				             	<c:if test="${que.type ne 'ASK_ANSWER' && que.type ne 'COMPUTING'}">
				             	   <input style="background:white; width:45px; height:25px;" type="hidden" name="getScore_${que.examQuestionResultId}"/>
				             	</c:if>
					        	</div>
					        </div>
		     				<c:if test="${status.last}">
			     				</div>
			     			</c:if>
				     	</c:forEach>
	                </div>
			  	</c:if>

              <p class="center mt40">
                <input type="submit" class="btn btnSearch nextStep" value="提交"/>
              </p>
          </div>
       </div>
    </div>
   
</form>

</body>
<script type="text/javascript">
var commentResult = {};
$(function(){
	$(".commentContentQue").each(function(){
		var queId = $(this).attr("data-id");
		commentResult[queId] = UE.getEditor(queId+"_comment");
	});
});
 
	function playVideo(videoPath,id){//点击播放视频

 		var index1=videoPath.lastIndexOf(".");
 		var index2=videoPath.length;
 		var postf=videoPath.substring(index1,index2);//获取文件后缀名
 		
 		var showClassId="watch_play"+id;
		var fileurl=API_PATH+"/Video/"+videoPath;
		
		
		//如果添加的音视频数目大于1则停止向下追加
		var showClassIndex="#"+showClassId;
		if($(""+showClassIndex+" audio").size()>=1){
			Win.alert("音频正在播放，请不要继续点击!");
			return;	
		}

 		//alert("postf="+postf);
 		if(postf.indexOf("mp3")>0){
 			//使用ajax获取本音频的据对路径，通过url
 			//http://172.17.54.4/public/Hair.mp3
          //alert("videoPath="+videoPath);
 		  $("#"+showClassId).append('&nbsp;&nbsp;&nbsp;<audio src="${root}/videos/'+videoPath+'" autoplay loop  controls></audio>'); 
					
 			
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
	
	$(
			function(){
				
				new BasicCheck({
					form: $id("comentForm"),
					
					ajaxReq : function(){
						
						var scoreArr=$(".checkInput");
						if(scoreArr.length>0){
							for(var i=0; i<scoreArr.length; i++){
								var scoreValue=$(scoreArr[i]).val();
								var queNum=$(scoreArr[i]).attr("queNum");
								if(""==scoreValue){
									Win.alert("第"+queNum+"题未给分！");
									return false;
								}else if(isNaN(scoreValue)){
									Win.alert("第"+queNum+"题输入的分值不合法!");
									return false;
								}else{
									var score=parseFloat($(scoreArr[i]).attr("queScore"));
									var getScore=parseFloat(scoreValue);
									if(getScore>score){//如果给的分数大于本题总分
										Win.alert("第"+queNum+"题所给分值超过了本题分值!");
									return false;
									}
								}
								
							}
						}
						
						
						  $.post('${root}/teacherTest/updatestucoment.do',$("#comentForm").serialize(),function(r){
							  try {
				                	console.log(r.result);
				                    if(!r.result){
				                        Win.alert('操作失败！');
				                    }else{
				                    		parent.Win.alert('操作成功!');
				                    		document.location.href = "${root}/teacherTest/readOverOnLineTest/${examTaskId}.html";//跳转到批阅列表页面
				                    }
				                } catch(e) {
				                    Win.alert("错误提示:"+e.message);
				                }
				            });
					},
					warm: function warm(o, msg) {
						Win.alert(msg);
					} 
				});
			}
	)
	
	
	//给分值的时候随时对输入的分值进行校验	
	          function validateNum(e){
					e.value=e.value.replace(/\D/g,'');
					 var queValue =$(e).val();
					 var qScore =parseFloat($(e).attr("queScore"));
					  var score=parseFloat(queValue);
					  if(score>qScore){
						  Win.alert("输入的分值超过了本题分值!");
						  $(e).val("");
							return false;
					  }
				}
	
</script>
</html>