package com.contactlist.contactlist.models.dtos;

import com.contactlist.contactlist.models.Contact;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactDto(Long id,
                        @NotBlank String name,
                        @NotBlank String number,
                        @NotBlank @Email String email,
                         @Valid AddresDto addres) {

    public ContactDto(Contact contact){
        this(contact.getId(), contact.getName(), contact.getNumber(), contact.getEmail(), new AddresDto(contact.getAddres()));
    }

}
