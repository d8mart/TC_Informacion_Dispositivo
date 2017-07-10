package marti.tc_informacion_dispositivo.infodispositivo;

import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 06/07/2017.
 */

public class getIP {


    public static String getLocalIpAddress(){ //devuelve la ipv4 del dispositivo

        try {
            List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());/*requiere permiso de Internet*/
            for(NetworkInterface networkInterface : networkInterfaces ){
                List<InetAddress> inetAddresses = Collections.list(networkInterface.getInetAddresses()); /*  */
                for (InetAddress inetAddress : inetAddresses) {

                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {  /*  */
                        String ip = inetAddress.getHostAddress();
                        return ip;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace(); Log.i("IP Address", e.toString());
        }

        return "0.0.0.0";
    }
}
