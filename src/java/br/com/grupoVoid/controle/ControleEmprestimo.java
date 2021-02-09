package br.com.grupoVoid.controle;


import br.com.grupoVoid.dao.EmprestimoDao;
import br.com.grupoVoid.modelo.Emprestimo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssjun
 */
public class ControleEmprestimo {

    //private EmprestimoDao dao = new EmprestimoDao();
    private static final Gson GSON = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    public String listarEmprestimos() {
        EmprestimoDao dao = new EmprestimoDao();
        
        String list = "";
        try {
            list = GSON.toJson(dao.listarEmprestimos());
        } catch (SQLException ex) {
            Logger.getLogger(ControleEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void alugarLivro(Integer idLivro, Integer idUsuario, String content) {
        EmprestimoDao dao = new EmprestimoDao();
        try {
            dao.alugarLivro(idLivro, idUsuario, GSON.fromJson(content, Emprestimo.class));
        } catch (SQLException ex) {
            Logger.getLogger(ControleEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void devolverLivro(Integer idEmprestimo) throws SQLException {
        EmprestimoDao dao = new EmprestimoDao();
        Emprestimo emprestimo = buscarPeoloId(idEmprestimo);
        dao.devolverLivro(emprestimo);
    }

    public Emprestimo buscarPeoloId(Integer id) throws SQLException {
        EmprestimoDao dao = new EmprestimoDao();
        return dao.buscarPeloId(id);
    }

}
