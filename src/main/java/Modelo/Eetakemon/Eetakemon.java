package Modelo.Eetakemon;

// Clase que define y permite crear objetos Eetakemon
public class Eetakemon extends EetakemonDAO{
    private int id;
    private String nombre;
    private int nivel;
    private String tipo;
    private String foto;

    public Eetakemon(){}

    public Eetakemon(String nombre, String tipo, /*Image foto,*/ int level)
    {
        this.nombre = nombre;
        this.tipo = tipo;
        this.foto = "foto";
        this.nivel=level;
    }

    public Eetakemon(String nombre, int level)
    {
        this.nombre = nombre;
        this.nivel=level;
        this.tipo="tipo";
    }

    public int getId() {
        return id;
    }

    //Obtener nombre
    public String getNombre() {
        return nombre;
    }
    //Obtener Tipo
    public String getTipo() {
        return tipo;
    }
    //Obtener foto

    //Obtener Nivel
    public int getNivel() {
        return nivel;
    }

    //definir Id
    public void setId(int id) {
        this.id = id;
    }
    //definir Nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //definir Tipo
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    //definir Foto

    //definir Nivel
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Eetakemon [Id = "+id+", nombre=" + nombre + ", tipo=" + tipo + /*", foto=" + foto +*/ ", nivel=" + nivel+"]";
    }
}

