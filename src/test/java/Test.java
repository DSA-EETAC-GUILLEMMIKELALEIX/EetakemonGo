/**
 * Created by Mikel on 15/03/2017.
 */
import Modelo.Eetakemon;
import Modelo.User;
import Controlador.EetakemonService;
import Controlador.DAO;

import java.util.List;


public class Test {



    @org.junit.Test
    public void insert(){
        User u = new User("Mikel", "1234", "guillem@gmail.com");
        u.insert();
    }

      @org.junit.Test
    public void insert1(){
        Eetakemon u = new Eetakemon("Bernorlax","Normal",15);
          Boolean a;
          a=u.checkExistent("nombre", u.getNombre());
          if (a)
          {
              u.insert();
          }
    }
    @org.junit.Test
    public void insert2(){
        Eetakemon u = new Eetakemon("Jesuskou","Inferior",1);
        Boolean a;
        a=u.checkExistent("nombre", u.getNombre());
        if (a)
        {
            u.insert();
        }
    }
    @org.junit.Test
    public void insert3(){
        Eetakemon u = new Eetakemon("Francerpie","Inferior",1);
        Boolean a;
        a=u.checkExistent("nombre", u.getNombre());
        if (a)
        {
            u.insert();
        }
    }
    @org.junit.Test
    public void insert4(){
        Eetakemon u = new Eetakemon("Davyphno","Normal",1);
        Boolean a;
        a=u.checkExistent("nombre", u.getNombre());
        if (a)
        {
            u.insert();
        }
    }
    @org.junit.Test
    public void insert5(){
        Eetakemon u = new Eetakemon("Jordinine","Normal",1);
        Boolean a;
        a=u.checkExistent("nombre", u.getNombre());
        if (a)
        {
            u.insert();
        }
    }
    @org.junit.Test
    public void insert6(){
        Eetakemon u = new Eetakemon("Lluiskarp","Inferior",30);
        Boolean a;
        a=u.checkExistent("nombre", u.getNombre());
        if (a)
        {
            u.insert();
        }
    }
    @org.junit.Test
    public void insert7(){
        Eetakemon u = new Eetakemon("Mewdecerio","Inferior",30);
        Boolean a;
        a=u.checkExistent("nombre", u.getNombre());
        if (a)
        {
            u.insert();
        }
    }
    @org.junit.Test
    public void update(){
        User u = new User();
        u.select(3);
        u.setNombre("Josefantuan");
        u.update();
    }

    @org.junit.Test
    public void select(){
        User u = new User("Guillem","Guillem", "pepe@gmail.com");
        u.select(0);

    }

    @org.junit.Test
    public void delete() {
        User u = new User();
        u.select(4);
        u.delete();
    }

    @org.junit.Test
    public void admin() {
        User u = new User();
        u.select(5);
        u.setAdmin(1);
        u.update();
    }

    @org.junit.Test
    public void lista() {
        List<Object> list;
        //list=new User().findAll();
        System.out.println();
    }
}
