var udemyApp = angular.module('udemyApp', ['ngAnimate','ngCookies','ngResource','ngRoute','ngSanitize','ngTouch']);

udemyApp.config(function ($routeProvider, $locationProvider) {
	   $routeProvider
       .when('/applicant',{
           templateUrl: '/views/usersstatic.html',
           controller: 'usersStaicController'
       })
       .when('/roles',{
           templateUrl: '/views/roles.html',
           controller: 'rolesController'
       })
       .when('/users',{
           templateUrl: '/views/users.html',
           controller: 'UserDBController'
       })
       .when('/task',{
           templateUrl: '/views/task.html',
           controller: 'taskManagerController'
       })
       .when('/degreetype',{
           templateUrl: '/views/degreetype.html',
           controller: 'DegreeTypeController'
       })
       .otherwise(
           { redirectTo: '/'}
       );
    $locationProvider.html5Mode(true);
  });

udemyApp.controller('DegreeTypeController', function ($scope,$http) {
	console.log('in Degree Type controller');
	//var urlBase="rsmortgage-degreetype-service";
	var urlBase = "rsmortgage-edge-service";
	$scope.toggle=true;
	$scope.selection = [];
	$http.defaults.headers.post["Content-Type"] = "application/json";

    function findAllDegreeTypes() {
    	console.log('in Degree Type controller findAllDegreeTypes');

        //get all degree types and display initially
        $http.get(urlBase + '/v1/degreeType').
            success(function (data) {
            	console.log('in Degree Type controller findAllDegreeTypes success');
            	console.log('in Degree Type controller findAllDegreeTypes success' + data);
            	console.log(data);
                if (data != undefined) {
                	console.log('data :'+data);
                    $scope.degreeTypes = data;
                    console.log($scope.degreeTypes);
                } else {
                    $scope.degreeTypes = [{
                        "id": 1,
                        "degreeTypeName": "Bachalors",
                        "degreeTypeDescription": "Undergraduate Degree"
                    },
                    {
                        "id": 2,
                        "degreeTypeName": "High School",
                        "degreeTypeDescription": "High School Degree"
                    }
                  ];
                }
                $scope.degreeTypeName="";
                $scope.degreeTypeDescription="";
                $scope.toggle='!toggle';
            });
    }

    findAllDegreeTypes();

	//add a new degree type
	$scope.addDegreeType = function addDegreeType() {
		if($scope.degreeTypeName=="" || $scope.degreeTypeDescription==""){
			alert("Insufficient Data! Please provide values for degree type name and description");
		}
		else{
		 $http.post(urlBase + '/v1/degreeType', {
			 degreeTypeName: $scope.degreeTypeName,
			 degreeTypeDescription: $scope.degreeTypeDescription
         }).
		  success(function(data, status, headers) {
			 alert("Degree Type added");
             var newDegreeTypeUri = headers()["location"];
             console.log("Might be good to GET " + newDegreeTypeUri + " and append the degree type.");
             // Refetching EVERYTHING every time can get expensive over time
             // Better solution would be to $http.get(headers()["location"]) and add it to the list
             findAllDegreeTypes();
		    });
		}
	};
	
});

//Angularjs Directive for confirm dialog box
udemyApp.directive('ngConfirmClick', [
	function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);
