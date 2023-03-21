package com.example.myproduct;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends Fragment {


    SearchView searchView;
    ImageView navDraw;
    public static LinearLayout containerLayout;
    RecyclerView topCat, ongoingView, homeProductView;
    MyHPAdapter hpAdapter;
    private String ongoingArr[];
    private String ongoingDes[];
    MyOnAdapter onAdapter;
    HashMap<String, String> hashMap;
    MyAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        topCat = v.findViewById(R.id.top_list_cat);
        ongoingView = v.findViewById(R.id.ongoing_view);
        homeProductView = v.findViewById(R.id.home_product_view);
        searchView = v.findViewById(R.id.search);
        navDraw = v.findViewById(R.id.align);



        ongoingArr = getResources().getStringArray(R.array.ongoing_arr);
        ongoingDes = getResources().getStringArray(R.array.ongoing_des);

        hpAdapter = new MyHPAdapter();
        homeProductView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        homeProductView.setAdapter(hpAdapter);

        onAdapter = new MyOnAdapter();
        ongoingView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ongoingView.setAdapter(onAdapter);


        ModelProduct.createallProduct();
        adapter = new MyAdapter();
        topCat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        topCat.setAdapter(adapter);



        return  v;
    }

    public class MyHPAdapter extends RecyclerView.Adapter<MyHPAdapter.MyProductView>{


        @NonNull
        @Override
        public MyProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View vv = layoutInflater.inflate(R.layout.layouthomeproduct, parent, false);
            return new MyProductView(vv);
        }

        @Override
        public void onBindViewHolder(@NonNull MyProductView holder, int position) {

            HashMap<String, String> map2 = ModelProduct.homeitemArr.get(position);
            holder.imageView.setImageResource(Integer.parseInt(map2.get("productimg")));
            holder.title.setText(map2.get("productname"));
            holder.price.setText("$"+map2.get("productprice"));
            holder.homeProductLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(getContext(), DetailsActivity.class);
                    Bitmap bitmap = ((BitmapDrawable) holder.imageView.getDrawable()).getBitmap();
                    DetailsActivity.imgBitmap = bitmap;
                    DetailsActivity.NAME = map2.get("productname");
                    DetailsActivity.PRICE = map2.get("productprice");
                    startActivity(detailIntent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return ModelProduct.homeitemArr.size();
        }

        public class MyProductView extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView title, price;
            LinearLayout homeProductLay;
            public MyProductView(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image);
                title = itemView.findViewById(R.id.title);
                price = itemView.findViewById(R.id.price);
                homeProductLay = itemView.findViewById(R.id.home_product_lay);
            }
        }
    }




    public class MyOnAdapter extends RecyclerView.Adapter<MyOnAdapter.MyView>{

        @NonNull
        @Override
        public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.layoutongoing, parent, false);
            return new MyView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyView holder, int position) {
            holder.t1.setText(ongoingArr[position]);
            holder.t2.setText(ongoingDes[position]);
        }

        @Override
        public int getItemCount() {
            return ongoingArr.length;
        }

        public class MyView extends RecyclerView.ViewHolder{
            TextView t1, t2;
            public MyView(@NonNull View itemView) {
                super(itemView);
                t1 = itemView.findViewById(R.id.t1);
                t2 = itemView.findViewById(R.id.t2);
            }
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View v = layoutInflater.inflate(R.layout.top_cat_layout, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            HashMap<String, String> map = ModelProduct.topCat.get(position);
            holder.imageTop.setImageResource(Integer.parseInt(map.get("catimg")));
            holder.topT.setText(map.get("cat"));

            holder.catgoreyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment mFragment = new CatFragment();
                    CatFragment.topstrcatname = map.get("cat");
                    CatFragment.catArr = ModelProduct.rootarray.get(position);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, mFragment).commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return ModelProduct.topCat.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView topT;
            ImageView imageTop;
            LinearLayout catgoreyLayout;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                topT = itemView.findViewById(R.id.topT);
                imageTop = itemView.findViewById(R.id.top_img);
                catgoreyLayout = itemView.findViewById(R.id.category_Layout);
                containerLayout = itemView.findViewById(R.id.containerLayout);
            }
        }
    }



}