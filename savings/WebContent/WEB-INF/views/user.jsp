<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<link rel='stylesheet' href='form-style.css' type='text/css' />
<head>
<title>savings</title>
<script type="text/javascript">
function updateUser(uid,uname,password,phone,email,landmark,role,village,mandal,district,state,status,placeOfBirth,firstMobileNo,favouritGame,motherMaidenName,loggedStatus,instId,dlist,mlist,vlist)
{
	loadingstop();
	var uuserrl=document.getElementById('loggedInuserVV').value;
    document.getElementById('id04').style.display='block';
    document.getElementById('uuid').value=uid;
    document.getElementById('uuusername').value=uname;
    //document.getElementById('ufathername').value=fathername;
    document.getElementById('uphone').value=phone;
    document.getElementById('ugmail').value=email;
    document.getElementById('urole').value=role;

    document.getElementById('ulandmark').value=landmark;
    document.getElementById('loggedStatus').value=loggedStatus;

    document.getElementById('upassword').value=password;
    document.getElementById('confirmpassword').value=password;
    document.getElementById('placeOfBirthu').value=placeOfBirth;
    document.getElementById('firstMobileNou').value=firstMobileNo;
    document.getElementById('favouritGameu').value=favouritGame;
    document.getElementById('motherMaidenNameu').value=motherMaidenName;
    document.getElementById('institutionIddd').value=instId;

    if(uuserrl == uid){
    	document.getElementById("securityQuestionstbalId").style.visibility = 'visible'; 
    	document.getElementById("securityQuestionstbalId").style.position='relative';

    	document.getElementById("upassword").style.visibility = 'visible'; 
    	document.getElementById("upassword").style.position='relative';

    	document.getElementById("confirmpassword").style.visibility = 'visible'; 
    	document.getElementById("confirmpassword").style.position='relative';
    }
    else{
    	document.getElementById("securityQuestionstbalId").style.visibility = 'hidden'; 
    	document.getElementById("securityQuestionstbalId").style.position='absolute';

    	document.getElementById("upassword").style.visibility = 'hidden'; 
    	document.getElementById("upassword").style.position='absolute';

    	document.getElementById("confirmpassword").style.visibility = 'hidden'; 
    	document.getElementById("confirmpassword").style.position='absolute';
        }

    document.getElementById('statesu').value=state;
    
    changedropdown('Telangana','districtsu',dlist);
    document.getElementById('districtsu').value=district;
    
    changedropdown(district,'mandalsu',mlist);
    document.getElementById('mandalsu').value=mandal;
    
    changedropdown(mandal,'villgaeId',vlist);
    document.getElementById('villgaeId').value=village;
    
    document.getElementById('statusuu').value=status;

    
    //document.getElementById('uphoto').src=photo;
    //document.getElementById('usignature').value=signature;
}

function uservalidate()
{

	var confirmpassword = document.getElementById('confirmpassword').value;
	var upassword =  document.getElementById('upassword').value;

	if(upassword != confirmpassword)
	{
	alert("password and confirm password should be same..");
	document.getElementById('upassword').focus();
	loadingstop();
	return false;
	}

	//var paswd=  '/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/';
	if(upassword.match(/[a-z]/g) && str.match( 
    /[A-Z]/g) && str.match( 
    /[0-9]/g) && str.match( 
    /[^a-zA-Z\d]/g) && str.length >= 8) 
	{ 
	alert('Correct, try another...');
	return true;
	}
	else
	{ 
	alert('Failed... Password should be between 8 to 15 characters which contain at least one lowercase letter, one uppercase letter, one numeric digit, and one special character');
	document.getElementById('upassword').focus();
	loadingstop();
	return false;
	}

	

}

</script>


</head>

<body>

