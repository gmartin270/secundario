package org.web3.secundario.bussiness.facade;

import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.factory.FactoryObjectBO;
import org.web3.secundario.bussiness.factory.IObjectBO;

public class RemoveFacade {
	
	private static RemoveFacade instance;
	
	private RemoveFacade(){};
	
	public static RemoveFacade getInstance(){
		
		if(instance == null)
			instance = new RemoveFacade();
		
		return instance;
	}
	
	public void remove(Object objetoDTO) throws RemoveObjectException{
		IObjectBO bo = FactoryObjectBO.createObjectBO(objetoDTO);
		
		bo.removeObject(objetoDTO);
	}	
}
