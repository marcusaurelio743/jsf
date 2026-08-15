package meujsf;

import javax.persistence.Persistence;

import org.junit.Test;

public class TestJPA {
	@Test
	public void testConexao() {
		Persistence.createEntityManagerFactory("meujsf");
	}

}
