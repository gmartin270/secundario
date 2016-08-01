package org.web3.secundario.presentation.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.facade.SearchFacade;
import org.web3.secundario.bussiness.facade.TipoFacade;
import org.web3.secundario.bussiness.factory.FactoryFacade;
import org.web3.secundario.model.TipoDocumentoDTO;

@ManagedBean(name="tipoDocumentoService")
@ApplicationScoped
public class TipoDocumentoService {

	private List<Object> tipos;
	
	@PostConstruct
	public void init(){
		SearchFacade sfacade = (SearchFacade)FactoryFacade.getFacade(TipoFacade.SEARCH);
		
		try{
			tipos = (List<Object>)sfacade.getAll(TipoDocumentoDTO.class);
		}catch (SearchObjectException e){
			
		}
	}

	public List<Object> getTipos() {
		return tipos;
	}	
}
