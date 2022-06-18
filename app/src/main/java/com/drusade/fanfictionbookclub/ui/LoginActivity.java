package com.drusade.fanfictionbookclub.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.drusade.fanfictionbookclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    float v = 0;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.userLoginEmailEditText) EditText mUserLoginEmailEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.userLoginPasswordEditText) EditText mUserLoginPasswordEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.logInButton) Button mLoginButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textViewTitle) TextView mTextViewTitle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.createAccountTextView) TextView mCreateAccountTextView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.googleButton) FloatingActionButton mGoogleButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.facebookButton) FloatingActionButton mFacebookButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.twitterButton) FloatingActionButton mTwitterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mCreateAccountTextView.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mTextViewTitle.setTranslationX(300);
        mUserLoginEmailEditText.setTranslationX(300);
        mUserLoginPasswordEditText.setTranslationX(300);
        mLoginButton.setTranslationY(300);
        mCreateAccountTextView.setTranslationY(300);
        mFacebookButton.setTranslationX(300);
        mTwitterButton.setTranslationX(300);
        mGoogleButton.setTranslationY(300);


        mTextViewTitle.setAlpha(v);
        mUserLoginEmailEditText.setAlpha(v);
        mUserLoginPasswordEditText.setAlpha(v);
        mLoginButton.setAlpha(v);
        mCreateAccountTextView.setAlpha(v);
        mFacebookButton.setAlpha(v);
        mTwitterButton.setAlpha(v);
        mGoogleButton.setAlpha(v);


        mTextViewTitle.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        mUserLoginEmailEditText.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        mUserLoginPasswordEditText.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        mLoginButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        mCreateAccountTextView.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        mFacebookButton.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        mTwitterButton.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        mGoogleButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccountTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == mLoginButton) {
            loginWithPassword();
        }
    }

    private void loginWithPassword() {
        String email = mUserLoginEmailEditText.getText().toString().trim();
        String password = mUserLoginPasswordEditText.getText().toString().trim();
        if (email.equals("")) {
            mUserLoginEmailEditText.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mUserLoginPasswordEditText.setError("Password cannot be blank");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}