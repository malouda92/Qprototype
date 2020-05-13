package com.cnaps.ramanandafylovamialy.qprototype;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cnaps.ramanandafylovamialy.lib.QuestionLib;
import com.cnaps.ramanandafylovamialy.lib.ResultatQuest;


public class QLibreFragment extends Fragment implements View.OnClickListener {

    private QuestionLib q;
    TextView quest_quizz = null;
    EditText rps_quizz = null;
    Button btn_valider = null;

    public QLibreFragment() {
        // Required empty public constructor
    }

    public QLibreFragment(QuestionLib q){
        this.q = q;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_qlibre, container, false);

        quest_quizz = view.findViewById(R.id.quest_quizz);
        rps_quizz = view.findViewById(R.id.rps_quizz);
        btn_valider = view.findViewById(R.id.btn_valider);

        quest_quizz.setText(this.q.getLib_questionLib());
        btn_valider.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(rps_quizz.getText().toString().isEmpty()){
            new AlertDialog.Builder(getContext()).setTitle("Erreur").setMessage("Veuillez saisir votre réponse").setCancelable(false).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }else{
            ((Main2Activity)getActivity()).cpt++;

            ((Main2Activity)getActivity()).resultatQuests.add(new ResultatQuest(((Main2Activity)getActivity()).cpt, this.q.getId_questionLib(), rps_quizz.getText().toString()));

            ((Main2Activity)getActivity()).next();
        }

    }
}