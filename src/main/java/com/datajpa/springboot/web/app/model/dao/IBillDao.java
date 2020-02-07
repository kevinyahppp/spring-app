package com.datajpa.springboot.web.app.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.datajpa.springboot.web.app.model.entity.Bill;

public interface IBillDao extends CrudRepository<Bill, Long> {
	@Query("select f from Bill f join fetch f.client c join fetch f.items l join fetch l.product where f.id=?1")
	Bill fetchByIdWithClientWithItemBillWithProduct(Long id);
}
