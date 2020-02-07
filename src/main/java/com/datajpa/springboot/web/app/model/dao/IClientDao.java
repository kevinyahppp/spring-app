package com.datajpa.springboot.web.app.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.datajpa.springboot.web.app.model.entity.Client;

public interface IClientDao extends PagingAndSortingRepository<Client, Long> {
	@Query("select c from Client c left join fetch c.bills f where c.id=?1")
	Client fetchByIdWithBills(Long id);
}
