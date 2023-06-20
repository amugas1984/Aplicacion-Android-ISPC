package com.example.nav.ui.home;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nav.databinding.FragmentHomeBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class HomeFragment extends Fragment {

    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 1;
    private FragmentHomeBinding binding;
    private GoogleSignInClient googleSignInClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Configurar el botón de inicio de sesión de Google
        SignInButton btnGoogleSignIn = binding.btnGoogleSignIn;
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });

        // Configurar el botón de cierre de sesión
        Button btnLogout = binding.btnLogout;
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        // Configurar las opciones de inicio de sesión de Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Obtener el cliente de inicio de sesión de Google
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE);
    }

    private void signOut() {
        googleSignInClient.signOut()
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Aquí puedes realizar acciones después de cerrar sesión, como navegar a la pantalla de inicio de sesión
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            // Resultado de la autenticación de Google
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Obtener la cuenta de Google autenticada
                GoogleSignInAccount account = task.getResult(ApiException.class);

                // Aquí puedes realizar acciones con la cuenta autenticada, como guardar los datos en tu aplicación

                // Por ejemplo, puedes obtener el nombre y el correo electrónico de la cuenta
                String displayName = account.getDisplayName();
                String email = account.getEmail();

                // También puedes obtener el ID de usuario único de Google
                String googleId = account.getId();

                // Puedes mostrar un mensaje de bienvenida al usuario o realizar otras acciones según tus necesidades

            } catch (ApiException e) {
                // Manejar cualquier error que ocurra durante la autenticación
                // ...
            }
        }
    }
}

