package com.websystique.springboot.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.websystique.dao.SQLiteManager;
import com.websystique.springboot.model.Account;
import com.websystique.springboot.model.Contact;

@Repository
public class AccountDAO {
	private Connection conn;

	public Account login(Account account) {
		if (conn == null) {
            conn = new SQLiteManager().getNewConnection();
        }
        try {
        	Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM Account WHERE id =" + "'" + account.getId() + "'" + "AND password=" + "'" + account.getPassword() + "'");
            Account a;
            if (rs.next()) {
            	String id  = rs.getString("id");
            	String password  = rs.getString("password");
            	String token  = rs.getString("token");
                a = new Account(id, password, token);
                return a;
            }
            return null;
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
	
    private String getUUIDGenerate() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

	public Account register(Account account) {
		if (conn == null) {
            conn = new SQLiteManager().getNewConnection();
        }
        try {
        	Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT id FROM Account WHERE id =" + "'" + account.getId() + "'");
            if (!rs.next()) {
                PreparedStatement pre3 = conn.prepareStatement("INSERT INTO Account values(?,?,?)");
                pre3.setString(1, account.getId());
                pre3.setString(2, account.getPassword());
                pre3.setString(3, getUUIDGenerate());
                pre3.execute();
                return account;
            }
            return null;
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

	public void logout(String id) {
		
		
	}

}
