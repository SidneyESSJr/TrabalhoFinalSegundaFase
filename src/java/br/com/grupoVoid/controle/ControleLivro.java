package br.com.grupoVoid.controle;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.dao.LivroDao;
import br.com.grupoVoid.modelo.Livro;
import com.google.gson.Gson;
import java.sql.SQLException;

/**
 *
 * @author ssjun
 */
public class ControleLivro {

    private static final Gson GSON = new Gson();
    private LivroDao DAO = new LivroDao(ConnectionFactory.getConnection());

    public  void addNovoLivro(String body) throws SQLException {
        Livro livro = GSON.fromJson(body, Livro.class);
        DAO.salvar(livro);
    }

    public  String pegarLista() throws SQLException {
        return GSON.toJson(DAO.buscarTodos());
    }

    public  String pegarLivro(int id) throws SQLException {
        return GSON.toJson(DAO.pegarLivro(id));
    }

    public  void atualizarLivro(int id, String content) throws SQLException {
        DAO.atualizarLivro(id, GSON.fromJson(content, Livro.class));
    }

    public  void deletarLivro(int id) throws SQLException {
        DAO.deletarLivro(id);
    }
   
    
}
