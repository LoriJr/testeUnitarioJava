package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.excecoes.ExcecaoFilmesSemEstoque;
import br.ce.wcaquino.excecoes.ExcecaoLocadora;

import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmeList ) {

        if(filmeList == null || filmeList.isEmpty()){
            throw new ExcecaoLocadora("Filme vazio");
        }

        if(usuario == null){
            throw new ExcecaoLocadora("Usuário vazio");
        }

        for(Filme filme : filmeList){
            if (filme.getEstoque() <= 0) {
                throw new ExcecaoFilmesSemEstoque("Filme sem estoque");
            }
        }


		Locacao locacao = new Locacao();
		locacao.setUsuario(usuario);

		locacao.setFilmeList(filmeList);

        aplicarDesconto(filmeList);

		locacao.somaValor(filmeList);


		locacao.setDataLocacao(new Date());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

    void aplicarDesconto(List<Filme> listaFilmes) {

        for(int i =0; i < listaFilmes.size(); i++){
            Filme filme = listaFilmes.get(i);
            switch (i) {
                case 2 -> filme.setPrecoLocacao(aplicar25Pct(filme.getPrecoLocacao()));
                case 3 -> filme.setPrecoLocacao(aplicar50Pct(filme.getPrecoLocacao()));
                case 4 -> filme.setPrecoLocacao(aplicar75Pct(filme.getPrecoLocacao()));
                case 5 -> filme.setPrecoLocacao(aplicar100Pct(filme.getPrecoLocacao()));
            }
        }
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
}