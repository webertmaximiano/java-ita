package test;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.CalculoDiaria;
import classes.CalculoHoraValorInicial;
import classes.TarifaEstacionamento;
import classes.calculoHora;

public class TesteTarifaEstacionamento {

	@Test
	public void testTarifaFixaPorHora() {
		TarifaEstacionamento t = 
				new TarifaEstacionamento(3, new calculoHora(4));
		int valor = t.valor();
		assertEquals(12, valor);
	}
	@Test
	public void testTarifaComValorInicialDepoisFixaPorHora() {
		TarifaEstacionamento t = new TarifaEstacionamento(5,
				new CalculoHoraValorInicial(5, 3, 2));
		int valor = t.valor();
		assertEquals(9, valor);
	}
	
	@Test
	public void testTarifaComValorInicialDentroDoLimite() {
		TarifaEstacionamento t = new TarifaEstacionamento(2,
				new CalculoHoraValorInicial(5, 3, 2));
		int valor = t.valor();
		assertEquals(5, valor);
	}
	
	@Test
	public void testTarifaDiaria() {
		TarifaEstacionamento t = new TarifaEstacionamento(50,
				new CalculoDiaria(20));
		int valor = t.valor();
		assertEquals(60, valor);
	}
	
	@Test
	public void testTarifaDiariaMenorQueUmDia() {
		TarifaEstacionamento t = new TarifaEstacionamento(10,
				new CalculoDiaria(20));
		int valor = t.valor();
		assertEquals(20, valor);
	}
}
