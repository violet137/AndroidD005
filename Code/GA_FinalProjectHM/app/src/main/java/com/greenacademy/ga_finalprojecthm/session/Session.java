package com.greenacademy.ga_finalprojecthm.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.greenacademy.ga_finalprojecthm.model.ProductDetailsInWishlist;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by thepa on 12/4/2017.
 */

public class Session {
    public static ArrayList<ProductDetailsInWishlist> wishlistProducts = new ArrayList<>();
    public static String username = "Guest";
    public static boolean isLogedIn;

    private static SharedPreferences prefsLogin, prefsWishList;

    public static void setLoginSession(Context context) {
        prefsLogin = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
    }
    public static void setWishListSession(Context context, String username){
        prefsWishList = context.getSharedPreferences(username + "WishList", Context.MODE_PRIVATE);
    }

    public static void setUsername(String username) {
        prefsLogin.edit().putString("username", username).apply();
    }

    public static String getUsername() {
        return prefsLogin.getString("username", "Guest");
    }

    public static void setPassword(String password) {
        prefsLogin.edit().putString("password", password).apply();
    }

    public static String getPassword() {
        return prefsLogin.getString("password", "Guest");
    }

    public static void removeLoginState() {
        username = "Guest";
        prefsLogin.edit().remove("username").apply();
        prefsLogin.edit().remove("password").apply();
    }

    public static void setWishlistProducts(ArrayList<ProductDetailsInWishlist> wishlistProducts) {
        //For save
        Gson gson = new Gson();
        String json = gson.toJson(wishlistProducts);
        prefsWishList.edit().putString("WishlistProducts", json).apply();
    }

    public static ArrayList<ProductDetailsInWishlist> getWishlistProducts() {
        //For get
        Gson gson = new Gson();
        String json = prefsWishList.getString("WishlistProducts", "");
        Type wishlistProductsType = new TypeToken<ArrayList<ProductDetailsInWishlist>>() {
        }.getType();
        return gson.fromJson(json, wishlistProductsType);
    }
}
