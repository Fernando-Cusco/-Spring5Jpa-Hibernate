package com.sistemas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender <T> {
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int cantidadElementos;
	private int paginaActual;
	
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		this.cantidadElementos = page.getSize();
		this.totalPaginas = page.getTotalPages();
		this.paginaActual = page.getNumber() + 1;
		
		
		int desde, hasta;
		if(this.totalPaginas <= this.cantidadElementos) {
			desde = 1;
			hasta = this.totalPaginas;
		} else {
			if(this.paginaActual <= this.totalPaginas/2) {
				desde = 1;
				hasta = this.cantidadElementos;
			} else if(this.paginaActual >= this.totalPaginas - this.cantidadElementos/2) {
				desde = this.totalPaginas - this.cantidadElementos + 1;
				hasta = this.cantidadElementos;
			} else {
				desde = this.paginaActual - this.cantidadElementos/2;
				hasta = this.cantidadElementos;
			}
		}
		
		for(int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, this.paginaActual == desde + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirts() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean hasNext() {
		return page.hasNext();
	}
	
	public boolean hasPrevious() {
		return page.hasPrevious();
	}
}
