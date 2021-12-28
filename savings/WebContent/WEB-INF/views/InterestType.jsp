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

function changedropdownTra(value,id1,id2)
{
	loadingstop();
	if(value == "Deposits")
		{
		document.getElementById(id1).style.visibility = 'visible'; 
		document.getElementById(id1).style.position='relative';

		document.getElementById(id2).style.visibility = 'visible'; 
		document.getElementById(id2).style.position='relative';
		}
	else{
		
		document.getElementById(id1).style.visibility = 'hidden'; 
		document.getElementById(id1).style.position='absolute';

		document.getElementById(id2).style.visibility = 'hidden'; 
		document.getElementById(id2).style.position='absolute';

		document.getElementById("fixedInterestAmount").value="0"
		document.getElementById("idealDepositAmount").value="0"
		   
		}
	if(value == "Special_Loans" || value == "Application_Forms")
	{
	document.getElementById('interestTypeTr').style.visibility = 'hidden'; 
	document.getElementById('interestTypeTr').style.position='absolute';
	
	    if(value == "Application_Forms")
		{
	    	document.getElementById('FineId').style.visibility = 'hidden'; 
	    	document.getElementById('FineId').style.position='absolute';

	    	document.getElementById("FineId").value="0"
	    	document.getElementById('rateperqu').innerHTML='RatePerQuantity';
		}
	}
	else{
		document.getElementById('interestTypeTr').style.visibility = 'visible'; 
		document.getElementById('interestTypeTr').style.position='relative';
		}
	
}

function changedropdownIn(value)
{
	loadingstop();
	if(value == "Simple")
		{
		document.getElementById('calculationType').style.visibility = 'hidden'; 
		document.getElementById('calculationType').style.position='absolute';
		}
	else{
		document.getElementById('calculationType').style.visibility = 'visible'; 
		document.getElementById('calculationType').style.position='relative';
		}
}
function displayAddDiv(id)
{
	alert(id);
}


function validate()
{
	
	var fixedInterestAmount = document.getElementById('fixedInterestAmount').value;
	var idealDepositAmount =  document.getElementById('idealDepositAmount').value;
	var fine =  document.getElementById('fine').value;
	var rateOfInterest =  document.getElementById('rateOfInterest').value;
	
	if(fixedInterestAmount=="" || fixedInterestAmount==null)
	{
		alert("Please Enter FixedInterestAmount");
		window.setTimeout(function ()
		    {
			document.getElementById('fixedInterestAmount').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(fixedInterestAmount))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('fixedInterestAmount').focus();
			}, 0);
		loadingstop();
		return false;
	}


	if(idealDepositAmount=="" || idealDepositAmount==null)
	{
		alert("Please Enter IdealDepositAmount");
		window.setTimeout(function ()
		    {
			document.getElementById('idealDepositAmount').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(idealDepositAmount))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('idealDepositAmount').focus();
			}, 0);
		loadingstop();
		return false;
	}



	if(fine=="" || fine==null)
	{
		alert("Please Enter fine");
		window.setTimeout(function ()
		    {
			document.getElementById('fine').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(fine))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('fine').focus();
			}, 0);
		loadingstop();
		return false;
	}



	if(rateOfInterest=="" || rateOfInterest==null)
	{
		alert("Please Enter RateOfInterest");
		window.setTimeout(function ()
		    {
			document.getElementById('rateOfInterest').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(rateOfInterest))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('rateOfInterest').focus();
			}, 0);
		loadingstop();
		return false;
	}
}
</script>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


function updateInterestType(id,tkey,idealDepositAmount,rateOfInterest,fixedInterestAmount,fine,status,interestTypee,typrr)
{
	loadingstop();
	document.getElementById('id01').style.display='block';
	document.getElementById('interestType_uid').value=id;
	document.getElementById('tkey').value=tkey;
    document.getElementById('fixedInterestAmount').value=fixedInterestAmount;
    document.getElementById('idealDepositAmount').value=idealDepositAmount;
    document.getElementById('rateOfInterest').value=rateOfInterest;
    document.getElementById('fine').value=fine;
    document.getElementById('status').value=status;
    document.getElementById('interestTypettt').value=interestTypee;

    if('Special_Loans' == typrr || 'Loans' == typrr)
	{
    	if('Special_Loans' == typrr){
	    document.getElementById('interestTypeTr').style.visibility = 'hidden'; 
	    document.getElementById('interestTypeTr').style.position='absolute';
    	}

	document.getElementById('fixedInterestAmountId').style.visibility = 'hidden'; 
	document.getElementById('fixedInterestAmountId').style.position='absolute';

	document.getElementById('idealDepositAmountId').style.visibility = 'hidden'; 
	document.getElementById('idealDepositAmountId').style.position='absolute';
	}
    if('Application_Forms' == typrr)
	{
	document.getElementById('interestTypeTr').style.visibility = 'hidden'; 
	document.getElementById('interestTypeTr').style.position='absolute';

	document.getElementById('fixedInterestAmountId').style.visibility = 'hidden'; 
	document.getElementById('fixedInterestAmountId').style.position='absolute';

	document.getElementById('idealDepositAmountId').style.visibility = 'hidden'; 
	document.getElementById('idealDepositAmountId').style.position='absolute';

	document.getElementById('FineId').style.visibility = 'hidden'; 
	document.getElementById('FineId').style.position='absolute';

	document.getElementById('rateperqu').innerHTML='RatePerQuantity';
	}
	
}
</script>

