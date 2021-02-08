/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.modelo.Emprestimo;
import java.sql.Connection;
import java.sql.Date;
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

    /*------------------------------------------------------------*/
    public EmprestimoDao(Connection conn) {
        this.conn = conn;
    }

    public List<Emprestimo> buscarTodos() throws SQLException {

        List<Emprestimo> list = new ArrayList<>();

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement("select * from emprestimo");
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(instanciarEmprestimo(rs));
            }
        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR EMPRESTIMO DO BANCO." + e);
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

    /*------------------------------------------------------------*/
    public Object pegarEmprestimo(int id) {
        return null;
    }

    /*------------------------------------------------------------*/
    public Boolean adicionarNovo(Emprestimo emprestimo) {

        PreparedStatement stm = null;

        try {
            stm = conn.prepareStatement("insert into emprestimo(id_usuario, "
                    + "id_livro, data_inicio, data_entrega, multa, situacao) "
                    + "values(?, ?, ?, ?, ?, ?)");
            
            stm.setInt(1, emprestimo.getUsuario());
            stm.setInt(2, emprestimo.getLivro());
            stm.setDate(3, new Date(emprestimo.getDataInicio().getTime()));
            stm.setDate(4, new Date(emprestimo.getDataEntrega().getTime()));
            stm.setDouble(5, emprestimo.getMulta());
            stm.setBoolean(6, emprestimo.isSituacao());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("erro salvar no banco " + e);
            return false;
        }
    }

    /*------------------------------------------------------------*/
    public void atualizarEmprestimo(int id, Emprestimo fromJson) {
    }

    /*------------------------------------------------------------*/
    public void deletarLivro(int id) {
    }

    /*------------------------------------------------------------*/
}
