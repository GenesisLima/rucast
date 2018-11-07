/**
 * Product Service
 */
angular.module('rucastServices')
    .factory('resourceProduct', function($resource) {

        return $resource('/product/:id', null, {
            'update' : { 
                method: 'PUT'
            }
             
        });
    });

