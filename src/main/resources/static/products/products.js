angular.module('app').controller('productsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

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

    $scope.generatePagesInd = function(fistPage, lastPage) {
        let arr = [];
        for (let i = fistPage; i < lastPage + 1; i++) {
            arr.push(i);
        };
        return arr;
    };

// Это когда была корзина на бэке
//    $scope.addProductToCart = function (id) {
//        $http.get(contextPath + '/api/v1/cart/add/' + id)
//            .then(function (response) {
//            });
//    };

// теперь корзина на фронте
    $scope.addToCartJS = function (productId) {
        $http.get(contextPath + '/api/v1/products/' + productId)
            .then(function (response) {
                $localStorage.marketCart.add(response.data);
                console.log($localStorage.marketCart);
            });
    }


    $scope.fillTable();

// Это получаетяс не нужно
//    $scope.createOrderWithAddress = function(address) {
//         $http.post(contextPath + '/api/v1/orders/create/', address)
//         .then(function (response) {
//             $scope.order.address = null;
//         });
//
//    };

    /*$scope.showProductsPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/api/v1/cart/add/' + productId)
            .then(function (response) {
            });
    }

    $scope.createOrder = function () {
        $http.get(contextPath + '/api/v1/orders/create')
            .then(function (response) {
            });
    }*/

});