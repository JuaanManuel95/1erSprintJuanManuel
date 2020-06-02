package com.example.mercadoesclavojmp.controller;

import com.example.mercadoesclavojmp.dao.ProductoDao;
import com.example.mercadoesclavojmp.model.ProductoContainer;
import com.example.mercadoesclavojmp.model.Productos;
import com.example.mercadoesclavojmp.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ProductosController {

    private ProductoDao productoDao;

    public ProductosController() {
        this.productoDao = new ProductoDao();
    }

    public void searchByQuery(String id, final ResultListener <ProductoContainer> resultListenerFromView){
        productoDao.searchByQuery(id, new ResultListener<ProductoContainer>() {
            @Override
            public void onFinish(ProductoContainer result) {
                resultListenerFromView.onFinish(result);

            }
        });
    }
}









/**
 public List<Productos> getProductos(){
 List<Productos> result = new ArrayList<>();
 if (hayinternet()){
 //ir a internet y buscar los productos.
 result = new ArrayList<>();
 }else {
 //los busco en el ProductosDao que es estatico.
 result = ProductoDao.getProductos();
 }
 return result;
 }

 public boolean hayinternet(){
 return false;
 }
 */
