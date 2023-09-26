package com.twitter.clone.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.twitter.clone.entity.PostMultimedia;

public class PostMultimediaRowMapperImple implements RowMapper<PostMultimedia> {

	@Override
	public PostMultimedia mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PostMultimedia postMultimedia = new PostMultimedia();
		postMultimedia.setId(rs.getInt("id"));
		postMultimedia.setMedia_type(rs.getString("media_type"));
		postMultimedia.setPath(rs.getString("path"));
		
		return postMultimedia;
	}

	
}
