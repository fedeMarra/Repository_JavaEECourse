package ardea.events;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cartitem")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="CartId")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="EventId")
	private Event event;
	
	private int quantity;
	
	public CartItem() {}

	public CartItem(Cart cart, Event event) {
		this.cart= cart;
		this.event = event;
	}

	public Event getEvent() {
		return event;
	}

	public void addOne() {
		quantity++;
		
	}

	@Override
	public String toString() {
		return "CartItem [event=" + event + ", quantity=" + quantity + "]";
	}
}
