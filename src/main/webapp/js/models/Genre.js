/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
define(function(require) {
	var _ = require('adapters/underscore-adapter');
	var $ = require('adapters/jquery-adapter');
	var Backbone = require('adapters/backbone-adapter');
	var JSetup = require('views/components/JSetup');	

	var GenreModel = JSetup.BaseModel.extend({

		urlRoot : 'rs/crud/genres',

		defaults : {
			id: null,
	    	name : '',    	
			movies : null,
		
		}
	});
	
	var GenreCollection = JSetup.BaseCollection.extend({
		model : GenreModel,
		
		url : 'rs/crud/genres',
	});
	
	var GenresCollection = JSetup.BasePageableCollection.extend({
		model : GenreModel,

		url : 'rs/crud/genres',

		mode : 'server',
	});

	var GenrePageClientCollection = GenresCollection.extend({
		mode : 'client',
		state : {
			pageSize : 10,
		},
	})
	
	return {
		Model : GenreModel,
		Collection : GenreCollection,
	  	PageCollection : GenresCollection,
	  	PageClientCollection : GenrePageClientCollection, 
	};
});
