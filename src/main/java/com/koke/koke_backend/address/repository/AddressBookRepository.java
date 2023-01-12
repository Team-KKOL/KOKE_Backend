package com.koke.koke_backend.address.repository;

import com.koke.koke_backend.address.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBook, Integer>, QAddressBookRepository {
}
