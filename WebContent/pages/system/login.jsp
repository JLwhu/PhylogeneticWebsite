<%@ include file="/pages/include/head.jsp" %>

<script language="JavaScript" for="document" event="onkeydown">
	if (event.keyCode == 13)
		login();
	//  event.keyCode=9;
</script>
<script type="text/javascript">
	function login() {
		var logname = document.getElementById("logname").value;
		var password = document.getElementById("password").value;
		var checkcode = document.getElementById("checkcode").value;
		if (logname == '') {
			alert("Please input User Name!");
			return false;
		}
		if (password == '') {
			alert("Please input Password!");
			return false;
		}
		if (checkcode == '') {
			alert("Please input Checkcode!");
			return false;
		}
		document.forms[0].submit();
	}
	function resetValue() {
		document.getElementById("logname").value = "";
		document.getElementById("password").value = "";
		document.getElementById("checkcode").value = "";
	}
	if ('<s:property value="message"/>' != "") {
		alert('<s:property value="message"/>');
	}
</script>

<%
	String loginCode = System.currentTimeMillis() + "";
	loginCode = loginCode.substring(loginCode.length() - 4);
	session.setAttribute("LOGIN_CODE", loginCode);
%>
	<div class="body">
		<div class="contact">
			<div>
				<h2>System Manager Login</h2>


				<s:form action="loginAction" theme="simple">
					<table border="0">
						<tr>
						<td>USERNAME</td>
							<td><s:textfield name="username" label="Username" /></td>
						</tr>
						<tr>
						<td>PASSWORD</td>
							<td><s:password name="password" label="Password" /></td>
						</tr>
						<tr>
						<td>CHECKCODE</td>
							<td><s:textfield name="checkcode" label="checkcode" /></td>
							<td><%=loginCode%></td>
						</tr>
						<tr>
							<td></td>
							<td><s:submit value="Login" onclick="login();" /><s:submit value="Reset" onclick="resetValue();" /></td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>

<%@ include file="/pages/include/foot.jsp" %>