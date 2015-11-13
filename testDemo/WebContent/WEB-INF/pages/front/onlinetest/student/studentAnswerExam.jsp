<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/quesAndAnswer.css"/>

<script type="text/javascript" src="${PUBLIC_PATH}/public/upload/uploadfile.js"></script>
<script type="text/javascript" src="${PUBLIC_PATH}/public/js/extend.js"></script>
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

<!--   引入答题需要的js   -->
<script type="text/javascript" charset="utf-8" src="${root}/biz/js/stuAnswerExam.js"></script>



<style>
.fixedBox {
	position: fixed;
	top:0;
	margin-top: 0;
}
.stuAnswer .contentRight2{background-color:#f6f6f6;width:258px; border:1px solid #ddd;margin-left:0; float:right; padding:10px; }
.stuAnswer .contentLeft2 {width: 74%;float: left;}
.fillAnswer {border-bottom: 1px solid #ccc;cursor: default;display: inline-block;margin: 0 10px;padding:3px 5px;min-width: 70px;vertical-align:bottom;word-break: break-all;overflow:hidden;}
</style>

<script>
 $(function(){
	 $(".chooseClass").on("click","a",function(){
		 var $this=$(this);
		 $this.addClass("active").siblings().removeClass("active");
	 })
 })
</script>
<script type="text/javascript" src="${root}/biz/js/teacreateExam.js"></script>
</head>
<body class="parOrChildRole">
     <%@include file="../../../common/topHeader.jsp"%>
     <%@include file="../../../common/examNav.jsp"%>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
                <a href="javascript:;">测试任务</a><span class="nextlevel">></span><span class="currentLevel">答题</span>
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox bgNew borderBoxP40">
                  <h3 class="titleOfCheckAnswer clearfix">
                   	 ${examTaskView.title}
                  </h3>
                 <ul class="commonUL gn-searchCondition bkgWhite specialSortBox" style="margin:0 -40px">
			    	 <li>
			    		 <label class="text gn-labelText"><b>年级：</b></label>
			    		 <span class="cont gn-sortBox  clearfix">
			    		   <span class="mr30">${examTaskView.classlevelName}</span>
			    		 学科：<span class="mr30">${examTaskView.subjectName}</span>
			    		<c:if test="${examTaskView.examCategoryType ne 'SELF'}">
				    		 试卷类型：<span class="mr30">${examTaskView.examTypeName}</span>
			    		 	测试人数：<span class="mr30"><span class="red">${examTaskView.finishedCount}</span>/${examTaskView.assignedCount}</span>
			    		</c:if>
			    		<c:if test="${examTaskView.answerTime != null and examTaskView.answerTime != 0}">
			    			 答题时长：<span class="mr30">${examTaskView.answerTime}分钟</span>
			    		</c:if>
			    		<c:if test="${examTaskView.examCategoryType ne 'SELF'}">
			    			 试卷总分：<span class="mr30">${examTaskView.score}分</span>
			    		</c:if>
			    		 </span>
			    		
			    	 </li>
			    	 <c:if test="${examTaskView.examCategoryType ne 'SELF'}">
			    	 <li>
			    		 <label class="text gn-labelText"><b>开始时间：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		   <span class="mr30"><fmt:formatDate value="${examTaskView.startTime}" pattern="yyyy-MM-dd HH:mm"/></span>
			    		   完成时间：<span class="mr30"><fmt:formatDate value="${examTaskView.finishedTime}" pattern="yyyy-MM-dd HH:mm"/></span>
			    		 </span>
			    	 </li>
			    	 </c:if>
			     </ul>
			     <input type="hidden" id="submitFlag" value="0" >
			     <input id="answerTime" type="hidden" value="${examTaskView.answerTime}">
			     
			     <div class="clearfix  stuAnswer">
			       <form id="examForm">
			       <div  class="contentLeft2">
				   <c:set var="title1" value="0"></c:set>
				   <c:set var="title2" value="0"></c:set>
				   <c:set var="title3" value="0"></c:set>
				   <c:set var="title4" value="0"></c:set>
				   <c:set var="title5" value="0"></c:set>
				   <c:set var="title6" value="0"></c:set>
				   <c:set var="questionNo" value="1"></c:set>
				   <c:set var="se" value="${fn:split('A,B,C,D,E,F,G',',')}" />
				   
			       <div class="" style="overflow:hidden">
			         <div class="checkAnswerWrap">
			          <c:forEach var="q" items="${examQuestions}" varStatus="status">
			          
			            <div class="checkAnsweContent testCon"> 
			            <c:if test="${q.type == 'SINGLE_CHOICE' && title1 == 0}">
			              <c:set var="title1" value="1"></c:set>
                          <c:set var="questionNo" value="1"></c:set>
                          <h4 class="quesKind">一、单选题 </h4>
			            </c:if>
			           
			           <c:if test="${q.type == 'MULTI_CHOICE' && title2 == 0}">
                        
                        <c:set var="title2" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h4 class="quesKind">
                           <c:if test="${title1 == 0 }">一</c:if>
						   <c:if test="${title1 == 1 }">二</c:if>

                                                                      、多选题</h4>
                   
                       </c:if>
                   
                      <c:if test="${q.type == 'FILL_IN_BLANK' && title3 == 0}">
                      
                        <c:set var="title3" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h4 class="quesKind">
                           <c:if test="${(title1 + title2) == 0 }">一</c:if>
						   <c:if test="${(title1 + title2) == 1 }">二</c:if>
						   <c:if test="${(title1 + title2) == 2 }">三</c:if>
						 、填空题
                         </h4>
                   
                      </c:if>
			       
			       
			          <c:if test="${q.type == 'JUDEMENT' && title4 == 0}">
                        <c:set var="title4" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h4 class="quesKind">
                           <c:if test="${(title1 + title2 + title3) == 0 }">一</c:if>
						   <c:if test="${(title1 + title2 + title3) == 1 }">二</c:if>
						   <c:if test="${(title1 + title2 + title3) == 2 }">三</c:if>
						   <c:if test="${(title1 + title2 + title3) == 3 }">四</c:if>
						 、判断题
                        </h4>
                   
                   </c:if>
			       
			        <c:if test="${q.type == 'ASK_ANSWER' && title5 == 0}">
                    
                        <c:set var="title5" value="1"></c:set>
					    <c:set var="questionNo" value="1"></c:set>
                        <h4 class="quesKind">
                          <c:if test="${(title1 + title2 + title3 + title4) == 0 }">一</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 1 }">二</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 2 }">三</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 3 }">四</c:if>
						  <c:if test="${(title1 + title2 + title3 + title4) == 4 }">五</c:if>
						 、问答题
                        </h4>
                   
                     </c:if>
			     
			     
			        <c:if test="${q.type == 'COMPUTING' && title6 == 0}">
			      
			          <c:set var="title6" value="1"></c:set>
					  <c:set var="questionNo" value="1"></c:set>	
					  <h4 class="quesKind">
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 0 }">一</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 1 }">二</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 2 }">三</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 3 }">四</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 4 }">五</c:if>
						 <c:if test="${(title1 + title2 + title3 + title4 + title5) == 5 }">六</c:if>
						、计算题
				     </h4>
				  </c:if>
			           
			      <div class="borderBox bkgWhite queDiv pd20" queId="${q.examQuestionId}" queType="${q.type}" queIndex="${status.count }">

			            <c:if test="${q.type == 'SINGLE_CHOICE'}">
			               <div class="quesContent verticalMiddle single" no="${status.count}" eqid="${q.examQuestionId }">
			               <p class="quesTitle">
					        <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}
					       <span class="ml50">
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
					        </span></span>
					                        分值：  <span class="mr40"><b class="fontNormal red">${q.score}</b></span>                
			                </p>
			               
			                
					         <c:forTokens items="${q.options}" delims="∷" var="option" varStatus="opStatus">
					            <label class="choiceLable"><input type="radio" class="schoice" no="${status.count}" optId="${se[opStatus.index] }" name="${q.examQuestionId}"></label>选项${option }<br/>
	                         </c:forTokens>
	                         
			                </div>
			                
			             </c:if>
			             
			         
			              <c:if test="${q.type == 'MULTI_CHOICE'}">
			               <div class="quesContent verticalMiddle multi" no="${status.count}" eqid="${q.examQuestionId }">
			               <p class="quesTitle">
					           <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}
					       <span class="ml50">
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
					       </span></span>
					                        分值：  <span class="mr40"><b class="fontNormal red">${q.score}</b></span>                
			                   
			                </p>
			                 <c:forTokens items="${q.options}" delims="∷" var="option" varStatus="opStatus">
					           <input type="checkbox" class="mchoice" no="${status.count}" optId="${se[opStatus.index]}" name="${q.examQuestionId}">选项${option }<br/>
	                         </c:forTokens>
	                          </div>
			             </c:if>
			             
			             <c:if test="${q.type == 'JUDEMENT'}">
			               <div class="quesContent verticalMiddle judement" no="${status.count}" eqid="${q.examQuestionId }">
			               <p class="quesTitle">
					        <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}
					       <span class="ml50">
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
					        </span></span>
					                        分值：  <span class="mr40"><b class="fontNormal red">${q.score}</b></span>                
			                </p>
			               
			                 
					         <c:forTokens items="${q.options}" delims="∷" var="option" varStatus="opStatus">
					            <label class="choiceLable"><input type="radio" class="jchoice" no="${status.count}" optId="${se[opStatus.index] }" name="${q.examQuestionId}"></label>选项${option }<br/>
	                         </c:forTokens>
			                </div>
			                
			             </c:if>
			             
			      
	 	              <c:if test="${q.type == 'FILL_IN_BLANK'}">
			             <div class="quesContent verticalMiddle fillInBlank" no="${status.count}" eqid="${q.examQuestionId }">
	                       <p class="quesTitle"> 
	                         <p class="fl fz20" id="${status.count}">${status.count}、</p>
						         ${q.content}
				            
					       <span class="ml50">        
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
					       </span></span>
					                        分值：  <span class="mr40"><b class="fontNormal red">${q.score}</b></span> 
					                           
			               </p>
			                </div>
			         </c:if>
			            
			         <c:if test="${q.type == 'ASK_ANSWER'}">
			             <div class="quesContent verticalMiddle answer" no="${status.count}" eqid="${q.examQuestionId }">
			               <p class="quesTitle"> 
			                <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}
	                      
			               <span class=ml50>      
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
					       </span></span>
					                          分值：  <span class="mr40 red">${q.score}</span>
					       
					         <div class="fuwenben mt20">
					           <script class="answers" id="${q.examQuestionId}" type="text/plain" style="height:210px;width:95.4%"></script>
					         </div>
			                  <p class="LH3">
			                   <a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:80px;">
	                                                                                    上传音视频<span id="uploadCont${status.count}" class="uploadCont"> </span>
                               </a>
                               <!-- 上传文件显示区域-->
                                <ul id="uploadInfoBox${status.count}" class="commonUL">
		                        
		                        </ul>
		                     
			                 </p>
					                   
			               </div>
			            </c:if>
			            
			           <c:if test="${q.type == 'COMPUTING'}">
			            <div class="quesContent verticalMiddle computing" no="${status.count}" eqid="${q.examQuestionId }">
			               <p class="quesTitle">
			                  <p class="fl fz20" id="${status.count}">${status.count}、</p>${q.content}
	                        <span class=ml50>      
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
					       </span></span>
					                          分值：  <span class="mr40 red">${q.score}</span>
					        
					         <div class="fuwenben mt20">
					           <script class="computings" id="${q.examQuestionId}" type="text/plain" style="height:210px;width:95.4%"></script>
					         </div>
			                  <p class="LH3">
			                     <a href="javascript:;" class="uploadBox btn" onclick="return false;" style="margin-top:30px;">
	                                                                                       上传音视频<span id="uploadCont${status.count}" class="uploadCont"> </span>
                                 </a>
                                 <!-- 上传文件显示区域-->
                                 <ul id="uploadInfoBox${status.count}" class="commonUL">
                                  
                                 </ul>
                              
		                      </p>
		                      
			               
			                 </div>
			            </c:if>
			          
			         </div>
			        </div>
		          </c:forEach>
			    </div>
			    </div>
			  
			  <div class="center LH3">
			   
			     
			       <input type="hidden" name="answerInfo" id="answerInfo" value="">
			       <input type="hidden" name="examTaskId" id="examTaskId" value="${examTaskView.examTaskId}">
			       <input type="hidden" name="examResultId" id="examResultId" value="${examTaskView.examResultId}">
			       <input type="hidden" name="studentId" id="studentId" value="${sessionScope.SESSION_USER.userId}">
			       <input type="hidden" name="questionResult" id="questionResult"/>
			       <button class="btn markingSub">提交答卷</button>
			   
			  </div>
			  </div>
			  </form>
			
			       <div class="contentRight2" id="fixRight">
			         <h4 class="countDown">倒计时<span class="red ml20" id="divmss">01:59:59</span></h4>
			           
                        <div class="right2Contents">
                            <%@include file="../../../common/numberBoard.jsp"%>
                        </div>
			          </div>
			     </div>
			       </div>
			   </div>
			   	 
			</div>
			
			</div>
	   
      <script type="text/javascript">
       fixedBox.init({
 		"id": "fixRight"
 	    });
    	var subjTxtResult = {};
    	var fillId;//填空题空格Id
    	var fillInAnswerSplitChar = '∷';
    	
    	window.onbeforeunload = function(e) {
       	 	var ev = e?e:window.event;
    		ev.returnValue = "如果刷新或离开页面，内容将会丢失。";
    	} 
    	
    	$(document).ready(function(){
    		var examTaskId = '${examTaskView.examTaskId}';
          
      	/* 富文本框初始化 */
  		<c:forEach var="q" items="${examQuestions}" varStatus="status">
  			if('${q.type}' == 'ASK_ANSWER' || '${q.type}' == 'COMPUTING' ){
  				subjTxtResult['${q.examQuestionId}'] = UE.getEditor('${q.examQuestionId}');
  				subjTxtResult['${q.examQuestionId}'].addListener('contentChange', function(){
  					$("._${q.examQuestionId}").removeClass("selected");
  					if (this.getContent != '' && this.getContent().length > 0){
  						$("._${q.examQuestionId}").addClass("selected");
  					} 
  						
  				});
  				initSwf('${status.count}', '${q.examQuestionId}');
  			}
  		</c:forEach>
  		
  		/* 填空题，空格初始化 */
  		$(".fillInBlank .question-blank-space.edui-faked-music").each(function(index, element){
  			var queId = $(this).parents(".queDiv").attr("queId");
  			var numId = $(this).parent().parent(".fillInBlank").attr("no");
  			var id = queId + "_" + index;
  			var html = '<div id="'+id+'" style="min-height:21px;" class="fillAnswer"  no='+(index+1)+' num='+numId+'></div>';
  			$(this).replaceWith(html);
  		});
  		
  		/*填空题，填写答案*/
  		$(".checkAnswerWrap").on("click",".fillAnswer",function(){
  			fillId = $(this).attr("id");
  			var url = "${root}/studentTest/fillInEditorPopup.html";
  			Win.open({
  				id:fillId+"_Win",
  				title:"输入答案",
  				width: 800,
  				height: 500,
  				url: url
  			});
  		});
  		
  		new BasicCheck({
  			form: $id("examForm"),
  			addition : finishExamining,
  			ajaxReq : submitExamineAsync,
  			warm : function(o,msg){
  				Win.alert(msg);
  			}
  		});
  		//开始倒计时答题
  		daojishi();
     });
    	
    	 var singleArray = new Array();//单选题数
     	 var multiArray = new Array();//多选题数
     	 var judementArray = new Array();//判断题数
     	 var fillArray = new Array();//填空题数
     	 var answerArray = new Array();//问答题数
     	 var compArray = new Array();  //计算题数
        	
         $(function (){
        	$("div.single").each(function(index, element) {
     			singleArray[index] = $(this).attr("no");
     		});
        	
        	 $("div.multi").each(function(index, element) {
     			multiArray[index] = $(this).attr("no");
     		});
        	 
        	 $("div.judement").each(function(index, element) {
        		 judementArray[index] = $(this).attr("no");
      		});
        	 
        	 $("div .fillInBlank").each(function(index, element){
     			fillArray[index] = $(this).attr("no");
     		});
        	 
        	 $("div .answer").each(function(index, element) {
        		 answerArray[index] = $(this).attr("no");
     		});
        	 
        	 $("div .computing").each(function(index, element) {
        		 compArray[index] = $(this).attr("no");
     		});
        	 
         });
     	 
         var vdowntime = $("#answerTime").val();
         if (vdowntime == 0){
     		vdowntime = 99999;
     		$(".countDown").hide();
     	}
      	  var counttime = vdowntime * 60;//总秒钟
        		 
         function daojishi(){
         	    if(counttime>=0){
         	           var ms = counttime%60;//余数 89%60==29秒
         	           var mis = Math.floor(counttime/60);//分钟
         	           if(mis>=60){
         	            var hour=Math.floor(mis/60);
         	   			mis=Math.floor((counttime-hour*60*60)/60);
         	            document.getElementById("divmss").innerHTML=""+hour+":"+mis+":"+ms;
         	           }else if(mis>=1){
         	            document.getElementById("divmss").innerHTML=""+mis+":"+ms; 
         	           }else{
         	            document.getElementById("divmss").innerHTML=""+ms; 
         	           }
         	            counttime--;
         	            vartt =  window.setTimeout("daojishi()",1000);
         			 }else{
         			     window.clearTimeout(vartt);
         			     Win.alert("考试时间结束,已自动提交");
         			     window.setTimeout(function(){
         			    	 autoFinishExamining();
         				 }, 3000);
         			    
         			 }
            }

     	/**
    	*	自动提交试卷,考试时间已到
    	*/
    	function autoFinishExamining() {
    		finishExamining('yes');
    	}
     
    	//提交答题
      	function submitExamineAsync() {
    		var f = submitExamineAsync;
         	submitExamineAsync = function(){};
         	$.post("${root}/studentTest/studentSubmitExam.do", $("#examForm").serialize(),
         		function(data){
         			if(data.result){
         			   window.onbeforeunload = null;
         			   window.location.href = '${root}/studentTest/studentTaskList.html';
         			}else{
         		      Win.alert("答题错误！3秒后自动退出答题。");
         		     submitExamineAsync = f;
  					 /*  window.setTimeout(function(){
  						window.location.href = "javascript:history.back();";
  					 }, 3000); */
         			}
         	    });
         }
    	
    /*******答过题目后题号面板显示特效 ****/
 	 
  	/* 单选题 */
  	$(".schoice").click(function(e) {
  		var el = e.target || e.srcElement; //获取被点击的对象
  		var num = el.getAttribute("no");
  		$("#_"+num).addClass("selected");
  	});
    	
  	/* 多选题 */
  	 $(".mchoice").click(function(e){
  		var el = e.target || e.srcElement; //获取被点击的对象
  		var num = el.getAttribute("no");
  		var i = 0;
  	    if($(el).prop("checked")){
      		$("#_"+num).addClass("selected");    	
  	    }else{
  	        $(el).siblings(".mchoice").each(function(){
  	        	if($(this).prop("checked")){
  	        		i++;
  	        	}
  	        });
  	        if(i < 1){
  	        	$("#_"+num).removeClass("selected");    	
  	        }
  	    	
  	    }
  	});
  	
  	/* 判断题 */
  	$(".jchoice").click(function(e){
  		var el = e.target || e.srcElement; //获取被点击的对象
  		var num = el.getAttribute("no");
  		$("#_"+num).addClass("selected");
  	});

  </script>
  
  <script>
  
  /*********************************上传文件 *********************************************/
  function initSwf(id, examQuestionId){
	  	 var spanJudge="#uploadInfoBox"+id+"";//每个视频，音频只能添加一个
	  	
	     var params= {
	  		         debug : 1,
	  		         fileType : "*.mp4;*.flv;*.mp3;",
	  		         typeDesc : "音视频",
	  		         sizeLimit : 500*1024*1024,
	  		         autoStart : true,
	  		         multiSelect : 0,  //表示每次只能上传一个     1 表示可以进行批量上传
	  		         server: encodeURIComponent("http://localhost:8080${root}/videoUpload")
	  	 }; 
	 	
	 

	  	  window["uploadSwf"+id]= new UploadFile($id("uploadCont"+id), "uploadSwf"+id, "${PUBLIC_PATH}/public/upload/uploadFile.swf",params);
	  	  
	 	
	  	  window["uploadSwf"+id].uploadEvent.add("onComplete",function(){
	  	  	var elm = arguments[0].message[0],
	    	 			data = arguments[0].message[1];
	    	var myProgressBox = $class("progressBox",$id(elm))[0],
	    				myUploadOperate = $class("uploadOperate",$id(elm))[0];
	  	  	myProgressBox.innerHTML = "上传完成!<input type ='hidden' name='answerVideoPath'  value="+JSON.parse(data).message+"><input type ='hidden' name='questionId'  value="+examQuestionId+">";	
	  	  	myUploadOperate.innerHTML = "<span id='watch_play"+id+"'><a style='color:green;' class='watch_play"+id+"'  href='javascript:playVideo(\""+JSON.parse(data).message+"\", \""+id+"\")'>点击播放</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a></span>";
	  	     
	  	  	if($("#uploadInfoBox"+id).children.length > 0){
	  	  		$("#_"+id).addClass("selected");
	  	  	}
	  	 
	  	  
	  	  });
	  	  	 	

	  	 events.delegate($id("uploadInfoBox"+id),".delUploadFile","click",function(){
	  	  	 		//删除
	  	  	 		var e = arguments[0] || window.event,
	  	  				target = e.srcElement || e.target,
	  	  				fileIndex = $("#uploadInfoBox"+id+" li").eq(0).attr("id");
	  	  		    liElm = $id(fileIndex);
	  	  	 		liElm.parentNode.removeChild(liElm);
	  	  	 		
	  	  	  		$("#_"+id).removeClass("selected");
	  	  	  
	  	  });
	  	  	 	
	  	 //显示上传的文件信息
	  	  window["uploadSwf"+id].uploadEvent.add("onOpen",function(){
	  		  
	  	  	 		var elm = arguments[0].message[0],
	  	  	 			info = arguments[0].message[1];
	  	  	 		//对音频进行处理   如果大于100M则不能进行上传操作
	  	  	 		var videoName=info.name;
	  	  	 		var index1=videoName.lastIndexOf(".");
	   		        var index2=videoName.length;
	   		        var postf=videoName.substring(index1,index2);//获取文件后缀名
	   		        if($(spanJudge).children().length >= 1){
	  	  	 			Win.alert("请删除已有的视频才可上传新的视频文件!");
	  					return ;
	  	  	 		}
	   		       
	   		        if(videoName.indexOf("mp3")>0){//表示是音频
	   		            if(info.size>100*1024*1024){
	   		            	
	   		            	Win.alert("上传的音频大小必须在100M以内才可上传!");
	   						 return;
	   		            }
	   		        }else{//表示进行视频上传
	   		        	
	                        if(info.size>500*1024*1024){
	   		            	
	   		            	Win.alert("上传的视频大小必须在500M以内才可上传!");
	   						 return;
	   		            }
	   		        }
	  	  	 		
	  	  	 		if($id(elm)) return false;
	  	  	 		$id("uploadInfoBox"+id).innerHTML += "<li id='"+elm+"'><span class='infoLabel'><b class='fileName mr10' title='"+info.name+"'>"+info.name+"</b><b class='fileSize mr10'></b></span><span class='progressBox mr20'><b class='progressBar mr10'><em class='progressRate'>&nbsp;</em></b>已上传<b class='uploaded'>0%</b></span><span class='uploadOperate'><a class='cancelUpload' href='javascript:;' file='"+elm+"'>取消上传</a></span></li>";
	  	  	 	});
	           
	  	  	 window["uploadSwf"+id].uploadEvent.add("onProgress",function(){
	  	  	  
	  	  	 		var elm = arguments[0].message[0],
	  	  	 			c = arguments[0].message[1],
	  	  	 			t = arguments[0].message[2];
	  	  			var myProgress = $class("progressRate",$id(elm))[0],
	  	  				myUploaded = $class("uploaded",$id(elm))[0];
	  	  			myProgress.style.width = (c/t*100 >>0)+"%";
	  	  			myUploaded.innerHTML = (c/t*100 >>0)+"%";
	  	  	 	
	  	  	 });


	  	  	 window["uploadSwf"+id].uploadEvent.add("noticeSizeError",function(){ //不会执行oncomplete事件
	  	  	 		
	  	  	 		var elm = arguments[0].message[0],
	  	  	 			limite = arguments[0].message[1],
	  	  	 			info =  arguments[0].message[2];
	  	  	 		if($id(elm)) return false;
	  	  	 		Win.alert("上传的文件必须在500M以内方可上传!");
	  	  	 		//$id("uploadInfoBox"+id).innerHTML += "<li id='"+elm+"'><span class='infoLabel'><b class='fileName mr10' title='"+info.name+"'>"+info.name+"</b><b class='fileSize mr10'>"+(info.size/1024/1024).toFixed(2)+"MB</b></span><span class='mr20'>上传文件过大，限制大小"+(limite/1024/1024)+"MB</span><span class='uploadOperate'><a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a></span></li>";
	  	  	 	}); 
	  	  	 	
	  	  	 	window["uploadSwf"+id].uploadEvent.add("onFail",function(){
	  	  	 		var elm = arguments[0].message[0],
	  	  	 			data = arguments[0].message;
	  	  			var myProgressBox = $class("progressBox",$id(elm))[0],
	  	  				myUploadOperate = $class("uploadOperate",$id(elm))[0];
	  	  			myProgressBox.innerHTML = "上传失败！";	
	  	  			myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
	  	  			
	  	  	 	});
	  	  	 	
	  	  	 	events.delegate($id("uploadInfoBox"+id),".cancelUpload","click",function(){
	  	  	 		//取消（单个）上传
	  	  	 		var e = arguments[0] || window.event,
	  	  				target = e.srcElement || e.target,
	  	  				fileIndex = target.getAttribute("file");
	  	  	 		window["uploadSwf"+id].cancelUpload(fileIndex);
	  	  	 	});
	  	  	 	
	  	  	 	window["uploadSwf"+id].uploadEvent.add("onStop",function(){//暂停上传 并可删除
	  	  	 		var elm = arguments[0].message[0];
	  	  	 		var myUploadOperate = $class("uploadOperate",$id(elm))[0];
	  	  	 		myUploadOperate.innerHTML = "<a href='javascript:;' file='"+elm+"' class='delUploadFile'>删除</a>";
	  	  	 	});
	  	  	 		
	  }
	  
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
		        	title : "视频播放窗口",
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