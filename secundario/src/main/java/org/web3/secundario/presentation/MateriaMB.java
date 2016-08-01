package org.web3.secundario.presentation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.facade.CreateFacade;
import org.web3.secundario.bussiness.facade.EditFacade;
import org.web3.secundario.bussiness.facade.RemoveFacade;
import org.web3.secundario.bussiness.facade.SearchFacade;
import org.web3.secundario.bussiness.facade.TipoFacade;
import org.web3.secundario.bussiness.factory.FactoryFacade;
import org.web3.secundario.model.MateriaDTO;

public class MateriaMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8240343516110620366L;
	
	private List<Object> materias;
	private MateriaDTO selectedMateria;
	private String id;
	private String nombre;
	private String descripcion;
	private String filterNombre;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setSelectedAlumno(MateriaDTO selectedMateria) {
		this.selectedMateria = selectedMateria;
	}

	@PostConstruct
	public void init() {
		refreshGrid();
	}

	public void save() {
		System.out.println("Saving ...");

		MateriaDTO dto = new MateriaDTO();
		dto.setNombre(nombre);
		dto.setDescripcion(descripcion);

		try {
			if (id != null && id.length() > 0) {
				dto.setId(id);
				EditFacade editFacade = (EditFacade) FactoryFacade
						.getFacade(TipoFacade.EDIT);
				editFacade.edit(dto);
			} else {
				CreateFacade createFacade = (CreateFacade) FactoryFacade
						.getFacade(TipoFacade.CREATE);
				createFacade.create(dto);
			}

		} catch (CreateObjectException co) {

		} catch (EditObjectException eo) {

		}

		id = null;
		nombre = null;
		descripcion = null;
		refreshGrid();

		System.out.println("Saved ...");
	}

	public void editarMateria() {
		nombre = selectedMateria.getNombre();
		descripcion = selectedMateria.getDescripcion();
		id = selectedMateria.getId();
	}

	public void borrarMateria() {
		RemoveFacade removeFacade = (RemoveFacade) FactoryFacade
				.getFacade(TipoFacade.REMOVE);

		try {
			removeFacade.remove(selectedMateria);
		} catch (RemoveObjectException re) {

		}

		selectedMateria = null;
		refreshGrid();
	}

	public void refreshGrid() {
		SearchFacade searchFac = (SearchFacade) FactoryFacade
				.getFacade(TipoFacade.SEARCH);

		try {
			MateriaDTO dto;
			
			if((dto = setFilters()) != null ){
				materias = searchFac.getByCriteria(dto, MateriaDTO.class);
			}else
				materias = searchFac.getAll(MateriaDTO.class);
			
			for (Object object : materias) {
				System.out.println("Nombre de la Materia: "
						+ ((MateriaDTO) object).getDescripcion());
			}
		} catch (SearchObjectException so) {

		}
	}
	
	private MateriaDTO setFilters(){
		MateriaDTO dto = null;
		
		if((filterNombre != null && filterNombre.length() > 0)){
			dto = new MateriaDTO();
		
			if(filterNombre != null && filterNombre.length() > 0)
				dto.setNombre(filterNombre);
		}
		
		return dto;
	}

	public List<Object> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Object> materias) {
		this.materias = materias;
	}

	public MateriaDTO getSelectedMateria() {
		return selectedMateria;
	}

	public void setSelectedMateria(MateriaDTO selectedMateria) {
		this.selectedMateria = selectedMateria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFilterNombre() {
		return filterNombre;
	}

	public void setFilterNombre(String filterNombre) {
		this.filterNombre = filterNombre;
	}
}
