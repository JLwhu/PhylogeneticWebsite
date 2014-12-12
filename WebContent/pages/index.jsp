<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>Login Page</title>

<script   language="JavaScript"   for="document"   event="onkeydown">     
  if(event.keyCode==13)   
  login();
//  event.keyCode=9;     
</script>  
<script type="text/javascript">
   function login(){
    var logname=document.getElementById("logname").value;
    var password=document.getElementById("password").value;
    var checkcode=document.getElementById("checkcode").value;
   if(logname == '' ){
      alert("Please input User Name!");
      return false;
   }
   if(password == ''){
      alert("Please input Password!");
      return false;
   }
   if(checkcode == ''){
      alert("Please input Checkcode!");
      return false;
   }      
    document.forms[0].submit();
   }
   function resetValue(){
    document.getElementById("logname").value="";
    document.getElementById("password").value="";
    document.getElementById("checkcode").value="";
   }
   if('<s:property value="message"/>'!=""){
	   alert('<s:property value="message"/>');
   }
   		
</script>
</head>
<%
String loginCode= System.currentTimeMillis()+"";
loginCode= loginCode.substring(loginCode.length()-4);
session.setAttribute("LOGIN_CODE",loginCode);
 %>
<body> 


    <s:form action="loginAction">
        <s:textfield name="username" label="Username" />
        <s:password name="password" label="Password" />
        <s:textfield name="checkcode" label="checkcode"  />

        <s:submit value="Login" onclick="login();" />
        <s:submit value="Reset" onclick="resetValue();" />
       <%=loginCode%>
    </s:form>

</body>
</html>