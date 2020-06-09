package com.cnaps.ramanandafylovamialy.lib;

import java.io.Serializable;

public class Enquete implements Serializable {

    private int id_enquete;
    private int type_assujetti;
    private String matricule;
    private String type_prestation;
    private int id_radio;
    private String rps;

    public Enquete(){

    }

    public Enquete(int id_enquete, int type_assujetti, String matricule, String type_prestation, int id_radio, String rps){
        this.id_enquete = id_enquete;
        this.type_assujetti = type_assujetti;
        this.matricule = matricule;
        this.type_prestation = type_prestation;
        this.id_radio = id_radio;
        this.rps = rps;
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

    public String getType_prestation() {
        return type_prestation;
    }

    public void setType_prestation(String type_prestation) {
        this.type_prestation = type_prestation;
    }

    public int getId_radio() {
        return id_radio;
    }

    public void setId_radio(int id_radio) {
        this.id_radio = id_radio;
    }

    public String getRps() {
        return rps;
    }

    public void setRps(String rps) {
        this.rps = rps;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
