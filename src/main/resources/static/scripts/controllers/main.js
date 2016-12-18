angular.module('udemyApp')
  .controller('MainCtrl', function ($scope, $http) {
	  $http({
          method: 'GET',
          url: 'product-service/productsservice/v1/products'
      }).then(function(response) {
		  if(response.data && response.data.content){
			   $scope.products = response.data.content;
		  }
    	 
    	  console.log($scope.products);
      }, function(response) {
    	  console.error('Error requesting races');
      });
  });