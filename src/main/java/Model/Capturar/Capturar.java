package Model.Capturar;

import Model.Location.Location;

/**
 * Created by aleix on 05/06/2017.
 */
public class Capturar {
    private String nombre;
    private String tipo;
    public Location LatLong;

    public Capturar(){}

    public Capturar(String nombre, String tipo, Location location)
    {
        this.nombre = nombre;
        this.tipo = tipo;
        this.LatLong= location;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Location getLatLong() {
        return LatLong;
    }

    public void setLatLong(Location latLong) {
        LatLong = latLong;
    }
}
