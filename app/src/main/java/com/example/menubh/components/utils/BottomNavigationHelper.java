package com.example.menubh.components.utils;

import android.content.Context;
import android.widget.TextView;

import com.example.menubh.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelper {

    private final Context context;
    private final BottomNavigationView bottomNavigationView;
    private final TextView titleText;
    private final NavigationCallback callback;

    public interface NavigationCallback {
        void filterListBySpecialty(String specialty);
        void filterFavorites();
    }

    public BottomNavigationHelper(Context context, BottomNavigationView bottomNavigationView, TextView titleText, NavigationCallback callback) {
        this.context = context;
        this.bottomNavigationView = bottomNavigationView;
        this.titleText = titleText;
        this.callback = callback;
    }

    public void setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            String currentTitle = titleText.getText().toString();

            if (itemId == R.id.navigation_sushi) {
                if (!currentTitle.equals(context.getString(R.string.str_sushi))) {
                    callback.filterListBySpecialty("Sushi");
                    titleText.setText(R.string.str_sushi);
                } else {
                    callback.filterListBySpecialty(null);
                    titleText.setText(R.string.str_home);
                }
            } else if (itemId == R.id.navigation_barbecue) {
                if (!currentTitle.equals(context.getString(R.string.str_barbecue))) {
                    callback.filterListBySpecialty("Barbecue");
                    titleText.setText(R.string.str_barbecue);
                } else {
                    callback.filterListBySpecialty(null);
                    titleText.setText(R.string.str_home);
                }
            } else if (itemId == R.id.navigation_fish) {
                if (!currentTitle.equals(context.getString(R.string.str_fish))) {
                    callback.filterListBySpecialty("Fish");
                    titleText.setText(R.string.str_fish);
                } else {
                    callback.filterListBySpecialty(null);
                    titleText.setText(R.string.str_home);
                }
            } else if (itemId == R.id.navigation_pizza) {
                if (!currentTitle.equals(context.getString(R.string.str_pizza))) {
                    callback.filterListBySpecialty("Pizza");
                    titleText.setText(R.string.str_pizza);
                } else {
                    callback.filterListBySpecialty(null);
                    titleText.setText(R.string.str_home);
                }
            } else if (itemId == R.id.navigation_favorite) {
                if (!currentTitle.equals(context.getString(R.string.str_favorite))) {
                    callback.filterFavorites();
                    titleText.setText(R.string.str_favorite);
                } else {
                    callback.filterListBySpecialty(null);
                    titleText.setText(R.string.str_home);
                }
            }

            return true;
        });
    }
}