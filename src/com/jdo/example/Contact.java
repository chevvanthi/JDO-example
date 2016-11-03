package com.jdo.example;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Contact {
@PrimaryKey
@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
private String firstName;
@Persistent 
private String  number;

@Persistent
private String lastName;


	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number=number;
	}
}
