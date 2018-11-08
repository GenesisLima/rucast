/**
 * Utility Controller for Category
 */
angular.module('rucast').controller('CategoryUtilityController',function($http){
	
	$scope.categoryCheckBox = {}
	
	$http.get('/category/api/categories')
	.success(function(categories){
		$scope.categoryCheckBox = categories;
		console.log('CATEGORIES '+categories)
	})
	.error(function(){
		console.log(erro);
	})	
})


