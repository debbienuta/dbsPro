package model;

import java.util.Date;


public class User
{
	private int id;
	private String joinDate;
	private String username;
	private String password;
	private String profilePicture;
	private int personId;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getJoinDate()
	{
		return joinDate;
	}
	public void setJoinDate(String joinDate)
	{
		this.joinDate = joinDate;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getProfilePicture()
	{
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture)
	{
		this.profilePicture = profilePicture;
	}
	public int getPersonId()
	{
		return personId;
	}
	public void setPersonId(int personId)
	{
		this.personId = personId;
	}
}
