package com.contactlist.contactlist.controllers;

import com.contactlist.contactlist.models.Contact;
import com.contactlist.contactlist.models.dtos.ContactCreateDto;
import com.contactlist.contactlist.models.dtos.ContactDto;
import com.contactlist.contactlist.models.dtos.ContactUpdateDto;
import com.contactlist.contactlist.services.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity createContact(@RequestBody @Valid ContactDto contactDto, UriComponentsBuilder uriBuilder){
    Contact contact = contactService.create(contactDto);
    var uri = uriBuilder.path("/contact/{id}").buildAndExpand(contact.getId()).toUri();

    return ResponseEntity.created(uri).body(new ContactCreateDto(contact));
    }

    @GetMapping("/{id}")
    public ResponseEntity getContact(@PathVariable("id") Long id){
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> getAllContacts(@PageableDefault( sort = "name") Pageable page){
        return ResponseEntity.ok(contactService.getAllContacts(page));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateContact(@PathVariable("id") Long id, @RequestBody ContactUpdateDto contactUpdateDto){
        return ResponseEntity.ok().body(contactService.updateContact(id, contactUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@PathVariable("id") Long id){
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
