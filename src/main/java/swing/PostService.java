package swing;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostService {

    private final HttpClient client;

    public PostService() {
        this.client = HttpClient.newHttpClient();
    }

    public String fetchPostTitle(int postId) throws Exception {
        // Add delay to simulate slower API call and demonstrate non-blocking UI
        Thread.sleep(3000);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + postId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON using Gson to extract title property
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        return json.get("title").getAsString();
    }
}
