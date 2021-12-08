package com.example.esummerce.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDto {
    String id;
    String name;
    String image;
    String price;
    String description;
    String producedBy;
    String city;
    String img_presentation;
    String presentation;
    String phone;
    String stock;

    public  ProductDto(JSONObject json) throws JSONException {
        this.id=json.getString("id");
        this.name=json.getString("name");
        this.image=json.getString("image");
        this.price=json.getString("price");
        this.description=json.getString("description");
        this.producedBy=json.getString("producedBy");
        this.city=json.getString("city");
        this.img_presentation=json.getString("img_presentation");
        this.presentation=json.getString("presentation");
        this.phone=json.getString("phone");
        this.stock=json.getString("stock");
    }

    public ProductDto(String id, String name, String image, String price,
                      String description, String producedBy, String city,
                      String img_presentation, String presentation, String phone, String stock){
        this.id=id;
        this.name=name;
        this.image=image;
        this.price=price;
        this.description=description;
        this.producedBy=producedBy;
        this.city=city;
        this.img_presentation=img_presentation;
        this.presentation=presentation;
        this.phone=phone;
        this.stock=stock;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStock() {
        return stock;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    public String getImg_presentation() {
        return img_presentation;
    }

    public String getPhone() {
        return phone;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getPrice() {
        return price;
    }

    public String getProducedBy() {
        return producedBy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImg_presentation(String img_presentation) {
        this.img_presentation = img_presentation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProducedBy(String producedBy) {
        this.producedBy = producedBy;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
