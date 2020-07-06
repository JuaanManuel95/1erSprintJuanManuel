package com.example.mercadoesclavojmp.controller;



import com.example.mercadoesclavojmp.dao.ProductoDao;
import com.example.mercadoesclavojmp.model.ProductoContainer;
import com.example.mercadoesclavojmp.util.ResultListener;

public class ProductosController {

    private ProductoDao productoDao;

    public ProductosController() {
        this.productoDao = new ProductoDao();
    }

    public void searchByQuery(String query, final ResultListener <ProductoContainer> resultListenerFromView){
        productoDao.searchByQuery(query, new ResultListener<ProductoContainer>() {
            @Override
            public void onFinish(ProductoContainer result) {
                resultListenerFromView.onFinish(result);

            }
        });
    }
}

