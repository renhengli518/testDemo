<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.mainLeft .NavTableTitle{margin:0; margin-bottom:-5px}
</style>
<!-- 知识点选题 --> 
<div class="clearfix floatWrap withBorderTop select-exercise-box" style="border-top-color:#d5f2df;display:block;" id="box1">
                <div class="mainLeft">
                  <div class="leftContent bkgWhite mt10">
                  	<div class="leftSection gn-KnowledgePointer" ></div>
                     <div class="leftSection" id="box1">
                       <c:if test="${isedit == null }">	
                       	<%@ include file="qtab.jsp"%>
                       </c:if>
                    </div>
                  </div>
                </div>
                <div class="mainRight1 bkgWhite">
                   <div class="borderBox pd20 criteria">
                      <ul class="commonUL gn-searchCondition gn-coffeeSort mt20">
				    	 <li id="typeLi">
				    		<label class="text gn-labelText gn-coffeeBracket"><b>类型</b></label> 
							<span class="cont gn-sortBox clearfix"> 
								<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a> 
								<span class="gn-sortKinds gn-overflowHidden "> 
									<a href="javascript:;" id="SINGLE_CHOICE">单选题</a> 
									<a href="javascript:;" id="MULTI_CHOICE">多选题</a> 
									<a href="javascript:;" id="JUDEMENT">判断题</a> 
									<a href="javascript:;" id="FILL_IN_BLANK">填空题</a>
									<a href="javascript:;" id="ASK_ANSWER">问答题</a> 
									<a href="javascript:;" id="COMPUTING">计算题</a>
								</span>
							</span>
				    	 </li>
				    	 <li id="difficultyLi">
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
					<p class="quesNum clearfix">共计<span id="totalCnt">0</span>题（提示：点击题目内容即可查看到内容和解析内容） <span class="fr">
					 <label class="mr20">习题种类： <select
							id="relationalIndicator" class="w100" onchange="searchQuestions1()">
								<option value="0">全部</option>
								<option value="TWINS">包含孪生题</option>
								<option value="EXTEND">包含衍生题</option>
						</select>
					</label>
					</span>
					</p>
                   <div class="mainRight1 borderBox testCon testConLittleMargin" id="choice1">
                   </div>
                   <div id="pageNavi"></div>	
                </div>
              </div>
<script type="text/javascript">
	function initKnowledge(){
		console.log("初始化知识点选题数据");
		console.log(semesterId+"***"+subjectId);
		var param = {
    			semesterId:semesterId,
    			subjectId:subjectId
    	};
    	$.post('${root}/commons/getRootKnowledgeViewBySemsterSubjectId.do', param, function (data) {	
    		var html = '<ul class="tree">';
			if(data != null && data.length > 0){				
				for(var i = 0; i < data.length; i++){
					var obj =  data[i];					
					html += "<li class=\"node-0\" onclick=\"getKLGChildren('"+obj.baseKnowledgeId+"', 0, this, event,"+obj.hasKnowledgeChild+")\" >";
					html += '<span class="klg-node" id="'+obj.baseKnowledgeId+'" value="'+obj.baseKnowledgeId+',0,0,0,0,0" >'+obj.knowledgeName+'</span>';
					html += '</li>';
				}				
			}
			html += '</ul>';
			$(".gn-KnowledgePointer").html(html); 
		}, 'json');
		
    	searchQuestions1();
	}
	
	//获取知识点树
	function getKLGChildren(id, nodeNumber, currentNode, e,hasChild) {
		$(".gn-KnowledgePointer .klg-node").removeClass("selected").css({'background-color':'transparent'});
		var txt = currentNode.getElementsByTagName("span");
		$(txt[0]).addClass("selected").css({'background-color':'#f4f8fb'});
		searchQuestionsByKnowledge();
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
				bindSubKnowledageByParentId(currentNode,id,nodeNumber);
			};
		};
	}
	
	// 查询共享题库列表
	function searchQuestionsByKnowledge() {
		var url  = '${root}/questionLib/searchShareList.do';
		var baseSemesterId = $("#semesterLi").find(".selected").prop("id");
		var baseClasslevelId = $("#classlevelLi").find(".selected").prop("id");
		var baseSubjectId = $("#subjectLi").find(".selected").prop("id");
		var baseVersionId;
		var chapterIdStr;
		if(!chapterIdStr){
			chapterIdStr = '0,0,0';
		} 
		var knowledgeIdStr = $(".gn-KnowledgePointer .selected").attr("value");
		if(!knowledgeIdStr){
			knowledgeIdStr = '0,0,0,0,0,0';
		}
		var type = $("#typeLi").find(".selected").prop("id");
		var difficulty = $("#difficultyLi").find(".selected").prop("id");
		var relationalIndicator = $("#relationalIndicator").val();
		var auditStatus = $("#auditStatus").val();
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
		 var mySplit = new SplitPage({
		        node : $id("pageNavi"),
		        url : url,
		        data :  param,
		        count : 10,
		        callback : formQuestionListKnowledge,
		        type : 'get' //支持post,get,及jsonp
		    });			 
	
		 mySplit.search(url,param);
		
	}
	
	function formQuestionListKnowledge(data,totalCnt){
		formQuestionListCommon(data,totalCnt,'#choice1','#totalCnt');
	}
	
