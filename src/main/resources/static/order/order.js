angular.module('app').controller('orderController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.submitCreateNewOrder = function () {
        $http.post(contextPath + '/api/v1/order', $scope.newOrder)

            .then(function (response) {
                $scope.newOrder = null;
                console.log(response.data());
            });
    };

        // $scope.submitCreateNewOrder = function ()  {
        //     $http({
        //         url: contextPath + '/api/v1/order',
        //         method: 'POST',
        //         params: {
        //             // cart: $scope.cart ? $scope.cart : null,
        //             address: $scope.newOrder ? $scope.newOrder.address : null,
        //             phone_number: $scope.newOrder ? $scope.newOrder.phone_number : null,
        //             receiver_name: $scope.newOrder ? $scope.newOrder.receiver_name : null
        //
        //         }
        //     }).then(function (response) {
        //         $scope.newOrder = null;
        //         console.log(response.data());
        //     });
        // };


    $scope.orderContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/order',
            method: 'GET'
        }).then(function (response) {
            console.log(response.data);
            $scope.cart = response.data;
        });
    };


    $scope.orderContentRequest();
});