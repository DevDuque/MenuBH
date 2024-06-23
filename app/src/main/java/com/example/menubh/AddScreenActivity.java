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
import com.example.menubh.components.utils.InputSelectionHelper;

public class AddScreenActivity extends AppCompatActivity {

    private ImageButton[] stars = new ImageButton[5];
    private ImageButton[] buttons = new ImageButton[4];
    private String[] specialties;

    // Formulário de entrada
    private String selectedSpecialty = "";
    private int selectedStar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_screen);

        // Responsividadde
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        specialties = getResources().getStringArray(R.array.specialties);

        // Inicializa os arrays de estrelas e botões com base nos IDs do layout XML
        stars[0] = findViewById(R.id.star1);
        stars[1] = findViewById(R.id.star2);
        stars[2] = findViewById(R.id.star3);
        stars[3] = findViewById(R.id.star4);
        stars[4] = findViewById(R.id.star5);

        buttons[0] = findViewById(R.id.sushi_input);
        buttons[1] = findViewById(R.id.barbecue_input);
        buttons[2] = findViewById(R.id.fish_input);
        buttons[3] = findViewById(R.id.pizza_input);

        // Configura listeners para cliques nas estrelas para selecionar a avaliação
        for (ImageButton star : stars) {
            star.setOnClickListener(v -> {
                // Chama o helper para selecionar a estrela
                selectedStar = InputSelectionHelper.onStarClick((ImageButton) v, stars);
            });
        }

        // Configura listeners para cliques nos botões de especialidade para selecionar a especialidade
        for (ImageButton button : buttons) {
            button.setOnClickListener(v -> {
                // Chama o helper para selecionar a especialidade
                selectedSpecialty = InputSelectionHelper.onSpecialtyClick((ImageButton) v, buttons, specialties);
            });
        }

        // Configura o listener para o botão de confirmação
        Button confirmButton = findViewById(R.id.btn_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método para criar uma instância de restaurante
                createRestaurantInstance();
            }
        });

        // Configura o listener para o botão de voltar
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fecha a activity e retorna á tela anterior
                finish();
            }
        });

    }

    // Método para criar uma instância de restaurante com base nos dados do formulário
    private void createRestaurantInstance() {
        // Obtém as entradas dos campos de texto
        EditText nameInput = findViewById(R.id.nameInput);
        EditText addressInput = findViewById(R.id.addressInput);
        EditText numberInput = findViewById(R.id.numberInput);

        String name = nameInput.getText().toString();
        String address = addressInput.getText().toString();
        String number = numberInput.getText().toString();

        // Verifica se todos os campos foram preenchidos corretamente
        if (name.isEmpty() || address.isEmpty() || number.isEmpty() || selectedSpecialty.isEmpty() || selectedStar == 0) {
            Toast.makeText(AddScreenActivity.this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
        } else {
            // Cria uma nova instância de RestaurantClass com os dados fornecidos
            RestaurantClass newRestaurant = new RestaurantClass(name, address, number, selectedSpecialty, selectedStar);

            // Prepara o Intent para retornar os dados para a Activity anterior
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newRestaurant", newRestaurant);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
