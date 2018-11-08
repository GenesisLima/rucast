/**
 * Episode Controller
 */
angular.module('rucast').controller('EpisodeController',function($scope, $routeParams, $http, resourceEpisode){
	
	$scope.episode={}
	$scope.episodes = []
	$scope.message = '';
	
	$scope.send = function(){
		
		resourceEpisode.save($scope.episode, function(){
			$scope.message = 'Episódio '+$scope.episode.name+' cadastrado com sucesso!';
			$scope.episode = {}
			
		})		
		
	}
	
//	function(data){
//		var fd = new FormData();
//		angular.forEach(data,function(value,key){
//			fd.append(key,value);
//		});
//			return fd;
//		},
		
	$scope.sendEpisode = function(){
		
	   	$http.post('/upload/file',$scope.foto, {
	   		withCredentials: true,
	   		headers:{'Content-Type':undefined},
	   		transformRequest : angular.identity
	   	})
     	.success(function(){
     		$scope.foto = {}
     		$scope.mensagem = 'Episódio cadastrada com sucesso.'
     		console.log('Episódio adicionado com sucesso.')
     	})
     	.error(function(){
     		$scope.mensagem = 'Não foi possível cadastrar foto'
     		console.log('Não foi possível cadastrar o episódio.')
     	})
	
	}
	
	
	$scope.episodes = resourceEpisode.query(function(){
		
	},function(error){
		console.log(error);
	})
	
	if($routeParams.id){
		$scope.episode = resourceEpisode.get({id: $routeParams.id},function(success,error){
		console.log('SUCCESS '+success);
		console.log('ERROR '+error);
		})
	}
	
	angular.module('rucast').factory('formDataObject', function(){
		return function(data){
		var fd = new FormData();
		angular.forEach(data,function(value,key){
			fd.append(key,value);
		});
			return fd;
		}
	})
	
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


