/**
 * Category Controller
 */
angular.module('rucast').controller('CategoryController', function($scope, $routeParams, $http, resourceCategory){
	
	$scope.category = {}
	$scope.mensagem = ''
	$scope.categories = [];
	
	$scope.send = function(){
		resourceCategory.save($scope.category,function(){
			$scope.mensagem = 'Categoria '+$scope.category.name +' cadastrada com sucesso.'
	 		$scope.category = {}; 	

		})	
 	

 	
}
	
	var entries = resourceCategory.query(function(){ 
		 	 $scope.categories = entries;
		 	console.log("CATEGORIES "+entries);
		 }, function(erro){
		 	console.log(erro);

		 })
		 
		  if($routeParams.id){			
			 $scope.category =  resourceCategory.get({id: $routeParams.id}, function(success,error){
				  console.log(success);
				  console.log(error);
			  });
		     }
	
	
$scope.categoryCheckBox = {}
	
	$http.get('/category/api/categories').
	success(function(categories){
		$scope.categoryCheckBox = categories;
		
	}).error(function(erro){
		console.log(erro)
	})
	
});

