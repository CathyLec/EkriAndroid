package com.movilbox.lector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.movilbox.lector.Referencias.Auth.BaseActivity;

public class Singin extends BaseActivity implements View.OnClickListener {
    private TextView txtSingup;
    private EditText inputMail;
    private EditText inputPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        txtSingup=(TextView) findViewById(R.id.link_Singup);
        inputMail=(EditText)findViewById(R.id.input_email);
        inputPassword=(EditText) findViewById(R.id.input_password);
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.btn_signin).setOnClickListener(this);
        mAuth= FirebaseAuth.getInstance();
    }

    public void signIn(){
        final String email=inputMail.toString().trim();
        String password=inputPassword.toString().trim();

        if (TextUtils.isEmpty(email)) {//(precio.equals(""))
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            Toast.makeText(Singin.this, "Bienvenido: " + inputMail.getText(), Toast.LENGTH_LONG).show();
                            Textos();


                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(Singin.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Singin.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    public void SingUp (View view){

        Intent intent = new Intent(Singin.this, Singup.class);
        startActivity(intent);

    }
    public void Textos (){

        Intent intent = new Intent(Singin.this, Textos.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        signIn();
    }
}
