package com.dga.contactbook.contacts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddNewContactResponse {

    private String contactId;

    private String status;

}
