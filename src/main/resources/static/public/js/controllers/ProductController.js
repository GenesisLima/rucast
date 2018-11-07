/**
 * Product Controller
 */
angular.module('rucast').controller('ProductController', function($scope, $routeParams, resourceProduct){
	
	$scope.product = {}
	$scope.mensagem = ''	
	$scope.products = [];
	
	$scope.send = function(){
		resourceProduct.save($scope.product,function(){
			$scope.mensagem = 'Produto '+$scope.product.name +' cadastrado com sucesso.'			
	 		$scope.product = {}; 	
			
			
		})	
 	

 	
}
	
	var entries = resourceProduct.query(function(){ 
		 	 $scope.products = entries;
		 	console.log("PRODUCTS "+entries);
		 }, function(erro){
		 	console.log(erro);

		 })
		 
		  if($routeParams.id){			
			 $scope.product =  resourceProduct.get({id: $routeParams.id}, function(success,error){
				 console.log("PRODUCT "+$scope.product.name);
				  console.log("SUCCESS "+success);
				  console.log("ERROR "+error);
			  });
		     }
	
});

