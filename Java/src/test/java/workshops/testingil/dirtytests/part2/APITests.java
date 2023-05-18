package workshops.testingil.dirtytests.part2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.newone.ResultWrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class APITests {

    WebClient client;

    @ParameterizedTest
    @CsvFileSource(resources = "/sequence_api_data.csv")
    public void test_multiple_sequences(String sequence, String expected) throws Exception {
        client = WebClient.create("http://localhost:8080");
        client.post()
                .uri(uriBuilder2 -> uriBuilder2
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        SequenceMessageHelper  message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence(sequence);
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);
        client.post()
                .uri(uriBuilder1 -> uriBuilder1
                        .path("/calc/sequence")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve().bodyToMono(Void.class).block();


        ResultWrapper result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals(expected, result.display);

    }

}
