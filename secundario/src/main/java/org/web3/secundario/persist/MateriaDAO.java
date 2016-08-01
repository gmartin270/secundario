package org.web3.secundario.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.web3.secundario.model.MateriaDTO;

public class MateriaDAO extends GenericDAO implements IGenericDAO{
	private static MateriaDAO instance;
	
	private MateriaDAO(){
		super();
	};
	
	public static MateriaDAO getInstance(){
		if(instance == null)
			instance = new MateriaDAO();
		return instance;
	}
	
	public void create(Object dto) {
        super.saveOrUpdate(dto);
    }
	
	public void update(Object dto) {
        super.saveOrUpdate(dto);
    }
	
	public void delete(Object dto) {
        super.delete(dto);
    }
	
	public List<Object> getAll() {
        return super.getAll("getAllMaterias");
    }
	
	@Override
	public List<Object> getByCriteria(Object obj){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		MateriaDTO dto = (MateriaDTO)obj;
		
		if(dto.getNombre() != null)
			criterions.add(Restrictions.like("nombre", "%" + dto.getNombre() + "%"));
		
		List<Object> lista = super.getByCriteria(criterions, MateriaDTO.class);
		
		return lista;
	}
}
