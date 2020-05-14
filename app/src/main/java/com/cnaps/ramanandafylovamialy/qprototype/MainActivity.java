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

import com.cnaps.ramanandafylovamialy.lib.Enquete;
import com.cnaps.ramanandafylovamialy.lib.OnTaskCompleted;
import com.cnaps.ramanandafylovamialy.lib.ServiceHTTP;
import com.cnaps.ramanandafylovamialy.lib.TypeAssujettis;
import com.cnaps.ramanandafylovamialy.lib.TypePrestation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<TypeAssujettis> listeTypeAssujettis;
    ArrayList<TypePrestation> listeTypePrestations;

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

        listeTypeAssujettis = new ArrayList<>();
        listeTypePrestations = new ArrayList<>();

        //initialisation des composants visuels
        select_assuj = (Spinner)findViewById(R.id.select_assuj);
        select_presta = (Spinner)findViewById(R.id.select_prest);
        text_age = (EditText)findViewById(R.id.text_age);
        select_sexe = (Spinner)findViewById(R.id.select_sexe);
        btn_valider = (Button)findViewById(R.id.btn_valider);

        ServiceHTTP serviceHTTPForAssuj = new ServiceHTTP();
        serviceHTTPForAssuj.context = this;
        serviceHTTPForAssuj.execute("http://192.168.6.247:3300/Assujettis/ass/list");
        serviceHTTPForAssuj.listener = new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String response) {
                try{
                    JSONArray jsa = new JSONArray(response);
                    for (int i = 0; i < jsa.length(); i++) {
                        JSONObject current = jsa.getJSONObject(i);
                        if (current.getInt("id_type") > 0 && !current.getString("lib_type").isEmpty()) {
                            listeTypeAssujettis.add(new TypeAssujettis(current.getInt("id_type"), current.getString("lib_type")));
                            ArrayAdapter<TypeAssujettis> adapterForAssuj = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, listeTypeAssujettis);
                            select_assuj.setAdapter(adapterForAssuj);
                        }
                    }
                }catch (JSONException e){

                }
            }
        };

        ServiceHTTP serviceHTTPForPresta = new ServiceHTTP();
        serviceHTTPForPresta.context = this;
        serviceHTTPForPresta.execute("http://192.168.6.247:3300/prest/prest/list");
        serviceHTTPForPresta.listener = new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String response) {
                try{
                    JSONArray jsa = new JSONArray(response);
                    for (int i = 0; i < jsa.length(); i++) {
                        JSONObject current = jsa.getJSONObject(i);
                        if (current.getInt("prestation_code") > 0 && !current.getString("prestation_libelle").isEmpty()) {
                            listeTypePrestations.add(new TypePrestation(current.getInt("prestation_code"), current.getString("prestation_libelle")));
                            ArrayAdapter<TypePrestation> adapterForPresta = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, listeTypePrestations);
                            select_presta.setAdapter(adapterForPresta);
                        }
                    }
                }catch (JSONException e){

                }
            }
        };


        ArrayAdapter<String> adapterForSexe = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[] {"M", "F"});
        select_sexe.setAdapter(adapterForSexe);

        enquete = new Enquete();


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
            enquete.setType_assujetti((int) id + 1);
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
