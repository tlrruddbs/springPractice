package project.book.shop.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipCode;

    protected Address(){}

    public Address(String city, String street, String zipCode){
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

}
