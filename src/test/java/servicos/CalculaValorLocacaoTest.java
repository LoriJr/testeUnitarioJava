package servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculaValorLocacaoTest {

    LocacaoService service;

    @BeforeEach
    void setUp(){
        service = new LocacaoService();
    }

    static Filme filme1 = new Filme("Filme1", 1, 4.0);
    static Filme filme2 = new Filme("Filme2", 1, 4.0);
    static Filme filme3 = new Filme("Filme3", 1, 4.0);
    static Filme filme4 = new Filme("Filme4", 1, 4.0);
    static Filme filme5 = new Filme("Filme5", 1, 4.0);
    static Filme filme6 = new Filme("Filme6", 1, 4.0);

    static Stream<Arguments> getParametros() {
        return Stream.of(
                Arguments.of(Arrays.asList(filme1, filme2, filme3), 11.0),
                Arguments.of(Arrays.asList(filme1, filme2, filme3, filme4), 13.0),
                Arguments.of(Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0),
                Arguments.of(Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0)
        );
    }

    @ParameterizedTest
    @MethodSource("getParametros")
    @DisplayName("Deve aplicar desconto na locação de filmes")
    void deveAplicarDescontoNaLocacaoDeFilme(List<Filme> filmes, Double valorLocacao) {

        Usuario usuario = new Usuario("Usuário 1");

        Locacao resultado = service.alugarFilme(usuario, filmes);

        assertThat(resultado.calcularValorLocacao(filmes), is(valorLocacao));
    }
}
