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
        <form action="addInterests" modelAttribute="InterestDto" method="post">
            <table border="0">
                <!-- <tr>
                    <td>NoteId:</td>
                    <td><input type="text" name="noteId" /></td>
                </tr> -->
                <tr>
                    <td>InterestRatee:</td>
                    <td><input type="text" name="interestRate" /></td>
                </tr>
                <tr>
                    <td>InterestType:</td>
                    <td><select name="interestType_id">
                    <c:forEach items="${interestTypeList }" var="i">
                    <option value="${i.interestType_uid }">${i.interestType }</option>
                    </c:forEach>
                    </select></td>
                      
                <tr>
                    <td>Status</td>
                    <td><select name="status">
                    <option value="Active">Active</option>
                    <option value="InActive">InActive</option>
                    </select></td>
                </tr>
               
                <tr>
                    <td colspan="2" align="center"><input onclick="loadingstart()" type="submit" value="Add" /></td>
                </tr>
            </table>
        </form>
    </div>
    
     <div align="center">
      
      <table border="0">
      <tr>
       <td>InterestRate</td>
       <td>InsterestType</td>
       <td>Status</td>
                </tr>
      <c:forEach items="${interestsList }" var="d">
       <tr>
       <td>${d.interestRate }</td>
       <td>${d.interestType_id }</td>
       <td>${d.status }</td>
       </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>

</html>