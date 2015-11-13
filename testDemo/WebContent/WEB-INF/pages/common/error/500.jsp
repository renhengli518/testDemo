<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.PrintStream" %>
<%@ include file="../meta.jsp"%>
<%@ page import="com.codyy.commons.HostConfig" %>
<%@ page import="com.codyy.commons.context.SpringContext" %>
<title>500页面</title>
</head>
<body class="mainIndex lost">
	<div class="container w1000 marginauto bkgNone new404 clearfix">
        <div class="verticalMiddle center">
            <img src='${PUBLIC_PATH }/public/img/workplace/404Pic.png' class="mr30">
            <span class="message left">抱歉！<br/>
您访问的页面不存在。</span>
         </div>
   </div>
	<div style="display: none;">
		<%
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
		exception.printStackTrace(new PrintStream(output)); 
		out.write(output.toString());
		%>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
