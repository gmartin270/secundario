package org.web3.secundario.presentation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

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
import org.web3.secundario.model.DocenteDTO;
import org.web3.secundario.model.TipoDocumentoDTO;
import org.web3.secundario.presentation.service.TipoDocumentoService;

public class DocenteMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5372336601082365927L;
	
	private List<Object> docentes;
	private DocenteDTO selectedDocente;
	private String id;
	private String name;
	private String lastName;
	private TipoDocumentoDTO tipoDocumento;
	private String dni;
	private List<Object> tiposDocumento;
	private String filterDNI;
	private String filterApellido;

	@ManagedProperty("#{tipoDocumentoService}")
	private TipoDocumentoService tipoDocumentoService;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Object> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(List<Object> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	public TipoDocumentoService getTipoDocumentoService() {
		return tipoDocumentoService;
	}

	public void setTipoDocumentoService(
			TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	public TipoDocumentoDTO getTipoDocumento() {
		return tipoDocumento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getFilterDNI() {
		return filterDNI;
	}

	public void setFilterDNI(String filterDNI) {
		this.filterDNI = filterDNI;
	}

	public void setSelectedAlumno(DocenteDTO selectedDocente) {
		this.selectedDocente = selectedDocente;
	}

	public String getFilterApellido() {
		return filterApellido;
	}

	public void setFilterApellido(String filterApellido) {
		this.filterApellido = filterApellido;
	}
	
	public List<Object> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Object> docentes) {
		this.docentes = docentes;
	}

	public DocenteDTO getSelectedDocente() {
		return selectedDocente;
	}

	public void setSelectedDocente(DocenteDTO selectedDocente) {
		this.selectedDocente = selectedDocente;
	}

	@PostConstruct
	public void init() {
		tiposDocumento = tipoDocumentoService.getTipos();

		refreshGrid();
	}

	public void save() {
		boolean valid = true;
		
		System.out.println("Saving ...");

		if(name.length() == 0){
			valid = false;
			FacesContext.getCurrentInstance().addMessage("msgsMateria", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo: Debe ingresar un nombre.",  "" ));
		}
		
		if(lastName.length() == 0){
			valid = false;
			FacesContext.getCurrentInstance().addMessage("msgsMateria", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo: Debe ingresar un apellido.",  "" ));
		}
		
		if(dni.length() == 0){
			valid = false;
			FacesContext.getCurrentInstance().addMessage("msgsMateria", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo: Debe ingresar un nro. de doc.",  "" ));
		}

		try {
			if (valid){
				DocenteDTO docente = new DocenteDTO();
				docente.setNombres(name);
				docente.setApellido(lastName);
				docente.setNroDocumento(dni);
				docente.setTipo(tipoDocumento);
				
				if (id != null && id.length() > 0) {
					docente.setId(id);
					EditFacade editFacade = (EditFacade) FactoryFacade
							.getFacade(TipoFacade.EDIT);
					editFacade.edit(docente);
				} else {
					CreateFacade createFacade = (CreateFacade) FactoryFacade
							.getFacade(TipoFacade.CREATE);
					createFacade.create(docente);
				}
			}

		} catch (CreateObjectException co) {

		} catch (EditObjectException eo) {

		}

		id = null;
		name = null;
		lastName = null;
		dni = null;
		tipoDocumento = null;

		refreshGrid();

		System.out.println("Saved ...");
	}

	public void editarDocente() {
		name = selectedDocente.getNombres();
		lastName = selectedDocente.getApellido();
		dni = selectedDocente.getNroDocumento();
		tipoDocumento = selectedDocente.getTipo();
		id = selectedDocente.getId();
	}

	public void borrarDocente() {
		RemoveFacade removeFacade = (RemoveFacade) FactoryFacade
				.getFacade(TipoFacade.REMOVE);

		try {
			removeFacade.remove(selectedDocente);
		} catch (RemoveObjectException re) {

		}

		selectedDocente = null;
		refreshGrid();
	}

	public void refreshGrid() {
		SearchFacade searchFac = (SearchFacade) FactoryFacade
				.getFacade(TipoFacade.SEARCH);

		try {
			DocenteDTO dto;
			
			if((dto = setFilters()) != null ){
				docentes = searchFac.getByCriteria(dto, DocenteDTO.class);
			}else
				docentes = searchFac.getAll(DocenteDTO.class);
			
			for (Object object : docentes) {
				System.out.println("Nombre del docente: "
						+ ((DocenteDTO) object).getNombres() + " - Tipo"
						+ ((DocenteDTO) object).getTipo().getId());
			}
		} catch (SearchObjectException so) {

		}
	}
	
	private DocenteDTO setFilters(){
		DocenteDTO dto = null;
		
		if((filterDNI != null && filterDNI.length() > 0) || (filterApellido != null && filterApellido.length() > 0)){
			dto = new DocenteDTO();
		
			if(filterDNI != null && filterDNI.length() > 0)
				dto.setNroDocumento(filterDNI);
			
			if(filterApellido != null && filterApellido.length() > 0)
				dto.setApellido(filterApellido);
		}
		
		return dto;
	}	
}
