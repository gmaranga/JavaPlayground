package com.restexample.jaxrs.prediction;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class PredictionsRS {

	@Context
	private ServletContext sctx;// dependency injection
	private static PredictionsList plist;// set in populate()

	public PredictionsRS() {
	}

	@GET
	@Path("/xml")
	@Produces({ MediaType.APPLICATION_XML })
	public Response getXml() {
		checkContext();
		return Response.ok(plist, "application/xml").build();
	}

	@GET
	@Path("/xml/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_XML })
	public Response getXml(@PathParam("id") int id) {
		checkContext();
		return toRequestedType(id, "application/xml");
	}

	@GET
	@Path("/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getJson() {
		checkContext();
		return Response.ok(toJson(plist), "application/json").build();
	}

	@GET
	@Path("/json/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getJson(@PathParam("id") int id) {
		checkContext();
		return toRequestedType(id, "application/json");
	}

	@GET
	@Path("/plain")
	@Produces({ MediaType.TEXT_PLAIN })
	public String getPlain() {
		checkContext();
		return plist.toString();
	}

	@POST
	@Path("/create")
	@Produces({MediaType.TEXT_PLAIN})
	public Response create(@FormParam("who") String who, @FormParam("what") String what){
		checkContext();
		String msg = null;
		//Required both properties to create
		if(who == null || what == null){
			msg = "Property 'who' or 'what' is missing.\n";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		//Otherwise, create the Prediction and add it to the collection
		int id = addPrediction(who, what);
		msg = "Prediction " + id +
				" created: (who = " + who + " what = " + what + ").\n";
		return Response.ok(msg, "text/plain").build();
	}
	

	@PUT
	@Path("/update")
	@Produces({MediaType.TEXT_PLAIN})
	public Response update(@FormParam("id") int id, @FormParam("who") String who, @FormParam("what") String what){
		checkContext();
		//Check that sufficient data is present to do an edit
		String msg = null;
		if(who == null && what == null)
			msg = "Neither who nor what is given: nothing to edit. \n";
		Prediction p  = plist.find(id);
		if(p == null)
			msg = "There is no prediction with ID " + id +"\n";
		
		if(msg != null)
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		
		//Update
		if(who != null) p.setWho(who);
		if(what != null) p.setWhat(what);
		msg = "Prediction " + id + " has been updated.\n";
		return Response.ok(msg, "text/plain").build();
	}
	
	@DELETE
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/delete/{id: \\d+}")
	public Response delete(@PathParam("id") int id){
		checkContext();
		String msg = null;
		Prediction p = plist.find(id);
		if(p == null){
			msg = "There is no prediction with ID " + id + ". Cannot delete.\n";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}
		
		plist.getPredictions().remove(p);
		msg = "Prediction " + id + " deleted.\n";
		return Response.ok(msg, "text/plain").build();
	}
	
	private int addPrediction(String who, String what) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Response toRequestedType(int id, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object toJson(PredictionsList plist2) {
		// TODO Auto-generated method stub
		return null;
	}

	private void checkContext() {
		if(plist == null) populate();
	}

	private void populate() {
		// TODO Auto-generated method stub
		
	}

}
