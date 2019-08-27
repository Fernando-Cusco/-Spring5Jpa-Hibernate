package com.sistemas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sistemas.springboot.app.models.dao.ClienteDaoImp;
import com.sistemas.springboot.app.models.dao.IClienteDao;
import com.sistemas.springboot.app.models.entity.Cliente;

@Controller														//Marcamos la clase propia de un controlador
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired													//Busca un componente en el contenedor que implemente ICliente
	private IClienteDao clienteDao;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista Clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus state) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Cliente");
			return "form";
		}
		clienteDao.save(cliente);
		state.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cli = null;
		if(id > 0) {
			cli = clienteDao.findOne(id);
		} else {
			return "redirect:listar";
		}
		model.put("cliente", cli);
		model.put("titulo",  "Editar el usuario");
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			clienteDao.delete(id);
		}
		return "redirect:/listar";
	}
}