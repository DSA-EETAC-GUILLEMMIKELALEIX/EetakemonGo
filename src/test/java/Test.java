/**
 * Created by Mikel on 15/03/2017.
 */
import Modelo.Eetakemon;
import Controlador.DAO;
import Modelo.Usuario;
import Controlador.JSONservice;


import static org.junit.Assert.*;


public class Test {



    @org.junit.Test
    public void insert(){
        Usuario u = new Usuario("aasdf", "guillem1234", "guillem@gmail.com");
        u.crear();
    }

    @org.junit.Test
    public void update(){
        Usuario u = new Usuario("Guillem","guillem1234", "guillem@gmail.com");
        u.setNombre("Jose");
        u.actualizar();
    }

    @org.junit.Test
    public void select(){
        Usuario u = new Usuario("Guillem","Guillem", "pepe@gmail.com");
        u.buscarPorId(0);

    }

    @org.junit.Test
    public void delete() {
        Usuario u = new Usuario();
        u.buscarPorId(0);
        u.borrar();
    }

    @org.junit.Test
    public void pruebaid(){
        Usuario u = new Usuario();
        u.getLastId();
    }
}
