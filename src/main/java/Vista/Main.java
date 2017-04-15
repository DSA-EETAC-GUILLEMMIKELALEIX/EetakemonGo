package Vista;
import Controlador.Controlador;
import Modelo.Eetakemon;
import javafx.scene.image.Image;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;

import java.net.URI;
import java.util.Scanner;
/**
 * Codigo creado por Mikel, Guillem, Aleix y Jose Antonio. Se ha escrito el codigo en local y se ha subido
 * directamente al repositorio github de Mikel.
 */
public class Main {

    //Base URI
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("Vista");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    //Main
    public static void main (String [ ] args) throws IOException{

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();




        String entradaTeclado = "";
        Scanner scanner = new Scanner (System.in); //Creación de un objeto Scanner
        boolean bucle=true;
        Controlador controlador = Controlador.getControlador();

        System.out.println ("Empezamoos el programa");

        while(bucle) {
            String nom="";
            String tipo="";
            Image foto=null;
            String ataque="";

            int nivel=0;


            System.out.println ("Por favor introduzca numero: \n" +
                    "1-Añadir eetac-emon\n" +
                    "2-Borrar eetac-emon\n" +
                    "3-Listar eetac-emon\n" +
                    "4-Buscar por nombre\n"+
                    "5-Busqueda avanzada\n"+
                    "6-Añadir usuario\n"+
                    "7-Salir");

            entradaTeclado = scanner.nextLine (); //Invocamos un método sobre un objeto Scanner

            switch (Integer.parseInt(entradaTeclado)) {
                case 1:
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
                    controlador.anadirATabla(eetakemon);

                    System.out.println("\n");
                    break;
                case 2:
                    boolean borrado = false;
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

                    borrado= controlador.borrarEetakemonPorId(idTemp);

                    if (borrado) {
                        System.out.println("Borrado correctamente");
                    } else {
                        System.out.println("Eetakemon no encontrado");
                    }
                    break;
                case 3:
                    //Llamar a la función Listar si pasarle nada y que devuelva la lista de eetacemons
                    controlador.listarEetakemon();
                    break;
                case 4:
                    //Buscar eetakemon
                    System.out.println("\nEscribe el nombre eetac-emon que quieras buscar:");
                    entradaTeclado = scanner.nextLine(); //Invocamos un método sobre un objeto Scanner
                    controlador.buscarPorNombre(entradaTeclado);
                    break;
                case 5:
                    //Fragmento a buscar BOLA EXTRA
                    System.out.println("\nEscribe el fragmento a buscar:");
                    entradaTeclado = scanner.nextLine(); //Invocamos un método sobre un objeto Scanner
                    controlador.busquedaAvanzada(entradaTeclado);
                    break;
                case 6:

                case 7:
                    //Para salir
                    System.out.println("\nAdiós");
                    bucle=false;
                    break;
            }
        }
        server.stop();
    }
}
