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
import org.web3.secundario.model.AlumnoDTO;
import org.web3.secundario.model.PaisDTO;
import org.web3.secundario.model.TipoDocumentoDTO;
import org.web3.secundario.presentation.service.PaisService;
import org.web3.secundario.presentation.service.TipoDocumentoService;

public class AlumnoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1961134727785477803L;

	private List<Object> alumnos;
	private AlumnoDTO selectedAlumno;
	private String id;
	private String name;
	private String lastName;
	private TipoDocumentoDTO tipoDocumento;
	private String dni;
	private PaisDTO nacionalidad;
	private List<Object> nacionalidades;
	private List<Object> tiposDocumento;
	private String filterDNI;
	private String filterApellido;

	@ManagedProperty("#{paisService}")
	private PaisService paisService;

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

	public PaisDTO getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(PaisDTO nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Object> getAlumnos() {
		return alumnos;
	}

	public AlumnoDTO getSelectedAlumno() {
		return selectedAlumno;
	}

	public void setSelectedCliente(AlumnoDTO selectedAlumno) {
		this.selectedAlumno = selectedAlumno;
	}

	public void setAlumnos(List<Object> alumnos) {
		this.alumnos = alumnos;
	}

	public void setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Object> getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(List<Object> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	public PaisService getPaisService() {
		return paisService;
	}

	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
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

	@PostConstruct
	public void init() {
		nacionalidades = paisService.getPaises();
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
			
			if(valid){
				AlumnoDTO alumno = new AlumnoDTO();
				alumno.setNombres(name);
				alumno.setApellido(lastName);
				alumno.setNroDocumento(dni);
				alumno.setNacionalidad(nacionalidad);
				alumno.setTipo(tipoDocumento);
				
				if (id != null && id.length() > 0) {
					alumno.setId(id);
					EditFacade editFacade = (EditFacade) FactoryFacade
							.getFacade(TipoFacade.EDIT);
					editFacade.edit(alumno);
				} else {
					CreateFacade createFacade = (CreateFacade) FactoryFacade
							.getFacade(TipoFacade.CREATE);
					createFacade.create(alumno);
				}
			}

		} catch (CreateObjectException co) {

		} catch (EditObjectException eo) {

		}

		id = null;
		name = null;
		lastName = null;
		dni = null;
		nacionalidad = null;
		tipoDocumento = null;

		refreshGrid();

		System.out.println("Saved ...");
	}

	public void editarAlumno() {
		name = selectedAlumno.getNombres();
		lastName = selectedAlumno.getApellido();
		dni = selectedAlumno.getNroDocumento();
		nacionalidad = selectedAlumno.getNacionalidad();
		tipoDocumento = selectedAlumno.getTipo();
		id = selectedAlumno.getId();

		// nacionalidades = paisService.getPaises();
		// reputaciones = reputacionService.getReputaciones();
		// tiposDocumento = tipoDocumentoService.getTipos();

	}

	public void borrarAlumno() {
		RemoveFacade removeFacade = (RemoveFacade) FactoryFacade
				.getFacade(TipoFacade.REMOVE);

		try {
			removeFacade.remove(selectedAlumno);
		} catch (RemoveObjectException re) {

		}

		selectedAlumno = null;
		refreshGrid();
	}

	public void refreshGrid() {
		SearchFacade searchFac = (SearchFacade) FactoryFacade
				.getFacade(TipoFacade.SEARCH);

		try {
			AlumnoDTO dto;
			
			if((dto = setFilters()) != null ){
				alumnos = searchFac.getByCriteria(dto, AlumnoDTO.class);
			}else
				alumnos = searchFac.getAll(AlumnoDTO.class);
			
			for (Object object : alumnos) {
				System.out.println("Nombre del alumno: "
						+ ((AlumnoDTO) object).getNombres() + " - Tipo"
						+ ((AlumnoDTO) object).getTipo().getId());
			}
		} catch (SearchObjectException so) {

		}
	}
	
	private AlumnoDTO setFilters(){
		AlumnoDTO dto = null;
		
		if((filterDNI != null && filterDNI.length() > 0) || (filterApellido != null && filterApellido.length() > 0)){
			dto = new AlumnoDTO();
		
			if(filterDNI != null && filterDNI.length() > 0)
				dto.setNroDocumento(filterDNI);
			
			if(filterApellido != null && filterApellido.length() > 0)
				dto.setApellido(filterApellido);
		}
		
		return dto;
	}

	public String getFilterDNI() {
		return filterDNI;
	}

	public void setFilterDNI(String filterDNI) {
		this.filterDNI = filterDNI;
	}

	public void setSelectedAlumno(AlumnoDTO selectedAlumno) {
		this.selectedAlumno = selectedAlumno;
	}

	public String getFilterApellido() {
		return filterApellido;
	}

	public void setFilterApellido(String filterApellido) {
		this.filterApellido = filterApellido;
	}
}
