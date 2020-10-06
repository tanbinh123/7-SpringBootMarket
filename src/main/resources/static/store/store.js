angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function () {
        console.log('fill');
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data;
            });
    };

    // $scope.applyFilter = function () {
    //     $http({
    //         url: contextPath + '/api/v1/books',
    //         method: "GET",
    //         params: {obj_title: $scope.obj.title, obj_price: $scope.obj.price}
    //     }).then(function (response) {
    //         ...
    //     });
    // }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.submitApplyNewFilter = function () {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: $scope.p != null ? $scope.p : 1,
                title: $scope.newFilter != null ? $scope.newFilter.title : '',
                min_price: $scope.newFilter != null ? $scope.newFilter.min_price : '',
                max_price: $scope.newFilter != null ? $scope.newFilter.max_price : ''
            }
        }).then(function (response) {
            $scope.Products = response.data;
        });
    };


    $scope.fillTable();
});