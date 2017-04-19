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
        Usuario u = new Usuario("Guillem", "guillem1234", "guillem@gmail.com");
        u.setId(0);
        u.insert();
    }

    @org.junit.Test
    public void update(){
        Usuario u = new Usuario("Guillem","guillem1234", "guillem@gmail.com");
        u.setNombre("Jose");
        u.setId(0);
        u.update();
    }

    @org.junit.Test
    public void select(){
        Usuario u = new Usuario("Guillem","Guillem", "pepe@gmail.com");
        u.select(0);

    }

    @org.junit.Test
    public void delete() {
        Usuario u = new Usuario("Guillem", "guillem1234", "guillem@gmail.com");
        u.setId(0);
        u.delete();
    }
}
