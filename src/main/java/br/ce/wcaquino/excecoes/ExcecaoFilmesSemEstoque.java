package br.ce.wcaquino.excecoes;

public class ExcecaoFilmesSemEstoque extends RuntimeException{

    public ExcecaoFilmesSemEstoque(String mensagem){
        super(mensagem);
    }

}
