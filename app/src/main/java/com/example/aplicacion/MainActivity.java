package com.example.aplicacion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacion.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int findNum, attempts;
    private Random r = new Random();
    private Intent intent;
    private AlertDialog nameDialog;
    private long startTimer, finishTimer;
    private Double totalTime;
    public int numMax = 10, numMin = 0;

    public class newUser{

        public String name;
        public int attempts;
        public double time;

        public newUser(String name, int attemps, Double time){
            this.name = name;
            this.attempts = attemps;
            this.time = time;
        }
    }

    public static ArrayList<newUser> record = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File path = getFilesDir();
        File fileRecords = new File(path+"dadesRanking.xml");

        if(!fileRecords.exists()){
            
        }
        Dades.Dades(fileRecords);

        startPlay();

        final Button button_comprova = findViewById(R.id.Comprova);
        button_comprova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                Toast toast;
                String warning;

                EditText numberUser = findViewById(R.id.numUser);
                Integer numUser = Integer.parseInt(String.valueOf( numberUser.getText() ));
                if (!numberUser.getText().toString().isEmpty()){
                    if(numUser == findNum){
                        attempts++;
                        finishTimer = System.currentTimeMillis();
                        totalTime = calculateTime(startTimer, finishTimer);
                        warning = "Felicidades, has acertado!";
                        toast = Toast.makeText(context, warning, Toast.LENGTH_LONG);
                        numberUser.setText("");
                        toast.show();

                        nameDialog();
                    } else if(numUser<findNum){
                        attempts++;
                        warning = "Numero demasiado pequeño!";
                        toast = Toast.makeText(context, warning, Toast.LENGTH_LONG);
                        numberUser.setText("");
                    } else {
                        attempts++;
                        warning = "Numero demasiado grande!";
                        toast = Toast.makeText(context, warning, Toast.LENGTH_LONG);
                        numberUser.setText("");
                    }
                    toast.show();
                } else {

                }

            }
        });

        final Button button_ranking = findViewById(R.id.ranking);
        button_ranking.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v){
                  intent = new Intent(MainActivity.this, RankingActivity.class);
                  startActivity(intent);
              }
          }
        );


    }

    public void nameDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Introdueix el teu nom");
        alert.setMessage("Introduir nom: ");
        final EditText name = new EditText(this);
        alert.setView(name);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String nameUser = name.getText().toString();
                record.add(new newUser(nameUser, attempts, totalTime));
                intent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent);

            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                nameDialog.cancel();
            }
        });

        alert.show();
    }

    public void startPlay(){
        startTimer = System.currentTimeMillis();
        findNum = (r.nextInt(numMax)+1);
        attempts = 0;
    }

    public double calculateTime(long startTimer, long finishTimer){
        double time = (double) ((finishTimer - startTimer)/1000);

        return time;
    }
}