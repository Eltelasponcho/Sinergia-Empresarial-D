package com.example.mauri.sede;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link vertodoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link vertodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class vertodoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Usuarios> listDatos;
    View vista;
    RecyclerView recyler;
    conexion cn = new conexion();
    private OnFragmentInteractionListener mListener;

    public vertodoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment vertodoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static vertodoFragment newInstance(String param1, String param2) {
        vertodoFragment fragment = new vertodoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy p = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(p);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_vertodo, container, false);
        listDatos= new ArrayList<>();
        recyler=(RecyclerView) vista.findViewById(R.id.recyclerId);
        recyler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        llenarPersonajes();
        AdapterDatos adapter = new AdapterDatos(listDatos);
        recyler.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        "Seleccion: " + listDatos.get(recyler.getChildAdapterPosition(vista)).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }

    private void llenarPersonajes() {
        listDatos.add(new Usuarios("Midoria","One for all",R.drawable.midorya));
        String texto2 = cn.vertodo("http://natanael13.000webhostapp.com/vertodo.php");
        // Toast.makeText(getApplicationContext(), texto2, Toast.LENGTH_SHORT).show();
        String[] dato = texto2.split(",");
        int cont = Integer.parseInt(dato[0]);
        int c = 1;
            for (int i = 1; i <= cont; i++) {
                listDatos.add(new Usuarios(dato[c+1],dato[c+2],R.drawable.midorya));
                /*txt1.setText(dato[c + 1]);
                txt2.setText(dato[c + 2]);*/
                //Toast.makeText(getApplicationContext(),dato[c] + dato[c + 1] + dato[c + 2], Toast.LENGTH_SHORT).show();
                c = c + 3;
            }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
