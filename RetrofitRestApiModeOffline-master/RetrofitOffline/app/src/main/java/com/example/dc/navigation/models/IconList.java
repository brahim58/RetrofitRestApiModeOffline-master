package com.example.dc.navigation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IconList {

    @SerializedName("icone")
    private ArrayList<Icon> iconeList;

    public ArrayList<Icon> getIconeList() {
        return iconeList;
    }

    public void setIconeList(ArrayList<Icon> iconeList) {
        this.iconeList = iconeList;
    }
}
