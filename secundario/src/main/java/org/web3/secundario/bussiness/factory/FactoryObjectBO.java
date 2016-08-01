package org.web3.secundario.bussiness.factory;

import org.web3.secundario.bussiness.CursoBO;
import org.web3.secundario.bussiness.CursoMateriaBO;
import org.web3.secundario.bussiness.DocenteBO;
import org.web3.secundario.bussiness.MateriaBO;
import org.web3.secundario.bussiness.PaisBO;
import org.web3.secundario.bussiness.TipoDocumentoBO;
import org.web3.secundario.model.CursoDTO;
import org.web3.secundario.model.CursoMateriaDTO;
import org.web3.secundario.model.DocenteDTO;
import org.web3.secundario.model.MateriaDTO;
import org.web3.secundario.model.PaisDTO;
import org.web3.secundario.model.TipoDocumentoDTO;
import org.web3.secundario.bussiness.AlumnoBO;
import org.web3.secundario.bussiness.UsuarioBO;
import org.web3.secundario.model.AlumnoDTO;
import org.web3.secundario.model.UsuarioDTO;

public class FactoryObjectBO {
	
	public static IObjectBO createObjectBO(Object objetoDTO) {
		IObjectBO objectBO = null;
		
		if(objetoDTO instanceof UsuarioDTO)
			objectBO = UsuarioBO.getInstance();
		else if(objetoDTO instanceof AlumnoDTO)
			objectBO = AlumnoBO.getInstance();
		else if(objetoDTO instanceof PaisDTO)
			objectBO = PaisBO.getInstance();
		else if(objetoDTO instanceof TipoDocumentoDTO)
			objectBO = TipoDocumentoBO.getInstance();
		else if(objetoDTO instanceof DocenteDTO)
			objectBO = DocenteBO.getInstance();
		else if(objetoDTO instanceof CursoDTO)
			objectBO = CursoBO.getInstance();
		else if(objetoDTO instanceof MateriaDTO)
			objectBO = MateriaBO.getInstance();
		else if(objetoDTO instanceof CursoMateriaDTO)
			objectBO = CursoMateriaBO.getInstance();
		
		return objectBO;		
	}
	
	public static IObjectBO createObjectBO(Class<?> clazz) {
		IObjectBO objectBO = null;
		
		if(clazz.equals(UsuarioDTO.class))
			objectBO = UsuarioBO.getInstance();
		else if(clazz.equals(AlumnoDTO.class))
			objectBO = AlumnoBO.getInstance();
		else if(clazz.equals(PaisDTO.class))
			objectBO = PaisBO.getInstance();
		else if(clazz.equals(TipoDocumentoDTO.class))
			objectBO = TipoDocumentoBO.getInstance();
		else if(clazz.equals(DocenteDTO.class))
			objectBO = DocenteBO.getInstance();
		else if(clazz.equals(CursoDTO.class))
			objectBO = CursoBO.getInstance();
		else if(clazz.equals(MateriaDTO.class))
			objectBO = MateriaBO.getInstance();
		else if(clazz.equals(CursoMateriaDTO.class))
			objectBO = CursoMateriaBO.getInstance();
		
		return objectBO;		
	}
}
