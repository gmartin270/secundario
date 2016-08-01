package org.web3.secundario.bussiness.factory;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;

public interface IObjectBO {
	
	public List<Object> getAll() throws SearchObjectException;
	
	public void createObject(Object dto) throws CreateObjectException;
	
	public void editObject(Object dto) throws EditObjectException;
	
	public void removeObject(Object dto) throws RemoveObjectException;
	
	public List<Object> getByCriteria(Object dto) throws SearchObjectException;
}
