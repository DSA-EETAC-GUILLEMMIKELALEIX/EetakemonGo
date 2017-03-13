package Controlador;

import Modelo.Eetakemon;

import java.util.*;

//Clase controlador
public class Controller {

    private List<Eetakemon> lista;

    public Controller(){

        tablaEetakemons=new Hashtable<Integer,Eetakemon>();
        tablaUsuarios=new Hashtable<Integer,Usuario>();
    }
    //metodo que añade un eetakemon a la lista
    public void añadireetakemon(Eetakemon e){
        lista.add(e);//añadir a la lista
    }
    //metodo que borra un eetakemon por ID
    public Boolean borrarEetakemonPorId(int id){
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
    //metodo que lista los eetakemons
    public void listarTodos(){
        if(lista.isEmpty()){
            System.out.println("\nLa lista está vacía");
        }else {
            System.out.println("\nLista de Eetakemon:");
            for (Eetakemon ek : lista) {
                System.out.println(ek.getNombre() + " " +
                        ek.getTipo() + " " + ek.getFoto() + " " + ek.getNivel() + " " + ek.getAtaque());
            }
        }
        System.out.println("\n");
    }
    //metodo que busca eetakemons por nombre
    public void buscarPorNombre(String nombre){
        boolean encontrado=false;

        System.out.println("\nResultados obtenidos:");

        for (Eetakemon ek:lista) {
            if (ek.getNombre().equalsIgnoreCase(nombre)){
                System.out.println(ek.getNombre() + " " +
                        ek.getTipo() + " " + ek.getFoto() + " " + ek.getNivel() + " " + ek.getAtaque());
                encontrado=true;
            }
        }

        if(!encontrado){
            System.out.println("No se ha encontrado ningun resultado");
        }

        System.out.println("\n");
    }
    //metodo busquedaAvanzada (BOLA EXTRA)
    public void busquedaAvanzada(String s){
        boolean encontrado=false;

        System.out.println("\nResultados obtenidos:");

        for (Eetakemon ek:lista) {
            if (ek.getNombre().contains(s)){
                System.out.println(ek.getNombre() + " " +
                        ek.getTipo() + " " + ek.getFoto() + " " + ek.getNivel() + " " + ek.getAtaque());
                encontrado=true;
            }
        }

        if(!encontrado){
            System.out.println("No se ha encontrado ningun resultado");
        }

        System.out.println("\n");
    }
}
