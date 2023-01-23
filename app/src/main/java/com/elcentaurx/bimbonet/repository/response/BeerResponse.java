package com.elcentaurx.bimbonet.repository.response;

import androidx.lifecycle.MutableLiveData;

import com.elcentaurx.bimbonet.model.Beer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeerResponse {
    @SerializedName("image_url")
    @Expose
    private String image_url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "BeerResponse{" +
                "image_url='" + image_url + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return this.name;
    }
}
