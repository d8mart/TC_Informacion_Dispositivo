package marti.tc_informacion_dispositivo.infodispositivo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Daniel on 06/07/2017.
 */

public class getNombreWifi {

    Context context;

    public getNombreWifi(Context context){
        this.context=context;
    }

    public String getNombreRed(){
        try {
            WifiManager wifiMgr = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            String name = wifiInfo.getSSID();
            return name;
        }catch (Exception e){e.printStackTrace();}
        return "";
    }
}
