package com.dga.contactbook.contacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsDto {

    private String id;

    private String contactName;

    private String contactEmail;

    private String phoneNumber;

    private String contactAddress;

}
