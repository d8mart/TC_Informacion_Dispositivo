package marti.tc_informacion_dispositivo.appmanager;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.annimon.stream.Stream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import marti.tc_informacion_dispositivo.R;
import marti.tc_informacion_dispositivo.adapter.RecyclerAdapter;

public class ListaAplicaciones extends AppCompatActivity {

    List<Aplicacion> aplicaciones;
    Aplicacion aplicacion;
    String nombreApp, nombrePkg,fechaInst;
    Bitmap icono;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aplicaciones);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this,aplicacionesInstaladas());
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }




    public List<Aplicacion> aplicacionesInstaladas(){
        List<PackageInfo> paquetes = this.getPackageManager ().getInstalledPackages (0);
        aplicaciones=new ArrayList<>();
        //List<String> appsFulName = Stream.of(paquetes)

        for(PackageInfo packageInfo : paquetes){

            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){

                nombreApp = packageInfo.applicationInfo.loadLabel(this.getPackageManager()).toString();
                nombrePkg = packageInfo.applicationInfo.packageName;
                fechaInst = primeraVezInstalado(packageInfo.applicationInfo.packageName);
                icono = obtenerIconoApp(packageInfo.applicationInfo.packageName);
                aplicacion = new Aplicacion(nombreApp,nombrePkg,icono,fechaInst);
                aplicaciones.add(aplicacion);
            }

        }


        return aplicaciones;
    }

    public String primeraVezInstalado(String pckname){
        try {
            long installed = this
                    .getPackageManager()
                    .getPackageInfo(pckname, 0)
                    .firstInstallTime;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.format(new Date(installed));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    public Bitmap obtenerIconoApp(String pckname){

        try {
            Drawable icon =  this.getPackageManager().getApplicationIcon(pckname);
            Bitmap bitIcon;

            if (icon instanceof BitmapDrawable) {
                bitIcon = ((BitmapDrawable)icon).getBitmap();
                bitIcon = Bitmap.createScaledBitmap(bitIcon, 32, 32, false);

            }else{
                bitIcon = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                bitIcon = Bitmap.createScaledBitmap(bitIcon, 32, 32, false);
            }

            return bitIcon;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
      return null;

    }



}
