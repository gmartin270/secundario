package org.web3.secundario.model;

public class PaisDTO {
	private String id;
	private String nombre;
	private String abreviatura;
	
	public PaisDTO(){};
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Override
    public String toString() {
        return nombre;
    }
}
