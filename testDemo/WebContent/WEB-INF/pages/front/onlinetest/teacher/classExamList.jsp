<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
</head>
<body class="mainIndex">
     <%@include file="../../../common/topHeader.jsp"%>
     <%@include file="../../../common/examNav.jsp"%>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
			  <div class="borderBox gn-bgWhite pd20">
				 <ul class="commonUL gn-searchCondition">
				   <li>
						<label class="text gn-labelText"><b>布置时间：</b></label>
						<span class="cont gn-sortBox clearfix gn-containInput">
							<input id="beginTime" needcheck nullmsg="请输入布置开始时间" type="text" class="Wdate" name="beginTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'});" value="" />  
							至
							<input id="endTime" needcheck nullmsg="请输入布置结束时间" type="text" class="Wdate" name="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginTime\')}'});" value="" />  
					    </span>
					</li>
					<li id="classlevelLi">
			    		 <label class="text gn-labelText"><b>年级：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		    <a href="javascript:;" class="selected gn-sortAll fl" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	  <c:forEach items="${classes }" var="classes">
								<a href="javascript:;" id="${classes.id }">${classes.name }</a>
							  </c:forEach>
			    		 	</span>
			    		 </span>
			    	 </li>
				      <li id="subjectLi">
			    		 <label class="text gn-labelText"><b>学科：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		    <a href="javascript:;" class="selected gn-sortAll fl" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	  <c:forEach items="${subjects }" var="subjects">
								<a href="javascript:;" id="${subjects.id }">${subjects.name }</a>
							  </c:forEach>
			    		 	</span>
			    		 </span>
			    	 </li>
			    	 <li id="examTypeLi">
			    		 <label class="text gn-labelText"><b>试卷类型：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		    <a href="javascript:;" class="selected gn-sortAll fl" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <c:forEach items="${examTypes }" var="examTypes">
			    		 	     <a href="javascript:;" id="${examTypes.id }">${examTypes.name }</a>
			    		 	   </c:forEach>
			    		 	</span>
			    		 </span>
			    		  
			    	 </li>
			    	 <li>
						<label class="text gn-labelText"><b>试卷名称：</b></label>
						<span class="cont gn-sortBox clearfix gn-containInput">
							<input type="text" name="examName" id="examName"/>
							<button id="searchId" class="btn btnColorSearch ml20">搜索</button>
					    </span>
					</li>
			     </ul>
			  </div>
			  <div class="gn-main mt30 clearfix pl10 pr10" id="pageBody">
			         
			  </div>
			  <div class="pageNavi" id="pager">
			  </div>
			  
          </div>
       </div>
    </div>
	<script type="text/javascript">
	    var userId = "${sessionScope.SESSION_USER.userId}";  <%-- 当前登录用户id --%>
		$(document).ready(function(){
    	  getClassTestData(userId);
 
     	  $('#subjectLi,#classlevelLi,#examTypeLi').on('click', 'a', function () {<%-- 年级/学科的选择事件 --%>
     	    $(this).parents('li').find('a').removeClass('selected');
     		$(this).addClass('selected');
     		getClassTestData(userId);
    	 
          });
	   });
     	
        //处理搜索按钮
        $('#searchId').on('click', function () {
           getClassTestData(userId);
        });
        
		var splitPage ;
        var url = "${root}/teacherTest/getClassExamList.do";
        
		function getClassTestData(userId){ 
      	  var subjectId = $("#subjectLi a.selected:last").attr("id");
      	  var classlevelId = $("#classlevelLi a.selected:last").attr("id");
      	  var examTypeId = $("#examTypeLi a.selected:last").attr("id");
      	  var examName = $.trim($("#examName").val());
      	  var beginTime = $("#beginTime").val();
      	  var endTime = $("#endTime").val();
      	  
      	  var data = {
      			subjectId    : subjectId,
      			classlevelId : classlevelId,
      			examTypeId   : examTypeId,
      			examName     : examName,
      			beginTime    : beginTime,
      			endTime      : endTime,
      	        userId       : userId
      	  }
      	  var config = {
      				node:$id("pager"),
      				url:url,
      				count : 10,
      				type :"post",
      				callback : resultClassTestData,
      				data:data
      	  };
      	  if (!splitPage) {
      			splitPage = new SplitPage(config); 
      	  } else {
      			splitPage.search(url, data) ;
      	  }
      }
        
      function resultClassTestData(data, total){<%-- 处理返回结果 --%>
    	  if(total || total != 0){
    		if(data){
    			var html = '';
    			var taskHtml = '';
    			var task;
    			for (var i = 0; i < data.length; i++){
    				task = data[i];
    				if (task.examCategoryType == 'CLASSLEVEL'){
    					taskHtml +='<div class="borderBox pl40 testWithLabel withLabel" id='+i+'>';
     				} else{
     					taskHtml +='<div class="borderBox pl40 testWithLabel" id='+i+'>';
     				}
    				taskHtml += '<h4 class="examTitle"><a href="${root}/teacherTest/viewClassExam/'+task.examTaskId+'.html" target="_blank">'+task.title+'</a></h4>';
    				taskHtml += '<div class="frBtnWrap verticalMiddle clearfix">';
    				taskHtml += '<div class="examDesc">布置时间：<span>'+task.createTime+'</span>'
    				       +'年级：<span>'+task.classlevelName+'</span>'
    				       +'学科：<span>'+task.subjectName+'</span>'
                           +'试卷类型：<span>'+task.examTypeName+'</span><br/>'
                           +'答题时长：<span>'+task.answerTime+'分钟</span>'
                           +'试卷总分：<span>'+task.score+'分</span>'
                           +'测试人数：<span><em class="red">'+task.finishedCount+'</em>/'+task.assignedCount+'</span></div>';
                    
                    if (task.status == 1){
                    	taskHtml += '<a href="${root}/teacherTest/readOverOnLineTest/'+task.examTaskId+'.html" class="examState threeItem" id='+task.status+'>批阅</a>';
                    }
                    if (task.status == 0){
                    	taskHtml += '<a href="javascript:;" class="examState threeItem" ></a>';
                        taskHtml += '<a href="${root}/teacherTest/toClassExamAnalyze/'+task.examTaskId+'.html" target="_blank" class="examState threeItem" id='+task.examTaskId+'>统计</a>';
                    }

                    if (task.delFlag == 1 && task.examCategoryType != 'CLASSLEVEL'){
                       taskHtml += '<a class="deleteTask threeItem red" href="javascript:;" eid='+task.examTaskId+'>删除</a>';
                    }
                    
                    taskHtml += '</div></div>';  
    			}
                  
    			html += taskHtml;
    			$("#pageBody").html(html);
    		}else{
    			$("#pageBody").html('<div class="borderBox pl40 testWithLabel center">抱歉，未查询到相关记录!</div>');
    		}
    	}else{
    		$("#pageBody").html('<div class="borderBox pl40 testWithLabel center">抱歉，未查询到相关记录!</div>');
    	}
    }
   
   
    //删除试卷
  	$(document).on('click', '.deleteTask', function (event) {
  		var $elm = $(this);
  		Win.confirm({id:"delTaskId",html:"您确定要删除该测试任务吗？"},function(){
  			$.post("${root}/teacherTest/delExamTask.do",{"examTaskId":$elm.attr("eid")},function(data){
  				if (data == 1){
  					Win.alert("删除成功！");
  					getClassTestData(userId);
  				} else{
  					Win.alert("删除失败");
  				}
  			}); 
  		},true);
  	})
   
   
   </script>
</body>
</html>