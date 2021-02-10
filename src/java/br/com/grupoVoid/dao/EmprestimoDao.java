package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.modelo.Emprestimo;
import br.com.grupoVoid.util.ConversorData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ssjun
 */
public class EmprestimoDao {

    private Connection conn = null;

    public EmprestimoDao(Connection conn) {
        this.conn = conn;
    }

    public void alugarLivro(Integer idLivro, Integer idUsuario, Emprestimo emprestimo) throws SQLException {

        PreparedStatement stm = null;

        try {
            stm = conn.prepareStatement("insert into emprestimo(id_usuario, "
                    + "id_livro, data_inicio, data_entrega, multa, situacao) "
                    + "values(?, ?, ?, ?, ?, ?)");

            stm.setInt(1, idUsuario);
            stm.setInt(2, idLivro);
            stm.setDate(3, ConversorData.converterUtilToSql(emprestimo.getDataInicio()));
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

    public void devolverLivro(Integer idEmprestimo) throws SQLException {

        String selectString = "SELECT * FROM emprestimo WHERE id = ?";

        String updateString = "UPDATE emprestimo set id_usuario = ?, "
                + "id_livro = ?, data_inicio = ?, data_entrega = ?, multa = ?, "
                + "situacao = ? where id = ?";

        ResultSet rs = null;
        conn.setAutoCommit(false);

        try (PreparedStatement selectEmprestimo = conn.prepareStatement(selectString);
                PreparedStatement updateEmprestimo = conn.prepareStatement(updateString)) {

            selectEmprestimo.setInt(1, idEmprestimo);

            if (selectEmprestimo.execute()) {

                rs = selectEmprestimo.getResultSet();

                rs.next();

                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setUsuario(rs.getInt("id_usuario"));
                emprestimo.setLivro(rs.getInt("id_livro"));
                emprestimo.setDataInicio(rs.getString("data_inicio"));
                emprestimo.setDataEntrega(rs.getString("data_entrega"));
                emprestimo.setMulta(rs.getDouble("multa"));
                emprestimo.setSituacao(rs.getBoolean("situacao"));

                updateEmprestimo.setInt(1, emprestimo.getUsuario());
                updateEmprestimo.setInt(2, emprestimo.getLivro());
                updateEmprestimo.setDate(3, ConversorData.converterUtilToSql(emprestimo.getDataInicio()));
                updateEmprestimo.setDate(4, ConversorData.converterUtilToSql(emprestimo.getDataEntrega()));
                updateEmprestimo.setDouble(5, emprestimo.getMulta());
                updateEmprestimo.setBoolean(6, false);
                updateEmprestimo.setInt(7, emprestimo.getId());

                updateEmprestimo.executeUpdate();

                conn.commit();

            } else {
                System.out.println("Não foi possivel encontrar o emprestimo de id " + idEmprestimo);
            }
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
}
