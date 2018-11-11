package com.kakao.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.kakao.domain.Sale;

@Repository
@Transactional
public class SaleRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Sale> saleRowMapper = (rs, i) -> {
		String user_id = rs.getString("user_id");
		String product_id = rs.getString("product_id");
		Integer sale_num = rs.getInt("sale_num");
		Integer sum_price = rs.getInt("sum_price");
		return new Sale(user_id, product_id, sale_num, sum_price);
	};
	
	//모든 거래 리스트 반환
	public List<Sale> findAllSale(String user_id){
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", user_id);
		List<Sale> sales = jdbcTemplate.query("SELECT user_id, product_id, sale_num, sum_price FROM sale_history WHERE user_id=:user_id", param, saleRowMapper);
		return sales;
	}
	
	//제품 구매시 거래 테이블 업데이트
	public void saveSale(Sale sale) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(sale); 
		jdbcTemplate.update("INSERT INTO sale_history(user_id, product_id, sale_num, sum_price)" + "values(:userId, :productId, :saleNum, :sumPrice)", param);
	}
	
}
