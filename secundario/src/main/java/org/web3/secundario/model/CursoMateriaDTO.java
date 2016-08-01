package org.web3.secundario.model;

public class CursoMateriaDTO {
	private String id;
	private CursoDTO curso;
	private MateriaDTO materia;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CursoDTO getCurso() {
		return curso;
	}
	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}
	public MateriaDTO getMateria() {
		return materia;
	}
	public void setMateria(MateriaDTO materia) {
		this.materia = materia;
	}
	
	
}
