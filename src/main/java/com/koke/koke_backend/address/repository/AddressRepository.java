package com.koke.koke_backend.address.repository;

import com.koke.koke_backend.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>, QAddressRepository {
}
