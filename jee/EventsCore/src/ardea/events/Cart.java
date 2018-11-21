package ardea.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String owner;
	
	@OneToMany(mappedBy ="cart", cascade=CascadeType.ALL)
	private List<CartItem> items = new ArrayList<>();
	
	public Cart() {}
	
	public Cart(String name) {
		this.owner = name;
		
	}

	public void addEvent(Event event) {
		
		Optional<CartItem> possibleItem = items.stream()
				.filter(k -> k.getEvent().equals(event))
				.findFirst();
		
		CartItem item = possibleItem.orElseGet(() -> {
			CartItem newItem = new CartItem(this, event);
			items.add(newItem);
			return newItem;
		});
		item.addOne();
		
	}
	
	public List<CartItem> getListItem(){
		return items;
	}

	@Override
	public String toString() {
		return "Cart [owner=" + owner + ", items=" + items + "]";
	}

}
