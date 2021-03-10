package com.example.pain2vieapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    static int TIMEOUT_MILLIS = 2000;
    int counter = 3;

    Button bt_login;
    EditText loginEmail, loginPassword; // yhwh

    TextView bottomValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bt_login = (Button)findViewById(R.id.btn_login);
        loginEmail = (EditText)findViewById(R.id.et_email);
        loginPassword = (EditText)findViewById(R.id.et_password);

        bottomValid = (TextView)findViewById(R.id.bottomText);
        bottomValid.setVisibility(View.GONE);

        bt_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String mail = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Veuillez entrer un indentifiant valide",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Mot de passe incorrect",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(mail.equals("pain2vie") && password.equals("yhwh")) {
                    bottomValid.setVisibility(View.VISIBLE);
                    bottomValid.setTextColor(Color.WHITE);
                    bottomValid.setText(R.string.bottom_validator_register);
                    Toast.makeText(getApplicationContext(),
                            "Connexion en cours...",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            Intent i = new Intent(LoginActivity.this, WebViewActivity.class);
                            startActivity(i);

                            // close this activity
                            finish();
                        }
                    }, TIMEOUT_MILLIS);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Informations incorrectes",Toast.LENGTH_SHORT).show();

                            bottomValid.setVisibility(View.VISIBLE);
                    bottomValid.setTextColor(Color.RED);
                    counter--;
                    bottomValid.setText("Reste " + Integer.toString(counter) + " tentatives");

                    if (counter == 0) {
                        bt_login.setEnabled(false);
                        bt_login.setClickable(false);
                        bottomValid.setText("Bloqué! Plus de 3 tentatives");
                    }
                }
            }
        });
    }
}
