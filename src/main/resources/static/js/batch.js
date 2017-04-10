var app = angular.module('BatchTrigger', []);
app.controller('Batch', function($scope) {
    $scope.count = 0;
    $scope.triggerBatch = function() {
        $scope.count++;
    }
});
