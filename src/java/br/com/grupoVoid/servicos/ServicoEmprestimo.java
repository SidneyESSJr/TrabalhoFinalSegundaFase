package br.com.grupoVoid.servicos;

import br.com.grupoVoid.controle.ControleEmprestimo;
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
@Path("/emprestimo")
public class ServicoEmprestimo {

    @Context
    private UriInfo context;
    
    private ControleEmprestimo controleEmprestimo = new ControleEmprestimo();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTodos() throws SQLException {
        return controleEmprestimo.pegarLista();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarPeloId(@PathParam("id") int id) {
        return controleEmprestimo.pegarEmprestimo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addNovoEmprestimo(String content) {
        controleEmprestimo.addNovoEmprestimo(content);
        return content;
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editarEmprestimoPeloId(@PathParam("id") int id, String content) {
        controleEmprestimo.atualizarEmprestimo(id, content);
        return content;
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletarEmprestimo(@PathParam("id") int id) {
        controleEmprestimo.deletarEmprestimo(id);
    }
}
