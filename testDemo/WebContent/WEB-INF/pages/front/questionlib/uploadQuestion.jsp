<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/meta.jsp"%>
<style>
body{background-color:#fff !important}
  #failure{ line-height:1.8; font-size:14px; color:#222;text-indent:1em}
  #failure div{margin-top:10px}
  .errorMsg{color:#f30011; margin:10px 0 5px;font-size:16px;ont-weight:bold;  text-indent:0}
  .submitBtn{ position:relative; width:100%; bottom:0}
</style>
<script type="text/javascript" src="${PUBLIC_PATH}/public/js/ajaxfileupload.js"></script>

<script>
	window.changeUploadParseJsonMethod = true;
	
	$(function(){
		$("#success").hide() ;
		$("#failure").hide() ;
	}) ;
	
	var len = 360 ;
	var add = 0 ;
	
	function openContenFrame(){
	    var td1 = $("#tdOne") ;
	    var td2 = $("#tdTwo") ;
	    add = add+10 ;
	    td1.width(add) ;
	    if(len - add <= 0){
	       td2.width(1) ;
	    }else{
	       td2.width((len - add)) ;
	    }
	    if(add<=len) {
		   ;
	    }else{
	       td1.width(1) ;
	       td2.width(360) ;
	       add = 0 ;
	    }
	    
	  	window.setTimeout('openContenFrame()', 100) ;
	}
	
	function loading(){
		//$("#uploadExcel").hide() ;
	    $("#load").css("display","block"); 
	    //openContenFrame();
	}
	
	function unloading(){
		$("#load").css("display","none"); 
	}
	
	String.prototype.endWith = function(str){     
		
		 var reg = new RegExp(str+"$"); 
		 return reg.test(this);        
	} ;
		
	function submitForm() {
		var fpath = $("#uploadFile").val();

		if (fpath == "") {
			
			Win.alert("未选择需要导入的文档！");
			return false;
		}
		
		if(!fpath.endWith(".doc")&&!fpath.endWith(".docx")){
			
			Win.alert("您上传的文件格式不正确,请重新上传!") ;
			return false ;
		}
		
		/* if((fpath.indexOf("teacher") == -1) && (fpath.indexOf("parent") == -1) && (fpath.indexOf("student") == -1) ) {
			
			Win.alert("请不要修改模板文件名称!") ;
			return false ;
		} */
	
		// ===== 加进度条控制
		loading() ;
		
		var url;
		if('exam' == '${type}'){
			url = '${root}/teacherTest/uploadExam.do'
		}else if('realexam' == '${type}'){
			url = '${root}/schoolTest/uploadRealExam.do'
		}else if('clazexam' == '${type}'){
			url = '${root}/schoolTest/uploadClazExam.do'
		}else{
			url = '${root}/questionLib/uploadQuestion.do';
		}
		
		
		$.ajaxFileUpload({
	    	url			:	url , // === 需要链接到服务器地址
	    	data		:	{"uploadfile":fpath} ,
	    	secureuri	:	true ,
	     	fileElementId	:	'uploadFile' ,                  			 // === 文件选择框的id属性
	    	dataType	:	'json',                                    	 	 // === 服务器返回的格式，可以是json
	     	success		:	function (json, status){            			 // === 相当于java中try语句块的用法      
	     		
	     		unloading();
	     		// === 导入成功
	     		if(json.flag){
	     			$("#successmsg").html(json.resultmsg);
	     			$("#success").show() ;
	     			uploadSuccess();
	     		}else{
	     			// === 导入失败
						var html = '<div class="errorMsg">错误信息</div>';
						var errormeg = json.errormeg
						var len = errormeg.length;
						for(var i=0;i<len;i++){
							var obj = errormeg[i];
							var msgList = obj.msgList;
							for(var j=0;j<msgList.length;j++){
								html +='<div>'+msgList[j]+'</div>';
							}
						}
						$("#failure").html(html);
						$("#failure").show() ;
	     		}  
	     	},
	     	error: function (data, status, e){ 
	     		
	     		// === 相当于java中catch语句块的用法
	     		console.log(e);
	     		console.log(data);
	     		unloading() ;
	     		//parent.window.searchQuestions();
	     		//alert("上传失败");
	     		parent.Win.wins.uploadQuestion.close();
	     	}
	 	}) ;
		
	}
	
	//doSearch
	function uploadSuccess(){
		parent.window.Win.alert("上传成功");
		callParentSearch();
		parent.Win.wins.uploadQuestion.close(); 
	}

	function cancelForm() {
		callParentSearch();
		parent.Win.wins.uploadQuestion.close();
	}
	
	function callParentSearch(){
		if(parent.window.searchQuestions){
			parent.window.searchQuestions();
		}else{
			parent.window.doSearch(1);
		}
	}
</script>
<body>
<table id="load" width="495" height="180" border="0" align="center" bgcolor="#FAFAFA" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;display:none ">
  <tr>
    <td><br><br>
    <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#287BCE" style="border-collapse:collapse ">
        <tr bgcolor="#F7F7F6">
          <td width="20%" height="100" valign="middle">
		    <table align='center' width='480'>
		      <tr>
		       <td colspan='2' align='center' ><font size="2">
		      		 正在进行保存，用时较长，请稍后...
		        </font>
		       </td>
		      </tr>
		      <tr>
		        <td id='tdOne' height='25' width=1 bgcolor="blue">&nbsp;</td>
		        <td id='tdTwo' height='25' width=360 bgColor='#999999'>&nbsp;</td>
		      </tr>
		    </table>
          </td>
        </tr>
    </table>
    </td>
  </tr>
</table>
<div id="uploadExcel" class="contPop pt10" >
	<label >
	<c:choose >
			<c:when test="${type == 'exam' or type == 'clazexam'}">
				<a class="btn testzj mr20" href="${root}/onlinetest_modal/uploadExam.doc">下载试卷模板</a>
			</c:when>
			<c:when test="${type == 'realexam' }">
				<a class="btn testzj mr20" href="${root}/onlinetest_modal/uploadRealExam.doc">下载试卷模板</a>
			</c:when>
			<c:otherwise>
				<a class="btn testzj mr20" href="${root}/onlinetest_modal/uploadQuestion.doc">下载习题模板</a>
			</c:otherwise>
	</c:choose>
	</label>
	<label class="pl20">
	<c:choose >
			<c:when test="${type == 'exam' or type == 'clazexam' or type == 'realexam'}">
				导入试卷：
			</c:when>
			<c:otherwise>
				导入习题：
			</c:otherwise>
	</c:choose>
	
	</label>
	<input type="file" id="uploadFile" name="uploadFile" style="width:260px;" />
	
	<div >
		<div id="success" class="pl20" style="height: 350px; overflow:auto; margin-top:10px">
			<span id="successmsg pl20"></span>
		</div>
		
		
		<div id="failure" class="pl20" style="height: 350px; overflow:auto; margin-top:10px">
		
		</div>
	</div>
	<div class="submitBtn">
		<a class="btn btnGreen mr20" href="javascript:;" id="btnSubmit" onclick="submitForm();"><span>确 定</span></a>
	    <a class="btn bgGray ml20" href="javascript:;" id="btnCancel" onclick="cancelForm();"><span>取 消</span></a>
	</div>
</div>
<body>
</html>



