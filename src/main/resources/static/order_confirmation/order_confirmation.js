angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart' + $localStorage.happyCartUuid), //а здесь не будет видно uuid?
            method: 'GET'
        }).then(function (response) {
            $scope.marketUserCart = response.data;
        });
    };

    $scope.submitOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            params: {
                cartUuid: $localStorage.marketCartUuid,
                address: $scope.orderInfo.address
            }
        }).then(function (response) {
            $location.path('/order_result/' + response.data.id);
        });
    }

    $scope.cartContentRequest();
});