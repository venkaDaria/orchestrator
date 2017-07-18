angular.module('hello', ['ngMaterial'])
	.controller('home', function($scope, $http) {
		$http.get('/').success(function() {
			$scope.greeting = "world";
		})
	});