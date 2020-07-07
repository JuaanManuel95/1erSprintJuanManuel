package com.example.mercadoesclavojmp.view.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.model.Producto;
import com.example.mercadoesclavojmp.model.UserNuevo;
import com.example.mercadoesclavojmp.view.Adapter.ProductosAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements ProductosAdapter.ProductosAdapterListener {

    private ProductosAdapter productoAdapter;
    private FragmentFavoritosListener fragmentFavoritosListener;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private RecyclerView recyclerViewFavoritos;


    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentFavoritosListener = (FragmentFavoritosListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerViewFavoritos= view.findViewById(R.id.FragmentFavoritos_RecyclerView);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        db.collection("usersME").document(mAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserNuevo userNuevo = documentSnapshot.toObject(UserNuevo.class);
                List<Producto> favoritos = userNuevo.getFavoritos();
                productoAdapter = new ProductosAdapter(favoritos, FavoritesFragment.this);

                recyclerViewFavoritos.setAdapter(productoAdapter);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerViewFavoritos.setLayoutManager(linearLayoutManager);


        return view;
    }

    @Override
    public void onClick(Producto producto) {
        fragmentFavoritosListener.onClick(producto);

    }

    public interface FragmentFavoritosListener {
        public void onClick (Producto producto);
    }
}

