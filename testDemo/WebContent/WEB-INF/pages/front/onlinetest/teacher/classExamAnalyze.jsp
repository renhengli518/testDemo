<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<script>
 $(function(){
	 $(".chooseClass").on("click","a",function(){
		 var $this=$(this);
		 $this.addClass("active").siblings().removeClass("active");
	 })
 })
</script>
</head>
<body class="mainIndex">
     <%@include file="../../../common/topHeader.jsp"%>
     <%@include file="../../../common/examNav.jsp"%>
     <div class="container clearfix w1200 bkgNone marginauto">
       <div class="content">
          <div class="content-in">
              <div class="breadCrumb">
                <a href="javascript:;">测试任务</a><span class="nextlevel">></span><span class="currentLevel">统计 </span>
              </div>
			  <!-- 数据列表 -->
			  <div class="borderBox gn-bgWhite pd20">
                  <h3 class="MarginTitleWithBg clearfix">${examTaskView.title}<button class="btn btnOrange fr exportData" id="exportData">导出数据</button></h3>
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
			     <div class="w1080 marginauto  mt30">
			        <div class="tableWrap marginauto">
				        <h5>班级统计</h5>
				        <table class="newTableStyle" id="statistics">
				           <thead>
				             <tr>
				               <th width="20%">来源</th>
				               <th width="16%">答题人数</th>
				               <th width="16%">最高得分</th>
				               <th width="16%">最低得分</th>
				               <th width="16%">平均得分</th>
				               <th width="16%">通过率</th>
				             </tr>
				           </thead>
				           <tbody>
				             
				           </tbody>
				        </table>
	
				     </div>
				      <div class="chooseClass" id="className">
				          
				      </div>
				      <div class="tableWrap marginauto">
				        <h5>正确率统计</h5>
				       <p class="sortWrap mb10 clearfix">
				         <span class="fr">
				           <a class="newHandle" href="javascript:;" id="sortBtn">按正确率排序</a>
				           <a class="newHandle" href="javascript:;" id="viewDetail">查看详情</a>
				         </span>
				       </p>
				       <div class="statifics">
				         <ul class="correctRate clearfix">
				            <li>
				              <span class="quesNo">题号</span>
				              <span class="theRate">正确率</span>
				            </li>
				           
				         </ul>
				       </div>
	
				     </div>
				     <div class="tableWrap marginauto">
				        <h5>学生统计</h5>
				        <div style="height:506px; overflow-y:auto">
				          <table class="newTableStyle" id="studentStatis">
				             <thead>
					             <tr>
					               <th width="20%">姓名<span class="arrowWrap" id="nameSort"><b class="sortArrow up"></b><b class="sortArrow"></b></span></th>
					               <th width="20%">得分<span class="arrowWrap" id="scoreSort"><b class="sortArrow up"></b><b class="sortArrow"></b></span></th>
					               <th width="20%">答题数<span class="arrowWrap" id="numSort"><b class="sortArrow up"></b><b class="sortArrow"></b></span></th>
					               <th width="20%">正确率（客观题）<span class="arrowWrap" id="rightSort"><b class="sortArrow up"></b><b class="sortArrow"></b></span></th>
					               <th width="20%">操作</th>
					             </tr>
					           </thead>
					           <tbody>
					             
					           </tbody>
					      </table>
				        </div>
	
				     </div>
			     </div>
              </div>
          </div>
       </div>
    </div>
   <script  type="text/javascript">
      $(document).ready(function(){
    	    
    	  $("#sortBtn").attr("flag","0");
    	  
    	 
    	  //班级统计
    	  bindStatistics();
    	  
    	  
    	  // 点击排序
    	  $("#sortBtn").click(function(){
 			 var flag = $(this).attr("flag");
 			
 			 if(flag == 0){
 				formPassRateList("1");
 		        $(this).attr("flag", (flag == '0' ? '1':'0'));
 			    $(this).html((flag == '0' ? '按题号排序':'按正确率排序'));
 			 }else{
 				formPassRateList("0");
 				$(this).attr("flag", (flag == '0' ? '1':'0'));
 			    $(this).html((flag == '0' ? '按题号排序':'按正确率排序'));
 			 }
 			
 		 });
    	  
    	  
    	  //选择班级
    	  $("#className").click(function(){
    		 var flag = $("#sortBtn").attr("flag");
    		
    		 if(flag == 0){
    			 formPassRateList("0");
    		 }else{
    			 formPassRateList("0");
    			 $("#sortBtn").attr("flag", "0");
        		 $("#sortBtn").html((flag == '0' ? '按题号排序':'按正确率排序'));
    		 }
    		 studentStatisticsList("0", "", "", "");
    		 $("#nameSort .up").remove();
    		
    	  });
    	  
    	  
    	  //按姓名排序
    	  $("#nameSort").click(function(){
    		  
    		  //添加答题数箭头
    		  $("#numSort b").remove();
    		  $("#numSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加得分箭头
    		  $("#scoreSort b").remove();
    		  $("#scoreSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加正确率箭头
    		  $("#rightSort b").remove();
    		  $("#rightSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  if($("#nameSort b").hasClass("up")){
    			 if($("#nameSort").children().length == 2){
    				  $("#nameSort .up").remove();
    			 }
    			
    			 studentStatisticsList("0", "", "" ,"");
    			 $("#nameSort b").removeClass("up");
    		  }else{
    			
    			 studentStatisticsList("1", "", "" ,"");
    			 $("#nameSort b").addClass("up");
    		  }
    		  
    	  });
    	  
    	  
    	  //按得分排序
    	  $("#scoreSort").click(function(){
    		  
    		  //添加姓名箭头
    		  $("#nameSort b").remove();
    		  $("#nameSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加答题数箭头
    		  $("#numSort b").remove();
    		  $("#numSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加正确率箭头
    		  $("#rightSort b").remove();
    		  $("#rightSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		 
    		  if($("#scoreSort b").hasClass("up")){
    			  if($("#scoreSort").children().length == 2){
    				  $("#scoreSort .up").remove();
    			  }
    		   
    			  studentStatisticsList("", "0", "", "");
    			  $("#scoreSort b").removeClass("up");
    		  }else{
    			 
        		  studentStatisticsList("", "1", "", "");
        		  $("#scoreSort b").addClass("up");
    		  } 
    	  });
    	  
    	  
    	  //按答题数排序
    	  $("#numSort").click(function(){
    		  
    		  //添加姓名箭头
    		  $("#nameSort b").remove();
    		  $("#nameSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加得分箭头
    		  $("#scoreSort b").remove();
    		  $("#scoreSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加正确率箭头
    		  $("#rightSort b").remove();
    		  $("#rightSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  
    		  if($("#numSort b").hasClass("up")){
    			  if($("#numSort").children().length == 2){
    				  $("#numSort .up").remove();
    			  }
    		   
    			  studentStatisticsList("", "", "0", "");
    			  $("#numSort b").removeClass("up");
    		  }else{
    			
        		  studentStatisticsList("", "", "1", "");
        		  $("#numSort b").addClass("up");
    		  }   
    	  });
    	  
    	  
    	  //按正确率排序
    	  $("#rightSort").click(function(){
    		  
    		  //添加姓名箭头
    		  $("#nameSort b").remove();
    		  $("#nameSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加得分箭头
    		  $("#scoreSort b").remove();
    		  $("#scoreSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  //添加答题数箭头
    		  $("#numSort b").remove();
    		  $("#numSort").html("<b class='sortArrow up'></b><b class='sortArrow'></b>");
    		  
    		  if($("#rightSort b").hasClass("up")){
    			  if($("#rightSort").children().length == 2){
    				  $("#rightSort .up").remove();
    			  }
    		      
    			  studentStatisticsList("", "", "", "0");
    			  $("#rightSort b").removeClass("up");
    		  }else{
    			  
        		  studentStatisticsList("", "", "", "1");
        		  $("#rightSort b").addClass("up");
    		  }   
    	  });
    		  
    	  
    	 //导出数据
    	 $("#exportData").click(function(){
    		 var nameSort = "";
    		 var scoreSort = "";
    		 var numSort = "";
    		 var rightSort = "";
    		 
    		 //获取学生统计中的排序
    		 if($("#scoreSort").children().length == 2 && 
    				 $("#numSort").children().length == 2 && 
    				 $("#rightSort").children().length == 2){
    			 if($("#nameSort b").hasClass("up")){
    				 nameSort = "1";
    			 }else{
    				 nameSort = "0";
    			 }
    		 }
    		 
    		 if($("#nameSort").children().length == 2 && 
    				 $("#numSort").children().length == 2 && 
    				 $("#rightSort").children().length == 2){
    			 if($("#scoreSort b").hasClass("up")){
    				 scoreSort = "1";
    			 }else{
    				 scoreSort = "0";
    			 }
    		 }
    		 
    		 if($("#nameSort").children().length == 2 && 
    				 $("#scoreSort").children().length == 2 && 
    				 $("#rightSort").children().length == 2){
    			 if($("#numSort b").hasClass("up")){
    				 numSort = "1";
    			 }else{
    				 numSort = "0";
    			 }
    		 }
    		
    		 if($("#nameSort").children().length == 2 && 
    				 $("#scoreSort").children().length == 2 && 
    				 $("#numSort").children().length == 2){
    			 if($("#rightSort b").hasClass("up")){
    				 rightSort = "1";
    			 }else{
    				 rightSort = "0";
    			 }
    		 }

    		var classlevel = $(".chooseClass .active").attr("id");
    		var className = $(".chooseClass .active").text();
    		var examTaskId = '${examTaskView.examTaskId}';
    		var flag = $("#sortBtn").attr("flag");
    		var url = "${root}/teacherTest/exportStatisticsData.do";
    		var obj = {
    			classlevel  : classlevel,
    			className   : className,
    			examTaskId  : examTaskId,
    			flag        : flag,
    			nameSort    : nameSort,
    			scoreSort   : scoreSort,
    			numSort     : numSort,
    		    rightSort   : rightSort
    		};
    		$.post(url, obj, function(data){
    			if(data.result){
    				window.location.href = "${root}/teacherTest/exportStatisticsUrl.do";
    			}else{
    				Win.alert("导出失败！");
    			}
    		});
    		
    	 });
    
    	 //查看详情
    	 $("#viewDetail").click(function(){
    	    var examTaskId = '${examTaskView.examTaskId}';
    	    var classlevel = $(".chooseClass .active").attr("id");
    	    if(examTaskId != null && classlevel != null){
    	    	var url =  "${root}/teacherTest/toViewAnalyzeDetail/"+examTaskId+"/"+classlevel+".html";
    	    	window.open (url, "_blank") ;
    	    }
    	   
    	 
    	 });
    	 
    	 
      });
      
      //班级统计
      function bindStatistics(){
    	var examTaskId = '${examTaskView.examTaskId}';
    	var url = "${root}/teacherTest/getExamStatisticsByClass.do";
    	$.post(url, {examTaskId:examTaskId}, function(data){
    		if(data != null && data.length > 0){
                var html = '';
                var chooseClass = '';
                var examStatis;
                for(var i = 0; i < data.length; i++){
    				examStatis = data[i];
                	html += '<tr>';
    			    html += '<td>'+examStatis.classlevelName+''+examStatis.className+'</td>';
    			    html += '<td>'+examStatis.commitCnt+'</td>';
    			    html += '<td>'+examStatis.highestScore+'</td>';
    			    html += '<td>'+examStatis.lowestScore+'</td>';
    			    html += '<td>'+examStatis.avgScore+'</td>';
    			    html += '<td>'+examStatis.passRate+'%</td>';
    			    html += '</tr>';
    			    if (i == 1){
        			 chooseClass += '<a href="javascript:;" class="active" id='+examStatis.classlevelId+':'+examStatis.classId+'>'+examStatis.classlevelName+''+examStatis.className+'</a>';	
    			    } 
    			    if (i > 1){
    			     chooseClass += '<a href="javascript:;" class="" id='+examStatis.classId+'>'+examStatis.classlevelId+':'+examStatis.classId+'</a>';	
    			    }
    			    
                }
             $("#statistics tbody").html(html);
             $("#className").html(chooseClass);
             
             //初始化时， 不会等到ajax 执行完才执执行
             var flag = $("#sortBtn").attr("flag");
       	  	 formPassRateList(flag); 
       	     studentStatisticsList("0", "", "", "");
       	     $("#nameSort .up").remove();
    		}
    	}, 'json');
    	   
      }
      
      //获取正确率统计
      function formPassRateList(flag){
    	  var examTaskId = '${examTaskView.examTaskId}';
    	  var classlevel = $(".chooseClass .active").attr("id");
    	  if(examTaskId != null && classlevel != null){
    	    var url = "${root}/teacherTest/getExamRightStatisByClass.do";
    	    var obj={
               classlevel : classlevel,
                examTaskId : examTaskId,
                flag       : flag
    	     };
    	  $.post(url, obj, function(data){
    		  if(data != null && data.length > 0){
    			 var html = '';
				 var title = '';
				 var colCnt = 15;
				 var cnt = data.length;
				 var loop = (cnt%colCnt == 0 ? cnt :cnt + (colCnt-cnt%colCnt));
				 console.log(cnt+" length");
				 console.log(loop+"  loop"); 
				 
				 for(var i = 0; i < loop; i++){
					
				    if(i == 0 || i%colCnt == 0){
					    title += '<li><span class="quesNo">题号</span>';
					    title += '<span class="theRate">正确率</span></li>';      
				    }
				    
				    if(i < cnt){
				       var obj = data[i];
					   title += '<li><span class="quesNo">'+obj.sort+'</span>';
				       title += '<span class="theRate">'+obj.passRate+'%</span></li>';
				    }else{
				       title += '<li><span class="quesNo">&nbsp;</span>';
					   title += '<span class="theRate">&nbsp;</span></li>';
				    }
				    
				  } 
				  
				  html += title;
			      $(".correctRate").html(html);
    		  }
    	    }, 'json');
    	  
    	  }
      }
      
      //学生统计
      function studentStatisticsList(nameSort, scoreSort, numSort, rightSort){
    	  var classlevel = $(".chooseClass .active").attr("id");
    	  var examTaskId = '${examTaskView.examTaskId}';
    	  var score = '${examTaskView.score}'*0.6;       //获取试卷总分的及格分数
    	if(examTaskId != null && classlevel != null){
    	  var url = "${root}/teacherTest/getStudentStatisList.do";
    	  var obj = {
    		classlevel : classlevel,
    		examTaskId : examTaskId,
    		nameSort   : nameSort,
    		scoreSort  : scoreSort,
    		numSort    : numSort,
    		rightSort  : rightSort
          };
    	  $.post(url, obj, function(data){
    		  if(data != null && data.length > 0){
    			 var html = '';
    			 var student;
    			 for(var i = 0; i < data.length; i++){
    				 student = data[i];
    				 html += '<tr>';
    				 html += '<td>'+student.baseUserName+'</td>';
    				 if(student.score > score){
    					 html += '<td>'+student.score+'</td>';
    				 }else{
    					 html += '<td><span class="red">'+student.score+'</span></td>';
    				 }	
    				 html += '<td><span class="red">'+student.answerCount+'</span>/ '+student.totalCount+'</td>';
    				 html += '<td>'+student.rightRate+'%</td>';
    				 html += '<td><a href="javascript:;" class="" onclick="viewAnswer(\''+student.baseUserId+'\')">查看答题</a></td>';
    				 html += '</tr>';
    			 }
    			 
    		   $("#studentStatis tbody").html(html);
    		   
    		  }
    	  }, 'json');
    	}
      }
      
      //查看答题
      function viewAnswer(baseUserId){
 		 var examTaskId = '${examTaskView.examTaskId}';
 		 var url = "${root}/teacherTest/toViewExamAnswer/"+examTaskId+"/"+baseUserId+".html";
 		 window.open(url, "_blank");
      }
        
      
    </script>
   
</body>
</html>