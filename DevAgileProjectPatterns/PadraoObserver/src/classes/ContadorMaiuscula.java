package classes;

public class ContadorMaiuscula implements ContadorPalavras {
	
	private int qtd;

	@Override
	public void contar(String[] palavras) {
		if (Character.isUpperCase(palavras.length))
				qtd++;

	}

	@Override
	public int contagem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
