package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.ga_finalprojecthm.MainActivity;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.fragment.WishListFragment;
import com.greenacademy.ga_finalprojecthm.model.ProductDetailsInWishlist;
import com.greenacademy.ga_finalprojecthm.session.Session;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by thepa on 12/1/2017.
 */

public class WishListProductAdapter extends RecyclerView.Adapter<WishListProductAdapter.ViewHolder> {

    private ArrayList<ProductDetailsInWishlist> productDetailsInWishlistArrayList;

    public WishListProductAdapter(ArrayList<ProductDetailsInWishlist> productDetailsInWishlistArrayList) {
        this.productDetailsInWishlistArrayList = productDetailsInWishlistArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_product_wishlist, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvProductName.setText(productDetailsInWishlistArrayList.get(position).getProductName());
        holder.tvProductSize.setText(productDetailsInWishlistArrayList.get(position).getProductSize());
        holder.tvProductColor.setText(productDetailsInWishlistArrayList.get(position).getProductColor());
        holder.tvProductPrice.setText(String.valueOf(productDetailsInWishlistArrayList.get(position).getProductPrice()));
        holder.tvProductPriceSale.setText(String.valueOf(productDetailsInWishlistArrayList.get(position).getProductPriceSale()));
        holder.tvProductQuantity.setText(String.valueOf(productDetailsInWishlistArrayList.get(position).getProductQuantity()));
        final Context context = holder.imgvProductWishList.getContext();
        Picasso
            .with(context)
            .load(productDetailsInWishlistArrayList.get(position).getLinkImage())
            .into(holder.imgvProductWishList);

        holder.llProductWishList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Session.wishlistProducts.remove(position);
                Toast.makeText(context, "Product removed!", Toast.LENGTH_SHORT).show();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new WishListFragment())
                        .commit();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetailsInWishlistArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llProductWishList;
        ImageView imgvProductWishList;
        TextView tvProductName, tvProductSize, tvProductColor, tvProductPrice, tvProductPriceSale, tvProductQuantity;

        ViewHolder(View itemView) {
            super(itemView);

            llProductWishList = itemView.findViewById(R.id.llProductWishList);
            imgvProductWishList = itemView.findViewById(R.id.imgvProductWishList);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductSize = itemView.findViewById(R.id.tvProductSize);
            tvProductColor = itemView.findViewById(R.id.tvProductColor);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductPriceSale = itemView.findViewById(R.id.tvProductPriceSale);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
        }
    }
}
