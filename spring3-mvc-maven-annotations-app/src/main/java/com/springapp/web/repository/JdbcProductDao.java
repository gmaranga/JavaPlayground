package com.springapp.web.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.springapp.web.domain.Product;

@Repository
public class JdbcProductDao implements ProductDao {

	/** Logger for this class and subclasses */
	static final Logger LOG = LoggerFactory.getLogger(JdbcProductDao.class);
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Product> getProductList() {
		LOG.info("Getting products!");
		List<Product> products = jdbcTemplate.query(
				"select id, description, price from products",
				new ProductMapper());
		return products;
	}

	@Override
	public void saveProduct(Product prod) {
		LOG.info("Saving product: " + prod.getDescription());
		int count = jdbcTemplate
				.update("update products set description = :description, price = :price where id = :id",
						new MapSqlParameterSource()
								.addValue("description", prod.getDescription())
								.addValue("price", prod.getPrice())
								.addValue("id", prod.getId()));
		LOG.info("Rows affected: " + count);
	}

	private static class ProductMapper implements
			ParameterizedRowMapper<Product> {

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product prod = new Product();
			prod.setId(rs.getInt("id"));
			prod.setDescription(rs.getString("description"));
			prod.setPrice(new Double(rs.getDouble("price")));
			return prod;
		}

	}

}
