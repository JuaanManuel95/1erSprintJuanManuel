package com.example.mercadoesclavojmp.view;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.model.Producto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private Toolbar toolbar;
    private FirebaseFirestore db;
    private FloatingActionButton btnFav;
    private FirebaseAuth mAuth;
    private ImageView imageViewProducto;
    private TextView textViewProducto;
    private TextView textViewPrecio;


    public static final String PRODUCTO = "Producto";

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        toolbar = view.findViewById(R.id.toolbarFragmentDetail);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Bundle bundle = getArguments();
        final Producto producto = (Producto) bundle.getSerializable(PRODUCTO);

         imageViewProducto = view.findViewById(R.id.fragmentDetailImageView);
         textViewProducto = view.findViewById(R.id.fragmentDetailTextViewNombreProducto);
         textViewPrecio = view.findViewById(R.id.fragmentDetailTextViewPrecioProducto);
         btnFav = view.findViewById(R.id.favoritesFab);

        Glide.with(imageViewProducto).load(producto.getThumbnail()).into(imageViewProducto);
        NumberFormat formatt = new DecimalFormat("###,###,###.##");
        String precioString = formatt.format(producto.getPrice());
        textViewProducto.setText(producto.getTitle());
        textViewPrecio.setText("$" + precioString);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("usersME").document(mAuth.getCurrentUser().getUid())
                        .update("favoritos", FieldValue.arrayUnion(producto)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Añadido a tus favoritos!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Hubo un problema, no nos dejes!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return view;
    }
}
