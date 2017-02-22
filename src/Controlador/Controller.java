package Controlador;

import Modelo.eetacemon;

import java.util.*;

/**
 * Created by aleix on 21/2/2017.
 */
public class Controller {

    //On van les funcions
    public List<eetacemon> l;

    public Controller(){
        l = new ArrayList<eetacemon>();
    }

    public void añadireetacemon(eetacemon e){
        System.out.println(l.size());
        l.add(e);
        System.out.println(l.size());
    }

    public Boolean borrarEetacemonPorId(int id){
        /*for(int i=0;i<l.size();i++)
        {
            if (==id)
                l.remove(i);
        }*/

    return true;
    }

    public List<eetacemon> listarTodos(){
        /*for(int i=0;i<l.size();i++)
        {
            System.out.println (l.get(i));
        }*/
        return l;
    }
    /*public eetacemon buscarPorNombre(String nombre){

    }*/
}
