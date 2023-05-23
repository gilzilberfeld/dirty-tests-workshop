package workshops.testingil.dirtytests.solutions.e05.duplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.app.ResultWrapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorAPITests {


    private WebClient client;
    private ResultWrapper result;

    @BeforeEach
    public void setup() {
        client = WebClient.create(Consts.URL);
    }


    @Test
    public void on_start_show_0() {

        post_press("0");
        result = get_display();
        assertEquals("0", result.display);
    }

    @Test
    @DisplayName("When pressing 1+ displays 1")
    public void pressing_1_plus_displays_1() {
        post_reset();
        post_press("1");
        post_press(encode("+"));

        result = get_display();
        assertEquals("1", result.display);
    }

    @Test
    @DisplayName("When pressing +2 displays 2")
    public void pressing_plus_2_displays_2() {
        post_reset();
        post_press(encode("+"));
        post_press("2");

        result = get_display();
        assertEquals("2", result.display);
    }

    @Test
    @DisplayName("0 => 0")
    public void pressing_0_displays_0() {
        post_reset();
        post_press("0");
        post_press("0");

        result = get_display();
        assertEquals("0", result.display);
    }

    private static String encode(String op) {
        return URLEncoder.encode(op, StandardCharsets.UTF_8);
    }

    private void post_reset() {
        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(Consts.RESET_PATH)
                        .build())
                .retrieve().bodyToMono(Void.class).block();
    }

    private ResultWrapper get_display() {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(Consts.DISPLAY_PATH)
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();
    }

    private void post_press(String key) {
        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(Consts.PRESS_PATH)
                        .queryParam("key", key)
                        .build())
                .retrieve().bodyToMono(Void.class).block();
    }
}
