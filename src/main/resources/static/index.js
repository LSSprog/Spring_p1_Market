angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.fillTable = function (pageInd = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title: null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageInd
            }
        }).then(function (response) {
               $scope.ProductsPage = response.data;
               $scope.PaginationArr = $scope.generatePagesInd(1, $scope.ProductsPage.totalPages);
        });
    };


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

     $scope.deleteProductById = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function (response) { //:Т сам дописал, не было его у меня и зачем он не понял
            $scope.fillTable();
        });
     };


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.generatePagesInd = function(fistPage, lastPage) {
        let arr = [];
        for (let i = fistPage; i < lastPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.fillTable();
});