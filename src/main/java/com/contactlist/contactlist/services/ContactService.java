package com.contactlist.contactlist.services;

import com.contactlist.contactlist.models.Addres;
import com.contactlist.contactlist.models.Contact;
import com.contactlist.contactlist.models.dtos.ContactDto;
import com.contactlist.contactlist.models.dtos.ContactUpdateDto;
import com.contactlist.contactlist.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;
    public Contact create(ContactDto contactDto){
        var contact = new Contact(contactDto);
        repository.save(contact);
        return contact;
    }
    public ContactDto getContact(Long id){
       var contact = repository.getReferenceById(id);
       return new ContactDto(contact);
    }

    public Page<ContactDto> getAllContacts(Pageable page){

        return repository.findAll(page).map(ContactDto::new);

    }

    public ContactDto updateContact(Long id , ContactUpdateDto contactUpdateDto){
        var contact = repository.getReferenceById(id);
        if(contactUpdateDto.name() != null){
            contact.setName(contactUpdateDto.name());
        }
        if(contactUpdateDto.number() != null){
            contact.setNumber(contactUpdateDto.number());
        }
        if(contactUpdateDto.email() != null){
            contact.setEmail(contactUpdateDto.email());
        }
        if(contactUpdateDto.addres() != null){
            var addres = new Addres(contactUpdateDto.addres());
            contact.setAddres(addres);
        }
        repository.save(contact);
        return new ContactDto(contact);
    }

    public void delete(Long id){
        repository.deleteById(id);

    }

}
