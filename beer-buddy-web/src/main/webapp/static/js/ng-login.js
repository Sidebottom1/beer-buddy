angular.module('beer-buddy-app')

.controller('LoginController', [ '$scope', '$rootScope', '$state', '$resource',
	function($scope, $rootScope, $state, $resource) {

	var User = $resource("/users");
	var Login = $resource("/login");
	
	$rootScope.menu = $rootScope.menu || {};
	$rootScope.menu.currentPage = "Beer Buddy";

	$scope.user = {};
	$scope.newUser = {};
	$scope.passwordReset = {};
	$rootScope.user = $scope.user;
	
	var clearErrors = function() {
		delete $scope.loginForm.username.$error.serverError;
		delete $scope.loginForm.username.$error.serverErrorMessage;
		delete $scope.error;
		clearFlags();
	};
	var clearFlags = function() {
		$scope.signupComplete = false;
		$scope.signupInProgress = false;
		$scope.loginInProgress = false;
		$scope.passwordResetComplete = false;
		$scope.passwordResetInProgress = false;
	};
	clearFlags();
	
	$scope.submitLogin = function() {
		clearErrors();
		if( $scope.loginForm.$valid ) {
			$scope.loginInProgress = true;
			new Login($scope.user).$save(function(response) {
				console.log(response);
				
				$scope.signupComplete = false;
				$scope.user = response;
				$rootScope.user = $scope.user;
				$scope.user.isLoggedIn = true;
				
				$scope.loginInProgress = false;
				$state.go("home");
			}, function(error) {
				console.log(error);
				if( error ) {
					$scope.error = error.data || {}; 
				}
				$scope.loginInProgress = false;
			});
		}
	};
	
	$scope.submitSignup = function() {
		clearErrors();
		if( $scope.signupForm.$valid ) {
			$scope.signupInProgress = true;
			new User($scope.newUser).$save(function(response) {
				console.log(response);
				$scope.signupComplete = true;
				$scope.signupInProgress = false;
				$scope.newUser = {};
			}, function(error) {
				console.log(error);
				if( error.data.error ) {
					$scope.signupForm.serverError = error.data || {};
					$scope.signupForm.username.$error.serverError = error.data.error; 
					$scope.signupForm.username.$error.serverErrorMessage = error.data.message;
				}
				$scope.signupInProgress = false;
			});
		}
	};
	
	$scope.passwordReset = function() {
		clearErrors();
		if( $scope.passwordResetForm.$valid ) {
			$scope.passwordResetInProgress = true;
			new PasswordReset($scope.passwordReset).$save(function(response) {
				console.log(response);
				$scope.passwordResetComplete = true;
				$scope.passwordResetInProgress = false;
				$scope.passwordReset = {};
			}, function(error) {
				console.log(error);
				if( error.data.error ) {
					$scope.passwordResetForm.serverError = error.data || {};
					$scope.passwordResetForm.username.$error.serverError = error.data.error; 
					$scope.passwordResetForm.username.$error.serverErrorMessage = error.data.message;
					$scope.passwordResetForm.question.$error.serverError = error.data.error; 
					$scope.passwordResetForm.question.$error.serverErrorMessage = error.data.message;
					$scope.passwordResetForm.response.$error.serverError = error.data.error; 
					$scope.passwordResetForm.response.$error.serverErrorMessage = error.data.message;
					$scope.passwordResetForm.email.$error.serverError = error.data.error; 
					$scope.passwordResetForm.email.$error.serverErrorMessage = error.data.message;
					$scope.passwordResetForm.password.$error.serverError = error.data.error; 
					$scope.passwordResetForm.password.$error.serverErrorMessage = error.data.message;
				}
				$scope.passwordResetInProgress = false;
			});
		}
	};

	
	}])

.controller('LogoutController', [ '$scope', '$rootScope', '$state', '$resource',
	function($scope, $rootScope, $state, $resource) {

	var Logout = $resource("/logout");
	
	$rootScope.menu = $rootScope.menu || {};
	$rootScope.menu.currentPage = "Beer Buddy";

	Logout.get(function(response) {
		$state.go("login");
	}, function(response) {
		$state.go("login");
	});
	
	}])


;
