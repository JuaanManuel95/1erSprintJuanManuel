package com.example.mercadoesclavojmp.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavojmp.controller.ProductosController;
import com.example.mercadoesclavojmp.dao.ProductosDao;
import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.model.Productos;

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

        ProductosController productosController = new ProductosController();

        List<Productos> productosList = productosController.getProductos();
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
