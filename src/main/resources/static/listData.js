angular.module("ListData", []);
angular.module("ListData").provider("ListData", function($scope,$http){
	     $scope.categories = []; //declare an empty array
	    $http.get("/category/api/categories").then(function(response){ 
	        $scope.categories = response.data;  //ajax request to fetch data into $scope.data
	        console.log(response.data);
	    });
	
});



//category form script bkp
/*$("#divAddCategory").ready(function(){

 //angular.element(document.querySelector('#divAddCategory')).click();
working -->    window.customRefresh();
   console.log("SCOPE");	
});
*/
