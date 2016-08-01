package org.web3.secundario.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.web3.secundario.model.TipoDocumentoDTO;

public class TipoDocumentoDAO extends GenericDAO implements IGenericDAO{
	private static TipoDocumentoDAO instance;
	
	private TipoDocumentoDAO(){
		super();
	};
	
	public static TipoDocumentoDAO getInstance(){
		if(instance == null)
			instance = new TipoDocumentoDAO();
		return instance;
	}
	
	public void create(Object tipoDocumentoDTO) {
        super.saveOrUpdate(tipoDocumentoDTO);
    }
	
	public void update(Object tipoDocumentoDTO) {
        super.saveOrUpdate(tipoDocumentoDTO);
    }
	
	public void delete(Object tipoDocumentoDTO) {
        super.delete(tipoDocumentoDTO);
    }
	
	public List<Object> getAll() {
        return super.getAll("getAllTiposDocumento");
    }
	
	@Override
	public List<Object> getByCriteria(Object dto){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		TipoDocumentoDTO tipoDto = (TipoDocumentoDTO)dto;
		
		if(tipoDto.getDescripcion() != null)
			criterions.add(Restrictions.like("descripcion", tipoDto.getDescripcion()));
		
		
		List<Object> tipos = super.getByCriteria(criterions, TipoDocumentoDTO.class);
		
		return tipos;
	}
}
