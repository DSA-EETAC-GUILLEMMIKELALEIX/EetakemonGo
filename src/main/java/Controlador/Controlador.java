package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;

import java.util.*;

//Clase controlador
public class Controlador {

    public static Controlador c;

    private Hashtable<Integer, Eetakemon> tablaEetakemons;
    private Hashtable<Integer, Usuario> tablaUsuarios;
    private int eetakemonID=0;
    private int usuarioID=0;


    public static Controlador getControlador(){

        if(c == null){
            c = new Controlador();
        }

        return c;
    }


    private Controlador(){

        tablaEetakemons = new Hashtable();
        tablaUsuarios = new Hashtable();
    }
    //metodo que añade un eetakemon a la lista
    public void añadireATabla(Object o){

        if (o.getClass().equals(Eetakemon.class)) {
            Eetakemon eTemp = (Eetakemon) o;
            eTemp.setId(eetakemonID);
            tablaEetakemons.put(eetakemonID, eTemp);
            eetakemonID++;
        }
        else if(o.getClass().equals(Usuario.class)){
            Usuario uTemp = (Usuario) o;
            uTemp.setId(usuarioID);
            tablaUsuarios.put(usuarioID, uTemp);
            usuarioID++;
        }
    }

    //metodo que borra un eetakemon por ID
    public Boolean borrarEetakemonPorId(int id){

        boolean borrado=false;

        if (tablaEetakemons.remove(id)!= null){
            borrado=true;
        }

        return borrado;
    }

    public Boolean borrarUsuarioPorId(int id){

        boolean borrado=false;

        if (tablaUsuarios.remove(id)!= null){
            borrado=true;
        }

        return borrado;
    }


    //metodo que lista los eetakemons
    public void listarTodos(){
        Enumeration<Eetakemon> enumTemp = tablaEetakemons.elements();
        List<Eetakemon> listaTemp = Collections.list(enumTemp);

        if(listaTemp.isEmpty()){
            System.out.println("\nLa lista está vacía");
        }else {
            System.out.println("\nLista de Eetakemon:");
            for (Eetakemon e : listaTemp) {
                System.out.println(e.getId()+ " "+e.getNombre() + " " +
                        e.getTipo() + " " + e.getNivel() + " " + e.getAtaque());
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
    //metodo busquedaAvanzada (BOLA EXTRA)
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
    public void añadirusuario(Object eetacemon){
    Usuario añadir = (Usuario) eetacemon;
    }
    public boolean registrovalido(String email){

    }
    public boolean loginvalido(String email, String contrasena){

    }
}
