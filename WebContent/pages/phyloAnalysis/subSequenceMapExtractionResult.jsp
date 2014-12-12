<%@ include file="/pages/include/head.jsp"%>

<%
	String fileName = "out.tre";
	String outputPath;
	String choice;
	String missingTaxa;

	fileName = request.getParameter("fileName");
	choice = request.getParameter("choice");
	missingTaxa = request.getParameter("missingTaxa");
	//outputPath  = request.getParameter("outputPath");

	String showString = "Download Result Tree File:";
	String outFile2="";
	String resSeqIDOutFileName;
	if (choice.equals("1")){
		showString = "Download Result Sequence File:";
		resSeqIDOutFileName = fileName.substring(0,
				fileName.lastIndexOf("."))
				+ "ID"
				+ ".txt";
		outFile2 = "<p id=\"f2\">Download Result Sequence ID File: <a onclick=\"deletef2()\" href=\"download.action?fileName="+resSeqIDOutFileName+"\">"+resSeqIDOutFileName+"</a></p>";
	}
	
		
	if (!missingTaxa.isEmpty()){		
		String temp = missingTaxa.replaceAll("@", "</p><p>");
		missingTaxa = "<h2>Missing Taxa</h2><p>" + temp +"</p>";
	}
	
	
%>

<script language="JavaScript">

function deletef1()
{
	var x = document.getElementById("f1")
	x.innerHTML= ""
}
function deletef2()
{
	var x = document.getElementById("f2")
	x.innerHTML= ""
}
function deletef3()
{
	var x = document.getElementById("f3")
	x.innerHTML= ""
}
</script>

<div class="body">
	<div class="contact">
		<h2>Results</h2>

		<p id="f1"><%=showString%>

			<a onclick="deletef1()" href="download.action?fileName=<%=fileName%>"><%=fileName%></a>
			<%=outFile2%>
		</p>
		<%=missingTaxa%>
	</div>
</div>
<%@ include file="/pages/include/foot.jsp"%>