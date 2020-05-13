package com.cnaps.ramanandafylovamialy.lib;

public class QuestionRad extends Question {

    private int id_questionRad;
    private String lib_questionRad;
    private ListeChoixRad[] listeChoixRads;

    public QuestionRad(){
        super();
    }

    public QuestionRad(int id_question, int id_questionRad, String lib_questionRad, ListeChoixRad[] listeChoixRads){
        super(id_question);
        this.id_questionRad = id_questionRad;
        this.lib_questionRad = lib_questionRad;
        this.listeChoixRads = listeChoixRads;
    }

    public int getId_questionRad() {
        return id_questionRad;
    }

    public void setId_questionRad(int id_questionRad) {
        this.id_questionRad = id_questionRad;
    }

    public String getLib_questionRad() {
        return lib_questionRad;
    }

    public void setLib_questionRad(String lib_questionRad) {
        this.lib_questionRad = lib_questionRad;
    }

    public ListeChoixRad[] getListeChoixRads() {
        return listeChoixRads;
    }

    public void setListeChoixRads(ListeChoixRad[] listeChoixRads) {
        this.listeChoixRads = listeChoixRads;
    }
}
