package classes;

public class CalculoHoraValorInicial implements Calculo {
	
	private int valorInicial;
	private int limiteHorasInicial;
	private int valorHoraExcedente;
	//construtor
	public CalculoHoraValorInicial(int valorInicial, int limiteHorasInicial, 
			int valorHoraExcedente) {
		this.valorInicial = valorInicial;
		this.limiteHorasInicial = limiteHorasInicial;
		this.valorHoraExcedente = valorHoraExcedente;
	}

	@Override
	public int calcularTarifa(int qtdHoras) {
		int valor = this.valorInicial;
		if (qtdHoras > limiteHorasInicial)
		valor += (qtdHoras - limiteHorasInicial)*valorHoraExcedente;
		return valor;
	}

}
