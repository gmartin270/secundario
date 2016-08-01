package org.web3.secundario.model;

public class SancionDTO {
	private String id;
	private AlumnoDTO alumno;
	
	public SancionDTO(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AlumnoDTO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoDTO alumno) {
		this.alumno = alumno;
	};
	
	
}
