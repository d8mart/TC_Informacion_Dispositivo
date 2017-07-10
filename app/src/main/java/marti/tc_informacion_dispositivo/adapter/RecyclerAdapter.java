package marti.tc_informacion_dispositivo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import marti.tc_informacion_dispositivo.R;
import marti.tc_informacion_dispositivo.appmanager.Aplicacion;

/**
 * Created by Daniel on 09/07/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

       Context context;
       List<Aplicacion> aplicaciones;
       LayoutInflater layoutInflater;

    public RecyclerAdapter(Context context, List<Aplicacion> aplicaciones){
        this.context=context;
        this.aplicaciones=aplicaciones;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.cardview_app_item,parent,false);
        RecyclerViewHolder view1 = new RecyclerViewHolder(v);
        return view1;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.tvnombre.setText(aplicaciones.get(position).getNombreApp());
        holder.tvpaquete.setText(aplicaciones.get(position).getNombrePaquete());
        holder.tvfecha.setText(aplicaciones.get(position).getFechaInstalacionApp());
        holder.iv.setImageBitmap(aplicaciones.get(position).getIconoApp());

    }

    @Override
    public int getItemCount() {
        return aplicaciones.size();
    }
}
