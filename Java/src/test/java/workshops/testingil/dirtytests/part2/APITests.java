package workshops.testingil.dirtytests.part2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.app.ResultWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        SequenceMessageHelper message = new SequenceMessageHelper();
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

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data_2.csv")
    public void test_more_sequences(String sequence, String expected) throws Exception {
        client = WebClient.create("http://localhost:8080");
        client.post()
                .uri(uriBuilder2 -> uriBuilder2
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        SequenceMessageHelper message = new SequenceMessageHelper();
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

    @Test
    public void test_result() throws Exception {
        client = WebClient.create("http://localhost:8080");
        client.post()
                .uri(uriBuilder2 -> uriBuilder2
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        SequenceMessageHelper message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence("5+2C");
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


        String result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(String.class).block();

        String json="";
        try {
            String dir = System.getProperty("user.dir");

            System.out.println(dir);
            json = Files.readString(Paths.get(".\\src\\test\\resources\\reference_result.json"), StandardCharsets.UTF_8);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        assertEquals(json, result);

    }
}
