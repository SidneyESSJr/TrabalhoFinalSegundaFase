package br.com.grupoVoid.modelo.dao;

import br.com.grupoVoid.dao.UsuarioDao;
import br.com.grupoVoid.modelo.Usuario;
import java.sql.SQLException;
import static junit.framework.Assert.fail;
import org.junit.Test;

/**
 *
 * @author maryu
 */
public class UsuarioDaoTest {

    public UsuarioDaoTest() {
    }

    @Test
    public void testSomeMethod() throws SQLException {
        Usuario user = new Usuario("maryucha", "8754212", 15, "barreiros", "8787878", true);
        UsuarioDao dao = new UsuarioDao();

        if (dao.salvar(user)) {
            System.out.println("Salvo com sucesso!");
        } else {
            fail("erro ao salvar!");
        }
    }

}
