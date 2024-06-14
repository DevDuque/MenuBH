package com.example.menubh.components.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menubh.R;
import com.example.menubh.components.classes.RestaurantClass;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private ArrayList<RestaurantClass> restaurantList;

    public CardAdapter(ArrayList<RestaurantClass> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        RestaurantClass currentRestaurant = restaurantList.get(position);

        holder.textViewName.setText(currentRestaurant.getRestaurantName());
        holder.textViewAddress.setText(currentRestaurant.getRestaurantAddress());
        holder.textViewNumber.setText(currentRestaurant.getRestaurantNumber());

        // Set specialty image
        switch (currentRestaurant.getRestaurantSpecialty()) {
            case "Sushi":
                holder.specialtyView.setImageResource(R.drawable.img_sushi);
                break;
            case "Barbecue":
                holder.specialtyView.setImageResource(R.drawable.img_barbecue);
                break;
            case "Fish":
                holder.specialtyView.setImageResource(R.drawable.img_fish);
                break;
            case "Pizza":
                holder.specialtyView.setImageResource(R.drawable.img_pizza);
                break;
            default:
                holder.specialtyView.setImageResource(R.drawable.img_addbutton);
                break;
        }

        // Set star ratings
        int stars = currentRestaurant.getRestaurantRating();
        for (int i = 0; i < 5; i++) {
            if (i < stars) {
                holder.starViews[i].setImageResource(R.drawable.mini_activestar);
            } else {
                holder.starViews[i].setImageResource(R.drawable.mini_inactivestar);
            }
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewAddress;
        public TextView textViewNumber;
        public TextView textViewSpecialty;
        public ImageView specialtyView;
        public ImageView[] starViews;

        public CardViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textName);
            textViewAddress = itemView.findViewById(R.id.textAddress);
            textViewNumber = itemView.findViewById(R.id.textNumber);
            specialtyView = itemView.findViewById(R.id.specialty_view);
            starViews = new ImageView[5];
            starViews[0] = itemView.findViewById(R.id.star1);
            starViews[1] = itemView.findViewById(R.id.star2);
            starViews[2] = itemView.findViewById(R.id.star3);
            starViews[3] = itemView.findViewById(R.id.star4);
            starViews[4] = itemView.findViewById(R.id.star5);
        }
    }
}
