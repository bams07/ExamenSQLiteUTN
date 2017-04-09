package com.bams.android.examensqlite.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bams.android.examensqlite.Entities.Plato;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by bams on 4/8/17.
 */

public class Entities {

    public long insertar(Context context, String table, ContentValues values) {
        try {
            // usar la clase DataBaseHelper para realizar la operacion de insertar
            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
            // Obtiene la base de datos en modo escritura
            SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
            // Insertar la nueva fila
            return db.insert(table, null, values);

        } catch (Exception e) {
            return -1;

        }
    }

    public void eliminar(Context context, String table, int id) {
        // usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = "rowid=" + id;
        // Realiza el SQL de borrado
        db.delete(table, selection, null);
    }

    public Cursor leer(Context context, String table, boolean rawQuery, String sqlQuery) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        if (rawQuery && !sqlQuery.isEmpty()) {
            return db.rawQuery(sqlQuery, null);
        }

        return db.query(
                table, // tabla
                new String[]{"rowid", "*"}, // columnas
                null, // where
                null, // valores del where
                null, //agrupamiento
                null, // filtros por grupo
                null // orden
        );
    }
}
