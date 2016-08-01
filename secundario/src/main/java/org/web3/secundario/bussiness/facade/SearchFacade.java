package org.web3.secundario.bussiness.facade;

import java.util.List;




import org.web3.secundario.bussiness.CursoMateriaBO;
//import org.web3.flota.bussiness.AsignacionBO;
import org.web3.secundario.bussiness.UsuarioBO;
//import org.web3.flota.bussiness.VehiculoBO;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.FactoryObjectBO;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.CursoMateriaDTO;
//import org.web3.flota.model.AsignacionDTO;
import org.web3.secundario.model.UsuarioDTO;
//import org.web3.flota.model.VehiculoDTO;

public class SearchFacade {
	private static SearchFacade instance;
	
	public static SearchFacade getInstance(){
		if(instance == null)
			instance = new SearchFacade();
		
		return instance;
	}
	
	public List<Object> getAll(Class<?> clazz) throws SearchObjectException {
		IObjectBO bo = FactoryObjectBO.createObjectBO(clazz);
		
		return bo.getAll();
	}
	
	public List<Object> getByCriteria(Object dto, Class<?> clazz) throws SearchObjectException {
		IObjectBO bo = FactoryObjectBO.createObjectBO(clazz);
		
		return bo.getByCriteria(dto);
	}
	
	public boolean validarUsuario(String login, String password) throws SearchObjectException {
		UsuarioBO bo = (UsuarioBO)FactoryObjectBO.createObjectBO(UsuarioDTO.class);
		
		return bo.validarUsuario(login, password);
	}
	
	public List<Object> getAllByFilter(Object obj){
		CursoMateriaBO bo = (CursoMateriaBO)FactoryObjectBO.createObjectBO(CursoMateriaDTO.class);
		
		return bo.getAllByFilter(obj);
	}
	
	/*public List<Object> getByVehiculo(VehiculoDTO vehiculoFiltro) throws SearchObjectException {
		VehiculoBO bo = (VehiculoBO)FactoryObjectBO.createObjectBO(VehiculoDTO.class);
		
		return bo.getByVehiculo(vehiculoFiltro);
	}
	
	public Long getCountVehiculos(VehiculoDTO vehiculoFiltro, boolean allVehiculos) throws SearchObjectException {
		VehiculoBO bo = (VehiculoBO)FactoryObjectBO.createObjectBO(VehiculoDTO.class);
		
		return bo.getCountVehiculos(vehiculoFiltro, allVehiculos);
	}
	
	public List<Object> getByAsignacion(AsignacionDTO asignacion, boolean fecDevNula) throws SearchObjectException {
		AsignacionBO bo = (AsignacionBO)FactoryObjectBO.createObjectBO(AsignacionDTO.class);
		
		return bo.getByAsignacion(asignacion, fecDevNula);
	}*/
}
