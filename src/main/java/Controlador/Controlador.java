package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

//Clase controlador
public class Controlador {

    //variables
    private static Controlador c;
    private Hashtable<Integer, Eetakemon> tablaEetakemons;
    private Hashtable<Integer, Usuario> tablaUsuarios;
    private int eetakemonID=0;
    private int usuarioID=0;


    private Controlador(){

        tablaEetakemons = new Hashtable();
        tablaUsuarios = new Hashtable();
    }

    //singleton
    public static Controlador getControlador(){

        if(c == null){
            c = new Controlador();
        }

        return c;
    }

    //metodo que añade un eetakemon a la tabla hash
    public void anadirATabla(Object o){

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
    public void listarEetakemon(){
        List<Eetakemon> listaTemp = Collections.list(tablaEetakemons.elements());

        if(listaTemp.isEmpty()){
            System.out.println("\nLa lista está vacía");
        }else {
            System.out.println("\nLista de Eetakemon:");
            /*for (Eetakemon e : listaTemp) {
                System.out.println(e.getId()+ " "+e.getNombre() + " " +
                        e.getTipo() + " " + e.getNivel() + " " + e.getAtaque());
            }*/

            for (Eetakemon e : listaTemp) {
                System.out.println(e.getId() + e.getNivel());
            }
        }
        System.out.println("\n");
    }
    //metodo que busca eetakemons por nombre
    public boolean buscarPorNombre(String nombre){
       List<Eetakemon> listaTemp = Collections.list(tablaEetakemons.elements());

        System.out.println("\nResultados obtenidos:");

        for (Eetakemon ek:listaTemp) {
            if (ek.getNombre().equalsIgnoreCase(nombre)){
                System.out.println(ek.getId() + " " +
                        ek.getNombre() + " " + ek.getNivel());

                return true;
            }
        }

        System.out.println("No se ha encontrado ningun elemento con ese nombre");
        System.out.println("\n");

        return false;
    }

    public Object buscarPorIdEetakemon(int id){
        List<Eetakemon> listaTemp = Collections.list(tablaEetakemons.elements());
        for (Eetakemon ek:listaTemp) {
            if (ek.getId()==id){
                return ek;
            }
        }

        return null;
    }
    public Object buscarPorIdUsuario(int id){
        List<Usuario> listaTemp = Collections.list(tablaUsuarios.elements());
        for (Usuario u:listaTemp) {
            if (u.getId()==id){
                return u;
            }
        }

        return null;
    }
    //metodo busquedaAvanzada (BOLA EXTRA)
    public boolean busquedaAvanzada(String s){
        List<Eetakemon> listaTemp = Collections.list(tablaEetakemons.elements());
        List<Eetakemon> resultados = new ArrayList<Eetakemon>();

        for (Eetakemon ek:listaTemp) {
            if (ek.getNombre().contains(s)){
                resultados.add(ek);
            }
        }

        if (!resultados.isEmpty()){
            System.out.println("\nResultados obtenidos:");

            for (Eetakemon e: resultados){
                System.out.println(e.getId() + " " +
                    e.getNombre() + " " + e.getNivel());
            }

            return true;
        }

        System.out.println("No se ha encontrado ningun elemento");
        System.out.println("\n");
        return false;
    }

    public boolean registrovalido(String email){
        return true;
    }
    public boolean loginvalido(String email, String contrasena){
        return true;
    }
}
