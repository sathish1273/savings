<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<style>
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #395e6e;
   color: white;
   text-align: center;
}
</style>
<title>savings</title>
<script type="text/javascript">
function choosediff(text)
{
	loadingstop();
	document.getElementById("secutityQ").style.visibility = 'visible'; 
	document.getElementById("secutityQ").style.position='relative';
	 document.getElementById('authType').value=text;
	 document.getElementById("otpid").style.visibility = 'hidden'; 
		document.getElementById("otpid").style.position='absolute';
}

</script>
</head>

<body style="background-color: #cbdee5;">
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->

	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	 <div align="center">
	 <table><tr>
	 <th><h1>forgot password</h1></th></tr>
	 <tr><th style="color: red;"> ${message }</th></tr>
	 
	 <tr><td>
        <form action="forgotpassword" modelAttribute="Login" method="post">
            <table border="0" style="border: 1px solid;border-spacing: 15px;margin-top: 10%;background: cadetblue;">
              <input type="text" style="visibility: hidden;position: absolute;" id="authType" name="authType" value="otp"/>
                <tr>
                    <th>UserName:</th>
                    <td><input type="text" name="username" value="${usernamel }" /></td>
                </tr>
                
                 <tr>
                    <th>MobileNo:</th>
                    <td><input type="text" name="phone" /></td>
                </tr>
              
                <tr>
                    <th>Institution:</th>
                    <td>
                    <select name="institutionId">
                    <c:forEach var="i" items="${institutionslist }">
                    <option value="${i.institution_uid }" <c:if test="${i.institution_uid eq instl }">selected</c:if>  >${i.institutionName }</option>
                    </c:forEach>
                    
                    </select>
                    </td>
                </tr>
                
              <tr><th>Security Questions:</th></tr>
              <tr>
              <td colspan="2">
                <table style="color: red;" id="securityQuestionstbalId">
                <tr><th>What is your mother maidenName</th><td><input type="text" id="motherMaidenNameu" name="motherMaidenName" value="${maidenname }" /></td></tr>
                <tr><th>What is your first mobileNo</th><td><input type="text" id="firstMobileNou" name="firstMobileNo" value="${firstMobileno }"  /></td></tr>
                <tr><th>what is your favorite game</th><td><input type="text" id="favouritGameu" name="favouritGame" value="${game }"  /></td></tr>
                <tr><th>what is your birthPlace</th><td><input type="text" id="placeOfBirthu" name="placeOfBirth" value="${birthplace }"  /></td></tr>
                </table>
                </td>
                </tr>
                <c:if test="${otp ne null and otp ne '' }">
                 <tr id="otpid">
                    <th>Enter OTP:</th>
                    <td><input type="text" name="otp" /> <input type="submit" onclick="choosediff('Resend')" value="Resend" /></td>
                </tr>
                </c:if>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
        
        <c:if test="${forgotpwdauthsuccess eq 'forgotpwdauthsuccess' }">
         <form action="updatepassword" modelAttribute="Login" method="post">
            <table border="0" style="border: 1px solid;border-spacing: 15px;margin-top: 10%;background: cadetblue;">
              <input type="text" style="visibility: hidden;position: absolute;" id="authType" name="authType" value="otp"/>
                <tr>
                    <th>Password:</th>
                    <td><input type="text" name="password" value="${usernamel }" /></td>
                </tr>
                
                 <tr>
                    <th>ConfirmPassword:</th>
                    <td><input type="text" id="confirmpasswordfor" /></td>
                </tr>
              
                
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
        </c:if>
        </td></tr>
        </table>
    </div>
<div class="footer">
  <p>@Copy rights: Vanga Sathish, ChinthaNekkonda, 8801140530, All rights reserved</p>
</div>
</body>

</html>