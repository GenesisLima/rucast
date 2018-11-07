var app = angular.module("RUCast", []);

//app.service('CategoryService', function(){
//	var category = {}
//		 
// 
//	 
//	 function setCategory(MyCategory){
//		 console.log("ID "+MyCategory.id +" CATEGORY "+MyCategory.name+" DESC "+ MyCategory.description);		
//		this.category = MyCategory;
//		return this.category;
//	 }
//	 function getCategory(){
//			console.log("GET CATEGORY "+category.name);
//			 return category;
//		 }
//
//	 return {
////	  setName: setName,
////	  getName: getName,
//      setCategory: setCategory,
//	  getCategory: getCategory
//	 }
//
// });


app.controller('RUCastController', function($scope, $http, $interval,$window,$timeout) {

	var controller = this;
	//controller.category = {}
	 category = {};
	 
	
//	$scope.$watch(function(scope) {
//		console.log(scope.category);
//		return scope.category; },
//            function(newValue, oldValue) {
//			//console.log("NEW "+ newValue.name+" "+"OLD "+oldValue);
//                //document.getElementById("categoryName").value = newValue.name;
//			//document.getElementById("categoryName").setAttribute("value", newValue.name);
//			if(!(typeof newValue === "undefined")){
//			$("#categoryName").val(newValue.name);		
//			//
//            }
//           );	}

	
    $scope.message = "Hello Angular";
    //$scope.page = "";
    $scope.shows = [];
    $scope.categories = [];
    
   
   // $scope.category.id = "";
    $scope.episode;
    $scope.sortKey;
    $scope.reverse;   
    $scope.page;
    
    $scope.setCategory = function(category){
    	$scope.category = category;
    	console.log("CATEGORY "+ category.name);    	
    	return category;
    	
    };
//    
  // CategoryService.getCategory();   
//   var refreshCategory =  $timeout(function refreshCategory() {    	   
//    	    
//	   angular.element('#addCategory').triggerHandler('click');
//    	   // angular.element('#addCategory').scope().$apply();
//    	
//    	},300);
    	
    	$window.customRefresh = function(){
    		console.log("CUSTOM REFRESH");
    		//refreshCategory();
    		if((angular.equals({category}, {}))){
    			console.log("CATEGORY NOT NULL "+category.name);
    		$timeout(function() {   
    			this.category = {}
    			angular.element('#addCategory').triggerHandler('click');
    			},
    			100);
    		} else{
    			console.log("CATEGORY NULL "+category.name);
    		
    			$timeout(function() {    		
        			angular.element('#addCategory').triggerHandler('click');
        			},
        			100);
    		}
    		
    		
    		
    		  // angular.element(document.getElementById('#rucastcontroller')).scope().refreshCategory();
    	       // angular.element('#rucastcontroller').scope().$apply() 
    	}
    
    
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    }

    var loadShows = function() {
        $http.get("/shows/api/shows").then(function(response) {
            $scope.shows = response.data;
            console.log(response.data);
        });
    };

    var loadCategories = function() {
        $http.get("/category/api/categories").then(function(response) {
            $scope.categories = response.data;
            console.log(response.data);
        });
    };
    
    //(typeof this.category == "undefined") || 
     $scope.loadCategoryForm = function(){
    	page = "/public/categoryForm.html";
    	category = {}
    	
//    	if(category != null ){    	
//        	console.log("CATEGORIA NAME "+this.category.name);
//              
//    	}
    
    return page;	
    }
     
     $scope.clearCategory = function(){
    	 this.category = {}
     }
    
    
    
    loadShows();
    loadCategories();
    
  
});
