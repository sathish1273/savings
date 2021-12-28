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
function changedropdown1(value){
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
    	 $('#loaddivid').load("MonthlyLoansSummary.jsp");
    }
});
}

</script>
</head>
<body>
<div align="center" id="loaddivid">
<h2 style="color: maroon;font-size: 2vw">MonthWiseLoanSummary Report</h2>
<table>
<form action="mothlyLoansSummary" modelAttribute="OccurranceDto" method="post">
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
      <c:if test="${fn:length(monthlyLoansSummary) gt 0  }">
      <tr class="rowheader">
       <th>CustomerId</th>
       <th>CustomerName</th>
       <th>SimpleLoan</th>
       <th>SpecialLoan</th>
       <th>NoOfInstallments</th>
      <!--  <th>CustomerSignature</th> -->
       <th>IntroducerName</th>
       <th>IntroducerId</th>
      </tr>
       </c:if>
      <c:forEach items="${monthlyLoansSummary }" var="d">
               <tr>
        <td>${d.customerId }</td>
        <td>${d.customerName }</td>
       <td>${d.simpleLoan }</td>
       <td>${d.specialLoan }</td>
       <td>${d.noOfInstallments }</td>
       <%-- <td>${d.customerSignature }</td> --%>
        <td>${d.introducerName }</td>
       <td>${d.introducerId }</td>
                </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>
</html>