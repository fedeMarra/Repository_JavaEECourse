package ardea.events;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	Team(){}
	
	public Team(String name) {
		this.name=name;
	}

	@Override
	public String toString() {
		return String.format("Team [name=%s]", name);
	}	
	
}
