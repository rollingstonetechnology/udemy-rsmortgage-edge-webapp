angular.module('udemyApp')
  .controller('CartCtrl', function ($scope, $http) {
	  $http({
          method: 'GET',
          url: 'cart-service/shoppingcartservice/v1/cart'
      }).then(function(response) {
		  if(response.data && response.data.content){
			  $scope.cartDate = response.data.content[0].cartDate;
              $scope.cartItems = response.data.content[0].cartItems;
              $scope.user = response.data.content[0].user;
		  }
    	  console.log($scope.cartItems);
      }, function(response) {
    	  console.error('Error requesting races');
      });
  });