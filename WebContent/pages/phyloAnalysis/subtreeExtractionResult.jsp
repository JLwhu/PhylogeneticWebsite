<%@ include file="/pages/include/head.jsp" %>

<%
	String fileName = "out.tre";
	String mainTreeDiversity;
	String subTreeDiversity;
	String mainTreeImageFileName;
	String subTreeImageFileName;
	String outputPath;

	fileName = request.getParameter("fileName");
	outputPath  = request.getParameter("outputPath");
	mainTreeDiversity = request.getParameter("mainTreeDiversity");
	subTreeDiversity = request.getParameter("subTreeDiversity");
	mainTreeImageFileName = request.getParameter("mainTreeImageFileName");
	subTreeImageFileName = request.getParameter("subTreeImageFileName");
	
	ArrayList deletesFileNames = new ArrayList();//fileName+","+mainTreeImageFileName+","+subTreeImageFileName;
	deletesFileNames.add(0, fileName);
	deletesFileNames.add(1, mainTreeImageFileName);
	deletesFileNames.add(2, subTreeImageFileName);
		
%>

<script language="JavaScript">

function deleteall()
{
	window.location = "deleteMultiFile.action?deletesFileNames=<%=deletesFileNames%>";
	  	
}

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

		<p id="f1" >
			<%-- Download Result Tree File:<a onclick="deletef1()" href="download.action?fileName=<%=fileName%>"><%=fileName%></a> --%>
			Download Result Tree File:<a href="download.action?fileName=<%=fileName%>"><%=fileName%></a>
		</p>
		<p>
			Main Tree Diversity
			<%=mainTreeDiversity%>
		</p>
		<p>
			SubTree Diversity
			<%=subTreeDiversity%>
		</p>
		<p id="f2" >
			<%-- Download Sub Tree Image:<a onclick="deletef2()" href="download.action?fileName=<%=subTreeImageFileName%>"><%=subTreeImageFileName%></a> --%>
			Download Sub Tree Image:<a href="download.action?fileName=<%=subTreeImageFileName%>"><%=subTreeImageFileName%></a>
		</p>
		</p>
		<p id="f3">
			<%-- Download Main Tree Image:<a  onclick="deletef3()" 
				href="download.action?fileName=<%=mainTreeImageFileName%>"><%=mainTreeImageFileName%></a> --%>
			Download Main Tree Image:<a href="download.action?fileName=<%=mainTreeImageFileName%>"><%=mainTreeImageFileName%></a>
		</p>
		
		
	<!--	<p>
			delete file:<a
				href="deleteMultiFile.action?deletesFileNames=<%=deletesFileNames%>"><%=mainTreeImageFileName%></a>
		</p>
		<img
			alt="Subtree" src="<%=subTreeImageFileName%>">
		<img alt="Main Tree" src="<%=mainTreeImageFileName%>">   -->
	</div>
</div>


<%@ include file="/pages/include/foot.jsp" %>