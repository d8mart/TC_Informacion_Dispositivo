package marti.tc_informacion_dispositivo.infodispositivo;

import android.os.Build;

/**
 * Created by Daniel on 06/07/2017.
 */

public class getNivelApi {

    public static String getSDKVersion(){

        int sdkVersion =0;
        sdkVersion = Build.VERSION.SDK_INT;
        return   String.valueOf(sdkVersion);

    }

}
