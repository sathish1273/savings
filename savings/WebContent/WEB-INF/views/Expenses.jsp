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


function validateEx12()
{
	
	var expenseName = document.getElementById('expenseName1').value;
	var amountEx =  document.getElementById('amountEx1').value;
	
	if(expenseName=="" || expenseName==null)
	{
		alert("Please Enter ExpenseName");
		window.setTimeout(function ()
		    {
			document.getElementById('expenseName1').focus();
		    }, 0);
		loadingstop();
		return false;
	} 

	if(amountEx=="" || amountEx==null)
	{
		alert("Please Enter amount");
		window.setTimeout(function ()
		    {
			document.getElementById('amountEx1').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(amountEx))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('amountEx1').focus();
			}, 0);
		loadingstop();
		return false;
	}
}

function validateEx()
{
	
	var expenseName = document.getElementById('expenseName').value;
	var amountEx =  document.getElementById('amountEx').value;
	
	if(expenseName=="" || expenseName==null)
	{
		alert("Please Enter ExpenseName");
		window.setTimeout(function ()
		    {
			document.getElementById('expenseName').focus();
		    }, 0);
		loadingstop();
		return false;
	} 

	if(amountEx=="" || amountEx==null)
	{
		alert("Please Enter amount");
		window.setTimeout(function ()
		    {
			document.getElementById('amountEx').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(amountEx))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('amountEx').focus();
			}, 0);
		loadingstop();
		return false;
	}
}

function updateInterestTypeOtherEx(id,expenseName1,amountEx1,comments1)
{
	loadingstop();
	document.getElementById('id0112344').style.display='block';
	document.getElementById('expenseId').value=id;
	document.getElementById('expenseName1').value=expenseName1;
    document.getElementById('amountEx1').value=amountEx1;
    document.getElementById('comments1').value=comments1;
}

</script>
</head>

<body>


<div id="id0112344" class="modal" >
  
  <form class="modal-content animate" action="addExpense" modelAttribute="Expenses" method="post" onsubmit="return validateEx12();">
    <div class="imgcontainer">
      <span onclick="reload('id0112344')" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table border="0">
     <input  type="text" id="expenseId" name="expenseId" value="0" />
                <tr>
                    <td>ExpenseName<label style="color: red;"> * </label>:</td>
                    <td><input type="text" id="expenseName1" name="expenseName" value="" /></td>
                </tr>
                <tr>
                    <td>amount<label style="color: red;"> * </label>:</td>
                    <td><input type="text" id="amountEx1" name="amount" value="0" /></td>
                </tr>
                <tr>
                    <td>Comments:</td>
                    <td><input type="text" id="comments1" name="comments" /></td>
                </tr>
                
            </table> 
      
        
      <button style="margin-left: 5%" type="submit" onclick="loadingstart()">Submit</button>
      <button type="button" onclick="reload('id0112344')" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>

<div id="expenseDiv" class="modal">
  
  <form class="modal-content animate" action="addExpense" modelAttribute="Expenses" method="post" onsubmit="return validateEx();">
    <div class="imgcontainer">
      <span onclick="document.getElementById('expenseDiv').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table border="0">
                <tr>
                    <td>ExpenseName<label style="color: red;"> * </label>:</td>
                    <td><input type="text" id="expenseName" name="expenseName" value="" /></td>
                </tr>
                <tr>
                    <td>amount<label style="color: red;"> * </label>:</td>
                    <td><input type="text" id="amountEx" name="amount" value="0" /></td>
                </tr>
                <tr>
                    <td>Comments:</td>
                    <td><input type="text" name="comments" /></td>
                </tr>
                
            </table> 
      <button style="margin-left: 5%" onclick="loadingstart()" type="submit">Submit</button>
      <button type="button" onclick="document.getElementById('expenseDiv').style.display='none'" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>
    
     <div align="center">
      <h2 class="tablenames">Expenses &nbsp;&nbsp; <button onclick="document.getElementById('expenseDiv').style.display='block'">+</button></h2>
      <table class="tablecssr">
      <tr class="rowheader">
       <th>ExpenseName</th>
       <th>Amount</th>
       <th>Comments</th>
                </tr>
      <c:forEach items="${ExpenseList }" var="i">
               <tr>
       <td><a href="#" onclick="updateInterestTypeOtherEx('${i.expenseId }','${i.expenseName }','${i.amount}','${i.comments }')">${i.expenseName }</a></td>
       <td>${i.amount }</td>
       <td>${i.comments }</td>
                </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>

</html>