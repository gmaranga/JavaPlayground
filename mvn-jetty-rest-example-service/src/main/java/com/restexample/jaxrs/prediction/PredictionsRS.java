package com.restexample.jaxrs.prediction;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

import org.codehaus.jackson.map.ObjectMapper;

@Path("/predictions")
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
	


	private void populate() {
		plist = new PredictionsList();
		String filename = "/WEB-INF/data/predictions.db";
		InputStream in  = sctx.getResourceAsStream(filename);
		//Read the data into the array of Predictions
		if(in != null){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String record = null;
				while((record = reader.readLine()) != null){
					String[] parts = record.split("!");
					addPrediction(parts[0], parts[1]);
					
				}
			}catch(Exception e){
				throw new RuntimeException("I/O failed", e);
			}
		}
	}

	private int addPrediction(String who, String what) {
		int id = plist.add(who, what);
		return id;
	}
	
	private void checkContext() {
		if(plist == null) populate();
	}

	//PredictionList --> JSON document
	private String toJson(PredictionsList plist2) {

		String json = "If you see this, there's a problem.";
		try{
			json = new ObjectMapper().writeValueAsString(plist);
		}catch(Exception e){e.printStackTrace();}
		return json;
	}
	
	//Prediction --> JSON document
		private String toJson(Prediction pred) {

			String json = "If you see this, there's a problem.";
			try{
				json = new ObjectMapper().writeValueAsString(pred);
			}catch(Exception e){e.printStackTrace();}
			return json;
		}

	//Generate an HTTP error response or type OK response
	private Response toRequestedType(int id, String type) {
		
		Prediction pred = plist.find(id);
		if(pred == null){
			String msg = id + " is a bad ID.\n";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).type(MediaType.TEXT_PLAIN).build();
		}else if(type.contains("json")){
			return Response.ok(toJson(pred), type).build();
		}else{
			return Response.ok(pred, type).build();
		}
	}
	

}
