package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import br.com.grupoVoid.modelo.Usuario;
import java.sql.PreparedStatement;

/**
 *
 * @author maryucha
 */
public class UsuarioDao {

    private Connection con = null;

    public UsuarioDao() throws SQLException {
        con = ConnectionFactory.getConnection();
    }

    public boolean salvar(Usuario usuario) throws SQLException {
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

}
