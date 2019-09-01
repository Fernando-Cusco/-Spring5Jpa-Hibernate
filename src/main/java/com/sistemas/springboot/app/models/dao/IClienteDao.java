package com.sistemas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sistemas.springboot.app.models.entity.Cliente;

public interface IClienteDao  extends PagingAndSortingRepository<Cliente, Long>{    //CrudRepository
	
}
