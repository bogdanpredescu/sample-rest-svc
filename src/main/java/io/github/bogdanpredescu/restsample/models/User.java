package io.github.bogdanpredescu.restsample.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_info")
public class User {

	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	public User() {}
	
	public User(String userId, String name, String email)
	{
		this.userId = userId;
		this.name = name;
		this.email = email;
	}
		
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String id) {
		this.userId = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	  
}

