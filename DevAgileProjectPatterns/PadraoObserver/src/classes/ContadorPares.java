package classes;

public class ContadorPares implements ContadorPalavras {
    
    private int qtd;
    
    @Override
    public void contar(String[] palavras) {
        for (String palavra : palavras) {
            if (palavra.length() % 2 == 0) {
                qtd++;
            }
        }
    }
    
    @Override
    public int contagem() {
        return qtd;
    }
}
