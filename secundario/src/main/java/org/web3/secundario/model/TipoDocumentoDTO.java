package org.web3.secundario.model;

import java.io.Serializable;

public class TipoDocumentoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6292500764734914391L;
	private String id;
	private String descripcion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString(){
		return descripcion;
	}
}
