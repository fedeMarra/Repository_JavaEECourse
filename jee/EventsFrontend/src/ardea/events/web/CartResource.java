package ardea.events.web;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ardea.events.Event;
import ardea.events.service.EventsBean;
import ardea.events.service.EventsService;

@Path("/cart")
public class CartResource {
	
	@EJB
	private EventsBean eventsBean;
	
	@EJB
	private EventsService eventService;
	
	
	@POST
	@Path("/events")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addEvent(@FormParam("name") String name, @FormParam("price") String price, @FormParam("date") String date) {
		Logger.getLogger("ardea.events.service").info("calling addEvent");
		eventsBean.addEvent(new Event(name, price, date)); //locale
		//eventService.addEvent(name, price, date); //Remote
	}
		
	@POST
	@Path("/events")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Event addEvent(Event event) {
		eventsBean.addEvent(event);
		return event;
	}
	
	@PUT
	@Path("/event/{eventId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Event updateEventAsJson(@PathParam("eventId") Long id, Event event) {
		Event existingEvent = eventsBean.searchId(id);
		if(existingEvent != null) {
			return eventsBean.updateEvent(event);
		}
		throw new RuntimeException(); 
	}

}
