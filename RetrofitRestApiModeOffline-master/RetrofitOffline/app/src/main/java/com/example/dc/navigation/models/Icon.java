package com.example.dc.navigation.models;

import com.google.gson.annotations.SerializedName;

public class Icon {

    @SerializedName("nome")
    private String name;

    @SerializedName("desc")
    private String desc;

    @SerializedName("url_foto")
    private String urlImage;

    public Icon() {
    }

    public Icon(String name, String desc, String urlImage) {
        this.name = name;
        this.desc = desc;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
