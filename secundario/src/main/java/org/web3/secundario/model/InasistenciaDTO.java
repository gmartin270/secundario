package org.web3.secundario.model;

import java.util.Date;

public class InasistenciaDTO {

	private String id;
	private AlumnoDTO alumno;
	private CursoDTO curso;
	private Date fecha;
	
	public InasistenciaDTO(){}

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
	}

	public CursoDTO getCurso() {
		return curso;
	}

	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	};
	
	
}
