package com.springboot.api.service.impl;

import com.springboot.api.model.Contact;
import com.springboot.api.operationtypes.OperationStatus;
import com.springboot.api.operationtypes.OperationTypes;
import com.springboot.api.repository.ContactRepository;
import com.springboot.api.requestClasses.PutRequest;
import com.springboot.api.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith({MockitoExtension.class})
public class ContactServiceImplTest {

    private final Contact testContact = getTestContact();
    private final PutRequest testRequest = testRequest();
    @InjectMocks
    private ContactServiceImpl userService;
    @Mock
    private ContactRepository userRepository;
    @Mock
    private final ContactServiceImpl deleteServiceTest = mock(ContactServiceImpl.class);

    private PutRequest testRequest() {
        return PutRequest.builder().id(1l)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .number(12312123)
                .build();
    }

    private Contact getTestContact() {
        return Contact.builder()
                .id(1)
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .number(12312123)
                .build();
    }

    @Test
    void saveContact() {
        ResponseDto responseDto = ResponseDto.builder()
                .operation_status(OperationStatus.SUCCESS)
                .operation_type(OperationTypes.OPERATION_ADD)
                .build();
        when(userRepository.save(any())).thenReturn(getTestContact());
        Contact contact = userService.saveContact(testContact);
        Assert.assertThat(contact, is(not(equalTo(null))));
        Assert.assertThat(contact, is(equalTo(testContact)));
        Assert.assertThat(contact.getNumber(), is(equalTo(12312123L)));

    }

    @Test
    void getAllContacts() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(testContact));
        Assert.assertThat(userService.getAllContacts(), is(not(equalTo(null))));
    }

    @Test
    void getContactById() {
    }

//    @Test(expected=IllegalArgumentException.class)
//    void updateContactEmpty() {
//        when(userRepository.findById(any())).thenReturn(Optional.empty());
////        Assert.assertThat(userService.updateContact(testRequest), is(equalTo(null)));
////        Assert.assertThat(exceptionThatWasThrown.getMessage(), equalTo("Message I expected to be thrown"));
//
////        Exception exception=Assert.assertThrows(ResourceNotFoundException.class,()->{
////            userRepository.findById(1L);
////        });
//        Exception exception= new ResourceNotFoundException("Contact", "Id", testRequest.getId());
//        Assert.assertThat(userService.updateContact(testRequest), is(equalTo(exception.getMessage())));


    //    }
    @Test
    void updateContactSuccess() {
        when(userRepository.findById(any())).thenReturn(Optional.of(testContact));
        Assert.assertThat(userService.updateContact(testRequest), is(not(equalTo(null))));
    }

//    @Test
//    public void deleteUser() {
//        when(userRepository.save(testContact));
//        verify(deleteServiceTest).deleteContact(1L);
//    }
}