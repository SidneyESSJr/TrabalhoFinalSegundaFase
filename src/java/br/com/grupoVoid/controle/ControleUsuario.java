package br.com.grupoVoid.controle;

import br.com.grupoVoid.dao.UsuarioDao;
import br.com.grupoVoid.modelo.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;

/**
 *
 * @author maryucha
 */
public class ControleUsuario {

    static final Gson GSON = new Gson();
    static final UsuarioDao DAO = new UsuarioDao();

    /*----------------------------------------------*/
    static public String pegarLista() throws SQLException {

        return GSON.toJson(DAO.buscarTodos());
    }

    /*----------------------------------------------*/
    static public void addNovoUsuario(String body) throws SQLException {
        Usuario user = GSON.fromJson(body, Usuario.class);
        DAO.salvar(user);
    }

    /*----------------------------------------------*/
    static public String pegarUsuario(int id) throws SQLException {

        Usuario user = DAO.buscarUsuario(id);

        return GSON.toJson(user);
    }

    /*----------------------------------------------*/
    static public void atualizarUsuario(int id, String body) throws SQLException {
        pegarUsuario(id);
        Usuario user = GSON.fromJson(body, Usuario.class);

        DAO.atualizarUsuario(id, user);
    }

    /*----------------------------------------------*/
    static public void deletarUsuario(int id) throws SQLException {

        DAO.deletarUsuario(id);
    }
}
