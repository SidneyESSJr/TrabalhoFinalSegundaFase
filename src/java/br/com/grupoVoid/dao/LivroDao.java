package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.modelo.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ssjun
 */
public class LivroDao {

    private Connection con = null;

    /*------------------------------------------------------------*/
    public LivroDao() {
        con = ConnectionFactory.getConnection();
    }

    /*------------------------------------------------------------*/
    public boolean salvar(Livro livro) throws SQLException {
        con = ConnectionFactory.getConnection();

        String sql = "insert into livro (qtdlivro,nome,genero,resumo,anopubli,edicao, disponibilidade) values(?,?,?,?,?,?,?)";
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, livro.getQtdLivro());
            stm.setString(2, livro.getNome());
            stm.setString(3, livro.getGenero());
            stm.setString(4, livro.getResumo());
            stm.setInt(5, livro.getAnoPubli());
            stm.setInt(6, livro.getEdicao());
            stm.setBoolean(7, livro.isDisponibilidade());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("erro salvar no banco " + e);
            return false;
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    public List<Livro> buscarTodos() throws SQLException {

        String sql = "select * from livro";

        List<Livro> list = new ArrayList<>();

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setQtdLivro(rs.getInt("qtdlivro"));
                livro.setNome(rs.getString("nome"));
                livro.setGenero(rs.getString("genero"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAnoPubli(rs.getInt("anopubli"));
                livro.setEdicao(rs.getInt("edicao"));
                livro.setDisponibilidade(rs.getBoolean("disponibilidade"));

                list.add(livro);
            }
        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR DO BANCO." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return list;
    }

    public Livro pegarLivro(int id) throws SQLException {
        String sql = "select * from livro where id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        Livro livro = new Livro();

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                livro.setId(rs.getInt("id"));
                livro.setQtdLivro(rs.getInt("qtdlivro"));
                livro.setNome(rs.getString("nome"));
                livro.setGenero(rs.getString("genero"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAnoPubli(rs.getInt("anopubli"));
                livro.setEdicao(rs.getInt("edicao"));
                livro.setDisponibilidade(rs.getBoolean("disponibilidade"));
            }
        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR DO BANCO." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return livro;
    }

    public void atualizarLivro(int id, Livro livro) throws SQLException {
        String sql = "update livro set qtdlivro = ?, nome =?, genero =?, resumo =?, anopubli= ?, edicao= ?, disponibilidade = ? where id = ?";

        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, livro.getQtdLivro());
            stm.setString(2, livro.getNome());
            stm.setString(3, livro.getGenero());
            stm.setString(4, livro.getResumo());
            stm.setInt(5, livro.getAnoPubli());
            stm.setInt(6, livro.getEdicao());
            stm.setBoolean(7, livro.isDisponibilidade());
            stm.setInt(8, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR DO BANCO." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    public void deletarLivro(int id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";

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
