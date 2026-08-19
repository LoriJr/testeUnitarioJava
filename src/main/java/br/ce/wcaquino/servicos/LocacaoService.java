package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.excecoes.ExcecaoFilmesSemEstoque;
import br.ce.wcaquino.excecoes.ExcecaoLocadora;
import br.ce.wcaquino.utils.DataUtils;

import java.util.Calendar;
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

        locacao.setDataLocacao(new Date());
		locacao.calcularValorLocacao(filmeList);

		Date dataEntrega = locacao.getDataLocacao();
		dataEntrega = adicionarDias(dataEntrega, 1);

        if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
            dataEntrega = adicionarDias(dataEntrega, 1);
        }

		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}