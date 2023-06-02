package workshops.testingil.dirtytests.demos.d08.fluent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.demos.Incrementer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class fluent_style_asserts {

    Incrementer inc;

    @BeforeEach
    public void setup(){
        inc = new Incrementer();
    }
    @AfterEach
    public void cleanup(){
        inc.reset();
    }

    @Test
    public void increment_once(){
        inc.addTwo();
        assertThat(inc.getResult()).isEqualTo(3);
    }

    @Test
    public void increment_twice(){
        increment().again().and_get(5);
    }

    private fluent_style_asserts increment(){
        inc.addTwo();
        return this;
    }

    private fluent_style_asserts again() {
        inc.addTwo();
        return this;
    }

    private void and_get(int i) {
        incrementer_should_be(5);
    }

    private void incrementer_should_be(int expected) {
        assertThat(inc.getResult()).isEqualTo(expected);
    }
}

