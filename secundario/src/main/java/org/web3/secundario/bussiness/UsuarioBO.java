package org.web3.secundario.bussiness;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.UsuarioDTO;
import org.web3.secundario.persist.UsuarioDAO;

public class UsuarioBO implements IObjectBO {
	
	private static UsuarioBO instance;
	private UsuarioDAO dao;
	
	private UsuarioBO(){
		dao = UsuarioDAO.getInstance();
	};
	
	public static UsuarioBO getInstance(){
		if(instance == null)
			instance = new UsuarioBO();
		
		return instance;
	}
	
	public void createObject(Object objectDTO) throws CreateObjectException{
		dao.create(objectDTO);
	}
	
	public void editObject(Object clienteDTO) throws EditObjectException{
		dao.update(clienteDTO);
	}
	
	public void removeObject(Object clienteDTO) throws RemoveObjectException{
		dao.delete(clienteDTO);
	}
	
	public List<Object> getAll() throws SearchObjectException{
		return dao.getAll();
	}
	
	@Override
	public List<Object> getByCriteria(Object object) throws SearchObjectException{
		return dao.getByCriteria(object);
	}
	
	public boolean validarUsuario(String login, String password) throws SearchObjectException{
		boolean result = false;
		
		
		UsuarioDTO usuario = (UsuarioDTO) dao.getByLogin(login);
		
		if(usuario != null){
			if(usuario.getPassword().equalsIgnoreCase(password))
				result = true;
		}
		
		return result;
	}
}
