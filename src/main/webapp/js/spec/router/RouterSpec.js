define(function(require) {
	var Router = require('Router');
	describe("Rotas", function() {

		beforeEach(function() {
			try {
				Backbone.history.stop();
			} catch (e) {
				console.error(e);
			}
		});
		
		afterEach(function() {
			// Reset URL
			var router = new Router();
			router.navigate("");
		});
		
				it("Rota de 'Genres'", function() {
			spyOn(Router.prototype, "genres")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/genres', true);
			expect(Router.prototype.genres).toHaveBeenCalled();
		});

		it("Rota de 'newGenre'", function() {
			spyOn(Router.prototype, "newGenre")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newGenre', true);
			expect(Router.prototype.newGenre).toHaveBeenCalled();
		});
		
		it("Rota de 'editGenre'", function() {
			spyOn(Router.prototype, "editGenre")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editGenre/1', true);
			expect(Router.prototype.editGenre).toHaveBeenCalled();
		});
		it("Rota de 'Movies'", function() {
			spyOn(Router.prototype, "movies")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/movies', true);
			expect(Router.prototype.movies).toHaveBeenCalled();
		});

		it("Rota de 'newMovie'", function() {
			spyOn(Router.prototype, "newMovie")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newMovie', true);
			expect(Router.prototype.newMovie).toHaveBeenCalled();
		});
		
		it("Rota de 'editMovie'", function() {
			spyOn(Router.prototype, "editMovie")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editMovie/1', true);
			expect(Router.prototype.editMovie).toHaveBeenCalled();
		});
		it("Rota de 'Languages'", function() {
			spyOn(Router.prototype, "languages")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/languages', true);
			expect(Router.prototype.languages).toHaveBeenCalled();
		});

		it("Rota de 'newLanguage'", function() {
			spyOn(Router.prototype, "newLanguage")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newLanguage', true);
			expect(Router.prototype.newLanguage).toHaveBeenCalled();
		});
		
		it("Rota de 'editLanguage'", function() {
			spyOn(Router.prototype, "editLanguage")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editLanguage/1', true);
			expect(Router.prototype.editLanguage).toHaveBeenCalled();
		});
		it("Rota de 'Actors'", function() {
			spyOn(Router.prototype, "actors")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/actors', true);
			expect(Router.prototype.actors).toHaveBeenCalled();
		});

		it("Rota de 'newActor'", function() {
			spyOn(Router.prototype, "newActor")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newActor', true);
			expect(Router.prototype.newActor).toHaveBeenCalled();
		});
		
		it("Rota de 'editActor'", function() {
			spyOn(Router.prototype, "editActor")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editActor/1', true);
			expect(Router.prototype.editActor).toHaveBeenCalled();
		});
		it("Rota de 'Users'", function() {
			spyOn(Router.prototype, "users")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/users', true);
			expect(Router.prototype.users).toHaveBeenCalled();
		});

		it("Rota de 'newUser'", function() {
			spyOn(Router.prototype, "newUser")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newUser', true);
			expect(Router.prototype.newUser).toHaveBeenCalled();
		});
		
		it("Rota de 'editUser'", function() {
			spyOn(Router.prototype, "editUser")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editUser/1', true);
			expect(Router.prototype.editUser).toHaveBeenCalled();
		});
		it("Rota de 'Roles'", function() {
			spyOn(Router.prototype, "roles")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/roles', true);
			expect(Router.prototype.roles).toHaveBeenCalled();
		});

		it("Rota de 'newRole'", function() {
			spyOn(Router.prototype, "newRole")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newRole', true);
			expect(Router.prototype.newRole).toHaveBeenCalled();
		});
		
		it("Rota de 'editRole'", function() {
			spyOn(Router.prototype, "editRole")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editRole/1', true);
			expect(Router.prototype.editRole).toHaveBeenCalled();
		});
		it("Rota de 'Permissions'", function() {
			spyOn(Router.prototype, "permissions")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/permissions', true);
			expect(Router.prototype.permissions).toHaveBeenCalled();
		});

		it("Rota de 'newPermission'", function() {
			spyOn(Router.prototype, "newPermission")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newPermission', true);
			expect(Router.prototype.newPermission).toHaveBeenCalled();
		});
		
		it("Rota de 'editPermission'", function() {
			spyOn(Router.prototype, "editPermission")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editPermission/1', true);
			expect(Router.prototype.editPermission).toHaveBeenCalled();
		});
		it("Rota de 'Groups'", function() {
			spyOn(Router.prototype, "groups")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/groups', true);
			expect(Router.prototype.groups).toHaveBeenCalled();
		});

		it("Rota de 'newGroup'", function() {
			spyOn(Router.prototype, "newGroup")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newGroup', true);
			expect(Router.prototype.newGroup).toHaveBeenCalled();
		});
		
		it("Rota de 'editGroup'", function() {
			spyOn(Router.prototype, "editGroup")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editGroup/1', true);
			expect(Router.prototype.editGroup).toHaveBeenCalled();
		});
		it("Rota de 'Items'", function() {
			spyOn(Router.prototype, "items")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/items', true);
			expect(Router.prototype.items).toHaveBeenCalled();
		});

		it("Rota de 'newItem'", function() {
			spyOn(Router.prototype, "newItem")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/newItem', true);
			expect(Router.prototype.newItem).toHaveBeenCalled();
		});
		
		it("Rota de 'editItem'", function() {
			spyOn(Router.prototype, "editItem")
			var router = new Router();
			Backbone.history.start();
			router.navigate('app/editItem/1', true);
			expect(Router.prototype.editItem).toHaveBeenCalled();
		});
	});
})
