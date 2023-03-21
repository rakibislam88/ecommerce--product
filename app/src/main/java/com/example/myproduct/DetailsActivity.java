package com.example.myproduct;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {


    public ArrayList<HashMap<String, String>> detailArr = new ArrayList<>();
    HashMap<String, String> hashMap;
    RecyclerView detailView;
    MyDetailAdapter detailAdapter;
    public static String NAME ="";
    public static String PRICE = "";
    public static Bitmap imgBitmap = null;
    ImageView imageView, backBtn;
    int count=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();


        imageView = findViewById(R.id.productimage);
        detailView = findViewById(R.id.detail_recycle);
        backBtn = findViewById(R.id.backbtn);
        hashMap = new HashMap<>();
        hashMap.put("image", imgBitmap.toString());
        hashMap.put("name", NAME);
        hashMap.put("price", PRICE);
        detailArr.add(hashMap);


        detailAdapter = new MyDetailAdapter();
        detailView.setLayoutManager(new GridLayoutManager(this, 1));
        detailView.setAdapter(detailAdapter);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public class MyDetailAdapter extends RecyclerView.Adapter<MyDetailAdapter.detailHolder>{

        @NonNull
        @Override
        public detailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View v = layoutInflater.inflate(R.layout.detailslayout, parent, false);
            return new detailHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull detailHolder holder, int position) {

            HashMap<String, String> map = detailArr.get(position);
            //holder.productImg.setImageResource(get);
            //Bitmap bitmap = ((BitmapDrawable)holder.productImg.getDrawable()).getBitmap();
            //if (imgBitmap!=null) holder.productImg.setImageBitmap(imgBitmap);
            holder.productImg.setImageBitmap(imgBitmap);
            holder.productN.setText(map.get("name"));


            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap<String, String> hashMap = new HashMap<>();
                    HashMap<String, Bitmap> mbit = new HashMap<String, Bitmap>();
                    hashMap = new HashMap<>();
                    mbit.put("imgstring", imgBitmap);
                    hashMap.put("image", imgBitmap.toString());
                    hashMap.put("name", map.get("name"));
                    hashMap.put("price", map.get("price"));
                    CartFragment.cartArr.add(hashMap);
                    count++;
                    holder.cartNumber.setText(""+count);
                    MainActivity.shopNumber = String.valueOf(count);
                }
            });

        }

        @Override
        public int getItemCount() {
            return detailArr.size();
        }

        public class detailHolder extends RecyclerView.ViewHolder{
            ImageView productImg;
            TextView productN;
            TextView addToCart, cartNumber;
            public detailHolder(@NonNull View itemView) {
                super(itemView);
                productImg = itemView.findViewById(R.id.productimage);
                productN = itemView.findViewById(R.id.productname);
                addToCart = itemView.findViewById(R.id.add_to_cart);
                cartNumber = itemView.findViewById(R.id.cart_number);

            }
        }
    }

}