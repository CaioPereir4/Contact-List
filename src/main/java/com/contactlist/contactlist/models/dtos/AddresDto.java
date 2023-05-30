package com.contactlist.contactlist.models.dtos;

import com.contactlist.contactlist.models.Addres;
import jakarta.validation.constraints.NotBlank;

public record AddresDto(@NotBlank String state,
                        @NotBlank String city,
                        @NotBlank String street,
                        int houseNumber) {
    public AddresDto(Addres addres){
        this(addres.getState(), addres.getCity(), addres.getStreet(), addres.getHouseNumber());
    }
}
