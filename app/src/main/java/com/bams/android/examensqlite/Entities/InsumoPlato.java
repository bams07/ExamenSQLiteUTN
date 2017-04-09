package com.bams.android.examensqlite.Entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bams.android.examensqlite.DB.DataBaseContract;
import com.bams.android.examensqlite.DB.DataBaseHelper;
import com.bams.android.examensqlite.DB.Entities;

/**
 * Created by bams on 4/6/17.
 */

public class InsumoPlato extends Entities {

    private int plato_id;
    private int insumo_id;

    public InsumoPlato(int plato_id, int insumo_id) {

        this.plato_id = plato_id;
        this.insumo_id = insumo_id;
    }

    public int getPlato_id() {
        return plato_id;
    }

    public InsumoPlato setPlato_id(int plato_id) {
        this.plato_id = plato_id;
        return this;
    }

    public int getInsumo_id() {
        return insumo_id;
    }

    public InsumoPlato setInsumo_id(int insumo_id) {
        this.insumo_id = insumo_id;
        return this;
    }

    public long insertar(Context context) {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FK_PLATO_ID, getPlato_id());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FK_INSUMO_ID, getInsumo_id());
        // Insertar la nueva fila
        return super
                .insertar(context, DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO_PLATO, values);
    }
}
