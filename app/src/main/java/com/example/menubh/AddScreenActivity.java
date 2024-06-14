package com.example.menubh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.menubh.components.classes.RestaurantClass;
import com.example.menubh.components.utils.SpecialtySelectionHelper;
import com.example.menubh.components.utils.StarRatingHelper;

public class AddScreenActivity extends AppCompatActivity {

    private ImageButton[] stars = new ImageButton[5];
    private ImageButton[] buttons = new ImageButton[4];
    private String[] specialties;

    // Formulário
    private EditText nameInput, addressInput, numberInput;
    private String selectedSpecialty = "";
    private int selectedStar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        specialties = getResources().getStringArray(R.array.specialties);

        // Popula o array de botões com as estrelas do [Layout XML]
        stars[0] = findViewById(R.id.star1);
        stars[1] = findViewById(R.id.star2);
        stars[2] = findViewById(R.id.star3);
        stars[3] = findViewById(R.id.star4);
        stars[4] = findViewById(R.id.star5);

        // Popula o array de botões com os botões do [Layout XML]
        buttons[0] = findViewById(R.id.sushi_input);
        buttons[1] = findViewById(R.id.barbecue_input);
        buttons[2] = findViewById(R.id.fish_input);
        buttons[3] = findViewById(R.id.pizza_input);

        for (ImageButton star : stars) {
            star.setOnClickListener(v -> {
                selectedStar = StarRatingHelper.onStarClick((ImageButton) v, stars);
            });
        }

        for (ImageButton button : buttons) {
            button.setOnClickListener(v -> {
                selectedSpecialty = SpecialtySelectionHelper.onSpecialtyClick((ImageButton) v, buttons, specialties);
            });
        }

        Button confirmButton = findViewById(R.id.btn_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRestaurantInstance();
            }
        });
    }

    private void createRestaurantInstance() {
        EditText nameInput = findViewById(R.id.nameInput);
        EditText addressInput = findViewById(R.id.addressInput);
        EditText numberInput = findViewById(R.id.numberInput);

        String name = nameInput.getText().toString();
        String address = addressInput.getText().toString();
        String number = numberInput.getText().toString();

        if (name.isEmpty() || address.isEmpty() || number.isEmpty() || selectedSpecialty.isEmpty() || selectedStar == 0) {
            Toast.makeText(AddScreenActivity.this, "Entre todos os campos corretamente", Toast.LENGTH_SHORT).show();
        } else {
            RestaurantClass newRestaurant = new RestaurantClass(name, address, number, selectedSpecialty, selectedStar);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("newRestaurant", newRestaurant);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}