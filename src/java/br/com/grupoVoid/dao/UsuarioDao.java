package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import br.com.grupoVoid.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maryucha
 */
public class UsuarioDao {

    private Connection con = null;

    /*------------------------------------------------------------*/
    public UsuarioDao() {
        con = ConnectionFactory.getConnection();
    }

    /*------------------------------------------------------------*/
    public boolean salvar(Usuario usuario) throws SQLException {
        con = ConnectionFactory.getConnection();

        String sql = "insert into usuario (nome,cpf,idade,endereco,telefone,ativo) values(?,?,?,?,?,?)";
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getCpf());
            stm.setInt(3, usuario.getIdade());
            stm.setString(4, usuario.getEndereco());
            stm.setString(5, usuario.getTelefone());
            stm.setBoolean(6, usuario.isAtivo());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("erro salvar no banco " + e);
            return false;
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    /*------------------------------------------------------------*/
    public List<Usuario> buscarTodos() throws SQLException {
        String sql = "select * from usuario";

        List<Usuario> listaUsuarios = new ArrayList<>();

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setIdade(rs.getInt("idade"));
                user.setEndereco(rs.getString("endereco"));
                user.setTelefone(rs.getString("telefone"));
                user.setAtivo(rs.getBoolean("ativo"));
                listaUsuarios.add(user);
            }
        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR DO BANCO." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return listaUsuarios;
    }

    /*------------------------------------------------------------*/
    public void atualizarUsuario(int id, Usuario user) throws SQLException {
        String sql = "update usuario set nome = ?, cpf =?, idade=?, endereco =?, telefone = ?, ativo = ? where id = ?";

        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(sql);

            stm.setString(1, user.getNome());
            stm.setString(2, user.getCpf());
            stm.setInt(3, user.getIdade());
            stm.setString(4, user.getEndereco());
            stm.setString(5, user.getTelefone());
            stm.setBoolean(6, user.isAtivo());
            stm.setInt(7, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR DO BANCO." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    /*------------------------------------------------------------*/
    public Usuario buscarUsuario(int id) throws SQLException {
        String sql = "select * from usuario where id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        Usuario user = new Usuario();

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setIdade(rs.getInt("idade"));
                user.setEndereco(rs.getString("endereco"));
                user.setTelefone(rs.getString("telefone"));
                user.setAtivo(rs.getBoolean("ativo"));
            }
        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR DO BANCO." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return user;
    }

    /*------------------------------------------------------------*/
    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";

        PreparedStatement stm = null;
        
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);

            stm.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Deu erro em excluir do banco." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }
}
