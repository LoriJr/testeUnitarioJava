package servicos;

import br.ce.wcaquino.servicos.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    Calculadora calc;

    @BeforeEach
    void setUp(){
        calc = new Calculadora(5,2);
    }

    @Test
    @DisplayName("Deve somar valores")
    void deveSomarValores(){

        int resultado = calc.somar(calc.getA(), calc.getB());

        assertEquals(7, resultado);
    }

    @Test
    @DisplayName("Deve subtrair valores")
    void deveSubtrairValores(){

        int resultado = calc.subtrair(calc.getA(), calc.getB());

        assertEquals(3, resultado);
    }

    @Test
    @DisplayName("Deve dividir valores")
    void deveDividirValores(){

        double resultado = calc.dividir(calc.getA(), calc.getB());

        assertEquals(2.5, resultado);
    }

    @Test
    @DisplayName("Deve multiplicar valores")
    void deveMultiplicarValores(){

        int resultado = calc.multiplicar(calc.getA(), calc.getB());

        assertEquals(10, resultado);
    }

    @Test
    @DisplayName("Deve lançar Exceção ao dividir por zero")
    void deveLancarExecaoAoDividirPorZero(){

        Calculadora calculadora = new Calculadora(5,0);

        ArithmeticException message = Assertions.assertThrows(ArithmeticException.class,
                ()-> calculadora.dividir(calculadora.getA(), calculadora.getB()));

        assertEquals("Não é possível dividir por zero", message.getMessage());
    }
}
