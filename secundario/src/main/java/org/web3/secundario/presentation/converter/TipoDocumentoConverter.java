package org.web3.secundario.presentation.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.web3.secundario.model.TipoDocumentoDTO;
import org.web3.secundario.presentation.service.TipoDocumentoService;

@FacesConverter("TipoDocumentoConverter")
public class TipoDocumentoConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Object item = null;
		
        if(value != null && value.trim().length() > 0) {
            try {
                TipoDocumentoService service = (TipoDocumentoService) fc.getExternalContext().getApplicationMap().get("tipoDocumentoService");
                
                for(int i=0; i<service.getTipos().size(); i++){
                	if(((TipoDocumentoDTO)service.getTipos().get(i)).getId().equals(value)){
                		item = service.getTipos().get(i);
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
            return String.valueOf(((TipoDocumentoDTO) object).getId());
        }
        else {
            return null;
        }
    }   
}
