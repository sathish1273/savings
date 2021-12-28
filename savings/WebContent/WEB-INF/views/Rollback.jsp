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

<script type="text/javascript">
function validateDertt()
{
	
	var customerUid = document.getElementById('customerId').value;
	var type=document.getElementById('typekey1roll').value

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

	 if (confirm("Are you sure, want to "+type+" for the customer "+customerUid+" ?") == false) {
	    	loadingstop();
	    	return false; 
	    }
}
function changedropdownTraOtherw(type)
{
	alert("sss");
	document.getElementById('typerrr').value=type;
}

</script>
</head>
<body>

	 <div align="center">
        <form action="rollback" modelAttribute="CustomerDto" method="POST" onsubmit="return validateDertt();">
            <table border="0">
                 <tr>
                    <th>Type Of Rollback<label style="color: red;"> * </label>:</th>
                    <td><select id="typekey1roll" class="select-d" name="type">
                    <option value="RollbackPayments">RollbackPayments</option>
                    <option value="RollbackLoans">RollbackLoans</option>
                    <option value="RollbackSpLoans">RollbackSpLoans</option>
                    </select></td>
                </tr>
                <tr>
                    <th>Enter CustomerId<label style="color: red;"> * </label>:</th>
                    <td><input type="text" id="customerId" name="customerId"/></td>
                </tr>
                <tr>
                <td></td>
                <td align="center"><input type="submit" onclick="loadingstart()" value="Submit" /></td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>