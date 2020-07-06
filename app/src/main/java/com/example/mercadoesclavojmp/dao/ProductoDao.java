package com.example.mercadoesclavojmp.dao;

import com.example.mercadoesclavojmp.model.ProductoContainer;
import com.example.mercadoesclavojmp.service.ProductoService;
import com.example.mercadoesclavojmp.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoDao extends RetrofitDao {

    private ProductoService productoService;

    public ProductoDao() {
        productoService = super.retrofit.create(ProductoService.class);
    }

    public void searchByQuery(String query, final ResultListener<ProductoContainer> resultListenerFromController){
        Call<ProductoContainer> call = this.productoService.searchByQuery(query);

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

}