function bindSubKnowledageByParentId(pNode,parentId,nodeNumber){
    	
    	$.post('${root}/commons/getChildKnowledgeViewByParentId.do', {"parentId":parentId}, function (data) {	
    		var html = '<ul>';
    		var currentNodeNum = (nodeNumber+1);
			if(data != null && data.length > 0){				
				for(var i = 0; i < data.length; i++){
					var obj =  data[i];
					var cls = (currentNodeNum>=3?'close':'');
					var hasChild = obj.hasKnowledgeChild;					
					
					html += "<li class='node-"+currentNodeNum+"' ";
					html += "onclick=\"getKLGChildren('"+obj.baseKnowledgeId+"',"+currentNodeNum+", this, event,"+obj.hasKnowledgeChild+")\" ";
					html += ">";
					
					var pVal = $(pNode.getElementsByTagName("span")[0]).attr("value");					
					var pArr = pVal.split(",");
					pArr[currentNodeNum] = obj.baseKnowledgeId;					
					
					html += '<span class="klg-node" id="'+obj.baseKnowledgeId+'" value="'+pArr.join()+'" >';
					
					if(hasChild){
					  html += '<i class="dot '+cls+'"></i>';
					}					
					html += obj.knowledgeName;
					html += '</span>';
					html += "</li>";
				};				
			}
			html += '</ul>';
			pNode.innerHTML += html;				
		}, 'json');
    }
    
$('.criteria').on('click', 'a', function () {
	$(this).parents('li').find('a').removeClass('selected');
	$(this).addClass('selected');
	searchQuestions1();
});

//知识点选题
function searchQuestions1() {
	console.log("知识点查询");
	var url  = '${root}/questionLib/searchShareList.do';
	var baseSemesterId = $("#semesterOption").val();
	var baseClasslevelId;
	var baseSubjectId = $("#subjOption").val();
	var baseVersionId;
	var chapterIdStr;
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
	var knowledgeIdStr = $(".gn-KnowledgePointer .selected").attr("value");
	if(!knowledgeIdStr){
		knowledgeIdStr = '0,0,0,0,0,0';
	}
	var type = $("#typeLi").find(".selected").prop("id");
	var difficulty = $("#difficultyLi").find(".selected").prop("id");
	var relationalIndicator = $("#relationalIndicator").val();
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
	 var mySplit = new SplitPage({
	        node : $id("pageNavi"),
	        url : url,
	        data :  param,
	        count : 10,
	        callback : formQuestionListKnowledge,
	        type : 'get' //支持post,get,及jsonp
	    });			 

	 mySplit.search(url,param);
	
}

</script>
