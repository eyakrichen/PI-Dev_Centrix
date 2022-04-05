/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Refka
 */
public class Produit {
    private int id;
    private String name;
    private String description;
    private float price;
    private int rate; 
    private String image;
    private String release_date;

    public Produit(String name, String description, float price, int rate, String image,String release_date) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.image = image;
        this.release_date = release_date;
    }

    public Produit(int id) {
        this.id = id;
    }

    public Produit(int id, String name, String description, float price, int rate, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.image = image;
    }

    public Produit(int id, String name, String description, float price, int rate, String image,String release_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.image = image;
        this.release_date = release_date;
    }


    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", rate=" + rate + ", image=" + image + ", release_date=" + release_date + '}';
    }
    


    
}
