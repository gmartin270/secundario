package org.web3.secundario.persist;

import java.util.List;

public interface IGenericDAO {
	
	public void create(Object objectDTO);
	
	public void update(Object objectDTO);
	
	public void delete(Object objectDTO);
	
	public List<Object> getAll();
	
	public List<Object> getByCriteria(Object dto);
}
