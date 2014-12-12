<%@ include file="/pages/include/controlHead.jsp" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
	<head>
		<title>Start Page</title>
	</head>
	<body>
	
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="titlebg4">
      <tr>
        <td width="197" align="center" valign="middle"  class="titlebg3">System Information</td>
        <td >&nbsp;</td>
      </tr>
    </table>
	<div class="largest" id="init_edit">
		<table class="editTab table_text">
		     <tr>
				<td colspan="4" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome to PhyloGenetic Lab Version1.0</td>
			</tr>
			 <tr>
				<td class="tdLabel">Server Type</td>
				<td class="tdInput">
				(IP: <%=request.getServerName() %> )(Port: <%=request.getServerPort() %> )
				</td>
			    <td class="tdLabel">Script engine：</td>
			    <td class="tdInput">Jsp 2.0</td>
			</tr>
			<tr>
				<td class="tdLabel">Client type：</td>
				<td class="tdInput">
				(IP: <%=request.getLocalAddr().toString() %> )
				</td>
			    <td class="tdLabel">Web Server：</td>
			    <td class="tdInput">Tomcat 7.0</td>
			</tr>
			<tr>
				<td class="tdLabel">Data Base Server：</td>
				<td class="tdInput">
				MySql 5.0
				</td>
			    <td class="tdLabel">&nbsp;</td>
			    <td class="tdInput">&nbsp;</td>
			</tr>
			<tr>
			    <td class="tdLabel">Developed by：</td>
			    <td class="tdInput">Department of Biology, Unversity of Florida</td>
			    <td class="tdLabel">Suggested resolution：</td>
				<td class="tdInput">
				1024 * 768
				</td>
			</tr>
	</table>
	
			<TABLE align=center>
				<TBODY>
					<TR>
						<TD width="100%" class="bottomline">
							Copyright 2011(c)
							<a href="http://localhost:8080/phyloGenetic/" target="_blank">
								http://www.edu </a> All Rights Reserved .
						</TD>
					</TR>
				</TBODY>
			</TABLE>
		</div>
	</body>
</html>
