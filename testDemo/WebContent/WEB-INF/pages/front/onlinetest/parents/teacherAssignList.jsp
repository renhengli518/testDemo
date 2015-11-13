<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
</head>
<body class="mainIndex parOrChildRole">
    <%@include file="../../../common/topHeader.jsp"%>
    <%@include file="../../../common/nav.jsp"%>
    
    
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
			    	 <li id="statusLi">
			    		 <label class="text gn-labelText"><b>状态：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
			    		    <a href="javascript:;" class="selected gn-sortAll fl" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	    <a href="javascript:;" id="INIT">未完成</a>
				    		 	<a href="javascript:;" id="SUBMITTED">待批阅</a>
				    		 	<a href="javascript:;" id="CHECKED">已完成</a>
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
     var userId = "${userId}";
     $(document).ready(function(){
    	getClassTestData(userId);

    	$('#subjectLi,#classlevelLi,#statusLi,#examTypeLi').on('click', 'a', function () {<%-- 年级/学科的选择事件 --%>
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
     var url = "${root}/parentTest/getTeacherAssignList.do";
     function getClassTestData(userId){ <%--获取班级测试条件 --%>
   	  var subjectId = $("#subjectLi a.selected:last").attr("id");
   	  var classlevelId = $("#classlevelLi a.selected:last").attr("id");
   	  var status = $("#statusLi a.selected:last").attr("id");
   	  var examTypeId = $("#examTypeLi a.selected:last").attr("id");
   	  var examName = $.trim($("#examName").val());
   	  var beginTime = $("#beginTime").val();
   	  var endTime = $("#endTime").val();
   	 
   	  var data = {
   			subjectId    : subjectId,
   			classlevelId : classlevelId,
   			status       : status,
   			examTypeId   : examTypeId,
   			examName     : examName,
   			beginTime    : beginTime,
   			endTime      : endTime,
   			userId       : userId
   	  }
   	  var config = {
   				node     : $id("pager"),
   				url      : url,
   				count    : 10,
   				type     : "post",
   				callback : resultClassTestData,
   				data     : data
   			};
   	  if(!splitPage) {
   			splitPage = new SplitPage(config); 
   		} else {
   			splitPage.search(url, data) ;
   		}
     }
     
    function resultClassTestData(data,total){<%-- 处理返回结果 --%>
 	  if(total || total != 0){
 		if(data){
 			var html = '';
 			var taskHtml = '';
 			var task;
 			for(var i = 0; i < data.length; i++){
 				task = data[i];
 				if (task.examCategoryType == 'CLASSLEVEL'){
 					taskHtml +='<div class="borderBox pl40 testWithLabel withLabel" id='+i+'>';
  				} else {
  					taskHtml +='<div class="borderBox pl40 testWithLabel" id='+i+'>';
  				}
 				taskHtml += '<h4 class="examTitle">';
 				if (task.status == 'INIT'){
 					taskHtml += '<a href="javascript:;">'+task.title+'</a>';
 				}
 				if (task.status == 'SUBMITTED'){
 					taskHtml += '<a href="${root}/parentTest/viewSubmittedExam/'+task.examTaskId+'/'+userId+'.html" target="_blank">'+task.title+'</a>';
 				}
 				if (task.status == 'CHECKED'){
 					taskHtml += '<a href="${root}/parentTest/viewCheckedExam/'+task.examTaskId+'/'+userId+'.html" target="_blank">'+task.title+'</a>';
 				}
 				taskHtml += '</h4>';
 				taskHtml += '<div class="frBtnWrap verticalMiddle clearfix">';
 				taskHtml += '<div class="examDesc">学科：<span>'+task.subjectName+'</span>'
                         +'试卷类型：<span>'+task.examTypeName+'</span>'
                         +'答题时长：<span>'+task.answerTime+'分钟</span>'
                         +'试卷总分：<span>'+task.score+'分</span>';
                if(task.status == 'CHECKED'){
                	taskHtml += '得分：<span><em class="red">'+task.myScore+'</em>分</span><br/>';
                }else{
                	taskHtml += '<br/>';
                }          
                   
                taskHtml += '测试人数：<span><em class="red">'+task.finishedCount+'</em>/'+task.assignedCount+'</span>'
                         +'开始时间：<span>'+task.startTime+'</span>'
                         +'完成时间：<span>'+task.finishedTime+'</span></div>';
              
               if (task.status == 'INIT'){
                 taskHtml += '<a href="javascript:;" class="examState red" eid='+task.status+'>未完成</a>'
                 taskHtml += '<a href="javascript:;" class="check threeItem c999">查看</a>';
               }
               if (task.status == 'SUBMITTED'){
                 taskHtml += '<a href="javascript:;" class="examState green" eid='+task.status+'>待批阅</a>'
                 taskHtml += '<a href="${root}/parentTest/viewSubmittedExam/'+task.examTaskId+'/'+userId+'.html" target="_blank" class="examState threeItem" id='+task.examTaskId+'>查看</a>';
               }
               if (task.status == 'CHECKED'){
                 taskHtml += '<a href="javascript:;" class="examState green" eid='+task.status+'>已完成</a>'
                 taskHtml += '<a href="${root}/parentTest/viewCheckedExam/'+task.examTaskId+'/'+userId+'.html" target="_blank" class="examState threeItem" id='+task.examTaskId+'>查看</a>';
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
  
     </script>
</body>
</html>