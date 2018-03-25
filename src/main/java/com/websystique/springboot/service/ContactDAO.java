package com.websystique.springboot.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;
import org.sqlite.SQLiteConnection;

import com.websystique.dao.SQLiteManager;
import com.websystique.springboot.model.Contact;


@Repository
public class ContactDAO {

	private static List<Contact> listContact = new ArrayList<>();
	
	private Connection conn;

//	static {
//		initContact();
//	}
	
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

	}// mở postman tét rhoi

	public List<Contact> getContactById(String userID)  {
		if(conn ==null) {
			conn = new SQLiteManager().getNewConnection();
		}
		List<Contact> list = new ArrayList<>();
		try {
	        Statement state =conn.createStatement();
	        ResultSet rs = state.executeQuery("SELECT * FROM Contact WHERE id =" + "'" + userID + "'");
	        while (rs.next()) {
	            int contactId = rs.getInt("contactID");
	            String cid = rs.getString("id");
	            String cfullName = rs.getString("fullName");
	            String cphoneNumber = rs.getString("phoneNumber");
	            String cemail = rs.getString("email");
	            String caddress = rs.getString("address");
	            Contact contact = new Contact(contactId, cid, cfullName, cphoneNumber, cemail, caddress);
	            list.add(contact);
	        }
	        return list;
		} catch(SQLException ex) {
			Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
			return list;
		}finally {
			if(conn != null ) {
        		try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		}
	}

	public Contact addContact(Contact contact) {
		if (conn == null) {
            conn = new SQLiteManager().getNewConnection();
        }
        try {
                PreparedStatement pre2 = conn.prepareStatement("INSERT INTO Contact values(?,?,?,?,?,?)");
                pre2.setString(2, contact.getId());
                pre2.setString(3, contact.getFullName());
                pre2.setString(4, contact.getPhoneNumber());
                pre2.setString(5, contact.getEmail());
                pre2.setString(6, contact.getAddress());
                pre2.execute();
                return contact;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
        	if(conn != null ) {
        		try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block lai xem
					e.printStackTrace();
				}
        	}
        }
	}

	public Contact deleteContact(int id) {
		if (conn == null) {
            conn = new SQLiteManager().getNewConnection();
        }
        try {
        	 PreparedStatement pre = conn.prepareStatement("DELETE FROM Contact "
                     + "WHERE contactID = ?");
             pre.setInt(1, id);
             pre.execute();
             return new Contact("deleted","deleted","deleted","deleted","deleted");
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
        	if(conn != null ) {
        		try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
	}

	public Contact editContact(Contact contact) {
		if (conn == null) {
            conn = new SQLiteManager().getNewConnection();
        }
        try {
                PreparedStatement pre = conn.prepareStatement("UPDATE Contact SET "
                        + "fullName = ? ,"
                        + "phoneNumber = ?,"
                        + "email = ?,"
                        + "address = ? "
                        + "WHERE contactID = ?");
                pre.setString(1, contact.getFullName());
                pre.setString(2, contact.getPhoneNumber());
                pre.setString(3, contact.getEmail());
                pre.setString(4, contact.getAddress());
                pre.setInt(5, contact.getContactId());
                pre.execute();
                return contact;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
        	if(conn != null ) {
        		try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
	}/// .xml để ra xml nhá, .json thi ra json, mac dinh la json
	// delele post man dau?

	public List<Contact> deleteListContact(String[] id) {
		if (conn == null) {
            conn = new SQLiteManager().getNewConnection();
        }
		List<Contact> list  = new ArrayList<>();
        try {
        	for(int i=0; i<id.length;i++) {
        		PreparedStatement pre = conn.prepareStatement("DELETE FROM Contact "
                        + "WHERE contactID = ?");
                pre.setInt(1, Integer.parseInt(id[i]));
                pre.execute();
                list.add(new Contact(Integer.parseInt(id[i]),"deleted","deleted","deleted","deleted","deleted"));
        	}
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
        	if(conn != null ) {
        		try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
	}

	public List<Contact> addListContact(Contact[] contacts) {
		if (conn == null) {
            conn = new SQLiteManager().getNewConnection();
        }
		List<Contact> list = new ArrayList<>();
        try {
        	
        	for(int i=0;i< contacts.length;i++) {
        		Contact contact = contacts[i];
        		PreparedStatement pre2 = conn.prepareStatement("INSERT INTO Contact values(?,?,?,?,?,?)");
                pre2.setString(2, contact.getId());
                pre2.setString(3, contact.getFullName());
                pre2.setString(4, contact.getPhoneNumber());
                pre2.setString(5, contact.getEmail());
                pre2.setString(6, contact.getAddress());
                pre2.execute();
                
                list.add(contact);
        	}
        	return list;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
        	if(conn != null ) {
        		try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
        	}
        }
	}

}
