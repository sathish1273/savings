<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>savings</title>
</head>

<body>
    <input style="visibility: hidden;position: absolute;" id="lpopuptext" value="${successpopupledgerSum }" />
 <%-- <c:remove var="successpopup"/> --%>
     <div align="center">
     <h2 style="color: maroon;font-size: 2vw">LedgerSummary Report-${ledgerSummaryList.coveredMonth }</h2>
     
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
       <tr class="rowheader"><th>IN</th><th>Rs</th><th>OUT</th><th>Rs</th></tr>
       <tr><th>Opening Balance</th><td style="text-align: right;">${d.openingBalance }</td><th>LT Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('SimpleLoans1').style.display='block'">${d.normalloans }</a></td></tr>
       <tr><th>Monthly Subscriptions</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('MonthlyShares1').style.display='block'">${d.monthlyDepositsAmount }</a></td><th>ST Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('SpecialLoans1').style.display='block'">${d.specialloans }</a></td></tr>
       <tr><th>New Enrollments</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('NewEnrollments1').style.display='block'">${d.newEnrollmentAmounts }</a></td><th></th><td></td></tr>
       <tr><th>Interest on LT loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('InterestOnSimpleLoans1').style.display='block'">${d.normalLoanInterestsAmount }</a></td><th>Expenses</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('Expenses11').style.display='block'">${d.expenses }</a></td></tr>
       <tr><th>Interest on ST Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('InterestOnSpecialLoans1').style.display='block'">${d.specialLoanInterestsAmount }</a></td><th>Withdrawal Amount</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('withdrawalCustomers1').style.display='block'">${d.withdrawalAmount }</a></td></tr>
       <tr><th>Fines</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('Fines1').style.display='block'">${d.fines }</a></td> <th>Current Balance</th><td style="text-align: right;">${d.currentBalance }</td></tr>
       <tr><th>Repayment of LT Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('RepaymentLoans1').style.display='block'">${d.repaymentLoans }</a></td><th></th><td></td></tr>
       <tr><th>Repayment of ST Loans</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('RepaymentSpecialLoans1').style.display='block'">${d.repaymentSpecialLoans }</a></td><th></th><td></td></tr>
       <tr><th>Other Income</th><td style="text-align: right;"><a href="#" onclick="document.getElementById('otherincome11').style.display='block'">${d.applicationFormsAmount }</a></td><th></th><td></td></tr>
       <tr><th>Total</th><td style="text-align: right;">${d.openingBalance+d.monthlyDepositsAmount+d.newEnrollmentAmounts+d.normalLoanInterestsAmount+d.specialLoanInterestsAmount+d.fines+d.applicationFormsAmount+d.repaymentLoans+d.repaymentSpecialLoans }</td><th>Total</th><td style="text-align: right;">${d.normalloans+d.specialloans+d.unpaidLoansAmount+d.expenses+d.withdrawalAmount+d.currentBalance }</td></tr>
      </table>
      </c:forEach>
      </div>
      
      
      
