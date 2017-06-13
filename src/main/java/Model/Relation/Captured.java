package Model.Relation;


public class Captured {
    int idEetakemon;
    String name;
    int level;
    String foto;

        public Captured(){}


    public Captured(int idEetakemon, String name, int level, String foto) {
        this.idEetakemon=idEetakemon;
        this.name=name;
        this.level=level;
        this.foto=foto;
    }




    public int getIdEetakemon() {
        return idEetakemon;
    }

    public void setIdEetakemon(int idEetakemon) {
        this.idEetakemon = idEetakemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