</head>

<body onload="changedropdownTra('Deposits','fixedInterestAmountId','idealDepositAmountId')">

<%-- <div id="id0122" class="modal" >
  
  <form class="modal-content animate" action="addInterestType" modelAttribute="InterestType" method="post" onsubmit="return validate();">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id0122').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>Date</th><th>Amount</th><th>Fine</th></tr>
    <tr>
    <tr><td>01-01-2020</td><td>100</td><td>10</td></tr>
    <tr><th>IdealDepositAmount<label style="color: red;"> * </label></th><td><input type="text" id="idealDepositAmount" name="idealDepositAmount" value="100"/></td></tr>
    </table>
      <button style="margin-left: 5%" type="submit">Submit</button>
      <button type="button" onclick="document.getElementById('id0122').style.display='none'" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div> --%>

<div id="id01" class="modal" >
  
  <form class="modal-content animate" action="addInterestType" modelAttribute="InterestType" method="post" onsubmit="return validate();">
    <div class="imgcontainer">
      <span onclick="reload('id01')" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <input style="display: none;position: absolute;" type="text" id="interestType_uid" name="interestType_uid" value="0" />
    <tr><th>TransactionType<label style="color: red;"> * </label></th><td><select id="tkey" class="select-d" onchange="changedropdownTra(this.options[this.selectedIndex].text,'fixedInterestAmountId','idealDepositAmountId')" name="transactionType">
                    <c:forEach items="${transactionTypes }" var="d">
                    <option value="${d.key }">${d.value }</option>
                    </c:forEach>
                    </select></td></tr>
    <tr id="interestTypeTr"><th>InterestType</th><td><select class="select-d" id="interestTypettt" name="interestType" onchange="changedropdownIn(this.options[this.selectedIndex].text)">
                    <c:forEach items="${InterestTypeEnums }" var="d">
                    <option value="${d.key }">${d.value }</option>
                    </c:forEach>
                    </select></td></tr>
    <tr hidden><th>CalculationType</th><td><select class="select-d" name="calculationType">
                    <c:forEach items="${CalculationTypeEnums }" var="d">
                     <option value="${d.key }">${d.value }</option>
                    </c:forEach>
                    </select></td></tr>
    <tr id="fixedInterestAmountId"><th>InitialContribution<label style="color: red;"> * </label></th><td><input type="text" id="fixedInterestAmount" name="fixedInterestAmount" value="500" /></td></tr>
    <tr id="idealDepositAmountId"><th>IdealDepositAmount<label style="color: red;"> * </label></th><td><input type="text" id="idealDepositAmount" name="idealDepositAmount" value="100"/></td></tr>
    <tr id="FineId"><th>Fine<label style="color: red;"> * </label></th><td><input type="text" id="fine" name="fine" value="5" />%</td></tr>
    <tr><th id="rateperqu">RateOfInterest<label style="color: red;"> * </label></th><td><input type="text" id="rateOfInterest" name="interestRate" value="1" />%</td></tr>
    <tr><th>Status<label style="color: red;"> * </label></th><td><select class="select-d" id="status" name="status">
                    <c:forEach items="${statusTypes }" var="d">
                    <option value="${d.key }">${d.value }</option>
                    </c:forEach>
                    </select></td></tr>
    
    </table>
      
        
      <button style="margin-left: 5%" type="submit" onclick="loadingstart()">Submit</button>
      <button type="button" onclick="reload('id01')" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>
     <div align="center">
      
      <h2 class="tablenames">InterestRates &nbsp;&nbsp; <button onclick="document.getElementById('id01').style.display='block'">+</button></h2>
      <table border="0" class="tablecssr">
      <tr class="rowheader">
      <th>TransactionType</th>
       <th>InterestType</th>
       <th hidden>CalculationType</th>
       <th>IdealDepositAmount</th>
       <th>RateOfInterest</th>
       <th>InitialContribution</th>
        <th>Fine</th>
       <th>Status</th>
                </tr>
      
      <c:forEach items="${interestTypeList }" var="d">
      <tr>
       <c:forEach items="${transactionTypes }" var="d1"><c:if test="${d1.key eq d.transactionType }"><td><a href="#" onclick="updateInterestType('${d.interestType_uid }','${d1.key}','${d.idealDepositAmount}','${d.interestRate }','${d.fixedInterestAmount }','${d.fine }','${d.status }','${d.interestType}','${d1.value }')">${d1.value }</a></td></c:if> </c:forEach>
       <c:forEach items="${InterestTypeEnums }" var="d1"><c:if test="${d1.key eq d.interestType }"><td>${d1.value }</td></c:if> </c:forEach>
       <c:forEach items="${CalculationTypeEnums }" var="d1"><c:if test="${d1.key eq d.calculationType }"><td hidden>${d1.value }</td></c:if> </c:forEach>
       <td>${d.idealDepositAmount }</td>
       <td>${d.interestRate }</td>
       <td>${d.fixedInterestAmount }</td>
       <td>${d.fine }</td>
       <c:forEach items="${statusTypes }" var="d1"><c:if test="${d1.key eq d.status }"><td>${d1.value }</td></c:if> </c:forEach>
        </tr>
       </c:forEach>
       </table>
           
    </div>
</body>

</html>