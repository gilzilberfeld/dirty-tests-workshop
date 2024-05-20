package workshops.testingil.dirtytests.demos.d10.builder;

import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.demos.Address;

import static org.assertj.core.api.Assertions.assertThat;

public class ClujAddressTests {

    @Test
    public void we_are_in_romania(){
        Address address = new ClujAddressBuilder().at("Trifoiului", 2).build();
        assertThat(address.country).isEqualTo("Romania");
    }
}
