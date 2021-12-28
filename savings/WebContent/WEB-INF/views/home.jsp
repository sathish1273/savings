<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<c:if test="${empty institutionId or empty userId}"><c:redirect url="index.jsp"></c:redirect></c:if>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta name=”owner” content=”glm@cyborgspiders.com” />
<meta name=”MSSmartTagsPreventParsing” content=”true” />
<meta http-equiv=”pragma” content=”no-cache” />
<meta http-equiv=”expires” content=”0″ />
<meta http-equiv=”Cache-Control” content=”no-cache” />
<meta http-equiv=”pragma-directive” content=”no-cache” />
<meta http-equiv=”cache-directive” content=”no-cache” />
<meta name=”robots” content=”all,follow” />


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.unclickable{
position: fixed;
top: 0px;
left: 0px;
width: 100%;
height: 100%;
background: rgba(0, 0, 0, 0.3);
z-index: 2;
}

.rowheader{
border: 1px solid;
border-spacing: 2px;
background-color: #9cbbff;
}

.tablecssr{
border: 1px solid;
border-spacing: 2px;
}

.tablenames{
color: maroon;
font-size: 1.5vw;
}

.loader {
  border: 16px solid #3e6068;
  border-radius: 50%;
  border-top: 16px solid #1aa8df87;
  width: 120px;
  height: 120px;
  z-index: 1;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
<style>

html{
  height:100%;
}
.buttonload {
  background-color: #4CAF50; /* Green background */
  border: none; /* Remove borders */
  color: white; /* White text */
  padding: 0% 0%; /* Some padding */
  font-size: 1vw; /* Set a font-size */
}

/* Add a right margin to each icon */
.fa {
  margin-left: -12px;
  margin-right: 8px;
}
</style>

 <script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }

    <%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);

    if(session.getAttribute("userId")==null)
        response.sendRedirect("index.jsp");

    %> 
</script>



 <script type="text/javascript"> 
        let timer, currSeconds = 0; 
  
        function resetTimer() { 
            /* Hide the timer text */ 
            document.querySelector(".timertext") 
                    .style.display = 'none'; 
  
            /* Clear the previous interval */ 
            clearInterval(timer); 
  
            /* Reset the seconds of the timer */ 
            currSeconds = 0; 
  
            /* Set a new interval */ 
            timer =  
                setInterval(startIdleTimer, 1000); 
        } 
  
        // Define the events that 
        // would reset the timer 
        window.onload = resetTimer; 
        window.onmousemove = resetTimer; 
        window.onmousedown = resetTimer; 
        window.ontouchstart = resetTimer; 
        window.onclick = resetTimer; 
        window.onkeypress = resetTimer; 
  
        function startIdleTimer() { 
        	//  alert("ghb");
            /* Increment the 
                timer seconds */ 
            currSeconds++; 
  
            /* Set the timer text 
                to the new value */ 

            if(currSeconds == 900){
                try
                {
                	$.get({
    	     	         url : 'logout',
    	     	         success : function(res) {
    	     	        	location.reload(true);
    	     	         }
    	     	      })
                 }
                catch(err)
                {
                    alert(err);
                }
                
                }
            document.querySelector(".secs") 
                .textContent = currSeconds; 
  
            /* Display the timer text */ 
            document.querySelector(".timertext") 
                .style.display = 'block'; 
            //alert(currSeconds);
          
        } 

   function reload(id)
   {
	   document.getElementById(id).style.display='none';
	   location.reload(true);
   }
        
    </script> 


<script>


