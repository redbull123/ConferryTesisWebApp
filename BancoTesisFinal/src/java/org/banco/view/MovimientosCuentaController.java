package org.banco.view;

import org.banco.model.MovimientosCuenta;
import org.banco.view.util.JsfUtil;
import org.banco.view.util.JsfUtil.PersistAction;
import org.banco.controller.MovimientosCuentaFacade;

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

@Named("movimientosCuentaController")
@SessionScoped
public class MovimientosCuentaController implements Serializable {

    @EJB
    private org.banco.controller.MovimientosCuentaFacade ejbFacade;
    private List<MovimientosCuenta> items = null;
    private MovimientosCuenta selected;

    public MovimientosCuentaController() {
    }

    public MovimientosCuenta getSelected() {
        return selected;
    }

    public void setSelected(MovimientosCuenta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MovimientosCuentaFacade getFacade() {
        return ejbFacade;
    }

    public MovimientosCuenta prepareCreate() {
        selected = new MovimientosCuenta();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MovimientosCuentaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MovimientosCuentaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MovimientosCuentaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MovimientosCuenta> getItems() {
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

    public MovimientosCuenta getMovimientosCuenta(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MovimientosCuenta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MovimientosCuenta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MovimientosCuenta.class)
    public static class MovimientosCuentaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovimientosCuentaController controller = (MovimientosCuentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movimientosCuentaController");
            return controller.getMovimientosCuenta(getKey(value));
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
            if (object instanceof MovimientosCuenta) {
                MovimientosCuenta o = (MovimientosCuenta) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovimientosCuenta.class.getName()});
                return null;
            }
        }

    }

}
