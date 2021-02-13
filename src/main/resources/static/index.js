angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';
    $scope.authorized = false;
    $scope.nameauth = null;

    $scope.fillTable = function (pageInd = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title: null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageInd
            }
        }).then(function (response) {
               $scope.ProductsPage = response.data;

               let minPageInd = pageInd - 2;
               if (minPageInd < 1) {
               minPageInd = 1;
               }
               let maxPageInd = pageInd + 2;
               if (maxPageInd > $scope.ProductsPage.totalPages) {
               maxPageInd = $scope.ProductsPage.totalPages;
               }

               $scope.PaginationArr = $scope.generatePagesInd(minPageInd, maxPageInd);
        });
    };


     $scope.deleteProductById = function (id) {
        $http.delete(contextPath + '/api/v1/products/' + id)
            .then(function (response) { //:Т сам дописал, не было его у меня и зачем он не понял
            $scope.fillTable();
        });
     };


//    $scope.submitCreateNewProduct = function () {
//        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
//            .then(function (response) {
//                $scope.newProduct = null;
//                $scope.fillTable();
//            });
//    };

    $scope.generatePagesInd = function(fistPage, lastPage) {
        let arr = [];
        for (let i = fistPage; i < lastPage + 1; i++) {
            arr.push(i);
        };
        return arr;
    };

    $scope.fillCart = function () {
        $http.get(contextPath + '/api/v1/cart')
                 .then(function (response) {
                     $scope.CartList = response.data;
                 });
    };

    $scope.deleteProductFromCart = function (id) {
            $http.get(contextPath + '/api/v1/cart/delete/' + id)
                .then(function (response) {
                $scope.fillCart();
            });
    };

    $scope.addProductToCart = function (id) {
        $http.get(contextPath + '/api/v1/cart/add/' + id)
            .then(function (response) {
            $scope.fillCart();
            });
    };

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
        .then(function successCallback(response) {
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $scope.nameauth = $scope.user.username;
                                       // $localStorage.marketUsername = $scope.user.username;
                                       // $localStorage.marketTokenWithBearerPrefix = 'Bearer ' + response.data.token;
                $scope.user.username = null;
                $scope.user.password = null;
                $scope.authorized = true;
                $scope.fillTable();
                $scope.CartList = null;
                $scope.fillCart();
                $scope.OrderList = null;
                $scope.fillOrders();
                }
            }, function errorCallback(response) {
                window.alert("Error");
                });
    };

    $scope.clearCart = function () {
            $http.get(contextPath + '/api/v1/cart/clear')
                .then(function (response) {
                    $scope.fillCart();
                });
        };

    $scope.incQuantity = function(id) {
        $http.get(contextPath + '/api/v1/cart/inc/' + id)
        .then(function (response) {
            $scope.fillCart();
        });
    };

    $scope.fillOrders = function() {
        $http.get(contextPath + '/api/v1/orders')
        .then(function (response) {
            $scope.OrderList = response.data;
        });
    };


    $scope.createOrder = function(address) {
        $http.get(contextPath + '/api/v1/orders/create')
        .then(function (response) {
            $scope.fillOrders();
            $scope.fillCart();
        });
    };

    $scope.createOrderWithAddress = function(address) { //переделать потом на Post запрос
         $http.get(contextPath + '/api/v1/orders/create/' + address)
         .then(function (response) {
             $scope.order.address = null;
             $scope.fillOrders();
             $scope.fillCart();
         });

    };

// скопировал это просто, чтобы было, как образец
            // if ($localStorage.marketUsername) {
            //     $http.defaults.headers.common.Authorization = $localStorage.marketTokenWithBearerPrefix;
            //     $scope.fillTable();
            //     $scope.fillOrders();
            //     $scope.fillCart();
            //     $scope.authorized = true;
            // }


// для кнопки выход
        // $scope.logout = function () {
        //     $http.defaults.headers.common.Authorization = null;
        //     delete $localStorage.marketUsername;
        //     delete $localStorage.marketTokenWithBearerPrefix;
        //     $scope.authorized = false;
        // }






//    $scope.fillTable();
//    $scope.fillCart(); // надо ли здесь эту функцию?

    //    $scope.fillTable = function () {
    //        $http({
    //            url: contextPath + '/products',
    //            method: 'GET',
    //            params: {
    //                min_price: $scope.filter ? $scope.filter.min_price : null,
    //                max_price: $scope.filter ? $scope.filter.max_price : null
    //            }
    //        }).then(function (response) {
    //            $scope.ProductsList = response.data;
    //        });
    //    };

    //     $scope.fillTable = function () {
    //         $http.get(contextPath + '/products')
    //             .then(function (response) {
    //                 console.log(response);
    //                 $scope.ProductsList = response.data;
    //             });
    //     };

});