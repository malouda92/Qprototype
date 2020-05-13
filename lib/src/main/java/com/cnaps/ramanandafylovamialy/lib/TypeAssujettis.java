package com.cnaps.ramanandafylovamialy.lib;

public class TypeAssujettis {
    private int id_typeAssujettis;
    private String lib_typeAssujettis;

    public TypeAssujettis(){

    }

    public TypeAssujettis(int id_typeAssujettis, String lib_typeAssujettis) {
        this.id_typeAssujettis = id_typeAssujettis;
        this.lib_typeAssujettis = lib_typeAssujettis;
    }

    public int getId_typeAssujettis() {
        return id_typeAssujettis;
    }

    public void setId_typeAssujettis(int id_typeAssujettis) {
        this.id_typeAssujettis = id_typeAssujettis;
    }

    public String getLib_typeAssujettis() {
        return lib_typeAssujettis;
    }

    public void setLib_typeAssujettis(String lib_typeAssujettis) {
        this.lib_typeAssujettis = lib_typeAssujettis;
    }

    @Override
    public String toString() {
        return this.lib_typeAssujettis;
    }
}
