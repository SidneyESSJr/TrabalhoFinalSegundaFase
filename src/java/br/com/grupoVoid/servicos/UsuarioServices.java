package br.com.grupoVoid.servicos;

import br.com.grupoVoid.controle.ControleUsuario;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author maryucha
 */
@Path("/user")
public class UsuarioServices {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioServices
     */
    public UsuarioServices() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTodos() throws SQLException {
        return ControleUsuario.pegarLista();

    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String buscarPeloId(@PathParam("id") int id) throws SQLException{
        return ControleUsuario.pegarUsuario(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addNovo(String body) throws SQLException {
        ControleUsuario.addNovoUsuario(body);
        return body;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String editarUsuarioPeloId(@PathParam("id") int id, String content) throws SQLException {

        ControleUsuario.atualizarUsuario(id, content);
        return content;
    }

}
