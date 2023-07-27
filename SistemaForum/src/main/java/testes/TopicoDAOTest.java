package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.TopicoDAO;
import model.Topico;

public class TopicoDAOTest {

    private IDatabaseTester databaseTester;
    private TopicoDAO topicoDAO;

    @Before
    public void setUp() throws Exception {
        // Configura o database tester com o banco de dados em memória
        databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:testdb", "sa", "");
        // Carrega o conjunto de dados de teste do arquivo XML
        Reader dataSetReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("dados_teste.xml"));
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(dataSetReader);
        databaseTester.setDataSet(dataSet);
        // Realiza a preparação do banco de dados com o conjunto de dados de teste
        databaseTester.onSetup();

        topicoDAO = new TopicoDAO();
    }

    @After
    public void tearDown() throws Exception {
        // Executa o teardown do database tester
        databaseTester.onTearDown();
    }

    @Test
    public void testTodosTopicos() {
        // Chama o método que queremos testar
        List<Topico> topicos = topicoDAO.todosTopicos("usuario1"); // Substitua pelo login de teste

        // Verifica se os tópicos retornados são iguais aos esperados
        assertNotNull(topicos);
        assertEquals(2, topicos.size());
        // Faça mais asserções conforme necessário
    }
}
