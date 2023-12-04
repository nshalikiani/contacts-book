package com.dga.contactbook.contacts.service;

import com.dga.contactbook.contacts.ContactsDto;
import com.dga.contactbook.contacts.ContactEntity;
import com.dga.contactbook.contacts.repository.ContactsRepository;
import com.dga.contactbook.contacts.request.*;
import com.dga.contactbook.contacts.response.AddNewContactResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactsService {

    private final ContactsRepository repository;

    public AddNewContactResponse addNewContact(AddNewContactRequest request) {

        ContactEntity contact = ContactEntity.builder()
                .contactAddress(request.getContactAddress())
                .contactEmail(request.getContactEmail())
                .contactName(request.getContactName())
                .contactOwnerEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        repository.save(contact);

        return new AddNewContactResponse(contact.getId(), "Contact Added Successfully");
    }


    public Page<ContactsDto> getContactsByUser(ContactsByCustomerRequest request) {

        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        String contactsOwner = SecurityContextHolder.getContext().getAuthentication().getName();

        Page<ContactEntity> contactsEntities = repository.findContactEntitiesByContactOwnerEmail(contactsOwner, pageable);

        List<ContactsDto> contactsDtoList = mapContactsEntitiesToDto(contactsEntities.getContent());
        return new PageImpl<>(contactsDtoList, pageable, contactsDtoList.size());

    }

    public List<ContactsDto> getContactByPhoneNumber(GetContactByPhoneNumberRequest request) {

        String contactsOwner = SecurityContextHolder.getContext().getAuthentication().getName();

        List<ContactEntity> contactEntity =
                repository.findContactEntitiesByPhoneNumberAndContactOwnerEmail(request.getPhoneNumber(), contactsOwner);

        return mapContactsEntitiesToDto(contactEntity);
    }

    public String updateContact (UpdateContactRequest request) {

        String contactsOwner = SecurityContextHolder.getContext().getAuthentication().getName();

        ContactEntity contact = repository.findContactEntitiesByContactOwnerEmailAndId(contactsOwner, request.getId());

        contact.setContactAddress(request.getContactAddress());
        contact.setContactName(request.getContactName());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setContactEmail(request.getContactEmail());

        repository.save(contact);

        return "Contact Updated Successfully";
    }

    public String deleteContract (DeleteContactRequest request){

        String contactsOwner = SecurityContextHolder.getContext().getAuthentication().getName();

        ContactEntity contact = repository.findContactEntitiesByContactOwnerEmailAndId(contactsOwner, request.getId());

        repository.delete(contact);

        return "Contact deleted Successfully";
    }

    private List<ContactsDto> mapContactsEntitiesToDto(List<ContactEntity> contactsEntities) {

        List<ContactsDto> contactsDtoList = new ArrayList<>();

        ContactsDto contactsDto = new ContactsDto();

        for (ContactEntity contactEntity : contactsEntities) {

            contactsDto.setContactAddress(contactEntity.getContactAddress());
            contactsDto.setContactName(contactEntity.getContactName());
            contactsDto.setContactEmail(contactEntity.getContactEmail());
            contactsDto.setPhoneNumber(contactEntity.getPhoneNumber());
            contactsDto.setId(contactEntity.getId());

            contactsDtoList.add(contactsDto);
        }

        return contactsDtoList;
    }

    private ContactsDto mapContactEntityToDto(ContactEntity contactEntity) {

        ContactsDto contactsDto = new ContactsDto();

        contactsDto.setContactAddress(contactEntity.getContactAddress());
        contactsDto.setContactName(contactEntity.getContactName());
        contactsDto.setContactEmail(contactEntity.getContactEmail());
        contactsDto.setPhoneNumber(contactEntity.getPhoneNumber());
        contactsDto.setId(contactEntity.getId());

        return contactsDto;

    }
}
