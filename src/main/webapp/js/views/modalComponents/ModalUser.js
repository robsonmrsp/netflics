/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
define(function(require) {
	var util = require('utilities/utils');
	var JSetup = require('views/components/JSetup');
	var User = require('models/User');
	
	var ModalUserTemplate = require('text!views/modalComponents/tpl/ModalUserTemplate.html');


	var UserModal = JSetup.View.extend({
		template : _.template(ModalUserTemplate),

		/** The declared form Regions. */
		regions : {
			dataTableUserRegion : '.datatable-user',
		},
		
		/** The form events you'd like to listen */
		events : {
			'click .btnSearchUser' : 'searchUser',
			'click .btnClearUser' : 'clearModal',
			'click tr' : 'selectRow',
			'keypress' : 'treatKeypress',
		},

		/** All the important fields must be here. */		
		ui : {
			loadButton : '.button-loading',
    		inputModalName : '.inputModalName',
    		inputModalUsername : '.inputModalUsername',
    		inputModalEmail : '.inputModalEmail',
    		inputModalPassword : '.inputModalPassword',
    		inputModalEnable : '.inputModalEnable',
		
			form : '#formSearchUser',
			modalScreen : '.modal',
		},
		treatKeypress : function (e){
		    if (util.enterPressed(e)) {
	    		e.preventDefault();
	    		this.searchUser();
	    	}
		},

		/** First function called, like a constructor. */
		initialize : function(opt) {
			var that = this;

			this.onSelectModel = opt.onSelectModel;
			this.suggestConfig = opt.suggestConfig;

			this.userCollection = new User.PageCollection();
			this.userCollection.state.pageSize = 5;
			this.userCollection.on('fetching', this.startFetch, this);
			this.userCollection.on('fetched', this.stopFetch, this);
			
			this.dataTableUser = new JSetup.DataTable({
				row : JSetup.RowClick,
				columns : this.getColumns(),
				collection : this.userCollection,
			});
			this.setValue(opt.initialValue);
		},
		
		/** Called after DOM´s ready.*/
		onShowView :  function() {
			var that = this;
		  	
		  	
		  	
	
			that.dataTableUserRegion.show(this.dataTableUser);
					
			if (that.suggestConfig) {
				that.suggestConfig.collection = that.userCollection;
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

		searchUser : function() {
			var that = this;
			this.userCollection.filterQueryParams = {
	    		name : this.ui.inputModalName.escape(), 
	    		username : this.ui.inputModalUsername.escape(), 
	    		email : this.ui.inputModalEmail.escape(), 
			};

			this.userCollection.getFirstPage({
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
			var modelUser = util.getWrappedModel(e);
			if (modelUser){
				this.modelSelect = modelUser; 
				this.onSelectModel(modelUser);
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
				label 	 : "Nome",
				cell : JSetup.CustomStringCell
			}, 
					{
				name : "username",
				sortable : true,
				editable : false,
				label 	 : "Username",
				cell : JSetup.CustomStringCell
			}, 
					{
				name : "email",
				sortable : true,
				editable : false,
				label 	 : "E-mail",
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
			
			this.searchUser();
		},

		clear : function() {
			this.clearModal();
		},
		clearModal : function() {
			this.modelSelect = null;
			this.jsonValue = null;
			this.userCollection.reset();
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
			util.showSpinner('spinUser');
		},
	});

	return UserModal;
});
