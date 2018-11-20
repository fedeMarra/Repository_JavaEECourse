package ardea.events.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ardea.events.Event;

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

}
