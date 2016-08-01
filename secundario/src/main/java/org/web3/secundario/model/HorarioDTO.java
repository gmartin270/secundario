package org.web3.secundario.model;

public class HorarioDTO {
	private String id;
	private MateriaDTO materia;
	private Long horaInicio;
	private Long minutoInicio;
	private Long horaFin;
	private Long minutoFin;
	private DiasSemana dia;
	
	public HorarioDTO(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MateriaDTO getMateria() {
		return materia;
	}

	public void setMateria(MateriaDTO materia) {
		this.materia = materia;
	}

	public Long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getMinutoInicio() {
		return minutoInicio;
	}

	public void setMinutoInicio(Long minutoInicio) {
		this.minutoInicio = minutoInicio;
	}

	public Long getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Long horaFin) {
		this.horaFin = horaFin;
	}

	public Long getMinutoFin() {
		return minutoFin;
	}

	public void setMinutoFin(Long minutoFin) {
		this.minutoFin = minutoFin;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public void setDia(DiasSemana dia) {
		this.dia = dia;
	};
	
	
}
