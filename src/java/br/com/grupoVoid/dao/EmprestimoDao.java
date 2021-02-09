package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.modelo.Emprestimo;
import br.com.grupoVoid.util.ConversorData;
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
public class EmprestimoDao {

    private Connection conn = null;

    public EmprestimoDao() {
        conn = ConnectionFactory.getConnection();
    }

    public void alugarLivro(Integer idLivro, Integer idUsuario, Emprestimo emprestimo) throws SQLException {

        PreparedStatement stm = null;

        try {
            stm = conn.prepareStatement("insert into emprestimo(id_usuario, "
                    + "id_livro, data_inicio, data_entrega, multa, situacao) "
                    + "values(?, ?, ?, ?, ?, ?)");

            stm.setInt(1, idUsuario);
            stm.setInt(2, idLivro);
            stm.setDate(3,ConversorData.converterUtilToSql(emprestimo.getDataInicio()));
            stm.setDate(4, ConversorData.converterUtilToSql(emprestimo.getDataEntrega()));
            stm.setDouble(5, emprestimo.getMulta());
            stm.setBoolean(6, true);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.err.println("erro salvar no banco " + e);

        } finally {
            ConnectionFactory.fecharConexao(conn, stm);
        }
    }

    public void devolverLivro(Emprestimo emprestimo) throws SQLException {
        PreparedStatement stm = null;
        System.out.println(emprestimo);
        try {
            stm = conn.prepareStatement("UPDATE emprestimo set id_usuario=?, id_livro=?, data_inicio=?, data_entrega=?, multa=?, situacao=? where id = ?");

            stm.setInt(1, emprestimo.getUsuario());
            stm.setInt(2, emprestimo.getLivro());
            stm.setDate(3,ConversorData.converterUtilToSql(emprestimo.getDataInicio()));
            stm.setDate(4, ConversorData.converterUtilToSql(emprestimo.getDataEntrega()));
            stm.setDouble(5, emprestimo.getMulta());
            stm.setBoolean(6,false);
            stm.setInt(7, emprestimo.getId());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro salvar no banco " + e);

        } finally {
            ConnectionFactory.fecharConexao(conn, stm);
        }
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException {
        List<Emprestimo> list = new ArrayList<>();

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement("select * from emprestimo where situacao = true");
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(instanciarEmprestimo(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro em buscar os dados do banco." + e);
        } finally {
            ConnectionFactory.fecharConexao(conn, stm, rs);
        }
        return list;
    }

    private Emprestimo instanciarEmprestimo(ResultSet rs) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(rs.getInt("id"));
        emprestimo.setDataInicio(rs.getString("data_inicio"));
        emprestimo.setDataEntrega(rs.getString("data_entrega"));
        emprestimo.setMulta(rs.getDouble("multa"));
        emprestimo.setSituacao(rs.getBoolean("situacao"));
        emprestimo.setUsuario(rs.getInt("id_usuario"));
        emprestimo.setLivro(rs.getInt("id_livro"));
        return emprestimo;
    }

    public Emprestimo buscarPeloId(Integer id) throws SQLException {
        String sql = "select * from emprestimo where id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        System.out.println(id);
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                Emprestimo emprestimoBuscado = new Emprestimo();
                emprestimoBuscado.setUsuario(rs.getInt("id_usuario"));
                emprestimoBuscado.setLivro(rs.getInt("id_livro"));
                emprestimoBuscado.setDataInicio(rs.getString("data_inicio"));
                emprestimoBuscado.setDataInicio(rs.getString("data_entrega"));
                emprestimoBuscado.setMulta(rs.getDouble("multa"));
                emprestimoBuscado.setSituacao(rs.getBoolean("situacao"));
                System.out.println(emprestimoBuscado);
                return emprestimoBuscado;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro em atualizar o banco." + e);
        } finally {
            ConnectionFactory.fecharConexao(conn, stm, rs);
        }
        return null;
    }

}
