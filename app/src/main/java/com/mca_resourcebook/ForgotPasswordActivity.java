package com.mca_resourcebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {


    private static final String TAG = "ForgotPasswordActivity";
    public FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    Button resetPasswordButton;
    EditText emailTextInput;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        emailTextInput = findViewById(R.id.fpEmailTextInput);
        resetPasswordButton = findViewById(R.id.ChangePasswordButton);
        back = findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();


        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAuth.sendPasswordResetEmail(emailTextInput.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) Log.d(TAG, "Email sent.");


                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        ForgotPasswordActivity.this);

                                // set title
                                alertDialogBuilder.setTitle("Reset Password");

                                // set dialog message
                                alertDialogBuilder
                                        .setMessage("A Reset Password Link Is Sent To Your Registered EmailID")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                ForgotPasswordActivity.this.finish();
                                            }
                                        });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();


                            }
                        });


            }
        });
        currentUser = mAuth.getCurrentUser();
        if (mAuth.getCurrentUser() != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent signUpIntent = new Intent(ForgotPasswordActivity.this, profile.class);
                    startActivity(signUpIntent);

                }
            });
        }else {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent signUpIntent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                    startActivity(signUpIntent);
                }
            });

        }
    }
}
