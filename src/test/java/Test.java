/**
 * Created by Mikel on 15/03/2017.
 */
import Controlador.Controlador;
import Modelo.Eetakemon;

import static org.junit.Assert.*;


public class Test {

    @org.junit.Test
    public void prueba(){
        Controlador c = Controlador.getControlador();
        boolean b=c.borrarEetakemonPorId(3);
        assertTrue(b==false);
    }

    @org.junit.Test
    public void busquedaAvanzada(){
        Controlador c = Controlador.getControlador();
        c.anadirATabla(new Eetakemon("Aleix",1));
        c.anadirATabla(new Eetakemon("Guillem",2));
        c.anadirATabla(new Eetakemon("MIkel",3));

        c.busquedaAvanzada("le");
    }
}
