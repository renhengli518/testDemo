<%@ page contentType="text/html; charset=UTF-8"%>
<!-- 同步章节选题 --> 
<div class="clearfix floatWrap withBorderTop select-exercise-box" style="border-top-color:#d5f2df;display:none" id="box2">
               <ul class="commonUL gn-searchCondition gn-coffeeSort mb20 criteria2">
				    <li id="versionLi">
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>教材版本：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		    <a href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	  <c:forEach items="${versions}" var="versions">
										<a href="javascript:;" id="${versions.id }">${versions.name }</a>
							  </c:forEach>
			    		 	</span>
			    		 </span>
			    	 </li>
			    	 <li  id="classlevelLi">
			    		 <label class="text gn-labelText  gn-coffeeBracket"><b>年级：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		    <a href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	    <c:forEach items="${classes }" var="classes">
										<a href="javascript:;" dir="${classes.parentId }" id="${classes.id }">${classes.name }</a>
								</c:forEach>
			    		 	</span>
			    		 </span>
			    	 </li>
			     </ul>
                <div class="mainLeft">
                  <div class="leftContent bkgWhite mt10">
                    <div class="leftSection gn-ChapterPointer">
                    </div>
                     <div class="leftSection" id="box2">
                     	<c:if test="${isedit == null }">
                        <%@ include file="qtab.jsp"%>
                        </c:if>
                    </div>
                  </div>
                </div>
                <div class="mainRight1 bkgWhite">
                   <div class="borderBox pd20 criteria2">
                      <ul class="commonUL gn-searchCondition gn-coffeeSort mt20">
				    	 <li id="typeLi2">
							<label class="text gn-labelText gn-coffeeBracket"><b>题型</b></label> 
							<span class="cont gn-sortBox clearfix"> 
								<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a> 
								<span class="gn-sortKinds gn-overflowHidden"> 
									<a href="javascript:;" id="SINGLE_CHOICE">单选题</a> 
									<a href="javascript:;" id="MULTI_CHOICE">多选题</a> 
									<a href="javascript:;" id="JUDEMENT">判断题</a> 
									<a href="javascript:;" id="FILL_IN_BLANK">填空题</a>
									<a href="javascript:;" id="ASK_ANSWER">问答题</a> 
									<a href="javascript:;" id="COMPUTING">计算题</a>
								</span>
							</span>
						</li>
				    	<li id="difficultyLi2">
							<label class="text gn-labelText gn-coffeeBracket"><b>难易度</b></label> 
							<span class="cont gn-sortBox clearfix"> 
								<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a> 
								<span class="gn-sortKinds gn-overflowHidden">
									<a href="javascript:;" id="EASY">容易</a> 
									<a href="javascript:;" id="LITTLE_EASY">较容易</a>  
									<a href="javascript:;" id="NORMAL">一般</a> 
									<a href="javascript:;" id="LITTLE_DIFFICULT">较难</a>
									<a href="javascript:;" id="DIFFICULT">困难</a>
								</span>
							</span>
						</li>
				     </ul>
                   </div>
                   <p class="quesNum clearfix">共计<span id="totalCnt2">0</span>题（提示：点击题目内容即可查看到内容和解析内容） <span class="fr">
					 <label class="mr20">习题种类： <select
							id="relationalIndicator2" class="w100" onchange="searchQuestions2()">
								<option value="0">全部</option>
								<option value="TWINS">包含孪生题</option>
								<option value="EXTEND">包含衍生题</option>
						</select>
					</label>
					</span>
					</p>
                   <div class="mainRight1 borderBox testCon testConLittleMargin" id="choice2">
                   </div>
                   <div id="pageNavi2"></div>	
              </div>
