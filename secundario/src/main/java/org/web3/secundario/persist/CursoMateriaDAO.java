package org.web3.secundario.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.web3.secundario.util.HibernateUtil;
import org.web3.secundario.model.CursoDTO;
import org.web3.secundario.model.CursoMateriaDTO;

public class CursoMateriaDAO extends GenericDAO implements IGenericDAO{
	private static CursoMateriaDAO instance;
	
	private CursoMateriaDAO(){
		super();
	};
	
	public static CursoMateriaDAO getInstance(){
		if(instance == null)
			instance = new CursoMateriaDAO();
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
	
	@SuppressWarnings("unchecked")
	public List<Object> getAll() {
        return super.getAll("getAllCursosMaterias");
    }
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllByFilter(Object obj) {
        List<Object> dtos = null;
		CursoMateriaDTO dto = (CursoMateriaDTO)obj;
		
		try {
            startOperation();
            Query query = session.getNamedQuery("getAllCursosMaterias");
            
            if(dto.getCurso() != null)
            	query.setParameter("idCurso", dto.getCurso().getId());
            
            dtos = (List<Object>)query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	HibernateUtil.close(session);
        }
        return dtos;
    }
	
	@Override
	public List<Object> getByCriteria(Object obj){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		CursoMateriaDTO dto = (CursoMateriaDTO)obj;
		
		if(dto.getCurso() != null)
			criterions.add(Restrictions.eq("curso", dto.getCurso()));
		
		if(dto.getMateria() != null)
			criterions.add(Restrictions.eq("materia", dto.getMateria()));
		
		List<Object> lista = super.getByCriteria(criterions, CursoDTO.class);
		
		return lista;
	}
}
