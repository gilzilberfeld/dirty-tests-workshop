package workshops.testingil.dirtytests.demos.d08.logging;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class logging {


    @Test
    public void call_out_and_log(){
        WebClient.create("http://www.google.com")
                .get()
                .retrieve()
                .onStatus(
                        status -> status.value() != HttpStatus.OK.value(),
                        response -> log_error(response.statusCode()))
                .bodyToMono(Void.class).block();
    }

    private Mono<Throwable> log_error(HttpStatus httpStatus) {
        return Mono.error(new IllegalStateException(
                String.format("We're not OK! We're " + httpStatus.toString())
        ));
    }

}

