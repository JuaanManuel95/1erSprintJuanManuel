package com.example.mercadoesclavojmp.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.controller.ProductosController;
import com.example.mercadoesclavojmp.model.Producto;
import com.example.mercadoesclavojmp.model.ProductoContainer;
import com.example.mercadoesclavojmp.util.ResultListener;
import com.example.mercadoesclavojmp.view.Fragments.DetailFragment;
import com.example.mercadoesclavojmp.view.Fragments.FavoritesFragment;
import com.example.mercadoesclavojmp.view.Fragments.RecyclerViewFragment;
import com.example.mercadoesclavojmp.view.Fragments.SobreNosotrosFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements RecyclerViewFragment.RecyclerViewFragmentListener, NavigationView.OnNavigationItemSelectedListener, FavoritesFragment.FragmentFavoritosListener {


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ProductosController productosController;
    private RecyclerViewFragment recyclerViewFragment;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private FavoritesFragment favoritesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        favoritesFragment = new FavoritesFragment();
        recyclerViewFragment = new RecyclerViewFragment();
        productosController = new ProductosController();
        productosController.searchByQuery("Notebooks", new ResultListener<ProductoContainer>() {
            @Override
            public void onFinish(ProductoContainer result) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(RecyclerViewFragment.PRODUCTOS, result);
                recyclerViewFragment.setArguments(bundle);
                pegarFragment(recyclerViewFragment);
            }
        });


        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbarActivityMain);

        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawerLayout,
                        toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public void onClickProductosDesdeFragment(Producto producto) {


        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailFragment.PRODUCTO, producto);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        pegarFragment(detailFragment);

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {

            case R.id.itemLogOut:
                signOut();
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuMiCuenta:
                Toast.makeText(MainActivity.this, "Próximamente", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuFavoritos:
                pegarFragment(favoritesFragment);
                break;
            case R.id.menuAjustes:
                Toast.makeText(MainActivity.this, "Próximamente", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSobreNosotros:
                SobreNosotrosFragment sobreNosotrosFragment = new SobreNosotrosFragment();
                pegarFragment(sobreNosotrosFragment);
                break;
        }
        drawerLayout.closeDrawers();
        return true;
    }

    /**
     * pegarFragment
     *
     * @param fragment
     */
    private void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainContenedorFragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void signOut() {

        mAuth.signOut();
        goToLogIn();
    }


    private void goToLogIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(Producto producto) {
        pegarFragment(favoritesFragment);
    }
}