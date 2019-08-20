package com.sistemas.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistemas.springboot.app.models.dao.IClienteDao;

@Controller														//Marcamos la clase propia de un controlador
public class ClienteController {
	
	@Autowired													//Busca un componente en el contenedor que implemente ICliente
	private IClienteDao clienteDao;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista Clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
}
