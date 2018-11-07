angular.module("rucast", ['ngRoute','ngResource','rucastServices'])
.config(function($routeProvider, $locationProvider){
	
	$locationProvider.html5Mode(true);

	 //Routes for Categories
	 $routeProvider.when('/category', {
	        templateUrl: 'public/categoryForm.html',
	        controller: 'CategoryController'
	    });
	 
	 $routeProvider.when('/categories', {
	        templateUrl: 'public/listCategories.html',
	        controller: 'CategoryController'
	    });
	 
	 $routeProvider.when('/category/:id', {
	        templateUrl: 'public/categoryForm.html',
	        controller: 'CategoryController'
	    });
	 
	 
	 //Routes for Products
	 $routeProvider.when('/product', {
	        templateUrl: 'public/productForm.html',
	        controller: 'ProductController'
	    });
	 
	 $routeProvider.when('/products',{		 
		 templateUrl: 'public/listProducts.html',
		 controller: 'ProductController'		 
	 });
	 
	 $routeProvider.when('/product/:id', {
	        templateUrl: 'public/productForm.html',
	        controller: 'ProductController'
	    });
	 
	 
	 $routeProvider.otherwise({redirectTo: '/'});
});

//, function(){
//	console.log('PASSANDO PELO CATEGORY CONTROLLER')
//}