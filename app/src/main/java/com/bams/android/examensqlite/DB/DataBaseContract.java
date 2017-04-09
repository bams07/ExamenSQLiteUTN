package com.bams.android.examensqlite.DB;

import android.provider.BaseColumns;

/**
 * Created by bams on 3/16/17.
 */

public final class DataBaseContract {

    public DataBaseContract() {

    }

    public static class DataBaseEntry implements BaseColumns {

        public static final String COLUMN_NAME_ID = "id";

        // Clase ORDEN

        public static final String TABLE_NAME_ORDEN = "Orden";

        public static final String COLUMN_NAME_PLATO_ID_ORDEN = "plato_id";

        public static final String COLUMN_NAME_FECHA_ORDEN = "fecha";

        public static final String COLUMN_NAME_HORA_ORDEN = "hora";

        public static final String COLUMN_NAME_COMENTARIO_ORDEN = "comentario";

        public static final String COLUMN_NAME_LOCALIZACION_ORDEN = "localizacion";


        // Clase PLATO

        public static final String TABLE_NAME_PLATO = "Plato";

        public static final String COLUMN_NAME_NOMBRE_PLATO = "nombre";

        public static final String COLUMN_NAME_DESCRIPCION_PLATO = "descripcion";

        public static final String COLUMN_NAME_PRECIO_PLATO = "precio";


        // Clase INSUMO

        public static final String TABLE_NAME_INSUMO = "Insumo";

        public static final String COLUMN_NAME_NOMBRE_INSUMO = "nombre";

        public static final String COLUMN_NAME_CANTIDAD_INSUMO = "cantidad";

        public static final String COLUMN_NAME_UNIDAD_MEDIDA_INSUMO = "unidad_medida";


        // Clase INSUMO - PLATO

        public static final String TABLE_NAME_INSUMO_PLATO = "InsumoPlato";

        public static final String COLUMN_NAME_FK_INSUMO_ID = "insumo_id";

        public static final String COLUMN_NAME_FK_PLATO_ID = "plato_id";

    }

// Construir las tablas de la base de datos

    private static final String TEXT_TYPE = " TEXT";

    private static final String LONG_TYPE = "LONG";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String REAL_TYPE = " REAL";

    private static final String COMMA_SEP = ",";


    public static final String SQL_CREATE_ORDEN =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ORDEN + " (" +

                    DataBaseEntry.COLUMN_NAME_ID + " " + INTEGER_TYPE + " PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_PLATO_ID_ORDEN + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FECHA_ORDEN + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_HORA_ORDEN + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_COMENTARIO_ORDEN + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_LOCALIZACION_ORDEN + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_PLATO_ID_ORDEN + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_PLATO + "(" + DataBaseEntry._ID +
                    "))";

    public static final String SQL_DELETE_ORDEN =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ORDEN;


    public static final String SQL_CREATE_PLATO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_PLATO + " (" +
                    DataBaseEntry.COLUMN_NAME_ID + " " + INTEGER_TYPE + " PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_NOMBRE_PLATO + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_DESCRIPCION_PLATO + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_PRECIO_PLATO + REAL_TYPE + ")";


    public static final String SQL_DELETE_PLATO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_PLATO;


    public static final String SQL_CREATE_INSUMO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_INSUMO + " (" +
                    DataBaseEntry.COLUMN_NAME_ID + " " + INTEGER_TYPE + " PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_NOMBRE_INSUMO + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_CANTIDAD_INSUMO + REAL_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_UNIDAD_MEDIDA_INSUMO + TEXT_TYPE + ")";


    public static final String SQL_DELETE_INSUMO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_INSUMO;


    public static final String SQL_CREATE_INSUMO_PLATO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_INSUMO_PLATO + " (" +
                    DataBaseEntry.COLUMN_NAME_ID + " " + INTEGER_TYPE + " PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_FK_PLATO_ID + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_FK_INSUMO_ID + INTEGER_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_FK_PLATO_ID + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_PLATO + "(" + DataBaseEntry._ID +
                    ")" + COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_FK_INSUMO_ID + ") REFERENCES " +
                    DataBaseEntry.TABLE_NAME_INSUMO + "(" + DataBaseEntry._ID +
                    "))";


    public static final String SQL_DELETE_INSUMO_PLATO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_INSUMO_PLATO;
}


