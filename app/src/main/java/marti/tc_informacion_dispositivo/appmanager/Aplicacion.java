package marti.tc_informacion_dispositivo.appmanager;

import android.graphics.Bitmap;

/**
 * Created by Daniel on 08/07/2017.
 */

public class Aplicacion {

    String nombreApp;
    String nombrePaquete;
    Bitmap iconoApp;
    String fechaInstalacionApp;

    public Aplicacion(String nombreApp, String nombrePaquete, Bitmap iconoApp, String fechaInstalacionApp){

        this.nombreApp=nombreApp;
        this.nombrePaquete=nombrePaquete;
        this.iconoApp=iconoApp;
        this.fechaInstalacionApp=fechaInstalacionApp;

    }


    public String getNombreApp() {
        return nombreApp;
    }

    public void setNombreApp(String nombreApp) {
        this.nombreApp = nombreApp;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public Bitmap getIconoApp() {
        return iconoApp;
    }

    public void setIconoApp(Bitmap iconoApp) {
        this.iconoApp = iconoApp;
    }

    public String getFechaInstalacionApp() {
        return fechaInstalacionApp;
    }

    public void setFechaInstalacionApp(String fechaInstalacionApp) {
        this.fechaInstalacionApp = fechaInstalacionApp;
    }
}
