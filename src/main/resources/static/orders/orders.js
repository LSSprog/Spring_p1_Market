angular.module('app').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillOrders = function() {
        $http.get(contextPath + '/api/v1/orders')
        .then(function (response) {
            $scope.OrderList = response.data;
        });
    };

    $scope.fillOrders();

    // уже не нужно получается
    //    $scope.createOrderWithAddress = function(address) {
    //         $http.post(contextPath + '/api/v1/orders/create/', address)
    //         .then(function (response) {
    //             $scope.order.address = null;
    //             $scope.fillOrders();
    //             //$scope.fillCart();
    //         });
    //    };


    // другой вариант get запроса (синтаксис)
    /*$scope.showMyOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };*/
});