package org.web3.secundario.bussiness;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.TipoDocumentoDTO;
import org.web3.secundario.persist.FactoryObjectDAO;
import org.web3.secundario.persist.IGenericDAO;

public class TipoDocumentoBO implements IObjectBO{
	private static TipoDocumentoBO instance;
	private IGenericDAO dao;
	
	private TipoDocumentoBO(){
		dao = FactoryObjectDAO.createObjectDAO(TipoDocumentoDTO.class);
	};
	
	public static TipoDocumentoBO getInstance(){
		if(instance == null)
			instance = new TipoDocumentoBO();
		
		return instance;
	}
	
	public List<Object> getAll() throws SearchObjectException{
		return dao.getAll();
	}
	
	@Override
	public List<Object> getByCriteria(Object object) throws SearchObjectException{
		return dao.getByCriteria(object);
	}
	
	public void createObject(Object tipoDocumentoDTO) throws CreateObjectException{
		dao.create(tipoDocumentoDTO);
	}
	
	public void editObject( Object tipoDocumentoDTO) throws EditObjectException{
		
		if(((TipoDocumentoDTO)tipoDocumentoDTO).getId() != null)
			dao.update(tipoDocumentoDTO);
		else
			throw new EditObjectException("");
	}
	
	public void removeObject(Object tipoDocumentoDTO) throws RemoveObjectException{
		if(((TipoDocumentoDTO)tipoDocumentoDTO).getId() != null)
			dao.delete(tipoDocumentoDTO);
		else
			throw new RemoveObjectException("");
	}
}
