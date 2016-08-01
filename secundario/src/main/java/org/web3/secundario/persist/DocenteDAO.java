package org.web3.secundario.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.web3.secundario.model.DocenteDTO;

public class DocenteDAO extends GenericDAO implements IGenericDAO{
	private static DocenteDAO instance;
	
	private DocenteDAO(){
		super();
	};
	
	public static DocenteDAO getInstance(){
		if(instance == null)
			instance = new DocenteDAO();
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
        return super.getAll("getAllDocentes");
    }
	
	@Override
	public List<Object> getByCriteria(Object obj){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		DocenteDTO dto = (DocenteDTO)obj;
		
		if(dto.getNroDocumento() != null)
			criterions.add(Restrictions.eq("nroDocumento", dto.getNroDocumento()));
		
		if(dto.getApellido() != null)
			criterions.add(Restrictions.like("apellido", "%" + dto.getApellido() + "%"));
		
		List<Object> docentes = super.getByCriteria(criterions, DocenteDTO.class);
		
		return docentes;
	}
}
