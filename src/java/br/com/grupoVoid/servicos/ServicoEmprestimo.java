package br.com.grupoVoid.servicos;

import br.com.grupoVoid.controle.ControleEmprestimo;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    
    private final ControleEmprestimo controleEmprestimo = new ControleEmprestimo();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTodos() throws SQLException {
        return controleEmprestimo.listarEmprestimos();
    }

    @POST
    @Path("/alugar/{idLivro}/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alugarLivro(@PathParam("idLivro") Integer idLivro, @PathParam("idUsuario") Integer idUsuario, String content) {
        controleEmprestimo.alugarLivro(idLivro, idUsuario, content);
    }
    
    @POST
    @Path("/devolver{idLivro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void devolverLivro(@PathParam("idLivro") Integer idLivro) {
        controleEmprestimo.devolverLivro(idLivro);
    }

}
