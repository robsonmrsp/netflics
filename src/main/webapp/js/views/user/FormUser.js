/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
define(function(require) {
	var _ = require('adapters/underscore-adapter');
	var $ = require('adapters/jquery-adapter');
	var util = require('utilities/utils');
	var JSetup = require('views/components/JSetup');

	var TemplateFormUsers = require('text!views/user/tpl/FormUserTemplate.html');
	var MultiselectModalRoles = require('views/modalComponents/MultiselectModalRole');
	
	var User = require('models/User');
	var Role = require('models/Role');			
	
	var FormUsers = JSetup.View.extend({
		template : _.template(TemplateFormUsers),

		/** The declared form Regions. */
		regions : {
			uploadImageRegion : '.upload-image-container',		
			multiselectModalRolesRegion : '.multiselect-roles-container',
		},

		/** The form events you'd like to listen */
		events : {
			'click 	.save' : 'save',
			'click 	.save-continue' : 'saveAndContinue',
			'change  #inputName' : 'changeName',	
			'change  #inputUsername' : 'changeUsername',	
			'change  #inputEmail' : 'changeEmail',	
		},
		/** All the important fields must be here. */
		ui : {
			inputId : '#inputId',
			saveButton : '.button-saving',
			inputName : '#inputName',
			inputUsername : '#inputUsername',
			inputEmail : '#inputEmail',
			inputPassword : '#inputPassword',
			inputEnable : '#inputEnable',
			inputImage : '#inputImage',
			form : '#formUser',
		},
		
		/** First function called, like a constructor. */
		initialize : function(options) {
			var that = this;
			//here the code 
		},
		
		/** Called after DOM´s ready.*/
		onShowView : function() {
			var that = this;
		    this.ui.inputEnable.bootstrapSwitch();

			this.multiselectModalRoles = new MultiselectModalRoles({
				initialValues : that.model.get('roles'),
			});
			this.uploadViewImage = new JSetup.InputUpload({
				bindElement : that.ui.inputImage,
				onSuccess : function(resp, options) {
					console.info('Upload da image concluido...[ ' + resp + ' ]')
				},
				onError : function(resp, options) {
					console.error('Problemas ao uppar foto1')
				}
			});
		
			this.multiselectModalRolesRegion.show(this.multiselectModalRoles);		
			this.uploadImageRegion.show(this.uploadViewImage);		
		},
		
		saveAndContinue : function() {
			this.save(true)
		},

		save : function(continua) {
			var that = this;
			var user = that.getModel();

			if (this.isValid()) {
			this.ui.saveButton.button('loading');
				user.save({}, {
					success : function(_model, _resp, _options) {
						util.showSuccessMessage('Usuário salvo com sucesso!');
						that.clearForm();

						if (continua != true) {
							util.goPage('app/users', true);
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
			var user = that.model; 
			user.set({
				id: this.ui.inputId.escape(),
		    	name : this.ui.inputName.escape(), 
		    	username : this.ui.inputUsername.escape(), 
		    	email : this.ui.inputEmail.escape(), 
		    	password : this.ui.inputPassword.escape(), 
		    	enable : this.ui.inputEnable.escape(), 
		    	image : this.ui.inputImage.escape(), 
				
				roles : that.multiselectModalRoles.getJsonValue(),
			});
			return user;
		},
		
		customClearForm : function(){
			this.ui.saveButton.button('reset');
			this.uploadViewImage.clear();		
		    this.multiselectModalRoles.clear(); 
		},
		changeName : function() {
			var that = this;
			util.validateUnique({
				element : that.ui.inputName,
				fieldName : 'name',
				fieldDisplayName : 'Nome',
				//onlyNumber : true,     
				view : that,
				collection : User.PageCollection,
			})
		},				
		changeUsername : function() {
			var that = this;
			util.validateUnique({
				element : that.ui.inputUsername,
				fieldName : 'username',
				fieldDisplayName : 'Username',
				//onlyNumber : true,     
				view : that,
				collection : User.PageCollection,
			})
		},				
		changeEmail : function() {
			var that = this;
			util.validateUnique({
				element : that.ui.inputEmail,
				fieldName : 'email',
				fieldDisplayName : 'E-mail',
				//onlyNumber : true,     
				view : that,
				collection : User.PageCollection,
			})
		},				
	});

	return FormUsers;
});