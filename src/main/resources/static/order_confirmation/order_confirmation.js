angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.CartList = response.data;
        });
    };

    $scope.submitOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders/create',
            method: 'POST',
            params: {
                address: $scope.orderInfo.address
            }
        }).then(function (response) {
            $location.path('/order_result/' + response.data.id);
        });
    }

    $scope.cartContentRequest();
});