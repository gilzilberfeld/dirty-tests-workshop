package workshops.testingil.dirtytests.verification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import workshops.testingil.dirtytests.newone.CalculatorController;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VerificationTests {

    @Autowired
    CalculatorController calc;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(calc).build();
    }
    @Test
    public void context_loaded(){
        assertNotNull(mockMvc);
        assertNotNull(calc);
    }

    @Test
    public void Server_answers() throws Exception {
        mockMvc.perform(get("/calc/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Working hard"));
    }

    @Test
    @DisplayName("1->1")
    public void press_and_display() throws Exception {
        mockMvc.perform(
                post("/calc/press")
                        .param("key", "1"))
                .andExpect(status().isOk());

        mockMvc.perform(
                        get("/calc/display"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"display\":\"1\"}"));


    }


        @Configuration
    static class TestConfig{

        @Bean
        public CalculatorController calculatorController(){
            return new CalculatorController();
        }
    }
}
