package com.dga.contactbook.contacts.controller;

import com.dga.contactbook.contacts.ContactsDto;
import com.dga.contactbook.contacts.request.AddNewContactRequest;
import com.dga.contactbook.contacts.request.ContactsByCustomerRequest;
import com.dga.contactbook.contacts.request.GetContactByPhoneNumberRequest;
import com.dga.contactbook.contacts.request.UpdateContactRequest;
import com.dga.contactbook.contacts.response.AddNewContactResponse;
import com.dga.contactbook.contacts.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactsService service;

    @PostMapping("/add")
    public ResponseEntity<AddNewContactResponse> addNewContact(
            @RequestBody AddNewContactRequest request
    ) {
        return ResponseEntity.ok(service.addNewContact(request));
    }

    @PostMapping("/get-by-costumer")
    public ResponseEntity<Page<ContactsDto>> getContacts(
            @RequestBody ContactsByCustomerRequest request
    ) {
        return ResponseEntity.ok(service.getContactsByUser(request));
    }

    @PostMapping("/get-by-phone_number")
    public ResponseEntity<List<ContactsDto>> getContactByPhoneNumber(
            @RequestBody GetContactByPhoneNumberRequest request
    ) {
        return ResponseEntity.ok(service.getContactByPhoneNumber(request));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateContact(
            @RequestBody UpdateContactRequest request
    ) {
        return ResponseEntity.ok(service.updateContact(request));
    }

}
