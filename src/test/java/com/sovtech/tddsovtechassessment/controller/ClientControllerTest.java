package com.sovtech.tddsovtechassessment.controller;

import com.sovtech.tddsovtechassessment.model.Client;
import com.sovtech.tddsovtechassessment.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static com.sovtech.tddsovtechassessment.util.AppUtils.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    ClientController clientController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void test_Create_Client() throws Exception {
        Client client = Client.builder().id(1).firstName("Ronald").lastName("Mthombeni").mobileNumber("0835264477")
                .idNumber("8109235475085")
                .physicalAddress("30 Cloverdene Road")
                .build();

        //arrange
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Ronald"))
                .andExpect(jsonPath("$.lastName").value("Mthombeni"))
                .andExpect(jsonPath("$.mobileNumber").value("0835264477"))
                .andExpect(jsonPath("$.idNumber").value("8109235475085"))
                .andExpect(jsonPath("$.physicalAddress").value("30 Cloverdene Road"))
        ;
    }

    @Test
    public void test_Update_Client() throws Exception {
        Client existingClient = Client.builder().id(1).firstName("Ronald").lastName("Mthombeni").mobileNumber("0835264477")
                .idNumber("8109235475085")
                .physicalAddress("30 Cloverdene Road")
                .build();

        Client client = Client.builder().firstName("Charles").lastName("Brown").mobileNumber("0799005492")
                .idNumber("9109235475085")
                .physicalAddress("234 Guybrew Street")
                .build();

        //arrange
        when(clientRepository.getReferenceById(1)).thenReturn(existingClient);
        when(clientRepository.save(existingClient)).thenReturn(client);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.put("/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
        ;
    }

    @Test
    public void test_searchByFirstName() throws Exception {
        Client client = Client.builder().id(1).firstName("Ronald").lastName("Mthombeni").mobileNumber("0835264477")
                .idNumber("8109235475085")
                .physicalAddress("30 Cloverdene Road")
                .build();

        //arrange
        when(clientRepository.findByFirstNameContainingIgnoreCase("ronald")).thenReturn(Collections.singletonList(client));
        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/search/firstName/ronald")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").value("Ronald"))
                .andExpect(jsonPath("$[0].lastName").value("Mthombeni"))
                .andExpect(jsonPath("$[0].mobileNumber").value("0835264477"))
                .andExpect(jsonPath("$[0].idNumber").value("8109235475085"))
                .andExpect(jsonPath("$[0].physicalAddress").value("30 Cloverdene Road"))
        ;
    }

    @Test
    public void test_searchByLastName() throws Exception {
        Client client = Client.builder().id(1).firstName("Ronald").lastName("Mthombeni").mobileNumber("0835264477")
                .idNumber("8109235475085")
                .physicalAddress("30 Cloverdene Road")
                .build();

        //arrange
        when(clientRepository.findByLastNameContainingIgnoreCase("Mthombeni")).thenReturn(Collections.singletonList(client));
        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/search/lastName/Mthombeni")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").value("Ronald"))
                .andExpect(jsonPath("$[0].lastName").value("Mthombeni"))
                .andExpect(jsonPath("$[0].mobileNumber").value("0835264477"))
                .andExpect(jsonPath("$[0].idNumber").value("8109235475085"))
                .andExpect(jsonPath("$[0].physicalAddress").value("30 Cloverdene Road"))
        ;
    }

    @Test
    public void test_searchByIdNumber() throws Exception {
        Client client = Client.builder().id(1).firstName("Ronald").lastName("Mthombeni").mobileNumber("0835264477")
                .idNumber("8109235475085")
                .physicalAddress("30 Cloverdene Road")
                .build();

        //arrange
        when(clientRepository.findByIdNumber("8109235475085")).thenReturn(client);
        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/search/idNumber/8109235475085")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Ronald"))
                .andExpect(jsonPath("$.lastName").value("Mthombeni"))
                .andExpect(jsonPath("$.mobileNumber").value("0835264477"))
                .andExpect(jsonPath("$.idNumber").value("8109235475085"))
                .andExpect(jsonPath("$.physicalAddress").value("30 Cloverdene Road"))
        ;
    }

    @Test
    public void test_searchByMobileNumber() throws Exception {
        Client client = Client.builder().id(1).firstName("Ronald").lastName("Mthombeni").mobileNumber("0835264477")
                .idNumber("8109235475085")
                .physicalAddress("30 Cloverdene Road")
                .build();

        //arrange
        when(clientRepository.findByMobileNumber("0835264477")).thenReturn(client);
        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/search/mobileNumber/0835264477")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Ronald"))
                .andExpect(jsonPath("$.lastName").value("Mthombeni"))
                .andExpect(jsonPath("$.mobileNumber").value("0835264477"))
                .andExpect(jsonPath("$.idNumber").value("8109235475085"))
                .andExpect(jsonPath("$.physicalAddress").value("30 Cloverdene Road"))
        ;
    }
}