package com.example.menubh.components.utils;

// StarRatingHelper.java

import android.widget.ImageButton;

import com.example.menubh.R;

public class StarRatingHelper {

    public static int onStarClick(ImageButton clickedStar, ImageButton[] stars) {
        int selectedStar = -1;

        // Determina qual estrela foi clicada
        for (int i = 0; i < stars.length; i++) {
            if (stars[i].equals(clickedStar)) {
                selectedStar = i + 1;
                setStarActive(clickedStar, stars, i);
                break;
            }
        }

        return selectedStar;
    }

    private static void setStarActive(ImageButton clickedStar, ImageButton[] stars, int position) {
        // Mudando para ativo baseado no clique
        for (int i = 0; i <= position; i++) {
            stars[i].setImageResource(R.drawable.img_activestar);
        }

        // Mudando para inativo baseado no clique
        for (int i = position + 1; i < stars.length; i++) {
            stars[i].setImageResource(R.drawable.img_inactivestar);
        }
    }
}
