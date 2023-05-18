package workshops.testingil.dirtytests.verification;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalVerification {

    @Test
    @Disabled
    public void server_working(){
        WebClient client = WebClient.create("http://localhost:8080");
        String result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/")
                        .build())
                .retrieve().bodyToMono(String.class).block();

        assertEquals(result, "Working hard");

    }
}
