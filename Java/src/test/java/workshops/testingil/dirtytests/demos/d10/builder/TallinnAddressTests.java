package workshops.testingil.dirtytests.demos.d10.builder;

import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.demos.Address;

import static org.assertj.core.api.Assertions.assertThat;

public class TallinnAddressTests {

    @Test
    public void we_are_in_estonia(){
        Address address = new TallinnAddressBuilder().at("Põhja pst", 27).build();
        assertThat(address.country).isEqualTo("Estonia");
    }
}
