package com.dga.contactbook.contacts.repository;

import com.dga.contactbook.contacts.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactsRepository extends JpaRepository<ContactEntity, Integer> {

    Page<ContactEntity> findContactEntitiesByContactOwnerEmail (String contactOwnerEmail, Pageable pageable);

    ContactEntity findContactEntitiesByContactOwnerEmailAndId (String contactOwnerEmail, String Id);

    List<ContactEntity> findContactEntitiesByPhoneNumberAndContactOwnerEmail (String phoneNumber, String contactOwnerEmail);
}
