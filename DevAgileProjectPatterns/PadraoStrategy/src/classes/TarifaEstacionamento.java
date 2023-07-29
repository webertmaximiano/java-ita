package classes;

public class TarifaEstacionamento {
	
	private int qtdHoras;
	private Calculo calculo;
	//construtor
	public TarifaEstacionamento(int qtdHoras, Calculo calculo) {
		this.qtdHoras = qtdHoras;
		this.calculo = calculo;
	}

	public int valor() {
		// TODO Auto-generated method stub
		return calculo.calcularTarifa(qtdHoras);
	}

}
