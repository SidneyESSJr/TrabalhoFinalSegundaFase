
package br.com.grupoVoid.controle;

import br.com.grupoVoid.dao.EmprestimoDao;
import br.com.grupoVoid.modelo.Emprestimo;
import com.google.gson.Gson;

/**
 *
 * @author ssjun
 */
public class ControleEmprestimo {
    
    private static final Gson GSON = new Gson();
    private final static EmprestimoDao DAO = new EmprestimoDao();

    public static String pegarLista() {
        return GSON.toJson(DAO.buscarTodos());
    }

    public static String pegarEmprestimo(int id) {
        return GSON.toJson(DAO.pegarEmprestimo(id));
    }

    public static void addNovoEmprestimo(String content) {
        DAO.adicionarNovo(GSON.fromJson(content, Emprestimo.class));
    }

    public static void atualizarEmprestimo(int id, String content) {
        DAO.atualizarEmprestimo(id, GSON.fromJson(content, Emprestimo.class));
    }

    public static void deletarEmprestimo(int id) {
        DAO.deletarLivro(id);
    }
    
}
