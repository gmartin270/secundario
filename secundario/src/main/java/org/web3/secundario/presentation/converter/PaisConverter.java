package org.web3.secundario.presentation.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.web3.secundario.model.PaisDTO;
import org.web3.secundario.presentation.service.PaisService;

@FacesConverter("PaisConverter")
public class PaisConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Object item = null;
		
        if(value != null && value.trim().length() > 0) {
            try {
                PaisService service = (PaisService) fc.getExternalContext().getApplicationMap().get("paisService");
                
                for(int i=0; i<service.getPaises().size(); i++){
                	if(((PaisDTO)service.getPaises().get(i)).getId().equals(value)){
                		item = service.getPaises().get(i);
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
            return String.valueOf(((PaisDTO) object).getId());
        }
        else {
            return null;
        }
    }   
}
