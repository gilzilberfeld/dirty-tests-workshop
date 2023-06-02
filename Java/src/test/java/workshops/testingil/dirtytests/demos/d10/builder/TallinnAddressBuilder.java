package workshops.testingil.dirtytests.demos.d10.builder;

import workshops.testingil.dirtytests.demos.Address;

public class TallinnAddressBuilder{

    private String street="";
    private int number;

    public TallinnAddressBuilder at(String street, int number){
        this.street = street;
        this.number = number;
        return this;
    }

    public Address build(){
        Address address = new Address();
        address.country = "Estonia";
        address.city = "Tallinn";
        address.street = this.street;
        address.number = this.number;
        return address;
    }
}
