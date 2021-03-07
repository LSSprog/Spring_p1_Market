angular.module('app').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillOrders = function() {
        $http.get(contextPath + '/api/v1/orders')
        .then(function (response) {
            $scope.OrderList = response.data;
        });
    };

    $scope.fillOrders();

    /*$scope.showMyOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };*/
});