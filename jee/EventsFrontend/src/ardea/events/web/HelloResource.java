package ardea.events.web;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/demo")
public class HelloResource {
	
	@GET
	@Path("/hello")
	public String hello(@QueryParam("name") @DefaultValue("everybody!") String val) {
		return "Hello JAX-RS "+ val +"!";
	}
	
	@GET
	@Path("/hello/{name}")
	public String HelloWithParam(@PathParam("name") String val) {
		return "Hello "+ val +"!";
	}
	
	@GET
	@Path("/hello1")
	public String HelloWithMatrix(@MatrixParam("name") String val) { //URL -> hello1;name=Fede
		return "Hello (matrix)"+ val +"!";
	}

}
