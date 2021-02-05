package br.com.grupoVoid.servicos;

import br.com.grupoVoid.controle.ControleLivro;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ssjun
 */
@Path("/livro")
public class ServicoLivro {

    private ControleLivro controleLivro = new ControleLivro();
    
    @Context
    private UriInfo context;
    
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTodos() throws SQLException {
        return controleLivro.pegarLista();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarPeloId(@PathParam("id") int id) throws SQLException {
        return controleLivro.pegarLivro(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addNovoLivro(String body) throws SQLException {
        controleLivro.addNovoLivro(body);
        return body;
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editarLivroPeloId(@PathParam("id") int id, String content) throws SQLException {
        controleLivro.atualizarLivro(id, content);
        return content;
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletarLivro(@PathParam("id") int id) throws SQLException {
        controleLivro.deletarLivro(id);
    }
}
