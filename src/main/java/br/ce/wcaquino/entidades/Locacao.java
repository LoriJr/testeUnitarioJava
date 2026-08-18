package br.ce.wcaquino.entidades;

import java.util.Date;
import java.util.List;

public class Locacao {

	private Usuario usuario;
	private List<Filme> filmes;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

    public double calcularValorLocacao(List<Filme> listaFilmes) {

        double total = 0d;

        for(int i =0; i < listaFilmes.size(); i++){

            Filme filme = listaFilmes.get(i);
            valor = filme.getPrecoLocacao();

            switch (i) {
                case 2 -> valor = aplicar25Pct(valor);
                case 3 -> valor = aplicar50Pct(valor);
                case 4 -> valor = aplicar75Pct(valor);
                case 5 -> valor = aplicar100Pct(valor);
            }

            total += valor;
        }

        return total;
    }

    double aplicar25Pct(double valor){
        return (1 - 0.25) * (valor);
    }
    double aplicar50Pct(double valor){
        return (1 - 0.50) * (valor);
    }
    double aplicar75Pct(double valor){
        return (1 - 0.75) * (valor);
    }
    double aplicar100Pct(double valor){
        return valor * 0  ;
    }

	public void setFilmeList(List<Filme> filmes) {
		this.filmes = filmes;
	}
}