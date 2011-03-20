package com.covoiturage.shared;




import com.covoiturage.server.domain.PassengerInfo;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(PassengerInfo.class)
public interface PassengerInfoProxy extends EntityProxy, UserInfoProxy{

	public void setRating(int rating);
	public int getRating();
	public void setCountOfJourneys(int countOfJourneys);
	public int getCountOfJourneys();
	public void setFirstName(String firstName);

	public String getFirstName();

	public void setLastName(String lastName);

	public String getLastName();
	
}