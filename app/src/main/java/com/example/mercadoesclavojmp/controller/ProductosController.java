package com.example.mercadoesclavojmp.controller;

import com.example.mercadoesclavojmp.dao.ProductosDao;
import com.example.mercadoesclavojmp.model.Productos;

import java.util.ArrayList;
import java.util.List;

public class ProductosController {

    public List<Productos> getProductos(){
        List<Productos> result = new ArrayList<>();
        if (hayinternet()){
            //ir a internet y buscar los productos.
            result = new ArrayList<>();
        }else {
            //los busco en el ProductosDao que es estatico.
            result = ProductosDao.getProductos();
        }
        return result;
    }

    public boolean hayinternet(){
        return false;
    }

}
