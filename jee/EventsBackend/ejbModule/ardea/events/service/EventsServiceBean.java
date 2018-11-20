package ardea.events.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ardea.events.Event;
import ardea.events.Match;
import ardea.events.Team;

/**
 * Session Bean implementation class EventsServiceBean
 */
@Stateless
public class EventsServiceBean implements EventsService {
	
	@EJB
	private EventsBean eventsBean;

    /**
     * Default constructor. 
     */
    public EventsServiceBean() {
    }

	@Override
	public void addEvent(String name, String price, String date) {
		eventsBean.addEvent(new Event(name, price, date));
		
	}

	@Override
	public void addMatch(String team1, String team2, String price, String date) {
		Team t1 = eventsBean.findTeam(team1);
		Team t2 = eventsBean.findTeam(team2);
		eventsBean.addEvent(new Match(t1, t2, price, date));
		
	}
	
	

}
