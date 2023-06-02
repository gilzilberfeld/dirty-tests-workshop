package workshops.testingil.dirtytests.demos.d04.fixtures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class fixture_tests {

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
        assertEquals(3, inc.getResult());
    }

    @Test
    public void increment_twice(){
        inc.addTwo();
        inc.addTwo();
        assertEquals(5, inc.getResult());
    }
}

