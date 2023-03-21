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


public class CatItemFragment extends Fragment {


    public static String catstrname="";
    public static ArrayList<HashMap<String, String>> catsubitemArr= new ArrayList<>();
    RecyclerView catItemCycleView;
    MyItemAdapter itemAdapter;
    ImageView catbackbtn;
    TextView catName;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cat_item, container, false);
        catItemCycleView = v.findViewById(R.id.catitemCycleView);
        catbackbtn = v.findViewById(R.id.catbackbtn);
        catName = v.findViewById(R.id.cat_name);        itemAdapter = new MyItemAdapter();
        catItemCycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        catItemCycleView.setAdapter(itemAdapter);


        catName.setText(catstrname);
        catbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, new CatFragment(), null).addToBackStack(null).commit();
            }
        });


        return  v;
    }

    public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyItemView>{

        @NonNull
        @Override
        public MyItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View vv = layoutInflater.inflate(R.layout.layoutitem, parent, false);
            return new MyItemView(vv);
        }

        @Override
        public void onBindViewHolder(@NonNull MyItemView holder, int position) {

            HashMap<String, String> map4 = catsubitemArr.get(position);
            holder.itemImg.setImageResource(Integer.parseInt(map4.get("productimg")));
            holder.itemname.setText(map4.get("productname"));
            holder.itemprice.setText(map4.get("productprice"));
        }

        @Override
        public int getItemCount() {
            return catsubitemArr.size();
        }

        public class MyItemView extends RecyclerView.ViewHolder{
            ImageView itemImg;
            TextView itemprice, itemname;
            public MyItemView(@NonNull View itemView) {
                super(itemView);
                itemImg = itemView.findViewById(R.id.item_image);
                itemname = itemView.findViewById(R.id.item_title);
                itemprice = itemView.findViewById(R.id.item_price);
            }
        }
    }

}