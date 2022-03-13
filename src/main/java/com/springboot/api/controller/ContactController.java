package com.springboot.api.controller;


import com.springboot.api.model.Contact;
import com.springboot.api.requestClasses.PutRequest;
import com.springboot.api.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping("/user/add")
    public String saveContact(@RequestBody Contact contact) {
        contactService.saveContact(contact);
        return "New Student added";
    }

    @GetMapping("/user/list")
    public List<Contact> getAllContacts(Model model) {
        return contactService.getAllContacts();

    }

    @PutMapping("/user/edit")
    public ResponseEntity<Contact> updateContact(@RequestBody PutRequest putcontact) {
        return new ResponseEntity<Contact>(contactService.updateContact(putcontact), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }

}