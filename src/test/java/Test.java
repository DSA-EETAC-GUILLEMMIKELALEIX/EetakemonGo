/**
 * Created by Mikel on 15/03/2017.
 */
import Modelo.User;

import java.util.List;


public class Test {



    @org.junit.Test
    public void insert(){
        User u = new User("Mikel", "1234", "guillem@gmail.com");
        u.insert();
    }

    @org.junit.Test
    public void update(){
        User u = new User();
        u.select(3);
        u.setNombre("Jose");
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
        u.select(0);
        u.delete();
    }

    @org.junit.Test
    public void lista() {
        List<Object> list;
        //list=new User().findAll();
        System.out.println();
    }
}
