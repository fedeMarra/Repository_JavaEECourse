package ardea.events.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ardea.events.service.EventsService;

public class EventsAppClient {

	private EventsService service;

	public static void main(String[] args) throws Exception {
		EventsAppClient client = new EventsAppClient();
		client.run();

	}
	
	public EventsAppClient() throws NamingException {
		Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http://localhost:8080/wildfly-services");
		jndiProperties.put("jboss.naming.client.ejb.context", Boolean.TRUE.toString());
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "fedeM");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "1234Marra");
		
		Context context = new InitialContext(jndiProperties);
		
		String name="EventsApp/EventsBackend/EventsServiceBean!ardea.events.service.EventsService";
		service = (EventsService)context.lookup(name);
	}

	private void run() {
		service.addEvent("Concerto3", "30", "2018-10-07@21:00");
		service.addEvent("Gran Prix Atletica3", "35", "2018-10-08@18:00");
		service.addMatch("Juventus", "Milan", "40", "2018-12-22@20:30");
		service.addMatch("Napoli", "Roma", "40", "2019-01-23@20:30");
		
	}

}
