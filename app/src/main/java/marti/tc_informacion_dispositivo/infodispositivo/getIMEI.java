package marti.tc_informacion_dispositivo.infodispositivo;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Daniel on 06/07/2017.
 */

public class getIMEI {

    Context context;


    public getIMEI(Context context){
        this.context=context;

    }


    public String getNumeroIMEI(){ /* necesita permiso READ_PHONE_STATE */

        String myIMEI = "";
        try {
            TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null) {
                myIMEI = mTelephony.getDeviceId();
                Log.i("IMEI", myIMEI);
                return myIMEI;
            }
        }catch (Exception e){e.printStackTrace();}
        return "";
    }

}
