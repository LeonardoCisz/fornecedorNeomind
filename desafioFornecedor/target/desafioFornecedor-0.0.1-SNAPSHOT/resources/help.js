angular.module("fornec", []).controller("ChamadoController", function($scope, $http) {
	$scope.novoFornecedor = { id: '', name: '', email: '', comment: '', cnpj: '' };

	$http.get("http://localhost:8080/desafioFornecedor/rest").then(function(response) {
		$scope.fornecedores = response.data;
	});

	$scope.inserir = function(existe) {
		$scope.erroUsuarioExiste = false;
		if (existe) {
			$http.put("http://localhost:8080/desafioFornecedor/rest/" + $scope.fornecedor.id, $scope.fornecedor).then(function(responsePost) {
				$http.get("http://localhost:8080/desafioFornecedor/rest").then(function(responseGet) {
					$scope.fornecedores = responseGet.data;
				});
			});
		}
		else {
			$http.post("http://localhost:8080/desafioFornecedor/rest", $scope.novoFornecedor).then(function(responsePost) {
				if (responsePost.data.id == null) {
					$scope.erroUsuarioExiste = true;
				}
				else {
					$scope.erroUsuarioExiste = false;
				}
				$http.get("http://localhost:8080/desafioFornecedor/rest").then(function(responseGet) {
					$scope.fornecedores = responseGet.data;
				});
			});
		}
	}
	$scope.limpaDados = function() {
		$scope.erroUsuarioExiste = false;
		$scope.novoFornecedor = { id: '', name: '', email: '', comment: '', cnpj: '' };
	}
	$scope.editar = function(id) {
		$scope.erroUsuarioExiste = false;
		$http.get("http://localhost:8080/desafioFornecedor/rest" + id).then(function(responseGet) {
			$scope.fornecedor = responseGet.data;
		});
	}
	$scope.delete = function(id) {
		$scope.erroUsuarioExiste = false;
		$http.delete("http://localhost:8080/desafioFornecedor/rest" + id);
		$http.get("http://localhost:8080/desafioFornecedor/rest").then(function(responseGet) {
			$scope.fornecedores = responseGet.data;
		});

	}
	

}).directive('onlyDigits', function() {
	return {
		require: 'ngModel',
		link: function(scope, element, attr, ngModelCtrl) {
			function fromUser(text) {
				if (text) {
					var transformedInput = text.replace(/[^0-9]/g, '');

					if (transformedInput !== text) {
						ngModelCtrl.$setViewValue(transformedInput);
						ngModelCtrl.$render();
					}
					return transformedInput;
				}
				return undefined;
			}
			ngModelCtrl.$parsers.push(fromUser);
		}
	};
})