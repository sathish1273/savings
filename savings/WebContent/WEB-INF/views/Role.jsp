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

function roleCheck(iid,id,pid)
{
	loadingstop();
	var checkstatus=document.getElementById(id).checked;
	if(checkstatus == true){
	document.getElementById("r"+pid).checked=true;
	document.getElementById('isAccessible').value=document.getElementById('isAccessible').value+","+iid;
	}
	else{
		document.getElementById("r"+pid).checked=false;
		document.getElementById('isAccessible').value=document.getElementById('isAccessible').value.replace(","+iid, "");
		}
}


function roleCheckupdate(iid,id,pid)
{
	loadingstop();
	var checkstatus=document.getElementById(id).checked;
	if(checkstatus == true){
	document.getElementById("rr"+pid).checked=true;
	document.getElementById('isAccessible1').value=document.getElementById('isAccessible1').value+","+iid;
	}
	else{
		document.getElementById("rr"+pid).checked=false;
		document.getElementById('isAccessible1').value=document.getElementById('isAccessible1').value.replace(","+iid, "");
		}
}

</script>
<script type="text/javascript">
function updateRole(uid,uname,institutionId,status,menus)
{
	loadingstop();
    document.getElementById('roleid055').style.display='block';
    document.getElementById('roleUidu1').value=uid;
    document.getElementById('roleNameu1').value=uname;
    document.getElementById('institutionIdr1').value=institutionId;
    document.getElementById('statusup1').value=status;
    //alert(menus);
    var str=menus.substring(1,(menus.length)).split(","); 
	 for (var m = 0; m < str.length; m++) 
	 {
		 //alert(str[m]); 
		 var names=str[m].split("#");
		 try{
			 //alert(names[0]+"  "+names[1]);
			 document.getElementById('rr'+names[0]).checked=true;
			 document.getElementById('isAccessible1').value=document.getElementById('isAccessible1').value+","+names[1];
			 }
		 catch(err)
		 {
			// alert("err");
	     }
	 }
}


</script>

</head>

<body>

<div id="roleid055" class="modal">
  
  <form class="modal-content animate" action="addRole" modelAttribute="RoleDto" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('roleid055').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table border="0">
    <input type="text" style="visibility: hidden;position: absolute;" id="roleUidu1" name="roleUid" />
                <tr>
                    <th>RoleName:</th>
                    <td><input type="text" id="roleNameu1" name="roleName" /></td>
                </tr>
               
                <tr>
                    <th>Institution:<label style="color: red;"> * </label></th>
                    <td><select class="select-d" id="institutionIdr1" name="institutionId">
                     <c:forEach items="${institutions }" var="i">
                     <c:if test="${i.value ne 'Chintha Nekkonda' }">
                    <option value="${i.key }">${i.value }</option>
                    </c:if>
                    </c:forEach>
                    </select></td>
                </tr>
               <tr>
               <table>
                  
                    <tr><th>AccessibleMenus:</th><td></td></tr>
                    
                 <c:forEach items="${allMenus }" var="i" varStatus="status">
                 <tr>
                 <c:set var="parentId" value="${i.parent }"></c:set>
                    <td></td><td><c:if test="${i.parent ne null }">&nbsp;&nbsp;&nbsp;&nbsp;</c:if><input type="checkbox"  id="rr${i.id }" onClick="roleCheckupdate('${i.menuName }','rr${i.id }','${parentId.id }')" name="menus" />&nbsp;${i.menuName }</td>
                </tr>
                </c:forEach>
                <input  type="text" style="visibility: hidden;position: absolute;" id="isAccessible1" name="isAccessible" />
                </table> 
                </tr> 
                <tr>
                    <th>Status</th>
                    <td><select name="status" id="statusup1">
                     <c:forEach items="${statusTypes }" var="i">
                    <option value="${i.key }">${i.value }</option>
                    </c:forEach>
                    </select></td>
                </tr>
            </table>     
     
    </div>
<button style="margin-left: 5%" type="submit" onclick="loadingstart()">Update</button>
                 <button type="button" onclick="document.getElementById('roleid055').style.display='none'" class="cancelbtn">Cancel</button>
   
  </form>
</div>

