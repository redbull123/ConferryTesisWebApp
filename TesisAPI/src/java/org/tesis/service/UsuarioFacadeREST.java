package org.tesis.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.tesis.controller.UsuarioFacade;
import org.tesis.model.Usuario;

@Stateless
@Path("usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @EJB
    private UsuarioFacade usuarioFacade;

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("finduser/{usuario}/")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario findUser(@PathParam("usuario") String user) {
        
        Usuario hasUser= usuarioFacade.findByUsuario(user);
        return hasUser;
    }
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {        
        return super.findAll();
    }


    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean createUser(Usuario user) {
     boolean r = false;
       
        if(!(usuarioFacade.findByUsuarioname(user.getUsuario())) && !(usuarioFacade.findByCI(user.getCi()))){
        create(user);
        r = true;
        }
        return r;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
