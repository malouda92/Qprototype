package com.cnaps.ramanandafylovamialy.qprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.cnaps.ramanandafylovamialy.lib.Enquete;
import com.cnaps.ramanandafylovamialy.lib.OnTaskCompleted;
import com.cnaps.ramanandafylovamialy.lib.ServiceHTTPGet;
import com.cnaps.ramanandafylovamialy.lib.ServiceHTTPPost;
import com.cnaps.ramanandafylovamialy.lib.TypeAssujettis;
import com.cnaps.ramanandafylovamialy.lib.TypePrestation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<TypeAssujettis> listeTypeAssujettis;
    ArrayList<TypePrestation> listeTypePrestations;

    Spinner select_assuj = null;
    Spinner select_presta = null;
    Button btn_valider = null;
    Enquete enquete = null;
    EditText text_matricule = null;
    EditText text_nom = null;
    EditText text_pourquoi = null;
    RadioGroup group = null;
    ProgressDialog pb= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        pb = new ProgressDialog(this);
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pb.setMessage("en cours");

        listeTypeAssujettis = new ArrayList<>();
        listeTypePrestations = new ArrayList<>();

        //initialisation des composants visuels
        select_assuj = findViewById(R.id.select_assuj);
        select_presta = findViewById(R.id.select_prest);
        btn_valider = findViewById(R.id.btn_valider);
        text_matricule = findViewById(R.id.text_matricule);
        text_nom = findViewById(R.id.text_nom);
        group = findViewById(R.id.group);
        text_pourquoi = findViewById(R.id.text_pourquoi);

        select_assuj.setOnItemSelectedListener(this);
        select_presta.setOnItemSelectedListener(this);

        ServiceHTTPGet serviceHTTPForAssuj = new ServiceHTTPGet();
        serviceHTTPForAssuj.execute("http://192.168.6.247:3300/Assujettis/ass/list");
        serviceHTTPForAssuj.setListener(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String response) {
                if(response != null) {
                    try {
                        JSONArray jsa = new JSONArray(response);
                        for (int i = 0; i < jsa.length(); i++) {
                            JSONObject current = jsa.getJSONObject(i);
                            if (current.getInt("id_type") > 0 && !current.getString("lib_type").isEmpty()) {
                                listeTypeAssujettis.add(new TypeAssujettis(current.getInt("id_type"), current.getString("lib_type")));
                                ArrayAdapter<TypeAssujettis> adapterForAssuj = new ArrayAdapter<>(Main3Activity.this, android.R.layout.simple_spinner_dropdown_item, listeTypeAssujettis);
                                select_assuj.setAdapter(adapterForAssuj);
                            }
                        }
                    } catch (JSONException e) {
                        Log.e("erreur", e.getMessage());
                    }
                }
            }
        });

        ServiceHTTPGet serviceHTTPForPresta = new ServiceHTTPGet();
        serviceHTTPForPresta.execute("http://192.168.6.247:3300/prest/prest/list");
        serviceHTTPForPresta.setListener(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String response) {
                if(response != null) {
                    try {
                        JSONArray jsa = new JSONArray(response);
                        for (int i = 0; i < jsa.length(); i++) {
                            JSONObject current = jsa.getJSONObject(i);
                            if (!current.getString("prestation_code").isEmpty() && !current.getString("prestation_libelle").isEmpty()) {
                                listeTypePrestations.add(new TypePrestation(current.getString("prestation_code"), current.getString("prestation_libelle")));
                                ArrayAdapter<TypePrestation> adapterForPresta = new ArrayAdapter<>(Main3Activity.this, android.R.layout.simple_spinner_dropdown_item, listeTypePrestations);
                                select_presta.setAdapter(adapterForPresta);
                            }
                        }
                    } catch (JSONException e) {
                        Log.e("erreur", e.getMessage());
                    }
                }
            }
        });

        enquete = new Enquete();

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_matricule.getText().toString().isEmpty() && text_nom.getText().toString().isEmpty()){
                    new AlertDialog.Builder(Main3Activity.this).setTitle("Erreur").setMessage("Veuillez nous informer votre matricule ou votre nom").setNeutralButton("OK", null).setCancelable(false).show();
                }else{
                    enquete.setMatricule(text_matricule.getText().toString());

                    int selectedId = group.getCheckedRadioButtonId();
                    RadioButton radioSelected = findViewById(selectedId);
                    if (selectedId > 0 && !text_pourquoi.getText().toString().isEmpty()) {
                        enquete.setId_radio(Integer.parseInt(radioSelected.getText().toString()));
                        enquete.setRps(text_pourquoi.getText().toString());
                        JSONObject jso = new JSONObject();
                        try {
                            jso.put("id_type", enquete.getType_assujetti());
                            jso.put("matricule", enquete.getMatricule());
                            jso.put("prestation_code", enquete.getType_prestation());
                            jso.put("choix", enquete.getId_radio());
                            jso.put("pourquoi", enquete.getRps());

                            pb.show();
                            ServiceHTTPPost serviceHTTPForPOST = new ServiceHTTPPost();
                            serviceHTTPForPOST.execute("http://192.168.6.247:3300/reponses/rps/ajout", jso.toString());
                            serviceHTTPForPOST.setListener(new OnTaskCompleted() {
                                @Override
                                public void onTaskCompleted(String response) {
                                    pb.hide();
                                    new AlertDialog.Builder(Main3Activity.this).setTitle("success").setMessage("Merci de votre collaboration").setCancelable(false).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);
                                        }
                                    }).show();
                                }
                            });
                        } catch (JSONException e) {
                            Log.e("Erreur", "" + e.getMessage());
                        }
                    }else {
                        new AlertDialog.Builder(Main3Activity.this).setTitle("Erreur").setMessage("Veuillez fournir votre réponse").setCancelable(false).setNeutralButton("OK", null).show();
                    }
                }
            }
        });

        text_matricule.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String assujettis = select_assuj.getSelectedItem().toString();
                if (assujettis.equals("TRAVAILLEUR")) {
                    ServiceHTTPGet serviceHTTPForMat = new ServiceHTTPGet();
                    serviceHTTPForMat.execute("http://192.168.6.247:3300/traval/trav/selectlike/" + s);
                    serviceHTTPForMat.setListener(new OnTaskCompleted() {
                        @Override
                        public void onTaskCompleted(String response) {
                            if(response != null) {
                                try {
                                    JSONObject jso = new JSONObject(response);
                                    if (!jso.getString("travailleur_nom").isEmpty()) {
                                        text_nom.setText(jso.getString("travailleur_nom"));
                                    }else{
                                        text_nom.setText("");
                                    }
                                }catch (JSONException e) {
                                    Log.e("erreur", e.getMessage());
                                }
                            }
                        }
                    });
                }else if(assujettis.equals("EMPLOYEUR")) {
                    ServiceHTTPGet serviceHTTPForMat = new ServiceHTTPGet();
                    serviceHTTPForMat.execute("http://192.168.6.247:3300/emplo/empl/select/" + s);
                    serviceHTTPForMat.setListener(new OnTaskCompleted() {
                        @Override
                        public void onTaskCompleted(String response) {
                            if(response != null) {
                                try {
                                    JSONObject jso = new JSONObject(response);
                                    if (!jso.getString("temployeur_nom").isEmpty()) {
                                        text_nom.setText(jso.getString("temployeur_nom"));
                                    }else{
                                        text_nom.setText("");
                                    }
                                }catch (JSONException e) {
                                    Log.e("erreur", e.getMessage());
                                }
                            }
                        }
                    });
                }else{
                    ServiceHTTPGet serviceHTTPForMat = new ServiceHTTPGet();
                    serviceHTTPForMat.execute("http://192.168.6.247:3300/retr/pen/selectlike/" + s);
                    serviceHTTPForMat.setListener(new OnTaskCompleted() {
                        @Override
                        public void onTaskCompleted(String response) {
                            if(response != null) {
                                try {
                                    JSONObject jso = new JSONObject(response);
                                    if (!jso.getString("benficiaire_nom").isEmpty()) {
                                        text_nom.setText(jso.getString("benficiaire_nom"));
                                    }else{
                                        text_nom.setText("");
                                    }
                                }catch (JSONException e) {
                                    Log.e("erreur", e.getMessage());
                                }
                            }
                        }
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("tag", ""+ s);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == select_assuj.getId()) {
            text_matricule.setText(" ");
            text_nom.setText(" ");
            enquete.setType_assujetti((int) id + 1);
        }else if(parent.getId() == select_presta.getId()) {
            enquete.setType_prestation(listeTypePrestations.get((int)parent.getItemIdAtPosition(position)).getId_typePrestation());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
