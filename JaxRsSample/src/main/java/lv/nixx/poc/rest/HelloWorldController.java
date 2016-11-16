package lv.nixx.poc.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/")
public class HelloWorldController {
	
	@GET
	@Path("/helloworld")
	public String helloworld() {
		return "Hello world";
	}

	@GET
	@Path("/helloworld/{name}")
    public String getUser(@PathParam("name") String name) {
		return "Hello " + name;
    }
	
	@GET
	@Path("/sanbox_response")
	public Response sandbox() {
		return Response.ok("response_text").build();
	}
	
	@POST
	@Path("/post")
	public Response post(@QueryParam("name") String name, @QueryParam("surname") String surname){
		return Response.ok(name + ":" + surname).build();
	}
	
	@POST
	@Path("/post_with_headers")
	public Response postWithHeaders(@HeaderParam("name") String name, @HeaderParam("surname") String surname){
		return Response.ok(name + ":" + surname).build();
	}
	
	
	
}
