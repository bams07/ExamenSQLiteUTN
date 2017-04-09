package com.bams.android.examensqlite.Entities;

/**
 * Created by bams on 4/6/17.
 */

public class Orden {

    private int plato_id;
    private String fecha;
    private String hora;
    private String comentario;
    private String localizacion;

    public Orden(int plato_id, String fecha, String hora, String comentario,
            String localizacion) {
        this.plato_id = plato_id;
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
        this.localizacion = localizacion;
    }

    public int getPlato_id() {

        return plato_id;
    }

    public Orden setPlato_id(int plato_id) {
        this.plato_id = plato_id;
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
}
