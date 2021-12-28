
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<body>
    
     <div align="center">
      <h2 class="tablenames">UnpaidCustomers</h2>
      <c:forEach items="${auditSummary }" var="d">
	<c:set var="unpaidCustomerList" value="${d.unpaidCustomerDtoList }"></c:set>
	</c:forEach>
    <div class="container" id="adddivInterestType">
    <table class="tablecssr" style="font-size: 1vw;">
    <tr class="rowheader"><th>CID</th><th>CustomerName</th><th>DPendingMonths</th><th>DPendingAmount</th><th>LPendingMonths</th><th>LPendingAmount</th><th>SLPendingMonths</th><th>SLDeposits</th></tr>
    <c:forEach varStatus="s" items="${unpaidCustomerList }" var="d1">
     <c:set var="withdrawalOccurrance" value="${d1.customer }"></c:set>
    <tr><td>${withdrawalOccurrance.customerUid }</td><td>${withdrawalOccurrance.customerName }</td><td>${d1.depositPendingMonths }</td><td>${d1.depositAmount }</td><td>${d1.loansPendingMonths }</td><td>${d1.loansAmount }</td><td>${d1.specialLoanPendingMonths }</td><td>${d1.specialLoanAmount }</td></tr>
   
    </c:forEach>
    </table>
     </div>
          
    </div>
</body>

</html>