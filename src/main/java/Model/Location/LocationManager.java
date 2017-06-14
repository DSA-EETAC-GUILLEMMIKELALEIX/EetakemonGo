package Model.Location;

import Model.Eetakemon.Eetakemon;
import Model.Eetakemon.EetakemonManager;
import Model.Exceptions.UnauthorizedException;

import javax.ws.rs.core.HttpHeaders;
import java.util.*;


public class LocationManager {

    /*LatLng aa = new LatLng(41.27539318720677, 1.9851908683449437); //Biblioteca
    LatLng bb = new LatLng(41.274566700768275, 1.9851908683449437);//Residencia
    LatLng cc = new LatLng(41.275224665468244, 1.986177653066079); //Entrada EETAC-1
    LatLng dd = new LatLng(41.275561305325134, 1.9871539771884272);//Entrada EETAC-2
    LatLng ee = new LatLng(41.27564395319903, 1.9865638912051509); //Entrada ESAB
    LatLng ff = new LatLng(41.27557178752102, 1.9858227968461506); //Canasta Basquet
    LatLng gg = new LatLng(41.275515250643224, 1.9840220282936217); //Pont
    LatLng hh = new LatLng(41.27581166751877, 1.9877861738041247); //Edifici professors
    LatLng ii = new LatLng(41.27523514765666, 1.9881053566871287); //Entrada UOC
    LatLng jj = new LatLng(41.275731035685105, 1.989977538569292); //Parking*/

    private final Location aa = new Location(41.27539318720677, 1.9851908683449437);
    private final Location bb = new Location(41.274566700768275, 1.9851908683449437);
    private final Location cc = new Location(41.275224665468244, 1.986177653066079);
    private final Location dd = new Location(41.275561305325134, 1.9871539771884272);
    private final Location ee = new Location(41.27564395319903, 1.9865638912051509);
    private final Location ff = new Location(41.27557178752102, 1.9858227968461506);
    private final Location gg = new Location(41.275515250643224, 1.9840220282936217);
    private final Location hh = new Location(41.27581166751877, 1.9877861738041247);
    private final Location ii = new Location(41.27523514765666, 1.9881053566871287);
    private final Location jj = new Location(41.275731035685105, 1.989977538569292);

    private Random rand = new Random();

    public Location getLocation() {
        Location locat = new Location();

        int n = rand.nextInt(9);

        if (n == 0) {
            locat = aa;
        }
        if (n == 1) {
            locat = bb;
        }
        if (n == 2) {
            locat = cc;
        }
        if (n == 3) {
            locat = dd;
        }
        if (n == 4) {
            locat = ee;
        }
        if (n == 5) {
            locat = ff;
        }
        if (n == 6) {
            locat = gg;
        }
        if (n == 7) {
            locat = hh;
        }
        if (n == 8) {
            locat = ii;
        }
        if (n == 9) {
            locat = jj;
        }

        return locat;
    }

    public List<EetakemonLocation> getListMap(HttpHeaders header) throws UnauthorizedException{
        List<EetakemonLocation> list = new ArrayList<EetakemonLocation>();
        Location loc= new Location();
        List<Location> locations= new ArrayList<>();
        EetakemonManager em = new EetakemonManager();
        Eetakemon inferior= new Eetakemon();
        Eetakemon normal = new Eetakemon();
        Eetakemon legend = new Eetakemon();
        boolean repeat=true;

        try {

            inferior = em.getOneByType(header, "Inferior");
            normal=em.getOneByType(header, "Normal");
            legend=em.getOneByType(header, "Legendario");
            loadLIstLocations(locations);

            //añadir tres inferiores
            for(int i=0; i<3;i++){
                EetakemonLocation temp=new EetakemonLocation();
                temp.setEetakemon(inferior);
                temp.setLatLong(generateNewLocation(locations));
                list.add(temp);
                inferior = em.getOneByType(header, "Inferior");
            }

            //añadir normal
            list.add(new EetakemonLocation(normal, generateNewLocation(locations)));

            //añadir legendario
            list.add(new EetakemonLocation(legend, generateNewLocation(locations)));

        }catch(UnauthorizedException ex){
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }

        return list;
    }

    private Location generateNewLocation(List<Location> l){
        int i= rand.nextInt(l.size());
        Location temp=l.get(i);
        l.remove(i);

        return temp;
    }

    private void loadLIstLocations(List<Location> l){
        l.add(aa);
        l.add(bb);
        l.add(cc);
        l.add(dd);
        l.add(ee);
        l.add(ff);
        l.add(gg);
        l.add(hh);
        l.add(ii);
        l.add(jj);

    }
}