function datescript(d){
	//alert(d);
// Set the date we're counting down to
//var countDownDate = new Date( ).getTime();
var countDownDate = new Date(d).getTime();
// Update the count down every 1 second
var x = setInterval(function() {

  // Get today's date and time
  var now = new Date().getTime();
    //alert(countDownDate+" "+now);
  // Find the distance between now and the count down date
  var distance = countDownDate - now ;
    
  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
  // Output the result in an element with id="demo"
  document.getElementById("demo").innerHTML =  hours + "h "
  + minutes + "m " + seconds + "s ";
    
  // If the count down is over, write some text 
  if (distance < 0) {
    clearInterval(x);
    if (confirm("Do you want to increase the Occurance time?") == true) {
    	document.getElementById('updateOccurrannce1').style.display='block';
    } else {
    	document.getElementById("demo").innerHTML = "EXPIRED";
    	loadingstart();
        try{
        /* 	$.ajax({
   	   	     type: "GET",
   	   	     url: "settle"
   	     	}) */

   	                	$.get({
		     	     	         url : 'settle',
		     	     	         success : function(res) {
		     	     	         //alert(res);
		     	     	         loadingstop();
		     	     	        	location.reload(true);
		     	     	         }
		     	     	      })
            }
        catch(err)
        {
        }
	    	
    	//location.reload(true); //loads from server
    }
    
  }
}, 1000);
}



function submitForms(){
	try{
		loadingstart();
		  $.post({
		         url : 'adddeposits',
		         data : $('form[name=adddepositsd]').serialize(),
		         success : function(res) {
		         
		        	  $.post({
		     	         url : 'addloans',
		     	         data : $('form[name=addloansl]').serialize(),
		     	         success : function(res) {
		     	        	  $.post({
		     	     	         url : 'addSpecialloans',
		     	     	         data : $('form[name=addSpecialloanss]').serialize(),
		     	     	         success : function(res) {
		     	     	         //alert(res);
		     	     	        	location.reload(true);
		     	     	         }
		     	     	      })
		     	        
		     	         }
		     	      })
		         }
		      })
		}
	catch(err)
	{
		//alert("err");
		loadingstop();
	}
	
	     // loadingstop();
} 
</script>

<style>

html{
  height:100%;
}
.collapsible {
  background-color: #00030ddb;
  color: white;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 1vw;
}

.active, .collapsible:hover {
  background-color: #555;
}

.collapsible:after {
  content: '\002B';
  color: white;
  font-weight: bold;
  float: right;
  margin-left: 5px;
}

.active:after {
  content: "\2212";
}

.content {
  padding: 0 18px;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.2s ease-out;
  background-color: #45717f;
}
</style>

<style>

.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #395e6e;
   color: white;
   text-align: center;
}


.tablecss {
  width:50%;
}
/* table {
border: 1px solid;
border-spacing: 2px;
} */
table, th, td {
 /* border: 1px solid black;*/
  border-collapse: collapse;
}
th, td {
  padding: 3px;
  text-align: left;
}
#t01 tr:nth-child(even) {
  background-color: #eee;
}
#t01 tr:nth-child(odd) {
 background-color: #fff;
}
#t01 th {
  background-color: skyblue;
  color: white;
}
tr:nth-child(even) {background-color: #f2f2f2;}
</style>

<style>
/* body {font-family: Arial, Helvetica, sans-serif;} */

/* Full-width input fields */
input[type=text], input[type=password] {
  /*width: 100%;
  padding: 12px 20px;
  margin: 8px 0;*/
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #2a2e32;
  color: white;
  padding: 0.7% 0.7%;
  margin: 1% 0;
  border: none;
  cursor: pointer;
  font-size: 0.75vw;
  /* width: 25%; */
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 0.7% 0.7%;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}
.readonlytd{
background: #dfb197;
}
.tableborder{
 border: 1px solid;
}

.trborder{
    border: 1px solid;
    background: burlywood;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 1%;
  width: fit-content;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 35%; /* Full width */
  height: 100%; /* Full height */
    overflow: auto;  /* Enable scroll if needed */
 /* background-color: rgb(0,0,0);  Fallback color */
 /* background-color: rgba(0, 0, 0, 0.4);  Black w/ opacity */
  /* padding-top: 60px; */
  /* padding-left: 45%; */
  margin-left: 40%;
  font-size: 0.8vw;
  
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 2%;
  top: -23px;
  color: #000;
  font-size: 2vw;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 100%) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}

</style>
<style>
body {
font-size: 1.1vw;
  font-family: "Lato", sans-serif;
  transition: background-color .5s;
}
.error{color:red}

.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #00030ddb;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 1% 0% 2% 2%;
  text-decoration: none;
  font-size: 1.3vw;
  color: #9db0b8;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 1%;
  font-size: 2vw;
 /*  margin-left: 50px; */
}

