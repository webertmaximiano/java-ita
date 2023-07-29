package classes;

public class Item {

	private String nome;
	private double valor;
	private Desconto desconto;
	
	public Item(String nome, double valor, Desconto desconto) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.desconto = desconto;
	}

	public Item(String nome, double valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}

	@Override
	public String toString() {
		if (desconto!= null)
			return nome + " R$" + desconto.darDesconto(valor);
		else
			return nome + " R$" + valor;
	}

	public double precoQuantidade(int qtd) {
		if (desconto != null)
			return desconto.darDesconto(valor) * qtd;
		else
			return valor * qtd;
	}

}
