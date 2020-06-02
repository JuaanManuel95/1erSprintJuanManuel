package com.example.mercadoesclavojmp.dao;

import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.model.ProductoContainer;
import com.example.mercadoesclavojmp.model.Productos;
import com.example.mercadoesclavojmp.service.ProductoService;
import com.example.mercadoesclavojmp.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoDao extends RetrofitDao {

    private ProductoService productoService;

    public ProductoDao() {
        productoService = super.retrofit.create(ProductoService.class);
    }

    public void searchByQuery(String id, final ResultListener<ProductoContainer> resultListenerFromController){
        Call<ProductoContainer> call = this.productoService.searchByQuery(id);

        call.enqueue(new Callback<ProductoContainer>() {
            @Override
            public void onResponse(Call<ProductoContainer> call, Response<ProductoContainer> response) {
                if(response.isSuccessful()){
                    ProductoContainer body = response.body();
                    resultListenerFromController.onFinish(body);
                }else{
                    response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<ProductoContainer> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    public static List<Productos> getProductos(){
        List<Productos> productosList = new ArrayList<>();

        productosList.add(new Productos("Airpods", R.drawable.airpods, "$30.000","Auriculares Bluetooth de excelente calidad de sonido."));
        productosList.add(new Productos("iPad",R.drawable.ipad, "$120.000","Tablet iPad de 9.7' con 32 GB de almacenamiento."));
        productosList.add(new Productos("Equipo de música",R.drawable.parlantes,"$40.000", "Equipo de alta fidelidad de Sonido con BT incorporado."));
        productosList.add(new Productos("PlayStation 4",R.drawable.playstation4, "$25.000","PlayStation 4 Slim de 1 TB de almacenamiento."));
        productosList.add(new Productos("Gafas Ray-Ban",R.drawable.rayban, "$15.000", "Gafas de sol Ray-Ban, modelo: Double Bridge."));
        productosList.add(new Productos("TV LED FULL HD",R.drawable.televisor, "$24.000","Televisor LED FHD de 50 pulgadas."));
        productosList.add(new Productos("Zapatillas Airmax",R.drawable.zapatillas_nike, "$8.000","Zapatillas Nike Airmax color negro, varios talles."));

        return productosList;

    }


}
