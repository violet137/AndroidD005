package com.greenacademy.ga_finalprojecthm.model.Home;

import java.util.ArrayList;

/**
 * Created by thepa on 11/19/2017.
 */

public class FashionCatalogResponse {
    private ArrayList<FashionCatalog> fashionCatalogs = new ArrayList<>();
    private int status;
    private String description;

    public ArrayList<FashionCatalog> getFashionCatalogs() {
        return fashionCatalogs;
    }

    public void setFashionCatalogs(ArrayList<FashionCatalog> fashionCatalogs) {
        this.fashionCatalogs = fashionCatalogs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
