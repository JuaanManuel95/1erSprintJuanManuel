package com.example.mercadoesclavojmp.view;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.model.Producto;
import com.example.mercadoesclavojmp.model.Productos;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private Toolbar toolbar;


    public static final String PRODUCTO = "Producto";



    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        Toolbar toolbar = view.findViewById(R.id.toolbarFragmentDetail);





        Bundle bundle = getArguments();
        Producto producto = (Producto) bundle.getSerializable(PRODUCTO);

        ImageView imageViewProducto = view.findViewById(R.id.fragmentDetailImageView);
        TextView textViewProducto = view.findViewById(R.id.fragmentDetailTextViewNombreProducto);
        TextView textViewPrecio = view.findViewById(R.id.fragmentDetailTextViewPrecioProducto);
        //TextView textViewDescripcion = view.findViewById(R.id.framentDetailTextViewDescripcionProducto);


        Glide.with(imageViewProducto).load(producto.getThumbnail()).into(imageViewProducto);
        textViewProducto.setText(producto.getTitle());
        textViewPrecio.setText(producto.getPrice().toString());
        //textViewDescripcion.setText(producto.getDescripcion());


        return view;
    }


}
