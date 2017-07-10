package marti.tc_informacion_dispositivo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import marti.tc_informacion_dispositivo.appmanager.ListaAplicaciones;
import marti.tc_informacion_dispositivo.infodispositivo.getCorreo;
import marti.tc_informacion_dispositivo.infodispositivo.getIMEI;
import marti.tc_informacion_dispositivo.infodispositivo.getIP;
import marti.tc_informacion_dispositivo.infodispositivo.getLocalizacion;
import marti.tc_informacion_dispositivo.infodispositivo.getMAC;
import marti.tc_informacion_dispositivo.infodispositivo.getModelo;
import marti.tc_informacion_dispositivo.infodispositivo.getNivelApi;
import marti.tc_informacion_dispositivo.infodispositivo.getNombreWifi;

public class MainActivity extends AppCompatActivity {

    TextView modelo, mac, ip, api, wifi, imei, correo, ubicacion;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!revisarPermisos()){
           // moveTaskToBack(true);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.GET_ACCOUNTS}, 225);
        }

        modelo = (TextView) findViewById(R.id.modelocontent);
        mac = (TextView) findViewById(R.id.maccontent);
        ip = (TextView) findViewById(R.id.ipcontent);
        api = (TextView) findViewById(R.id.apicontent);
        wifi = (TextView) findViewById(R.id.wificontent);
        imei = (TextView) findViewById(R.id.imeicontent);
        correo = (TextView) findViewById(R.id.correocontent);
        ubicacion = (TextView) findViewById(R.id.ubicacioncontent);


        modelo.setText(getModelo.getModeloDispositivo());
        mac.setText(new getMAC(this).getMacAddress());
        ip.setText(getIP.getLocalIpAddress());
        api.setText(getNivelApi.getSDKVersion());
        wifi.setText(new getNombreWifi(this).getNombreRed());
        imei.setText(new getIMEI(this).getNumeroIMEI());
        correo.setText(getCorreo.getEmailDispositivo(this));



    }


    @Override
    protected void onStart() {
        super.onStart();


        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 225);
                return;
            }
        }

        final getLocalizacion getLoc = new getLocalizacion(this);
        String ultimaUbicacion = getLoc.getDireccion(lm.getLastKnownLocation(lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER));
        ubicacion.setText(ultimaUbicacion);
        LocationListener ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                ubicacion.setText(getLoc.getDireccion(location));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        try {
            lm.requestSingleUpdate(lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER, ll, null);
            lm.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000, 0, ll);
            lm.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    10000, 0, ll);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }



    }


    public boolean revisarPermisos(){


        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.GET_ACCOUNTS}, 225);
                return false;
            }

            return true;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



            switch (requestCode) {
                case 225: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startActivity(new Intent(this,MainActivity.class));
                    }
                }

                //default: finish();
            }

    }

    public void actividadListaApp(View view) {
        Intent intent = new Intent(this, ListaAplicaciones.class);
        startActivity(intent);
    }
}
