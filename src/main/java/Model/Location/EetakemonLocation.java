package Model.Location;

import Model.Eetakemon.Eetakemon;


public class EetakemonLocation {
    private Eetakemon eetakemon;
    public Location location;

    public EetakemonLocation(){}

    public EetakemonLocation(Eetakemon eetakemon, Location location)
    {
        this.eetakemon  = eetakemon;
        this.location = location;
    }

    public Eetakemon getEetakemon() {
        return eetakemon;
    }

    public void setEetakemon(Eetakemon eetakemon) {
        this.eetakemon = eetakemon;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
