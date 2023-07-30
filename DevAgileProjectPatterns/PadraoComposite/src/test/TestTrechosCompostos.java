package test;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.TrechoAereo;
import classes.TrechoAereoComConexao;
import classes.TrechoAereoComEscala;
import classes.TrechoAereoSimples;

public class TestTrechosCompostos {

	@Test
	public void trechoComEscala() {
		TrechoAereo t1 = new TrechoAereoSimples("GRU", "FOR", 200, 400);
		TrechoAereo t2 = new TrechoAereoSimples("FOR", "NAT", 150, 200);
		TrechoAereoComEscala escala = new TrechoAereoComEscala(t1, t2);
		assertEquals("GRU", escala.getOrigem());
		assertEquals("NAT", escala.getDestino());
		assertEquals(350, escala.getCusto());
		assertEquals(600, escala.getDistancia());
	}
	
	@Test
	public void trechoComEscalaAeroportoDiferentes() {
		TrechoAereo t1 = new TrechoAereoSimples("GRU", "FOR", 200, 400);
		TrechoAereo t2 = new TrechoAereoSimples("FOR", "NAT", 150, 200);
		new TrechoAereoComEscala(t1, t2);
		
	}
	
	@Test
	public void trechoComConexao() {
		TrechoAereo t1 = new TrechoAereoSimples("GRU", "FOR", 200, 400);
		TrechoAereo t2 = new TrechoAereoSimples("FOR", "NAT", 150, 200);
		TrechoAereo conexao = new TrechoAereoComConexao(t1, t2,80);
		assertEquals("GRU", conexao.getOrigem());
		assertEquals("NAT", conexao.getDestino());
		assertEquals(430, conexao.getCusto());
		assertEquals(600, conexao.getDistancia());
	}
	@Test
	public void trechoComEscalaEConexao() {
		TrechoAereo t1 = new TrechoAereoSimples("GRU", "FOR", 200, 400);
		TrechoAereo t2 = new TrechoAereoSimples("FOR", "NAT", 150, 200);
		TrechoAereo escala = new TrechoAereoComEscala(t1,t2);
		TrechoAereo t3 = new TrechoAereoSimples("NAT", "BSB", 250, 300);
		TrechoAereo conexao = new TrechoAereoComConexao(escala, t3,50);
		assertEquals("GRU", conexao.getOrigem());
		assertEquals("BSB", conexao.getDestino());
		assertEquals(650, conexao.getCusto());
		assertEquals(900, conexao.getDistancia());
	}
}
