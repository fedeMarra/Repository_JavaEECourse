package ardea.events.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ardea.events.Event;

/**
 * Session Bean implementation class EventSearchBean
 */
@Stateless
@LocalBean
public class EventSearchBean {
	
	@EJB
	private EventsBean eventsBean;

    /**
     * Default constructor. 
     */
    public EventSearchBean() {
        
    }
    
    public List<Event> searchByName(String word){
    	return eventsBean.search(word);
    }

}
