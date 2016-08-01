package org.web3.secundario.presentation.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.facade.SearchFacade;
import org.web3.secundario.bussiness.facade.TipoFacade;
import org.web3.secundario.bussiness.factory.FactoryFacade;
import org.web3.secundario.model.MateriaDTO;

@ManagedBean(name="materiaService")
@ApplicationScoped
public class MateriaService {

	private List<Object> materias;
	
	@PostConstruct
	public void init(){
		SearchFacade sfacade = (SearchFacade)FactoryFacade.getFacade(TipoFacade.SEARCH);
		
		try{
			materias = (List<Object>)sfacade.getAll(MateriaDTO.class);
		}catch (SearchObjectException e){
			
		}
	}

	public List<Object> getMaterias() {
		return materias;
	}	
}
