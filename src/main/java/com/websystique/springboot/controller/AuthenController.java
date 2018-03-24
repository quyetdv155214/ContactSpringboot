package com.websystique.springboot.controller;

import javax.ws.rs.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.model.Account;
import com.websystique.springboot.service.AccountDAO;

@RestController
@Component
public class AuthenController {
	
	@Autowired
	AccountDAO accountDAO;
	 
	@RequestMapping(value = "/account/login", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
	public Account login(@RequestBody Account account) {
		return accountDAO.login(account);
		
	}
	
	@RequestMapping(value = "/account/register", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
	public Account register(@RequestBody Account account) {
		return accountDAO.register(account);
	}
	
	@RequestMapping(value = "/account/logout/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
	public void logout(@PathVariable("id") String id) {
		accountDAO.logout(id);
	}
	
	
}
