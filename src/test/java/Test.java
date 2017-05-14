/**
 * Created by Mikel on 15/03/2017.
 */
import Modelo.Eetakemon.Eetakemon;
import Modelo.Eetakemon.EetakemonManager;
import Modelo.User.User;
import Modelo.User.UserManager;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.util.List;


public class Test {
    UserManager um=new UserManager();
    EetakemonManager em = new EetakemonManager();

    @Before
    public void setUp() throws Exception {//parametros iniciales antes de testear

    }

    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void insert(){
        User u = new User("a", "a", "a");
        u.setAdmin(1);
        new UserManager().register(u);
    }

      @org.junit.Test
    public void insert1(){
        Eetakemon u = new Eetakemon("Bernorlax","Normal",15);
        em.addEetakemon(u);
    }

    @org.junit.Test
    public void insert2(){
        Eetakemon u = new Eetakemon("Jesuskou","Inferior",1);
        em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert3(){
        Eetakemon u = new Eetakemon("Francerpie","Inferior",1);
        em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert4(){
        Eetakemon u = new Eetakemon("Davyphno","Normal",1);
        em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert5(){
        Eetakemon u = new Eetakemon("Jordinine","Normal",1);
        em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert6(){
        Eetakemon u = new Eetakemon("Lluiskarp","Inferior",30);
        em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert7(){
        Eetakemon u = new Eetakemon("Mewdecerio","Inferior",30);
        em.addEetakemon(u);
    }
}
