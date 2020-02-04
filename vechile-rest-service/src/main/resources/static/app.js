//Register the module
var app = angular.module('myApp', ['ngMaterial']);

//Controller for myApp
app.controller('VehicleController', function($scope, $http, $mdDialog) {

    var URL = 'http://localhost:8080/vehicleservice'; 
    $scope.vehicles = [];
    $scope.vehicleForm = {
        id : 0,
        make : '',
        model : '',
        year: ''
    };

    _refreshVehicleData();

    //HTTP POST/PUT methods for add/edit vehicle 
    // with the help of id, we are going to find out whether it is put or post operation

    $scope.submitVehicle = function() {

        var method = '';
        var url = '';
        if ($scope.vehicleForm.id == 0) {
            //Id is absent in form data, it is create new vehicle operation
            method = 'POST';
            url = URL + '/vehicles';
        } else {
            //Id is present in form data, it is edit vehicle operation
            method = 'PUT';
            url = URL + '/vehicles';
        }

        $http({
            method : method,
            url : url,
            data : angular.toJson($scope.vehicleForm),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then( _success, _error);
    };

    //HTTP DELETE- delete vehicle by Id
    $scope.deleteVehicle = function(vehicle) {
        $http({
            method : 'DELETE',
            url : URL + '/vehicles/' + vehicle.id
        }).then(_success, _error);
    };

    // In case of edit, populate form fields and assign form.id with vehicle id
    $scope.editVehicle = function(vehicle) {
        $http({
            method : 'GET',
            url : URL + '/vehicles/' + vehicle.id
        }).then(function successCallback(response) {
            $scope.vehicleForm = response.data;
        }, function errorCallback(response) {
            _showDialog(response);
        });
    };

    //HTTP GET- get all vehicles collection
    function _refreshVehicleData() {
        $http({
            method : 'GET',
            url : URL + '/vehicles'
        }).then(function successCallback(response) {
            $scope.vehicles = response.data;
        }, function errorCallback(response) {
            _showDialog(response);
        });
    }

    function _success(response) {
        _refreshVehicleData();
        _clearFormData();
    }

    function _error(response) {
       _showDialog(response);
    }

    function _showDialog(response) {
    	 $mdDialog.show(
                 $mdDialog.alert()
                 .parent(angular.element(document.querySelector('#popupContainer')))
                 .clickOutsideToClose(true)
                 .textContent(response.data.message)
                 .ariaLabel('Alert Dialog Demo')
                 .ok('Got it!')
         );
    }
    
    //Clear the form
    function _clearFormData() {
        $scope.vehicleForm.id = 0;
        $scope.vehicleForm.make = '';
        $scope.vehicleForm.model = '';
        $scope.vehicleForm.year = '';

    }
});