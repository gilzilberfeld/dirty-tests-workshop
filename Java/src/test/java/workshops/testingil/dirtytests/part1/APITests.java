package workshops.testingil.dirtytests.part1;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.app.ResultWrapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class APITests {
    @Test
    public void test_0() {
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
    //@Disabled
    public void test_1_and_plus() {

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
//@Disabled
    public void test_plus_and_2() {

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
    public void test_zero_zero() {

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
