package Test;

import Controlador.Controlador;

import static org.junit.Assert.*;

/**
 * Created by Mikel on 13/03/2017.
 */
public class Test {

    @org.junit.Test
    public void prueba(){
        Controlador c = Controlador.getControlador();
        boolean b=c.borrarEetakemonPorId(3);
        assertTrue(b==false);
    }
}
