package com.twitter.clone.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.twitter.clone.entity.User;

public class UserRowMapperImple implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {			
		User user = new User();
		user.setId(rs.getInt(1));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setImage(rs.getString("image"));
		user.setPassword(rs.getString("password"));
		user.setReg_date(rs.getDate("reg_date"));
		
		return user;
	}

}
