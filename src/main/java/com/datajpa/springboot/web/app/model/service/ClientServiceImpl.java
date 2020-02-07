package com.datajpa.springboot.web.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datajpa.springboot.web.app.model.dao.IBillDao;
import com.datajpa.springboot.web.app.model.dao.IClientDao;
import com.datajpa.springboot.web.app.model.dao.IProductDao;
import com.datajpa.springboot.web.app.model.entity.Bill;
import com.datajpa.springboot.web.app.model.entity.Client;
import com.datajpa.springboot.web.app.model.entity.Product;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private IClientDao clientDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IBillDao billDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional
	public void save(Client client) {
		// TODO Auto-generated method stub
		clientDao.save(client);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findOne(Long id) {
		// TODO Auto-generated method stub
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clientDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return productDao.findByNameLikeIgnoreCase("%"+name+"%");
	}

	@Override
	@Transactional
	public void saveBill(Bill bill) {
		// TODO Auto-generated method stub
		billDao.save(bill);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findProductById(Long id) {
		// TODO Auto-generated method stub
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Bill findBillById(Long id) {
		// TODO Auto-generated method stub
		return billDao.findById(id).orElse(null);
	}
}
