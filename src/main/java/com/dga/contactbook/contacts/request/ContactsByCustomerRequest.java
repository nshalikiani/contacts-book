package com.dga.contactbook.contacts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsByCustomerRequest {

    private Integer pageNumber;

    private Integer pageSize;

}
