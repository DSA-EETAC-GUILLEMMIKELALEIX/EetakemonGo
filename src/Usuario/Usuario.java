package Usuario;

public class Usuario{

    private int id;
    private String nombre;
    private String contrasena;

    public Usuario (String nombre, String contrasena){
        this. nombre=nombre;
        this.contrasena=contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getId() {
        return id;
    }
}