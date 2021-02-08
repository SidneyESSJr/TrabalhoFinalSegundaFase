package br.com.grupoVoid.controle;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.dao.EmprestimoDao;
import br.com.grupoVoid.modelo.Emprestimo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;

/**
 *
 * @author ssjun
 */
public class ControleEmprestimo {
    
    private EmprestimoDao dao = new EmprestimoDao(ConnectionFactory.getConnection());
    private static final Gson GSON = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    
    public String pegarLista() throws SQLException {
        return GSON.toJson(dao.buscarTodos());
    }

    public String pegarEmprestimo(int id) {
        return GSON.toJson(dao.pegarEmprestimo(id));
    }

    public void addNovoEmprestimo(String content) {
        dao.adicionarNovo(GSON.fromJson(content, Emprestimo.class));
    }

    public void atualizarEmprestimo(int id, String content) {
        dao.atualizarEmprestimo(id, GSON.fromJson(content, Emprestimo.class));
    }

    public void deletarEmprestimo(int id) {
        dao.deletarLivro(id);
    }

}
