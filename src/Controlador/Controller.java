package Controlador;

import Modelo.Eetakemon;

import java.util.*;

/**
 * Created by aleix on 21/2/2017.
 */
public class Controller {

    //On van les funcions
    private List<Eetakemon> lista;

    public Controller(){

        lista = new ArrayList<Eetakemon>();
    }

    public void añadireetacemon(Eetakemon e){
        lista.add(e);
        System.out.println(lista.isEmpty());
        System.out.println(lista.size());
    }

    public Boolean borrarEetacemonPorId(int id){
        int i=0;
        boolean borrado=false;

        for (Eetakemon ek:lista) {
            if (ek.getId()==id){
                lista.remove(i);
                borrado=true;
                break;
            }
            System.out.println("prueba");
            i++;
        }

    return borrado;
    }

    public void listarTodos(){
        /*if(lista.isEmpty()){
            System.out.println("\nLa lista está vacía\n");
        }else {
            for (Eetakemon ek : lista) {
                System.out.println("Lista de Eetakemon:\n" + ek.getId() + " " + ek.getNombre() + " " + ek.getNivel() + "\n");
            }
        }*/

        for (Eetakemon ek : lista) {
            System.out.println("Lista de Eetakemon:\n" + ek.getId() + " " + ek.getNombre() + " " + ek.getNivel() + "\n");
        }
    }
}
