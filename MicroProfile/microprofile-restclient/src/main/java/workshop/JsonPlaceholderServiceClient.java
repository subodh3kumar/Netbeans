package workshop;

import java.util.Base64;
import java.util.concurrent.CompletionStage;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RegisterClientHeaders(GlobalClientHeaders.class)
@RegisterProvider(ResponseLoggingFilter.class)
@ClientHeaderParam(name = "X-Application-Name", value = "mp-restclient")
public interface JsonPlaceholderServiceClient {
    
    @GET
    @Path("/posts")
    public JsonArray getAllPost(@QueryParam("orderBy") String orderDirection);
    
    @GET
    @Path("/posts/{id}")
    public CompletionStage<JsonObject> getPostById(@PathParam("id") String id);
    
    @GET
    @Path("/posts/{id}/comments")
    public JsonArray getCommentsByPostId(@PathParam("id") String id);
    
    @POST
    @Path("/posts")
    public Response createPost(JsonObject post);
    
    @DELETE
    @Path("/posts/{id}")
    public Response deleteByPostId(@PathParam("id") String id);
    
    @PUT
    @Path("posts/{id}")
    @ClientHeaderParam(name = "Authorization", value = "{generateAuthHeader}")
    public Response updatePostById(@PathParam("id") String id, JsonObject post, @HeaderParam("X-Request-Id") String requestHeaderId);
    
    default String generateAuthHeader() {
        return "Basic " + new String(Base64.getEncoder().encode("duke:SECRET".getBytes()));
    }
}
