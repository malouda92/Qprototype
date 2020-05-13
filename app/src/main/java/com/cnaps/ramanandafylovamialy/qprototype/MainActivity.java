package com.cnaps.ramanandafylovamialy.qprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cnaps.ramanandafylovamialy.lib.Enquete;
import com.cnaps.ramanandafylovamialy.lib.TypeAssujettis;
import com.cnaps.ramanandafylovamialy.lib.TypePrestation;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<TypeAssujettis> listeTypeAssujettis = null;
    ArrayList<TypePrestation> listeTypePrestations = null;
    Spinner select_assuj = null;
    Spinner select_presta = null;
    Spinner select_sexe = null;
    Button btn_valider = null;
    Enquete enquete = null;
    EditText text_age = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialisation des classes
        listeTypeAssujettis = new ArrayList<>();
        listeTypePrestations = new ArrayList<>();
        ArrayAdapter<TypeAssujettis> adapterForAssuj = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listeTypeAssujettis);
        ArrayAdapter<String> adapterForSexe = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[] {"M", "F"});
        ArrayAdapter<TypePrestation> adapterForPresta = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listeTypePrestations);
        adapterForPresta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enquete = new Enquete();

        listeTypeAssujettis.add(new TypeAssujettis(1, "Travailleurs"));
        listeTypeAssujettis.add(new TypeAssujettis(2, "Employeurs"));
        listeTypePrestations.add(new TypePrestation(400, "Allocation Familiale"));
        listeTypePrestations.add(new TypePrestation(310, "Pension de vieillesse"));

        //initialisation des composants visuels
        select_assuj = (Spinner)findViewById(R.id.select_assuj);
        select_presta = (Spinner)findViewById(R.id.select_prest);
        text_age = (EditText)findViewById(R.id.text_age);
        select_sexe = (Spinner)findViewById(R.id.select_sexe);
        btn_valider = (Button)findViewById(R.id.btn_valider);

        select_assuj.setAdapter(adapterForAssuj);
        select_presta.setAdapter(adapterForPresta);
        select_sexe.setAdapter(adapterForSexe);

        select_assuj.setOnItemSelectedListener(this);
        select_presta.setOnItemSelectedListener(this);
        select_sexe.setOnItemSelectedListener(this);

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_age.getText().toString().isEmpty()){
                    new AlertDialog.Builder(MainActivity.this).setTitle("Erreur").setMessage("Veuillez préciser votre âge").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setCancelable(false).show();
                }else{
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("enquete", enquete);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == select_assuj.getId()){
            enquete.setType_assujetti((int) id);
        }else if(parent.getId() == select_presta.getId()){
            enquete.setType_prestation(listeTypePrestations.get((int)parent.getItemIdAtPosition(position)).getId_typePrestation());
        }else{
            enquete.setSexe(parent.getItemAtPosition(position).toString().charAt(0));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
