package org.web3.secundario.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.web3.secundario.model.CursoDTO;

public class CursoDAO extends GenericDAO implements IGenericDAO{
	private static CursoDAO instance;
	
	private CursoDAO(){
		super();
	};
	
	public static CursoDAO getInstance(){
		if(instance == null)
			instance = new CursoDAO();
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
        return super.getAll("getAllCursos");
    }
	
	@Override
	public List<Object> getByCriteria(Object obj){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		CursoDTO dto = (CursoDTO)obj;
		
		if(dto.getNombre() != null)
			criterions.add(Restrictions.like("nombre", "%" + dto.getNombre() + "%"));
		
		List<Object> lista = super.getByCriteria(criterions, CursoDTO.class);
		
		return lista;
	}
}
