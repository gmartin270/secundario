package org.web3.secundario.model;

import java.util.List;

import org.web3.secundario.model.PaisDTO;
import org.web3.secundario.model.TipoDocumentoDTO;

public class AlumnoDTO {
	private String id;
	private String nombres;
	private String apellido;
	private TipoDocumentoDTO tipo;
	private String nroDocumento;
	private PaisDTO nacionalidad;
	private List<MateriaDTO> materias;

	public AlumnoDTO() {
	}

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

	public List<MateriaDTO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaDTO> materias) {
		this.materias = materias;
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

	public PaisDTO getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(PaisDTO nacionalidad) {
		this.nacionalidad = nacionalidad;
	};
}
