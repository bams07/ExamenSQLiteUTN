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

import com.bams.android.examensqlite.Adapter.InsumosListAdapter;
import com.bams.android.examensqlite.Adapter.PlatosListAdapter;
import com.bams.android.examensqlite.Entities.Insumo;
import com.bams.android.examensqlite.Entities.InsumoPlato;
import com.bams.android.examensqlite.Entities.Plato;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlatosFragment extends Fragment {

    @BindView(R.id.txtNombrePlato) EditText txtNombrePlato;
    @BindView(R.id.txtPrecioPlato) EditText txtPrecioPlato;
    @BindView(R.id.txtDescripcionPlato) EditText txtDescripcionPlato;
    @BindView(R.id.btnInsertar) Button btnInsertar;
    @BindView(R.id.btnVerLista) Button btnVerLista;
    @BindView(R.id.btnEliminarLista) Button btnEliminarLista;
    @BindView(R.id.btnAgregarInsumos) Button btnAgregarInsumos;
    @BindView(R.id.txtInsumosList) EditText txtInsumosList;


    Dialog matchTextDialog;
    ListView listViewPlatos;
    ListView listViewInsumos;
    ArrayList<Plato> listPlatos;
    ArrayList<Insumo> listInsumos;
    ArrayList<Integer> selectedInsumos = new ArrayList<Integer>();


    public PlatosFragment() {
        // Required empty public constructor
    }

    public static PlatosFragment newInstance(String param1, String param2) {
        PlatosFragment fragment = new PlatosFragment();
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
        View view = inflater.inflate(R.layout.fragment_platos, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.btnInsertar, R.id.btnVerLista, R.id.btnEliminarLista, R.id.btnAgregarInsumos})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertar:
                insertarPlato();
                break;
            case R.id.btnVerLista:
                verLista();
                break;
            case R.id.btnEliminarLista:
                eliminarLista();
                break;
            case R.id.btnAgregarInsumos:
                agregarInsumos();
                break;
        }
    }

    private void agregarInsumos() {
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


        listViewInsumos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int insumo_id = listInsumos.get(position).getId();
                String nombre = listInsumos.get(position).getNombre();
                String insumos = txtInsumosList.getText().toString();
                selectedInsumos.add(insumo_id);
                txtInsumosList.setText(insumos + " - " + nombre);
                matchTextDialog.hide();

            }
        });
        matchTextDialog.show();
    }


    private void insertarPlato() {
        try {

            String nombre = txtNombrePlato.getText().toString();
            Double precio = Double.parseDouble(txtPrecioPlato.getText().toString());
            String descripcion = txtDescripcionPlato.getText().toString();

            if (nombre.isEmpty() || precio.isNaN() || descripcion.isEmpty()) {
                Toast.makeText(this.getContext(), "CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG)
                        .show();
                return;
            }

            if (selectedInsumos.isEmpty()) {
                Toast.makeText(this.getContext(), "ES NECESARIO AGREGAR INSUMOS", Toast.LENGTH_LONG)
                        .show();
                return;
            }

            Plato plato = new Plato(nombre, descripcion, precio);
            long plato_id = plato.insertar(this.getContext());

            for (int insumo_id : selectedInsumos) {
                InsumoPlato insumo_plato = new InsumoPlato((int) plato_id, insumo_id);
                insumo_plato.insertar(this.getContext());
            }

            Toast.makeText(this.getContext(),
                    "NUEVO PLATO AGREGADO: " + nombre + " - id: " + plato_id, Toast.LENGTH_LONG)
                    .show();

            limpiar();

        } catch (Exception e) {
            Toast.makeText(this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }


    private void eliminarLista() {
        final Plato plato = new Plato();
        matchTextDialog = new Dialog(this.getContext());
        matchTextDialog.setContentView(R.layout.dialog_matches_frag);
        matchTextDialog.setTitle("ELIMINAR PLATOS");
        listViewPlatos = (ListView) matchTextDialog.findViewById(R.id.listView1);
        listPlatos = plato.leer(this.getContext());

        /**
         * Set adapter to listView
         */
        PlatosListAdapter adapter = new PlatosListAdapter(this.getContext(), listPlatos);
        listViewPlatos.setAdapter(adapter);



        listViewPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int plato_id = listPlatos.get(position).getId();
                String nombre = listPlatos.get(position).getNombre();
                plato.eliminar(parent.getContext(), plato_id);
                matchTextDialog.hide();
                Toast.makeText(parent.getContext(), "ELIMINADO " + nombre, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        matchTextDialog.show();
    }

    private void verLista() {
        Plato plato = new Plato();
        matchTextDialog = new Dialog(this.getContext());
        matchTextDialog.setContentView(R.layout.dialog_matches_frag);
        matchTextDialog.setTitle("PLATOS");
        listViewPlatos = (ListView) matchTextDialog.findViewById(R.id.listView1);
        listPlatos = plato.leer(this.getContext());

        /**
         * Set adapter to listView
         */
        PlatosListAdapter adapter = new PlatosListAdapter(this.getContext(), listPlatos);
        listViewPlatos.setAdapter(adapter);


        matchTextDialog.show();
    }


    private void limpiar() {

        txtNombrePlato.setText("");
        txtPrecioPlato.setText("");
        txtDescripcionPlato.setText("");
        txtInsumosList.setText("");
        selectedInsumos.clear();

    }

}
