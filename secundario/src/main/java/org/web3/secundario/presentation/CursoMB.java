package org.web3.secundario.presentation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
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
import org.web3.secundario.model.CursoDTO;
import org.web3.secundario.model.CursoMateriaDTO;
import org.web3.secundario.model.MateriaDTO;
import org.web3.secundario.presentation.service.MateriaService;

public class CursoMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5962275823025163352L;
	
	private List<Object> cursos;
	private List<Object> cursosMaterias;
	private CursoDTO selectedCurso;
	private CursoMateriaDTO selectedCursoMateria;
	private String id;
	private String nombre;
	private String descripcion;
	private MateriaDTO materia;
	private List<Object> materias; 
	private String filterNombre;
	
	@ManagedProperty("#{materiaService}")
	private MateriaService materiaService;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setSelectedAlumno(CursoDTO selectedCurso) {
		this.selectedCurso = selectedCurso;
	}

	@PostConstruct
	public void init() {
		materias = materiaService.getMaterias();
		refreshGrid();
	}

	public void save() {
		System.out.println("Saving ...");
		boolean valid = true;
		
		CursoDTO dto = new CursoDTO();
		dto.setNombre(nombre);
		dto.setDescripcion(descripcion);

		try {
			if(nombre.length() == 0){
				FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo: Debe ingresar un nombre de materia.",  "" ));
				valid = false;
			}
			
			if(valid){
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

	public void editarCurso() {
		nombre = selectedCurso.getNombre();
		descripcion = selectedCurso.getDescripcion();
		id = selectedCurso.getId();
	}

	public void borrarCurso() {
		RemoveFacade removeFacade = (RemoveFacade) FactoryFacade
				.getFacade(TipoFacade.REMOVE);

		try {
			removeFacade.remove(selectedCurso);
		} catch (RemoveObjectException re) {

		}

		selectedCurso = null;
		refreshGrid();
	}

	public void refreshGrid() {
		SearchFacade searchFac = (SearchFacade) FactoryFacade
				.getFacade(TipoFacade.SEARCH);

		try {
			CursoDTO dto;
			
			if((dto = setFilters()) != null ){
				cursos = searchFac.getByCriteria(dto, CursoDTO.class);
			}else
				cursos = searchFac.getAll(CursoDTO.class);
			
			for (Object object : cursos) {
				System.out.println("Nombre del Curso: "
						+ ((CursoDTO) object).getDescripcion());
			}
			
			if(selectedCurso != null){
				CursoMateriaDTO cm = new CursoMateriaDTO();
				cm.setCurso(selectedCurso);
				
				cursosMaterias = searchFac.getAllByFilter(cm);
			}
		} catch (SearchObjectException so) {

		}
	}
	
	private CursoDTO setFilters(){
		CursoDTO dto = null;
		
		if((filterNombre != null && filterNombre.length() > 0)){
			dto = new CursoDTO();
		
			if(filterNombre != null && filterNombre.length() > 0)
				dto.setNombre(filterNombre);
		}
		
		return dto;
	}
	
	public void saveCursoMateria() {
		boolean valid = true;
		
		if(selectedCurso == null){
			FacesContext.getCurrentInstance().addMessage("msgsMateria", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo: Debe seleccionar un curso.",  "" ));
			valid = false;
		}
		
		if(materia == null){
			FacesContext.getCurrentInstance().addMessage("msgsMateria", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo: Debe seleccionar una materia.",  "" ));
			valid = false;
		}
		
		if(valid){
			CursoMateriaDTO cursoMateria = new CursoMateriaDTO();
			cursoMateria.setCurso(selectedCurso);
			cursoMateria.setMateria(materia);
			
			try{
				CreateFacade createFacade = (CreateFacade) FactoryFacade
						.getFacade(TipoFacade.CREATE);
				createFacade.create(cursoMateria);
			} catch (CreateObjectException co) {
				
			}
		}
	}
	
	public void onRowSelect(SelectEvent event) {
		refreshGrid();
	}
	
	public void borrarCM(){
		RemoveFacade removeFacade = (RemoveFacade) FactoryFacade
				.getFacade(TipoFacade.REMOVE);

		try {
			removeFacade.remove(selectedCursoMateria);
		} catch (RemoveObjectException re) {

		}

		selectedCursoMateria = null;
		refreshGrid();
	}

	public List<Object> getCursos() {
		return cursos;
	}

	public void setCursos(List<Object> cursos) {
		this.cursos = cursos;
	}

	public CursoDTO getSelectedCurso() {
		return selectedCurso;
	}

	public void setSelectedCurso(CursoDTO selectedCurso) {
		this.selectedCurso = selectedCurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFilterNombre() {
		return filterNombre;
	}

	public void setFilterNombre(String filterNombre) {
		this.filterNombre = filterNombre;
	}

	public MateriaDTO getMateria() {
		return materia;
	}

	public void setMateria(MateriaDTO materia) {
		this.materia = materia;
	}

	public List<Object> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Object> materias) {
		this.materias = materias;
	}

	public MateriaService getMateriaService() {
		return materiaService;
	}

	public void setMateriaService(MateriaService materiaService) {
		this.materiaService = materiaService;
	}

	public List<Object> getCursosMaterias() {
		return cursosMaterias;
	}

	public void setCursosMaterias(List<Object> cursosMaterias) {
		this.cursosMaterias = cursosMaterias;
	}

	public CursoMateriaDTO getSelectedCursoMateria() {
		return selectedCursoMateria;
	}

	public void setSelectedCursoMateria(CursoMateriaDTO selectedCursoMateria) {
		this.selectedCursoMateria = selectedCursoMateria;
	}

}
