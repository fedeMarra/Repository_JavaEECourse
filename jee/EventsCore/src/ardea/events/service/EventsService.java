package ardea.events.service;

import javax.ejb.Remote;

@Remote
public interface EventsService {
	
	void addEvent(String name, String price, String date);

}
