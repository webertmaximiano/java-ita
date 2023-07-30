package test;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.ContadorMaiuscula;
import classes.ContadorPares;
import classes.ContadorSimples;
import classes.QuebradorPalavras;

public class TesteQuebradorPalavras {

	@Test
	public void quebradorPalavras() {
		String frase = "o rato roeu a roupa do rei";
		QuebradorPalavras quebrador = new QuebradorPalavras();
		String[] palavras = quebrador.quebrar(frase);
		assertEquals(7,palavras.length);
	}
	
	@Test
	public void contadorSimples() {
		String frase = "o rato roeu a roupa do rei";
		QuebradorPalavras quebrador = new QuebradorPalavras();
		quebrador.adicionaContador("SIMPLES", new ContadorSimples());
		quebrador.quebrar(frase);
		assertEquals(7,quebrador.getContagem("SIMPLES"));
	}
	
	@Test
	public void contadorMaiuscula() {
		String frase = "o Rato roeu a roupa do Rei";
		QuebradorPalavras quebrador = new QuebradorPalavras();
		quebrador.adicionaContador("MAIUSCULA", new ContadorMaiuscula());
		quebrador.quebrar(frase);
		assertEquals(2,quebrador.getContagem("MAIUSCULA"));
	}
	
	@Test
	public void contadorLetrasPares() {
		String frase = "o Rato roeu a roupa do Rei";
		QuebradorPalavras quebrador = new QuebradorPalavras();
		quebrador.adicionaContador("PARES", new ContadorPares());
		quebrador.quebrar(frase);
		assertEquals(2,quebrador.getContagem("PARES"));
	}

}
