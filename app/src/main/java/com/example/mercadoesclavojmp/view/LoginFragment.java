package com.example.mercadoesclavojmp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.databinding.FragmentLoginBinding;
import com.example.mercadoesclavojmp.model.Producto;
import com.example.mercadoesclavojmp.model.UserNuevo;
import com.example.mercadoesclavojmp.util.ResultListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    private FragmentLogInListener fragmentLogInListener;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        firebaseUser = mAuth.getCurrentUser();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        binding.textViewForgotPassword.setOnClickListener(this);
        binding.signUpText.setOnClickListener(this);
        binding.btnLogIn.setOnClickListener(this);
        binding.signInButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentLogInListener = (FragmentLogInListener) context;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInButton:
                signIn();
                break;
            case R.id.textViewForgotPassword:
                fragmentLogInListener.onClickForgotPassword();
                break;
            case R.id.signUpText:
                fragmentLogInListener.onClickSignUp();
                break;
            case R.id.btnLogIn:
                confirmInput();
                break;
        }

    }

    private void confirmInput() {
        if (!validateEmail() | !validatePassword()) {
            return;
        }
        iniciarSesion(binding.editTextUsernameEmail.getText().toString(), binding.editTextPassword.getText().toString());

    }

    private boolean validateEmail() {
        String emailInput = binding.editTextUsernameEmail.getText().toString();
        if (!emailInput.contains("@") || emailInput.length() < 10) {
            binding.editTextUsernameEmail.setError("La dirección no es valida.");
            return false;
        } else {
            binding.editTextUsernameEmail.setError(null);
            return true;
        }
    }


    private boolean validatePassword() {
        String passwordInput = binding.editTextPassword.getText().toString();
        if (passwordInput.isEmpty()) {
            binding.editTextPassword.setError("Introducir Contraseña");
            return false;
        } else {
            binding.editTextPassword.setError(null);
            return true;
        }
    }


    public interface FragmentLogInListener {
        void onClickSignUp();

        void onClickForgotPassword();
    }

    private void iniciarSesion(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {

                            Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w("GOOGLE", "signInResult:failed code=" + e.getStatusCode());
                updateUI(null);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginFragment.this.getActivity());
                            UserNuevo usuario = new UserNuevo(
                                    acct.getGivenName(),
                                    acct.getFamilyName(),
                                    acct.getDisplayName(),
                                    acct.getEmail(),
                                    new ArrayList<Producto>());
                            db.collection("usersME").document(mAuth.getCurrentUser().getUid()).set(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                            LoginFragment.this.updateUI(user);
                        } else {

                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            LoginFragment.this.updateUI(null);
                        }
                    }
                });
    }
}
