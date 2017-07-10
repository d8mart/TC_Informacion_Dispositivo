package marti.tc_informacion_dispositivo.infodispositivo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by Daniel on 06/07/2017.
 */

public class getMAC {

  Context context;

  public getMAC(Context context){
      this.context=context;
  }


  public static String  getMacApi23(){

      try {
          List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());

          for (NetworkInterface nif : all) {

              if (!nif.getName().equalsIgnoreCase("wlan0")) continue; /*wlan es lan inalambrica*/

              byte[] macBytes = nif.getHardwareAddress(); /*devuelve la mac en bytes, un digito hex son 4 bits*/
              if (macBytes == null) {
                  return "";
              }

              StringBuilder res1 = new StringBuilder();
              for (byte b : macBytes) {
                  res1.append(Integer.toHexString(b & 0xFF) + ":"); /*operacion logica AND y convertimos los byte a hexadecimales*/
              }

              if (res1.length() > 0) {
                  res1.deleteCharAt(res1.length() - 1); /*eliminamos el ultimo caracter que es un : */
              }

              return res1.toString();
          }




      } catch (SocketException e) {
          e.printStackTrace();
      }

      return "";


  }

    public String getMacWifiManager(){
        WifiManager manager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo(); /* necesita permiso ACCESS_WIFI_STATE */
        String address = info.getMacAddress();
        if(address==null) {
            address = "";
        }
        return address;
    }


    public String getMacAddress(){

        if (Build.VERSION.SDK_INT >= 23) { /*Agregar permiso en tiempo de ejecucion*/

            return getMacApi23();

        }else{
            return getMacWifiManager();
        }
    }


}
