package com.websystique.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.model.Contact;
import com.websystique.springboot.service.ContactDAO;


@RestController
@Component
public class ContactController {

	@Autowired
	private ContactDAO contactDAO;
	
	
	@RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }
	
	// get
	@RequestMapping(value = "/contact/{id}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Contact> getContact(@PathVariable("id") String id) {
        return contactDAO.getContactById(id);
    }
	
	// insert
	@RequestMapping(value = "/contact/newcontact", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Contact addContact(@RequestBody Contact contact) {
        return contactDAO.addContact(contact);
    }
	
	//edit
	@RequestMapping(value = "/contact/edit", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Contact> editContact(@RequestBody Contact contact) {
        return contactDAO.editContact(contact);
    }
	
	//detete
	@RequestMapping(value = "/contact/{contactid}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteContact(@PathVariable("contactid") String contactid) {
        contactDAO.deleteContact(contactid);
    }
	
	// detete list 
	
	@RequestMapping(value = "/contact/batchdelete", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Contact> deleteListContact(@RequestBody String id[]) {
        return contactDAO.deleteListContact(id);
    }
	
}
