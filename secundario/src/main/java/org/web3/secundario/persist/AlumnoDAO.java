package org.web3.secundario.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.web3.secundario.model.AlumnoDTO;

public class AlumnoDAO extends GenericDAO implements IGenericDAO{
	private static AlumnoDAO instance;
	
	private AlumnoDAO(){
		super();
	};
	
	public static AlumnoDAO getInstance(){
		if(instance == null)
			instance = new AlumnoDAO();
		return instance;
	}
	
	public void create(Object alumnoDTO) {
        super.saveOrUpdate(alumnoDTO);
    }
	
	public void update(Object alumnoDTO) {
        super.saveOrUpdate(alumnoDTO);
    }
	
	public void delete(Object alumnoDTO) {
        super.delete(alumnoDTO);
    }
	
	public List<Object> getAll() {
        return super.getAll("getAllAlumnos");
    }
	
	@Override
	public List<Object> getByCriteria(Object alumno){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		AlumnoDTO alumnoDto = (AlumnoDTO)alumno;
		
		if(alumnoDto.getNroDocumento() != null)
			criterions.add(Restrictions.eq("nroDocumento", alumnoDto.getNroDocumento()));
		
		if(alumnoDto.getApellido() != null)
			criterions.add(Restrictions.like("apellido", "%" + alumnoDto.getApellido() + "%"));
		
		List<Object> alumnos = super.getByCriteria(criterions, AlumnoDTO.class);
		
		return alumnos;
	}
}
