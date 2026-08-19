package matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

    private Integer diaSemana;

    public DataDiferencaDiasMatcher(Integer diaSemana){
        this.diaSemana = diaSemana;
    }

    @Override
    public void describeTo(Description description) {

    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(diaSemana));
    }
}
