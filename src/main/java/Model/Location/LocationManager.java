package Model.Location;

import Model.Location.Location;

import java.util.Random;

/**
 * Created by aleix on 05/06/2017.
 */
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

    private Location aa = new Location(41.27539318720677, 1.9851908683449437);
    private Location bb = new Location(41.274566700768275, 1.9851908683449437);
    private Location cc = new Location(41.275224665468244, 1.986177653066079);
    private Location dd = new Location(41.275561305325134, 1.9871539771884272);
    private Location ee = new Location(41.27564395319903, 1.9865638912051509);
    private Location ff = new Location(41.27557178752102, 1.9858227968461506);
    private Location gg = new Location(41.275515250643224, 1.9840220282936217);
    private Location hh = new Location(41.27581166751877, 1.9877861738041247);
    private Location ii = new Location(41.27523514765666, 1.9881053566871287);
    private Location jj = new Location(41.275731035685105, 1.989977538569292);


    public Location getLocations() {
        Location locat = new Location();
        Random rand = new Random();
        int n = rand.nextInt(9);

        if(n==0){
            locat=aa;
        }
        if(n==1){
            locat=bb;
        }
        if(n==2){
            locat=cc;
        }
        if(n==3){
            locat=dd;
        }
        if(n==4){
            locat=ee;
        }
        if(n==5){
            locat=ff;
        }
        if(n==6){
            locat=gg;
        }
        if(n==7){
            locat=hh;
        }
        if(n==8){
            locat=ii;
        }
        if(n==9){
            locat=jj;
        }

        return locat;
    }
}