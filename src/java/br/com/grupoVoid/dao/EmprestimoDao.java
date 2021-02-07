/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.modelo.Emprestimo;
import br.com.grupoVoid.modelo.Usuario;
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

    private Connection con = null;
    private final UsuarioDao uDao = new UsuarioDao();
    private final LivroDao lDao = new LivroDao();

    /*------------------------------------------------------------*/
    public EmprestimoDao() {
        con = ConnectionFactory.getConnection();
    }

    public List<Emprestimo> buscarTodos() throws SQLException {
        String sql = "select * from livro";

        List<Emprestimo> list = new ArrayList<>();

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();

                emprestimo.setId(rs.getInt("id"));
                emprestimo.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                emprestimo.setDataEntrega(rs.getDate("data_entrega").toLocalDate());
                emprestimo.setMulta(rs.getDouble("multa"));
                emprestimo.setSituacao(rs.getBoolean("situacao"));
                int usuario = rs.getInt("usuario");
                emprestimo.setUsuario(uDao.buscarUsuario(usuario));
                int livro = rs.getInt("livro");
                emprestimo.setLivro(lDao.pegarLivro(livro));
            }
        } catch (SQLException e) {
            System.err.println("DEU PAU EM PEGAR DO BANCO." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm,rs);
        }
        return list;
    }

    public Object pegarEmprestimo(int id) {
        return null;
    }

    public void adicionarNovo(Emprestimo fromJson) {
    }

    public void atualizarEmprestimo(int id, Emprestimo fromJson) {
    }

    public void deletarLivro(int id) {
    }



}
