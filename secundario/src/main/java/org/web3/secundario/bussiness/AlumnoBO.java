package org.web3.secundario.bussiness;

import java.util.List;

import org.web3.secundario.bussiness.exception.CreateObjectException;
import org.web3.secundario.bussiness.exception.EditObjectException;
import org.web3.secundario.bussiness.exception.RemoveObjectException;
import org.web3.secundario.bussiness.exception.SearchObjectException;
import org.web3.secundario.bussiness.factory.IObjectBO;
import org.web3.secundario.model.AlumnoDTO;
import org.web3.secundario.persist.AlumnoDAO;

public class AlumnoBO implements IObjectBO {
	
	private static AlumnoBO instance;
	private AlumnoDAO alumnoDAO;
	
	private AlumnoBO(){
		alumnoDAO = AlumnoDAO.getInstance();
	};
	
	public static AlumnoBO getInstance(){
		if(instance == null)
			instance = new AlumnoBO();
		
		return instance;
	}
	
	public void createObject(Object alumnoDTO) throws CreateObjectException{
		String validacion = validate((AlumnoDTO)alumnoDTO);
		
		if(validacion.length() != 0)
			throw new CreateObjectException(validacion);
		
		alumnoDAO.create(alumnoDTO);
	}
	
	public void editObject(Object alumnoDTO) throws EditObjectException{
		String validacion = validate((AlumnoDTO)alumnoDTO);
		
		if(validacion.length() != 0)
			throw new EditObjectException(validacion);
		
		alumnoDAO.update(alumnoDTO);
	}
	
	public void removeObject(Object alumnoDTO) throws RemoveObjectException{
		alumnoDAO.delete(alumnoDTO);
	}
	
	public List<Object> getAll() throws SearchObjectException{
		return alumnoDAO.getAll();
	}
	
	@Override
	public List<Object> getByCriteria(Object object) throws SearchObjectException{
		return alumnoDAO.getByCriteria(object);
	}
	
	private String validate(AlumnoDTO alumno){
		String result = "";
		
		if(alumno.getApellido().length() == 0)
			result += "El valor de apellido es nulo.\n" ;
		
		if(alumno.getNombres().length() == 0)
			result += "El valor para el nombre es nulo.\n";
		
		if(alumno.getNroDocumento().length() == 0)
			result += "El valor de nro. de documento es nulo.\n";
		
		return result;
	}

	
}
