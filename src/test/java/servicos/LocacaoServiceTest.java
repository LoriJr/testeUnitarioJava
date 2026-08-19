package servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.excecoes.ExcecaoFilmesSemEstoque;
import br.ce.wcaquino.excecoes.ExcecaoLocadora;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static matchers.MatchersProprios.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LocacaoServiceTest {

    private LocacaoService service;

    private Usuario usuario;
    private List<Filme> filmes = new ArrayList<>();
    private Filme filme1;
    private Filme filme2;

    @BeforeEach
    void setUp(){
        usuario = new Usuario("junior");
        filme1 = new Filme("filme1", 1, 3.0);
        filme2 = new Filme("filme2", 2, 5.0);

        filmes.add(filme1);
        filmes.add(filme2);

        service = new LocacaoService();
    }

    @Test
    @DisplayName("Deve fazer locação com sucesso")
    public void deveFazerLocacaoComSucesso() {

        //ação
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificação
        //exemplo usando matcher criado
        assertThat(locacao.getDataRetorno(), ehHojeComDiferencaDeDias(1));
        assertThat(locacao.getDataLocacao(), ehHoje());

        //exemplo bom para ser usado
        assertAll(
                ()->assertThat(locacao.calcularValorLocacao(filmes), CoreMatchers.is(CoreMatchers.equalTo(8.0))),
                ()-> assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true)),
                ()-> assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), CoreMatchers.is(true))
        );

    }

    @Test
    @DisplayName("Deve lançar exceção de filme sem estoque")
    void deveLancarExcecaoDeFilmeSemEstoque(){
        //Cenário
        List<Filme> filmeEstoqueZero = new ArrayList<>();
        Filme filmeSemEstoque = new Filme("star-wars", 0, 2.5);

        filmeEstoqueZero.add(filmeSemEstoque);

        //ação
        ExcecaoFilmesSemEstoque excecao = assertThrows(ExcecaoFilmesSemEstoque.class,
                ()->service.alugarFilme(usuario, filmeEstoqueZero));

        assertEquals("Filme sem estoque", excecao.getMessage());

    }

    @Test
    @DisplayName("Deve lançar exceção usuário vazio")
    void deveLancarExcecaoUsuarioVazio(){

        Usuario usuarioVazio = null;

        ExcecaoLocadora excecao = assertThrows(ExcecaoLocadora.class,
                ()-> service.alugarFilme(usuarioVazio, filmes));
        assertEquals("Usuário vazio", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção filme vazio")
    void deveLancarExcecaoFilmeVazio(){

        List<Filme> filmeVazio = Collections.emptyList();

        ExcecaoLocadora excecao = assertThrows(ExcecaoLocadora.class,
                ()-> service.alugarFilme(usuario, filmeVazio));
        assertEquals("Filme vazio", excecao.getMessage());
    }

    @Test
    @DisplayName("Deve devolver na segunda ao alugar no sábado")
    void deveDevolverNaSegundaAoAlugarNoSabado(){

        //adicionado para que o teste execute somente no sábado
        //caso precise testar em outro dia, altere a data de locação no método alugarFilme
        Assumptions.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        Locacao locacao = service.alugarFilme(usuario, filmes);

        assertThat(locacao.getDataRetorno(), caiNumaSegunda());
    }

}
