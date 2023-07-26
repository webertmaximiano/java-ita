package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import db.DataBaseConection;

class DataBaseConectionTest {

	@Test
    void testConnection() {
        try {
            Connection connection = DataBaseConection.getConnection();
            assertNotNull(connection);
            connection.close(); // Feche a conexão após o teste.
        } catch (Exception e) {
            fail("Falha na conexão com o banco de dados: " + e.getMessage());
        }
    }
}
