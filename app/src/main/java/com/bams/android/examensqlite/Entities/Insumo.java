package com.bams.android.examensqlite.Entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.bams.android.examensqlite.DB.DataBaseContract;
import com.bams.android.examensqlite.DB.DataBaseHelper;
import com.bams.android.examensqlite.DB.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bams on 4/6/17.
 */

public class Insumo extends Entities {

    private int id;
    private String nombre;
    private Double cantidad;
    private String unidadMedida;

    public Insumo(String nombre, Double cantidad, String unidadMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    public Insumo() {
    }

    protected Insumo(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        unidadMedida = in.readString();
    }


    public int getId() {
        return id;
    }

    public Insumo setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Insumo setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public Insumo setCantidad(Double cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public Insumo setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
        return this;
    }

    public long insertar(Context context) {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_INSUMO, getNombre());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CANTIDAD_INSUMO, getCantidad());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_UNIDAD_MEDIDA_INSUMO,
                getUnidadMedida());
        // Insertar la nueva fila
        return super.insertar(context, DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO, values);
    }

    public void eliminar(Context context, int id) {
        super.eliminar(context, DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO, id);
    }

    public ArrayList<Insumo> leer(Context context) {
        ArrayList<Insumo> listInsumos = new ArrayList<Insumo>();

        Cursor cursor = super.leer(context, DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO, false, null);

        if (cursor.moveToFirst()) {
            do {
                Insumo insumo = new Insumo();

                insumo.setId(cursor.getInt(0));

                insumo.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_INSUMO)));

                insumo.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_CANTIDAD_INSUMO)));

                insumo.setUnidadMedida(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_UNIDAD_MEDIDA_INSUMO)));

                // Adding insumo to list
                listInsumos.add(insumo);
            } while (cursor.moveToNext());
        }

        return listInsumos;
    }
}
