package test;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Usuario;
import classes.InterfaceNegocio;
import classes.NegocioMock;
import classes.SegurancaNegocio;

public class TesteProxySeguranca {

	@Test
	public void testeAutorizaAcesso() {
		Usuario guerra = new Usuario("Guerra");
		guerra.autorizaAcesso("InterfaceNegocio", "executaTransacao");
		NegocioMock mock = new NegocioMock();
		InterfaceNegocio n = new SegurancaNegocio(mock, guerra);
		n.executaTransacao();
	}
	
	@Test
	public void testeNaoAutorizadoAcesso() {
		Usuario guerra = new Usuario("Guerra");
		guerra.autorizaAcesso("InterfaceNegocio", "executaTransacao");
		NegocioMock mock = new NegocioMock();
		InterfaceNegocio n = new SegurancaNegocio(mock, guerra);
		
		try {
			n.cancelaTransacao();
			fail();
		} catch (Exception e) {
			assertFalse(mock.foiAcessado());
		}
	}

}
