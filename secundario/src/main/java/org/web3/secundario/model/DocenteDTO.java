package org.web3.secundario.model;

public class DocenteDTO {
	private String id;
	private String nombres;
	private String apellido;
	private TipoDocumentoDTO tipo;
	private String nroDocumento;
	
	public DocenteDTO(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public TipoDocumentoDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoDocumentoDTO tipo) {
		this.tipo = tipo;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}	
}
