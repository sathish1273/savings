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
function updateInstitution(iId,iIname,iValidityUpto,iRehargeAmount,statusInst,iLandMark,statesI,districtId,mandalId,villgaesI,dlist,mlist,vlist,mdate,messagesRequired)
{
	loadingstop();
    document.getElementById('id05').style.display='block';
    document.getElementById('iId').value=iId;
    document.getElementById('iIname').value=iIname;
    document.getElementById('iOpeningBalance').style.visibility = 'hidden';
    document.getElementById('iOpeningBalance').style.visibility = 'absolute';
    
    document.getElementById('iValidityUpto').value=iValidityUpto;
    document.getElementById('iRehargeAmount').value=iRehargeAmount;
    document.getElementById('msgScheduledDate').value=mdate;
    
    document.getElementById('statusInst').value=statusInst;
    document.getElementById('iLandMark').value=iLandMark;
    document.getElementById('messagesRequired').value=messagesRequired;
    
    document.getElementById('statesI').value=statesI;
    changedropdown('Telangana','districtsI',dlist);
    document.getElementById('districtsI').value=districtId;
    changedropdown(districtId,'mandalsI',mlist);
    document.getElementById('mandalsI').value=mandalId;
    changedropdown(mandalId,'villgaesI',vlist);
    document.getElementById('villgaesI').value=villgaesI;
   
}

</script>
</head>

<body>

<div id="id05" class="modal">
  
  <form class="modal-content animate" action="addInstitution" modelAttribute="InstitutionDto" method="post">
    <div class="imgcontainer">
      <span onclick="reload('id05')" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table border="0">
                <!-- <tr>
                    <td>NoteId:</td>
                    <td><input type="text" name="noteId" /></td>
                </tr> -->
                <input type="text"  style="visibility: hidden;position: absolute;"  id="iId" name="institutionId" />
                <tr>
                    <td>InstitutionName:</td>
                    <td><input type="text" id="iIname" name="institutionName" /></td>
                </tr>
                <tr>
                    <td>InstitutionLogo:</td>
                   <td><input type="text" id="iLogo" name="institutionlogo" /></td>
                 </tr>
                 
                  <tr>
                    <td>Opening Balance:</td>
                   <td><input type="text" id="iOpeningBalance" name="openingBalance" value="0" /></td>
                 </tr>
                 
                <!--   <tr>
                    <td>SangamStartDate:</td>
                   <td><input style="width: 100%" id="sangamStartDate" type="date" name="sangamStartDate" /></td>
                 </tr> -->
                 
                   <tr>
                    <td>ValidityUpto:</td>
                   <td><input style="width: 100%" id="iValidityUpto" type="date" name="validityTo" /></td>
                 </tr>
                 
                  <tr>
                    <td>RechargeAmount:</td>
                   <td><input type="text" id="iRehargeAmount" name="rechargeAmount" value="0" /></td>
                 </tr>
                 
                 <tr>
                    <td>ScheduledMessageDate:</td>
                   <td>
                   <select class="select-d" id="msgScheduledDate" name="msgScheduledDate" >
                   <option value="0">lastDay</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13<option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="24">24</option>
                    <option value="25">25</option>
                    <option value="26">26</option>
                    <option value="27">27</option>
                    <option value="28">28</option>
                    </select>
                   
                   </td>
                 </tr>
                 
                   <tr>
                    <td>MessageAlertsRequired:</td>
                   <td>
                   <select class="select-d" id="messagesRequired" name="messagesRequired" >
                   <option value="YES">YES</option>
                    <option value="NO">NO</option>
                    </select>
                    </td>
                    </tr>
                 
                <tr>
                    <td>LandMark:</td>
                   <td><input type="text" id="iLandMark" name="landMark" /></td>
                 </tr>
                 <tr>
                    <td>State:</td>
                    <td><select class="select-d" id="statesI" name="stateId" onchange="changedropdown(this.options[this.selectedIndex].text,'districtsI','${districtsList }')">
                    <option value="">Select</option>
                    <c:forEach items="${statesList }" var="i">
                    <option value="${i.value }">${i.key }</option>
                    </c:forEach>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <td>District:</td>
                    <td><select class="select-d" id="districtsI" name="districtId" onchange="changedropdown(this.options[this.selectedIndex].text,'mandalsI','${mandalsList }')">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <td>Mandal:</td>
                    <td><select class="select-d" id="mandalsI" name="mandalId" onchange="changedropdown(this.options[this.selectedIndex].text,'villgaesI','${villagesList }')">
                    <option value="">Select</option>
                    </select></td>
                 </tr>
                 
                 <tr>
                    <td>Village:</td>
                    <td><select class="select-d" id="villgaesI" name="villgaeId">
                    <option value="">Select</option> 
                    </select></td>
                 </tr>
                      
                <tr>
                    <td>Status</td>
                    <td><select class="select-d" id="statusInst" name="status">
                     <c:forEach items="${statusTypes }" var="i">
                    <option value="${i.key }">${i.value }</option>
                    </c:forEach>
                    </select></td>
                </tr>
            </table>     
      <button style="margin-left: 5%" onclick="loadingstart()" type="submit">Submit</button>
      <button type="button" onclick="reload('id05')" class="cancelbtn">Cancel</button>
    </div>

   
  </form>
</div>
    
      <div align="center">
      
      <table border="0">
       <tr><td colspan="2" style="text-align: center;"><h2><span style="font-size:2vw;">Institutions</span></h2></td><td style="text-align:left; "><button onclick="document.getElementById('id05').style.display='block'" style="width:60%;">+</button></td></tr>
      <tr>
       <th>InstitutionName</th>
       <th>Address</th>
       <th>Status</th>
                </tr>
      <c:forEach items="${institutionList }" var="d">
      <c:set var="address" value="${d.address_id }"></c:set>
      <c:forEach var="v" items="${validityList }">
      <c:set var="validity" value="${v.toDate }"></c:set>
      <c:set var="validityamount" value="${v.amount }"></c:set>
      </c:forEach>
       <tr>
       <td><a href="#" onclick="updateInstitution('${d.institution_uid}','${d.institutionName }','${validity }','${validityamount }','${d.status }','${address.landMark }','${address.state }','${address.district }','${address.mandal }','${address.village }','${districtsList }','${mandalsList }','${villagesList }','${d.msgScheduledDate }','${d.messagesRequired }')">${d.institutionName }</a></td>
       <c:set var="address" value="${d.address_id }"></c:set>
       <td>${address.village },${address.mandal },${address.district },${address.state }</td>
       <td><c:forEach items="${statusTypes }" var="t">
       <c:if test="${t.key eq d.status }"><td>${t.value }</td></c:if>
       </c:forEach></td>
       </tr>
              
                </c:forEach>
            </table>
           
    </div>
</body>

</html>