package com.codespeedy.projectMP.Model;

import java.util.ArrayList;

public class SavePlayList {

    ArrayList<String> arrayList;

    public SavePlayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<String> getList()
    {
         return arrayList;
    }
}