</div>
<script type="text/javascript">
	function initChapter(){
		console.log("初始化章节选题数据");
		console.log(semesterId+"***"+subjectId);
		bindVolumeByClslevelSubjectVerionId();
		
	}
	
	  function bindVolumeByClslevelSubjectVerionId(){
		    var classlevelId = $("#classlevelLi").find(".selected").prop("id");
			var versionId = $("#versionLi").find(".selected").prop("id");
			console.log(classlevelId+"**"+versionId+"**"+subjectId);
			console.log(!subjectId);
			if(classlevelId == '0' || !subjectId || versionId == '0'){
				
				return;	
			}
			/* if(!baseClasslevelId){
				//如果没选班级
				return;
			}
			if(!baseVersionId){
				//如果没选版本
				return;
			}
			if(!subjectId){
				//没有选择学科
				return;
			} */
	    	var param = {
	   			classlevelId:classlevelId,
	   			subjectId:subjectId,
	   			versionId:versionId
	    	};
	    	$.post('${root}/commons/getAllVolumeByClslevelSubjectVerionId.do', param, function (data) {	
	    		var html = '<ul class="tree">';
				if(data != null && data.length > 0){				
					for(var i = 0; i < data.length; i++){
						var obj =  data[i];					
						html += "<li class=\"node-0\" onclick=\"getChapterChildren('"+obj.baseVolumeId+"', 0, this, event,"+obj.hasChapterChild+")\" >";
						html += '<span class="chapter-node" id="'+obj.baseVolumeId+'" value="'+obj.baseVolumeId+',0,0" >'+obj.volumeName+'</span>';
						html += '</li>';
					}				
				}
				html += '</ul>';
				$(".gn-ChapterPointer").html(html); 
			}, 'json');
	    }

	//获取章节树
		function getChapterChildren(id, nodeNumber, currentNode, e,hasChild) {
			$(".gn-ChapterPointer .chapter-node").removeClass("selected").css({'background-color':'transparent'});
			var txt = currentNode.getElementsByTagName("span");
			$(txt[0]).addClass("selected").css({'background-color':'#f4f8fb'});
			searchQuestions2();
			if(e.preventDefault) {
				e.stopPropagation();
				e.preventDefault();
			} else {
				window.event.cancelBubble = true;
				window.event.returnValue = false;
			}

			var nodes = currentNode.getElementsByTagName("ul");

			var exp = new RegExp("node-hide");

			if(nodes && nodes.length>0) {
				if(-1!=nodes[0].className.indexOf("node-hide")) {
					nodes[0].className = nodes[0].className.replace(exp, "");
					var dots = currentNode.getElementsByTagName("i");
					if(nodeNumber>2) dots[0].className = "open";
				} else {
					nodes[0].className = nodes[0].className+" node-hide";
					var dots = currentNode.getElementsByTagName("i");
					if(nodeNumber>2) dots[0].className = "close";
				}
			} else {
				var dots = currentNode.getElementsByTagName("i");
				if(nodeNumber>2 && dots.length >0 ) dots[0].className = "open";
				if(hasChild){
					switch(nodeNumber){
						case 0:
							bindChapterByVolumeId(currentNode,id,nodeNumber);
							break;
						case 1:
							(currentNode,id,nodeNumber);
							break;
					}
				};
			};
		}
	
	
		//章节选题
		function searchQuestions2() {
			console.log("章节查询");
			var url  = '${root}/teacherTest/searchShareList.do';
			var baseSemesterId = $("#semesterOption").val();
			var baseSubjectId = $("#subjOption").val();
			var baseClasslevelId = $("#classlevelLi").find(".selected").prop("id");
			var baseVersionId = $("#versionLi").find(".selected").prop("id");
			var chapterIdStr = $(".gn-ChapterPointer .selected").attr("value");
			if(baseSemesterId == 0){
				Win.alert("请选择学段");
				return;
			}
			if(baseSubjectId == 0){
				Win.alert("请选择学科");
				return;
			}
			if(!chapterIdStr){
				chapterIdStr = '0,0,0';
			} 
			var knowledgeIdStr ;
			if(!knowledgeIdStr){
				knowledgeIdStr = '0,0,0,0,0,0';
			}
			var type = $("#typeLi2").find(".selected").prop("id");
			var difficulty = $("#difficultyLi2").find(".selected").prop("id");
			var relationalIndicator = $("#relationalIndicator2").val();
			var auditStatus;
			var param  = {
					baseSemesterId:baseSemesterId,
					baseClasslevelId:baseClasslevelId,
					baseSubjectId:baseSubjectId,
					baseVersionId:baseVersionId,
					chapterIdStr:chapterIdStr,
					knowledgeIdStr:knowledgeIdStr,
					type:type,
					difficulty:difficulty,
					relationalIndicator:relationalIndicator,
					auditStatus : auditStatus
			};
			 var mySplit2 = new SplitPage({
			        node : $id("pageNavi2"),
			        url : url,
			        data :  param,
			        count : 10,
			        callback : formQuestionListChapter,
			        type : 'get' //支持post,get,及jsonp
			    });			 

			 mySplit2.search(url,param);
			
		}
		
		
		function formQuestionListChapter(data,totalCnt){
			formQuestionListCommon(data,totalCnt,'#choice2','#totalCnt2');
		}
		
		function bindChapterByVolumeId(pNode,volumeId,nodeNumber){
	    	$.post('${root}/commons/getAllChapterViewByVolumeId.do', {volumeId:volumeId}, function (data) {	
				var html = '<ul>';
	    		var currentNodeNum = (nodeNumber+1);
				if(data != null && data.length > 0){				
					for(var i = 0; i < data.length; i++){
						var obj =  data[i];
						var cls = (currentNodeNum>=3?'close':'');
						var hasChild = obj.hasSectionChild;					
						
						html += "<li class='node-"+currentNodeNum+"' ";
						html += "onclick=\"getChapterChildren('"+obj.baseChapterId+"',"+currentNodeNum+", this, event,"+obj.hasSectionChild+")\" ";
						html += ">";
						
						var pVal = $(pNode.getElementsByTagName("span")[0]).attr("value");	
						var pArr = pVal.split(",");
						pArr[currentNodeNum] = obj.baseChapterId;					
						
						html += '<span class="chapter-node" id="'+obj.baseChapterId+'" value="'+pArr.join()+'" >';
						
						if(hasChild){
						  html += '<i class="dot '+cls+'"></i>';
						}					
						html += obj.chapterName;
						html += '</span>';
						html += "</li>";
					};				
				}
				html += '</ul>';
				pNode.innerHTML += html;
			}, 'json');
	    }
	  $('.criteria2').on('click', 'a', function () {
		  	
		  
		  
			$(this).parents('li').find('a').removeClass('selected');
			$(this).addClass('selected');
			searchQuestions2();
			var type = $(this).parents('li').attr("id");
			switch(type){
			  case 'classlevelLi':
			  case 'versionLi':			  
				  $(".gn-ChapterPointer").empty();
				  bindVolumeByClslevelSubjectVerionId(); 
				  break;			  
			};
		});	
</script>
