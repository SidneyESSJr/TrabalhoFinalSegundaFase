/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupoVoid.dao;

import br.com.grupoVoid.connection.ConnectionFactory;
import br.com.grupoVoid.modelo.Emprestimo;
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
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return list;
    }

    /*------------------------------------------------------------*/
    public Object pegarEmprestimo(int id) {
        return null;
    }

    /*------------------------------------------------------------*/
    public Boolean adicionarNovo(Emprestimo emprestimo) {
        String sql = "insert into emprestimo(id_usuario,id_livro,data_inicio,data_entrega,multa,situacao) values(?,?,?,?,?,?)";

        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, emprestimo.getUsuario().getId());
            stm.setInt(2, emprestimo.getLivro().getId());
            stm.setDate(3, Date.valueOf(emprestimo.getDataInicio()));
            stm.setDate(4, Date.valueOf(emprestimo.getDataEntrega()));
            stm.setDouble(5, emprestimo.getMulta());
            stm.setBoolean(6, emprestimo.isSituacao());
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