#main {
  transition: margin-left .5s;
/* padding: 16px; */
}

@media screen and (max-height: 100%) {
  .sidenav {padding-top: 1vw;}
  .sidenav a {font-size: 1vw;}
}

.topnav {
  overflow: hidden;
  background-color: #395e6e;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 1% 1%;
  text-decoration: none;
  font-size: 1.1vw;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}




/* frames concept */
.left-div {
    float: left;
  /*   width: 100px; */
    height: 20px;
    margin-right: 8px;
    background-color: linen;
}
.right-div {
    width: 330px;
    float: right;
    margin-left: 108px;
    background-color: skyblue;
}
.select-d {
   width: 100%;
}
</style>
<script>
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    } 
  });
}
</script>

<script>
/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}
</script>
<script type="text/javascript">

function loadingstart()
{
	document.getElementById('loadingimage').style.visibility = 'visible'; 
	document.getElementById('loadingimage').style.position='absolute'; 
	//document.getElementById("maindiv").readonly=false;
	document.getElementById('unclickable').style.visibility = 'visible'; 
	document.getElementById('unclickable').style.position='absolute'; 
}

function loadingstop()
{
	
	document.getElementById('loadingimage').style.visibility = 'hidden'; 
	document.getElementById('loadingimage').style.visibility = 'absolute'; 
	//document.getElementById('loadingimage').readonly=true;

	document.getElementById('unclickable').style.visibility = 'hidden'; 
	document.getElementById('unclickable').style.position='absolute'; 
	
}


function allHidden(urls,type,clear)
{
	loadingstart();
	
	 var d=document.getElementById("dateh").value;
	 if(d != null && d != ""){
	 datescript(d);
	 }
	 
	 var str=urls.split(","); 
	 for (var m = 0; m < str.length; m++) 
	 { 
		 document.getElementById(str[m]).style.visibility = 'hidden'; 
		 document.getElementById(str[m]).style.position='absolute';
		 
	 }
	 try{
		 if(type != "all")
			{
			//document.getElementById("businessmsg").innerHTML = "";
			document.getElementById(type).style.visibility = 'visible'; 
			document.getElementById(type).style.position='relative';
			}
		 }
	 catch(err)
	 {
	 }
	if(clear == "clearbmg")
	{
		document.getElementById("businessmsg").innerHTML = "";
	}
	 window.history.forward();
	openNav();
	
	loadingstop();
    
}

function menu()
{
	//alert(document.getElementById('displaymenus').value);
	if(document.getElementById('displaymenus').value == 0){
	document.getElementById('sideMenu').style.visibility = 'hidden'; 
	document.getElementById('sideMenu').style.position='absolute';
	document.getElementById('displaymenus').value="1";
	}
	else{
		document.getElementById('sideMenu').style.visibility = 'visible'; 
		document.getElementById('sideMenu').style.position='absolute';
		document.getElementById('displaymenus').value="0";
	}
}

function openNav() {
	  document.getElementById("mySidenav").style.width = "12%";
	  document.getElementById("main").style.marginLeft = "12%";
	  document.body.style.backgroundColor = "aliceblue";
	}

	function closeNav() {
	  document.getElementById("mySidenav").style.width = "0";
	  document.getElementById("main").style.marginLeft= "0";
	  document.body.style.backgroundColor = "white";
	}
</script>
<script type="text/javascript">
function clearAll(id)
{
	var select = document.getElementById(id);
	select.options.length = 0;
	 var opt = document.createElement('option');
	    opt.value = '';
	    opt.innerHTML = 'Select';
	    select.appendChild(opt);
}


