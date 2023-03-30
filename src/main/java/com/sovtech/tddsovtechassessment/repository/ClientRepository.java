package com.sovtech.tddsovtechassessment.repository;

import com.sovtech.tddsovtechassessment.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByFirstNameContainingIgnoreCase(String firstName);
    List<Client> findByLastNameContainingIgnoreCase(String lastName);
    Client findByIdNumber(String idNumber);
    Client findByMobileNumber(String mobileNumber);
}
