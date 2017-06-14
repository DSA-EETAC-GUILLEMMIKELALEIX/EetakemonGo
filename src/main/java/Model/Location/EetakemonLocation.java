package Model.Location;

import Model.Eetakemon.Eetakemon;


public class EetakemonLocation {
    private Eetakemon eetakemon;
    public Location latLong;

    public EetakemonLocation(){}

    public EetakemonLocation(Eetakemon eetakemon, Location location)
    {
        this.eetakemon  = eetakemon;
        this.latLong = location;
    }

    public Eetakemon getEetakemon() {
        return eetakemon;
    }

    public void setEetakemon(Eetakemon eetakemon) {
        this.eetakemon = eetakemon;
    }

    public Location getLatLong() {
        return latLong;
    }

    public void setLatLong(Location latLong) {
        this.latLong = latLong;
    }
}
