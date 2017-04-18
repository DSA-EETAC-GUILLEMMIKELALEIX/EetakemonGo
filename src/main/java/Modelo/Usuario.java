package Modelo;
import Controlador.DAO;

public class Usuario extends DAO{

    private int id;
    private String nombre;
    private String contrasena;
    private String email;

    public Usuario(){}

    public Usuario (String nombre, String contrasena, String email){
        this.nombre=nombre;
        this.contrasena=contrasena;//
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [Id ="+id+", Nombre=" + nombre + ", contrasena=" + contrasena + ", email=" + email + "]";
    }
}