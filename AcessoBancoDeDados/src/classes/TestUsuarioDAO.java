package classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestUsuarioDAO {
	
	JdbcDatabaseTester jdt;
	
	@BeforeEach
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester("org.postgresql.Driver","jdbc:postgresql://localhost:5432/cursojava","postgres","");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}
	

    @Test
    void testRecuperaTodos() {
    	List<Usuario> lista = UsuarioDAO.todosUsuarios();
    	assertEquals(4, lista.size());
    	//assertEquals("webert", lista.get(0).getLogin());
    }

}



