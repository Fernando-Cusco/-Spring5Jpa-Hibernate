package com.sistemas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity																			//persistencia
@Table(name="clientes") 														//permite colocar un nombre sobre la base de datos
public class Cliente implements Serializable{

	@Id																			//inidica que el atributo id es la llave primaria sobre la DB.
	@GeneratedValue(strategy = GenerationType.IDENTITY )						//Indica que el id sera autoincrementable
	private Long id;

	@NotEmpty
	@Size(min = 4, max = 100)													//Especifica un rango de campo
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Column(name = "create_at")													//Indica el nombre del campo sobre la DB.
	@Temporal(TemporalType.DATE)												//Indica el formato de la fecha
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private Date createAt;														
	
	
	/*
	@PrePersist																	//se llama este metodo justo antes de que se inserte en la base de datos
	public void prePersist() {
		createAt = new Date();
	}*/
	
	//es recomendable serializar la clase
	private static final long serialVersionUID = 8014868644097168934L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
