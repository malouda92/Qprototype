package com.cnaps.ramanandafylovamialy.lib;

public class QuestionChk extends Question {

    private int id_questionChk;
    private String lib_questionChk;

    public QuestionChk(){
        super();
    }

    public QuestionChk(int id_question, int id_questionChk, String lib_questionChk){
        super(id_question);
        this.id_questionChk = id_questionChk;
        this.lib_questionChk = lib_questionChk;
    }

    public int getId_questionChk() {
        return id_questionChk;
    }

    public void setId_questionChk(int id_questionChk) {
        this.id_questionChk = id_questionChk;
    }

    public String getLib_questionChk() {
        return lib_questionChk;
    }

    public void setLib_questionChk(String lib_questionChk) {
        this.lib_questionChk = lib_questionChk;
    }
}
