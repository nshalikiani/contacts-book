package com.dga.contactbook.contacts.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactRequest {

    @NotNull
    private String Id;

    private String contactName;

    private String contactEmail;

    private String phoneNumber;

    private String contactAddress;

}
