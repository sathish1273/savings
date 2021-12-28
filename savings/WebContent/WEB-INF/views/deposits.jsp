<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>Savings</title>

<style>
#ddeposits {
  padding: 50px 0;
  text-align: center;
  margin-top: 20px;
}

#loans { 
  padding: -1px 0;
  text-align: center;
  margin-top: 20px;
}

</style>

</head>

<script type="text/javascript">
function check(tid,total,id,ps)
{
	//loadingstop();
	var checkstatus=document.getElementById(id).checked;
	if(checkstatus == true){
	document.getElementById(ps).value="yes";
	document.getElementById(tid).value=parseInt(document.getElementById(tid).value)+parseInt(total);
	}
	else{
		document.getElementById(ps).value="no";
		document.getElementById(tid).value=parseInt(document.getElementById(tid).value)-parseInt(total);
		}
}

function specialcheck(tid,total,id,ps)
{
	//loadingstop();
	var checkstatus=document.getElementById(id).checked;
	if(checkstatus == true){
	document.getElementById(ps).value="yes";
	document.getElementById('specialltotal').style.visibility = 'visible';
	document.getElementById('specialltotal').style.position='relative';
	}
	else{
		document.getElementById(ps).value="no";
		document.getElementById('specialltotal').style.visibility = 'hidden'; 
		document.getElementById('specialltotal').style.position = 'absolute'; 
		}
}

function loansCheck(amount,id,pid,iid)
{
	//loadingstop();
	var x=0;
	var y=0;
	var checkstatus=document.getElementById(id).checked;
	var mm=document.getElementById('loanstdId').innerHTML;
	var mm1=document.getElementById('totalloanstdId').innerHTML;
	if(mm == "" || isNaN(mm))
		{
		x=0;
		}
	else{
		x=mm;
		y=mm1;
		}
	if(mm1 == "" || isNaN(mm1))
	{
	y=0;
	}
else{
	y=mm1;
	}
	if(checkstatus == true){
	document.getElementById(pid).value=document.getElementById(pid).value+","+iid;
	document.getElementById('pamount').value=parseInt(document.getElementById('pamount').value)+parseInt(amount);
	document.getElementById('ltotal').value=parseInt(document.getElementById('ltotal').value)+parseInt(amount);
	document.getElementById('totalloanstdId').innerHTML=parseInt(y)+parseInt(amount);
	document.getElementById('loanstdId').innerHTML=parseInt(x)+parseInt(amount);
	
	}
	else{
		document.getElementById(pid).value=document.getElementById(pid).value.replace(","+iid, "");
		document.getElementById('pamount').value=parseInt(document.getElementById('pamount').value)-parseInt(amount);
		document.getElementById('ltotal').value=parseInt(document.getElementById('ltotal').value)-parseInt(amount);
		document.getElementById('totalloanstdId').innerHTML=parseInt(y)+parseInt(amount);
		document.getElementById('loanstdId').innerHTML=parseInt(x)-parseInt(amount);
		
		}
}

function validation()
{
	
	var customerId=document.getElementById('customerUid').value;
	if(customerId == '' || customerId == null)
		{
		alert("please enter valid customerId.");
		loadingstop();
		return false;
		}
}

function display(div) {
	  var x = document.getElementById(div);
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}

function displaySuccesspopup1(id)
{
	alert(id);
	//document.getElementById(id).style.display='block';
	alert(id+"kkk");
}

$(document).ready(function(){
	var id=document.getElementById('popuptext').value;
	if(id != "" && id != null){
	document.getElementById(id).style.display='block';
	}
	});

</script>

<script type="text/javascript">
function validateDe()
{
	
	var customerUid = document.getElementById('customerUid').value;

	if(customerUid=="" || customerUid==null)
	{
		alert("Please Enter CustometID.");
		window.setTimeout(function ()
		    {
			document.getElementById('customerUid').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(customerUid))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('customerUid').focus();
			}, 0);
		loadingstop();
		return false;
	}
}


function loansvalidateDe()
{
	loadingstart();
	var LoanAmountcc = document.getElementById('LoanAmountcc').value;
	var NoOfInstallmentscc = document.getElementById('NoOfInstallmentscc').value;

	if(LoanAmountcc=="" || LoanAmountcc==null)
	{
		alert("Please Enter LoanAmount.");
		window.setTimeout(function ()
		    {
			document.getElementById('LoanAmountcc').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(LoanAmountcc))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('LoanAmountcc').focus();
			}, 0);
		loadingstop();
		return false;
	}

	if(NoOfInstallmentscc=="" || NoOfInstallmentscc==null)
	{
		alert("Please Enter NoOfInstallments.");
		window.setTimeout(function ()
		    {
			document.getElementById('NoOfInstallments').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(NoOfInstallmentscc))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('NoOfInstallmentscc').focus();
			}, 0);
		loadingstop();
		return false;
	}
}


function sploansvalidateDe()
{
	loadingstart();
	var LoanAmountcc = document.getElementById('SPLoanAmountcc').value;

	if(LoanAmountcc=="" || LoanAmountcc==null)
	{
		alert("Please Enter SpecialLoanAmount.");
		window.setTimeout(function ()
		    {
			document.getElementById('SPLoanAmountcc').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(LoanAmountcc))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('SPLoanAmountcc').focus();
			}, 0);
		loadingstop();
		return false;
	}
}

