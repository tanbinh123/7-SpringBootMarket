angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.editUser = function () {
        // $http.post(contextPath + '/api/v1/profile', $scope.editProfile)
        $http({
            url: contextPath + '/api/v1/profile',
            method: 'POST',
            params: {
                // username: $scope.editUser.username ? $scope.editUser.username : null,
                //password: $scope.editUser.password ? $scope.editUser.password : null,
                // newPassword: $scope.editUser.newPassword ? $scope.editUser.newPassword : null,

                id: $scope.Profile.id,
                name: $scope.editProfile.name ? $scope.editProfile.name : null,
                surname: $scope.editProfile.surname ? $scope.editProfile.surname : null,
                phone: $scope.editProfile.phone ? $scope.editProfile.phone : null,
                email: $scope.editProfile.email ? $scope.editProfile.email : null,
                birthday: $scope.editProfile.birthday ? $scope.editProfile.birthday : null,
                gender: $scope.editProfile.gender ? $scope.editProfile.gender : null,
                city: $scope.editProfile.city ? $scope.editProfile.city : null,
            }
        })
            .then(function () {
                window.alert("Edit user ok");
                $scope.editUser = null;
                $scope.editProfile = null;
            });
    };

    $scope.getProfile = function () {
        $http({
            url: contextPath + '/api/v1/profile',
            method: 'GET'
        }).then(function (response) {
            console.log(response.data);
            $scope.Profile = response.data;
        });
    };

    $scope.getProfile();
});