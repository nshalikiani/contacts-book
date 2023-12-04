package com.dga.contactbook.contacts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts", schema = "public")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String contactOwnerEmail;

    private String contactName;

    private String contactEmail;

    private String phoneNumber;

    private String contactAddress;

}
