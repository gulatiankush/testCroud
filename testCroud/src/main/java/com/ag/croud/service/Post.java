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

import com.ag.croud.db.PostDBUtil;

// POJO, no interface no extends
// The browser requests per default the HTML MIME type.
@Path("/post")
public class Post {

	// Update
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePost(@QueryParam("id") String id,
			@QueryParam("data") String JSONData) {
		// Should come from JSONData
		Response response = Response.serverError().build();
		try {
			JSONData = "{ \"firstName\":\"Dave\" , \"lastName\":\"Johnson\" }";
			if (PostDBUtil.updatePost(id, JSONData)) {
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
	public Response createPost(@QueryParam("data") String JSONData) {
		// Should come from JSONData
		Response response = Response.serverError().build();
		try {
			JSONData = "{ \"firstName\":\"John\" , \"lastName\":\"Doe\" }";
			if (PostDBUtil.insertPost(JSONData)) {
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
	public Response getPost(@QueryParam("id") String id) {
		Response response = Response.serverError().build();
		try {
			if (id != null) {
				response = Response.ok(PostDBUtil.fetchPost(id),
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
	public Response deletePost(@QueryParam("id") String id) {
		Response response = Response.serverError().build();
		try {
			PostDBUtil.deletePost(id);
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