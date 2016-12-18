udemyApp.controller('DegreeTypeController', function ($scope,$http) {
	console.log('in Degree Type controller');
	var urlBase="rsmortgage-degreetype-service";
	$scope.toggle=true;
	$scope.selection = [];
	$http.defaults.headers.post["Content-Type"] = "application/json";

    function findAllDegreeTypes() {
        //get all degree types and display initially
        $http.get(urlBase + '/v1/degreeType').
            success(function (data) {
                if (data._embedded != undefined) {
                	console.log('data :'+data);
                    $scope.degreeTypes = data._embedded.degreeTypes;
                } else {
                    $scope.degreeTypes = [];
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
