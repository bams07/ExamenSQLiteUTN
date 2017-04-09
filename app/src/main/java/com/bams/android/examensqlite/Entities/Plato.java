package com.bams.android.examensqlite.Entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bams.android.examensqlite.DB.DataBaseContract;
import com.bams.android.examensqlite.DB.DataBaseHelper;
import com.bams.android.examensqlite.DB.Entities;

import java.util.ArrayList;

/**
 * Created by bams on 4/6/17.
 */

public class Plato extends Entities {

    private String nombre;
    private String descripcion;
    private Double precio;
    private ArrayList<Insumo> insumos;
    private int id;

    public Plato(String nombre, String descripcion, Double precio, ArrayList<Insumo> insumos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.insumos = insumos;
    }

    public Plato(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Plato() {
    }

    public String getNombre() {
        return nombre;
    }

    public Plato setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public int getId() {
        return id;
    }

    public Plato setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Plato setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public Double getPrecio() {
        return precio;
    }

    public Plato setPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    public Plato setInsumos(
            ArrayList<Insumo> insumos) {
        this.insumos = insumos;
        return this;
    }

    public long insertar(Context context) {
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_PLATO, getNombre());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_PLATO, getDescripcion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_PRECIO_PLATO,
                getPrecio());

        return super.insertar(context, DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO, values);

    }


    public void eliminar(Context context, int id) {
        // Realiza el SQL de borrado
        super.eliminar(context, DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO, id);
    }

    public ArrayList<Plato> leer(Context context) {
        ArrayList<Plato> listPlatos = new ArrayList<Plato>();
        insumos = new ArrayList<Insumo>();
        int position;
        Plato plato;

        String sqlQuery = String.format(
                "SELECT DISTINCT p.*, i.nombre insumo_nombre, " +
                        "i.cantidad insumo_cantidad, i.unidad_medida insumo_unidad_medida " +
                        "FROM %s p " +
                        "INNER JOIN %s ip ON p.id=ip.plato_id " +
                        "INNER JOIN %s i ON ip.insumo_id=i.id" +
                        " ORDER BY ip.id",
                DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO,
                DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO_PLATO,
                DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO);

        Cursor cursor = super.leer(context, DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO, true,
                sqlQuery);


        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_ID));
                String insumoNombre = cursor.getString(cursor.getColumnIndexOrThrow(
                        "insumo_nombre"));
                Double insumoCantidad = cursor.getDouble(cursor.getColumnIndexOrThrow(
                        "insumo_cantidad"));
                String insumoUnidadMedida = cursor.getString(cursor.getColumnIndexOrThrow(
                        "insumo_unidad_medida"));

                if (id != getId()) {

                    plato = new Plato();

                    if (id != getId()) {
                        insumos = new ArrayList<Insumo>();
                    }

                    // ID
                    plato.setId(id);

                    setId(id);

                    // NOMBRE
                    plato.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(
                            DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_PLATO)));

                    // PRECIO
                    plato.setPrecio(cursor.getDouble(cursor.getColumnIndexOrThrow(
                            DataBaseContract.DataBaseEntry.COLUMN_NAME_PRECIO_PLATO)));

                    // DESCRIPCION
                    plato.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(
                            DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION_PLATO)));

                    insumos.add(new Insumo(insumoNombre, insumoCantidad, insumoUnidadMedida));

                    plato.setInsumos(insumos);


                    // Adding plato to list
                    listPlatos.add(plato);
                }else if (id == getId()){
                    insumos.add(new Insumo(insumoNombre, insumoCantidad, insumoUnidadMedida));
                    listPlatos.get(listPlatos.size() -1).setInsumos(insumos);

                }
            } while (cursor.moveToNext());
        }

        return listPlatos;
    }
}
