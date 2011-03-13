package com.covoiturage.shared;

import java.util.Date;
import java.util.List;



import com.covoiturage.server.domain.SimpleTravel;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;






@ProxyFor(SimpleTravel.class)
public interface SimpleTravelProxy extends EntityProxy{

	public Date getDate();

	public void setDate(Date date);

	public List<String> getSteps();



	public String getPassenger();

	public void setPassenger(String passenger);

	
	public void setSteps(List<String> steps);
	
	EntityProxyId<SimpleTravelProxy> stableId();
	
}
