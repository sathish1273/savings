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
function changedropdown12(value){
	//alert(value);
$.ajax({
    type : "GET",
    url : "mothlyLoansSummary",
    dataType: 'json',
    async: true,
    data : {"id" : value},
    success: function(data){
        alert("success"+data);
        //$('#input_field').val(data);
    },
    error: function (data) {
    	 alert("errr"+data);
    	 $('#loaddivid').load("MonthlyLoansSummary.jsp");
    	 alert("succc");
        // do something.
    }
});
}

</script>
</head>
<body>
<div align="center" id="loaddivid">
<h2 style="color: maroon;font-size: 2vw">MonthWiseDepositSummary Report</h2>      
 <table>
<form action="monthlyDepositsSummary" modelAttribute="OccurranceDto" method="post">
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
      
      <table class="tablecssr">
      <c:if test="${fn:length(monthlyDepositsSummary) gt 0  }">
      <tr class="rowheader">
      <th>CoveredMonth</th>
       <th>CustomerUid</th>
       <th>CustomerName</th>
       <th>DepositedAmount</th>
       <th>PaidInterest</th>
       </tr>
       </c:if>
      <c:forEach items="${monthlyDepositsSummary }" var="d">
       <tr>
       <c:set var="occurrance" value="${d.occurrance }"></c:set>
       <c:set var="customer" value="${d.customer }"></c:set>
       <td>${occurrance.coveredMonth }</td>
       <td>${customer.customerId }</td>
       <td>${customer.customerName }</td>
       <td>${d.amountPaid }</td>
       <td>${d.fine }</td>
      </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>
</html>