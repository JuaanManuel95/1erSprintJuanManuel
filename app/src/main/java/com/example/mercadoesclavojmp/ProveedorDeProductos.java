package com.example.mercadoesclavojmp;

import java.util.ArrayList;
import java.util.List;

public abstract class ProveedorDeProductos {

    public static List<Productos> getProductos(){
        List<Productos> productosList = new ArrayList<>();

        productosList.add(new Productos("Airpods",R.drawable.airpods, "$30.000"));
        productosList.add(new Productos("iPad",R.drawable.ipad, "$120.000"));
        productosList.add(new Productos("Equipo de música",R.drawable.parlantes,"$40.000"));
        productosList.add(new Productos("PlayStation 4",R.drawable.playstation4, "$25.000"));
        productosList.add(new Productos("Gafas Ray-Ban",R.drawable.rayban, "$15.000"));
        productosList.add(new Productos("TV LED FULL HD",R.drawable.televisor, "$24.000"));
        productosList.add(new Productos("Zapatillas Nike Airmax",R.drawable.zapatillas_nike, "$8.000"));

        return productosList;

    }
}
