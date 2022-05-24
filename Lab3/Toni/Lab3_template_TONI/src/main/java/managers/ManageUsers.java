package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;
import utils.DB;
import utils.MD5;

public class ManageUsers {
	
	private DB db = null ;
	private boolean sqlException = false;
	private boolean mailExists = false;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Wrong initialization");
		}
	}
	
	public boolean getSqlException() {
		return this.sqlException;
	}
	
	public void activateSqlException() {
		this.sqlException = true;
	}
	
	public void deactivateSqlException() {
		this.sqlException = false;
	}

	public boolean getMailExists() {
		return this.mailExists;
	}
	
	public void activateMailExists() {
		this.mailExists = true;
	}
	
	public void deactivateMailExists() {
		this.mailExists = false;
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	// Add new user
	public void addUser(String username, String mail, String password, String gender, String salt, String hashtag) {
		String query = "INSERT INTO users (username,mail,password,gender,salt,hashtag) VALUES (?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, mail);
			statement.setString(3, password);
			statement.setString(4, gender);
			statement.setString(5, salt);
			statement.setString(6, hashtag);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			activateSqlException();
		}
	}
	
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
	    return(hasValue(user.getUser()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd1()) &&
	           hasValue(user.getPwd2()) ) &&
	    	   hasValue(user.getGender());
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	 
	
	// PRE: you enter an email
	// POST: you get true if the email exists or there is an exception, false if it doesn't exist
	public boolean mailExists(String mail){
		
		String s1="SELECT mail FROM users WHERE mail = ";
		s1+="'";
		s1+=mail;
		s1+="'";
		
		try {
		Statement stmt = db.connection.createStatement();
		ResultSet rs = stmt.executeQuery(s1);
		String retrieved="";
		while (rs.next()) {
			  retrieved = rs.getString("mail");
			}
		return retrieved.equals(mail) ? true : false;
		}
		
		catch (SQLException s){
			System.out.println("SQL exception");
			return true;
		}
	}
	
	//PRE: you enter an email and a password
	//POST: you get if the password is correct or not (if exception, false is returned)
	public boolean isCorrectPwd(String mail, String pwd) {
		try {
			
			//Getting the hash of the password of the user
			
			String s1="SELECT password FROM users WHERE mail = ";
			s1+="'";
			s1+=mail;
			s1+="'";
			
			Statement stmt = db.connection.createStatement();
			ResultSet rs = stmt.executeQuery(s1);
			String retrieved="";
			while (rs.next()) {
				  retrieved = rs.getString("password");
				}
			//Comparing it to the introduced pwd
			return retrieved.equals(MD5.getMd5(pwd)) ? true : false;
			}
			
			catch (SQLException s){
				System.out.println("SQL exception");
				return false;
			}
	}

}
