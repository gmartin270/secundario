package org.web3.secundario.bussiness.factory;

import org.web3.secundario.bussiness.facade.CreateFacade;
import org.web3.secundario.bussiness.facade.EditFacade;
import org.web3.secundario.bussiness.facade.TipoFacade;
import org.web3.secundario.bussiness.facade.RemoveFacade;
import org.web3.secundario.bussiness.facade.SearchFacade;

public class FactoryFacade {

	public static Object getFacade(TipoFacade tipo){
		Object facade = null;

		switch(tipo){
			case CREATE:
				facade = (Object)CreateFacade.getInstance();
				break;
			case EDIT:
				facade = (Object)EditFacade.getInstance();
				break;
			case REMOVE:
				facade = (Object)RemoveFacade.getInstance();
				break;
			case SEARCH:
				facade = (Object)SearchFacade.getInstance();
				break;
			default:
				facade = null;
		}
		
		return facade;
	}
}
