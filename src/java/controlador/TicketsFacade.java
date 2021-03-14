/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Tickets;

/**
 *
 * @author SERVER
 */
@Stateless
public class TicketsFacade extends AbstractFacade<Tickets> {

    @PersistenceContext(unitName = "Proyecto_CelerityPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketsFacade() {
        super(Tickets.class);
    }
 
    
    public List<Tickets> filtrar(String filtro){
        Query q;
        if(filtro.equals("Todos")){
            q = em.createNativeQuery("Select * FROM tickets;", Tickets.class);
        }else{
            q = em.createNativeQuery("Select * FROM tickets WHERE estado = '"+filtro+"';", Tickets.class);
        }
        List<Tickets>lista = q.getResultList();
        return lista;
    }
}
