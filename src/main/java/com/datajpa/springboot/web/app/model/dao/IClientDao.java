package com.datajpa.springboot.web.app.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.datajpa.springboot.web.app.model.entity.Client;

public interface IClientDao extends PagingAndSortingRepository<Client, Long> {

	
}
