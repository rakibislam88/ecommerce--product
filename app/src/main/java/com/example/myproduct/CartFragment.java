package com.example.myproduct;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class CartFragment extends Fragment {

    public static ArrayList<HashMap<String, String>> cartArr = new ArrayList<>();
    HashMap<String, String> hashMap;
    RecyclerView cartView;
    MyCartAdapter adapter;
    public int p=0, mValue, ip=0, t;
    public static String imgBitmap = null;
    EditText total;
    ImageView delete, cartBackBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_cart, container, false);
        cartView = v.findViewById(R.id.cartview);
        cartBackBtn = v.findViewById(R.id.cartbackbtn);
        delete = v.findViewById(R.id.delete);
        total = v.findViewById(R.id.total);

        delete.setVisibility(View.INVISIBLE);


        adapter = new MyCartAdapter();
        cartView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cartView.setAdapter(adapter);

        cartBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, new HomeFragment(), null).addToBackStack(null).commit();
            }
        });

        return v;
    }

    public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.cartHolder>{

        @NonNull
        @Override
        public cartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View v = layoutInflater.inflate(R.layout.cartlayout, parent, false);
            return new cartHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull cartHolder holder, @SuppressLint("RecyclerView") int position) {
            MainActivity.cartArrLeng = cartArr;
            HashMap<String, String> map = cartArr.get(position);
            Bitmap myBitmap = BitmapFactory.decodeFile(map.get("image"));
            holder.itemImg.setImageBitmap(myBitmap);
            holder.name.setText(map.get("name"));
            holder.price.setText("$"+map.get("price"));

            holder.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean ischecked = ((CheckBox)v).isChecked();
                    checkedVisbleView(ischecked);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                }
            });

            holder.itemInrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p = Integer.parseInt(map.get("price"));
                    mValue = Integer.parseInt(holder.itemNumber.getText().toString());
                    mValue++;
                    holder.itemNumber.setText(""+mValue);
                    ip=p*mValue;
                    holder.price.setText("$"+ip);
                    cartupdate();
                }
            });
            holder.itemDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mValue>0) {
                        mValue--;
                        ip-=p;
                    }

                    holder.itemNumber.setText(""+mValue);
                    holder.price.setText("$"+ip);

                    if (t>0 && t>-0) t-=p;
                    total.setText(""+t);
                }
            });

        }

        @Override
        public int getItemCount() {
            return cartArr.size();
        }

        public class cartHolder extends RecyclerView.ViewHolder{
            ImageView itemImg, itemDecrease, itemInrease;
            TextView name, price;
            EditText itemNumber;
            CheckBox radioButton;
            public cartHolder(@NonNull View itemView) {
                super(itemView);

                itemImg = itemView.findViewById(R.id.item_image);
                itemDecrease = itemView.findViewById(R.id.item_decrease);
                itemInrease = itemView.findViewById(R.id.item_increase);
                itemNumber = itemView.findViewById(R.id.item_number);
                name = itemView.findViewById(R.id.name);
                price = itemView.findViewById(R.id.price);
                radioButton = itemView.findViewById(R.id.radio);
            }
        }
    }


    private void cartupdate() {
        t= Integer.parseInt(total.getText().toString());
        t = t+p;
        total.setText(""+t);
    }

    public void checkedVisbleView(boolean isChecked){
        if (isChecked){
            delete.setVisibility(View.VISIBLE);
        }else {
            delete.setVisibility(View.INVISIBLE);
        }
    }
}