<div id="roleid05" class="modal">
  
  <form class="modal-content animate" action="addRole" modelAttribute="RoleDto" method="post">
    <div class="imgcontainer">
      <span onclick="reload('roleid05')" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container" id="adddivInterestType">
    <table border="0">
    <input type="text" style="visibility: hidden;position: absolute;" id="roleUidu" name="roleUid" />
                <tr>
                    <th>RoleName:</th>
                    <td><input type="text" id="roleNameu" name="roleName" /></td>
                </tr>
               
                <tr>
                    <th>Institution:<label style="color: red;"> * </label></th>
                    <td><select class="select-d" id="institutionIdr" name="institutionId">
                     <c:forEach items="${institutions }" var="i">
                     <c:if test="${i.value ne 'Chintha Nekkonda' }">
                    <option value="${i.key }">${i.value }</option>
                    </c:if>
                    </c:forEach>
                    </select></td>
                </tr>
               <tr>
               <table>
                  
                    <tr><th>AccessibleMenus:</th><td></td></tr>
                    
                 <c:forEach items="${allMenus }" var="i" varStatus="status">
                 <tr>
                 <c:set var="parentId" value="${i.parent }"></c:set>
                 <c:if test="${i.parent eq null }">
                  <c:set var="dmenus" value="${dmenus},${i.menuName }"></c:set>
                  </c:if>
                    <td></td><td><c:if test="${i.parent ne null }">&nbsp;&nbsp;&nbsp;&nbsp;</c:if><input type="checkbox" id="r${i.id }" onClick="roleCheck('${i.menuName }','r${i.id }','${parentId.id }')" name="menus" />&nbsp;${i.menuName }</td>
                </tr>
                </c:forEach>
                <input  value="${dmenus }" style="visibility: hidden;position: absolute;" type="text" id="isAccessible" name="isAccessible" />
                </table> 
                </tr> 
                <tr>
                    <th>Status</th>
                    <td><select name="status" id="statusup">
                     <c:forEach items="${statusTypes }" var="i">
                    <option value="${i.key }">${i.value }</option>
                    </c:forEach>
                    </select></td>
                </tr>
            </table>     
     
    </div>
      <button style="margin-left: 5%" type="submit" onclick="loadingstart()">Submit</button>
      <button type="button" onclick="reload('roleid05')" class="cancelbtn">Cancel</button>
   
  </form>
</div>
    
      <div align="center">
      <h2 class="tablenames">Roles &nbsp;&nbsp; <button onclick="document.getElementById('roleid05').style.display='block'">+</button></h2>
      <table class="tablecssr">
      <tr class="rowheader">
       <th>RoleName</th>
       <th>Institution</th>
       <th>Status</th>
                </tr>
      <c:forEach items="${roleList }" var="d">
      <c:set var="rolerole" value="${user.role }"></c:set>
      <c:if test="${rolerole.roleName eq 'SuperAdmin' }">
      <c:set var="institution" value="${d.institution }"></c:set>
      <c:set var="menus1" value=""></c:set>
      <c:forEach items="${roleMenuLinkList }" var="r">
      <c:set var="role" value="${r.role }"></c:set>
      <c:set var="menu" value="${r.inventoryMenu }"></c:set>
      <c:if test="${role.roleUid eq d.roleUid }">
      <c:set var="menus1" value="${menus1 },${menu.id }#${menu.menuName }"></c:set>
      </c:if>
      </c:forEach>
       <tr>
       <td><a href="#" onclick="updateRole('${d.roleUid }','${d.roleName}','${institution.institution_uid }','${d.status }','${menus1 }')">${d.roleName }</a></td>
       <td>${institution.institutionName }</td>
       <c:forEach items="${statusTypes }" var="t">
       <c:if test="${t.key eq d.status }"><td>${t.value }</td></c:if>
       </c:forEach>
       </tr>
         </c:if> 
         
      <c:if test="${rolerole.roleName ne 'SuperAdmin' }">
      <c:if test="${d.roleName ne 'Admin' }">
      <c:set var="institution" value="${d.institution }"></c:set>
      <c:set var="menus1" value=""></c:set>
      <c:forEach items="${roleMenuLinkList }" var="r">
      <c:set var="role" value="${r.role }"></c:set>
      <c:set var="menu" value="${r.inventoryMenu }"></c:set>
      <c:if test="${role.roleUid eq d.roleUid }">
      <c:set var="menus1" value="${menus1 },${menu.id }#${menu.menuName }"></c:set>
      </c:if>
      </c:forEach>
       <tr>
       <td><a href="#" onclick="updateRole('${d.roleUid }','${d.roleName}','${institution.institution_uid }','${d.status }','${menus1 }')"></a>${d.roleName }</td>
       <td>${institution.institutionName }</td>
       <c:forEach items="${statusTypes }" var="t">
       <c:if test="${t.key eq d.status }"><td>${t.value }</td></c:if>
       </c:forEach>
       </tr>
       </c:if>
         </c:if>     
                </c:forEach>
            </table>
           
    </div>
</body>

</html>