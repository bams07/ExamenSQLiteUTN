package com.bams.android.examensqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bams.android.examensqlite.Entities.Orden;
import com.bams.android.examensqlite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bams on 2/23/17.
 */

public class OrdenesListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Orden> ordenes; //data source of the list adapter

    //public constructor
    public OrdenesListAdapter(Context context, ArrayList<Orden> ordenes) {
        this.context = context;
        this.ordenes = ordenes;
    }


    @Override
    public int getCount() {
        return ordenes.size();
    }

    @Override
    public Object getItem(int position) {
        return ordenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        View view = LayoutInflater.from(context).
                inflate(R.layout.ordenes_item_list_view, parent, false);

        holder = new ViewHolder(view);


        Orden currentItem = (Orden) getItem(position);

        holder.txtOrdenPlatoNombre.setText(currentItem.getPlato().getNombre());
        holder.txtOrdenLocalizacion.setText(currentItem.getLocalizacion());
        holder.txtOrdenFechaHora.setText(currentItem.getFecha() + " " + currentItem.getHora());
        holder.txtOrdenComentario.setText(currentItem.getComentario());

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.txtOrdenPlatoNombre) TextView txtOrdenPlatoNombre;
        @BindView(R.id.txtOrdenLocalizacion) TextView txtOrdenLocalizacion;
        @BindView(R.id.txtOrdenFechaHora) TextView txtOrdenFechaHora;
        @BindView(R.id.txtOrdenComentario) TextView txtOrdenComentario;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
