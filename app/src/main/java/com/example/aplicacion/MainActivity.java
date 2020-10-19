package com.example.aplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int findNum, attempts, playerNum = 0;
    private Random r = new Random();
    private Intent intent;
    private AlertDialog nameDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                if(numUser == findNum){
                    attempts++;
                    warning = "Felicidades, has acertado!";
                    toast = Toast.makeText(context, warning, Toast.LENGTH_LONG);
                    numberUser.setText("");
                    toast.show();
                    intent = new Intent(MainActivity.this, RankingActivity.class);
                    intent.putExtra("NAME", "Player"+playerNum);
                    intent.putExtra("ATTEMPTS", attempts);
                    startActivity(intent);
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


            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }

    public void startPlay(){
        findNum = (r.nextInt(10)+1);
        attempts = 0;
    }
}