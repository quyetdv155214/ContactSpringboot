/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websystique.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.websystique.springboot.model.Contact;

/**
 *
 * @author Khuong Duy
 */
public class SQLite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SQLiteManager manager = new SQLiteManager();
        try {

            //   GET ACCOUNT TOKEN
//            String token = manager.getAccountToken("id", "password");
//            System.out.println(token);
            // ADD NEW CONTACT
//            Contact c = new Contact("Au duy", "+84 965 113300", "duyakse04298@fpt.edu.vn", "FPT University");
//            boolean added = manager.addContactWithToken(token, c);
//            System.out.println(added);
            // EDIT CONTACT
//            Contact c = new Contact(0,"id","Au duy ", "+84 965 113300", "duyakse04298@fpt.edu", "FPT University");
//            boolean added = manager.editContactWithToken(token, c);
//            System.out.println(added);
            // REGISTER
//            boolean register = manager.registerAccount("auduy", "123456");
//            System.out.println(register);

            //GET ACCOUNT TOKEN
            String token = manager.getAccountToken("auduy", "123456");
            System.out.println(token);
            
            
            // GET ALL CONTACT WITH TOKEN
            List<Contact> contacts = manager.getAllContactWithToken(token);
            for (Contact contact : contacts) {
                System.out.println(contact.toString());
            }
            //DELETE CONTACT WITH TOKEN
            if (!contacts.isEmpty()) {
                boolean delete = manager.deleteWithToken(token, contacts.get(0).getContactId());
                System.out.println(delete + "delete");
            }
            
            
            // GET ALL CONTACT WITH TOKEN AGAIN
            List<Contact> contacts2 = manager.getAllContactWithToken(token);
            System.out.println("list 2 below:");
            for (Contact contact : contacts2) {
                System.out.println(contact.toString());
            }

        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
