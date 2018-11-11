package com.kakao.repository;

import com.kakao.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<User> userRowMapper = (rs, i) ->{
		String user_id = rs.getString("user_id");
		String user_password = rs.getString("user_password");
		String user_name = rs.getString("user_name");
		String user_gender = rs.getString("user_gender"); 
		return new User(user_id, user_password, user_name, user_gender);
	};
	
	//모든 유저 리스트 반환
	public List<User> findAll() {
		List<User> users = jdbcTemplate.query("SELECT user_id, user_password, user_name, user_gender FROM users",
					userRowMapper);
		return users;
	}
	
	//유저 아이디를 통해 유저 객체 반환
	public User findOne(String user_id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", user_id);
		return jdbcTemplate.queryForObject("SELECT user_id, user_password, user_name, user_gender FROM users WHERE user_id=:user_id"
											, param, userRowMapper);
	}
	
	//신규 유저 등록
	public User save(User user) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(user); 
		try {
			jdbcTemplate.update("INSERT INTO users(user_id, user_password, user_name, user_gender)" 
					+ "values(:userId, :password, :name, :gender)", param);
			return user;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//유저 삭제
	public void delete(String user_id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", user_id);
		jdbcTemplate.update("DELETE FROM users WHERE user_id=:user_id", param);
	}
}
