package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    private Intent intent;

    ArrayList Record = MainActivity.record;

    ArrayAdapter<MainActivity.newUser> adapter;

   //final ListView records = findViewById(R.id.listRecords);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        adapter = new ArrayAdapter<MainActivity.newUser>(this, R.id.layout, Record){
            @Override
            public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup container) {
                if( convertView==null ) {
                    convertView = getLayoutInflater().inflate(R.layout.layout, container, false);
                }

                ((TextView) convertView.findViewById(R.id.nameUser)).setText("Nom: "+getItem(pos).name);
                ((TextView) convertView.findViewById(R.id.userAttempts)).setText("Intents: "+Integer.toString(getItem(pos).attempts));
                ((TextView) convertView.findViewById(R.id.userTime)).setText("Temps: "+Double.toString(getItem(pos).time)+"s");

                return convertView;
            }
        };

        ListView listRecord = findViewById(R.id.listRecords);
        listRecord.setAdapter(adapter);

        final Button button_Juga = findViewById(R.id.buttonJuga);
        button_Juga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}