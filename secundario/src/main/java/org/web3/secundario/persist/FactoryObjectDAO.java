package org.web3.secundario.persist;

import org.web3.secundario.model.PaisDTO;
import org.web3.secundario.model.TipoDocumentoDTO;

public class FactoryObjectDAO {

	public static IGenericDAO createObjectDAO(Class<?> clazz) {

		IGenericDAO dao = null;
		
		if(clazz.equals(PaisDTO.class))
			dao = PaisDAO.getInstance();
		else if(clazz.equals(TipoDocumentoDTO.class))
			dao = TipoDocumentoDAO.getInstance();
		
		return dao;
	}
}
