package Modelo;

import javafx.scene.image.Image;

// Clase que define y permite crear objetos Eetakemon
public class Eetakemon {
    private String nombre;
    private String tipo;
    private Image foto;
    private int nivel;
    private String ataque;

    public Eetakemon(String name, String tipo, Image foto, int level, String ataque)
    {
        this.nombre = name;
        this.tipo = tipo;
        this.foto = foto;
        this.nivel=level;
        this.ataque=ataque;
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
    public Image getFoto() {
        return foto;
    }
    //Obtener Nivel
    public int getNivel() {
        return nivel;
    }
    //Obtener ataque
    public String getAtaque() {
        return ataque;
    }
}

