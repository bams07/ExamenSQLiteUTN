package com.bams.android.examensqlite;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bams.android.examensqlite.Adapter.InsumosListAdapter;
import com.bams.android.examensqlite.Entities.Insumo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InsumosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @BindView(R.id.txtNombre) EditText txtNombre;
    @BindView(R.id.txtCantidad) EditText txtCantidad;
    @BindView(R.id.txtUnidadMedida) EditText txtUnidadMedida;
    @BindView(R.id.btnInsertar) Button btnInsertar;
    @BindView(R.id.btnVerLista) Button btnVerLista;
    @BindView(R.id.btnEliminarLista) Button btnEliminarLista;


    Dialog matchTextDialog;
    ListView listViewInsumos;
    ArrayList<Insumo> listInsumos;

    public InsumosFragment() {
        // Required empty public constructor
    }


    public static InsumosFragment newInstance(String param1, String param2) {
        InsumosFragment fragment = new InsumosFragment();
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
        View view = inflater.inflate(R.layout.fragment_insumos, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    private void insertarInsumo() {
        try {
            String nombre = txtNombre.getText().toString();
            Double cantidad = Double.parseDouble(txtCantidad.getText().toString());
            String unidadMedida = txtUnidadMedida.getText().toString();

            if (nombre.isEmpty() || cantidad.isNaN() || unidadMedida.isEmpty()) {
                Toast.makeText(this.getContext(), "CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG)
                        .show();
                return;
            }

            Insumo insumo = new Insumo(nombre, cantidad, unidadMedida);
            long newRowId = insumo.insertar(this.getContext());
            // Mostrar un mensaje para el usuario
            if (newRowId == -1) {
                Toast.makeText(this.getContext(), "ERROR AL AGREGAR INSUMO: " + newRowId,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.getContext(), "NUEVO INSUMO: " + newRowId, Toast.LENGTH_LONG)
                        .show();
            }

            limpiar();

        } catch (Exception e) {
            Toast.makeText(this.getContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }


    @OnClick({R.id.btnInsertar, R.id.btnVerLista, R.id.btnEliminarLista})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertar:
                insertarInsumo();
                break;
            case R.id.btnVerLista:
                verLista();
                break;
            case R.id.btnEliminarLista:
                eliminarLista();
                break;
        }
    }

    private void eliminarLista() {
        final Insumo insumo = new Insumo();
        matchTextDialog = new Dialog(this.getContext());
        matchTextDialog.setContentView(R.layout.dialog_matches_frag);
        matchTextDialog.setTitle("ELIMINAR INSUMOS");
        listViewInsumos = (ListView) matchTextDialog.findViewById(R.id.listView1);
        listInsumos = insumo.leer(this.getContext());

        /**
         * Set adapter to listView
         */
        InsumosListAdapter adapter = new InsumosListAdapter(this.getContext(), listInsumos);
        listViewInsumos.setAdapter(adapter);

        listViewInsumos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int insumo_id = listInsumos.get(position).getId();
                String nombre = listInsumos.get(position).getNombre();
                insumo.eliminar(parent.getContext(), insumo_id);
                matchTextDialog.hide();
                Toast.makeText(parent.getContext(), "ELIMINADO " + nombre, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        matchTextDialog.show();
    }

    private void verLista() {
        final Insumo insumo = new Insumo();
        matchTextDialog = new Dialog(this.getContext());
        matchTextDialog.setContentView(R.layout.dialog_matches_frag);
        matchTextDialog.setTitle("INSUMOS");
        listViewInsumos = (ListView) matchTextDialog.findViewById(R.id.listView1);
        listInsumos = insumo.leer(this.getContext());


        /**
         * Set adapter to listView
         */
        InsumosListAdapter adapter = new InsumosListAdapter(this.getContext(), listInsumos);
        listViewInsumos.setAdapter(adapter);

        matchTextDialog.show();
    }

    private void limpiar() {
        txtCantidad.setText("");
        txtNombre.setText("");
        txtUnidadMedida.setText("");
    }
}
