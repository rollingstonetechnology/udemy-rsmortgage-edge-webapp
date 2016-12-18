angular.module('udemyApp')
  .controller('OrderingCtrl', function ($scope, $http) {
	  $http({
          method: 'GET',
          url: 'ordering-service//orderingservice/v1/order'
      }).then(function(response) {
    	  console.log('response data :'+response);

    	  if(response.data && response.data.content){
			  $scope.orderDate = response.data.content[0].orderDate;
              $scope.orderItems = response.data.content[0].orderItems;
        	  console.log(response.data.content[0].orderItems);

              $scope.orderTotalValue = response.data.content[0].orderTotalValue;
              $scope.user = response.data.content[0].user;
		  }
    	  console.log($scope.orderItems);
    	  for(i=0;i< $scope.orderItems.length;i++){
    		  console.log($scope.orderItems[i]);
    	  }
      }, function(response) {
    	  console.error('Error requesting races');
      });
  });