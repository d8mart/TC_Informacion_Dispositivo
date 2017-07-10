package marti.tc_informacion_dispositivo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import marti.tc_informacion_dispositivo.R;

/**
 * Created by Daniel on 09/07/2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView tvnombre;
    TextView tvpaquete;
    TextView tvfecha;
    ImageView iv;


    public RecyclerViewHolder(View itemView) {
        super(itemView);

        tvnombre= (TextView) itemView.findViewById(R.id.nombreappcontent);
        tvpaquete= (TextView) itemView.findViewById(R.id.nombrepkgcontent);
        tvfecha= (TextView) itemView.findViewById(R.id.fechainstcontent);
        iv=(ImageView) itemView.findViewById(R.id.img);
    }
}