</script>



<body>

<input id="popuptext" hidden value="${successpopup }" />
	 <div align="center">
        <form action="searchCustomer" modelAttribute="Customer" method="GET" onsubmit="return validateDe();">
            <table border="1">
                <!-- <tr>
                    <td>NoteId:</td>
                    <td><input type="text" name="noteId" /></td>
                </tr> -->
                <tr>
                    <th>Enter CustomerId:</th>
                    <td><input type="text" id="customerUid" name="customerId" value="${customerIdfordis }" /></td>
                    <td align="center"><input type="submit" onclick="loadingstart()" value="Submit" /></td>
                </tr>
            </table>
        </form>
    </div>
    
     <c:forEach items="${LoanDtoList.loanDto }" var="d" varStatus="status">
     <c:set var="statusL" value="${d.initialLoanStatus }"></c:set>
     </c:forEach>
     
     <c:forEach items="${SpecialLoanDtoList.loanDto }" var="d" varStatus="status">
      <c:set var="statusSL" value="${d.initialLoanStatus }"></c:set>
     </c:forEach>
    
    <c:set var="totlamount" value="0"></c:set>
    <c:if test="${businessMsgList.serviceStatus eq 'SUCCESS' }">
    <table style="border: 1px solid;border-spacing: 2%;margin-top: 5%;margin-left: 43%;width: 20%;">
    <tr><td><p style="color: darkblue;" bold>${customerName }</p></td><td></td></tr>
    <tr style="border: 1px solid;border-spacing: 2%;background-color: bisque;"><th colspan="2" style="border-right: 1px solid;border-spacing: 2%">PendingTransactions</th></tr>
    <tr>
    <th ><c:if test="${fn:length(DepositDtoList.depositdto) gt 0}">
    <!-- <a onclick="document.getElementById('ddeposits').style.display='block'" style="width:10%;"> -->
    <c:forEach items="${DepositDtoList.depositdto }" var="d" varStatus="status">
      <c:set var="ddtotalD" value="${ddtotalD+d.total }"></c:set>
      </c:forEach>Deposits: <c:if test="${ddtotalD gt 0 || ddtotalD ne '' }">&nbsp;<button style="width: 30%;" onclick="document.getElementById('ddeposits').style.display='block'" style="width:10%;">view</button></c:if>
      <c:set var="totlamount" value="${totlamount+ddtotalD }"></c:set>
    <!-- </a> -->
    </c:if></th><td style="border-right: 1px solid;border-spacing: 2%;text-align: right;">${ddtotalD }</td></tr>
    <tr>
    <th><c:if test="${statusL eq 'N' }">
    <!-- <a onclick="document.getElementById('loans').style.display='block'" style="width:10%;"> -->
      <c:forEach items="${LoanDtoList.loanDto }" var="d" varStatus="status">
      <c:forEach items="${d.remainingInstallmentsList }" var="r" varStatus="s">
       <c:if test="${r.status eq true }">
        <c:set var="loanssssamount" value="${loanssssamount+r.amount+r.interest+r.fine }"></c:set>
        </c:if>
        </c:forEach>
      </c:forEach><c:if test="${loanssssamount gt 0 || sssssettle ne '' }"> Loans:&nbsp;<button style="width: 30%;" onclick="document.getElementById('loans').style.display='block'" style="width:10%;">Edit</button></c:if>
      <c:set var="totlamount" value="${totlamount+loanssssamount }"></c:set>
    <!-- </a> -->
    </c:if></th><td id="loanstdId" style="border-right: 1px solid;border-spacing: 2%;text-align: right;">${loanssssamount }</td></tr>
    <tr> <th ><c:if test="${statusSL eq 'N' }">
    <!-- <a onclick="document.getElementById('Simpleloans').style.display='block'" style="width:10%;"> -->
      <c:forEach items="${SpecialLoanDtoList.loanDto }" var="d" varStatus="status">
      <c:set var="specialLoansssss" value="${specialLoansssss+d.proposedPayAmount+d.proposedPayInterest+d.fine }"></c:set>
      </c:forEach>SpLoans:  <c:if test="${specialLoansssss gt 0 || specialLoansssss ne '' }">&nbsp;<button style="width: 30%;" onclick="document.getElementById('Simpleloans').style.display='block'" style="width:10%;">view</button></c:if>
      <c:set var="totlamount" value="${totlamount+specialLoansssss }"></c:set>
    <!-- </a> -->
    </c:if></th><td style="border-right: 1px solid;border-spacing: 2%;text-align: right;">${specialLoansssss }</td>
    </tr>
    <tr><th>Total: </th><td id="totalloanstdId" style="text-align: right;">${totlamount }</td></tr>
     <tr><td colspan="0"><c:if test="${totlamount gt 0 }"><input type="button" style="color: red;margin-left: 46%;" value="Pay" onclick="submitForms()"/></c:if></td><td>
