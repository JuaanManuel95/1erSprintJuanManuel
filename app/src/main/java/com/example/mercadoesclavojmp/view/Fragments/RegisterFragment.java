package com.example.mercadoesclavojmp.view.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.databinding.FragmentRegisterBinding;
import com.example.mercadoesclavojmp.model.Producto;
import com.example.mercadoesclavojmp.model.UserNuevo;
import com.example.mercadoesclavojmp.view.Activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FragmentRegisterBinding binding;
    private FragmentRegisterListener fragmentRegisterListener;
    private FirebaseFirestore db;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        binding.btnRegister.setOnClickListener(this);
        binding.signInText.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentRegisterListener = (FragmentRegisterListener) context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInText:
                fragmentRegisterListener.onClickSignIn();
                break;
            case R.id.btnRegister:
                crearUsuario(binding.editTextRegisterEMail.getText().toString(), binding.editTextRegisterPassword1.getText().toString());
                break;
        }


    }


    private void crearUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserNuevo userNuevo = new UserNuevo(
                                    binding.editTextUsername.getText().toString(),
                                    binding.editTextRegisterFirstName.getText().toString(),
                                    binding.editTextRegisterLastName.getText().toString(),
                                    binding.editTextRegisterEMail.getText().toString(),
                                    new ArrayList<Producto>());
                            db.collection("usersME").document(mAuth.getCurrentUser().getUid()).set(userNuevo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getContext(), "Me alegro que estes de vuelta!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "Ha ocurrido un error, lo siento.", Toast.LENGTH_SHORT).show();
                                }
                            });
                            updateUI(user);
                        } else {
                            Toast.makeText(RegisterFragment.this.getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    public interface FragmentRegisterListener {
        void onClickSignIn();
    }
}
