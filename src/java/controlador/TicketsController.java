package controlador;

import modelo.Tickets;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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

@Named("ticketsController")
@SessionScoped
public class TicketsController implements Serializable {

    @EJB
    private controlador.TicketsFacade ejbFacade;
    private List<Tickets> items = null;
    private Tickets selected;
    private String filtro;
    private Calendar fechaActual = Calendar.getInstance();
    
    
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public TicketsController() {
    }

    public Tickets getSelected() {
        return selected;
    }

    public void setSelected(Tickets selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TicketsFacade getFacade() {
        return ejbFacade;
    }

    public Tickets prepareCreate() {
        selected = new Tickets();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TicketsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TicketsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TicketsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tickets> getItems() {
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

    public Tickets getTickets(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Tickets> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tickets> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tickets.class)
    public static class TicketsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TicketsController controller = (TicketsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ticketsController");
            return controller.getTickets(getKey(value));
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
            if (object instanceof Tickets) {
                Tickets o = (Tickets) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tickets.class.getName()});
                return null;
            }
        }

    }
     public List<Tickets> filtrar() {
        items = getFacade().filtrar(this.filtro);
        return items;
    }
     
     public String getDate(){
        String dia,mes,anio,fecha;
        dia = Integer.toString(fechaActual.get(Calendar.DATE));
        mes = Integer.toString(fechaActual.get(Calendar.MONTH));
        anio = Integer.toString(fechaActual.get(Calendar.YEAR));
        
        if(fechaActual.get(Calendar.DATE) < 10){
            dia = "0"+dia;
        }
        if(fechaActual.get(Calendar.MONTH) < 10){
            mes = "0"+mes;
        }
        
        fecha = dia+"/"+mes+"/"+anio;
        
        selected.setFecha(new Date());
        
        return fecha;
     }

}
