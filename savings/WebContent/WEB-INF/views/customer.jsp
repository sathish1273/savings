<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>savings</title>
<script type="text/javascript">
function updateCustomer(cid,cname,fathername,phoneNo,gmail,photo,signature,landMark,village,mandal,district,state,status,dlist,mlist,vlist)
{
	loadingstop();
    document.getElementById('id03').style.display='block';
    document.getElementById('cid').value=cid;
    document.getElementById('cname').value=cname;
    document.getElementById('cfathername').value=fathername;
    document.getElementById('cphoneNo').value=phoneNo;
    document.getElementById('cgmail').value=gmail;
    
     document.getElementById('statesc').value=state;
    
    changedropdown('Telangana','districtsc',dlist);
    document.getElementById('districtsc').value=district;
    
    changedropdown(district,'mandalsc',mlist);
    document.getElementById('mandalsc').value=mandal;
    
    changedropdown(mandal,'villgaesc',vlist);
    document.getElementById('villgaesc').value=village;
    
    document.getElementById('clandmark').value=landMark;
    document.getElementById('cstatus').value=status;
    
    document.getElementById('initialContribution').value='0.0';
     document.getElementById("initialContribution").style.visibility = 'hidden'; 
	document.getElementById("initialContribution").style.position='absolute';
     

    document.getElementById('cphoto').src=photo;
    document.getElementById('csignature').value=signature;
}

function printCustomer(id)
{
	loadingstop();
	document.getElementById('printCUstomer').style.display='block';
	document.getElementById('custId1').value=id;
}

$(document).ready(function(){
	var id=document.getElementById('printCUstomerid').value;
	if(id != "" && id != null){
	document.getElementById(id).style.display='block';
	}
	});


