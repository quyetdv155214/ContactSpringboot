/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.websystique.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.websystique.springboot.model.Contact;

/**
 *
 * @author Khuong Duy
 */
public class SQLiteManager {

    Connection conn = null;
    private static boolean hasData = false;

    public String getAccountToken(String id, String password) throws SQLException {
        if (conn == null) {
            getConnection();
        }
        String token = "Token Invalid";
        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery("SELECT token FROM Account WHERE id =" + "'" + id + "'" + "AND password=" + "'" + password + "'");
        if (rs.next()) {
            token = rs.getString("token");
        }
        return token;
    }

    public String getIdWithToken(String token) throws SQLException {
        if (conn == null) {
            getConnection();
        }
        // viết theo id cơ, mấy hàm kia n khác mà,với cả t k lấy được caisn connection o đây ra class kí để gọi
        String id = "";
        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery("SELECT id FROM Account WHERE token =" + "'" + token + "'");
        if (rs.next()) {
            id = rs.getString("id");
        }
        return id;
    }

    public List<Contact> getAllContactWithToken(String token) throws SQLException {
        if (conn == null) {
            getConnection();
        }
        String id = getIdWithToken(token);
        List<Contact> list = new ArrayList<>();
        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery("SELECT * FROM Contact WHERE id =" + "'" + id + "'");
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
    }

    public boolean addContactWithToken(String token, Contact contact) {
        if (conn == null) {
            getConnection();
        }
        try {
            String id = getIdWithToken(token);
            if (!id.isEmpty()) {
                PreparedStatement pre2 = conn.prepareStatement("INSERT INTO Contact values(?,?,?,?,?,?)");
                pre2.setString(2, id);
                pre2.setString(3, contact.getFullName());
                pre2.setString(4, contact.getPhoneNumber());
                pre2.setString(5, contact.getEmail());
                pre2.setString(6, contact.getAddress());
                pre2.execute();
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean editContactWithToken(String token, Contact contact) {
        if (conn == null) {
            getConnection();
        }
        try {
            String id = getIdWithToken(token);
            if (id.equals(contact.getId())) {
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
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteWithToken(String token, int contactId) {
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement pre = conn.prepareStatement("DELETE FROM Contact "
                    + "WHERE contactID = ?");
            pre.setInt(1, contactId);
            pre.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // nếu vậy thì làm sao mà tạo đc database?
    public boolean registerAccount(String id, String password) {
        if (conn == null) {
            getConnection();
        }
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT id FROM Account WHERE id =" + "'" + id + "'");
            if (!rs.next()) {
                PreparedStatement pre3 = conn.prepareStatement("INSERT INTO Account values(?,?,?)");
                pre3.setString(1, id);
                pre3.setString(2, password);
                pre3.setString(3, getUUIDGenerate());
                pre3.execute();
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // DAO no  token
    
    
    //
    
    
    private String getUUIDGenerate() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public void getConnection() {
    	
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:SQLiteTest22.db");
            System.out.println(conn.toString());
            initilase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // hàm nay copy giong hệt chỉ là return ra conn thôi, mà k được à, để sang kia g
    // do cai connection n time out roi, n ma n k null, voi lai n k chay lai cai ham khoi tao
    public Connection getNewConnection() {
//    	Connection conn = null;
    	  try {
    		  /// t biet roi
    		  //
              Class.forName("org.sqlite.JDBC");
              conn = DriverManager.getConnection("jdbc:sqlite:SQLite.db");
              System.out.println(conn.toString());
              initilase();
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
          }
    	  return conn;
    }
    //nó phải gọi được hàm này thì mới tạo đc db chứ
    //lại xem
    private void initilase() throws SQLException {
        if (!hasData) {
            hasData = true;
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name ='Contact'");
            if (!res.next()) {
                System.out.println("Building database");
                Statement state2 = conn.createStatement();
                state2.execute("CREATE TABLE Contact(contactID integer PRIMARY KEY,  \n"
                        + "					 id varchar(50),\n"
                        + "                     fullName NVARCHAR(50) NOT NULL,\n"
                        + "                     phoneNumber NVARCHAR(50),\n"
                        + "                    email NVARCHAR(50),\n"
                        + "					address NVARCHAR(50));");
                Statement state3 = conn.createStatement();
                state3.execute("CREATE TABLE Account(\n"
                        + "   id varchar(50) PRIMARY KEY  NOT NULL,\n"
                        + "   password           varchar(50)    NOT NULL,\n"
                        + "   token            varchar(50)\n"
                        + ");");
                System.out.println("Building successful");
                PreparedStatement pre = conn.prepareStatement("INSERT INTO Contact values(?,?,?,?,?,?)");
                pre.setInt(1, 0);
                pre.setString(2, "id");
                pre.setString(3, "full name");
                pre.setString(4, "phone number");
                pre.setString(5, "email");
                pre.setString(6, "address");
                pre.execute();

//                PreparedStatement pre2 = conn.prepareStatement("INSERT INTO Contact values(?,?,?,?,?,?)");
//                pre2.setString(2, "id");
//                pre2.setString(3, "full name2");
//                pre2.setString(4, "phone number2");
//                pre2.setString(5, "email2");
//                pre2.setString(6, "address2");
//                pre2.execute();
//                
//                PreparedStatement pre3 = conn.prepareStatement("INSERT INTO Account values(?,?,?)");
//                pre3.setString(1, "id");
//                pre3.setString(2, "password");
//                pre3.setString(3, "token");
//                pre3.execute();
//                
//                PreparedStatement pre4 = conn.prepareStatement("INSERT INTO Account values(?,?,?)");
//                pre4.setString(1, "id2");
//                pre4.setString(2, "password2");
//                pre4.setString(3, "token2");
//                pre4.execute();
            }

        }
    }
}
