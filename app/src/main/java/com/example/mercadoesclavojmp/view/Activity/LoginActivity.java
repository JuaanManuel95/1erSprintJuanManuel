package com.example.mercadoesclavojmp.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.databinding.ActivityLoginBinding;
import com.example.mercadoesclavojmp.view.Fragments.LoginFragment;
import com.example.mercadoesclavojmp.view.Fragments.RegisterFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity implements LoginFragment.FragmentLogInListener, RegisterFragment.FragmentRegisterListener {

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private ActivityLoginBinding binding;
    private FirebaseFirestore db;



    public LoginActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        pegarFragment(loginFragment);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        if(currentUser != null){
            updateUI(currentUser);
        }
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            pegarFragment(new LoginFragment());
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.logInContenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClickSignUp() {
        pegarFragment(registerFragment);

    }

    @Override
    public void onClickForgotPassword() {
        Toast.makeText(this, "JAJA NV", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClickSignIn() {
        pegarFragment(loginFragment);

    }
}
