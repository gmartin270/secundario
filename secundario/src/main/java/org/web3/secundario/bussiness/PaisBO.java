package org.web3.secundario.bussiness;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.PaisDTO;
import org.web3.secundario.persist.FactoryObjectDAO;
import org.web3.secundario.persist.IGenericDAO;

public class PaisBO implements IObjectBO{
	private static PaisBO instance;
	private IGenericDAO dao;
	
	private PaisBO(){
		dao = FactoryObjectDAO.createObjectDAO(PaisDTO.class);
	};
	
	public static PaisBO getInstance(){
		if(instance == null)
			instance = new PaisBO();
		
		return instance;
	}
	
	public List<Object> getAll() throws SearchObjectException{
		return dao.getAll();
	}
	
	@Override
	public List<Object> getByCriteria(Object object) throws SearchObjectException{
		return dao.getByCriteria(object);
	}
	
	public void createObject(Object paisDTO) throws CreateObjectException{
		dao.create(paisDTO);
	}
	
	public void editObject( Object paisDTO) throws EditObjectException{
		
		if(((PaisDTO)paisDTO).getId() != null)
			dao.update(paisDTO);
		else
			throw new EditObjectException("");
	}
	
	public void removeObject(Object paisDTO) throws RemoveObjectException{
		if(((PaisDTO)paisDTO).getId() != null)
			dao.delete(paisDTO);
		else
			throw new RemoveObjectException("");
	}
}
