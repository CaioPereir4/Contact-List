package com.contactlist.contactlist.models;

import com.contactlist.contactlist.models.dtos.AddresDto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Addres {
    private String state;
    private String city;
    private String street;

    private int houseNumber;

    public Addres(AddresDto addresDto){
        this.state = addresDto.state();;
        this.city = addresDto.city();
        this.street = addresDto.street();
        this.houseNumber = addresDto.houseNumber();
    }
}
