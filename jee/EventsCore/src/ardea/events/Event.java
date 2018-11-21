package ardea.events;

import java.io.Serializable;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ardea.events.EventsRegistry;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DTYPE", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="Event")
@Table(name="event")
@Access(AccessType.FIELD)
@NamedQuery(name="Event.findByName", query="SELECT e FROM Event e WHERE UPPER(e.name) LIKE :eventName")
public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private int price;
	//private LocalDate localDate;
	
	@Transient
	private LocalDateTime localDate;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name="eventDate")
	//private Date date;
	
	public Event() {

	}
	
	public Event(String name, String price, String date) {
		this.name = name;
		this.price = Integer.parseInt(price);
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//this.date = LocalDate.parse(date, formatter);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm");
		this.localDate = LocalDateTime.parse(date, formatter);
		//this.date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="eventDate")
	@Access(AccessType.PROPERTY)
	public Date getEventDate() {
		return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
	}

	public Long getId() {
		return id;
	}

	public void setEventDate(Date date) {
		localDate= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	@Override
	public String toString() {
		//LocalDateTime date = LocalDateTime.ofInstant(this.date.toInstant(), ZoneId.systemDefault());
		return String.format("Event [name=%s, price=%s, date=%s]",  name, price, localDate);
	}
	
	public void registerOn(EventsRegistry registry){
		registry.register(name, this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localDate == null) ? 0 : localDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localDate == null) {
			if (other.localDate != null)
				return false;
		} else if (!localDate.equals(other.localDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	
	

}
