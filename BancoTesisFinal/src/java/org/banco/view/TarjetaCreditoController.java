package org.banco.view;

import org.banco.model.TarjetaCredito;
import org.banco.view.util.JsfUtil;
import org.banco.view.util.JsfUtil.PersistAction;
import org.banco.controller.TarjetaCreditoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("tarjetaCreditoController")
@SessionScoped
public class TarjetaCreditoController implements Serializable {

    @EJB
    private org.banco.controller.TarjetaCreditoFacade ejbFacade;
    private List<TarjetaCredito> items = null;
    private TarjetaCredito selected;

    public TarjetaCreditoController() {
    }

    public TarjetaCredito getSelected() {
        return selected;
    }

    public void setSelected(TarjetaCredito selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TarjetaCreditoFacade getFacade() {
        return ejbFacade;
    }

    public TarjetaCredito prepareCreate() {
        selected = new TarjetaCredito();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TarjetaCreditoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TarjetaCreditoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TarjetaCreditoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TarjetaCredito> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TarjetaCredito getTarjetaCredito(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TarjetaCredito> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TarjetaCredito> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TarjetaCredito.class)
    public static class TarjetaCreditoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TarjetaCreditoController controller = (TarjetaCreditoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tarjetaCreditoController");
            return controller.getTarjetaCredito(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TarjetaCredito) {
                TarjetaCredito o = (TarjetaCredito) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TarjetaCredito.class.getName()});
                return null;
            }
        }

    }

}
