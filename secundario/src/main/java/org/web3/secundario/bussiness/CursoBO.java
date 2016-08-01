package org.web3.secundario.bussiness;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.CursoDTO;
import org.web3.secundario.persist.CursoDAO;

public class CursoBO implements IObjectBO {
	
	private static CursoBO instance;
	private CursoDAO dao;
	
	private CursoBO(){
		dao = CursoDAO.getInstance();
	};
	
	public static CursoBO getInstance(){
		if(instance == null)
			instance = new CursoBO();
		
		return instance;
	}
	
	public void createObject(Object dto) throws CreateObjectException{
		String validacion = validate((CursoDTO)dto);
		
		if(validacion.length() != 0)
			throw new CreateObjectException(validacion);
		
		dao.create(dto);
	}
	
	public void editObject(Object dto) throws EditObjectException{
		String validacion = validate((CursoDTO)dto);
		
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
	
	private String validate(CursoDTO dto){
		String result = "";
		
		if(dto.getNombre().length() == 0)
			result += "El nombre del curso es nulo.\n" ;
		
		return result;
	}	
}
