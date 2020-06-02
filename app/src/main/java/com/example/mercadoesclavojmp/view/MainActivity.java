package com.example.mercadoesclavojmp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.controller.ProductosController;
import com.example.mercadoesclavojmp.model.ProductoContainer;
import com.example.mercadoesclavojmp.model.Productos;
import com.example.mercadoesclavojmp.util.ResultListener;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements RecyclerViewFragment.RecyclerViewFragmentListener {


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    //private ProductosController productosController;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ProductosController productosController = new ProductosController();
        productosController.searchByQuery("Notebooks", new ResultListener<ProductoContainer>() {
            @Override
            public void onFinish(ProductoContainer result) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("productos", result);
                RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
                recyclerViewFragment.setArguments(bundle);
                pegarFragment(recyclerViewFragment);


            }
        });





        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuMiCuenta:
                        Toast.makeText(MainActivity.this, "Próximamente", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuFavoritos:
                        Toast.makeText(MainActivity.this, "Próximamente", Toast.LENGTH_SHORT).show();
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
        });

        
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        pegarFragment(recyclerViewFragment);


    }


    private void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activityMainContenedorFragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onClickProductosDesdeFragment(Productos productos) {


        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailFragment.PRODUCTO, productos);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        pegarFragment(detailFragment);

    }

    @Override
    public void onBackPressed(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }








}
