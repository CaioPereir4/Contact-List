package com.contactlist.contactlist.models.dtos;

import com.contactlist.contactlist.models.Contact;

public record ContactCreateDto(Long id, String name , String created) {
    public ContactCreateDto(Contact contact){
        this(contact.getId(), contact.getName(), "contact created successfully" );
    }
}
