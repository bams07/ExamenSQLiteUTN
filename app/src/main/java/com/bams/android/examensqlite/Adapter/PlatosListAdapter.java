package com.bams.android.examensqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bams.android.examensqlite.Entities.Plato;
import com.bams.android.examensqlite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bams on 2/23/17.
 */

public class PlatosListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Plato> platos; //data source of the list adapter

    //public constructor
    public PlatosListAdapter(Context context, ArrayList<Plato> platos) {
        this.context = context;
        this.platos = platos;
    }


    @Override
    public int getCount() {
        return platos.size();
    }

    @Override
    public Object getItem(int position) {
        return platos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        View view = LayoutInflater.from(context).
                inflate(R.layout.platos_item_list_view, parent, false);

        holder = new ViewHolder(view);


        Plato currentItem = (Plato) getItem(position);

        holder.txtPlatoNombre.setText(currentItem.getNombre());
        holder.txtPlatoDescripcion.setText(currentItem.getDescripcion());
        holder.txtPlatoPrecio.setText("¢" + currentItem.getPrecio());
        holder.txtPlatosInsumosCount.setText("CANTIDAD DE INSUMOS: " + currentItem.getInsumos().size());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.txtPlatoNombre) TextView txtPlatoNombre;
        @BindView(R.id.txtPlatoPrecio) TextView txtPlatoPrecio;
        @BindView(R.id.txtPlatoDescripcion) TextView txtPlatoDescripcion;
        @BindView(R.id.txtPlatosInsumosCount) TextView txtPlatosInsumosCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