function changedropdown(key,id,list){
	//alert(key+", "+id+" , "+list);
	if(id == "states")
		{
		clearAll("states");
		clearAll("districts");
		clearAll("mandals");
		clearAll("villgaes");
		}
	else if(id == "districts")
		{
		clearAll("districts");
		clearAll("mandals");
		clearAll("villgaes");
		}
	else if(id == "mandals")
		{
		clearAll("mandals");
		clearAll("villgaes");
		}
	else if(id == "villgaes")
	{
	clearAll("villgaes");
	}
	var select = document.getElementById(id);
	 var str=list.substring(1,(list.length-1)).split(","); 
	 var ddd;
	 for (var m = 0; m < str.length; m++) 
	 {
		 try{
			// alert(str[m]);
			 ddd=str[m].split("=");
			// alert(ddd[1])
			 if(ddd[1] == key)
				 {
				 var opt = document.createElement('option');
				    opt.value = ddd[0];
				    opt.innerHTML = ddd[0];
				    select.appendChild(opt);
				 }
			 }
		 catch(err){
         //alert(err);
			 }
		 
	 }
	//document.getElementById(id).selectedIndex=key.value;
}

function printDiv(divName) {
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
    location.reload(true);
}


function sameas1(v,m,d,s,vid,mid,did,sid)
{
	

	var select = document.getElementById(vid);
	var opt = document.createElement('option');
    opt.value =  v;
    opt.innerHTML = v;
    opt.selected = true; 
    select.appendChild(opt);

	var select = document.getElementById(mid);
	var opt = document.createElement('option');
    opt.value =  m;
    opt.innerHTML = m;
    opt.selected = true; 
    select.appendChild(opt);

	var select = document.getElementById(did);
	var opt = document.createElement('option');
    opt.value =  d;
    opt.innerHTML = d;
    opt.selected = true; 
    select.appendChild(opt);

	var select = document.getElementById(sid);
	var opt = document.createElement('option');
    opt.value =  s;
    opt.innerHTML = s;
    opt.selected = true; 
    select.appendChild(opt);
}

</script>

</head>

<body style="background: aliceblue;" onpageshow="if (event.persisted) noBack();" onUnload="" onload="allHidden('${menusperuser }','${currentpage}','')">
<div id="loadingimage" style="margin-left: 45%;margin-top: 14%;position: absolute;" class="loader" ></div>
<div id="unclickable" class="unclickable"></div>
<input style="visibility: hidden;position: absolute;" type="text" id="dateh" value="${countdowndate }"/>
<div id="maindiv">
<div  id="mySidenav" class="sidenav">
<label style="font-size: 1.0vw;"><a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a></label>
<c:forEach items="${menus }" var="i">
<c:if test="${i.parentId eq null }">
<a style="background: #12576cdb;font-size: 1vw;" href="#" >${i.menuName }</a>  <!-- class="collapsible" -->
<div><!--  class="content" -->
<c:forEach items="${menus }" var="ii">
<c:if test="${ii.parentId ne null and i.menu_uid eq ii.parentId }">
<a <c:choose>
<c:when test="${ii.url eq 'MonthlyLoansSummary' }">href="#"</c:when>
<c:when test="${ii.url eq 'LedgerSummary' }">href="#"</c:when>
<c:otherwise>href="#"</c:otherwise></c:choose> onclick="allHidden('${menusperuser }','${ii.url }','clearbmg')"> &nbsp; &nbsp; &nbsp;<span style="font-size: 0.9vw;">${ii.menuName }</span></a>
</c:if>

 </c:forEach>
 </div>
 </c:if>
</c:forEach>
&nbsp;
&nbsp; 
&nbsp;
</div>

<div id="main" style="resize: both;">
<div class="topnav" style="resize: both;">
<input type="hidden" id="displaymenus" value="0"/>
<a class="active" href="#home" onclick="menu()"> <span style="font-size:1.5vw;cursor:pointer" onclick="openNav()">&#9776;</span></a>
<div style="margin-left: 72px;resize: both;">
       <p class="timertext" style="visibility: hidden;position: absolute;font-size: 1.5rem;"> 
        <span class="secs"></span> seconds. 
    </p> 
  <a href="#" style="color: darkgoldenrod;"><span style="font-size:1vw;">${activeOccurrance.coveredMonth }</span></a>
  <a style="color: burlywood;" id="demo" href="#"></a>
  <c:if test="${ledgerSummaryList.balance ne '' }"><a href="#" style="color: #47baaa;"><span style="font-size:1vw;">Bal : ${ledgerSummaryList.balance }</span>    </a></c:if>
   <a href="#about" onclick="printDiv('printableArea')">Print</a>
  <div style="margin-left: 65%;margin-top: 1%;color: aliceblue;">
  <c:set var="address" value="${institutionuser.address_id }"></c:set>
  <span style="font-size:1vw;">${institutionuser.institutionName }&nbsp;${address.village }&nbsp;${address.mandal }&nbsp;${address.district }&nbsp;${address.state }</span>
