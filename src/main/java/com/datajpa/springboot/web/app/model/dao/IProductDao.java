package com.datajpa.springboot.web.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.datajpa.springboot.web.app.model.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long> {

	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByName(String name);
	
	List<Product> findByNameLikeIgnoreCase(String name);
}
