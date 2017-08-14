package com.devoir;

import java.util.Set;
import java.util.HashSet;
import com.housing.*;

public class UserModel
{
  private String firstName;
  private String lastName;
  private String mail;
  private String password;
  private Set<Integer> housings;

	/**
	* Default empty User constructor
	*/
	public UserModel() {
		super();
	}

	/**
	* Default User constructor
	*/
	public UserModel(String firstName, String lastName, String mail, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
    this.housings = new HashSet<>();
    this.housings.add(1);
	}

	/**
	* Returns value of firstName
	* @return
	*/
	public String getFirstName() {
		return firstName;
	}

	/**
	* Sets new value of firstName
	* @param
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	* Returns value of lastName
	* @return
	*/
	public String getLastName() {
		return lastName;
	}

	/**
	* Sets new value of lastName
	* @param
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	* Returns value of mail
	* @return
	*/
	public String getMail() {
		return mail;
	}

	/**
	* Sets new value of mail
	* @param
	*/
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	* Returns value of password
	* @return
	*/
	public String getPassword() {
		return password;
	}

	/**
	* Sets new value of password
	* @param
	*/
	public void setPassword(String password) {
		this.password = password;
	}

  public Set<Integer> getHousings(){
    return this.housings;
  }

  public void setHousings(Set<Integer> housings){
    this.housings = housings;
  }

	/**
	* Create string representation of User for printing
	* @return
	*/
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail + ", password=" + password + "]";
	}
}
