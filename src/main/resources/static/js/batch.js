var app = angular.module('BatchTrigger', []);
app.controller('Batch', function($scope, $http) {
    $scope.count = 0;
    $scope.triggerBatch = function() {
        $scope.count++;
        
        $http.post('/api/import/run').
        then(function(response) {
            //$scope.greeting = response.data;
        });
    }
});
