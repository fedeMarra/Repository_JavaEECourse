package ardea.events.service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ardea.events.Event;
import ardea.events.EventsRegistry;


/**
 * Session Bean implementation class EventsBean
 */
@Singleton
@LocalBean
public class EventsBean implements EventsRegistry{
	
	@PersistenceContext(name="EventsCore")
	private EntityManager em;
	
	private Map<String,Event> events;

    /**
     * Default constructor. 
     */
    public EventsBean(){
    	events = new HashMap<>();
    }
    
    public void addEvent(Event event){
    	Logger.getLogger("ardea.events.service").info("Calling addEvent" + event.toString());
    	//event.registerOn(this);
    	em.persist(event);
    }
    
    @Override
    public void register(String name, Event event) {
    	//events.put(name,  event);
    }
    
    public List<Event> search(String word){
    	TypedQuery<Event> query = em.createNamedQuery("Event.findByName", Event.class)
    			.setParameter("eventName", "%"+word.toUpperCase()+"%");
    	
    	return query.getResultList();
    	
    	
    	/*List<String> matchingKeys = events.keySet().stream()
    			.filter(whenContains(word))
    			.collect(Collectors.toList());
    	
    	return matchingKeys.stream()
    			.map(fromKeyToEvent())
    			.collect(Collectors.toList());*/
    }

	private Function<? super String, ? extends Event> fromKeyToEvent() {
		return k-> events.get(k);
	}

	private Predicate<? super String> whenContains(String word) {
		return k->k.contains(word);
	}

}
