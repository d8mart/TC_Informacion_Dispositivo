package marti.tc_informacion_dispositivo.infodispositivo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Daniel on 07/07/2017.
 */

public class getLocalizacion {

     Context context;
     double latitude,longitude;

     public getLocalizacion(Context context){
        this.context=context;
     }


     public String getDireccion(Location location) {

         latitude = location.getLatitude();
         longitude = location.getLongitude();

         Geocoder geocoder;
         List<Address> addresses;
         geocoder = new Geocoder(context, Locale.getDefault());

           try{
               addresses = geocoder.getFromLocation(latitude, longitude, 1);
               if(addresses.size()>0) {
                   String address = addresses.get(0).getAddressLine(0); //si address.size = 0 falla
                   String city = addresses.get(0).getLocality();
                   Log.i("Address", address + "-" + city);
                   return address + " - " + city;
               }
               return "AddressNotFound";
           } catch (IOException e) {
               e.printStackTrace();
           }
               return "AddressNotFound";
         }

     }


