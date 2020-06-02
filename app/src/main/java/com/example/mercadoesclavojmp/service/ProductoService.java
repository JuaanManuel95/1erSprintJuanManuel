package com.example.mercadoesclavojmp.service;

import com.example.mercadoesclavojmp.model.ProductoContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("/sites/MLA/search?q=")
    Call<ProductoContainer> searchByQuery (@Query("producto")String producto);
}
