package org.web3.secundario.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.web3.secundario.model.AlumnoDTO;
import org.web3.secundario.model.PaisDTO;

public class PaisDAO extends GenericDAO implements IGenericDAO{
	private static PaisDAO instance;
	
	private PaisDAO(){
		super();
	};
	
	public static PaisDAO getInstance(){
		if(instance == null)
			instance = new PaisDAO();
		return instance;
	}
	
	public void create(Object paisDTO) {
        super.saveOrUpdate(paisDTO);
    }
	
	public void update(Object paisDTO) {
        super.saveOrUpdate(paisDTO);
    }
	
	public void delete(Object paisDTO) {
        super.delete(paisDTO);
    }
	
	public List<Object> getAll() {
        return super.getAll("getAllPaises");
    }
	
	@Override
	public List<Object> getByCriteria(Object dto){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		PaisDTO paisDto = (PaisDTO)dto;
		
		if(paisDto.getNombre() != null)
			criterions.add(Restrictions.like("nombre", paisDto.getNombre()));		
		
		List<Object> paises = super.getByCriteria(criterions, PaisDTO.class);
		
		return paises;
	};
}
