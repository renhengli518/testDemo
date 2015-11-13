<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--#include virtual="../../common/meta.shtml"-->
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/test.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${PUBLIC_PATH}/public/css/4.1/qabank.css"/>
<script>
$(function(){
	$(".quesBro").on("click","a",function(){
		var $this=$(this),
		    index=$this.index();
		$this.addClass("active").siblings().removeClass('active');
		$(this).parent(".quesBro").siblings(".quesWrap").eq(index).addClass("show").siblings().removeClass("show")
		
	})
})
</script>
<%@include file="../../common/meta.jsp"%>
</head>
<body class="mainIndex">
     <!--#include virtual="../../common/header.shtml"-->
     <!--#include virtual="../../common/nav.shtml"-->
     <%@include file="../../common/topHeader.jsp"%>
     <%@include file="../../common/nav.jsp"%> 
     
    <div class="container w1200 marginauto posRelative bkgNone clearfix">
       <p class="uploadContainer">
  	     <span class="fr">
		   <a href="javascript:;" class="btn btnUpload mr20" id="addRes" style="margin-top:53px;">批量导入习题</a>
		   <a href="javascript:;" class="btn btnUpload" id="addRes" style="margin-top:53px;">新建习题</a>
	    </span>
	 </p>
       	<div class="content">
       	  <div class="content-in">
       	    
  			<div class="borderBox bkgWhite mb10 criteria pd20">
				 <ul class="commonUL gn-searchCondition gn-coffeeSort">
				 <li>
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>学段：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" dir="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">	
			    		 	  <c:forEach items="${semesters}" var="semester">
										<a href="javascript:;" id="${semester.baseSemesterId }">${semester.semesterName }</a>
							  </c:forEach>
						    </span>
			    		 </span>
			    	</li>
			    	<li id="classlevelLi">
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>年级：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" dir="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">	
			    		 	  <c:forEach items="${classes }" var="classes">
										<a href="javascript:;" dir="${classes.parentId }" id="${classes.id }">${classes.name }</a>
							  </c:forEach>
						    </span>
			    		 </span>
			    	</li>
			    	<li>
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>学科：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <c:forEach items="${subjects}" var="subjects">
										<a href="javascript:;" id="${subjects.id }">${subjects.name }</a>
							   </c:forEach>
			    		 	</span>
			    		 </span>
			    	</li>
			    	<li>
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>版本：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <c:forEach items="${versions}" var="versions">
										<a href="javascript:;" id="${versions.id }">${versions.name }</a>
							   </c:forEach>
			    		 	</span>
			    		 </span>
			    	</li>
			    	<li>
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>分册：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <a href="javascript:;">上册</a>
							   <a href="javascript:;">下册</a>
			    		 	</span>
			    		 </span>
			    	</li>
			    	<li>
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>类型：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <a href="javascript:;" id="SINGLE_CHOICE">单选题</a> 
			    		 	   <a href="javascript:;" id="MULTI_CHOICE">多选题</a> 
							   <a href="javascript:;" id="JUDEMENT">判断题</a>
							   <a href="javascript:;" id="FILL_IN_BLANK">填空题</a> <a href="javascript:;" id="ASK_ANSWER">问答题</a> 
							   <a href="javascript:;" id="COMPUTING">计算题</a>
			    		 	</span>
			    		 </span>
			    	</li>
			    	<li>
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>难易度：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <a href="javascript:;" id="EASY">容易</a> 
			    		 	   <a href="javascript:;" id="LITTLE_EASY">较容易</a> 
			    		 	   <a href="javascript:;" id="NORMAL">一般</a> 
			    		 	   <a href="javascript:;" id="LITTLE_DIFFICULT">较难</a>
							   <a href="javascript:;" id="DIFFICULT">困难</a>
			    		 	</span>
			    		 </span>
			    	</li>
			    	<li>
			    		 <label class="text gn-labelText gn-coffeeBracket"><b>习题种类：</b></label>
			    		 <span class="cont gn-sortBox clearfix">
							<a id="0" href="javascript:;" class="selected gn-sortAll fl">全部</a>
			    		 	<span class="gn-sortKinds gn-overflowHidden">
			    		 	   <a href="javascript:;" id="TWINS">孪生题</a>
							   <a href="javascript:;" id="EXTEND">衍生题</a>
			    		 	</span>
			    		 </span>
			    	</li>
			    	
			    </ul>
			  </div>
       	 
       	    <div class="clearfix floatWrap">
                <div class="mainLeft borderBox bkgWhite pd0">
                   <div class="gn-sourceTabs  gn-redTopArrow clearfix">
			          <a class="gn-TopArrowSelected" id="chapterTab" href="javascript:;">章节</a>
			          <a id="klgTab" href="javascript:;">知识点</a>
			        </div>
			        <div class="pd10 gn-ChapterPointer">
			          <ul class="tree">
							<li class="node-0" onclick="getChildren(id, 0, this, event)">
								<span>上册</span>
							</li>
							<li class="node-1" onclick="getChildren(id, 1, this, event)">
		                     <span>
		                        <i class="dot"></i>第一层
		                     </span>
		                     <ul>
		                       <li class="node-2" onclick="getChildren(id, 2, this, event)">
		                         <span>
		                           <i class="dot"></i>第一层
		                          </span>
		                        </li>
		                      </ul>
		                    </li>
						</ul>				       
			        </div>
			        
			        <div style="display: none" class="pd10 gn-sourceContent gn-KnowledgePointer">
			        </div>
                </div>
                <div class="mainRight1 ">
                    <div  class="quesContainer">
	                    <div class="quesDetail1">
	                      <p class="quesDesc">
	                         <label>组卷次数：</label><span class="red">48</span>
	                         <label>知识点：</label><span class="c888">5.9 有理数的混合运算</span>
	                         <label>难易度：</label><span>较易</span>
	                         <label>更新时间：</label><span>2015-08-17  11:37</span>
	                         <span class="fr editOrDel">
	                           <span>编辑</span>
	                           <b class="c888">|</b>
	                           <span>删除</span>
	                         </span>
	                       </p>
	                       <div class="quesBro">
	                          <a href="javascript:;" class="active">母题</a><a href="javascript:;">孪生题</a><a href="javascript:;">衍生题1</a><a href="javascript:;">衍生题1</a><a href="javascript:;">衍生题2</a><a href="javascript:;">衍生题3</a>
	                           <a href="javascript:;">衍生题3</a><a href="javascript:;">衍生题4</a><a href="javascript:;">衍生题5</a><a href="javascript:;">衍生题6</a><a href="javascript:;">衍生题7</a>
	                       </div>
	                       
	                       <div class="quesWrap show">
	                            <p>1 、已知世运会、亚运会、奥运会分别于公元2009年、2010年、2012年举办，若这三项运动会均每四年举办一次，则这三项运动均不在下列哪一年举办？（<input type="text" class="testQues choiceInput">）</p>
		                        <p>A . 公元2070年<br>
		                           B . 公元2071年<br>
		                           C . 公元2072年<br>
		                           D . 公元2073年
		                        </p>  
		                       
	                       </div>
	                       
	                       <div class="quesWrap">
	                            <p>1 、已知世运会、亚运会、奥运会分别于公元2009年、2010年、2012年举办，若这三项运动会均每四年举办一次，则这三项运动均不在下列哪一年举办？（<input type="text" class="testQues choiceInput">）</p>
		                        <p>A . 公元2070年<br>
		                           B . 公元2071年<br>
		                           C . 公元2072年<br>
		                           D . 公元2073年
		                        </p>  
		                        <div class="Analytical">
		                          <div class="borderBox pd10 commentContent show">
		                            <b>答案：A</b>
		                       		 选项A，陶渊明，为《桃花源记》作者。为南朝宋初诗人，思想家。<br>
		                       		 选项B，李白，为唐朝诗人。<br>
		                       		 选项D，杜甫为唐朝现实主义诗人。<br>
		                       		 《离骚》为诗歌，是战国末期，爱国诗人屈原为表达爱国情怀而作的楚辞。于《诗经》并称“风骚”二体。所以正确答案应是选项C。
		                       		<p class="playVideoP"> <button class="btn playVideo">点播解析视频</button></p>
			                       </div>
		                       </div>
	                       </div>
	                        
	                     </div>
	                  </div>
	                  
	                  <div  class="quesContainer">
	                    <div class="quesDetail1">
	                      <p class="quesDesc">
	                         <label>组卷次数：</label><span class="red">48</span>
	                         <label>知识点：</label><span class="c888">5.9 有理数的混合运算</span>
	                         <label>难易度：</label><span>较易</span>
	                         <label>更新时间：</label><span>2015-08-17  11:37</span>
	                       </p>
	                       <div class="quesBro">
	                          <a href="javascript:;" class="active">母题</a><a href="javascript:;">孪生题</a><a href="javascript:;">衍生题1</a><a href="javascript:;">衍生题1</a><a href="javascript:;">衍生题2</a><a href="javascript:;">衍生题3</a>
	                           <a href="javascript:;">衍生题3</a><a href="javascript:;">衍生题4</a><a href="javascript:;">衍生题5</a><a href="javascript:;">衍生题6</a><a href="javascript:;">衍生题7</a>
	                       </div>
	                       
	                       <div class="quesWrap show">
	                            <p>1 、已知世运会、亚运会、奥运会分别于公元2009年、2010年、2012年举办，若这三项运动会均每四年举办一次，则这三项运动均不在下列哪一年举办？（<input type="text" class="testQues choiceInput">）</p>
		                        <p>A . 公元2070年<br>
		                           B . 公元2071年<br>
		                           C . 公元2072年<br>
		                           D . 公元2073年
		                        </p>  
		                       
	                       </div>
	                       
	                       <div class="quesWrap">
	                            <p>1 、已知世运会、亚运会、奥运会分别于公元2009年、2010年、2012年举办，若这三项运动会均每四年举办一次，则这三项运动均不在下列哪一年举办？（<input type="text" class="testQues choiceInput">）</p>
		                        <p>A . 公元2070年<br>
		                           B . 公元2071年<br>
		                           C . 公元2072年<br>
		                           D . 公元2073年
		                        </p>  
		                        <div class="Analytical">
		                          <div class="borderBox pd10 commentContent show">
		                            <b>答案：A</b>
		                       		 选项A，陶渊明，为《桃花源记》作者。为南朝宋初诗人，思想家。<br>
		                       		 选项B，李白，为唐朝诗人。<br>
		                       		 选项D，杜甫为唐朝现实主义诗人。<br>
		                       		 《离骚》为诗歌，是战国末期，爱国诗人屈原为表达爱国情怀而作的楚辞。于《诗经》并称“风骚”二体。所以正确答案应是选项C。
		                       		<p class="playVideoP"> <button class="btn playVideo">点播解析视频</button></p>
			                       </div>
		                       </div>
	                       </div>
	                        
	                     </div>
	                  </div>
	                  
	                  
                   </div>
                </div>
              </div>
       	  
       	  </div>
       	</div>
    </div>
    <script type="text/javascript">

		function getChildren(id, nodeNumber, currentNode, e) {
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
				if(nodeNumber>2) dots[0].className = "open";
				currentNode.innerHTML = currentNode.innerHTML+"<ul><li class='node-"+(nodeNumber+1)+"' onclick='getChildren(id, "+(nodeNumber+1)+", this, event)'><span><i class='dot"+(nodeNumber+1>=3?' close':'')+"'></i>第一层</span></li></ul>";
			}
		}

      $(function(){
    	 $(".gn-sourceTabs").on("click","a",function(e){
    		 e=e||window.event;
    		 if(e.stopPropagation())
    			 {
    			   e.stopPropagation();
    			 }
    		 else{
    			 window.cancelBubble=true;
    		 }
    		 $(this).siblings().removeClass("gn-TopArrowSelected");
    		 $(this).addClass("gn-TopArrowSelected")
    	 });

    	 $(".gn-resourcesHandle .gn-selBorder").click(function(){
    		     $(".gn-aWrap").show();
    		     $(".gn-resourcesHandle").addClass("clicked")
    		     $(this).removeClass("gn-down").addClass("gn-up");
    	 });
    	 $(".gn-aWrap").on("click","a",function(){
    		 $(".gn-aWrap").hide();
    		 $(".gn-resourcesHandle").removeClass("clicked")
    		 $(".gn-resourcesHandle .gn-selBorder").removeClass("gn-up").addClass("gn-down");
    	 });
    	 $(document).click(function(){
    		 $(".gn-aWrap").hide();
    		 $(".gn-resourcesHandle").removeClass("clicked")
    		 $(".gn-resourcesHandle .gn-selBorder").removeClass("gn-up").addClass("gn-down");
    	 });

    	 events.addEvent($class("gn-selBorder"),"click",function(e){
    		e = e || window.event;
            if (e.stopPropagation){
                e.stopPropagation();    
            } else {
                e.cancelBubble=true;
            }
    		// e.preventDefault();
    		// e.stopPropagation();
    	 });
    	 
    	 
      })
      
      
      
      ///////////////////////////////////////
      //获取选中章节查询的各个选项值进行分页和查询
      
    </script>
</body>
</html>