<%--   <table><tr><td><span style="font-size:1vw;">${institutionuser.institutionLogo }</span></td><td><span style="font-size:1vw;">${institutionuser.institutionName }</span></td></tr>
  <tr style="background-color: #395e6e;">
  <td><span style="font-size:1vw;">${address.village }</span></td><td><span style="font-size:1vw;">${address.mandal }</span></td><td><span style="font-size:1vw;">${address.district }</span></td><td><span style="font-size:1vw;">${address.state }</span></td></tr></table>
  --%></div>
 </div>
</div> 

<div style="margin-left: 75%;"><span style="font-size:0.9vw;color: red;">${version }&nbsp;&nbsp;&nbsp;ValidityUpto:${validityuptoo } &nbsp;&nbsp;&nbsp; <a href="logout">logOut</a></span></div>
<div style="margin-top: 1%" id="printableArea" style="resize: both;">
	<div align="center" id="businessmsg">
	<table>
	  <c:forEach items="${businessMsgList.businessMessage }" var="d">
       <tr>
       <td>
       <c:choose>
       <c:when test="${businessMsgList.serviceStatus eq 'SUCCESS' }"><label style="color: green;">
       </c:when>
       <c:otherwise><label style="color: red;"></c:otherwise>
       </c:choose>
       
       <c:set var="succcessmsg" value="${d.message }"></c:set>
       <span style="font-size:1vw;">${d.message }</span></label></td>
       </tr>
       </c:forEach>
       </table>
	</div>
<c:forEach items="${menus }" var="i">
 <div id="${i.url }" style="visibility: hidden;position: absolute;">
 <c:set var="page" value="${i.url }"></c:set>
 <c:if test="${i.url ne '' and i.url ne null}">
 <jsp:include page="${i.url }.jsp" flush="true" />
 </c:if>
 </div>
 </c:forEach>
 </div>
<!--  
<div class="topnav" style="margin-top: 30%">
<div style="margin-left: 72px;">
   <a href="#news">@Powered By VANGA SATHISH Chintha Nekkonds</a>
 </div>
</div>  -->

</div>



<div id="updateOccurrannce1" class="modal">
  
  <form class="modal-content animate" action="updateOccurrannce" modelAttribute="OccurranceDto" method="post">
<!--     <div class="imgcontainer">
      <span onclick="document.getElementById('updateOccurrannce1').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div> -->

    <div class="container" id="adddivInterestType">
    
      <table border="0">
                <tr>
                    <th>ActiveHours:</th>
                    <c:set var="ledgercounter" value="${activeOccurrance.ledger }"></c:set>
                    <input style="visibility: hidden;position: absolute;]" type="text" name="ledgerId" value="${ledgercounter.ledger_uid }" />
                    <td><input type="text" name="activehours" value="${activeOccurrance.activehours }" /></td>
                </tr>
                <tr>
                    <th>Reason:</th>
                    <td><input type="text" name="reason" value="${activeOccurrance.reason }" /></td>
                </tr>
                
            </table>
        
      <button style="margin-left: 10%" onclick="loadingstart()" type="submit">Submit</button>
     <!--  <button type="button" onclick="document.getElementById('updateOccurrannce1').style.display='none'" class="cancelbtn">Cancel</button> -->
    </div>

   
  </form>
</div>
</div>
 <c:remove var="businessMsgList"/>
 <div class="footer">
  <p style="font-size: 0.9vw;">@Copy rights: Vanga Sathish, Chintha Nekkonda, 8801140530, All rights reserved</p>
</div>
&nbsp;&nbsp;
&nbsp;&nbsp;
&nbsp;&nbsp;



</body>
</html> 
