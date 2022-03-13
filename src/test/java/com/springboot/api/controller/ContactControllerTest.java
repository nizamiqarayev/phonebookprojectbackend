package com.springboot.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.api.model.Contact;
import com.springboot.api.model.ContactDto;
import com.springboot.api.model.ContactRequestData;
import com.springboot.api.operationtypes.OperationStatus;
import com.springboot.api.operationtypes.OperationTypes;
import com.springboot.api.repository.ContactRepository;
import com.springboot.api.requestClasses.PutRequest;
import com.springboot.api.response.ResponseDto;
import com.springboot.api.service.ContactService;
import com.springboot.api.service.impl.ContactServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.plaf.IconUIResource;
import java.util.Collections;
import java.util.UUID;

import static java.util.UUID.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(
        value = ContactController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE)
)
public class ContactControllerTest {
    @Autowired
    private ContactController userController;
    @MockBean
    private ContactService contactService;
    @MockBean
    private ContactRepository testRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final UUID userId = randomUUID();
    private final ContactRequestData testContactData = getContactRequestData();
    private final ContactDto contactDto = getContactDto();
    private final Contact testContact= getTestContact();

    private Contact getTestContact(){
        return Contact.builder()
                .id(1)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .number(12312123).build();
    }

    private ContactDto getContactDto() {
        return ContactDto.builder()
                .user_id(UUID.randomUUID())
                .name("Name")
                .phone("0123456").build();
    }
    private ContactRequestData getContactRequestData() {
        return ContactRequestData.builder()
                .name("Name")
                .phone("0123456").build();
    }

    @SneakyThrows
    @Test
    public void addUserSuccess() {
        ResponseDto responseDto = ResponseDto.builder()
                .operation_status(OperationStatus.SUCCESS)
                .operation_type(OperationTypes.OPERATION_ADD)
                .build();
        ContactRequestData userRequestData = ContactRequestData.builder()
                .name("Name")
                .phone("1234567")
                .build();

        when(contactService.saveContact(any())).thenReturn(any());
        final String contentAsString = mockMvc.perform(post("/user/add")
                .content(objectMapper.writeValueAsString(userRequestData))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertThat(contentAsString, is(not(equalTo(null))));

    }

    @SneakyThrows
    @Test
    public void editUserSuccess() {
        ResponseDto responseDto = ResponseDto.builder()
                .operation_status(OperationStatus.SUCCESS)
                .operation_type(OperationTypes.OPERATION_EDIT)
                .build();
        ContactRequestData userRequestData = ContactRequestData.builder()
                .name("Name")
                .phone("1234567")
                .build();
        PutRequest testRequest= PutRequest.builder().id(1)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .number(12312123)
                .build();

        when(contactService.updateContact(testRequest)).thenReturn(getTestContact());
        final String contentAsString = mockMvc.perform(
                put("/user/edit")
                        .content(objectMapper.writeValueAsString(testContactData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertThat(contentAsString, is(not(equalTo(null))));
    }

    @SneakyThrows
    @Test
    public void getUserListSuccess() {
        when(contactService.getAllContacts()).thenReturn(Collections.singletonList(testContact));
        final String contentAsString = mockMvc.perform(
                get("/user/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertThat(contentAsString, is(not(equalTo(null))));
    }

    @Test
    public void deleteUser() throws Exception {
        ResponseDto responseDto = ResponseDto.builder()
                .operation_status(OperationStatus.SUCCESS)
                .operation_type(OperationTypes.OPERATION_DELETE)
                .build();
        final String contentAsString = mockMvc.perform(
                delete("/user/delete/1")
                        .content(objectMapper.writeValueAsString(testContactData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertThat(contentAsString, is(not(equalTo(null))));
    }

}