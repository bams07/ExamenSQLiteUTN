package com.bams.android.examensqlite.Entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.bams.android.examensqlite.DB.DataBaseContract;
import com.bams.android.examensqlite.DB.Entities;

import java.util.ArrayList;

/**
 * Created by bams on 4/6/17.
 */

public class Orden extends Entities {

    private int id;
    private int plato_id;
    private String fecha;
    private String hora;
    private String comentario;
    private String localizacion;
    private Plato plato;

    public Orden(int plato_id, String fecha, String hora, String comentario,
            String localizacion) {
        this.plato_id = plato_id;
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
        this.localizacion = localizacion;
    }

    public Orden(int plato_id, String fecha, String hora, String comentario,
            String localizacion, Plato plato) {
        this.plato_id = plato_id;
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
        this.localizacion = localizacion;
        this.plato = plato;
    }

    public Orden() {
    }

    public Plato getPlato() {
        return plato;
    }

    public Orden setPlato(Plato plato) {
        this.plato = plato;
        return this;
    }

    public int getPlato_id() {
        return plato_id;
    }

    public Orden setPlato_id(int plato_id) {
        this.plato_id = plato_id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Orden setId(int id) {
        this.id = id;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public Orden setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getHora() {
        return hora;
    }

    public Orden setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public String getComentario() {
        return comentario;
    }

    public Orden setComentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public Orden setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
        return this;
    }


    public long insertar(Context context) {
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_PLATO_ID_ORDEN, getPlato_id());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_HORA_ORDEN, getHora());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA_ORDEN, getFecha());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_COMENTARIO_ORDEN, getComentario());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_LOCALIZACION_ORDEN,
                getLocalizacion());

        return super.insertar(context, DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN, values);

    }


    public void eliminar(Context context, int id) {
        super.eliminar(context, DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN, id);
    }

    public ArrayList<Orden> leer(Context context) {
        ArrayList<Orden> listOrdenes = new ArrayList<Orden>();

        String sqlQuery = String.format(
                "SELECT o.*, p.nombre plato_nombre, " +
                        "p.precio plato_precio, p.descripcion plato_descripcion " +
                        "FROM %s o " +
                        "INNER JOIN %s p ON o.plato_id=p.id ",
                DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN,
                DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO);

        Cursor cursor = super.leer(context, DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN, true,
                sqlQuery);


        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_ID));

                String platoNombre = cursor.getString(cursor.getColumnIndexOrThrow(
                        "plato_nombre"));
                Double platoPrecio = cursor.getDouble(cursor.getColumnIndexOrThrow(
                        "plato_precio"));
                String platoDescripcion = cursor.getString(cursor.getColumnIndexOrThrow(
                        "plato_descripcion"));


                Orden orden = new Orden();

                // ID
                orden.setId(id);

                // HORA
                orden.setHora(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_HORA_ORDEN)));

                // FECHA
                orden.setFecha(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA_ORDEN)));

                // COMENTARIO
                orden.setComentario(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_COMENTARIO_ORDEN)));

                // LOCALIZACION
                orden.setLocalizacion(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_LOCALIZACION_ORDEN)));


                orden.setPlato(new Plato(platoNombre, platoDescripcion, platoPrecio));

                listOrdenes.add(orden);

            } while (cursor.moveToNext());
        }

        return listOrdenes;
    }
}
