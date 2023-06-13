(function () {

    angular
        .module('app')
        .controller('LoginController', LoginController);

   
    function LoginController() {
        var vm = this;

        vm.login = login;

        (function initController() {
            
        })();

        function login() {
            console.log("login");
            console.log(vm.username +  vm.password);
        };
    }

})();