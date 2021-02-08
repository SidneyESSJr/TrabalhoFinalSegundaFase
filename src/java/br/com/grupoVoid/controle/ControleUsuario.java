package br.com.grupoVoid.controle;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.dao.UsuarioDao;
import br.com.grupoVoid.modelo.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;

/**
 *
 * @author maryucha
 */
public class ControleUsuario {

    private static final Gson GSON = new Gson();
    private final UsuarioDao dao = new UsuarioDao(ConnectionFactory.getConnection());

    /*----------------------------------------------*/
    public String pegarLista() throws SQLException {
        return GSON.toJson(dao.buscarTodos());
    }

    /*----------------------------------------------*/
    public void addNovoUsuario(String body) throws SQLException {
        Usuario user = GSON.fromJson(body, Usuario.class);
        dao.salvar(user);
    }

    /*----------------------------------------------*/
    public String pegarUsuario(int id) throws SQLException {
        Usuario user = dao.buscarUsuario(id);
        return GSON.toJson(user);
    }

    /*----------------------------------------------*/
    public void atualizarUsuario(int id, String body) throws SQLException {
        pegarUsuario(id);
        Usuario user = GSON.fromJson(body, Usuario.class);
        dao.atualizarUsuario(id, user);
    }

    /*----------------------------------------------*/
    public void deletarUsuario(int id) throws SQLException {
        dao.deletarUsuario(id);
    }
}
