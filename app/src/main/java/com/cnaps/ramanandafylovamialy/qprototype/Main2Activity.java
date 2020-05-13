package com.cnaps.ramanandafylovamialy.qprototype;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cnaps.ramanandafylovamialy.lib.Enquete;
import com.cnaps.ramanandafylovamialy.lib.ListeChoixRad;
import com.cnaps.ramanandafylovamialy.lib.Question;
import com.cnaps.ramanandafylovamialy.lib.QuestionLib;
import com.cnaps.ramanandafylovamialy.lib.QuestionRad;
import com.cnaps.ramanandafylovamialy.lib.ResultatQuest;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    Enquete enquete;
    ArrayList<Question> questions = new ArrayList<>();
    int cpt = 0;
    ArrayList<ResultatQuest> resultatQuests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        enquete = (Enquete) getIntent().getSerializableExtra("enquete");
        questions.add(new QuestionLib(1, 1, "Votre avis?"));
        ListeChoixRad[] listeChoixRads = new ListeChoixRad[]{new ListeChoixRad(1, "Satisfait"), new ListeChoixRad(2, "Pas satisfait"), new ListeChoixRad(3, "Indécit")};
        ListeChoixRad[] listeChoixRads2 = new ListeChoixRad[]{new ListeChoixRad(2, "Oui"), new ListeChoixRad(2, "Non")};
        questions.add(new QuestionRad(2, 1, "Êtes-vous satisfait?", listeChoixRads));
        questions.add(new QuestionRad(3, 2, "Êtes-vous satisfait?", listeChoixRads2));

        next();

    }

    public void next(){
        if(cpt < questions.size()) {
            if (questions.get(cpt) instanceof QuestionLib) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, new QLibreFragment((QuestionLib) questions.get(cpt)));
                transaction.commit();
            }else if (questions.get(cpt) instanceof QuestionRad){
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, new QRadFragment(((QuestionRad) questions.get(cpt)), ((QuestionRad) questions.get(cpt)).getListeChoixRads()));
                transaction.commit();
            }
        }else{
            new AlertDialog.Builder(this).setTitle("Validation").setMessage("Souhaitez-vous valider?").setCancelable(false).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Main2Activity.this, MainActivity.class));
                }
            }).show();
        }
    }
}
