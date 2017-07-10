package marti.tc_informacion_dispositivo.infodispositivo;

import android.os.Build;

/**
 * Created by Daniel on 06/07/2017.
 */

public class getModelo {



    public static String getModeloDispositivo() {

        /* Usamos la clase Build para extraer informacion de las propiedades del sistema */
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        if (model.startsWith(manufacturer)) {
            return model;
        }else{
            return manufacturer + " " + model;
        }

    }


    }
