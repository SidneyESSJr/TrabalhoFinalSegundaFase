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

    /*----------------------------------------------*/
    static public String pegarLista() throws SQLException {
        UsuarioDao dao = new UsuarioDao();
        return GSON.toJson(dao.buscarTodos());
    }

    /*----------------------------------------------*/
    static public void addNovoUsuario(String body) throws SQLException {
        Usuario user = GSON.fromJson(body, Usuario.class);
        UsuarioDao dao = new UsuarioDao();
        dao.salvar(user);
    }

    /*----------------------------------------------*/
    static public String pegarUsuario(int id) throws SQLException {
        UsuarioDao dao = new UsuarioDao();
        Usuario user = dao.buscarUsuario(id);    
        
        return GSON.toJson(user);
    }

    /*----------------------------------------------*/
    static public void atualizarUsuario(int id, String body) throws SQLException {
        pegarUsuario(id);
        Usuario user = GSON.fromJson(body, Usuario.class);
        UsuarioDao dao = new UsuarioDao();
        dao.atualizarUsuario(id,user);
    }

    /*----------------------------------------------*/
}
