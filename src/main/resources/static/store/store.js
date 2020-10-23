angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';


    // $scope.dataCategory = {
    //     options: [
    //         {id: '1', name: 'grocery'},
    //         {id: '2', name: 'industrial'},
    //         {id: '3', name: 'electronics'},
    //         {id: '4', name: 'digital'}
    //     ]
    // }


    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                category: $scope.filter ? $scope.filter.category : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.ProductsPage = response.data;
            $scope.PaginationArray = $scope.generatePagesInd(1, $scope.ProductsPage.totalPages)
        });
    };

    $scope.getCategory = function () {
        $http({
            url: contextPath + '/api/v1/category',
            method: 'GET'
        }).then(function (response) {
            console.log(response.data);
            $scope.dataCategory = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            console.log("add to cart")
        });
    };

    $scope.generatePagesInd = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.getCategory();
    $scope.fillTable();
});