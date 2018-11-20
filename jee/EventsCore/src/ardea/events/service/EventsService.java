package ardea.events.service;

import javax.ejb.Remote;

@Remote
public interface EventsService {
	
	void addEvent(String name, String price, String date);
	void addMatch(String team1, String team2, String price, String date);

}
