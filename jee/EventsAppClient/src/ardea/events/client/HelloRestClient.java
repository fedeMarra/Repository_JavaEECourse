package ardea.events.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation.Builder;


public class HelloRestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/EventsFrontend/rest/cart/hello");
		Builder request = target.request();
		Response response = request.get();
		System.out.println(response.readEntity(String.class));

	}

}
