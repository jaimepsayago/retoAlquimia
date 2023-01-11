package com.ejercicio.clientaddress.repository;

import com.ejercicio.clientaddress.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
