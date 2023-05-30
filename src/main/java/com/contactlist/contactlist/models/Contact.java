package com.contactlist.contactlist.models;

import com.contactlist.contactlist.models.dtos.ContactDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CONTACT_LIST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String number;

    private String email;

    @Embedded
    private Addres addres;

    public Contact(ContactDto contactDto){
        this.name = contactDto.name();
        this.number = contactDto.number();
        this.email = contactDto.email();
        this.addres = new Addres(contactDto.addres());
    }
}
