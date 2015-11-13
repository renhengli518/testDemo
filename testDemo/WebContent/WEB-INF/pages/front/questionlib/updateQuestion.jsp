<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--#include virtual="../../common/meta.shtml"-->
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css"/>
<%@include file="../../common/meta.jsp"%>
</head>
<body class="editQuestion">
     <!--#include virtual="../../common/header.shtml"-->
     <!--#include virtual="../../common/nav.shtml"-->
     <%@include file="../../common/topHeader.jsp"%>
      <%@include file="../../common/nav.jsp"%> 
     <div class="editQues">
        <div class="breadCrumb">
          <a href="javascript:;">题库</a><span class="nextArrow">></span><a href="javascript:;">我的习题</a>
           <span class="nextArrow">></span>
           <span class="currentLevel">编辑习题</span>
        </div>
        
        <!-- 内容区 -->
         <div class="editQuesContent mt20">
		     <span class="mr50"><b class="red">*</b>学段</span>
		       <select class="newSel w160 mr30">
		         <option>请选择</option>
		         <option>小学</option>
		       </select>
		       <span class="mr50"><b class="red">*</b>年级</span>
		       <select class="newSel w160 mr30">
		         <option>请选择</option>
		         <option>小学</option>
		       </select>
		       <span class="mr50"><b class="red">*</b>学科</span>
		       <select class="newSel w160">
		         <option>请选择</option>
		         <option>小学</option>
		       </select>
		 </div>
		 <p class="associate">关联章节</p>
		 <div class="borderBox pd20">
		             版本：<select class="newSel w160 mr20">
		            <option>请选择</option>
		          </select>
		            分册：<select class="newSel w160 mr20">
		            <option>请选择</option>
		          </select>
		             章：<select class="newSel w160 mr20">
		            <option>请选择</option>
		          </select>
		               节：<select class="newSel w160 mr20">
		            <option>请选择</option>
		          </select>
		        <button class="btn addChapter">添加章节</button>
		        
		      <ul class="commonUL ml30 mt20">
		        <li>
		       	 	<label customdel="" class="itemDel">
		       	 		<button value="1" name="v1" type="button">三年级(语文)</button>
		       	 	</label>
		       	 	<label class="itemDel">
		       	 		<button value="2" name="v2" type="button">三年级(语文)</button>
		       	 	</label>
		       	 	<label class="itemDel">
		       	 		<button value="3" name="v3" type="button">三年级(语文)</button>
		       	 	</label>
		       	 </li>
			  </ul>
		   </div>
		   <p class="associate">关联知识点</p>
		 <div class="borderBox pd20">
		              知识点：<select class="newSel w160 mr20">
		              <option>请选择</option>
		            </select>
		            <select class="newSel w160 mr20">
		              <option>请选择</option>
		            </select>
		           
		        <button class="btn addChapter">添加知识点</button>
		        
		      <ul class="commonUL ml30 mt20">
		        <li>
		       	 	<label customdel="" class="itemDel">
		       	 		<button value="1" name="v1" type="button">三年级(语文)</button>
		       	 	</label>
		       	 	<label class="itemDel">
		       	 		<button value="2" name="v2" type="button">三年级(语文)</button>
		       	 	</label>
		       	 	<label class="itemDel">
		       	 		<button value="3" name="v3" type="button">三年级(语文)</button>
		       	 	</label>
		       	 </li>
			  </ul>
		   </div>
          <ul class="commonUL mainUl">
			    <li>
			      <label class="text left">来源</label>
			      <span class="cont">
			        <input type="text" style="width:470px">
			      </span>
			    </li>
			    <li>
			      <label class="text left"><span class="red">*</span>难易度</label>
			      <span class="cont">
			        <select class="newSel w160">
			          <option>请选择</option>
			        </select>
			      </span>
			    </li>
			    <li>
			      <label class="text left"><span class="red">*</span>习题题干</label>
			      <span class="cont">
			        <div class="fuwenben"></div>
			        <a class="uploadMedia mt20" href="javascript:;">上传音视频</a><br/>
			        <label class="choices">选项A：<input type="text" placeholder="陶渊明"></label>
			        <label class="choices">选项B：<input type="text" placeholder="陶渊明"></label>
			        <label class="choices">选项C：<input type="text" placeholder="陶渊明"></label>
			        <label class="choices">选项D：<input type="text" placeholder="陶渊明"></label>
			                  正确答案：<label class="mr20"><input type="checkbox" name="theCorrectAnser">&nbsp;&nbsp;选项A</label>
		                   <label class="mr20"><input type="checkbox" name="theCorrectAnser">&nbsp;&nbsp;选项B</label>
		                   <label class="mr20"><input type="checkbox" name="theCorrectAnser">&nbsp;&nbsp;选项C</label>
		                   <label class="mr20"><input type="checkbox" name="theCorrectAnser">&nbsp;&nbsp;选项D</label>
			      </span>
			    </li>
			    <li>
			      <label class="text left"><span class="red">*</span>习题解析</label>
			      <span class="cont">
			        <div class="fuwenben"></div>
			        <a class="uploadMedia brown mt20" href="javascript:;">上传视频解析</a><br/>
			            <span class="videname mr50">sdds.flv</span>
			            <span class="red">删除</span>
			            <a href="javascript:;" class="clickToPlay">点击播放</a>
			       </span>
			   </li>
			   <li>
			      <label class="text left">
			        <a href="javascript:;">添加子题</a>
			      </label>
			      
			   </li>
			   <li>
			      <label class="text left"><span class="red">*</span>权限</label>
			      <span class="cont">
			          <label class="mr20"><input name="policy" type="radio">&nbsp;&nbsp;仅供辖区内使用</label>
			          <label class="mr20"><input name="policy" type="radio">&nbsp;&nbsp;分享到平台</label>
			       </span>
			   </li>
			   <li class="center mt30">
			      <button class="btn bigBtn saveAndSubmit mr20">提交保存</button>
			      <span class="toClear">清空</span>
			   </li>
		</ul>
     </div>
    
     
</body>
</html>