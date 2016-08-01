package org.web3.secundario.presentation.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.web3.secundario.model.MateriaDTO;
import org.web3.secundario.presentation.service.MateriaService;

@FacesConverter("MateriaConverter")
public class MateriaConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Object item = null;
		
        if(value != null && value.trim().length() > 0) {
            try {
                MateriaService service = (MateriaService) fc.getExternalContext().getApplicationMap().get("materiaService");
                
                for(int i=0; i<service.getMaterias().size(); i++){
                	if(((MateriaDTO)service.getMaterias().get(i)).getId().equals(value)){
                		item = service.getMaterias().get(i);
                		break;
                	}
                }
                
                return item;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((MateriaDTO) object).getId());
        }
        else {
            return null;
        }
    }   
}
