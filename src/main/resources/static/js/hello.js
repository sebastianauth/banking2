angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('/api/greeting').
        then(function(response) {
            $scope.greeting = response.data;
        });
});
