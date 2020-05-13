package com.cnaps.ramanandafylovamialy.lib;

public class TypePrestation {

    private int id_typePrestation;
    private String lib_typePrestation;

    public TypePrestation(){

    }

    public TypePrestation(int id_typePrestation, String lib_typePrestation){
        this.id_typePrestation = id_typePrestation;
        this.lib_typePrestation = lib_typePrestation;
    }

    public int getId_typePrestation() {
        return id_typePrestation;
    }

    public void setId_typePrestation(int id_typePrestation) {
        this.id_typePrestation = id_typePrestation;
    }

    public String getLib_typePrestation() {
        return lib_typePrestation;
    }

    public void setLib_typePrestation(String lib_typePrestation) {
        this.lib_typePrestation = lib_typePrestation;
    }

    @Override
    public String toString() {
        return this.lib_typePrestation;
    }
}