<div id="withdrawalCustomers1" class="modal" style="width: 55%;">
  <form class="modal-content animate" action="addInterestType" modelAttribute="InterestType" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('withdrawalCustomers1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -45%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>JoiningDate</th><th>WithdrawalDate</th><th>Share</th></tr>
    <c:forEach varStatus="s" items="${withdrawalCustomerList }" var="d1">

     <c:set var="withShare" value="${d1.share }"></c:set>
    <c:set var="jOccurrance" value="${d1.occurrance }"></c:set>
     <c:set var="withdrawalOccurrance" value="${d1.withDrawalOccurrance }"></c:set>
    <tr><td>${d1.customerUid }</td><td>${d1.customerName }</td><td>${jOccurrance.coveredMonth }</td><td>${withdrawalOccurrance.coveredMonth }</td><td>${d1.share }</td></tr>
   
    </c:forEach>
    <tr><th colspan="4" style="text-align: right;">Total</th><td><b>${withShare }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('withdrawalCustomers1')">Print</button>
      <button type="button" onclick="document.getElementById('withdrawalCustomers1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="NewEnrollments1" class="modal" style="width: 55%;">
  <form class="modal-content animate" action="addInterestType" modelAttribute="InterestType" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('NewEnrollments1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>JoiningDate</th><th>InitialContribution</th></tr>
    <c:forEach varStatus="s" items="${deposits }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
     <c:if test="${d1.depositType eq 1 }">
    <c:set var="NewEnrollmentssum" value="${NewEnrollmentssum+d1.amountPaid }"></c:set>
    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.depositDate }</td><td>${d1.amountPaid }</td></tr>
    </c:if>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${NewEnrollmentssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left:  -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('NewEnrollments1')">Print</button>
      <button type="button" onclick="document.getElementById('NewEnrollments1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="MonthlyShares1" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('MonthlyShares1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>DepositedDate</th><th>Amount</th></tr>
    <c:forEach varStatus="s" items="${deposits }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:if test="${d1.depositType eq 2 or d1.depositType eq 3}">
    <c:set var="MonthlySharessum" value="${MonthlySharessum+d1.amountPaid }"></c:set>
    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.depositDate }</td><td>${d1.amountPaid }</td></tr>
    </c:if>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${MonthlySharessum }</b></td></tr>
    </table>
   <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('MonthlyShares1')">Print</button>
      <button type="button" onclick="document.getElementById('MonthlyShares1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="SimpleLoans1" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('SimpleLoans1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>SanctionedDate</th><th>LoanPrinciple</th><!-- <th>RemianingLoanAmount</th> --></tr>
    <c:forEach varStatus="s" items="${loans }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="SimpleLoanssum" value="${SimpleLoanssum+d1.loanPrinciple }"></c:set>
    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.loan_paid_date }</td><td>${d1.loanPrinciple }</td><%-- <td>${d1.getLoanPrinciple()-((d1.getLoanPrinciple()/d1.getNoOfInstallments())*d1.getPaidInstallments()) }</td> --%></tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${SimpleLoanssum }</b></td></tr>
    </table>
     <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('SimpleLoans1')">Print</button>
      <button type="button" onclick="document.getElementById('SimpleLoans1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="InterestOnSimpleLoans1" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('InterestOnSimpleLoans1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>InterestPaidDate</th><th>Amount</th></tr>
    <c:forEach varStatus="s" items="${loans }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    </c:forEach>
	    <c:forEach items="${loanhistory }" var="l">
	    <c:if test="${l.paidStatus eq 'Y' }">
	    <c:set var="InterestOnSimpleLoanssum" value="${InterestOnSimpleLoanssum+l.paidInterest }"></c:set>
	    <c:if test="${l.paidInterest ne 0.0  }">
	    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${l.loanPaidDate }</td><td>${l.paidInterest }</td></tr>
	    </c:if>
	    </c:if>
	    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${InterestOnSimpleLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('InterestOnSimpleLoans1')">Print</button>
      <button type="button" onclick="document.getElementById('InterestOnSimpleLoans1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="Fines1" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('Fines1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>FinePaidDate</th><th>FineAmount</th> <th>Loans</th></tr>
    <c:forEach varStatus="s" items="${loanhistory }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="Finessum" value="${Finessum+d1.fine }"></c:set>
    <c:if test="${d1.fine ne 0.0  }">
	    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.fine }</td><td>Loans</td></tr>
	    </c:if>
	 </c:forEach>
	 
    <c:forEach varStatus="s" items="${specialloanhistory }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="Finessum" value="${Finessum+d1.fine }"></c:set>
    <c:if test="${d1.fine ne 0.0  }">
	    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.fine }</td><td>SpecialLoans</td></tr>
	    </c:if>
	 </c:forEach>
    
    <c:forEach varStatus="s" items="${deposits }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="Finessum" value="${Finessum+d1.fine }"></c:set>
    <c:if test="${(d1.depositType eq 2 or d1.depositType eq 3) and d1.fine ne 0.0  }">
    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.depositDate }</td><td>${d1.fine }</td><td>Deposits</td></tr>
    </c:if>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${Finessum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('Fines1')">Print</button>
      <button type="button" onclick="document.getElementById('Fines1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="InterestOnSpecialLoans1" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('InterestOnSpecialLoans1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>SanctionedDate</th><th>InterestAmount</th></tr>
    <c:forEach varStatus="s" items="${specialloanhistory }" var="d1">
    <tr>
    <c:set var="InterestOnSpecialLoanssum" value="${InterestOnSpecialLoanssum+d1.paidInterest }"></c:set>
    <c:if test="${d1.paidInterest ne 0.0  }">
     <td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.paidInterest }</td>
     </c:if>
    </tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${InterestOnSpecialLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('InterestOnSpecialLoans1')">Print</button>
      <button type="button" onclick="document.getElementById('InterestOnSpecialLoans1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="SpecialLoans1" class="modal" style="width: 55%">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('SpecialLoans1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>SanctionedDate</th><th>InterestAmount</th></tr>
    <c:forEach varStatus="s" items="${specialloan }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="SpecialLoanssum" value="${SpecialLoanssum+d1.loanPrinciple }"></c:set>
    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.loan_paid_date }</td><td>${d1.loanPrinciple }</td></tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${SpecialLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('SpecialLoans1')">Print</button>
      <button type="button" onclick="document.getElementById('SpecialLoans1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="Expenses11" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('Expenses11').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>ExpenseDetails</th><th>Comments</th><th>Amount</th></tr>
    <c:forEach varStatus="s" items="${expenseslist }" var="d1">
    <c:set var="Expenses1sum" value="${Expenses1sum+d1.amount }"></c:set>
    <tr><td>${d1.expenseName }</td><td>${d1.comments }</td><td>${d1.amount }</td></tr>
    </c:forEach>
    <tr><th colspan="2" style="text-align: right;">Total</th><td><b>${Expenses1sum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -30%;background-color: skyblue;width: 11%;"  onclick="printDiv('Expenses11')">Print</button>
      <button type="button" onclick="document.getElementById('Expenses11').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="otherincome11" class="modal" style="width: 55%;">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('otherincome11').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-right: 68%;">
    <tr><th>TypeOfSource</th><th>Quantity</th><th>Description</th><th>Amount</th></tr>
    <c:forEach varStatus="s" items="${otherIncomelist }" var="d1">
    <c:set var="Otherincome1sum" value="${Otherincome1sum+d1.amount }"></c:set>
    <tr><td>${d1.type }</td><td>${d1.quantity }</td><td>${d1.description }</td><td>${d1.amount }</td></tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${Otherincome1sum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -68%;background-color: skyblue;width: 17%;"  onclick="printDiv('otherincome11')">Print</button>
      <button type="button" onclick="document.getElementById('otherincome11').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="RepaymentLoans1" class="modal" style="width: 65%">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('RepaymentLoans1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>InstallmentNo</th><th>PaidDate</th><th>PaidAmount</th><!-- <th>InterestAmount</th><th>Fine</th> --></tr>
    <c:forEach varStatus="s" items="${loanhistory }" var="d1">
    <c:set var="RepaymentLoanssum" value="${RepaymentLoanssum+d1.paidAmount }"></c:set>
    <c:set var="customer" value="${d1.customer }"></c:set>
    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.installmentId }</td><td>${d1.loanPaidDate }</td><td>${d1.paidAmount }</td><%-- <td>${d1.paidInterest }</td><td>${d1.fine }</td> --%></tr>
    </c:forEach>
    <tr><th colspan="4" style="text-align: right;">Total</th><td><b>${RepaymentLoanssum }</b></td></tr>
    </table>
      <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;"  onclick="printDiv('RepaymentLoans1')">Print</button>
      <button type="button" onclick="document.getElementById('RepaymentLoans1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="RepaymentSpecialLoans1" class="modal" style="width: 55%">
  <form class="modal-content animate" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('RepaymentSpecialLoans1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table style="margin-left: -30%;">
    <tr><th>CustomerID</th><th>CustomerName</th><th>PaidDate</th><th>PaidAmount</th><!-- <th>Interest</th><th>Fine</th> --></tr>
    <c:forEach varStatus="s" items="${specialloanhistory }" var="d1">
    <c:set var="customer" value="${d1.customer }"></c:set>
    <c:set var="repaymentSPloan" value="${repaymentSPloan+d1.paidAmount }"></c:set>
    <tr><td>${customer.customerUid }</td><td>${customer.customerName }</td><td>${d1.loanPaidDate }</td><td>${d1.paidAmount }</td><%-- <td>${d1.paidInterest }</td><td>${d1.fine }</td> --%></tr>
    </c:forEach>
    <tr><th colspan="3" style="text-align: right;">Total</th><td><b>${repaymentSPloan }</b></td></tr>
    </table>
    <button type="button" style="margin-left: -52%;background-color: skyblue;width: 11%;" onclick="printDiv('RepaymentSpecialLoans1')">Print</button>
      <button type="button"  onclick="document.getElementById('RepaymentSpecialLoans1').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>
     
     <%-- <table >
     <tr>
     <td><table style="margin-top: -37%;"><tr><th style="color: green;">OpeningBalance</th><td>${ledgerSummaryList.openingBalance }</td></tr></table></td>
     <td>
       <table>
       <tr><th style="text-align: center;color: red;">Credits</th></tr>
       <tr><th align="left">DepsitsAmount</th><td>${ledgerSummaryList.depsitsAmount }</td></tr>
       <tr><th align="left">DepositInterests</th><td>${ledgerSummaryList.depositsInterestsAmount }</td></tr>
       <tr><th align="left">DepositFines</th><td>${ledgerSummaryList.depositFines }</td></tr>
       
       <tr><th align="left">LoanRepaymentPrinciples</th><td>${ledgerSummaryList.loanAmountPrinciples }</tr>
       <tr><th align="left">LoanRepaymentInterets</th><td>${ledgerSummaryList.loanInteretsAmount }</td> </tr>
       <tr><th align="left">LoanFines</th><td>${ledgerSummaryList.loanFines }</td></tr>
       
       <tr><th align="left">SLoanRepaymentPrinciples</th><td>${ledgerSummaryList.specialLoanPaidPrinciples }</tr>
       <tr><th align="left">SLoanRepaymentInterets</th><td>${ledgerSummaryList.specialLoanInterests }</td> </tr>
       <tr><th align="left">SLoanFines</th><td>${ledgerSummaryList.specialLoanFines }</td></tr>
       </table>
     </td>
     <td>
        <table style="margin-bottom: 50%;">
       <tr><th style="text-align: center;color: red;">Debits</th></tr>
       <tr><th align="left">NormalLoans</th><td>${ledgerSummaryList.givenLoansAmount }</td></tr>
       <tr><th align="left">SpecialLoans</th><td>${ledgerSummaryList.specialLoanPrinciples }</td></tr>
       <tr><th align="left">AdditionalExpenses</th> <td>${ledgerSummaryList.additionalExpenses }</td></tr>
       <tr><th align="left">Withdrawals</th> <td>${ledgerSummaryList.withdrawals }</td></tr>
       </table>
     </td>
     <td><table style="margin-top: -37%;"><tr><th style="color: green;">ClosingBalance</th><td>${(ledgerSummaryList.openingBalance-ledgerSummaryList.givenLoansAmount-ledgerSummaryList.additionalExpenses-ledgerSummaryList.specialLoanPrinciples-ledgerSummaryList.withdrawals)+ledgerSummaryList.depsitsAmount+ledgerSummaryList.depositsInterestsAmount+ledgerSummaryList.depositFines+ledgerSummaryList.loanAmountPrinciples+ledgerSummaryList.loanInteretsAmount+ledgerSummaryList.loanFines+ledgerSummaryList.specialLoanPaidPrinciples+ledgerSummaryList.specialLoanInterests+ledgerSummaryList.specialLoanFines }</td></tr></table></td>
     </tr>
     </table>
     
     <table><tr><th style="text-align: top;color: green;">CurrentBalance</th><td>${ledgerSummaryList.balance }</td></tr></table> --%>
         
       &nbsp;  
       &nbsp;
       &nbsp;
      <div align="center">
      <c:if test="${countdowndate ne '' }">
           <a href="settle" > <input type="button" onclick="loadingstart()" value="Settle"/></a>
           </c:if>
          <c:if test="${countdowndate eq '' }"> 
           <a onclick="printDiv('personalSummarypopup')" ><input type="button" value="PrintCurrentMonthTransactions"/></a>
           </c:if>
      </div>
      
       <div id="personalSummarypopup" style="overflow-x:auto;visibility: hidden;position: absolute;width: 75%;align-content: center;">
      <table border="0" id="myTable" style="border: 1px solid;border-spacing: 2px;">
      <c:if test="${fn:length(personalSummary) gt 0  }">
      <tr class="header" style="border: 1px solid;border-spacing: 2px;background-color: bisque;">
       <th><input type="text" id="InputCDate" onkeyup="myFunction('InputCDate','0')" placeholder="CoveredMonth" title="Type in a name"></th>
       <th>Id</th>
       <th><input type="text" id="InputCustName" onkeyup="myFunction('InputCustName','2')" placeholder="Name" title="Type in a name"></th>
       <th>MlyShare</th>
       <th style="border-right: 1px solid;border-spacing: 2px;">Fine</th>
       
       <th>InstNo</th>
       <th>MlyInst</th>
       <th>Interest</th>
       <th>Fine</th>
       <!-- <th>LAmount</th> -->
       <th style="border-right: 1px solid;border-spacing: 2px;">LDue</th>
       
       <th>SPLoan</th>
       <th>SPInterest</th>
       <th>Fine</th>
       <th>SLDue</th>
       
       
      </tr>
      </c:if>
      <c:forEach items="${personalSummary }" var="d" varStatus="status">
       <tr <c:if test="${cId ne d.customerUid and status.index != 0 }">style="border-top: 1px solid;border-spacing: 2px;"</c:if> >
       <td>${d.coveredMonth }</td>
       <td>${d.customerUid }</td>
       <td>${d.customerName }</td>
       <td>${d.monthlyShare }</td>
       <td style="border-right: 1px solid;border-spacing: 2px;">${d.monthlySharefine }</td>
       <td>${d.installments }</td>
       <td>${d.monthlyInstallment }</td>
       <td>${d.monthlyInstallmentinterest }</td>
       <td>${d.monthlyInstallmentloanFine }</td>
     <%--   <td>${d.loanAmount }</td> --%>
       <td style="border-right: 1px solid;border-spacing: 2px;">${d.due }</td>
       <td>${d.specialLoan }</td>
       <td>${d.specialLoanInterest }</td>
       <td>${d.specialLoanFine }</td>
       <td>${d.stDue }</td>
      <c:set var="cId" value="${d.customerUid }"></c:set>
      </tr>
              
       </c:forEach>
      </table>
           
    </div>
      
    </div>
    
   
</body>

</html>