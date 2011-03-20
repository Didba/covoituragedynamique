package com.covoiturage.client;

import com.covoiturage.client.view.MenuView;
import com.covoiturage.client.view.AddUserView;
import com.covoiturage.client.view.EditProfilView;
import com.covoiturage.client.view.LoginView;
import com.covoiturage.client.view.MapView;
import com.covoiturage.client.view.ValidatePassengersView;
import com.covoiturage.shared.CovoiturageRequestFactory;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;


public interface ClientFactory {
	EventBus getEventBus();
	PlaceController getPlaceController();
	LoginView getLoginView();
	MapView getMapView();
	AddUserView getAddUserView();
	ValidatePassengersView getValidatePassengersView();
	CovoiturageRequestFactory getRequestFactory();
	EditProfilView getEditProfilView();
	MenuView getMenuView();
}