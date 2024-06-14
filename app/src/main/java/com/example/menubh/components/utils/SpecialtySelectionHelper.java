package com.example.menubh.components.utils;

import android.widget.ImageButton;

import com.example.menubh.R;

public class SpecialtySelectionHelper {

    public static String onSpecialtyClick(ImageButton clickedButton, ImageButton[] buttons, String[] specialties) {
        String selectedSpecialty = "";

        // Determina qual foi clicado
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].equals(clickedButton)) {
                selectedSpecialty = specialties[i];
                setSpecialtyActive(buttons, i);
                break;
            }
        }

        return selectedSpecialty;
    }

    private static void setSpecialtyActive(ImageButton[] buttons, int position) {
        // Mantém todos como ativos
        for (int i = 0; i < buttons.length; i++) {
            if (i == position) {
                switch (i) {
                    case 0:
                        buttons[i].setImageResource(R.drawable.img_sushi_active);
                        break;
                    case 1:
                        buttons[i].setImageResource(R.drawable.img_barbecue_active);
                        break;
                    case 2:
                        buttons[i].setImageResource(R.drawable.img_fish_active);
                        break;
                    case 3:
                        buttons[i].setImageResource(R.drawable.img_pizza_active);
                        break;
                }
            } else {
                switch (i) {
                    case 0:
                        buttons[i].setImageResource(R.drawable.img_sushi);
                        break;
                    case 1:
                        buttons[i].setImageResource(R.drawable.img_barbecue);
                        break;
                    case 2:
                        buttons[i].setImageResource(R.drawable.img_fish);
                        break;
                    case 3:
                        buttons[i].setImageResource(R.drawable.img_pizza);
                        break;
                }
            }
        }
    }
}
