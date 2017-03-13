package Modelo;

public class Usuario{

    private int id;
    private String nombre;
    private String contrasena;
    private String email;

    public Usuario (String nombre, String contrasena, String email){
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.email=email;
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

    public String getEmail() { return email; }
}