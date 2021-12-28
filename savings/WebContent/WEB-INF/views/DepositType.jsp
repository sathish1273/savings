<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>savings</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->

	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	 <div align="center">
        <form action="addDepositType" modelAttribute="DepositType" method="post">
            <table border="0">
                <!-- <tr>
                    <td>NoteId:</td>
                    <td><input type="text" name="noteId" /></td>
                </tr> -->
                <tr>
                    <td>DepositType:</td>
                    <td>
                    <select name="deposit_type" >
                    <c:forEach items="${depositsEnums }" var="d">
                    <option value="${d }">${d }</option>
                    </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td>Ideal Amount:</td>
                    <td><input type="text" name="amount" /></td>
                </tr>
               
                <tr>
                    <td colspan="2" align="center"><input type="submit" onclick="loadingstart()" value="Add" /></td>
                </tr>
            </table>
        </form>
    </div>
    
     <div align="center">
      
      <table border="0">
      <tr>
       <td>DepositType</td>
       <td>IdealAmount</td>
                </tr>
      <c:forEach items="${depositTypeList }" var="d">
               <tr>
       <td>${d.deposit_type }</td>
       <td>${d.amount }</td>
                </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>

</html>