<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>savings</title>
<script type="text/javascript">
function validateExo()
{
	
	var amount =  document.getElementById('amountr').value;

	if(amount=="" || amount==null)
	{
		alert("Please Enter amount");
		window.setTimeout(function ()
		    {
			document.getElementById('amountr').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(amount))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('amountr').focus();
			}, 0);
		loadingstop();
		return false;
	}
}

function validate123()
{
	
	var amount =  document.getElementById('amount1').value;

	if(amount=="" || amount==null)
	{
		alert("Please Enter amount");
		window.setTimeout(function ()
		    {
			document.getElementById('amount1').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(amount))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('amount1').focus();
			}, 0);
		loadingstop();
		return false;
	}
}

function changedropdownTraOther(value){
if(value == 'OtherIncome'){
document.getElementById('quantity1').style.visibility = 'hidden'; 
document.getElementById('quantity1').style.position='absolute';
document.getElementById('amount1').style.visibility = 'visible'; 
document.getElementById('amount1').style.position='relative';
}
else{
	document.getElementById('amount1').style.visibility = 'hidden'; 
	document.getElementById('amount1').style.position='absolute';
	document.getElementById('quantity1').style.visibility = 'visible'; 
	document.getElementById('quantity1').style.position='relative';
}
}


function updateInterestTypeOther(id,type,amount,description)
{
	loadingstop();
	document.getElementById('id01123').style.display='block';
	document.getElementById('otherIncomeUId').value=id;
	document.getElementById('amount1').value=amount;
    document.getElementById('desc1').value=description;
    document.getElementById('typekey1').value=type;
}

</script>
</head>

<body>

<div id="id01123" class="modal" >
  
  <form class="modal-content animate" action="addOtherIncome" modelAttribute="OtherIncome" method="post" onsubmit="return validate123();">
    <div class="imgcontainer">
      <span onclick="reload('id01123')" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <input hidden  type="text" id="otherIncomeUId" name="otherIncomeUId" value="0" />
     <tr>
                    <td>TypeOfIncome<label style="color: red;"> * </label>:</td>
                    <td><select id="typekey1" class="select-d" onchange="changedropdownTraOther(this.options[this.selectedIndex].text)" name="type">
                    <option value="ApplicationForms">ApplicationForms</option>
                    <option value="OtherIncome">OtherIncome</option>
                    </select></td>
                </tr>
                <tr>
                    <td>Amount<label style="color: red;"> * </label>:</td>
                    <td><input type="text" id="amount1" name="amount" value="0" /></td>
                </tr>
                <tr>
                    <td>Comments:</td>
                    <td><input type="text" id="desc1" name="description" /></td>
                </tr>
    </table>
      
        
      <button style="margin-left: 5%" type="submit" onclick="loadingstart()">Submit</button>
      <button type="button" onclick="reload('id01123')" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>

<div id="otherIncomeDiv" class="modal">
  
  <form class="modal-content animate" action="addOtherIncome" modelAttribute="OtherIncome" method="post" onsubmit="return validateExo();">
    <div class="imgcontainer">
      <span onclick="document.getElementById('otherIncomeDiv').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivOtherIncomeType">
    <table border="0">
                <tr>
                    <td>TypeOfIncome<label style="color: red;"> * </label>:</td>
                    <td><select id="typekey" class="select-d" onchange="changedropdownTraOther(this.options[this.selectedIndex].text)" name="type">
                    <option value="ApplicationForms">ApplicationForms</option>
                    <option value="OtherIncome">OtherIncome</option>
                    </select></td>
                </tr>
                <tr id="amount1">
                    <td>Amount<label style="color: red;"> * </label>:</td>
                    <td><input type="text" id="amountr" name="amount" value="0" /></td>
                </tr>
                <tr>
                    <td>Comments:</td>
                    <td><input type="text" name="description" /></td>
                </tr>
                
            </table> 
      <button style="margin-left: 5%" onclick="loadingstart()" type="submit">Submit</button>
      <button type="button" onclick="document.getElementById('otherIncomeDiv').style.display='none'" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>
    
     <div align="center">
      <h2 class="tablenames">OtherIncome &nbsp;&nbsp; <button onclick="document.getElementById('otherIncomeDiv').style.display='block'">+</button></h2>
      <table class="tablecssr">
      <tr class="rowheader">
       <th>TypeOfIncome</th>
       <th>Amount</th>
       <th>Description</th>
                </tr>
      <c:forEach items="${OtherIncomeList }" var="i">
               <tr>
       <td><a href="#" onclick="updateInterestTypeOther('${i.otherIncomeUId }','${i.type }','${i.amount}','${i.description }')">${i.type }</a></td>
       <td>${i.amount }</td>
       <td>${i.description }</td>
                </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>

</html>