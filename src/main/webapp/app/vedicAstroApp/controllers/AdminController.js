(function() {
	'use strict';
	angular.module('vedicAstroApp').controller('AdminController',
			AdminController);

	AdminController.$inject = [ '$rootScope', '$scope', 'MemberService',
			'ReferenceDataService' ];

	function AdminController($rootScope, $scope, MemberService,
			ReferenceDataService) {
		var vm = this;
		vm.panelTitle = "< Manage member >";
		vm.countries = [];
		vm.member = {};
		vm.cities = [];

		$scope.members = [];
		$scope.selected = undefined;

		$scope.alerts = [];

		$scope.addAlert = function() {
			$scope.alerts.push({
				msg : 'Another alert!'
			});
		};

		$scope.closeAlert = function(index) {
			$scope.alerts.splice(index, 1);
			vm.member = {};
			$scope.selected = undefined;
		};

		vm.loadMember = loadMember;
		vm.saveMember = saveMember;
		vm.cancel = cancel;

		(function init() {
			loadAllCountries();
			loadAllCities();
			angular.element(document.getElementById('datetimepicker1'))
					.datetimepicker();
			loadMembers();
		})();

		function loadAllCountries() {
			ReferenceDataService.getData('countries').then(function(countries) {
				vm.countries = countries;
			});
		}

		function loadAllCities() {
			ReferenceDataService.getData('cities').then(function(cities) {
				vm.cities = cities;
			});
		}

		function loadMembers() {
			MemberService.getAll().then(function(members) {
				$scope.members = members;
			});
		}

		function loadMember(memberId) {
			MemberService.getByMemberId(memberId).then(function(member) {
				vm.member = member;
			});
		}

		function saveMember(member) {
			MemberService.save(member).then(function(response) {
						loadMembers();
					});
			$scope.alerts.push({
				type : 'success', msg : 'Member saved successfully !'
			});
		}

		function cancel() {
			vm.member = {};
			$scope.selected = undefined;
		}
	}
}());
