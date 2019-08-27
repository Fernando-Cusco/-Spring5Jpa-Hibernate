package com.sistemas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistemas.springboot.app.models.entity.Cliente;


@Repository
public class ClienteDaoImp implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)									//Modo solo lectura, toma el metodo y lo envuelve dentro de la transaccion
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);															//actualiza el cliente
		} else {
			em.persist(cliente);														//crea un nuevo cliente
		}
	}

	@Override
	
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

}
