<%@ page language="java"  import="java.util.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script language="javascript">
	function addComponent() {
		var uploadHTML = document
				.createElement("<input type='file' name='filesUpload' />");
		document.getElementById("files").appendChild(uploadHTML);
		uploadHTML = document.createElement("<p />");
		document.getElementById("files").appendChild(uploadHTML);
	}

	
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Subtree Extraction Page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> <meta
	http-equiv="keywords" content="keyword1,keyword2,keyword3"> <meta
	http-equiv="description" content="This is my page"> <!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
</head>

<body>
	<input type="button" onclick="addComponent();" value="Add File" />
	<br />
	<s:form action="uploadfiles.action" enctype="multipart/form-data">
		<span id="files"> <input type='file' name='filesUpload' />
			<p />
		</span> <input type="submit" value="Upload" />
	</s:form>

	<br>


<s:form action="doMultipleUploadUsingList" method="POST" enctype="multipart/form-data">
	<s:file label="Subtree Name List" name="upload" />
	<s:file label="Main Tree File(Newick)" name="upload" />
	<s:file label="Subtree File(Newick)" name="upload" />
	<s:submit />
</s:form>

	<s:form action="upload.action" enctype="multipart/form-data">
		<input type="file" name="fileUpload"></input>
		<input type="submit" value="Submit"></input>
	</s:form>
	<h4>Download file - <s:a href="%{fileDownload}">fileABC.txt</s:a>
</h4>

                 
 <%

    String fileName="out.tre";

    //以点为分隔符分割文件名字符串并添加到向量中，以获得不带扩展名的文件名

    StringTokenizer st=new StringTokenizer(fileName,".");

    Vector<String> vector=new Vector<String>();

    while(st.hasMoreTokens())

        vector.add(st.nextToken());

       String showName=vector.get(0)+"sample download";

    %>

      Download Result Tree File：<br/>

    <a href="download.action?fileName=<%= fileName%>"><%=showName %></a>
          
</body>
</html>