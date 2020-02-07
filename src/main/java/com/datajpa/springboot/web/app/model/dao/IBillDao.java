package com.datajpa.springboot.web.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.datajpa.springboot.web.app.model.entity.Bill;

public interface IBillDao extends CrudRepository<Bill, Long> {

}
