package com.example.mercadoesclavojmp.model;

import java.io.Serializable;
import java.util.List;

public class Producto implements Serializable {


    private String id;
    private String title;
    private Double price;
    private String condition;
    private String thumbnail;
    private List<Producto> pictures;

    public Producto(){

    }


    public Producto(String id, String title, Double price, String condition, String thumbnail, List<Producto> pictures) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.condition = condition;
        this.thumbnail = thumbnail;
        this.pictures = pictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Producto> getPictures() {
        return pictures;
    }

    public void setPictures(List<Producto> pictures) {
        this.pictures = pictures;
    }

}


