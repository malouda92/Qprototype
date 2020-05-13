package com.cnaps.ramanandafylovamialy.lib;

public class ResultatQuest {

    private int id_reponse;
    private int id_question;
    private String lib_reponse;

    public ResultatQuest() {

    }

    public ResultatQuest(int id_reponse, int id_question, String lib_reponse) {
        this.id_question = id_question;
        this.id_reponse = id_reponse;
        this.lib_reponse = lib_reponse;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getLib_reponse() {
        return lib_reponse;
    }

    public void setLib_reponse(String lib_reponse) {
        this.lib_reponse = lib_reponse;
    }
}
