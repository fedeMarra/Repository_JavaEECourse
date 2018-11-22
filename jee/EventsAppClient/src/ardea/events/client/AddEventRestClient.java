package ardea.events.client;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ardea.events.Event;

import javax.ws.rs.client.Invocation.Builder;


public class AddEventRestClient {

	public static void main(String[] args) {
		run();

	}
	
	private static void run() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/EventsFrontend/rest/cart/events");
		Builder request = target.request();
		Event event = new Event("Concert", "50", "2018-12-24@21:00");
		//String json = "{\"name\": \"Concert3\":, \"price\": \"70\", \"eventDate\": \"2018-12-24T21:00\"}";
		Entity<Event> entity = Entity.entity(event, MediaType.APPLICATION_JSON);
		//Entity entity = Entity.entity(json, MediaType.APPLICATION_JSON);
		Response response = request.post(entity);
		System.out.println(response.getStatus());
		event = response.readEntity(Event.class);
		event.setEventDate(new Date());
		WebTarget updateEvent = client.target("http://localhost:8080/EventsFrontend/rest/cart/event/" + event.getId());
		Builder updateRequest = updateEvent.request();
		Response updateResponse = updateRequest.put(Entity.entity(event, MediaType.APPLICATION_JSON));
		System.out.println(updateResponse.getStatus());
		System.out.println(event);
	}

	public static void runFormRequest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/EventsFrontend/rest/cart/events");
		Builder request = target.request();
		
		Form f = new Form();    
		f.param("name", "Concert");
		f.param("price", "30");
		f.param("date", "2018-12-20@21:15");
		Entity<Form> e = Entity.form(f);
		
		Response response = request.post(e);
		System.out.println(response.getStatus());
		//System.out.println(response.readEntity(String.class));
	}

}
