package com.example.menubh.components.utils;

import android.widget.ImageButton;

import com.example.menubh.R;

public class InputSelectionHelper {

    // Método para lidar com o clique em estrelas de avaliação
    public static int onStarClick(ImageButton clickedStar, ImageButton[] stars) {
        int selectedStar = -1;

        // Determina qual estrela foi clicada
        for (int i = 0; i < stars.length; i++) {
            if (stars[i].equals(clickedStar)) {
                // A estrela selecionada é o índice + 1 (1 a 5)
                selectedStar = i + 1;

                // Define as estrelas como ativas ou inativas com base no índice
                setStarActive(clickedStar, stars, i);
                break;
            }
        }

        return selectedStar;
    }

    // Método privado para configurar as estrelas como ativas ou inativas
    private static void setStarActive(ImageButton clickedStar, ImageButton[] stars, int position) {
        // Define as estrelas como ativas ou inativas com base no índice do clique
        for (int i = 0; i <= position; i++) {
            stars[i].setImageResource(R.drawable.img_activestar);
        }

        for (int i = position + 1; i < stars.length; i++) {
            stars[i].setImageResource(R.drawable.img_inactivestar);
        }
    }

    // Método para lidar com o clique em botões de especialidade
    public static String onSpecialtyClick(ImageButton clickedButton, ImageButton[] buttons, String[] specialties) {
        String selectedSpecialty = "";

        // Determina qual botão foi clicado
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].equals(clickedButton)) {
                // A especialidade selecionada é baseada no índice
                selectedSpecialty = specialties[i];

                // Define os botões de especialidade como ativos ou inativos com base no índice
                setSpecialtyActive(buttons, i);
                break;
            }
        }

        return selectedSpecialty;
    }

    // Método privado para configurar os botões de especialidade como ativos ou inativos
    private static void setSpecialtyActive(ImageButton[] buttons, int position) {
        // Define todos os botões de especialidade como inativos, exceto o selecionado que recebe uma imagem diferente
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
