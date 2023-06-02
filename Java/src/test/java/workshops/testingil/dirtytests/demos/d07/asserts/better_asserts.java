package workshops.testingil.dirtytests.demos.d07.asserts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.demos.Incrementer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class better_asserts {

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
        inc.addTwo();
        inc.addTwo();
        incrementer_should_be(5);
    }

    private void incrementer_should_be(int expected) {
        assertThat(inc.getResult()).isEqualTo(expected);
    }
}

