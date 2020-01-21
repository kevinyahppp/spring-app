package com.datajpa.springboot.web.app.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.datajpa.springboot.web.app.model.entity.Client;

public interface IClientService {
	List<Client> findAll();
	Page<Client> findAll(Pageable pageable);
	void save(Client client);
	Client findOne(Long id);
	void delete(Long id);
}
