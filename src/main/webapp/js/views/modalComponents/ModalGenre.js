/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
define(function(require) {
	var util = require('utilities/utils');
	var JSetup = require('views/components/JSetup');
	var Genre = require('models/Genre');
	
	var ModalGenreTemplate = require('text!views/modalComponents/tpl/ModalGenreTemplate.html');


	var GenreModal = JSetup.View.extend({
		template : _.template(ModalGenreTemplate),

		/** The declared form Regions. */
		regions : {
			dataTableGenreRegion : '.datatable-genre',
		},
		
		/** The form events you'd like to listen */
		events : {
			'click .btnSearchGenre' : 'searchGenre',
			'click .btnClearGenre' : 'clearModal',
			'click tr' : 'selectRow',
			'keypress' : 'treatKeypress',
		},

		/** All the important fields must be here. */		
		ui : {
			loadButton : '.button-loading',
    		inputModalName : '.inputModalName',
		
			form : '#formSearchGenre',
			modalScreen : '.modal',
		},
		treatKeypress : function (e){
		    if (util.enterPressed(e)) {
	    		e.preventDefault();
	    		this.searchGenre();
	    	}
		},

		/** First function called, like a constructor. */
		initialize : function(opt) {
			var that = this;

			this.onSelectModel = opt.onSelectModel;
			this.suggestConfig = opt.suggestConfig;

			this.genreCollection = new Genre.PageCollection();
			this.genreCollection.state.pageSize = 5;
			this.genreCollection.on('fetching', this.startFetch, this);
			this.genreCollection.on('fetched', this.stopFetch, this);
			
			this.dataTableGenre = new JSetup.DataTable({
				row : JSetup.RowClick,
				columns : this.getColumns(),
				collection : this.genreCollection,
			});
			this.setValue(opt.initialValue);
		},
		
		/** Called after DOM´s ready.*/
		onShowView :  function() {
			var that = this;
		  	
	
			that.dataTableGenreRegion.show(this.dataTableGenre);
					
			if (that.suggestConfig) {
				that.suggestConfig.collection = that.genreCollection;
				that.suggestConfig.onSelect = function(json) {
					var model = new JSetup.BaseModel(json)
					that.onSelectModel(model);
					if (json) {
						that.modelSelect = model
					} else
						that.modelSelect = null;
				}
				util.configureSuggest(that.suggestConfig);
				that.suggestConfig.field.change(function(e) {
					if(!that.suggestConfig.field.val()){
						that.clear();
					}
				})
			}		
		},

		searchGenre : function() {
			var that = this;
			this.genreCollection.filterQueryParams = {
	    		name : this.ui.inputModalName.escape(), 
			};

			this.genreCollection.getFirstPage({
				resetState : true,
				success : function(_coll, _resp, _opt) {
					//caso queira algum tratamento de sucesso adicional
				},
				error : function(_coll, _resp, _opt) {
					console.error(_coll, _resp, _opt)
				}
			});
		},

		selectRow : function(e) {
			var modelGenre = util.getWrappedModel(e);
			if (modelGenre){
				this.modelSelect = modelGenre; 
				this.onSelectModel(modelGenre);
			}
		},
		
		getJsonValue : function() {
			if (_.isEmpty(this.modelSelect) && _.isEmpty(this.jsonValue)) {
				return null;
			}
			if (this.modelSelect) {
				return this.modelSelect.toJSON();
			} else {
				return this.jsonValue;
			}
		},
		
		getRawValue : function() {
			var json = this.getJsonValue();
			if(json )
				return json.id
			return null;
		},
		
		getValue : function() {
			return this.modelSelect;
		},

		setValue : function(value) {
			this.jsonValue = value;
		},

		getColumns : function() {
			var columns = [	

		{
				name : "name",
				sortable : true,
				editable : false,
				label 	 : "Name",
				cell : JSetup.CustomStringCell
			}, 
						];
			return columns;
		},

		hidePage : function() {
			this.ui.modalScreen.modal('hide');
		},

		showPage : function() {
			this.clearModal();
			this.ui.modalScreen.modal('show');
			
			this.searchGenre();
		},

		clear : function() {
			this.clearModal();
		},
		clearModal : function() {
			this.modelSelect = null;
			this.jsonValue = null;
			this.genreCollection.reset();
			util.scrollUpModal();
			this.ui.form.get(0).reset();
		},
		
		// Executada depois da consulta concluida.
		stopFetch : function() {
			this.ui.loadButton.button('reset');
			util.stopSpinner();
			util.scrollDownModal();
		},
		
		// Executada Antes da realização da consulta.
		startFetch : function() {
			this.ui.loadButton.button('loading');
			util.showSpinner('spinGenre');
		},
	});

	return GenreModal;
});
