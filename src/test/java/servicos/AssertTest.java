package servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AssertTest {

    @Test
    void test(){
        assertTrue(true); //retorno booleano
        assertFalse(false);

        assertEquals("Erro de comparação",1,1); //comparação entre valores int long e também alguns booleanos

        assertEquals(0.51, 0.51, 0.001); // para double é importante usar o valor de variação após as comparações

        Usuario u1 = new Usuario("Us1");
        Usuario u2 = new Usuario("Us1");
        Usuario u3 = null;

        assertEquals(u1, u2); // foi preciso criar o método equals na entidade Usuario
        assertNull(u3);


    }
}
