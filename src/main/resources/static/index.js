angular.module('app', []).controller('indexController', function ($scope, $http) {
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


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

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
                $scope.user.username = null;
                $scope.user.password = null;
                $scope.authorized = true;
                $scope.fillTable();
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