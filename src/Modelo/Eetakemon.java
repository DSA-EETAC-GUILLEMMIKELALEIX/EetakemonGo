package Modelo;

/**
 * Created by aleix on 21/2/2017.
 */
public class Eetakemon {
    public String nombre;
    int id;
    int nivel;

    public Eetakemon(String name, int id, int level)
    {
        this.nombre = name;
        this.id= id;
        this.nivel=level;
    }

    //obtener nombre
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public int getNivel() {
        return nivel;
    }
}

