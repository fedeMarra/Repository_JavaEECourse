package ardea.events.service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ardea.events.Event;
import ardea.events.EventsRegistry;
import ardea.events.Team;

/**
 * Session Bean implementation class EventsBean
 */
@Singleton
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class EventsBean implements EventsRegistry{
	
	@PersistenceContext(name="EventsCore")
	private EntityManager em;
	
	@Inject
	private JMSContext context;
	
	@Resource(lookup="java:jboss/exported/jms/eventsQueue")
	private Queue queue;
	
	@Resource
	private UserTransaction tx;
	
	private Map<String,Event> events;

    /**
     * Default constructor. 
     */
    public EventsBean(){
    	events = new HashMap<>();
    }
    
    public void addEvent(Event event){
    	try {
			tx.begin();
			Logger.getLogger("ardea.events.service").info("Calling addEvent" + event.toString());
	    	//event.registerOn(this);
	    	em.persist(event);
	    	JMSProducer producer =context.createProducer();
	    	producer.send(queue, event);
	    	tx.commit();
		}catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			try {
				tx.rollback();
			}catch(IllegalStateException | SecurityException | SystemException e1){
				e1.printStackTrace();
			}
		}
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

	public Team findTeam(String name) {
		
		return em.createQuery("select t from Team t where t.name = :name", Team.class)
				.setParameter("name", name)
				.getSingleResult();
	}

	public Event searchId(Long eventId) {
		return em.find(Event.class, eventId);
	}

	public Event updateEvent(Event event) {
		return em.merge(event);
	}

}
