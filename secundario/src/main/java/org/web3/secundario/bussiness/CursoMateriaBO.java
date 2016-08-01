package org.web3.secundario.bussiness;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.CursoMateriaDTO;
import org.web3.secundario.persist.CursoMateriaDAO;

public class CursoMateriaBO implements IObjectBO {
	
	private static CursoMateriaBO instance;
	private CursoMateriaDAO dao;
	
	private CursoMateriaBO(){
		dao = CursoMateriaDAO.getInstance();
	};
	
	public static CursoMateriaBO getInstance(){
		if(instance == null)
			instance = new CursoMateriaBO();
		
		return instance;
	}
	
	public void createObject(Object dto) throws CreateObjectException{
		String validacion = validate((CursoMateriaDTO)dto);
		
		if(validacion.length() != 0)
			throw new CreateObjectException(validacion);
		
		dao.create(dto);
	}
	
	public void editObject(Object dto) throws EditObjectException{
		String validacion = validate((CursoMateriaDTO)dto);
		
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
	
	private String validate(CursoMateriaDTO dto){
		String result = "";
		
		if(dto.getCurso() == null)
			result += "El curso es nulo.\n" ;
		
		if(dto.getMateria() == null)
			result += "La materia es nula.\n";
		
		return result;
	}	
	
	public List<Object> getAllByFilter(Object obj){
		return dao.getAllByFilter(obj);
	}
}
