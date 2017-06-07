package Model.Location;

public class Location {

    private double latitud;
    private double longitud;

    public Location(){}

    public Location(double latitud, double longitud)
    {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Location [latitud=" + latitud + ", longitud=" + longitud + "]";
    }
}
