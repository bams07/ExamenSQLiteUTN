package com.bams.android.examensqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bams.android.examensqlite.Entities.Insumo;
import com.bams.android.examensqlite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bams on 2/23/17.
 */

public class InsumosListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Insumo> Insumos; //data source of the list adapter

    //public constructor
    public InsumosListAdapter(Context context, ArrayList<Insumo> Insumos) {
        this.context = context;
        this.Insumos = Insumos;
    }


    @Override
    public int getCount() {
        return Insumos.size();
    }

    @Override
    public Object getItem(int position) {
        return Insumos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        View view = LayoutInflater.from(context).
                inflate(R.layout.insumos_item_list_view, parent, false);

        holder = new ViewHolder(view);


        Insumo currentItem = (Insumo) getItem(position);

        holder.txtInsumoNombre.setText(currentItem.getNombre());
        holder.txtInsumoCantidad.setText("CANTIDAD: " + currentItem.getCantidad().toString());
        holder.txtInsumoUnidadMedida.setText(currentItem.getUnidadMedida());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.txtInsumoNombre) TextView txtInsumoNombre;
        @BindView(R.id.txtInsumoUnidadMedida) TextView txtInsumoUnidadMedida;
        @BindView(R.id.txtInsumoCantidad) TextView txtInsumoCantidad;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

}
