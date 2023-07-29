package classes;

public class calculoHora implements Calculo {
	
	private int valorHora;
	//construtor
	public calculoHora(int valorHora) {
		this.valorHora = valorHora;
	}
	@Override
	public int calcularTarifa(int qtdHoras) {
		
		return qtdHoras * valorHora;
	}
	
	
}
