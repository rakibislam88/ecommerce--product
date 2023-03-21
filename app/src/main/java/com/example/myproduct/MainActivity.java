package com.example.myproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    public static String shopNumber="";
    public static ArrayList<HashMap<String, String>> cartArrLeng = new ArrayList<>();
    TextView manyProduct;
    ImageView home, addNav;
    public static LinearLayout containerLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        home = findViewById(R.id.home);
        addNav = findViewById(R.id.add_nav);
        manyProduct = findViewById(R.id.many_product);
        containerLayout = findViewById(R.id.containerLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, new HomeFragment()).commit();



        manyProduct.setText(shopNumber);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, new HomeFragment()).commit();
            }
        });

        addNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, new CartFragment()).commit();
            }
        });
    }


}