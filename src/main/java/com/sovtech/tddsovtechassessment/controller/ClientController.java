package com.sovtech.tddsovtechassessment.controller;

import com.sovtech.tddsovtechassessment.model.Client;
import com.sovtech.tddsovtechassessment.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/create")
    public Client create(@Validated @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/update/{id}")
    public Client update(@PathVariable Integer id, @RequestBody Client client) {
        Client found = clientRepository.getReferenceById(id);
        BeanUtils.copyProperties(client, found, "id");
        return clientRepository.save(found);
    }

    @GetMapping("/search/firstName/{firstName}")
    public List<Client> searchByFirstName(@PathVariable String firstName) {
        return clientRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @GetMapping("/search/lastName/{lastName}")
    public List<Client> searchByLastName(@PathVariable String lastName) {
        return clientRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @GetMapping("/search/idNumber/{idNumber}")
    public Client searchByIdNumber(@PathVariable String idNumber) {
        return clientRepository.findByIdNumber(idNumber);
    }

    @GetMapping("/search/mobileNumber/{mobileNumber}")
    public Client searchByMobileNumber(@PathVariable String mobileNumber) {
        return clientRepository.findByMobileNumber(mobileNumber);
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
