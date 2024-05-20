package workshops.testingil.dirtytests.demos.d10.builder;

import workshops.testingil.dirtytests.demos.Address;

public class ClujAddressBuilder {

    private String street="";
    private int number;

    public ClujAddressBuilder at(String street, int number){
        this.street = street;
        this.number = number;
        return this;
    }

    public Address build(){
        Address address = new Address();
        address.country = "Romania";
        address.city = "Cluj-Napoca";
        address.street = this.street;
        address.number = this.number;
        return address;
    }
}
