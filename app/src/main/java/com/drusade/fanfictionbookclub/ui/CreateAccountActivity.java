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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String mName;
    float v = 0;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textViewTitle) TextView mTextViewTitle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.userNameEditText) EditText mUserNameEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.userEmailEditText) EditText mUserEmailEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.userPasswordEditText) EditText mUserPasswordEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signUpButton) Button mSignUpButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.loginTextView) TextView mLoginTextView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.googleButton)
    FloatingActionButton mGoogleButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.facebookButton) FloatingActionButton mFacebookButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.twitterButton) FloatingActionButton mTwitterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acccount);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mLoginTextView.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);

        createAuthStateListener();

        mTextViewTitle.setTranslationX(300);
        mUserNameEditText.setTranslationX(300);
        mUserEmailEditText.setTranslationX(300);
        mUserPasswordEditText.setTranslationX(300);
        mConfirmPasswordEditText.setTranslationX(300);
        mSignUpButton.setTranslationY(300);
        mLoginTextView.setTranslationY(300);
        mFacebookButton.setTranslationX(300);
        mTwitterButton.setTranslationX(300);
        mGoogleButton.setTranslationY(300);

        mTextViewTitle.setAlpha(v);
        mUserNameEditText.setAlpha(v);
        mUserEmailEditText.setAlpha(v);
        mUserPasswordEditText.setAlpha(v);
        mConfirmPasswordEditText.setAlpha(v);
        mSignUpButton.setAlpha(v);
        mLoginTextView.setAlpha(v);
        mFacebookButton.setAlpha(v);
        mTwitterButton.setAlpha(v);
        mGoogleButton.setAlpha(v);

        mTextViewTitle.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        mUserNameEditText.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        mUserEmailEditText.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        mUserPasswordEditText.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        mConfirmPasswordEditText.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1200).start();
        mSignUpButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        mLoginTextView.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1200).start();
        mFacebookButton.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        mTwitterButton.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        mGoogleButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1200).start();
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mUserEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mUserNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mUserPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mUserPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void createNewUser() {
        mName = mUserNameEditText.getText().toString().trim();
        final String email = mUserEmailEditText.getText().toString().trim();
        String password = mUserPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(mName);
        boolean validPassword = isValidPassword(password, confirmPassword);

        if (!validEmail || !validName || !validPassword) return;

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Log.d(TAG, "Authentication successful");
                    createFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createAuthStateListener(){
        mAuthListener = firebaseAuth -> {
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null){
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
    }

    private void createFirebaseUserProfile(final FirebaseUser user) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, Objects.requireNonNull(user.getDisplayName()));
                    Toast.makeText(CreateAccountActivity.this, "The display name has been set", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mLoginTextView) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (v == mSignUpButton) {
            createNewUser();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}