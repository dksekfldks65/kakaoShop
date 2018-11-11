package com.kakao.repository;

import com.kakao.domain.Product;
import com.kakao.domain.Sale;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	private static final RowMapper<Product> productRowMapper = (rs, i) ->{
		String  product_id = rs.getString("product_id");
		String  product_category = rs.getString("product_category");
		String  product_name = rs.getString("product_name");
		Integer product_price = rs.getInt("product_price");
		String  product_desc = rs.getString("product_desc"); 
		Integer product_remain = rs.getInt("product_remain");
		String  proudct_url = rs.getString("product_url");
		return new Product(product_id, product_category, product_name, product_price, product_desc, product_remain, proudct_url);
	};
	
	//상품 아이디를 통해 상품 객체 반환
	public Product findOne(String product_id) {
		System.out.println(product_id);
		SqlParameterSource param = new MapSqlParameterSource().addValue("product_id", product_id);
		return jdbcTemplate.queryForObject("SELECT product_id, product_category, product_name, product_price, product_desc, product_remain, product_url FROM products WHERE product_id=:product_id"
											, param, productRowMapper);
	}
	
	//모든 상품 리스트 반환
	public List<Product> findAll() {
		List<Product> products = jdbcTemplate.query("SELECT product_id, product_category, product_name, product_price, product_desc, product_remain, product_url FROM products",
					productRowMapper);
		return products;
	}
	
	//상품 삭제
	public void delete(String product_id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("product_id", product_id);
		jdbcTemplate.update("DELETE FROM products WHERE product_id=:product_id", param);
	}
}