<%--     <c:if test="${statusL eq 'N' }">
    <input type="button" style="color: orange;" onclick="document.getElementById('loans').style.display='block'" value="Edit"/>
    </c:if> --%>
    <c:if test="${fn:length(depositsSuccess) gt 0 or fn:length(LoansSuccess) gt 0 or fn:length(spLoansSuccess) gt 0}">
     <input type="button" value="Print" style="color: green;margin-left: -50%;" onclick="document.getElementById('successdeposits').style.display='block'"/>
    </c:if></td>
     </tr>
    </table>
    &nbsp;
     &nbsp;
      &nbsp;
    <div>
    <table style="margin-left: 45%">
    <tr><th><c:if test="${statusL eq 'Y' and totlamount le 0 }"><a href="#" onclick="document.getElementById('loans').style.display='block'">LT Loans</a></c:if></th>
    <th><c:if test="${statusSL eq 'Y' and totlamount le 0 }"><a href="#" onclick="document.getElementById('Simpleloans').style.display='block'">ST Loans</a></c:if></th>
    </tr>
    </table>
    </div>
    </c:if>
    

   
   
     <div id="ddeposits" align="center" class="modal" style="width: 90%;margin-left: 12%;">
      <form:form class="modal-content animate" id="adddepositsd" name="adddepositsd" action="adddeposits" modelAttribute="DepositDtoList" method="post">
      <div class="imgcontainer">
      <span onclick="document.getElementById('ddeposits').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container" style="font-size: 0.9vw;">
      <table border="0" id="t02">
      <tr><td></td></tr>
      <tr><label><h2>Deposits</h2></label></tr>
      <tr>
       <th></th>
       <th>CoveredMonth</th>
       <th >NoOfMonths</th>
       <th>Principle Amount</th>
      <!--  <th>Interest</th> -->
       <th>Fine</th>
       <th>Total</th>
       </tr>
    
    
      <c:forEach items="${DepositDtoList.depositdto }" var="d" varStatus="status">
      <c:set var="ddtotal" value="${ddtotal+d.total }"></c:set>
       <tr>
       <td><input type="checkbox" id="check${status.index}" checked="checked" disabled onclick="check('total','${d.total }','check${status.index }','paidStatus${status.index }')" /></td>
       <td hidden><form:input path="depositdto[${status.index}].customerId" value="${d.customerId }"/></td>
        <td><form:input class="readonlytd" readonly="true" path="depositdto[${status.index}].coveredMonth" value="${d.coveredMonth }"/></td>
       <td><form:input class="readonlytd" readonly="true" path="depositdto[${status.index}].noofMonths" value="${d.noofMonths }"/></td>
       <td><form:input class="readonlytd" readonly="true" path="depositdto[${status.index}].principleAmount" value="${d.principleAmount }" /></td>
       <%-- <td><form:input path="depositdto[${status.index}].interest" value="${d.interest }" class="form-control" readonly="true"/></td> --%>
       <td><form:input class="readonlytd" readonly="true" path="depositdto[${status.index}].fine" value="${d.fine }"  /></td>
       <td><form:input class="readonlytd" readonly="true" path="depositdto[${status.index}].total" value="${d.total }" /></td>
       <td hidden><form:input path="depositdto[${status.index}].paidStatus" id="paidStatus${status.index}"  value="yes" /></td>
       <td hidden><form:input path="depositdto[${status.index}].occurranceId" value="${d.occurranceId }" /></td>
       </tr>
              
       </c:forEach>
       
       <tr>
       <th style="text-align: right;" colspan="5">Grand Total</th>
       <td><input type="text" id="total" readonly="readonly" value="${ddtotal }"/></td>
       </tr>
       </table>
       <!-- <button type="submit" onclick="loadingstart()">Submit</button> -->
       <button type="button" onclick="document.getElementById('ddeposits').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
    </form:form>
    </div>
    
    
    <div id="successdeposits" align="center" class="modal" style="width: 100%;margin-left: 10%;overflow: auto;">
      <form:form class="modal-content animate" action="adddeposits" modelAttribute="DepositDtoList" method="post">
      <div class="imgcontainer">
      <span onclick="document.getElementById('successdeposits').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container">
      <c:forEach items="${depositsSuccess }" var="d" varStatus="status">
      <c:set var="customer" value="${d.customer }"></c:set>
      <c:set var="institution" value="${d.institution }"></c:set>
      <c:set var="user" value="${d.user }"></c:set>
      <c:set var="rid" value="${d.rid }"></c:set>
      </c:forEach>
      <table style="margin-right: 12%;">
      <tr>
      <td>
      <table style="border: 1px solid;width: 50%;padding: 1px;margin-right: 50%"> 
      <tr>
      <th style="padding-left: 4%"><!-- <span style="color: red;"><h1><h1>&#9729;</h1></h1></span> -->
      ReceiptNo:<b>${rid }</b> <span style="margin-left: 24%;font-size: large;font-size: 0.7vw;">Customer Receipt</span></th>
      <td>
         <table style="margin-left: 15%;">
         <tr><th>${institution.institutionName }</th></tr>
         <c:set var="address" value="${institution.address_id }"></c:set>
         <tr><td>${address.landMark }</td></tr>
         <tr><td>${address.village }</td></tr>
         <tr><td>${address.mandal }</td></tr>
         <tr><td>${address.district }</td></tr>
         <tr><td>${address.state }</td></tr>
         </table>
       </td>
      </tr>
      <tr>
      <td colspan="2" style="padding-left: 42%;" >
      <table style="width: 98%;">
      <tr><td><span style="color: green;">&#10003; Successfully paid..</span></td></tr>
      <tr><td>Date of Issue - ${activeOccurrance.coveredMonth }</td></tr>
      <c:set var="interestType" value="${activeOccurrance.interestType }"></c:set>
      </table></td></tr>
      
      <!-- <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr>
      <td colspan="2"><table style="border: 1px solid;width: 98%;margin-left: 1%">
      <tr><th>CustomerId</th><td>${customer.customerId }</td><th>IdealAmount</th><td>${interestType.idealDepositAmount }</td></tr>
      <tr><th>CustomerName</th><td>${customer.customerName }</td><th>OperatorId</th><td>${user.userId }</td></tr>
      <tr><th>ModeOfPayment</th><td>Cash</td><th>OperatorName</th><td>${user.username }</td></tr>
      </table></td>
      </tr>
     <!--  <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr>
      <td colspan="2">
            <table style="border: 1px solid;width: 98%;margin-left: 1%" id="t02">
      <tr>
       <th>CoveredMonth</th>
       <th>PaidAmount</th>
       <th>Interest</th>
       <th>Fine</th>
       <th>InstallmentNo</th>
       <th>TransactionId</th>
       <th>Type</th>
       </tr>
      <c:forEach items="${depositsSuccess }" var="d" varStatus="status">
      <c:set var="totalamm" value="${totalamm+d.amount+d.fine }"></c:set>
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.amount }</td>
       <td></td>
       <td>${d.fine }</td>
       <td></td>
       <td>${d.transactionId }</td>
       <td>Deposits</td>
      </tr> 
       </c:forEach>
       <c:set var="remaingLtamount" value="0"></c:set>
      <c:forEach items="${LoansSuccess }" var="d" varStatus="status">
      <c:set var="totalamm" value="${totalamm+d.amount+d.fine+d.interest }"></c:set>
      <c:choose>
      <c:when test="${d.indicator eq 'remainingamount' }">
      <c:set var="remaingLtamount" value="${remaingLtamount+d.remainingloanamount}"></c:set>
      </c:when>
      <c:otherwise>
      <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.amount }</td>
       <td>${d.interest }</td>
       <td>${d.fine }</td>
       <td>${d.installmentNo }</td>
       <td>${d.transactionId }</td>
       <td>Loans</td>
      </tr> 
      </c:otherwise>
      </c:choose>
      
       
       </c:forEach>
       
       <c:forEach items="${spLoansSuccess }" var="d" varStatus="status">
       <c:set var="totalamm" value="${totalamm+d.amount+d.fine+d.interest }"></c:set>
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.amount }</td>
       <td>${d.interest }</td>
       <td>${d.fine }</td>
       <td></td>
       <td>${d.transactionId }</td>
       <td>SpecialLoan</td>
      </tr> 
       </c:forEach>
       </table>
      </td>
      </tr>
      
      
  <!--     <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr><td colspan="2"><table><tr><td>Total amount received towards in this month: <b>${totalamm }</b></td></tr></table></td></tr>
      <tr><td colspan="2"><table><tr><td>Remaining LT loan amount: <b>${remaingLtamount }</b></td></tr></table></td></tr>
     <!--  <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr><td colspan="2"><table><tr><td>This Receipt is electronically generated.</td></tr></table></td></tr>
      <tr></tr>

      
      <tr><td colspan="2">&nbsp;</td></tr>
      </table>
      </td>
      <td><c:set var="totalamm" value="0"></c:set></td>
      <td>
      <table style="border: 1px solid;width: 50%;padding: 1px;margin-right: 50%"> 
      <tr>
      <th style="padding-left: 4%"><!-- <span style="color: red;"><h1><h1>&#9729;</h1></h1></span> -->
      ReceiptNo:<b>${rid }</b> <span style="margin-left: 24%;font-size: large;font-size: 0.7vw;">Sangam Receipt</span></th>
      <td>
         <table style="margin-left: 15%;">
         <tr><th>${institution.institutionName }</th></tr>
         <c:set var="address" value="${institution.address_id }"></c:set>
         <tr><td>${address.landMark }</td></tr>
         <tr><td>${address.village }</td></tr>
         <tr><td>${address.mandal }</td></tr>
         <tr><td>${address.district }</td></tr>
         <tr><td>${address.state }</td></tr>
         </table>
       </td>
      </tr>
      <tr>
      <td colspan="2" style="padding-left: 42%;" >
      <table style="width: 98%;">
      <tr><td><span style="color: green;">&#10003; Successfully paid..</span></td></tr>
      <tr><td>Date of Issue - ${activeOccurrance.coveredMonth }</td></tr>
      <c:set var="interestType" value="${activeOccurrance.interestType }"></c:set>
      </table></td></tr>
      
      <!-- <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr>
      <td colspan="2"><table style="border: 1px solid;width: 98%;margin-left: 1%">
      <tr><th>CustomerId</th><td>${customer.customerId }</td><th>IdealAmount</th><td>${interestType.idealDepositAmount }</td></tr>
      <tr><th>CustomerName</th><td>${customer.customerName }</td><th>OperatorId</th><td>${user.userId }</td></tr>
      <tr><th>ModeOfPayment</th><td>Cash</td><th>OperatorName</th><td>${user.username }</td></tr>
      </table></td>
      </tr>
     <!--  <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr>
      <td colspan="2">
            <table style="border: 1px solid;width: 98%;margin-left: 1%" id="t02">
      <tr>
       <th>CoveredMonth</th>
       <th>PaidAmount</th>
       <th>Interest</th>
       <th>Fine</th>
       <th>InstallmentNo</th>
       <th>TransactionId</th>
       <th>Type</th>
       </tr>
      <c:forEach items="${depositsSuccess }" var="d" varStatus="status">
      <c:set var="totalamm" value="${totalamm+d.amount+d.fine }"></c:set>
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.amount }</td>
       <td></td>
       <td>${d.fine }</td>
       <td></td>
       <td>${d.transactionId }</td>
       <td>Deposits</td>
      </tr> 
       </c:forEach>
       
         <c:set var="remaingLtamount" value="0"></c:set>
      <c:forEach items="${LoansSuccess }" var="d" varStatus="status">
      <c:set var="totalamm" value="${totalamm+d.amount+d.fine+d.interest }"></c:set>
      <c:choose>
      <c:when test="${d.indicator eq 'remainingamount' }">
      <c:set var="remaingLtamount" value="${remaingLtamount+d.remainingloanamount}"></c:set>
      </c:when>
      <c:otherwise>
      <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.amount }</td>
       <td>${d.interest }</td>
       <td>${d.fine }</td>
       <td>${d.installmentNo }</td>
       <td>${d.transactionId }</td>
       <td>Loans</td>
      </tr> 
      </c:otherwise>
      </c:choose>
      </c:forEach>
       
       <c:forEach items="${spLoansSuccess }" var="d" varStatus="status">
       <c:set var="totalamm" value="${totalamm+d.amount+d.fine+d.interest }"></c:set>
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.amount }</td>
       <td>${d.interest }</td>
       <td>${d.fine }</td>
       <td></td>
       <td>${d.transactionId }</td>
       <td>SpecialLoan</td>
      </tr> 
       </c:forEach>
       </table>
      </td>
      </tr>
      
      
  <!--     <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr><td colspan="2"><table><tr><td>Total amount received towards in this month: <b>${totalamm }</b></td></tr></table></td></tr>
      <tr><td colspan="2"><table><tr><td>Remaining LT loan amount: <b>${remaingLtamount }</b></td></tr></table></td></tr>
     <!--  <tr><td colspan="2">&nbsp;</td></tr> -->
      <tr><td colspan="2"><table><tr><td>This Receipt is Electronically generated.</td></tr></table></td></tr>
      <tr></tr>

      
     <tr><td colspan="2">&nbsp;</td></tr>
      </table>
      </td>
      
      </tr>
      </table>
     
       <button type="button" onclick="printDiv('successdeposits')">Print</button>
       <button type="button" onclick="document.getElementById('successdeposits').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
    </form:form>
    </div>
    
      <div id="loans" class="modal">
      <form:form class="modal-content animate" onsubmit="return loansvalidateDe();" id="addloansl" name="addloansl" action="addloans"  modelAttribute="LoanDtoList" method="post">
      <div class="imgcontainer">  
      <span onclick="document.getElementById('loans').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
     
      <div  class="container" style="font-size: 1.0vw;">
      <table border="0" id="t02">
      <tr><label><h2 style="margin-left: -747%">LongTerm Loans</h2></label></tr>
       <c:forEach items="${LoanDtoList.loanDto }" var="d" varStatus="status">
     
       <tr hidden><th>CustomerId</th><td ><form:input path="loanDto[${status.index}].customeId" value="${d.customeId }"/></td></tr>
       <tr hidden><th>interestId</th><td ><form:input path="loanDto[${status.index}].interestTypeId" value="${d.interestTypeId }" readonly="true"/></td></tr>
       <c:if test="${d.initialLoanStatus eq 'N' }">
       <tr><th>LoanSanctionDate</th><td><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].loanSanctionDate" value="${d.loanSanctionDate }" /></td></tr>
       <tr><th>Loan Amount</th><td><form:input id="LoanAmountcc" class="readonlytd" readonly="true"  path="loanDto[${status.index}].amount" value="${d.amount }" /></td></tr>
       <tr><th>NoOfInstallments</th><td><form:input id="NoOfInstallmentscc" class="readonlytd" readonly="true" path="loanDto[${status.index}].noOfInstallments" value="${d.noOfInstallments }"/></td></tr>
       </c:if>
       
       <c:if test="${d.initialLoanStatus eq 'Y' }">
       <tr><th>Loan Amount</th><td><form:input id="LoanAmountcc"  path="loanDto[${status.index}].amount" value="${d.amount }" /></td></tr>
       <tr><th>NoOfInstallments</th><td><form:input id="NoOfInstallmentscc" path="loanDto[${status.index}].noOfInstallments" value="${d.noOfInstallments }"/></td></tr>
       <tr><th>Introducer</th><td>
       <form:select path="loanDto[${status.index}].introducer" style="padding-right: 35%;">
       <c:forEach items="${d.customerlist }" var="c">
       <form:option value="${c.customerUid }" label="${c.customerName }"/>  
       </c:forEach>
       </form:select></td></tr></c:if>
       <c:if test="${d.initialLoanStatus eq 'N' }">
       
       <tr><th>PaidAmount</th><td ><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].paidAmount" value="${d.paidAmount }" /></td></tr>
       <tr><th>PaidInstallments</th><td><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].paidInstallments" value="${d.paidInstallments }" /></td></tr>
       <tr><th>PaidInterest</th><td ><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].paidInterest" value="${d.paidInterest }" /></td></tr>
       <tr hidden><th>RemainingInstallments</th><td><form:input path="loanDto[${status.index}].remainingInstallments" value="${d.remainingInstallments }" /></td></tr>
       <tr hidden><th>RemainingAmount</th><td><form:input path="loanDto[${status.index}].remainingAmount" value="${d.remainingAmount }" /></td></tr>
       <tr>
       <th>PendingInstallments</th>
       <td>
      <table>
      <tr><th>InstallmentId</th><th>Interest</th><th>Fine</th></tr>
       <c:forEach items="${d.remainingInstallmentsList }" var="r" varStatus="s">
       <tr><td>
       <c:if test="${r.status eq true }">
       <c:set var="proposedPayAmount" value="${proposedPayAmount+r.amount }"></c:set>
       <c:set var="proposedPayInterest" value="${proposedPayInterest+r.interest }"></c:set>
       <c:set var="proposedPayFine" value="${proposedPayFine+r.fine }"></c:set>
       <c:set var="firstInstallmentId" value="${firstInstallmentId },${r.month }#${r.interest }#${r.fine }"></c:set>
       </c:if>
       <input type="checkbox" id="${r.month}r" onClick="loansCheck('${r.amount}','${r.month}r','${status.index}p','${r.month}#${r.interest }#${r.fine }')" <c:if test="${r.status eq true }">checked="checked" checked disabled</c:if> />
       ${r.month}
       <c:choose>
       <c:when test="${r.month eq 1  }">st</c:when>
       <c:when test="${r.month eq 2 }">nd</c:when>
       <c:when test="${r.month eq 3 }">rd</c:when>
       <c:otherwise>
       th
       </c:otherwise>
       </c:choose>
       </td><td> ${r.interest }</td><td> ${r.fine }</td>
       </tr>
       </c:forEach>
       </table>
       
       </td>
       </tr>
       <tr><th>ProposedToPayAmount</th><td ><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].proposedPayAmount" value="${proposedPayAmount}" id="pamount" /></td></tr> 
       <tr><th>ProposedToPayInterest</th><td ><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].proposedPayInterest" value="${proposedPayInterest }" /></td></tr> 
       <tr><th>ProposedToPayFine</th><td>${proposedPayFine }</td></tr>
      
       </c:if> 
       <tr hidden><th>CurrentPaidInstallments</th><td ><form:input path="loanDto[${status.index}].currentPaidInstallmentIds" value="${firstInstallmentId }" id="${status.index}p" /></td></tr> 
       <tr hidden><th>LoanId</th><td ><form:input path="loanDto[${status.index}].loanId" value="${d.loanId }" /></td></tr> 
       <tr hidden><th>Occurrance</th><td ><form:input path="loanDto[${status.index}].occurranceId" value="${d.occurranceId }" /></td></tr>    
      
       <c:if test="${d.initialLoanStatus eq 'N' }">
       <tr>
       <th>Grand Total</th>
       <td><input class="readonlytd" readonly="true" type="text" id="ltotal" value="${proposedPayAmount+proposedPayInterest+proposedPayFine}"/></td>
       </tr>
       </c:if>
       <c:set var="initialLoanStatus" value="${d.initialLoanStatus }"></c:set>
        </c:forEach>
       </table>
    </div>
        <c:if test="${initialLoanStatus eq 'Y'}"> <button type="submit" onclick="loadingstart()">Submit</button></c:if>
       <button type="button" onclick="document.getElementById('loans').style.display='none'" class="cancelbtn">Cancel</button>
    </form:form> 
    </div>  
    
    
    <div id="successLoans" align="center" class="modal" style="width: 100%;margin-left: 10%">
      <form:form class="modal-content animate" action="adddeposits" modelAttribute="DepositDtoList" method="post">
      <div class="imgcontainer">
      <span onclick="document.getElementById('successLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container">
      <c:forEach items="${LoansSuccess }" var="d" varStatus="status">
      <c:set var="customer" value="${d.customer }"></c:set>
      <c:set var="institution" value="${d.institution }"></c:set>
      <c:set var="successll" value="${d.note }"></c:set>
      </c:forEach>
      <table>
      <tr><th><h2>${institution.institutionName }</h2></th></tr>
      <tr><th>CustomerId</th><td>${customer.customerId }</td></tr>
      <tr><th>CustomerName</th><td>${customer.customerName }</td></tr>
      </table>
      
      <table border="0" id="t02">
      <tr><span><h1>${successll }</h1></span></tr>
      <tr><span style="color: green;"><h1>&#10003;</h1>Loan Successfully paid..</span></tr>
      <tr>
       <th>CoveredMonth</th>
       <th>InstallmentNo</th>
       <th>PaidAmount</th>
       <th>Interest</th>
       <th>Fine</th>
       <th>TransactionId</th>
       </tr>
    
    
      <c:forEach items="${LoansSuccess }" var="d" varStatus="status">
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.installmentNo }</td>
       <td>${d.amount }</td>
       <td>${d.interest }</td>
       <td>${d.fine }</td>
       <td>${d.transactionId }</td>
      </tr> 
       </c:forEach>
       </table>
       <button type="button" onclick="printDiv('successLoans')">Print</button>
       <button type="button" onclick="document.getElementById('successLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
    </form:form>
    </div>
    
    
    <div id="successLoansInitiate" align="center" class="modal" style="width: 100%;margin-left: 10%">
      <form:form class="modal-content animate">
      <div class="imgcontainer">
      <span onclick="document.getElementById('successLoansInitiate').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container">
      <c:forEach items="${LoansSuccessIni }" var="d" varStatus="status">
      <c:set var="customer" value="${d.customer }"></c:set>
      <c:set var="introducer" value="${d.introducer }"></c:set>
      <c:set var="institution" value="${d.institution }"></c:set>
      </c:forEach>
      <table>
      <tr><th><h2>${institution.institutionName }</h2></th></tr>
      <tr><th>CustomerId</th><td>${customer.customerId }</td></tr>
      <tr><th>CustomerName</th><td>${customer.customerName }</td></tr>
      
      <tr><th>MediatorId</th><td>${introducer.customerUid }</td></tr>
      <tr><th>MediatorName</th><td>${introducer.customerName }</td></tr>
      </table>
      
      <table border="0" id="t02">
      <tr><span style="color: green;"><h1>&#10003;</h1>Loan issued successfully..</span></tr>
      <tr>
       <th>CoveredMonth</th>
       <th>LoanSanctionedDate</th>
       <th>PrincipleAmount</th>
       <th>NoOfInstallments</th>
       <th>Interest</th>
       <th>Fine</th>
       <th>TransactionId</th>
       </tr>
    
    
      <c:forEach items="${LoansSuccessIni }" var="d" varStatus="status">
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.loanIssuedTime }</td>
       <td>${d.amount }</td>
        <td>${d.installmentNo }</td>
       <td>${d.interest } % </td>
       <td>${d.fine } % </td>
       <td>${d.transactionId }</td>
      </tr> 
       </c:forEach>
      <tr><td></td></tr>
      <tr><td></td></tr>
       </table>
       <table><tr><th><span>Note: I here by declare that i will pay the loan as per the Sangam guidelines.</span></th></tr></table>
       
       <button type="button" onclick="printDiv('successLoansInitiate')">Print</button>
       <button type="button" onclick="document.getElementById('successLoansInitiate').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
    </form:form>
    </div>
    
    
    <div id="spsuccessLoansInitiate" align="center" class="modal" style="width: 100%;margin-left: 10%">
      <form:form class="modal-content animate">
      <div class="imgcontainer">
      <span onclick="document.getElementById('spsuccessLoansInitiate').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container">
      <c:forEach items="${spLoansSuccessIni }" var="d" varStatus="status">
      <c:set var="customer" value="${d.customer }"></c:set>
      <c:set var="introducer" value="${d.introducer }"></c:set>
      <c:set var="institution" value="${d.institution }"></c:set>
      </c:forEach>
      <table>
      <tr><th><h2>${institution.institutionName }</h2></th></tr>
      <tr><th>CustomerId</th><td>${customer.customerId }</td></tr>
      <tr><th>CustomerName</th><td>${customer.customerName }</td></tr>
      
      <tr><th>MediatorId</th><td>${introducer.customerUid }</td></tr>
      <tr><th>MediatorName</th><td>${introducer.customerName }</td></tr>
      </table>
      
      <table border="0" id="t02">
      <tr><span style="color: green;"><h1>&#10003;</h1>SpecialLoan issued successfully..</span></tr>
      <tr>
       <th>CoveredMonth</th>
       <th>LoanSanctionedDate</th>
       <th>PrincipleAmount</th>
       <th>NoOfInstallments</th>
       <th>Interest</th>
       <th>Fine</th>
       <th>TransactionId</th>
       </tr>
    
    
      <c:forEach items="${spLoansSuccessIni }" var="d" varStatus="status">
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.loanIssuedTime }</td>
       <td>${d.amount }</td>
        <td>${d.installmentNo }</td>
       <td>${d.interest } % </td>
       <td>${d.fine } % </td>
       <td>${d.transactionId }</td>
      </tr> 
       </c:forEach>
      <tr><td></td></tr>
      <tr><td></td></tr>
       </table>
       <table><tr><th><span>Note: I here by declare that i will pay the loan as per the Sangam guidelines.</span></th></tr></table>
       
       <button type="button" onclick="printDiv('spsuccessLoansInitiate')">Print</button>
       <button type="button" onclick="document.getElementById('spsuccessLoansInitiate').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
    </form:form>
    </div>
    
    
    <div id="Simpleloans" class="modal">
      <form:form class="modal-content animate" onsubmit="return sploansvalidateDe();" name="addSpecialloanss" id="addSpecialloanss" action="addSpecialloans" modelAttribute="SpecialLoanDtoList" method="post">
      <div class="imgcontainer">  
      <span onclick="document.getElementById('Simpleloans').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
     
       <div  class="container">
      <table border="0" id="t02">
      <tr><input type="checkbox" hidden id="specialcheck1" onclick="specialcheck('specialltotal','0','specialcheck1','spaidStatus')" /><label style="margin-left: 7%;">ShortTerm Loans</label></tr>
       <c:forEach items="${SpecialLoanDtoList.loanDto }" var="d" varStatus="status">
       <tr hidden><th>LoanSeekerName</th><td ><form:input path="loanDto[${status.index}].customeId" value="${d.customeId }"/></td></tr>
       <tr hidden><th>interestId</th><td ><form:input path="loanDto[${status.index}].interestTypeId" value="${d.interestTypeId }" readonly="true"/></td></tr>
       <c:if test="${d.initialLoanStatus eq 'N' }">
       <tr><th>LoanSanctionDate</th><td><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].loanSanctionDate" value="${d.loanSanctionDate }" /></td></tr>
       </c:if>
       
       <c:if test="${d.initialLoanStatus eq 'Y' }">
       <tr><th>LoanAmount</th><td><form:input  id="SPLoanAmountcc" path="loanDto[${status.index}].amount" value="${d.amount }" /></td></tr>
       <tr><th>Introducer</th><td>
       <form:select path="loanDto[${status.index}].introducer">
       <c:forEach items="${d.customerlist }" var="c">
       <form:option value="${c.customerUid }" label="${c.customerName }"/>  
       </c:forEach>
       </form:select></td></tr></c:if>
       <c:if test="${d.initialLoanStatus eq 'N' }">
       <tr><th>LoanAmount</th><td><form:input  class="readonlytd" readonly="true" id="SPLoanAmountcc" path="loanDto[${status.index}].amount" value="${d.amount }" /></td></tr>
       <tr><th>ProposedToPayAmount</th><td ><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].proposedPayAmount" value="${d.proposedPayAmount}" id="pamount" /></td></tr> 
       <tr><th>ProposedToPayInterest</th><td ><form:input class="readonlytd" readonly="true"  path="loanDto[${status.index}].proposedPayInterest" value="${d.proposedPayInterest }" /></td></tr> 
       <tr><th>ProposedToPayFine</th><td><form:input class="readonlytd" readonly="true" path="loanDto[${status.index}].fine" value="${d.fine }" /></td></tr>
      
       </c:if> 
       <tr hidden><th>CurrentPaidInstallments</th><td ><form:input path="loanDto[${status.index}].currentPaidInstallmentIds" value="${firstInstallmentId }" id="${status.index}p" /></td></tr> 
       <tr hidden><th>LoanId</th><td ><form:input path="loanDto[${status.index}].loanId" value="${d.loanId }" /></td></tr> 
       <tr hidden><th>Occurrance</th><td ><form:input path="loanDto[${status.index}].occurranceId" value="${d.occurranceId }" /></td></tr>    
      <tr hidden><th>paidStatus</th><td ><form:input id="spaidStatus" path="loanDto[${status.index}].currentPaidInstallmentIds" value="no" /></td></tr> 
       <c:if test="${d.initialLoanStatus eq 'N' }">
       <tr id="specialltotal" style="visibility: hidden;position: absolute;">
       <th>Grand Total</th>
       <td><input class="readonlytd" readonly="true" type="text"  value="${d.proposedPayAmount+d.proposedPayInterest+d.fine }"/></td>
       </tr>
       </c:if>
       <c:set value="${d.initialLoanStatus}" var="initialLoanStatus22"></c:set>
        </c:forEach>
       </table>
    </div>
    <c:if test="${initialLoanStatus22 eq 'Y' }">
       <button style="margin-left: 40%;" type="submit" onclick="loadingstart()">Submit</button>
       </c:if>
       <button type="button" onclick="document.getElementById('Simpleloans').style.display='none'" class="cancelbtn">Cancel</button>
    </form:form> 
    </div>  
    
    <div id="successspLoans" align="center" class="modal" style="width: 100%;margin-left: 10%">
      <form:form class="modal-content animate" action="adddeposits" modelAttribute="DepositDtoList" method="post">
      <div class="imgcontainer">
      <span onclick="document.getElementById('successspLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div  class="container">
      <c:forEach items="${spLoansSuccess }" var="d" varStatus="status">
      <c:set var="customer" value="${d.customer }"></c:set>
      <c:set var="institution" value="${d.institution }"></c:set>
      </c:forEach>
      <table>
      <tr><th><h2>${institution.institutionName }</h2></th></tr>
      <tr><th>CustomerId</th><td>${customer.customerId }</td></tr>
      <tr><th>CustomerName</th><td>${customer.customerName }</td></tr>
      </table>
      
      <table border="0" id="t02">
      <tr><span style="color: green;"><h1>&#10003;</h1>Special Loan Successfully paid..</span></tr>
      <tr>
       <th>CoveredMonth</th>
       <th>PaidAmount</th>
       <th>Interest</th>
       <th>Fine</th>
       <th>TransactionId</th>
       </tr>
    
    
      <c:forEach items="${spLoansSuccess }" var="d" varStatus="status">
       <tr>
       <td>${d.coveredMonth }</td>
       <td>${d.amount }</td>
       <td>${d.interest }</td>
       <td>${d.fine }</td>
       <td>${d.transactionId }</td>
      </tr> 
       </c:forEach>
       </table>
       <button type="button" onclick="printDiv('successspLoans')">Print</button>
       <button type="button" onclick="document.getElementById('successspLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
    </form:form>
    </div>
    <c:remove var="depositsSuccess"/>
     <c:remove var="LoansSuccess"/>
     <c:remove var="spLoansSuccess"/>
     <c:remove var="successpopup"/>
     <c:remove var="customerName"/>
</body>

</html>