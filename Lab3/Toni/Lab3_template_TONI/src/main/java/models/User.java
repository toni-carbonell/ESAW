package models;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.MD5;

public class User implements java.io.Serializable {
	//On Database "WEB" -> if not existing, create it
	/*
	
	EXECUTE THIS ON MYSQL COMMAND LINE!!!! ;))
	
	------
	SYSTEM mysql -u mysql -p 
	CREATE DATABASE WEB;
	USE WEB;
	 CREATE TABLE users (
  		username varchar(255) NOT NULL,
  		mail varchar(255) NOT NULL,
  		password varchar(255) NOT NULL,
  		gender varchar(10) check (gender in ('Female','Male')),
  		salt varchar(255) NOT NULL;
  		hashtag varchar(255) NOT NULL,
  		PRIMARY KEY (mail),
  		UNIQUE KEY (mail)
	 ); 
	 ------
	 
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String user = "";
	private String mail = "";
	private String pwd1 = "";
	private String pwd2 = "";
	
	//Our code
	private String gender = "";
	private String salt = "";
	private String g1 = "Male";
	private String g2 = "Female";
	private String hashtag = "0000";
	
	//We need to add "errors" for our attributes
	private boolean[] error  = {false,false,false,false,false,false}; /*All errors start at false. Whenever an input is set unsuccessfully 
																its error flag will be set to true*/
	
	public User() {
		
	}
	
	//User attribute
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		/* We can simulate that a user with the same name exists in our DB and mark error[0] as true  */
		//error[0] = true;
		this.user = user;
		System.out.println(user);
		error[0]=false;
	}
	
	//Mail attribute
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
			System.out.println(mail);
			error[1]=false;
		} else {
			error[1]=true;
			System.out.println("Mail must be of the correct form");
		}
		
	}
	
	//Pwd1 attribute
	public String getPwd1() {
		return this.pwd1;
	}
	
	public void setPwd1(String pwd1) {
		
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(pwd1);
		
	    if (matcher.matches()) {
			this.pwd1 = pwd1;
			System.out.println(pwd1);
		} 
		else {
			error[2]=true;
			System.out.println("Password must be of the correct format");
		}
		
		
	}
	
	//Pwd2 attribute
	public String getPwd2() {
		return this.pwd2;
	}
	
	public void setPwd2(String pwd2) {
		
		/* TODO check restriction with pattern and check if pwd1=pwd2*/
		//We only need to check if it equals pwd1 (pwd1 will already be with the condition)
		if (pwd1.equals(pwd2)) {
			this.pwd2 = pwd2;
			System.out.println(pwd2);
		} else {
			error[3]=true;
			System.out.println("Passwords must match");
		}
		
	}
	
	public String encriptPwd() {
		String salt = getSalt();
		String pwd = getPwd2();
		
		String hashedPwd = MD5.getMd5(salt + pwd); //Calls MD5 function (in Utils) to generate the hash for the salted password
		return hashedPwd;
	}
	
	//Gender attribute
	public String getGender() {
		return this.gender;
	}
	
	public void setGender (String gender) {
		if (gender.equals(this.g1) || gender.equals(this.g2)) {
			this.gender=gender;
			System.out.println(this.gender);
		}
		else {
			this.error[4]=true;
			System.out.println("Gender must be of the two types");
		}
	}
	
	//Salt attribute
	public String getSalt() {
		return this.salt;
	}
	
	public void setSalt() {
		
		byte[] bytes = new byte[16];
		
		SecureRandom saltGenerator = new SecureRandom();
		saltGenerator.nextBytes(bytes);
		
		//System.out.println(bytes);
		String salt = Base64.getEncoder().encodeToString(bytes);
		//System.out.println("salt:" + salt);
		this.salt = salt;
	}
	
	//Hash attribute
	public String getHashtag() {
		return this.hashtag;
	}
	public void setHashtag() {
		
		Random rand = new Random();
		int hashLong = rand.nextInt(10000);
		String hash = Integer.toString(hashLong);
		
		this.hashtag=hash;
		System.out.println(hash);
	}
	//Error attribute
	
	public boolean[] getError() {
		return error;
	}
		
}
