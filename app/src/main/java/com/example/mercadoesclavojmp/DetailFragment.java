package com.example.mercadoesclavojmp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public static final String PRODUCTO = "Producto";

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        Productos productos = (Productos) bundle.getSerializable(PRODUCTO);

        ImageView imageViewProducto = view.findViewById(R.id.fragmentDetailImageView);
        TextView textViewProducto = view.findViewById(R.id.fragmentDetailTextViewNombreProducto);
        TextView textViewPrecio = view.findViewById(R.id.fragmentDetailTextViewPrecioProducto);
        TextView textViewDescripcion = view.findViewById(R.id.framentDetailTextViewDescripcionProducto);


        imageViewProducto.setImageResource(productos.getImagen());
        textViewProducto.setText(productos.getNombre());
        textViewPrecio.setText(productos.getPrecio());
        textViewDescripcion.setText(productos.getDescripcion());


        return view;
    }


}
