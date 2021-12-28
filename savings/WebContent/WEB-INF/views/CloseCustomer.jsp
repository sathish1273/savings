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
<title>savings</title>

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
<script type="text/javascript">
function validationclose()
{
	
	var customerUid=document.getElementById('ccustomerUidclose').value;
	if(customerUid=="" || customerUid==null)
	{
		alert("Please Enter CustometID.");
		window.setTimeout(function ()
		    {
			document.getElementById('ccustomerUidclose').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(customerUid))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('ccustomerUidclose').focus();
			}, 0);
		loadingstop();
		return false;
	}
}
</script>
</head>

<body>
	 <div align="center">
        <form action="closeCustomer" onsubmit="return validationclose();" modelAttribute="Customer" method="POST">
            <table border="1">
                <tr>
                    <th>Enter CustomerId:</th>
                    <td><input type="text" id="ccustomerUidclose" name="customerId" value="${closeCustomerIdfordis }" /></td>
                    <td align="center"><input type="submit" onclick="loadingstart()" value="Submit" /></td>
                </tr>
            </table>
        </form>
    </div>
    
    <c:if test="${finalshare ne null and finalshare ne '' }">
    <div align="center">
     <table>
     <tr style="color: red;"><th><b><c:if test="${fn:length(finalshare.pendingDeposits) gt 0  }"> Deposits Pending... Please Pay through the Payment Tab</c:if></b></th></tr>
     <tr style="color: red;"><th><b><c:if test="${finalshare.pendingLoans ne null  }"> 
     <c:set var="sssssettle" value="${finalshare.pendingLoans }"></c:set> Loans Pending... Please Pay through the Payment Tab</c:if></b></th></tr>
     <tr style="color: red;"><th><b><c:if test="${finalshare.pendingSpLoans ne null }"> SpecialLoans Pending... Please Pay through the Payment Tab</c:if></b></th></tr>
    
     <c:if test="${fn:length(finalshare.pendingDeposits) eq 0 and finalshare.pendingLoans eq null and finalshare.pendingSpLoans eq null}">
     <tr style="align-content: center;"><th><h1>Withdraw Customer</h1></th></tr>
     <c:set var="finalTotal" value="${finalshare.currentBalance+finalshare.totalLoans+finalshare.totalSpLoans+totaldeposits }"></c:set>
     <tr><th>CurrentBalance</th><td style="text-align: right;">${finalshare.currentBalance }</td></tr>
     <tr><th>Deposits</th><td style="text-align: right;">${finalshare.totaldeposits }</td></tr>
     <tr><th>Loans</th><td style="text-align: right;">${finalshare.totalLoans }</td></tr>
     <tr><th>SpecialLoans</th><td style="text-align: right;">${finalshare.totalSpLoans }</td></tr>
     <tr><th>Total</th><td style="text-align: right;">${finalTotal }</td></tr>
     <%-- <tr><th>PendingDeposits</th><td><table>
     <tr><th>CoveredMonth</th><th>NoOfMonths</th><th>Deposits</th><th>Fine</th></tr>
     <c:forEach var="d" items="${finalshare.pendingDeposits }">
     <tr><td>${d.coveredMonth }</td><td>${d.principleAmount }</td><td>${d.noofMonths }</td><td>${d.fine }</td></tr>
     </c:forEach>
     </table></td></tr>
     <c:set var="pLoans" value="${finalshare.pendingLoans }"></c:set>
     <tr><th>PendingLoans</th> <tr><th>LoanPrinciple</th><td>${pLoans.loanPrinciple }</td><th>NoOfInstalments</th><td>${pLoans.noOfInstallments }</td><th>PaidInstallments</th><td>${pLoans.paidInstallments }</td></tr><td><table>
     
    
     <c:forEach var="d" items="${pLoans.loanHistory }">
     <tr><td>${d.paidAmount }</td><td>${d.paidInterest }</td><td>${d.fine }</td></tr>
     </c:forEach>
     </table></td></tr> --%>
     
     <tr><th>ShareOfCandidate</th><td>${finalTotal / finalshare.noOfCustomers }</td></tr>
     </c:if>
     </table>
    </div>
    
    <c:if test="${fn:length(finalshare.pendingDeposits) eq 0 and finalshare.pendingLoans eq null and finalshare.pendingSpLoans eq null }">
     <div align="center">
        <form action="withdrawCustomer" modelAttribute="Customer" method="POST">
            <table border="1">
                <tr>
                    <input style="visibility: hidden;position: absolute;" type="text" id="wccustomerUid" name="customerUid" value="${closeCustomerId }" />
                    <td align="center"><input type="submit" onclick="loadingstart()" value="withdraw" /></td>
                </tr>
            </table>
        </form>
    </div>
    </c:if>
    </c:if>
    
    
    
   <%--  <div>
    <table>
    <tr><th>CoveredMonth</th>
	 <th>depositAmount</th>
	 <th>depositFine</th>
	 <th>loanPrinciple</th>
	 <th>loanSanctionedDate</th>
	 <th>noOfInstallments</th>
	 <th>paidInstallments</th>
	 <th>lhInstallmetAmount</th>
	 <th>installmentNo</th>
	 <th>lhInterest</th>
	 <th>lhloanFine</th>
	 <th>loanClearedDate</th>
	 <th>sploanPrinciple</th>
	 <th>sploanhPaidAmount</th>
	 <th>sploanhInterest</th>
	 <th>sploanhFine</th>
	 <th>sploanSanctionedDate</th>
	 <th>sploanClearedDate</th></tr>
	 <c:forEach items="${personalHistory }" var="d">
    <tr><td>${d.coveredMonth }</td><td>${d.depositAmount }</td><td>${d.depositFine }</td><td>${d.loanPrinciple }</td><td>${d.loanSanctionedDate }</td><td>${d.noOfInstallments }</td><td>${d.paidInstallments }</td><td>${d.lhInstallmetAmount }</td><td>${d.installmentNo }</td>
    <td>${d.lhInterest }</td>
    <td>${d.lhloanFine }</td><td>${d.loanClearedDate }</td><td>${d.sploanPrinciple }</td><td>${d.sploanhPaidAmount }</td><td>${d.sploanhInterest }</td>
    <td>${d.sploanhFine }</td><td>${d.sploanSanctionedDate }</td><td>${d.sploanClearedDate }</td></tr>
   </c:forEach> 
    </table>
    
    </div> --%>
 
  <c:remove var="closeCustomerId"/>
  <c:remove var="finalshare"/>
    
</body>

</html>