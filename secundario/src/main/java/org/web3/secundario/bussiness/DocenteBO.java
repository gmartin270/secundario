package org.web3.secundario.bussiness;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.DocenteDTO;
import org.web3.secundario.persist.DocenteDAO;

public class DocenteBO implements IObjectBO {
	
	private static DocenteBO instance;
	private DocenteDAO dao;
	
	private DocenteBO(){
		dao = DocenteDAO.getInstance();
	};
	
	public static DocenteBO getInstance(){
		if(instance == null)
			instance = new DocenteBO();
		
		return instance;
	}
	
	public void createObject(Object dto) throws CreateObjectException{
		String validacion = validate((DocenteDTO)dto);
		
		if(validacion.length() != 0)
			throw new CreateObjectException(validacion);
		
		dao.create(dto);
	}
	
	public void editObject(Object dto) throws EditObjectException{
		String validacion = validate((DocenteDTO)dto);
		
		if(validacion.length() != 0)
			throw new EditObjectException(validacion);
		
		dao.update(dto);
	}
	
	public void removeObject(Object dto) throws RemoveObjectException{
		dao.delete(dto);
	}
	
	public List<Object> getAll() throws SearchObjectException{
		return dao.getAll();
	}
	
	@Override
	public List<Object> getByCriteria(Object object) throws SearchObjectException{
		return dao.getByCriteria(object);
	}
	
	private String validate(DocenteDTO alumno){
		String result = "";
		
		if(alumno.getApellido().length() == 0)
			result += "El valor de apellido es nulo.\n" ;
		
		if(alumno.getNombres().length() == 0)
			result += "El valor para el nombre es nulo.\n";
		
		if(alumno.getNroDocumento().length() == 0)
			result += "El valor de nro. de documento es nulo.\n";
		
		return result;
	}	
}
