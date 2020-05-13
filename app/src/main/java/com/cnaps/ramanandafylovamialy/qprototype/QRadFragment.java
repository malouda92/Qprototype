package com.cnaps.ramanandafylovamialy.qprototype;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cnaps.ramanandafylovamialy.lib.ListeChoixRad;
import com.cnaps.ramanandafylovamialy.lib.Question;
import com.cnaps.ramanandafylovamialy.lib.QuestionRad;
import com.cnaps.ramanandafylovamialy.lib.ResultatQuest;


public class QRadFragment extends Fragment implements View.OnClickListener {

    private QuestionRad q;
    private ListeChoixRad[] listeChoixRads;
    TextView quest_quizz = null;
    RadioGroup rad_group = null;
    Button btn_valider = null;
    View view;

    public QRadFragment() {
        // Required empty public constructor
    }

    public QRadFragment(QuestionRad q, ListeChoixRad[] listeChoixRads){
        this.q = q;
        this.listeChoixRads = listeChoixRads;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_qrad, container, false);

        quest_quizz = view.findViewById(R.id.quest_quizz);
        rad_group = view.findViewById(R.id.rad_group);
        btn_valider = view.findViewById(R.id.btn_valider);

        quest_quizz.setText(this.q.getLib_questionRad());
        btn_valider.setOnClickListener(this);

        for (int i = 0; i < listeChoixRads.length; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(listeChoixRads[i].getLib_listeChoix());
            rad_group.addView(rb, new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        int selectedId = rad_group.getCheckedRadioButtonId();
        Log.e("tag", ""+selectedId);
        if(selectedId > 0){
            ((Main2Activity)getActivity()).cpt++;

            RadioButton rad = (RadioButton)this.view.findViewById(selectedId);

            ((Main2Activity)getActivity()).resultatQuests.add(new ResultatQuest(((Main2Activity)getActivity()).cpt, this.q.getId_questionRad(), rad.getText().toString()));

            ((Main2Activity)getActivity()).next();
        }else{
            new AlertDialog.Builder(getContext()).setTitle("Erreur").setMessage("Veuillez choisir une réponse").setCancelable(false).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }

    }
}
