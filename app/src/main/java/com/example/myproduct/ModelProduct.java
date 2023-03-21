package com.example.myproduct;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelProduct {

    public static ArrayList<ArrayList<HashMap<String, String>>> rootarray = new ArrayList();
    public static ArrayList<ArrayList<HashMap<String, String>>> subItemrootarray = new ArrayList();
    public static ArrayList<HashMap<String, String>> homeitemArr =new ArrayList<>();
    public static ArrayList<HashMap<String, String>> allCategoryItem = new ArrayList();
    public static ArrayList<HashMap<String, String>> topCat = new ArrayList();
    public static ArrayList<HashMap<String, String>> subCategoryArr = new ArrayList();
    public static HashMap<String, String> hashMap;

    public static void createCategoryItem(Integer productImg, String productName, String productPrice){
        //homeitemArr = new ArrayList<>();
        hashMap = new HashMap<>();
        hashMap.put("productimg", String.valueOf(productImg));
        hashMap.put("productname", productName);
        hashMap.put("productprice", productPrice);
        homeitemArr.add(hashMap);
        allCategoryItem.add(hashMap);
    }
    public static void createSubCat(Integer subCatImg, String subCatTitle){
        subItemrootarray.add(allCategoryItem);
        hashMap = new HashMap<>();
        hashMap.put("subcatimg", String.valueOf(subCatImg));
        hashMap.put("subcattitle", subCatTitle);
        allCategoryItem = new ArrayList<>();
        subCategoryArr.add(hashMap);
    }

    public static void createTopCat(String cat, Integer catImg){
        rootarray.add(subCategoryArr);
        hashMap = new HashMap<>();
        hashMap.put("cat", cat);
        hashMap.put("catimg", String.valueOf(catImg));
        topCat.add(hashMap);
        subCategoryArr = new ArrayList<>();
    }

    public static void createallProduct(){
         homeitemArr = new ArrayList<>();

        createTopCat("Newsfeed", R.drawable.comment);


        createTopCat("Gift Cards", R.drawable.gift);



        createTopCat("Campaigns", R.drawable.adwords);


        createCategoryItem(R.drawable.m8, "Basic Math Review",  "14");
        createCategoryItem(R.drawable.m9, "Adult Basic Math College Prep",  "34");
        createSubCat(R.drawable.math, "Math");
        createCategoryItem(R.drawable.a04, "Samsung Galaxy A04", "200");
        createCategoryItem(R.drawable.f13, "Samsung Galaxy F13", "250");
        createSubCat(R.drawable.samsung, "Samsung");
        createCategoryItem(R.drawable.n100, "OnePlus Nord N100 ", "300");
        createCategoryItem(R.drawable.oneplusnord, "OnePlus Nord CE 5G", "320");
        createSubCat(R.drawable.oneplus, "Oneplus");
        createCategoryItem(R.drawable.nokiag20, "Nokia G20", "150");
        createCategoryItem(R.drawable.nokia23, "Nokia 2.3", "250");
        createSubCat(R.drawable.nokia, "Nokia");
        createTopCat("Categories", R.drawable.category);






    }
}
