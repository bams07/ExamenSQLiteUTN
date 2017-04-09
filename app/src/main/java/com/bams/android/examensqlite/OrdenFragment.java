package com.bams.android.examensqlite;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bams.android.examensqlite.Entities.Orden;
import com.bams.android.examensqlite.Entities.Plato;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdenFragment extends Fragment {
    @BindView(R.id.txtFecha) EditText txtFecha;
    @BindView(R.id.txtHora) EditText txtHora;
    @BindView(R.id.txtComentario) EditText txtComentario;
    @BindView(R.id.txtLocalizacion) EditText txtLocalizacion;
    @BindView(R.id.btnInsertar) Button btnInsertar;
    @BindView(R.id.btnVerLista) Button btnVerLista;
    @BindView(R.id.btnEliminarLista) Button btnEliminarLista;
    @BindView(R.id.btnAgregarPlatos) Button btnAgregarPlatos;
    @BindView(R.id.txtSelectedPlato) EditText txtSelectedPlato;

    Dialog matchTextDialog;
    ListView textListView;
    int selectedPlato = 0;
    ArrayList<Plato> listPlatos;
    ArrayList<Orden> listOrdenes;
    ArrayList<String> listPlatosName;
    ArrayList<String> listOrdenesName;


    public OrdenFragment() {
        // Required empty public constructor
    }

    public static OrdenFragment newInstance(String param1, String param2) {
        OrdenFragment fragment = new OrdenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orden, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.btnInsertar, R.id.btnVerLista, R.id.btnEliminarLista, R.id.btnAgregarPlatos})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertar:
                insertarOrden();
                break;
            case R.id.btnVerLista:
                verLista();
                break;
            case R.id.btnEliminarLista:
                eliminarLista();
                break;
            case R.id.btnAgregarPlatos:
                agregarPlatos();
                break;
        }
    }

    private void agregarPlatos() {
        final Plato plato = new Plato();
        matchTextDialog = new Dialog(this.getContext());
        matchTextDialog.setContentView(R.layout.dialog_matches_frag);
        matchTextDialog.setTitle("AGREGAR PLATOS");
        textListView = (ListView) matchTextDialog.findViewById(R.id.listView1);
        listPlatos = plato.leer(this.getContext());
        listPlatosName = new ArrayList<String>();

        for (Plato data : listPlatos) {
            listPlatosName.add("Nombre: " + data.getNombre() + " Precio: " + data.getPrecio() +
                    "Descripcion: " + data.getDescripcion());
        }

        /**
         * Set adapter to listView
         */
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,
                        listPlatosName);
        textListView.setAdapter(adapter);


        textListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int plato_id = listPlatos.get(position).getId();
                String nombre = listPlatos.get(position).getNombre();
                selectedPlato = plato_id;
                txtSelectedPlato.setText(nombre);
                matchTextDialog.hide();

            }
        });
        matchTextDialog.show();
    }


    private void insertarOrden() {
        try {

            String fecha = txtFecha.getText().toString();
            String hora = txtHora.getText().toString();
            String comentario = txtComentario.getText().toString();
            String localizacion = txtLocalizacion.getText().toString();
            int plato_id = selectedPlato;

            if (fecha.isEmpty() || hora.isEmpty() || comentario.isEmpty() ||
                    localizacion.isEmpty()) {
                Toast.makeText(this.getContext(), "CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG)
                        .show();
                return;
            }

            if (selectedPlato == 0) {
                Toast.makeText(this.getContext(), "ES NECESARIO AGREGAR UN PLATO", Toast.LENGTH_LONG)
                        .show();
                return;
            }

            Orden orden = new Orden(plato_id, fecha, hora, comentario, localizacion);

            long orden_id = orden.insertar(this.getContext());


            Toast.makeText(this.getContext(),
                    "NUEVA ORDEN AGREGADA: " + orden_id, Toast.LENGTH_LONG)
                    .show();

            limpiar();

        } catch (Exception e) {
            Toast.makeText(this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }


    private void eliminarLista() {
        final Orden orden = new Orden();
        matchTextDialog = new Dialog(this.getContext());
        matchTextDialog.setContentView(R.layout.dialog_matches_frag);
        matchTextDialog.setTitle("ELIMINAR ORDENES");
        textListView = (ListView) matchTextDialog.findViewById(R.id.listView1);
        listOrdenes = orden.leer(this.getContext());
        listOrdenesName = new ArrayList<String>();

        for (Orden data : listOrdenes) {
            listOrdenesName
                    .add("ID: " + data.getId() + " Fecha: " + data.getFecha() +
                            " Hora: " + data.getHora() + " Comentario: " + data.getComentario() +
                            " Localizacion: " + data.getLocalizacion());
        }

        /**
         * Set adapter to listView
         */
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,
                        listOrdenesName);
        textListView.setAdapter(adapter);


        textListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int orden_id = listOrdenes.get(position).getId();
                orden.eliminar(parent.getContext(), orden_id);
                matchTextDialog.hide();
                Toast.makeText(parent.getContext(), "ELIMINADA ORDEN: " + orden_id,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        matchTextDialog.show();
    }

    private void verLista() {
        Orden orden = new Orden();
        matchTextDialog = new Dialog(this.getContext());
        matchTextDialog.setContentView(R.layout.dialog_matches_frag);
        matchTextDialog.setTitle("ELIMINAR ORDENES");
        textListView = (ListView) matchTextDialog.findViewById(R.id.listView1);
        listOrdenes = orden.leer(this.getContext());
        listOrdenesName = new ArrayList<String>();

        for (Orden data : listOrdenes) {
            listOrdenesName
                    .add("ID: " + data.getId() + " Fecha: " + data.getFecha() +
                            " Hora: " + data.getHora() + " Comentario: " + data.getComentario() +
                            " Localizacion: " + data.getLocalizacion());
        }

        /**
         * Set adapter to listView
         */
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,
                        listOrdenesName);
        textListView.setAdapter(adapter);


        matchTextDialog.show();
    }


    private void limpiar() {

        txtLocalizacion.setText("");
        txtComentario.setText("");
        txtHora.setText("");
        txtFecha.setText("");
        txtSelectedPlato.setText("");
        selectedPlato = 0;

    }
}
