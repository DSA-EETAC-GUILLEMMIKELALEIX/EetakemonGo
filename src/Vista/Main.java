package Vista;
import Controlador.Controller;
import Modelo.Eetakemon;
import javafx.scene.image.Image;

import java.util.Scanner;
/**
 * Codigo creado por Mikel, Guillem, Aleix y Jose Antonio. Se ha escrito el codigo en local y se ha subido
 * directamente al repositorio github de Mikel.
 */
public class Main {

    static int id=0;
    //Main
    public static void main (String [ ] args) {
        String entradaTeclado = "";
        Scanner scanner = new Scanner (System.in); //Creación de un objeto Scanner
        boolean bucle=true;
        Controller c = new Controller();

        System.out.println ("Empezamos el programa");

        while(bucle) {
            String nom="";
            String tipo="";
            Image foto;
            String ataque="";

            int nivel=0;


            System.out.println ("Por favor introduzca numero: \n" +
                    "1-Añadir eetac-emon\n" +
                    "2-Borrar eetac-emon\n" +
                    "3-Listar eetac-emon\n" +
                    "4-Buscar por nombre\n"+
                    "5-Busqueda avanzada\n"+
                    "6-Salir");

            entradaTeclado = scanner.nextLine (); //Invocamos un método sobre un objeto Scanner

            switch (entradaTeclado) {
                case "1":
                    nivel=-1;

                    System.out.println("\nEscribe el nombre del eetac-emon que quieras añadir:");
                    nom = scanner.nextLine(); //Invocamos un método sobre un objeto Scanner

                    while(nivel==-1) {
                        System.out.println("Escribe el nivel del eetak-emon que quieras añadir:");
                        entradaTeclado = scanner.nextLine(); //Invocamos un método sobre un objeto Scanner
                        try {
                            nivel = Integer.parseInt(entradaTeclado);
                        } catch (NumberFormatException ex) {
                            System.out.println("Nivel no válido");
                        }
                    }

                    Eetakemon eetakemon = new Eetakemon(nom, tipo, foto, nivel, ataque);
                    c.añadireetakemon(eetakemon);
                    id++;

                    System.out.println("\n");
                    break;
                case "2":
                    boolean borrado = true;
                    int idTemp=-1;

                    while(idTemp==-1) {
                        System.out.println("\nEscribe el id del eetak-emon que quieras borrar:");
                        entradaTeclado = scanner.nextLine(); //Invocamos un método sobre u1n objeto Scanner
                        try {
                            idTemp = Integer.parseInt(entradaTeclado);
                        } catch (NumberFormatException ex) {
                            System.out.println("Id no válido");
                        }
                    }

                    /*borrado= c.borrarEetakemonPorId(idTemp);

                    if (borrado) {
                        System.out.println("Borrado correctamente");
                    } else {
                        System.out.println("Eetakemon no encontrado");
                    }*/
                    break;
                case "3":
                    //Llamar a la función Listar si pasarle nada y que devuelva la lista de eetacemons
                    c.listarTodos();
                    break;
                case "4":
                    //Buscar eetakemon
                    System.out.println("\nEscribe el nombre eetac-emon que quieras buscar:");
                    entradaTeclado = scanner.nextLine(); //Invocamos un método sobre un objeto Scanner
                    c.buscarPorNombre(entradaTeclado);
                    break;
                case "5":
                    //Fragmento a buscar BOLA EXTRA
                    System.out.println("\nEscribe el fragmento a buscar:");
                    entradaTeclado = scanner.nextLine(); //Invocamos un método sobre un objeto Scanner
                    c.busquedaAvanzada(entradaTeclado);
                    break;
                case "6":
                    //Para salir
                    System.out.println("\nAdiós");
                    bucle=false;
                    break;
            }
        }
    }
}
