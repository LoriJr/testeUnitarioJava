package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import servicos.CalculaValorLocacaoTest;
import servicos.LocacaoServiceTest;

@Suite
@SelectClasses({
        CalculaValorLocacaoTest.class,
        CalculaValorLocacaoTest.class,
        LocacaoServiceTest.class
})
public class SuiteExecucao {

}
