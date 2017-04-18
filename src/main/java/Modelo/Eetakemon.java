package Modelo;
import Controlador.DAO;

// Clase que define y permite crear objetos Eetakemon
public class Eetakemon extends DAO{
    private int id;
    private String nombre;
    private String tipo;
    //private Image foto;
    private int nivel;
    private String ataque;

    public Eetakemon(){}

    public Eetakemon(String nombre, String tipo, /*Image foto,*/ int level, String ataque)
    {
        this.nombre = nombre;
        this.tipo = tipo;
        //this.foto = foto;
        this.nivel=level;
        this.ataque=ataque;
    }

    public Eetakemon(String nombre, int level)
    {
        this.nombre = nombre;
        this.nivel=level;
        this.tipo="tipo";
        this.ataque="ataque";
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

   /* public Image getFoto() {
        return foto;
    }*/

    //Obtener Nivel
    public int getNivel() {
        return nivel;
    }
    //Obtener ataque
    public String getAtaque() {
        return ataque;
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

  /*  public void setFoto(Image foto) {
        this.foto = foto;
    }*/

    //definir Nivel
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    //definir Ataque
    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    @Override
    public String toString() {
        return "Eetakemon [Id = "+id+", nombre=" + nombre + ", tipo=" + tipo + /*", foto=" + foto +*/ ", nivel=" + nivel + ", ataque=" + ataque + "]";
    }
}

