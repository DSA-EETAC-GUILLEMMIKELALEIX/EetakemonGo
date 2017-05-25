/**
 * Created by Mikel on 15/03/2017.
 */
import Modelo.Eetakemon.Eetakemon;
import Modelo.Eetakemon.EetakemonManager;
import Modelo.Relation.Relation;
import Modelo.Relation.RelationManager;
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
    public void insertuser1(){
        User u = new User("a", "b", "b");
        u.setAdmin(1);
        new UserManager().register(u);
    }
    @org.junit.Test
    public void insertuser2(){
        User u = new User("guillem", "guillem", "guillem@gmail.com");
        u.setAdmin(1);
        new UserManager().register(u);
    }
      @org.junit.Test
    public void insert1(){
        Eetakemon u = new Eetakemon("Bernorlax","Normal",15);
        //em.addEetakemon(u);
    }

    @org.junit.Test
    public void insert2(){
        Eetakemon u = new Eetakemon("Jesuskou","Inferior",1);
        //em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert3(){
        Eetakemon u = new Eetakemon("Francerpie","Inferior",1);
        //em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert4(){
        Eetakemon u = new Eetakemon("Davyphno","Normal",1);
        //em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert5(){
        Eetakemon u = new Eetakemon("Jordinine","Normal",1);
        //em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert6(){
        Eetakemon u = new Eetakemon("Lluiskarp","Inferior",30);
       // em.addEetakemon(u);
    }
    @org.junit.Test
    public void insert7(){
        Eetakemon u = new Eetakemon("Mewdecerio","Inferior",30);
        //em.addEetakemon(u);
    }
    @org.junit.Test
    public void insertall(){
        Eetakemon a = new Eetakemon("Mewdecerio","Inferior",30);
        /*em.addEetakemon(a);
        Eetakemon b = new Eetakemon("Lluiskarp","Inferior",30);
        em.addEetakemon(b);
        Eetakemon c = new Eetakemon("Jordinine","Normal",1);
        em.addEetakemon(c);
        Eetakemon d = new Eetakemon("Davyphno","Normal",1);
        em.addEetakemon(d);
        Eetakemon e = new Eetakemon("Francerpie","Inferior",1);
        em.addEetakemon(e);
        Eetakemon f = new Eetakemon("Jesuskou","Inferior",1);
        em.addEetakemon(f);
        Eetakemon g = new Eetakemon("Bernorlax","Normal",15);
        em.addEetakemon(g);*/

    }

    @org.junit.Test
    public void relation(){
        RelationManager rm=new RelationManager();
        Relation r = new Relation(1,4,10);
        rm.addRelation(r);
    }
    @org.junit.Test
    public void relation2(){
        RelationManager rm=new RelationManager();
        Relation r = new Relation(1,2,10);
        rm.addRelation(r);
    }

    @org.junit.Test
    public void relation3(){
        RelationManager rm=new RelationManager();
        Relation r = new Relation(1,2,10);
        rm.getCaptured(1);
    }
}
