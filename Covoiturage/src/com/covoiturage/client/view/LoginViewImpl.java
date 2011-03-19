package com.covoiturage.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class LoginViewImpl extends Composite implements LoginView {
	
	interface MyUiBinder extends UiBinder<FlowPanel, LoginViewImpl> { }
    private static final MyUiBinder binder = GWT.create(MyUiBinder.class);
	
	@UiField Button sendButton ; 
	@UiField TextBox nameField ;
	@UiField PasswordTextBox passField ;
	@UiField Button addUserButton;
	@UiField Image image;
    @SuppressWarnings("unused")
	private Presenter presenter;

	
	public LoginViewImpl(){
		initWidget(binder.createAndBindUi(this)); 
		image.setUrl("http://1.bp.blogspot.com/_q6GXjsy7QfU/SwheN_DnA6I/AAAAAAAACC4/ktKRwE4r3tQ/s1600/D%C3%A9rapage.gif");
	}

	public HasClickHandlers getSendLoginButton() {
		return sendButton;
	}

	public HasClickHandlers getAddUserButton(){
		return addUserButton;
	}

	public  String getPassword() {
		return passField.getText();
	}
	public String getLogin() {
		return nameField.getText();
	}

	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}
	@UiHandler("addUserButton")
	void onAddUserButtonClick(ClickEvent event) {
	}
}