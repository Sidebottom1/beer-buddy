<!DOCTYPE html>
<html ng-app="beer-buddy-app" ng-strict-di>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<head>
<title><sitemesh:write property='title'/></title>

<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<!-- 
<link rel="stylesheet"
	href="<c:url value='/static/lib/bootstrap/3.2.0/css/bootstrap.min.css' />">

<link rel="stylesheet"
	href="<c:url value='/static/lib/bootstrap/3.2.0/css/bootstrap-theme.min.css'/>">
 -->

<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no" />
    
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/angular-material.css'/>">

<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/amber-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/blue-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/cyan-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/green-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/light-blue-dark-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/light-green-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/lime-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/orange-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/red-theme.css'/>">
<link rel="stylesheet" href="<c:url value='/static/bower_components/angular-material/themes/teal-theme.css'/>">

<!-- Popup styles for thingies -->
<link rel="stylesheet" href="<c:url value='/static/css/popup.css'/>">

<link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">


<script src="<c:url value='/static/lib/jquery/1.11.1/jquery.min.js'/>"></script>
	<script src="<c:url value='/static/bower_components/angular/angular.js'/>"></script>
    <script src="<c:url value='/static/bower_components/angular-aria/angular-aria.js'/>"></script>
    <script src="<c:url value='/static/bower_components/angular-animate/angular-animate.js'/>"></script>
    <script src="<c:url value='/static/bower_components/angular-resource/angular-resource.js'/>"></script>
    <script src="<c:url value='/static/bower_components/angular-messages/angular-messages.min.js'/>"></script>
    <script src="<c:url value='/static/bower_components/angular-ui-router/release/angular-ui-router.min.js'/>"></script>
    <script src="<c:url value='/static/bower_components/hammerjs/hammer.js'/>"></script>
    <script src="<c:url value='/static/bower_components/angular-material/angular-material.js'/>"></script>
	<script src="<c:url value='/static/js/popup.js'/>"></script>
	
	
	<script type="text/javascript">
		
		var beerBuddyContext = {
			contextPath : "<%= request.getContextPath() %>"
			<c:if test="${not empty user}">
			, user : {
				  "name" : "${user.name}",
				  "lastLogin" : "${user.lastLogin}",
				  "username" : "${user.username}",
				  "email" : "${user.email}",
				  "lastLoginInMillis" : ${user.lastLoginInMillis},
				  "isLoggedIn": true,
				  isLoggedIn: true
			}
			</c:if> 
		};
	</script> 
	
	<!-- This bit of code allows us to generate a popup window. -->
	<script type="text/javascript">	
	
		var popup;
		var content;
		
		function setupPopup(){
			
			if(popup)
				popup.hide();
			
			popup = new Popup();
		}
		
		function setPopupContent(input){
			content = input;
		}
		
		function surprise(){
			popup.setContent(content).show();
		}
		
		function closePopup(){
			popup.hide();
		}
		
		//The below functions give added functionality.
		function signupFromPopup(){
			location.replace('/#/login'); 
			closePopup();
		}
		
		function showLoginForm(){
			content = '<h1>Log In</h1>';
			
			popup.setContent(content).show();
		}
		
		function checkLoggedIn(){
			var isLoggedIn = false;
			var location = " " + window.location;
			
			<c:if test="${not empty user}">
				isLoggedIn = true;
			</c:if> 
			
			if(!isLoggedIn && location.indexOf("login") < 0){
				setupPopup();
				content =  '<div id = "popupContent">';
				content += '<h1>Welcome to BeerBuddy</h1>';
				content += '<h2>My dear beverage avenger!</h2>';
				content += '<br/>';
				content += '<br/>';
				content += '<div style = "width:100%; text-align:center; padding:10px;">To get the most out of this site, you must log in or sign up.</div>';
				content += '<br/>';
				content += '<img src = "/static/imgs/decoration/beer-icon.png" style = "width:50%; height:auto; margin-left:25%;"/>';
				content += '<br/>';
				content += '<div class = "longButtonYellow" value = "Cancel" onclick = "showLoginForm()">Log In</div>';
				content += '<br/>';
				content += '<div class = "longButtonBlue" value = "Cancel" onclick = "signupFromPopup()">Sign Up</div>';
				content += '</div>';
				surprise();
			}
			else{
			
			}
		}
		
	</script>
	<script src="<c:url value='/static/js/main.js'/>"></script>
	<script src="<c:url value='/static/js/ng-app.js'/>"></script>
	<script src="<c:url value='/static/js/ng-home.js'/>"></script>
	<script src="<c:url value='/static/js/ng-login.js'/>"></script>

<sitemesh:write property='head'/>

</head>
<body onload = "checkLoggedIn()">
	<div class="container theme-showcase" data-role="main">

		<%--
		<jsp:include page="/WEB-INF/jsp/common/navbar.jsp" />
		--%>
		
      	<sitemesh:write property='body'/>
      	
	</div>
	

	<!-- 
	<script src="<c:url value='/static/lib/angularjs/1.3.2/angular.min.js'/>"></script>
	<script src="<c:url value='/static/lib/angularjs/1.3.2/angular-resource.js'/>"></script>
	 -->
	
	
</body>
</html>