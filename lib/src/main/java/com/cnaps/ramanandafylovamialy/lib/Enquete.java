package com.cnaps.ramanandafylovamialy.lib;

import java.io.Serializable;

public class Enquete implements Serializable {

    private int id_enquete;
    private int type_assujetti;
    private int type_prestation;
    private int age;
    private char sexe;

    public Enquete(){

    }

    public Enquete(int id_enquete, int type_assujetti, int type_prestation, int age, char sexe){
        this.id_enquete = id_enquete;
        this.type_assujetti = type_assujetti;
        this.type_prestation = type_prestation;
        this.age = age;
        this.sexe = sexe;
    }

    public int getId_enquete() {
        return id_enquete;
    }

    public void setId_enquete(int id_enquete) {
        this.id_enquete = id_enquete;
    }

    public int getType_assujetti() {
        return type_assujetti;
    }

    public void setType_assujetti(int type_assujetti) {
        this.type_assujetti = type_assujetti;
    }

    public int getType_prestation() {
        return type_prestation;
    }

    public void setType_prestation(int type_prestation) {
        this.type_prestation = type_prestation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }
}
