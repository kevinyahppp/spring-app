package com.datajpa.springboot.web.app.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.datajpa.springboot.web.app.model.entity.Bill;
import com.datajpa.springboot.web.app.model.entity.Client;
import com.datajpa.springboot.web.app.model.entity.Product;

public interface IClientService {
	List<Client> findAll();
	Page<Client> findAll(Pageable pageable);
	void save(Client client);
	Client findOne(Long id);
	void delete(Long id);
	List<Product> findByName(String name);
	void saveBill(Bill bill);
	Product findProductById(Long id);
	Bill findBillById(Long id);
	void deleteBill(Long id);
	Bill fetchByIdWithClientWithItemBillWithProduct(Long id);
	Client fetchByIdWithBills(Long id);
}
