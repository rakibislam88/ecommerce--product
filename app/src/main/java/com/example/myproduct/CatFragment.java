package com.example.myproduct;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class CatFragment extends Fragment {

    public static String topstrcatname="";
    public static ArrayList<HashMap<String, String>> catArr = new ArrayList<>();
    RecyclerView catViewCycle;
    MyCatAdapter catAdapter;
    TextView topCatName;
    ImageView catbackbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_cat, container, false);

        catbackbtn = v.findViewById(R.id.catbackbtn);
        catViewCycle = v.findViewById(R.id.cat_view_cycle);
        topCatName = v.findViewById(R.id.top_cat_name);

        catAdapter = new MyCatAdapter();
        catViewCycle.setLayoutManager(new GridLayoutManager(getContext(), 2));
        catViewCycle.setAdapter(catAdapter);



        topCatName.setText(topstrcatname);
        catbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeFragment home = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, new HomeFragment(), null).addToBackStack(null).commit();
            }
        });
        return  v;
    }

    public class MyCatAdapter extends RecyclerView.Adapter<MyCatAdapter.MyCat>{


        @NonNull
        @Override
        public MyCat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View v = layoutInflater.inflate(R.layout.layoutcatall, parent, false);
            return new MyCat(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyCat holder, @SuppressLint("RecyclerView") int position) {

            HashMap<String, String> map3 = catArr.get(position);
            holder.catImg.setImageResource(Integer.parseInt(map3.get("subcatimg")));
            holder.catT.setText(map3.get("subcattitle"));
            holder.catLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment itemfragment = new CatItemFragment();
                    CatItemFragment.catstrname= map3.get("subcattitle");
                    CatItemFragment.catsubitemArr = ModelProduct.subItemrootarray.get(position);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, itemfragment).commit();
                }
            });

        }

        @Override
        public int getItemCount() {
            return catArr.size();
        }

        public class MyCat extends RecyclerView.ViewHolder{
            TextView catT;
            ImageView catImg;
            LinearLayout catLay;
            public MyCat(@NonNull View itemView) {
                super(itemView);
                catImg = itemView.findViewById(R.id.cat_img);
                catT = itemView.findViewById(R.id.cat_t);
                catLay = itemView.findViewById(R.id.cat_Lay);
            }
        }
    }

}