/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
define(function(require) {
	var util = require('utilities/utils');
	var JSetup = require('views/components/JSetup');
	var Actor = require('models/Actor');
	
	var ModalActorTemplate = require('text!views/modalComponents/tpl/ModalActorTemplate.html');


	var ActorModal = JSetup.View.extend({
		template : _.template(ModalActorTemplate),

		/** The declared form Regions. */
		regions : {
			dataTableActorRegion : '.datatable-actor',
		},
		
		/** The form events you'd like to listen */
		events : {
			'click .btnSearchActor' : 'searchActor',
			'click .btnClearActor' : 'clearModal',
			'click tr' : 'selectRow',
			'keypress' : 'treatKeypress',
		},

		/** All the important fields must be here. */		
		ui : {
			loadButton : '.button-loading',
    		inputModalBirthDate : '.inputModalBirthDate',
			groupInputModalBirthDate : '.groupInputModalBirthDate',
    		inputModalName : '.inputModalName',
		
			form : '#formSearchActor',
			modalScreen : '.modal',
		},
		treatKeypress : function (e){
		    if (util.enterPressed(e)) {
	    		e.preventDefault();
	    		this.searchActor();
	    	}
		},

		/** First function called, like a constructor. */
		initialize : function(opt) {
			var that = this;

			this.onSelectModel = opt.onSelectModel;
			this.suggestConfig = opt.suggestConfig;

			this.actorCollection = new Actor.PageCollection();
			this.actorCollection.state.pageSize = 5;
			this.actorCollection.on('fetching', this.startFetch, this);
			this.actorCollection.on('fetched', this.stopFetch, this);
			
			this.dataTableActor = new JSetup.DataTable({
				row : JSetup.RowClick,
				columns : this.getColumns(),
				collection : this.actorCollection,
			});
			this.setValue(opt.initialValue);
		},
		
		/** Called after DOM´s ready.*/
		onShowView :  function() {
			var that = this;
			this.ui.inputModalBirthDate.date();
			this.ui.groupInputModalBirthDate.date();
		  	
		  	
	
			that.dataTableActorRegion.show(this.dataTableActor);
					
			if (that.suggestConfig) {
				that.suggestConfig.collection = that.actorCollection;
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

		searchActor : function() {
			var that = this;
			this.actorCollection.filterQueryParams = {
	    		birthDate : this.ui.inputModalBirthDate.escape(), 
	    		name : this.ui.inputModalName.escape(), 
			};

			this.actorCollection.getFirstPage({
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
			var modelActor = util.getWrappedModel(e);
			if (modelActor){
				this.modelSelect = modelActor; 
				this.onSelectModel(modelActor);
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
				name : "birthDate",
				sortable : true,
				editable : false,
				label 	 : "Birth date",
				cell : JSetup.CustomStringCell
			}, 
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
			
			this.searchActor();
		},

		clear : function() {
			this.clearModal();
		},
		clearModal : function() {
			this.modelSelect = null;
			this.jsonValue = null;
			this.actorCollection.reset();
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
			util.showSpinner('spinActor');
		},
	});

	return ActorModal;
});
