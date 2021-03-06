/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
define(function(require) {
	var util = require('utilities/utils');
	var JSetup = require('views/components/JSetup');	
	var PageItemTemplate = require('text!views/item/tpl/PageItemTemplate.html');

	var Item = require('models/Item');

	var PageItem = JSetup.View.extend({
		template : _.template(PageItemTemplate),

		/** The declared form Regions. */
		regions : {
			dataTableItemRegion : '.datatable-item',
		},
		
		/** The form events you'd like to listen */
		events : {
			'click 	.reset-button' : 'resetItem',			
			'keypress' : 'treatKeypress',
			
			'click 	.search-button' : 'searchItem',
			'click  .show-advanced-search-button' : 'toggleAdvancedForm',
		},
		
		/** All the important fields must be here. */		
		ui : {
			loadButton : '.loading-button',
			inputName : '#inputName',
			inputItemType : '#inputItemType',
			inputIdentifier : '#inputIdentifier',
			inputDescription : '#inputDescription',
			form : '#formItemFilter',
			advancedSearchForm : '.advanced-search-form',
		},
		
		/** First function called, like a constructor. */		
		initialize : function() {
			var that = this;
			this.items = new Item.PageCollection();
			
			this.dataTableItem = new JSetup.DataTable({
				columns : this.getColumns(),
				collection : this.items,
				onFetching : this.startFetch,
				onFetched : this.stopFetch,
				view : this
			});			
		},

		/** Called after DOM´s ready.*/		
		onShowView : function() {
			var that = this;
		
			this.dataTableItemRegion.show(this.dataTableItem);
		
			this.dataTableItem.recoveryLastQuery();
		},
		
		 searchItem : function(){		                                                                   
		 	var that = this;                                                                                                   
		 	this.dataTableItem.getFirstPage({                                                                             
		 		success : function(_coll, _resp, _opt) {                                                                       
		 			//console.info('Consulta para o grid item');                                         
		 		},                                                                                                             
		 		error : function(_coll, _resp, _opt) {                                                                         
		 			//console.error(_resp.responseText || (_resp.getResponseHeader && _resp.getResponseHeader('exception')));  
		 		},                                                                                                             
		 		filterQueryParams : {                                                                                          
		     		name : this.ui.inputName.escape(),                                   
		     		itemType : this.ui.inputItemType.escape(),                                   
		     		identifier : this.ui.inputIdentifier.escape(),                                   
		     		description : this.ui.inputDescription.escape(),                                   
		 		}                                                                                                              
		 	})                                                                                                                 
		 },																													   
		
		resetItem : function(){
			this.ui.form.get(0).reset();
			this.items.reset();
		},
				
		getColumns : function() {
			var that = this;
			var columns = [
			{
				name : "name",
				sortable : true,
				editable : false,
				label 	 : "Nome",
				cell : JSetup.CustomStringCell
			}, 
			{
				name : "itemType",
				sortable : true,
				editable : false,
				label 	 : "Tipo",
				cell : JSetup.CustomStringCell
			}, 
			{
				name : "identifier",
				sortable : true,
				editable : false,
				label 	 : "Identificador",
				cell : JSetup.CustomStringCell
			}, 
			{
				name : "description",
				sortable : true,
				editable : false,
				label 	 : "Descrição",
				cell : JSetup.CustomStringCell
			}, 
			{
				name : "acoes",
				label : "Ações(Editar, Deletar)",
				editable : false,
				sortable : false,
				cell : JSetup.ActionCell.extend({
					buttons : that.getCellButtons(),
					context : that,
				})
			} ];
			return columns;
		},
		
		getCellButtons : function() {
			var that = this;
			var buttons = [];

			buttons.push({
				id : 'edit_button',
				type : 'primary',
				icon : 'fa-pencil',
				customClass : 'auth[edit-item,disable]',
				hint : 'Editar Item',
				onClick : that.editModel,
			}, {
				id : 'delete_button',
				type : 'danger',
				customClass : 'auth[delete-item, disable]',
				icon : 'fa-trash',
				hint : 'Remover Item',
				onClick : that.deleteModel,
			});

			return buttons;
		},

		deleteModel : function(model) {
			var that = this;
			
			var modelTipo = new Item.Model({id : model.id});
			
			util.confirm({
				title : "Importante",
				text : "Tem certeza que deseja remover o registro [ " + model.get('id') + " ] ?",
				onConfirm : function() {
					modelTipo.destroy({
						success : function() {
							that.items.remove(model);
							util.alert({title : "Concluido", text : "Registro removido com sucesso!"});
						},
					});
				}
			});
		},

		editModel : function(model) {
			util.goPage("app/editItem/" + model.get('id'));
		},

		
		// additional functions
		toggleAdvancedForm : function() {
			this.ui.advancedSearchForm.slideToggle("slow");
		},

		treatKeypress : function(e) {
			if (util.enterPressed(e)) {
				e.preventDefault();
				this.searchItem();
			}
		},
		startFetch : function() {
			util.loadButton(this.ui.loadButton)
		},

		stopFetch : function() {
			util.resetButton(this.ui.loadButton)
		}
	});

	return PageItem;
});
