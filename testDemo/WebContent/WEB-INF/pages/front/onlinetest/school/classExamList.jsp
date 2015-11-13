<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>

<style>
.testWithLabel .examDesc{width:800px; display:inline-block; line-height:1.8; }
</style>
</head>
<body class="mainIndex">
    <%@include file="../../../common/topHeader.jsp"%>
    <%@include file="../../../common/examNav.jsp"%>
   
    <div class="container w1200 marginauto bkgNone clearfix">
       	<div class="content">
       	  <div class="content-in">
       	    <div class="borderBox gn-bgWhite pd20">
  			
				 <ul class="commonUL gn-searchCondition gn-blueSort">
			    	<li id="arrangeLi">
			    		 <label class="text gn-labelText gn-blueBracket"><b>布置时间：</b></label>
			    		 <span class="cont gn-sortBox clearfix gn-containInput">
						  <input id="beginTime" needcheck nullmsg="请输入布置开始时间" type="text" class="Wdate" name="beginTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'});" value="" />  
			    		      至   
			              <input id="endTime" needcheck nullmsg="请输入布置结束时间" type="text" class="Wdate" name="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginTime\')}'});" value="" />  
			    		 </span>
			    	</li>
			    	<li id="subjectLi">
			    		 <label class="text gn-labelText gn-blueBracket"><b>学科：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	  <c:forEach items="${subjects }" var="subjects">
								<a href="javascript:;" id="${subjects.id }">${subjects.name }</a>
							  </c:forEach>
			    		 	  
			    		 	</span>
			    		 </span>
			    	</li>
			    	<li id="classlevelLi">
			    		 <label class="text gn-labelText gn-blueBracket"><b>年级：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <c:forEach items="${classes }" var="classes">
								<a href="javascript:;" id="${classes.id }">${classes.name }</a>
							  </c:forEach>
			    		 	</span>
			    		 </span>
			    	</li>
			    	<li id="classLi">
			    		 <label class="text gn-labelText gn-blueBracket"><b>班级：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   
			    		 	</span>
			    		 </span>
			    	</li>
			    	
			    	<li id="statusLi">
			    		 <label class="text gn-labelText gn-blueBracket"><b>状态：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	    <a href="javascript:;" id="INIT">未开始</a>
								<a href="javascript:;" id="PROGRESS">进行中</a>
								<a href="javascript:;" id="END">已结束</a>
								
			    		 	</span>
			    		 </span>
			    	</li>
			    	
			    	<li id="examTypeLi">
			    		 <label class="text gn-labelText gn-blueBracket"><b>试卷类型：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a class="selected gn-sortAll fl" href="javascript:;" id="0">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <c:forEach items="${examTypes }" var="examTypes">
			    		 	     <a href="javascript:;" id="${examTypes.id }">${examTypes.name }</a>
			    		 	   </c:forEach>
			    		 	</span>
			    		 </span>
			    	</li>
			    	
			    	<li id="examNameLi">
			    		 <label class="text gn-labelText gn-blueBracket"><b>试卷名称：</b></label>
			    		 <span class="cont gn-sortBox clearfix gn-containInput">
							 <input type="text" name="examName" id="examName"/>
							 <input id="searchId" type="button" class="btn btnBlue ml20" value="搜索"/>
			    		 </span>
			    	</li>
			    </ul>
			  </div>
			  </div>
			    
			     <div class="gn-main mt30 clearfix pl10 pr10" id="pageBody">
			         
			     </div>
			     <div class="pageNavi" id="pager">
			      </div>
			   </div>
			  </div>
			  
       	  
       
  
    <script type="text/javascript">
     var user_schoolId = "${sessionScope.SESSION_USER.schoolId}";	<%-- 当前登录用户学校id --%>
     $(document).ready(function(){
    	getClassTestData(user_schoolId);

    	$('#subjectLi,#classlevelLi,#classLi,#statusLi,#examTypeLi').on('click', 'a', function () {<%-- 年级/学科的选择事件 --%>
    	    $(this).parents('li').find('a').removeClass('selected');
    		$(this).addClass('selected');
    		getClassTestData(user_schoolId);
    	});
    	
    	
     });
     
     //通过年级选择班级
     $("#classlevelLi").on("click","a",function(){
 		var $elem = $(this);
 		var classlevelId = $elem.attr("id");
 		if(classlevelId && classlevelId != '0'){
 			bindClassroomByClasslevelId(classlevelId, user_schoolId);
 		}
 		if(classlevelId == '0'){
 		    $(".bclass").remove();
 		}
 	});
     
     //获取班级
     function bindClassroomByClasslevelId(classlevelId, user_schoolId){
    	 $(".bclass").remove();
    	 $.post('${root}/commons/getClassByClasslevelId.do', {classlevelId:classlevelId, user_schoolId:user_schoolId}, 
    	   function(data){
    		 if(data != null && data.length > 0){
    			 var html = '';
    			 for(var i = 0; i < data.length; i++){
    				 var obj =  data[i];
 					 html += '<a href="javascript:;" id="'+obj.id+'" class="bclass">'+obj.name+'</a>';
    			 }
    		
    			 $("#classLi a.selected:last").after(html);
    		 }
    	 }, 'json');
     }
     
     
      //处理搜索按钮
      $('#searchId').on('click', function () {
           getClassTestData(user_schoolId);
      });
      
      var splitPage ;
      var url = "${root}/schoolTest/getClassExamList.do";
      function getClassTestData(user_schoolId){ <%--获取班级测试条件 --%>
    	  
          var subjectId = $("#subjectLi a.selected:last").attr("id");
    	  var classlevelId = $("#classlevelLi a.selected:last").attr("id");
    	  var classId = $("#classLi a.selected:last").attr("id");
    	  var status = $("#statusLi a.selected:last").attr("id");
    	  var examTypeId = $("#examTypeLi a.selected:last").attr("id");
    	  var examName = $.trim($("#examName").val());
    	  var beginTime = $("#beginTime").val();
    	  var endTime = $("#endTime").val();
    	  
    	  var data = {
    			  
    			subjectId     :  subjectId,
    			classlevelId  :  classlevelId,
    			classId       :  classId,
    			status        :  status,
    			examTypeId    :  examTypeId,
    			examName      :  examName,
    			beginTime     :  beginTime,
    			endTime       :  endTime,
    			user_schoolId :  user_schoolId
    			
    	  };
    	  
    	  var config = {
    				node:$id("pager"),
    				url:url,
    				count : 10,
    				type :"post",
    				callback : resultClassTestData,
    				data:data
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
  				if(task.examCategoryType == 'CLASSLEVEL'){
  					taskHtml +='<div class="borderBox pl40 testWithLabel withLabel" id='+i+'>';
   				}else{
   					taskHtml +='<div class="borderBox pl40 testWithLabel" id='+i+'>';
   				}
  				taskHtml += '<h4 class="examTitle"><a href="${root}/schoolTest/viewClassExam/'+task.examTaskId+'.html" target="_blank">'+task.title+'</a></h4>';
  				taskHtml += '<div class="frBtnWrap verticalMiddle clearfix">';
  				taskHtml += '<div class="examDesc">学科：<span>'+task.subjectName+'</span>'
                         +'年级：<span>'+task.classlevelName+'</span>'
                         +'试卷类型：<span>'+task.examTypeName+'</span>'
                         +'答题时长：<span>'+task.answerTime+'分钟</span>'
                         +'试卷总分：<span>'+task.score+'分</span><br/>'
                         +'测试人数：<span><em class="red">'+task.finishedCount+'</em>/'+task.assignedCount+'</span>'
                         +'开始时间：<span>'+task.startTime+'</span>'
                         +'完成时间：<span>'+task.finishedTime+'</span></div>';
               
                if(task.status == 'INIT' && task.examCategoryType == 'CLASSLEVEL'){
                  taskHtml += '<a href="javascript:;" class="examState threeItem" eid='+task.status+'>未开始</a>'
                  taskHtml += '<a href="javascript:;" class="check threeItem c999">统计</a>';
                  taskHtml += '<a href="javascript:;" class="delExamTask red" id='+task.examTaskId+'>删除</a>';
                }
                
                if(task.status == 'INIT' && task.examCategoryType != 'CLASSLEVEL'){
                	 taskHtml += '<a href="javascript:;" class="examState threeItem" eid='+task.status+'>未开始</a>'
                     taskHtml += '<a href="javascript:;" class="check threeItem c999">统计</a>';
                }
                
                if(task.status == 'PROGRESS'){
                  taskHtml += '<a href="javascript:;" class="examState threeItem" eid='+task.status+'>进行中</a>'
                  taskHtml += ' <a href="javascript:;" class="check threeItem c999">统计</a>';
                }
                
                if(task.status == 'END'){
                  taskHtml += '<a href="javascript:;" class="examState threeItem" eid='+task.status+'>已结束</a>'
                  taskHtml += '<a href="${root}/schoolTest/toClassExamAnalyze/'+task.examTaskId+'.html" target="_blank" class="examState threeItem">统计</a>';
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
	$(document).on('click', '.delExamTask', function (event) {
		var $elm = $(this);
		Win.confirm({id:"delExamTask",html:"您确定要删除该测试吗？"},function(){
			$.post("${root}/schoolTest/delExamTask.do",{"examTaskId":$elm.attr("id")},function(data){
				console.log(data);
				if(data == 1){
					Win.alert("删除成功！");
					getClassTestData(user_schoolId);
				}else{
					Win.alert("删除失败");
				}
			}); 
		},true);
	})
   
    	
    
    
   </script>
</body>
</html>