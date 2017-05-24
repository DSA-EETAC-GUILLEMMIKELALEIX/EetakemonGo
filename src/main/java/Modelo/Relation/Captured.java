package Modelo.Relation;


/**
 * Created by usuario on 17/05/2017.
 */
public class Captured {
    int idEetakemon;
    String name;
    int level;

    public Captured(int idEetakemon, String name, int level) {
        this.idEetakemon=idEetakemon;
        this.name=name;
        this.level=level;
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
}
