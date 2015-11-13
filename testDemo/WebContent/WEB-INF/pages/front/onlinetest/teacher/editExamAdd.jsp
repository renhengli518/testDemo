<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/meta.jsp" %>
<!--#include virtual="../../common/meta.shtml"-->
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.0/differentRoleStyle.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css" />
<script type="text/javascript" src="${root}/biz/js/teaeditExam.js"></script>
<script type="text/javascript" src="${root}/biz/js/pageNavigate.js"></script>
<script type="text/javascript" src="${root}/biz/js/seteditScore.js"></script>
<!-- <style>
.fixedBox {
		position: fixed;
		top:0;
		margin-top: 0;
 		width: 270px;
  		height: 150px;		
	}
	
</style> -->

</head>
<body class="SynchChapter">
           <div class="bkgWhite pd20 mt20">
	              <div >
	                  <ul class="commonUL">
	                    <li class="black mt10" >
	                      <label class="text">选择习题：</label>
	                      <span class="cont">请选择需要生成试卷的习题</span>
	                    </li>
	                    <li class="mt10">
	                      <label class="text"><span class="red">*</span>选题方式：</label>
	                      <span class="cont noPd quesKinds select-exercise-style">
	                          <a href="javascript:;" class="active " index="0">知识点选题</a>
	                          <a href="javascript:;" index="1">同步章节选题</a>
	                      </span>
	                    </li>
	                  </ul>
	              </div>
					<%@ include file="zj-box/zj-box-1.jsp"%>
					<%@ include file="zj-box/zj-box-2.jsp"%>
              </div>
             
              <p class="center mt40">
                <button class="btn btnSearch nextStep" id="tosetscore" onclick="finishAdd()">完成</button>
              </p>
          </div>
    </div>
   </div>

<script>
	var domid = frameElement.getAttribute("domid");
	var semesterId = '${semesterId}';
	var subjectId = '${subjectId}';
	$(document).ready(function(){
		initKnowledge();
		initChapter();
	});
	
	function finishAdd(){
		 parent.Win.wins[domid].close();
	}
</script>
</body>

</html>