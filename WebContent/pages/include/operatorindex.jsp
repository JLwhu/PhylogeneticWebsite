<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
	<head>
		<title>VOD后台管理系统</title>
	</head>
<frameset rows="90,*" frameborder="NO" border="0" framespacing="0">
<frame src="<%=request.getContextPath()%>/pages/include/head.jsp" name="topFrame" scrolling="NO" >
<frameset cols="170,*" frameborder="yes" border="0" framespacing="0">
     <frame src="<%=request.getContextPath()%>/pages/include/tree.jsp" name="leftFrame"   frameborder="0">
    <frame src="<%=request.getContextPath()%>/pages/include/operator.jsp" name="mainFrame" scrolling="yes" frameborder="0">
  </frameset>
</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
