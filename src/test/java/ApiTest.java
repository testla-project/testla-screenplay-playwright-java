import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testla.api.Response;
import testla.api.ResponseBodyFormat;
import testla.api.abilities.UseAPI;
import testla.api.actions.Get;
import testla.api.actions.Post;
import testla.api.actions.Put;
import testla.screenplay.actor.Actor;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    // shared between all tests in this class.
    static APIRequestContext request;
    static Actor actor;

    @BeforeAll
    static void setup() {
        request = Playwright.create().request().newContext();
        actor = Actor.named("TestActor").can(UseAPI.using(request));
    }

    @Test
    void GetTest() {
        Response response = (Response) actor.attemptsTo(
                Get.from("http://zippopotam.us/us/90210").withResponseBodyFormat(ResponseBodyFormat.TEXT)
        );
        assert response.status == 200;
        assert response.body != null;
    }

    @Test
    void PostTest() {
        Response response = (Response) actor.attemptsTo(
                Post.to("https://ptsv2.com/t/ibcu7-1639386619/post")
                        .withData("TEST!")
                        .withResponseBodyFormat(ResponseBodyFormat.TEXT)
        );
        assert response.status == 200;
        assert response.body.equals("Thank you for this dump. I hope you have a lovely day!");
    }

    @Test
    void PutTest() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("title", "foo");
        data.put("body", "bar");
        data.put("userId", 1);

        // do not modify this, exact spaces and newlines are required like this or else the equality check fails
        String expectedData = "{\n"
                + "  \"id\": 1,\n"
                + "  \"title\": \"foo\",\n"
                + "  \"body\": \"bar\",\n"
                + "  \"userId\": 1\n"
                + "}";

        Response response = (Response) actor.attemptsTo(
                Put.to("https://jsonplaceholder.typicode.com/posts/1")
                        .withData(data)
        );
        assert response.status == 200;
        assert response.body.equals(expectedData);

        String expectedHeaderBody = "{\n"
                + "  \"id\": 1\n"
                + "}";

        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "text/plain");
        Response responseWithHeaders = (Response) actor.attemptsTo(
                Put.to("https://jsonplaceholder.typicode.com/posts/1")
                        .withData(data)
                        .withHeaders(headers)
        );
        assert responseWithHeaders.status == 200;
        assert responseWithHeaders.body.equals(expectedHeaderBody);
    }
}
