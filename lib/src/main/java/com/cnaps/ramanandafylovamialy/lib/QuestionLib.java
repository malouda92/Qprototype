package com.cnaps.ramanandafylovamialy.lib;

public class QuestionLib extends Question {

    private int id_questionLib;
    private String lib_questionLib;

    public QuestionLib(){
        super();
    }

    public QuestionLib(int id_question, int id_questionLib, String lib_questionLib){
        super(id_question);
        this.id_questionLib = id_questionLib;
        this.lib_questionLib = lib_questionLib;
    }

    public int getId_questionLib() {
        return id_questionLib;
    }

    public void setId_questionLib(int id_questionLib) {
        this.id_questionLib = id_questionLib;
    }

    public String getLib_questionLib() {
        return lib_questionLib;
    }

    public void setLib_questionLib(String lib_questionLib) {
        this.lib_questionLib = lib_questionLib;
    }
}
