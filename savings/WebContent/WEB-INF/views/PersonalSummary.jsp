<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<style>
* {
  box-sizing: border-box;
}

/* #myInput {
  background-image: url('/css/searchicon.png');
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
} */


</style>

<head>
<meta charset="ISO-8859-1">
<title>savings</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function myFunction1(td,id) {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById(td);
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable11");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[id];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>
</head>
<body>
<div align="center" id="loaddivid">
<h2 class="tablenames">PersonalSummary Report</h2>
<table>
<form action="personalSummary" modelAttribute="OccurranceDto" method="post">
<tr>
<th>Select Month:</th>
<td>
<select id="from" name="fromOccurrance">
                   <!--  <option value="">Select</option> -->
                    <c:forEach items="${occuranceIds }" var="i">
                    <option <c:if test="${from eq  i.key}"> selected="selected"  </c:if> value="${i.key }">${i.value }</option>
                    </c:forEach>
                    
</select>
</td>
<td><select id="to" name="toOccurrance">
                   <!--  <option value="">Select</option> -->
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
      
      <table id="myTable11" class="tablecssr" style="font-size: 0.82vw">
      <c:if test="${fn:length(personalSummary) gt 0  }">
      <tr class="rowheader">
       <th><input type="text" id="InputCDate11" onkeyup="myFunction1('InputCDate11','0')" style="width: 55%;" placeholder="CoveredMonth"></th>
       <th><input type="text" id="InputId11" onkeyup="myFunction1('InputId11','1')" placeholder="ID" style="width: 35%;"></th>
       <th><input type="text" id="InputCustName11" onkeyup="myFunction1('InputCustName11','2')" placeholder="Name" style="width: 65%;"></th>
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
       <td style="overflow: auto;">${d.installments }</td>
       <td>${d.monthlyInstallment }</td>
       <td>${d.monthlyInstallmentinterest }</td>
       <td>${d.monthlyInstallmentloanFine }</td>
       <%-- <td>${d.loanAmount }</td> --%>
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
</body>
</html>