package org.web3.secundario.bussiness.facade;

import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.factory.FactoryObjectBO;
import org.web3.secundario.bussiness.factory.IObjectBO;

public class EditFacade {
	
	private static EditFacade instance;
	
	private EditFacade(){};
	
	public static EditFacade getInstance(){
		
		if(instance == null)
			instance = new EditFacade();
		
		return instance;
	}
	
	public void edit(Object objetoDTO) throws EditObjectException{
		IObjectBO bo = FactoryObjectBO.createObjectBO(objetoDTO);
		
		bo.editObject(objetoDTO);
	}	
}
