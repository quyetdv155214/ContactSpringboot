package com.websystique.springboot.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.Contact;


@Repository
public class ContactDAO {

	private static List<Contact> listContact = new ArrayList<>();

	static {
		initContact();
	}
	
	// dummy data
	
	private static void initContact() {
		Contact c1 = new Contact("quyet", "duyak", "123456789", "duyak@gamil.com", "");
		Contact c2 = new Contact("quyet1", "duyak1", "123456789", "duyak@gamil.com", "");
		Contact c3 = new Contact("thang", "quyet2", "123456789", "duyak@gamil.com", "");
		Contact c4 = new Contact("thang", "quyet3", "123456789", "duyak@gamil.com", "");
		Contact c5 = new Contact("quyet4", "duyak4", "123456789", "duyak@gamil.com", "");
		Contact c6 = new Contact("quyet4", "duyak5", "123456789", "duyak@gamil.com", "");
		listContact.add(c1);
		listContact.add(c2);
		listContact.add(c3);
		listContact.add(c4);
		listContact.add(c5);
		listContact.add(c6);

	}

	public List<Contact> getContactById(String userID) {
		List<Contact> result = new ArrayList<>();

		for (Contact contact : listContact) {
			if (contact.getId().equalsIgnoreCase(userID)) {
				result.add(contact);
			}

		}
		return result;
	}

	public Contact addContact(Contact contact) {

//		String sql = "INSERT INTO [dbo].[Contact] ([id] ,[fullName] ,[phoneNumber]  ,[email]  ,[address])  VALUES (?,?,?,?,?)";
//		PreparedStatement stm = null;
//		Connection connection = null;
//
//		try {
//			connection = DBContext.getInstance().getConnection();
//			stm = connection.prepareStatement(sql);
//			
//			stm.setString(1, contact.getId());
//			stm.setString(2, contact.getFullName());
//			stm.setString(3, contact.getPhoneNumber());
//			stm.setString(4, contact.getEmail());
//			stm.setString(5, contact.getAddress());
//			
//			stm.executeQuery();
//			return contact;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException ex) {
//				}
//			}
//		}
//
		return null;
	}

	public Contact deleteContact(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> editContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> deleteListContact(String[] id) {
		// TODO Auto-generated method stub
		return null;
	}

}
