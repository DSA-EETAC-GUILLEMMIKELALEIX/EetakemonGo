package Vista;
import Controlador.Controller;
import Modelo.eetacemon;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Scanner;
/**
 * Created by aleix on 21/2/2017.
 */
public class Main {

    public static void main (String [ ] args) {
        System.out.println ("Empezamos el programa");
        Boolean ffff=true;

        while(ffff) {
            System.out.println ("Por favor introduzca numero: \n" +
                    "1-Añadir eetac-emon\n" +
                    "2-Borrar eetac-emon\n" +
                    "3-Listar eetac-emon\n" +
                    "4-Buscar por nombre\n");
            String entradaTeclado = "";
            Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
            entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner

            String nom="";
            int id =0;
            int nivell=0;

            Controller c = new Controller();
            eetacemon d = new eetacemon(nom,id,nivell);


            switch (entradaTeclado) {
                case "1":
                    System.out.println("Escribe el nombre del eetac-emon que quieras añadir:");
                    String entradaTeclado1a = "";
                    Scanner nombreeetacemon = new Scanner(System.in);
                    entradaTeclado1a = nombreeetacemon.nextLine(); //Invocamos un método sobre un objeto Scanner

                    int idd = 0;

                    System.out.println("Escribe el nivel del eetac-emon que quieras añadir:");
                    String entradaTeclado1c = "";
                    Scanner niveleetacemon = new Scanner(System.in);
                    entradaTeclado1c = niveleetacemon.nextLine(); //Invocamos un método sobre un objeto Scanner

                    int nivel = Integer.parseInt(entradaTeclado1c);

                    eetacemon a = new eetacemon(entradaTeclado1a, idd, nivel);
                    c.añadireetacemon(a);
                    idd++;

                    break;
                case "2":
                    boolean aaaa = true;

                    System.out.println("Escribe el id dep eetac-emon que quieras borrar:");
                    String entradaTeclado2 = "";
                    Scanner entradaeetacemon2 = new Scanner(System.in);
                    entradaTeclado2 = entradaeetacemon2.nextLine(); //Invocamos un método sobre u1n objeto Scanner

                    int id1 = Integer.parseInt(entradaTeclado2);

                    aaaa = c.borrarEetacemonPorId(id1);

                    if (aaaa) {
                        System.out.println("Error al borrar:");
                    } else {
                        System.out.println("Borrado correctamente");
                    }
                    break;
                case "3":
                    //Llamar a la función Listar si pasarle nada y que devuelva la lista de eetacemons
                    c.l=c.listarTodos();
                    System.out.println(c.l);
                    break;
                case "4":
                    System.out.println("Escribe el nombre eetac-emon que quieras buscar:");
                    String entradaTeclado3 = "";
                    Scanner entradaeetacemon3 = new Scanner(System.in);
                    entradaTeclado3 = entradaeetacemon3.nextLine(); //Invocamos un método sobre u1n objeto Scanner
                    //Llamar a la funcion buscar pasandole entradaeetacemon2 y que devuelva el eetacemon
                    break;
            }
        }
    } //Cierre del main
}
