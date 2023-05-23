package workshops.testingil.dirtytests.solutions.e02.names;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.app.ResultWrapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorAPITests {
    @Test
    public void on_start_show_0() {
        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "0")
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("0", result.display);
    }

    @Test
    @DisplayName("When pressing 1+ displays 1")
    public void pressing_1_plus_displays_1() {

        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "1")
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", URLEncoder.encode("+", StandardCharsets.UTF_8))
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("1", result.display);

    }

    @Test
    @DisplayName("When pressing +2 displays 2")
    public void pressing_plus_2_displays_2() {

        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", URLEncoder.encode("+", StandardCharsets.UTF_8))
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "2")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("2", result.display);

    }

    @Test
    @DisplayName("0 => 0")
    public void pressing_0_displays_0() {

        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "0")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "0")
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("0", result.display);

    }
}
