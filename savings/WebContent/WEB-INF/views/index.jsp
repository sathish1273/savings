<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<style>
.unclickable{
position: fixed;
top: 0px;
left: 0px;
width: 100%;
height: 100%;
background: rgba(0, 0, 0, 0.3);
z-index: 2;
}

.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #395e6e;
   color: white;
   text-align: center;
}

.loader {
  border: 16px solid #3e6068;
  border-radius: 50%;
  border-top: 16px solid #1aa8df87;
  width: 120px;
  height: 120px;
  z-index: 1;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}
</style>
<title>Savings</title>
<script type="text/javascript">
function choosediff(text)
{
	loadingstop();
	document.getElementById("secutityQ").style.visibility = 'visible'; 
	document.getElementById("secutityQ").style.position='relative';
	 document.getElementById('authType').value=text;
	 document.getElementById("otpid").style.visibility = 'hidden'; 
		document.getElementById("otpid").style.position='absolute';

		document.getElementById('loginbutton').style.visibility = 'hidden'; 
		document.getElementById('loginbutton').style.position = 'absolute'; 
}

function loadingstart()
{
	document.getElementById('loadingimage').style.visibility = 'visible'; 
	document.getElementById('loadingimage').style.position='absolute'; 
	//document.getElementById("maindiv").readonly=false;
	document.getElementById('unclickable').style.visibility = 'visible'; 
	document.getElementById('unclickable').style.position='absolute'; 
}

function loadingstop()
{
	
	document.getElementById('loadingimage').style.visibility = 'hidden'; 
	document.getElementById('loadingimage').style.visibility = 'absolute'; 
	//document.getElementById('loadingimage').readonly=true;

	document.getElementById('unclickable').style.visibility = 'hidden'; 
	document.getElementById('unclickable').style.position='absolute'; 
	
}

</script>
</head>

<body style="background-color: #cbdee5;" onload="loadingstop()">
<div id="loadingimage" style="margin-left: 45%;margin-top: 13%;position: absolute;" class="loader" ></div>
<div id="unclickable" class="unclickable"></div>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->

	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	 <div align="center" style="position: absolute;margin-left: 40%;">
	 <table><tr>
	 <th><h1>Savings</h1></th></tr>
	 <c:choose>
	 <c:when test="${message eq 'OTP sent to your registered mobile no..' }">
	 <tr><th style="color: green;"> ${message }</th></tr>
	 </c:when>
	 <c:otherwise>
	 <tr><th style="color: red;"> ${message }</th></tr>
	 </c:otherwise>
	 </c:choose>
	 
	 
	 <tr><td>
        <form action="login" modelAttribute="Login" method="post">
            <table border="0" style="border: 10px solid;border-spacing: 15px;background: #3864668c;width: 169%;margin-left: -36%">
              <input type="text" style="visibility: hidden;position: absolute;" id="authType" name="authType" value="otp"/>
                <tr>
                    <th>UserName:</th>
                    <td><input type="text" name="username" value="${usernamel }" /></td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td><input type="password" name="password" value="${passwordl }" /><!-- <a style="color: darkred;" href="forgotpasswordscreen"> &nbsp; &nbsp; forgot password</a> --></td>
                </tr>
                <tr>
                    <th>Sangam:</th>
                    <td>
                    <select name="institutionId" style="width: auto;position: absolute;">
                    <c:forEach var="i" items="${institutionslist }">
                    <option value="${i.institution_uid }" <c:if test="${i.institution_uid eq instl }">selected</c:if>  >${i.institutionName }</option>
                    </c:forEach>
                    
                    </select>
                    </td>
                </tr>
                <c:if test="${otp ne null and otp ne '' }">
                 <tr id="otpid">
                    <th>Enter OTP:</th>
                    <td><input type="text" name="otp" /> <input type="submit" onclick="choosediff('Resend')" value="Resend" /></td>
                </tr>
                </c:if>
               
              <tr id="secutityQ" style="visibility: hidden;position: absolute;">
              <td colspan="2">
                <table style="color: #2f5d68;">
                 <tr><th colspan="2" style="color: brown;">Security Questions:</th></tr>
                <tr><th style="text-align: inherit;">What is your mother maidenName</th><td><input type="password" id="motherMaidenNamel" name="motherMaidenName" /></td></tr>
                <tr><th style="text-align: inherit;">What is your first mobileNo</th><td><input type="password" id="firstMobileNol" name="firstMobileNo" /></td></tr>
                <tr><th style="text-align: inherit;">what is your favorite game</th><td><input type="password" id="favouritGamel" name="favouritGame" /></td></tr>
                <tr><th style="text-align: inherit;">what is your birthPlace</th><td><input type="password" id="placeOfBirthl" name="placeOfBirth" /></td></tr>
                </table>
                </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input id="loginbutton" onclick="loadingstart()" type="submit" value="Login" /></td>
                </tr>
            </table>
        </form>
        </td></tr>
        <tr><th><a href="#" style="color: darkred;" onclick="choosediff('questions')">choose different option login</a></th></tr>
        

        </table>
        <table style="width: 169%;margin-left: -36%;margin-top: 6%;color: #ed7f0d;">
                <tr><th><b>Design & Developed by</b></th></tr>
        <tr><th><b>Vanga Sathish</b></th></tr>
        <tr><th><b>For any software products contact 8801140530</b></th></tr>
        </table>
    </div>
<div class="footer">
  <p>@Copy rights: Vanga Sathish, ChinthaNekkonda, 8801140530, All rights reserved</p>
</div>
<c:remove var="message"/>
<c:remove var="otp"/>
<c:remove var="usernamel"/>
<c:remove var="passwordl"/>
<c:remove var="instl"/>
</body>

</html>