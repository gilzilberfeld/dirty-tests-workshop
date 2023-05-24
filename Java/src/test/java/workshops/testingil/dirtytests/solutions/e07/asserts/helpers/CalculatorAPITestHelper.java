package workshops.testingil.dirtytests.solutions.e07.asserts.helpers;

import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.app.ResultWrapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CalculatorAPITestHelper {

    public static final String URL = "http://localhost:8080";
    public static final String PRESS_PATH = "/calc/press";
    public static final String DISPLAY_PATH = "/calc/display";
    public static final String RESET_PATH = "/calc/reset";
    private final WebClient client;

    public CalculatorAPITestHelper(String url) {
        client = WebClient.create(URL);
    }

    public void post_press(String key) {
        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(PRESS_PATH)
                        .queryParam("key", key)
                        .build())
                .retrieve().bodyToMono(Void.class).block();
    }

    public String get_display() {
        ResultWrapper result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(DISPLAY_PATH)
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();
        return result.display;
    }

    public void post_reset() {
        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(RESET_PATH)
                        .build())
                .retrieve().bodyToMono(Void.class).block();
    }

    public String encode(String op) {
        return URLEncoder.encode(op, StandardCharsets.UTF_8);
    }
}
