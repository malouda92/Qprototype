package com.cnaps.ramanandafylovamialy.lib;

public class ListeChoixRad {
    private int id_listeChoix;
    private String lib_listeChoix;

    public ListeChoixRad(){

    }

    public ListeChoixRad(int id_listeChoix, String lib_listeChoix){
        this.id_listeChoix = id_listeChoix;
        this.lib_listeChoix = lib_listeChoix;
    }

    public int getId_listeChoix() {
        return id_listeChoix;
    }

    public void setId_listeChoix(int id_listeChoix) {
        this.id_listeChoix = id_listeChoix;
    }

    public String getLib_listeChoix() {
        return lib_listeChoix;
    }

    public void setLib_listeChoix(String lib_listeChoix) {
        this.lib_listeChoix = lib_listeChoix;
    }
}
