package com.codespeedy.projectMP.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveFavouriteList {


    ArrayList<String> arrayList;

    public SaveFavouriteList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<String> getList()
    {
        return arrayList;
    }
}
