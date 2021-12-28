<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>savings</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

function details(id)
{
	alert(id);
}

</script>
</head>
<body>


<div align="center" id="loaddivid">
<h2 style="color: maroon;font-size: 2vw;">AuditSummary Report</h2>
<table>
<form action="auditSummary" modelAttribute="OccurranceDto" method="post">
<tr>
<th>Select Month:</th>
<td>
<select id="from" name="fromOccurrance">
                    <!-- <option value="">Select</option> -->
                    <c:forEach items="${occuranceIds }" var="i">
                    <option <c:if test="${from eq  i.key}"> selected="selected"  </c:if> value="${i.key }">${i.value }</option>
                    </c:forEach>
                    
</select>
</td>
<td><select id="to" name="toOccurrance">
                    <!-- <option value="">Select</option> -->
                    <c:forEach items="${occuranceIds }" var="i">
                    <option <c:if test="${to eq  i.key}"> selected="selected"  </c:if> value="${i.key }">${i.value }</option>
                    </c:forEach>
                    
</select></td>
<td colspan="2" align="center"><input type="submit" onclick="loadingstart()" value="Submit" /></td>
</tr>
            
 </form>
        </table>
</div>

<div align="center">
      <c:forEach items="${auditSummary }" var="d">
      <table class="tablecssr" style="width: 50%;">
      <c:set var="deposits" value="${d.deposits }"></c:set>
      <c:set var="loans" value="${d.loans }"></c:set>
      <c:set var="specialloan" value="${d.specialLoan }"></c:set>
      <c:set var="specialloanhistory" value="${d.specialLoanHistory }"></c:set>
      <c:set var="loanhistory" value="${d.loansHistory }"></c:set>
      <c:set var="expenseslist" value="${d.expenseslist }"></c:set>
      <c:set var="otherIncomelist" value="${d.otherIncomeList }"></c:set>
      <c:set var="withdrawalCustomerList" value="${d.withdrawalCustomerList }"></c:set>
      <c:set var="unpaidCustomerList" value="${d.unpaidCustomerDtoList }"></c:set>
       <tr class="rowheader"><th>Income Sources</th><th>Rs</th><th>Income Consumptions</th><th>Rs</th></tr>
       <tr><th>Opening Balance</th><td style="text-align: right;">${d.openingBalance }</td><th>LT Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('SimpleLoans').style.display='block'">${d.normalloans }</a></td></tr>
       <tr><th>Monthly Subscriptions</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('MonthlyShares').style.display='block'">${d.monthlyDepositsAmount }</a></td><th>ST Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('SpecialLoans').style.display='block'">${d.specialloans }</a></td></tr>
       <tr><th>New Enrollments</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('NewEnrollments').style.display='block'">${d.newEnrollmentAmounts }</a></td><th></th><td></td></tr>
       <tr><th>Interest On LT Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('InterestOnSimpleLoans').style.display='block'">${d.normalLoanInterestsAmount }</a></td><th>Expenses</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('Expenses1').style.display='block'">${d.expenses }</a></td></tr>
       <tr><th>Interest On ST Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('InterestOnSpecialLoans').style.display='block'">${d.specialLoanInterestsAmount }</a></td><th>Withdrawal Amount</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('withdrawalCustomers').style.display='block'">${d.withdrawalAmount }</a></td></tr>
       <tr><th>Fines</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('Fines').style.display='block'">${d.fines }</a></td> <th>Current Balance</th><td style="text-align: right;">${d.currentBalance }</td></tr>
       <tr><th>Repayment Of LT Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('RepaymentLoans').style.display='block'">${d.repaymentLoans }</a></td><th></th><td></td></tr>
       <tr><th>Repayment Of ST Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('RepaymentSpecialLoans').style.display='block'">${d.repaymentSpecialLoans }</a></td><th></th><td></td></tr>
       <tr><th>Other Income</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('otherincome1').style.display='block'">${d.applicationFormsAmount }</a></td><th></th><td></td></tr>
       <tr><th>Total</th><td style="text-align: right;">${d.openingBalance+d.monthlyDepositsAmount+d.newEnrollmentAmounts+d.normalLoanInterestsAmount+d.specialLoanInterestsAmount+d.fines+d.applicationFormsAmount+d.repaymentLoans+d.repaymentSpecialLoans }</td><th>Total</th><td style="text-align: right;">${d.normalloans+d.specialloans+d.unpaidLoansAmount+d.expenses+d.withdrawalAmount+d.currentBalance }</td></tr>
      </table>
      </c:forEach>
</div>

<div id="unpaidCustomer" class="modal" style="width: 55%;">
  <form class="modal-content animate" action="addInterestType" modelAttribute="InterestType" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('unpaidCustomer').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="font-size: 0.6vw;">
    <tr><th>CID</th><th>CustomerName</th><th>DPendingMonths</th><th>DPendingAmount</th><th>LPendingMonths</th><th>LPendingAmount</th><th>SLPendingMonths</th><th>SLDeposits</th></tr>
    <c:forEach varStatus="s" items="${unpaidCustomerList }" var="d1">
     <c:set var="withdrawalOccurrance" value="${d1.customer }"></c:set>
    <tr><td>${withdrawalOccurrance.customerId }</td><td>${withdrawalOccurrance.customerName }</td><td>${d1.depositPendingMonths }</td><td>${d1.depositAmount }</td><td>${d1.loansPendingMonths }</td><td>${d1.loansAmount }</td><td>${d1.specialLoanPendingMonths }</td><td>${d1.specialLoanAmount }</td></tr>
   
    </c:forEach>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('unpaidCustomer')">Print</button>
      <button type="button" onclick="document.getElementById('unpaidCustomer').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>

