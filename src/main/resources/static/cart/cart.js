angular.module('app').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillCart = function () {
        $http.get(contextPath + '/api/v1/cart')
                 .then(function (response) {
                     $scope.CartList = response.data;
                 });
    };

    $scope.addProductToCart = function (product_id) {
        $http.get(contextPath + '/api/v1/cart/add/' + product_id)
            .then(function (response) {
            $scope.fillCart();
            });
    };

    $scope.clearCart = function () {
            $http.get(contextPath + '/api/v1/cart/clear')
                .then(function (response) {
                    $scope.fillCart();
                });
    };

    $scope.createOrderWithAddress = function(address) {
         $http.post(contextPath + '/api/v1/orders/create/', address)
         .then(function (response) {
             $scope.order.address = null;
             $scope.fillOrders();
             $scope.fillCart();
         });
    };

    $scope.incQuantity = function(id) {
        $http.get(contextPath + '/api/v1/cart/inc/' + id)
        .then(function (response) {
            $scope.fillCart();
        });
    };

    $scope.deleteProductFromCart = function (id) {
            $http.get(contextPath + '/api/v1/cart/delete/' + id)
                .then(function (response) {
                $scope.fillCart();
            });
    };

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.fillCart();

    /*$scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.createOrder = function () {
        $http.get(contextPath + '/api/v1/orders/create')
            .then(function (response) {
                $scope.showMyOrders();
                $scope.showCart();
            });
    }*/

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.fillCart();
});