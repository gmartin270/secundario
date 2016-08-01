package org.web3.secundario.model;

import java.util.List;

public class MateriaDTO {
	private String id;
	private String nombre;
	private String descripcion;
	private DocenteDTO docente;
	private List<CursoDTO> cursos;
	
	public MateriaDTO(){}

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

	public DocenteDTO getDocente() {
		return docente;
	}

	public void setDocente(DocenteDTO docente) {
		this.docente = docente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CursoDTO> getCursos() {
		return cursos;
	}

	public void setCursos(List<CursoDTO> cursos) {
		this.cursos = cursos;
	};
	
	@Override
    public String toString() {
        return nombre;
    }
}
