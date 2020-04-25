package com.user.management.model;

import lombok.Data;

@Data
public class Role {
	
	private Integer id;
    private String role;
	public Role(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

}
