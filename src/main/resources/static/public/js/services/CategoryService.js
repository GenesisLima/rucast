/**
 * Category Service
 */
angular.module('rucastServices', ['ngResource'])
    .factory('resourceCategory', function($resource) {

        return $resource('/category/:id', null, {
            'update' : { 
                method: 'PUT'
            }
             
        });
    });