<div id="id04" class="modal">
  
  <form class="modal-content animate" action="addUser" modelAttribute="UserDto" method="post" onsubmit="return uservalidate();">
    <div class="imgcontainer">
      <span onclick="reload('id04')" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table border="0">
                <!-- <tr>
                    <td>NoteId:</td>
                    <td><input type="text" name="userId" /></td>
                </tr> -->
                <input type="text" style="visibility: hidden;position: absolute;" id="loggedInuserVV" value="${user.userId}"/>
                <input type="text" style="visibility: hidden;position: absolute;" id="uuid" name="userId" />
                <tr>
                    <th>UserName:<label style="color: red;"> * </label></th>
                    <td><input type="text" id="uuusername" name="userName" /></td>
                </tr>
                <tr>
                    <th>FatherName:<label style="color: red;"> * </label></th>
                    <td><input type="text" id="ufathername" name="fatherName" /></td>
                </tr>
                <tr>
                    <th>PhoneNo:<label style="color: red;"> * </label></th>
                    <td><input type="text" value="8801140530" id="uphone" name="phoneNo" /></td>
                </tr>
                
                 <tr>
                    <th>Gmail:</th>
                    <td><input type="text" id="ugmail" name="gmail" /></td>
                </tr>
                <tr>
                    <th>Photo:</th>
                    <td><input type="text" id="uphoto" name="photo" /></td>
                </tr>
                <tr>
                    <th>Password:<label style="color: red;"> * </label></th>
                    <td><input type="password" value="12345678aA@" id="upassword" name="password" /></td>
                </tr>
                
                  <tr>
                    <th>ConfirmPassword:<label style="color: red;"> * </label></th>
                    <td><input type="password" value="12345678aA@" id="confirmpassword"/></td>
                </tr>
                
                 <tr>
                    <th>Institution:<label style="color: red;"> * </label></th>
                    <td><select class="select-d" id="institutionIddd" name="institutionId">
                     <c:forEach items="${institutions }" var="i">
                     <c:if test="${i.value ne 'Chintha Nekkonda' }">
                    <option value="${i.key }">${i.value }</option>
                    </c:if>
                    </c:forEach>
                    </select></td>
                </tr>
                 <tr>
                    <th>Role:<label style="color: red;"> * </label></th>
                    <td><select class="select-d" id="urole" name="roleId">
                     <c:forEach items="${rolesList }" var="i">
                     <c:if test="${i.value ne 'SuperAdmin' }">
                    <option value="${i.key }">${i.value }</option>
                    </c:if>
                    </c:forEach>
                    </select></td>
                </tr>
               <tr><th>Security Questions:</th></tr>
              <tr>
              <td colspan="2">
                <table style="color: #00b8ff;font-size: 0.8vw" id="securityQuestionstbalId">
                <tr><th>What is your mother maidenName <label style="color: red;"> * </label></th><td><input type="text" value="123" id="motherMaidenNameu" name="motherMaidenName" /></td></tr>
                <tr><th>What is your first mobileNo<label style="color: red;"> * </label></th><td><input type="text" value="123" id="firstMobileNou" name="firstMobileNo" /></td></tr>
                <tr><th>what is your favorite game<label style="color: red;"> * </label></th><td><input type="text" value="123" id="favouritGameu" name="favouritGame" /></td></tr>
                <tr><th>what is your birthPlace<label style="color: red;"> * </label></th><td><input type="text" value="123" id="placeOfBirthu" name="placeOfBirth" /></td></tr>
                </table>
                </td>
                </tr>
                
                <tr>
                   <th>LandMark:</th>
                   <td><input type="text" id="ulandmark" name="landMark" /></td>
                 </tr>
                 
                 <tr>
                  <c:set var="address" value="${institutionuser.address_id }"></c:set>
                   <td><input type="checkbox" onclick="sameas1('${address.village}','${address.mandal}','${address.district}','${address.state}','villgaeId','mandalsu','districtsu','statesu')" id="sameas" name="sameas" /></td>
                   <th>Same as Sangam address</th>
                 </tr>
                 <tr>
                    <th>State:</th>
                    <td><select class="select-d" id="statesu" name="stateId" onchange="changedropdown(this.options[this.selectedIndex].text,'districtsu','${districtsList }')">
                    <option value="">Select</option>
                    <c:forEach items="${statesList }" var="i">
                    <option value="${i.value }">${i.key }</option>
                    </c:forEach>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>District:</th>
                    <td><select class="select-d" id="districtsu" name="districtId" onchange="changedropdown(this.options[this.selectedIndex].text,'mandalsu','${mandalsList }')">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>Mandal:</th>
                    <td><select class="select-d" id="mandalsu" name="mandalId" onchange="changedropdown(this.options[this.selectedIndex].text,'villgaeId','${villagesList }')">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>Village:</th>
                    <td><select class="select-d" id="villgaeId" name="villgaeId">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                 
                  <tr>
                    <th>LoggedStatus</th>
                    <td><select class="select-d" id="loggedStatus" name="loggedStatus">
                     <c:forEach items="${statusTypes }" var="i">
                    <option value="${i.key }">${i.value }</option>
                    </c:forEach>
                    </select></td>
                </tr>
                      
                <tr>
                    <th>Status</th>
                    <td><select class="select-d" id="statusuu" name="status">
                     <c:forEach items="${statusTypes }" var="i">
                    <option value="${i.key }">${i.value }</option>
                    </c:forEach>
                    </select></td>
                </tr>
            </table> 
      <button style="margin-left: 5%" onclick="loadingstart()" type="submit">Submit</button>
      <button type="button" onclick="reload('id04')" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>
    
     <div align="center">
      <h2 class="tablenames">Users &nbsp;&nbsp; <button onclick="document.getElementById('id04').style.display='block'">+</button></h2>
      <table class="tablecssr">
      <tr class="rowheader">
      <th>UserId</th>
       <th>UserName</th>
       <th>Role</th>
       <th>MobileNo</th>
       <th>Gmail</th>
       <th>Institution</th>
       <th>Address</th>
                </tr>
      <c:forEach items="${userList }" var="i">
               <c:set var="address" value="${i.address }"></c:set>
               <c:set var="role" value="${i.role }"></c:set>
       <tr>
       <c:set var="institution" value="${i.institution }"></c:set>
       <c:set var="address" value="${i.address }"></c:set>
       <td><a href="#" onclick="updateUser('${i.userId}','${i.username }','${i.password }','${i.phone }','${i.email }','${address.landMark }','${role.roleUid }','${address.village }','${address.mandal }','${address.district }','${address.state }','${i.status }','${i.placeOfBirth }','${i.firstMobileNo }','${i.favouritGame }','${i.motherMaidenName }','${i.loggedStatus }','${institution.institution_uid }','${districtsList }','${mandalsList }','${villagesList }')">${i.userId }</a></td>
       <td>${i.username }</td>
       <td>${role.roleName }</td>
       <td>${i.phone }</td>
       <td>${i.email }</td>
       <td>${institution.institutionName }</td>
       <td>${address.village },${address.mandal },${address.district },${address.state }</td>
                </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>

</html>