/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
define(function(require) {
	var _ = require('adapters/underscore-adapter');
	var $ = require('adapters/jquery-adapter');
	var util = require('utilities/utils');
	var JSetup = require('views/components/JSetup');

	var TemplateFormItems = require('text!views/item/tpl/FormItemTemplate.html');
	
	var Item = require('models/Item');
	
	var FormItems = JSetup.View.extend({
		template : _.template(TemplateFormItems),

		/** The declared form Regions. */
		regions : {
		},

		/** The form events you'd like to listen */
		events : {
			'click 	.save' : 'save',
			'click 	.save-continue' : 'saveAndContinue',
			'change  #inputName' : 'changeName',	
			'change  #inputIdentifier' : 'changeIdentifier',	
		},
		/** All the important fields must be here. */
		ui : {
			inputId : '#inputId',
			saveButton : '.button-saving',
			inputName : '#inputName',
			inputItemType : '#inputItemType',
			inputIdentifier : '#inputIdentifier',
			inputDescription : '#inputDescription',
			form : '#formItem',
		},
		
		/** First function called, like a constructor. */
		initialize : function(options) {
			var that = this;
			//here the code 
		},
		
		/** Called after DOM´s ready.*/
		onShowView : function() {
			var that = this;

		
		},
		
		saveAndContinue : function() {
			this.save(true)
		},

		save : function(continua) {
			var that = this;
			var item = that.getModel();

			if (this.isValid()) {
			this.ui.saveButton.button('loading');
				item.save({}, {
					success : function(_model, _resp, _options) {
						util.showSuccessMessage('Item salvo com sucesso!');
						that.clearForm();

						if (continua != true) {
							util.goPage('app/items', true);
						}
					},

					error : function(_model, _resp, _options) {
						that.ui.saveButton.button('reset');
						util.showErrorMessage('Problema ao salvar registro',_resp);
					}
				});
			} 
		},
		
		getModel : function() {
			var that = this;
			var item = that.model; 
			item.set({
				id: this.ui.inputId.escape(),
		    	name : this.ui.inputName.escape(), 
		    	itemType : this.ui.inputItemType.escape(), 
		    	identifier : this.ui.inputIdentifier.escape(), 
		    	description : this.ui.inputDescription.escape(), 
				
			});
			return item;
		},
		
		customClearForm : function(){
			this.ui.saveButton.button('reset');
		},
		changeName : function() {
			var that = this;
			util.validateUnique({
				element : that.ui.inputName,
				fieldName : 'name',
				fieldDisplayName : 'Nome',
				//onlyNumber : true,     
				view : that,
				collection : Item.PageCollection,
			})
		},				
		changeIdentifier : function() {
			var that = this;
			util.validateUnique({
				element : that.ui.inputIdentifier,
				fieldName : 'identifier',
				fieldDisplayName : 'Identificador',
				//onlyNumber : true,     
				view : that,
				collection : Item.PageCollection,
			})
		},				
	});

	return FormItems;
});