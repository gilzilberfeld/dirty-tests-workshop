package workshops.testingil.dirtytests.solutions.e07.abstraction.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
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
                .retrieve()
                .onStatus(
                        status -> status.value() != HttpStatus.OK.value(),
                        response -> log_press_error(key))
                .bodyToMono(Void.class).block();
    }

    private static Mono<Throwable> log_press_error(String key) {
        String message = String.format("POST Press q=%s", key);
        return log_messagae(message);
    }

    public String get_display() {
        ResultWrapper result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(DISPLAY_PATH)
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.value() != HttpStatus.OK.value(),
                        response -> log_get_display_error())
                .bodyToMono(ResultWrapper.class).block();
        return result.display;
    }

    public void post_reset() {
        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(RESET_PATH)
                        .build())
                .retrieve()
                .onStatus(
                        status -> status.value() != HttpStatus.OK.value(),
                        response -> log_reset_error()).
                bodyToMono(Void.class).block();
    }

    private static Mono<Throwable> log_messagae(String message) {
        return Mono.error(new IllegalStateException(
                String.format(message + " failed")
        ));
    }

    public String encode(String op) {
        return URLEncoder.encode(op, StandardCharsets.UTF_8);
    }

    private static Mono<Throwable> log_get_display_error() {
        return log_messagae("GET Display Failed");
    }

    private static Mono<Throwable> log_reset_error() {
        return log_messagae("POST Reset Failed");
    }
}
