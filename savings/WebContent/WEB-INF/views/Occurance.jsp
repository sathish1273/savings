<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>savings</title>
<script type="text/javascript">


function occurvalidate()
{
	
	var coveredMonth = document.getElementById('coveredMonth').value;
	var activehours =  document.getElementById('activehours').value;
	
	if(coveredMonth=="" || coveredMonth==null)
	{
		alert("Please Select CoveredMonth");
		window.setTimeout(function ()
		    {
			document.getElementById('coveredMonth').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	

	if(activehours=="" || activehours==null)
	{
		alert("Please Enter Activehours");
		window.setTimeout(function ()
		    {
			document.getElementById('activehours').focus();
		    }, 0);
		loadingstop();
		return false;
	} 
	
	if(isNaN(activehours))
	{
		alert("Please Enter Only Number");
		window.setTimeout(function()
			{
			document.getElementById('activehours').focus();
			}, 0);
		loadingstop();
		return false;
	}
	 if (confirm("Are you sure, want to start the sangam on "+coveredMonth+" ?") == false) {
		    	loadingstop();
		    	return false; 
		    }


}

</script>
</head>

<body>

<div id="id02" class="modal">
  
  <form class="modal-content animate" action="addOccurance" modelAttribute="OccurranceDto" method="post" onsubmit="return occurvalidate();">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    
      <table border="0">
                <tr>
                    <th>CoveredMonth:<label style="color: red;"> * </label></th>
                    <td><input style="width: 100%" type="date" id="coveredMonth" name="coveredMonth" value="${activeOccurrance.coveredMonth }"/></td>
                </tr>
                <tr>
                    <th>ActiveHours:<label style="color: red;"> * </label></th>
                    <td><input type="text" name="activehours" id="activehours" value="12" value="${activeOccurrance.activehours }" /></td>
                </tr>
                
                 <tr>
                    <th>LandMark:</th>
                   <td><input type="text" name="landMark" value="" /></td>
                 </tr>
                 <c:set var="addressocc" value="${activeOccurrance.address }"></c:set>
                 <tr>
                   <c:set var="address" value="${institutionuser.address_id }"></c:set>
                   <td><input type="checkbox" onclick="sameas1('${address.village}','${address.mandal}','${address.district}','${address.state}','villgaesO','mandalsO','districtsO','statesO')" id="sameas" name="sameas" /></td>
                   <th>Same as Sangam address</th>
                 </tr>
                 <tr>
                    <th>State:</th>
                    <td><select class="select-d" id="statesO" name="stateId" onchange="changedropdown(this.options[this.selectedIndex].text,'districtsO','${districtsList }')">
                    <option value="">Select</option>
                    <c:forEach items="${statesList }" var="i">
                    <option <c:if test="${addressocc.state eq i.value }"> selected="selected"  </c:if> value="${i.value }">${i.key }</option>
                    </c:forEach>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>District:</th>
                    <td><select class="select-d" id="districtsO" name="districtId" onchange="changedropdown(this.options[this.selectedIndex].text,'mandalsO','${mandalsList }')">
                    <option  value="">${addressocc.district}</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>Mandal:</th>
                    <td><select class="select-d" id="mandalsO" name="mandalId" onchange="changedropdown(this.options[this.selectedIndex].text,'villgaesO','${villagesList }')">
                    <option value="">${addressocc.mandal}</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <th>Village:</th>
                    <td><select class="select-d" id="villgaesO" name="villgaeId">
                    <option value="">${addressocc.village}</option>
                    </select></td>
                 </tr>
                      
                <tr>
                    <th>Status</th>
                    <td><select class="select-d" name="status">
                     <c:forEach items="${statusTypes }" var="i">
                    <option value="${i.key }">${i.value }</option>
                    </c:forEach>
                    </select></td>
                </tr>
            </table>
        
      <button style="margin-left: 5%" onclick="loadingstart()" type="submit">Submit</button>
      <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>

    
     <div align="center">
      <h2 class="tablenames">Occurrances &nbsp;&nbsp; <button onclick="document.getElementById('id02').style.display='block'">+</button></h2>
      <table class="tablecssr">
      <tr class="rowheader">
      <th>CoveredMonth</th>
      <th>TransactionType</th>
      <th>ActiveHours</th>
      <th>Status</th>
      <th>Address</th>
      </tr>

      <c:forEach items="${occuranceList }" var="d">
               <tr>
       <td>${d.coveredMonth }</td>
       <c:forEach items="${transactionTypes }" var="t">
       <c:if test="${t.key eq d.transactionType }"><td>${t.value }</td></c:if>
       </c:forEach>
       <td>${d.activehours }</td>
       <c:forEach items="${statusTypes }" var="t">
       <c:if test="${t.key eq d.status }"><td>${t.value }</td></c:if>
       </c:forEach>
       
       <c:set var="addresso" value="${d.address }"></c:set>
       <td><table><tr><td>${addresso.village }</td> <%-- <td>${addresso.mandal }</td> <td>${addresso.district }</td> <td>${addresso.state }</td> --%></tr></table></td>
       </tr>
              
       </c:forEach>
       </table>
           
    </div>
</body>

</html>