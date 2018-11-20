package ardea.events;

import java.time.LocalDate;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ardea.events.EventsRegistry;

@Entity
@Table(name="event")
@Access(AccessType.FIELD)
@NamedQuery(name="Event.findByName", query="SELECT e FROM Event e WHERE UPPER(e.name) LIKE :eventName")
public class Event {
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
	
	

}
