package com.ag.croud.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ag.croud.db.UserDBUtil;

// POJO, no interface no extends
// The browser requests per default the HTML MIME type.
@Path("/user")
public class User {

	// Update
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@QueryParam("id") String id,
			@QueryParam("data") String JSONData) {
		// Should come from JSONData
		Response response = Response.serverError().build();
		try {
			JSONData = "{ \"firstName\":\"Dave\" , \"lastName\":\"Johnson\" }";
			if (UserDBUtil.updateUser(id, JSONData)) {
				response = Response.ok().build();
			}
			// } catch (NullPointerException nx) {
			// response = Response.status(404).build();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return response;
	}

	// Insert
	@POST
	@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response createUser(@QueryParam("data") String JSONData) {
		// Should come from JSONData
		Response response = Response.serverError().build();
		try {
			JSONData = "{ \"firstName\":\"John\" , \"lastName\":\"Doe\" }";
			if (UserDBUtil.insertUser(JSONData)) {
				response = Response.ok().build();
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return response;
	}

	// Fetch
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getUser(@QueryParam("id") String id) {
		Response response = Response.serverError().build();
		try {
			if (id != null) {
				response = Response.ok(UserDBUtil.fetchUser(id),
						MediaType.APPLICATION_JSON).build();
			} else {
				response = Response.status(400).build();
			}
		} catch (NullPointerException nx) {
			response = Response.status(404).build();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return response;
	}

	// Delete
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteUser(@QueryParam("id") String id) {
		Response response = Response.serverError().build();
		try {
			UserDBUtil.deleteUser(id);
			response = Response.ok().build();
		} catch (NullPointerException nx) {
			response = Response.status(404).build();
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
		return response;
	}
}