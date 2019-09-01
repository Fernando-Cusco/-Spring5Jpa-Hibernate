package com.sistemas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistemas.springboot.app.models.entity.Cliente;
import com.sistemas.springboot.app.models.service.IClienteService;

@Controller														//Marcamos la clase propia de un controlador
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired													//Busca un componente en el contenedor que implemente ICliente
	private IClienteService clienteService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		model.addAttribute("titulo", "Lista Clientes");
		model.addAttribute("clientes", clientes);
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model, Model m) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		m.addAttribute("boton", "Crear Cliente");
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus state) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Cliente");
			model.addAttribute("boton", "Crear Cliente");
			return "form";
		}
		String mns = (cliente.getId() != null)? "Cliente editado correctamente!" : "Cliente creado correctamente!";
		clienteService.save(cliente);
		state.setComplete();
		flash.addFlashAttribute("success", mns);
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, Model m, RedirectAttributes flash) {
		Cliente cli = null;
		if(id > 0) {
			cli = clienteService.findOne(id);
			if(cli == null) {
				flash.addFlashAttribute("info", "El cliente no existe en la base de datos!!!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "No se puedo editar el cliente!.");
			return "redirect:listar";
		}
		model.put("cliente", cli);
		model.put("titulo",  "Editar el usuario");
		m.addAttribute("boton", "Actualizar Cliente");
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if(id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado correctamente!");
		}
		return "redirect:/listar";
	}
}