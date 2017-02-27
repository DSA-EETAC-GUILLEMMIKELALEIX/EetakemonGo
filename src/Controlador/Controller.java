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

        lista = new ArrayList<Eetakemon>();//crear la lista
    }

    public void añadireetacemon(Eetakemon e){
        lista.add(e);//añadir a la lista
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
        if(lista.isEmpty()){
            System.out.println("\nLa lista está vacía");
        }else {
            System.out.println("\nLista de Eetakemon:");
            for (Eetakemon ek : lista) {
                System.out.println(ek.getId() + " " +
                        ek.getNombre() + " " + ek.getNivel());
            }
        }
        System.out.println("\n");
    }

    public void buscarPorNombre(String nombre){
        boolean encontrado=false;

        System.out.println("\nResultados obtenidos:");

        for (Eetakemon ek:lista) {
            if (ek.getNombre().equalsIgnoreCase(nombre)){
                System.out.println(ek.getId() + " " +
                        ek.getNombre() + " " + ek.getNivel());
                encontrado=true;
            }
        }

        if(!encontrado){
            System.out.println("No se ha encontrado ningun resultado");
        }

        System.out.println("\n");
    }

    public void busquedaAvanzada(String s){
        boolean encontrado=false;

        System.out.println("\nResultados obtenidos:");

        for (Eetakemon ek:lista) {
            if (ek.getNombre().contains(s)){
                System.out.println(ek.getId() + " " +
                        ek.getNombre() + " " + ek.getNivel());
                encontrado=true;
            }
        }

        if(!encontrado){
            System.out.println("No se ha encontrado ningun resultado");
        }

        System.out.println("\n");
    }
}
