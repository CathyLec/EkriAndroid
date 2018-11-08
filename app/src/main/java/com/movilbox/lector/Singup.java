package com.movilbox.lector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Singup extends AppCompatActivity implements View.OnClickListener {
    private TextView txtLogin;
    private EditText txtName, txtEmail, txtPassword, txtPasswordConfirm;
    private Button btnSignup;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        mAuth = FirebaseAuth.getInstance();

        txtLogin=(TextView) findViewById(R.id.link_login);
        txtName=(EditText) findViewById(R.id.txtName);
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        txtPasswordConfirm=(EditText) findViewById(R.id.txtPasswordConfirm);
        btnSignup=(Button) findViewById(R.id.btnSignup);

        progressDialog = new ProgressDialog(this);

        btnSignup.setOnClickListener(this);
    }

    private void registrar() {
        String name= txtName.getText().toString().trim();
        String email= txtEmail.getText().toString().trim();
        String password=txtPassword.getText().toString().trim();
        String confirmation= txtPasswordConfirm.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Se debe ingresar un nombre",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(confirmation)){
            Toast.makeText(this,"Se debe verificar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        while(!password.equals(confirmation)){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(Singup.this,"Se ha registrado el usuario con el email: "+ txtEmail.getText(),Toast.LENGTH_LONG).show();
                            CreateTextos();
                        }else{

                            Toast.makeText(Singup.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    public void Login (View view){

        Intent intent = new Intent(Singup.this, Singin.class);
        startActivity(intent);

    }
    public void CreateTextos (){

        Intent intent = new Intent(Singup.this, Textos.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        registrar();
    }
}
