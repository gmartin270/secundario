package org.web3.secundario.presentation;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.facade.SearchFacade;
import org.web3.secundario.bussiness.facade.TipoFacade;
import org.web3.secundario.bussiness.factory.FactoryFacade;

@ManagedBean(name="loginMB")
@ApplicationScoped
public class LoginMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1397930870979414733L;
	private String usuario;
	private String password;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		boolean usuarioValido = false;
		
		System.out.println("log in ...");
		System.out.println(usuario);
		System.out.println(password);
		
		SearchFacade searchFacade = (SearchFacade)FactoryFacade.getFacade(TipoFacade.SEARCH);
		
		try{
			usuarioValido = searchFacade.validarUsuario(usuario, password);
		}catch (SearchObjectException se){
			
		}
		
		if(usuarioValido){		
			System.out.println("logged ...");
			return "home";
		}else{
			FacesContext context = FacesContext.getCurrentInstance();	         
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo",  "Usuario y/o contraseña incorrectos" ));
			return "login";
		}
	}		
}
