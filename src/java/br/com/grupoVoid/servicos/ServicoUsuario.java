package br.com.grupoVoid.servicos;

import br.com.grupoVoid.controle.ControleUsuario;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
public class ServicoUsuario {

    @Context
    private UriInfo context;

    private ControleUsuario controleUsuario = new ControleUsuario();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTodos() throws SQLException {
        return controleUsuario.pegarLista();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String buscarPeloId(@PathParam("id") int id) throws SQLException {
        return controleUsuario.pegarUsuario(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addNovo(String body) throws SQLException {
        controleUsuario.addNovoUsuario(body);
        return body;
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editarUsuarioPeloId(@PathParam("id") int id, String content) throws SQLException {

        controleUsuario.atualizarUsuario(id, content);
        return content;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void deletarUsuario(@PathParam("id") int id) throws SQLException {
        controleUsuario.deletarUsuario(id);

    }

}