<div id="withdrawalCustomers" class="modal" style="width: 55%;">
  <form class="modal-content animate" action="addInterestType" modelAttribute="InterestType" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('withdrawalCustomers').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>JoiningDate</th><th>WithdrawalDate</th><th>Share</th></tr>
    <c:forEach varStatus="s" items="${withdrawalCustomerList }" var="d1">

     <c:set var="withShare" value="${d1.share }"></c:set>
    <c:set var="jOccurrance" value="${d1.occurrance }"></c:set>
     <c:set var="withdrawalOccurrance" value="${d1.withDrawalOccurrance }"></c:set>
    <tr><td>${d1.customerId }</td><td>${d1.customerName }</td><td>${jOccurrance.coveredMonth }</td><td>${withdrawalOccurrance.coveredMonth }</td><td>${d1.share }</td></tr>
   
    </c:forEach>
    <tr><th colspan="4" style="text-align: right;">Total</th><td><b>${withShare }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('withdrawalCustomers')">Print</button>
      <button type="button" onclick="document.getElementById('withdrawalCustomers').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="NewEnrollments" class="modal" style="width: 55%;">
  <form class="modal-content animate" action="addInterestType" modelAttribute="InterestType" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('NewEnrollments').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>JoiningDate</th><th>InitialContribution</th></tr>
    <c:forEach varStatus="s" items="${deposits }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
     <c:if test="${d1.depositType eq 1 }">
    <c:set var="NewEnrollmentssum" value="${NewEnrollmentssum+d1.amountPaid }"></c:set>
    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.depositDate }</td><td>${d1.amountPaid }</td></tr>
    </c:if>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${NewEnrollmentssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('NewEnrollments')">Print</button>
      <button type="button" onclick="document.getElementById('NewEnrollments').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="MonthlyShares" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('MonthlyShares').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>DepositedDate</th><th>Amount</th></tr>
    <c:forEach varStatus="s" items="${deposits }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:if test="${d1.depositType eq 2 or d1.depositType eq 3}">
    <c:set var="MonthlySharessum" value="${MonthlySharessum+d1.amountPaid }"></c:set>
    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.depositDate }</td><td>${d1.amountPaid }</td></tr>
    </c:if>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${MonthlySharessum }</b></td></tr>
    </table>
   <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('MonthlyShares')">Print</button>
      <button type="button" onclick="document.getElementById('MonthlyShares').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="SimpleLoans" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('SimpleLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>SanctionedDate</th><th>LoanPrinciple</th><!-- <th>RemianingLoanAmount</th> --></tr>
    <c:forEach varStatus="s" items="${loans }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="SimpleLoanssum" value="${SimpleLoanssum+d1.loanPrinciple }"></c:set>
    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.loan_paid_date }</td><td>${d1.loanPrinciple }</td><%-- <td>${d1.getLoanPrinciple()-((d1.getLoanPrinciple()/d1.getNoOfInstallments())*d1.getPaidInstallments()) }</td> --%></tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${SimpleLoanssum }</b></td></tr>
    </table>
     <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('SimpleLoans')">Print</button>
      <button type="button" onclick="document.getElementById('SimpleLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="InterestOnSimpleLoans" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('InterestOnSimpleLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>InterestPaidDate</th><th>Amount</th></tr>
	    <c:forEach items="${loanhistory }" var="l">
	    <c:if test="${l.paidStatus eq 'Y' }">
	    <c:set var="InterestOnSimpleLoanssum" value="${InterestOnSimpleLoanssum+l.paidInterest }"></c:set>
	    <c:if test="${l.paidInterest ne 0.0  }">
	     <c:set var="customer1" value="${l.customer }"></c:set>
	    <tr><td>${customer1.customerId }</td><td>${customer1.customerName }</td><td>${l.loanPaidDate }</td><td>${l.paidInterest }</td></tr>
	    </c:if>
	    </c:if>
	    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${InterestOnSimpleLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('InterestOnSimpleLoans')">Print</button>
      <button type="button" onclick="document.getElementById('InterestOnSimpleLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="Fines" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('Fines').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>FinePaidDate</th><th>FineAmount</th> <th>Loans</th></tr>
    <c:forEach varStatus="s" items="${loanhistory }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="Finessum" value="${Finessum+d1.fine }"></c:set>
    <c:if test="${d1.fine ne 0.0  }">
	    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.fine }</td><td>Loans</td></tr>
	    </c:if>
	 </c:forEach>
	 
    <c:forEach varStatus="s" items="${specialloanhistory }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="Finessum" value="${Finessum+d1.fine }"></c:set>
    <c:if test="${d1.fine ne 0.0  }">
	    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.fine }</td><td>SpecialLoans</td></tr>
	    </c:if>
	 </c:forEach>
    
    <c:forEach varStatus="s" items="${deposits }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="Finessum" value="${Finessum+d1.fine }"></c:set>
    <c:if test="${(d1.depositType eq 2 or d1.depositType eq 3) and d1.fine ne 0.0  }">
    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.depositDate }</td><td>${d1.fine }</td><td>Deposits</td></tr>
    </c:if>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${Finessum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('Fines')">Print</button>
      <button type="button" onclick="document.getElementById('Fines').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="InterestOnSpecialLoans" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('InterestOnSpecialLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>SanctionedDate</th><th>InterestAmount</th></tr>
    <c:forEach varStatus="s" items="${specialloanhistory }" var="d1">
    <tr>
    <c:set var="InterestOnSpecialLoanssum" value="${InterestOnSpecialLoanssum+d1.paidInterest }"></c:set>
    <c:if test="${d1.paidInterest ne 0.0  }">
     <td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.paidInterest }</td>
     </c:if>
    </tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${InterestOnSpecialLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('InterestOnSpecialLoans')">Print</button>
      <button type="button" onclick="document.getElementById('InterestOnSpecialLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="SpecialLoans" class="modal" style="width: 55%">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('SpecialLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>SanctionedDate</th><th>InterestAmount</th></tr>
    <c:forEach varStatus="s" items="${specialloan }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="SpecialLoanssum" value="${SpecialLoanssum+d1.loanPrinciple }"></c:set>
    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.loan_paid_date }</td><td>${d1.loanPrinciple }</td></tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${SpecialLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('SpecialLoans')">Print</button>
      <button type="button" onclick="document.getElementById('SpecialLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="Expenses1" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('Expenses1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>ExpenseDetails</th><th>Comments</th><th>Amount</th></tr>
    <c:forEach varStatus="s" items="${expenseslist }" var="d1">
    <c:set var="Expenses1sum" value="${Expenses1sum+d1.amount }"></c:set>
    <tr><td>${d1.expenseName }</td><td>${d1.comments }</td><td>${d1.amount }</td></tr>
    </c:forEach>
    <tr><th colspan="2" style="text-align: right;">Total</th><td><b>${Expenses1sum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 3%;background-color: skyblue;width: 11%;"  onclick="printDiv('Expenses1')">Print</button>
      <button type="button" onclick="document.getElementById('Expenses1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>

<div id="otherincome1" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('otherincome1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>TypeOfSource</th><th>Description</th><th>Amount</th></tr>
    <c:forEach varStatus="s" items="${otherIncomelist }" var="d1">
    <c:set var="Otherincome1sum" value="${Otherincome1sum+d1.amount }"></c:set>
    <tr><td>${d1.type }</td><td>${d1.description }</td><td>${d1.amount }</td></tr>
    </c:forEach>
    <tr><th colspan="2" style="text-align: right;">Total</th><td><b>${Otherincome1sum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 3%;background-color: skyblue;width: 17%;"  onclick="printDiv('otherincome1')">Print</button>
      <button type="button" onclick="document.getElementById('otherincome1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="RepaymentLoans" class="modal" style="width: 65%">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('RepaymentLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>InstallmentNo</th><th>PaidDate</th><th>PaidAmount</th><!-- <th>InterestAmount</th><th>Fine</th> --></tr>
    <c:forEach varStatus="s" items="${loanhistory }" var="d1">
    <c:set var="RepaymentLoanssum" value="${RepaymentLoanssum+d1.paidAmount }"></c:set>
    <c:set var="customer" value="${d1.customer }"></c:set>
    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.installmentId }</td><td>${d1.loanPaidDate }</td><td>${d1.paidAmount }</td><%-- <td>${d1.paidInterest }</td><td>${d1.fine }</td> --%></tr>
    </c:forEach>
    <tr><th colspan="4" style="text-align: right;">Total</th><td><b>${RepaymentLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;"  onclick="printDiv('RepaymentLoans')">Print</button>
      <button type="button" onclick="document.getElementById('RepaymentLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="RepaymentSpecialLoans" class="modal" style="width: 55%">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('RepaymentSpecialLoans').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table>
    <tr><th>CustomerID</th><th>CustomerName</th><th>PaidDate</th><th>PaidAmount</th><!-- <th>Interest</th><th>Fine</th> --></tr>
    <c:forEach varStatus="s" items="${specialloanhistory }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="repaymentSPloan" value="${repaymentSPloan+d1.paidAmount }"></c:set>
    <tr><td>${customer.customerId }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.paidAmount }</td><%-- <td>${d1.paidInterest }</td><td>${d1.fine }</td> --%></tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${repaymentSPloan }</b></td></tr>
    </table>
    <button type="button" style="margin-left: 13%;background-color: skyblue;width: 11%;" onclick="printDiv('RepaymentSpecialLoans')">Print</button>
      <button type="button"  onclick="document.getElementById('RepaymentSpecialLoans').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>

</body>
</html>