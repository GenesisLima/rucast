/**
 * Episode Service
 */

angular.module('rucastServices')
.factory('resourceEpisode',function($resource){
	
	 return $resource('/episode/:id',null,{
		'update' :{
			method :'PUT'
		}
	 })
	
});