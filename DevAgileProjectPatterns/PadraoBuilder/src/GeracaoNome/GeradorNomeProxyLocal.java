package GeracaoNome;

public class GeradorNomeProxyLocal extends GeradorNome {
	
	private GeradorNome gerador;
	private String local;
	
	public GeradorNomeProxyLocal(GeradorNome gerador, String local) {
		super();
		this.gerador = gerador;
		this.local = local;
	}
	public void setTratamento(Tratamento tratamento) {
		gerador.setTratamento(tratamento);
	}
	public String gerarNome(String nomeBase) {
		return gerador.gerarNome(nomeBase)+ " de "+local;
	}
	public Tratamento getTratamentoStrategy() {
		return gerador.getTratamentoStrategy();
	}
	@Override
	protected String getTratamento() {
		return gerador.getTratamento();
	}
	
	
}
