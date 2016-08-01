package org.web3.secundario.presentation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.facade.CreateFacade;
import org.web3.secundario.bussiness.facade.EditFacade;
import org.web3.secundario.bussiness.facade.SearchFacade;
import org.web3.secundario.bussiness.facade.TipoFacade;
import org.web3.secundario.bussiness.factory.FactoryFacade;

public class HomeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2182990776549800921L;
	
	private String id;
	private List<Object> vehiculos;
	private List<Object> clientes;
	private Date fechaEntrega;
	private Date fechaDevolucion;
	private Date fechaDevolucionEfectiva;
	private boolean deshabilitarAsignacion;
	private List<Object> modelos;
    private String dominio;
    private List<Object> asignaciones;
    private Long vehiculosTotal;
    private Long vehiculosDisponibles;
    private Long vehiculosAsignados;
     
//    @ManagedProperty(value="#{modeloTodosService}", name="modeloTodosService")
//    private ModeloTodosService modeloTodosService;
	
	public List<Object> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Object> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Object> getClientes() {
		return clientes;
	}

	public void setClientes(List<Object> clientes) {
		this.clientes = clientes;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean getDeshabilitarAsignacion() {
		return deshabilitarAsignacion;
	}

	public void setDeshabilitarAsignacion(boolean deshabilitarAsignacion) {
		this.deshabilitarAsignacion = deshabilitarAsignacion;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public List<Object> getModelos() {
		return modelos;
	}

	public void setModelos(List<Object> modelos) {
		this.modelos = modelos;
	}

	
	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	public Date getFechaDevolucionEfectiva() {
		return fechaDevolucionEfectiva;
	}

	public void setFechaDevolucionEfectiva(Date fechaDevolucionEfectiva) {
		this.fechaDevolucionEfectiva = fechaDevolucionEfectiva;
	}

	public List<Object> getAsignaciones() {
		return asignaciones;
	}

	public void setAsignaciones(List<Object> asignaciones) {
		this.asignaciones = asignaciones;
	}

	
	public Long getVehiculosTotal() {
		return vehiculosTotal;
	}

	public void setVehiculosTotal(Long vehiculosTotal) {
		this.vehiculosTotal = vehiculosTotal;
	}

	public Long getVehiculosDisponibles() {
		return vehiculosDisponibles;
	}

	public void setVehiculosDisponibles(Long vehiculosDisponibles) {
		this.vehiculosDisponibles = vehiculosDisponibles;
	}

	public Long getVehiculosAsignados() {
		return vehiculosAsignados;
	}

	public void setVehiculosAsignados(Long vehiculosAsignados) {
		this.vehiculosAsignados = vehiculosAsignados;
	}
	
	/*
	@PostConstruct
    public void init() {
		deshabilitarAsignacion = true;
		
		modelos = modeloTodosService.getModelos();
		
		refreshGrid();
    }
	
	public void guardarAsignacion(){
		System.out.println("Guardardando asignación...");
		boolean valid = true;
		
		if(selectedCliente == null){
			FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo",  "Debe seleccionar un cliente." ));
			valid = false;
		}
		
		if(fechaEntrega == null){
			FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo",  "Debe ingresar una fecha de Entrega." ));
			valid = false;
		}
		
		if(fechaDevolucion == null){
			FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo",  "Debe ingresar una fecha de Devolución." ));
			valid = false;
		}
		
		if(valid){
			EditFacade editFacade = (EditFacade)FactoryFacade.getFacade(TipoFacade.EDIT);
			
			AsignacionDTO asignacion = new AsignacionDTO();
			
			asignacion.setCliente(selectedCliente);
			asignacion.setVehiculo(selectedVehiculo);
			asignacion.setFechaEntrega(fechaEntrega);
			asignacion.setFechaDevolucion(fechaDevolucion);
			
			try{
				if(id != null && id.length() > 0){
					asignacion.setId(id);
					editFacade.edit(asignacion);
				}else{
					CreateFacade createFacade = (CreateFacade)FactoryFacade.getFacade(TipoFacade.CREATE);
					createFacade.create(asignacion);
					
					selectedVehiculo.setDisponible(false);
					editFacade.edit(selectedVehiculo);
				}	
					
			}catch (CreateObjectException co){
				
			}catch (EditObjectException eo){
				
			}
			
			selectedCliente = null;
			selectedVehiculo = null;
			fechaEntrega = null;
			fechaDevolucion = null;
			id = null;
			refreshGrid();
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('asignacionDialog').hide();");
		}			
	}
	
	public void guardarDevolucion(){
		boolean valid = true;
		
		if(fechaDevolucionEfectiva == null){
			FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo",  "Debe ingresar una fecha de Devolución Efectiva." ));
			valid = false;
		}
		
		if(selectedAsignacion == null){
			FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo",  "Debe seleccionar una Asignación." ));
			valid = false;
		}
		
		if(valid){
			EditFacade editFacade = (EditFacade)FactoryFacade.getFacade(TipoFacade.EDIT);
			
			selectedAsignacion.setFechaDevolucionEfectiva(fechaDevolucionEfectiva);
			try{
				editFacade.edit(selectedAsignacion);
				selectedAsignacion.getVehiculo().setDisponible(true);
				editFacade.edit((VehiculoDTO)selectedAsignacion.getVehiculo());
				
			}catch (EditObjectException eo){
				
			}
			
			refreshGrid();
			
			fechaDevolucionEfectiva = null;
		}
	}
	
	public void onRowSelect(){
		fechaDevolucion = null;
		fechaEntrega = null;
		selectedCliente = null;
		
		if(selectedVehiculo != null){
			if(selectedVehiculo.getDisponible())
				deshabilitarAsignacion = false;
		}
	}
	
	public void filtrar(){
		if(dominio != null && dominio.length() > 0)
			vehiculoFilter.setDominio(dominio);
		
		if(modelo.getCodigo().longValue() != 0)
			vehiculoFilter.setModelo(modelo);
	 	
		refreshGrid();
	}
	
	public void refreshGrid(){
		SearchFacade searchFac = (SearchFacade)FactoryFacade.getFacade(TipoFacade.SEARCH);
		VehiculoDTO vehiculo = new VehiculoDTO();
		
		try{
			vehiculos = searchFac.getByVehiculo(vehiculoFilter);
			clientes = searchFac.getAll(ClienteDTO.class);

			AsignacionDTO asignacion = new AsignacionDTO();
			
			asignaciones = searchFac.getByAsignacion(asignacion, true);		
			
			vehiculosTotal = searchFac.getCountVehiculos(vehiculo, true);
			
			vehiculo.setDisponible(true);
			vehiculosDisponibles = searchFac.getCountVehiculos(vehiculo, false);
			
			vehiculo.setDisponible(false);
			vehiculosAsignados = searchFac.getCountVehiculos(vehiculo, false);
			
		}catch (SearchObjectException so){
			
		}
	}*/
}
