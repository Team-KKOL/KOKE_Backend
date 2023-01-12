package com.koke.koke_backend.address.service;

import com.koke.koke_backend.address.repository.AddressBookRepository;
import com.koke.koke_backend.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressBookRepository addressBookRepository;

}
