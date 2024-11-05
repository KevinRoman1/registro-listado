package com.example.java3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.java3.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private EditText txtcliente;

    private EditText txtlistado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        this.txtlistado=findViewById(R.id.txtlistado);
        this.txtcliente=findViewById(R.id.txtcliente);

        Button buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar(v); // Llama a la función Guardar
            }
        });

        Button buttonVer = findViewById(R.id.buttonVer);
        buttonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leer(v); // Llama a la función Guardar
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void Guardar(View vista){


        String texto = txtcliente.getText().toString()+"\n";
        FileOutputStream file=null;
        try{
            file=openFileOutput("texto.txt",MODE_APPEND);
            file.write(texto.getBytes());
            Log.d("tag1",String.valueOf(getFilesDir()));

    }catch(Exception e)
    {
        e.printStackTrace();
    }finally{
            if(file!=null)
            {
                try {
                    file.close();
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
    }
    }

public void Leer(View vista){
            FileInputStream file=null;
            try {
                file=openFileInput("texto.txt");
                InputStreamReader LeerFile=new InputStreamReader(file);
                BufferedReader buferLeer= new BufferedReader(LeerFile);
                String linea;
                StringBuilder parrafo = new StringBuilder();
                while ((linea=buferLeer.readLine())!=null)
                {
                    parrafo.append(linea).append("\n");
                }
                txtlistado.setText(parrafo);
            }catch (Exception e)
            {

            }finally {
                if(file!=null)
                {
                    try {
                        file.close();
                    }catch (Exception e){

                    }
                }
            }
}






}



