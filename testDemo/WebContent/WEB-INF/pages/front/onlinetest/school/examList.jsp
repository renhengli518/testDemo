<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
</head>
<body class="mainIndex">
     <%@include file="../../../common/topHeader.jsp"%>
     <%@include file="../../../common/examNav.jsp"%>
     <div class="container w1200 marginauto bkgNone clearfix">
       <div class="content">
	       <p class="uploadContainer">
	  	     <span class="fr">
			   <a href="${root}/schoolTest/toCreateExam.html" target="_blank" class="btn btnUpload mr20" >组建试卷</a>
			   <a href="javascript:uploadClazExam();" class="btn btnUpload" >上传试卷</a>
		    </span>
		   </p>
          <div class="content-in">
			  <div class="borderBox gn-bgWhite pd20">
				 <ul class="commonUL gn-searchCondition">
				   <li>
						<label class="text gn-labelText"><b>更新时间：</b></label>
						<span class="cont gn-sortBox clearfix gn-containInput">
							<input id="beginTime" type="text" class="Wdate" name="beginTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'});" value="" />  
							至
							<input id="endTime"  type="text" class="Wdate" name="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginTime\')}'});" value="" />  
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
	    var user_schoolId = "${sessionScope.SESSION_USER.schoolId}";	<%-- 当前登录用户学校id --%>
		$(document).ready(function(){
    	
    	  getExamData(user_schoolId);
 
     	  $('#subjectLi,#classlevelLi,#classroomLi,#statusLi,#examTypeLi').on('click', 'a', function () {<%-- 年级/学科的选择事件 --%>
     	    //$(this).addClass('selected').siblings().removeClass('selected');
     	    $(this).parents('li').find('a').removeClass('selected');
     		$(this).addClass('selected');
     		getExamData(user_schoolId);
    	 
          });
	   });
     	
        //处理搜索按钮
        $('#searchId').on('click', function () {
           getExamData(user_schoolId);
        });
        
        
        //删除试卷
    	$(document).on('click', '.delexam', function (event) {
    		var $elm = $(this);
    		Win.confirm({id:"delExamId",html:"您确定要删除该试卷吗？"},function(){
    			$.post("${root}/schoolTest/delExam.do",{"examId":$elm.attr("eid")},function(data){
    				console.log(data);
    				if(data == 1){
    					Win.alert("删除成功！");
    					getExamData(user_schoolId);
    				}else{
    					Win.alert("删除失败");
    				}
    			}); 
    		},true);
    	})
    	
    	//布置试卷
    	$(document).on('click', '.bzexam', function (event) {
    		var $elm = $(this);
    		Win.open({
				title : "布置试卷",
				width : 520,
				height : 570,
				url : "${root}/schoolTest/selectClassPre.do?examId="+$elm.attr("eid")
			});
    	})
    	
    	
    	
    	
		var splitPage ;
        var url = "${root}/schoolTest/getExamList.do";
        
		function getExamData(user_schoolId){ <%--获取班级测试条件 --%>
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
      			endTime      : endTime	  
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
     				taskHtml += '<div class="borderBox pl40 testWithLabel" id='+i+'>';
    				taskHtml += '<h4 class="examTitle"><a href="${root}/schoolTest/viewExam/exam/'+task.examId+'.html" target="_blank">'+task.title+'</a></h4>';
    				taskHtml += '<div class="frBtnWrap verticalMiddle clearfix">';
    				taskHtml += '<div class="examDesc">更新时间：<span>'+task.createTime+'</span>'
    					   +'学段：<span>'+task.semesterName+'</span>'
    					   +'年级：<span>'+task.classlevelName+'</span>'
    				       +'学科：<span>'+task.subjectName+'</span>'
                           +'试卷类型：<span>'+task.examTypeName+'</span><br/>'
                           +'答题时长：<span>'+task.answerTime+'分钟</span>'
                           +'试卷总分：<span>'+task.score+'分</span>'
                           +'题量：<span class="fontNormal">'+task.questionCount+'题</span>'
                           +'</div>';
                       taskHtml += '<a href="${root}/schoolTest/editExam/exam/'+task.examId+'.html" target="_black" class="examState threeItem" id='+task.status+' eid='+task.examId+'>编辑</a>';
                       taskHtml += '<a href="javascript:;" class="examState bzexam threeItem" id='+task.status+' eid='+task.examId+'>布置</a>';
                       if(task.startnum == 0){
	                       taskHtml += '<a href="javascript:;" class="examState delexam  threeItem red" eid='+task.examId+'>删除</a>';
                       }
                    taskHtml += '</div></div>';  
    			}
                  
    			html += taskHtml;
    			$("#pageBody").html(html);
    		}else{
    			$("#pageBody").html('<div class="borderBox pl40 testWithLabel align="center">抱歉，未查询到相关记录!</div>');
    		}
    	}else{
    		$("#pageBody").html('<div class="borderBox pl40 testWithLabel align="center">抱歉，未查询到相关记录!</div>');
    	}
    }
   
   
   
      function uploadClazExam (){
 		 Win.open({id:'uploadQuestion',width:500,height:520,title:"导入年级统考试卷",url:"${root}/questionLib/uploadQuestionPage.do?type=clazexam"});
 	}
   
   </script>
</body>
</html>