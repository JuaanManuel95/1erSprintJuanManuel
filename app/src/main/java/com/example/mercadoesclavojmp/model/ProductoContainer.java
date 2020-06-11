package com.example.mercadoesclavojmp.model;

import java.io.Serializable;
import java.util.List;

public class ProductoContainer implements Serializable {

    private List<Producto> results;


    public ProductoContainer(List<Producto> results) {
        this.results = results;
    }

    public List<Producto> getResults() {
        return results;
    }

    public void setResults(List<Producto> results) {
        this.results = results;
    }
}