function validatecustomer()
{
	
	var cname = document.getElementById('cname').value;
	var cfathername =  document.getElementById('cfathername').value;
	var cphoneNo =  document.getElementById('cphoneNo').value;
	var initialContribution =  document.getElementById('initialContribution').value;
	
	if(initialContribution=="" || initialContribution==null)
	{
		alert("Please Enter initialContribution");
		window.setTimeout(function ()
		    {
			document.getElementById('initialContribution').focus();
		    }, 0);
		loadingstop();
		return false;
	} 

	
	if(cphoneNo=="" || cphoneNo==null)
	{
		alert("Please Enter phoneNumber");
		window.setTimeout(function()
			{
			document.getElementById('cphoneNo').focus();
			}, 0);
		loadingstop();
		return false;
	}
	
	if(isNaN(cphoneNo))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('cphoneNo').focus();
			}, 0);
		loadingstop();
		return false;
	}


	if(cfathername=="" || cfathername==null)
	{
		alert("Please Enter fatherName");
		window.setTimeout(function ()
		    {
			document.getElementById('cfathername').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	



	if(cname=="" || cname==null)
	{
		alert("Please Enter customerName");
		window.setTimeout(function ()
		    {
			document.getElementById('cname').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	
}



</script>


</head>

<body>
<input hidden id="printCUstomerid" value="${lastSavedCustomerpop }" />
<c:remove var="lastSavedCustomerpop"/>
<div id="id03" class="modal">
  
  <form class="modal-content animate" action="addCustomer" modelAttribute="CustomerDto" onsubmit="return validatecustomer();" enctype="multipart/form-data" acceptCharset="UTF-8" method="post">
    <div class="imgcontainer">
      <span onclick="reload('id03')" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table border="0">
    
                <tr>
                    <td hidden><input type="text" id="cid" name="customerUid" /></td>
                    <th>CustomerName:<label style="color: red;"> * </label></th>
                    <td><input type="text" id="cname" name="customerName" /></td>
                </tr>
                <tr>
                    <th>FatherName:<label style="color: red;"> * </label></th>
                    <td><input type="text" id="cfathername" name="fatherName" /></td>
                </tr>
                <tr>
                    <th>PhoneNo:<label style="color: red;"> * </label></th>
                    <td><input type="text" id="cphoneNo" name="phoneNo" /></td>
                </tr>
                
                 <tr>
                    <th>Gmail:</th>
                    <td><input type="text" id="cgmail" name="gmail" /></td>
                </tr>
                <tr>
                    <th>Photo:</th>
                    <td><input name="photo" id="cphoto" src="" type="file"/></td>
                </tr>
                 <tr>
                    <th>Signature:</th>
                    <td><input name="signature" id="csignature" src=""  type="file"/></td>
                </tr>
                <tr>
                    <th>InitialContribution:<label style="color: red;"> * </label></th>
                    <td><%-- <input type="text" name="initialContribution" readonly="readonly" value="${InitialContribution }"/> --%>
                    <select class="select-d" style="width: 65%" id="initialContribution" name="initialContribution">
                    <option value="">Select</option>
                    <c:forEach items="${InitialContribution }" var="i">
                    <option value="${i.value }">${i.key }</option>
                    </c:forEach>
                    </select>
                    
                    </td>
                </tr>
                
              <!--   <tr>
                    <th>PendingDeposits:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="depositsAmount"  value="200"/></td>
                </tr>
                
                <tr>
                    <th>DepositsLastPaidDate:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="depositslastPaidDate" value="2020-05-01"/></td>
                </tr>
                
                <tr>
                    <th>PendingMonths:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="noOfMonths" value="2"/></td>
                </tr>
                
                <tr>
                    <th>PendingLoans:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="loansAmount" value="1000"/></td>
                </tr>
                
                <tr>
                    <th>PendingInstallments:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="noOfInstallments" value="2"/></td>
                </tr>
                
                 <tr>
                    <th>LoansLastPaidDate:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="loanslastPaidDate" value="2020-05-01"/></td>
                </tr>
                
                <tr>
                    <th>PendingSpecialLoans11:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="specialLoansAmount" value="500"/></td>
                </tr>
                
                 <tr>
                    <th>LoansLastPaidDate:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="sploanslastPaidDate" value="2020-05-01"/></td>
                </tr> -->
                
                
                <tr>
                    <th>LandMark:</th>
                   <td><input type="text" id="clandmark" name="landMark" /></td>
                 </tr>
                 <tr>
                 <c:set var="address" value="${institutionuser.address_id }"></c:set>
                   <td><input type="checkbox" onclick="sameas1('${address.village}','${address.mandal}','${address.district}','${address.state}','villgaesc','mandalsc','districtsc','statesc')" id="sameas" name="sameas" /></td>
                   <th>Same as Sangam address</th>
                 </tr>
                 <tr>
                    <th>State:</th>
                    <td><select class="select-d" id="statesc" style="width: 65%" name="stateId" onchange="changedropdown(this.options[this.selectedIndex].text,'districtsc','${districtsList }')">
                    <option value="">Select</option>
                    <c:forEach items="${statesList }" var="i">
                    <option value="${i.value }">${i.key }</option>
                    </c:forEach>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>District:</th>
                    <td><select  class="select-d" id="districtsc" style="width: 65%" name="districtId" onchange="changedropdown(this.options[this.selectedIndex].text,'mandalsc','${mandalsList }')">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>Mandal:</th>
                    <td><select  class="select-d" id="mandalsc" style="width: 65%" name="mandalId" onchange="changedropdown(this.options[this.selectedIndex].text,'villgaesc','${villagesList }')">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>Village:</th>
                    <td><select  class="select-d" id="villgaesc" style="width: 65%" name="villgaeId">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                      
                <tr>
                    <th>Status</th>
                    <td><select id="cstatus" style="width: 65%" class="select-d" name="status">
                     <c:forEach items="${statusTypes }" var="i">
                    <option value="${i.key }">${i.value }</option>
                    </c:forEach>
                    </select></td>
                </tr>
            </table>
      
        
      <button style="margin-left: 5%" type="submit" onclick="loadingstart()">Submit</button>
      <button type="button" onclick="reload('id03')" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>



<div id="id033" class="modal">
  
  <form class="modal-content animate" action="addFileCustomers" modelAttribute="CustomerDto" enctype="multipart/form-data" acceptCharset="UTF-8" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id033').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container">
    <table border="0">
     <tr>
                    <th>Excel File:</th>
                    <td><input name="customerFile" id="customerFile" src=""  type="file"/></td>
     </tr>
                
    </table>
      <button style="margin-left: 5%" type="submit" onclick="loadingstart()">Submit</button>
      <button type="button" onclick="document.getElementById('id033').style.display='none'" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>



    
     <div align="center">
      <h2 class="tablenames">Customers &nbsp;&nbsp; <button onclick="document.getElementById('id03').style.display='block'">+</button>&nbsp;&nbsp;<button onclick="document.getElementById('id033').style.display='block'">File</button></h2>
      <table class="tablecssr">
      <tr class="rowheader">

       <th>CustomerId</th>
       <th>CustomerName</th>
       <th>FatherName</th>
       <th>MobileNo</th>
       <th>Gmail</th>
       <th>Address</th> 
       <th>Photo</th>
       <th>Signature</th>
       <th></th>
                </tr>
      <c:if test="${fn:length(customerList) gt 0}">
      <c:forEach items="${customerList }" var="i">
       <c:set var="address" value="${i.address }"></c:set>
       <tr>
       <td><a href="#" onclick="updateCustomer('${i.customerUid}','${i.customerName }','${i.fatherName }','${i.phoneNo }','${i.gmail }','/resources/images/${i.photo }','/resources/images/${i.signature }','${address.landMark }','${address.village }','${address.mandal }','${address.district }','${address.state }','${i.status }','${districtsList }','${mandalsList }','${villagesList }')">${i.customerId }</a></td>
       <td><%-- <a href="#" onclick="printCustomer('${i.customerUid}')" > --%>${i.customerName }<c:set var="custId" value="${i.customerUid}"></c:set><!-- </a> --></td>
       <td>${i.fatherName }</td>
       <td>${i.phoneNo }</td>
       <td>${i.gmail }</td>
      
       <td>${address.village }<%-- ,${address.mandal },${address.district },${address.state } --%></td>
       <td><img width="5%" src='<spring:url value="/resources/images/${i.photo }"></spring:url>'></td>
       <td><img width="25%" src='<spring:url value="/resources/images/${i.signature }"></spring:url>'></td>
       <td></td>
        </tr>
              
                </c:forEach>
                </c:if>
            </table>
           
    </div>
    
    
    <div id="printCUstomer" align="center" class="modal" style="width: 100%;margin-left: 10%;">
      <form:form class="modal-content animate" action="adddeposits" modelAttribute="DepositDtoList" method="post">
      <div class="imgcontainer">
      <span onclick="document.getElementById('printCUstomer').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container">

      
      <table border="0">
      
       <c:set var="institution" value="${lastSavedCustomer.institution }"></c:set>
       <c:set var="address" value="${lastSavedCustomer.address }"></c:set>
       <tr>
       <td><table>
       <tr><span style="color: green;"><h1>&#10003;</h1>Customer Successfully Registered..</span></tr>
       <tr><td><h1>${institution.institutionName }</h1></td></tr>
       <tr><th>CustomerId:</th><td><b>${lastSavedCustomer.customerId }</b></td></tr>
       <tr><th>CustomerName:</th><td>${lastSavedCustomer.customerName }</td></tr>
       <tr><th>FatherName:</th><td>${lastSavedCustomer.fatherName }</td></tr>
       <tr><th>PhoneNo:</th><td>${lastSavedCustomer.phoneNo }</td></tr>
       <tr><th>Gmail:</th><td>${lastSavedCustomer.gmail }</td></tr>
       <tr><th>Address:</th><td>${address.village },${address.mandal },${address.district },${address.state }</td></tr></table>
       </td>
       <td><table>
       <tr><td><img width="100px" src='<spring:url value="/resources/images/${lastSavedCustomer.photo }"></spring:url>'></td></tr>
       <tr><td><img width="20%" src='<spring:url value="/resources/images/${lastSavedCustomer.signature }"></spring:url>'></td></tr>
       </table></td>
       </tr>
     
       </table>
       <button type="button" onclick="printDiv('printCUstomer')" class="cancelbtn">Print</button>
    </div>
    </form:form>
    </div>
    
    <div id="withdrawCustomer" align="center" class="modal" style="width: 100%;margin-left: 10%;">
      <form:form class="modal-content animate" action="adddeposits" modelAttribute="DepositDtoList" method="post">
      <div class="imgcontainer">
      <span onclick="document.getElementById('withdrawCustomer').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container">

      
      <table border="0">
      
       <c:set var="institution" value="${withdrawCustomer.institution }"></c:set>
       <c:set var="address" value="${withdrawCustomer.address }"></c:set>
       <tr>
       <td><table>
       <tr><span style="color: green;"><h1>&#10003;</h1>Customer successfully withdrawn from Sangam..</span></tr>
       <tr><td><h1>${institution.institutionName }</h1></td></tr>
       <tr><th>CustomerId:</th><td><b>${withdrawCustomer.customerId }</b></td></tr>
       <tr><th>CustomerName:</th><td>${withdrawCustomer.customerName }</td></tr>
       <tr><th>FatherName:</th><td>${withdrawCustomer.fatherName }</td></tr>
       <tr><th>PhoneNo:</th><td>${withdrawCustomer.phoneNo }</td></tr>
       <tr><th>Gmail:</th><td>${withdrawCustomer.gmail }</td></tr>
       <tr><th>Address:</th><td>${address.village },${address.mandal },${address.district },${address.state }</td></tr>
       <tr><th><b>Share</b></th><td><b>${withdrawCustomer.share }</b></td></tr>
       </table>
       </td>
       <td><table>
       <tr><td><img width="100px" src='<spring:url value="/resources/images/${withdrawCustomer.photo }"></spring:url>'></td></tr>
       <tr><td><img width="20%" src='<spring:url value="/resources/images/${withdrawCustomer.signature }"></spring:url>'></td></tr>
       </table></td>
       </tr>
     
       </table>
       <button type="button" onclick="printDiv('withdrawCustomer')" class="cancelbtn">Print</button>
    </div>
    </form:form>
    </div>
    
</body>

</html>