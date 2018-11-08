/**
 * 
 */
angular.module('rucast').controller('ProductComboController',function($scope,$http){
	
	$scope.productsCombo = {}
	
	$http.get('/product/names').
	success(function(productsCombo){
		$scope.productsCombo = productsCombo;
		
	}).error(function(erro){
		console.log(erro)
	})
	
})