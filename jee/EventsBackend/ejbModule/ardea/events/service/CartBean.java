package ardea.events.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import ardea.events.Cart;
import ardea.events.Event;

/**
 * Session Bean implementation class CartBean
 */
@Stateful
@LocalBean
public class CartBean {

	@PersistenceContext(name="EventsCore")
	private EntityManager em;
	private Cart cart;
	
	@EJB
	private EventSearchBean eventSearchBean;
    /**
     * Default constructor. 
     */
    public CartBean() {
       
    }

	public void initialize() {
		cart = new Cart("Michele");
        em.persist(cart);
	}
	
	public Cart getCart() {
		return cart;
	}

	public void addEvent(Long eventId) {
		cart = em.merge(cart);
		Event event = eventSearchBean.searchById(eventId);
		cart.addEvent(event);
		Logger.getLogger("ardea.events.service").info(">>>>> " + cart);
	}

	@Remove
	public void close() {
		cart = null;
		
	}

}
