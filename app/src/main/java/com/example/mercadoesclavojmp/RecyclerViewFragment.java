package com.example.mercadoesclavojmp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements ProductosAdapter.ProductosAdapterListener {

    private RecyclerViewFragmentListener recyclerViewFragmentListener;
    //private SwipeRefreshLayout swipeRefreshLayout;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.recyclerViewFragmentListener = (RecyclerViewFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

      // SwipeRefreshLayout swipeRefreshLayout =  view.findViewById(R.id.swipeRefreshLayout);

        RecyclerView recyclerViewProductos = view.findViewById(R.id.fragmentRecyclerRecyclerView);

        List<Productos> productosList = ProveedorDeProductos.getProductos();
        ProductosAdapter productosAdapter = new ProductosAdapter(productosList,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewProductos.setLayoutManager(linearLayoutManager);
        recyclerViewProductos.setAdapter(productosAdapter);

        return view;
    }

    @Override
    public void onClick(Productos productos) {

        recyclerViewFragmentListener.onClickProductosDesdeFragment(productos);

    }

    public interface RecyclerViewFragmentListener{
        public void onClickProductosDesdeFragment(Productos productos);
    }


}
