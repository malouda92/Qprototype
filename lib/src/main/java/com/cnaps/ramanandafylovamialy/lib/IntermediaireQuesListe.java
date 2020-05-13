package com.cnaps.ramanandafylovamialy.lib;

public class IntermediaireQuesListe {

    private int id_QuestionRad;
    private int id_listeChoix;

    public IntermediaireQuesListe(){

    }

    public IntermediaireQuesListe(int id_QuestionRad, int id_listeChoix){
        this.id_QuestionRad = id_QuestionRad;
        this.id_listeChoix = id_listeChoix;
    }

    public int getId_QuestionRad() {
        return id_QuestionRad;
    }

    public void setId_QuestionRad(int id_QuestionRad) {
        this.id_QuestionRad = id_QuestionRad;
    }

    public int getId_listeChoix() {
        return id_listeChoix;
    }

    public void setId_listeChoix(int id_listeChoix) {
        this.id_listeChoix = id_listeChoix;
    }
}
