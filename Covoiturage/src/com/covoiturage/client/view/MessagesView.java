package com.covoiturage.client.view;


import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;



public interface MessagesView extends IsWidget{
    void setPresenter(Presenter presenter);
	public interface Presenter {
	    void goTo(Place place);
	}

}