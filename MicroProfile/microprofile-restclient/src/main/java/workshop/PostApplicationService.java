package workshop;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.RestClientDefinitionException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class PostApplicationService {

    @Inject
    @RestClient
    private JsonPlaceholderServiceClient client;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object obj) {
        //restClientBuilderExample();
        getSinglePost();
        //getAllPost();
        //createPost();
        //updatePost();
        //deletePost();
    }

    private void restClientBuilderExample() {
        System.out.println("PostApplicationService - restClientBuilderExample() method called");
        JsonPlaceholderServiceClient client = null;
        try {
            client = RestClientBuilder.newBuilder()
                    .baseUri(new URI("https://jsonplaceholder.typicode.com"))
                    .register(ResponseLoggingFilter.class)
                    .connectTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.SECONDS)
                    .build(JsonPlaceholderServiceClient.class);
        } catch (IllegalStateException | URISyntaxException | RestClientDefinitionException e) {
            e.printStackTrace();
        }
        client.getPostById("1").thenAccept(System.out::println);
    }

    private void getAllPost() {
        System.out.println("PostApplicationService - getAllPost() method called");
        client.getAllPost("ASC").stream()
                .limit(5)
                .forEach(System.out::println);
    }

    private void getSinglePost() {
        System.out.println("PostApplicationService - getSinglePost() method called");
        client.getPostById("1").thenAccept(System.out::println);
    }

    private void createPost() {
        System.out.println("PostApplicationService - createPost() method called");
        JsonObject post = Json.createObjectBuilder()
                .add("id", 101)
                .add("title", "Jakarta EE 8")
                .add("body", "Welcome to Jakarta EE 8")
                .add("userId", 1)
                .build();

        Response response = client.createPost(post);
        System.out.println(response.readEntity(JsonObject.class));
    }

    private void updatePost() {
        System.out.println("PostApplicationService - updatePost() method called");

        JsonObject updatePost = Json.createObjectBuilder()
                .add("id", 1)
                .add("title", "Eclipse MicroProfile")
                .add("body", "Eclipse MicroProfile built on Jakarta EE and its easy to learn")
                .add("userId", 1)
                .build();

        Response response = client.updatePostById("1", updatePost, UUID.randomUUID().toString());
        System.out.println(response.readEntity(JsonObject.class));
    }

    private void deletePost() {
        System.out.println("PostApplicationService - deletePost() method called");
        Response result = client.deleteByPostId("42");
        System.out.println(result.readEntity(JsonObject.class));
    }
}
