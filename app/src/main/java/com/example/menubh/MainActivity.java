package com.example.menubh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menubh.components.classes.RestaurantClass;
import com.example.menubh.components.utils.CardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<RestaurantClass> restaurantList = new ArrayList<>();
    private ArrayList<RestaurantClass> filteredList = new ArrayList<>();

    private LinearLayout imgDiv;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;

    private ImageButton ascendingButton;
    private ImageButton descendingButton;
    private ImageButton addButton;

    private TextView titleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Responsividade
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa o layout
        imgDiv = findViewById(R.id.img_div);
        titleText = findViewById(R.id.title_text);
        recyclerView = findViewById(R.id.recyclerView);
        bottomNavigationView = findViewById(R.id.footer_frame);

        addButton = findViewById(R.id.addButton);
        ascendingButton = findViewById(R.id.btn_ascending);
        descendingButton = findViewById(R.id.btn_descending);

        // Configura o RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(restaurantList));

        ascendingButton.setOnClickListener(v -> sortList(restaurantList, true));
        descendingButton.setOnClickListener(v -> sortList(restaurantList, false));

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddScreenActivity.class);
            startActivityForResult(intent, 1);
        });

        // Configura o BottomNavigationView
        setupBottomNavigationView();

        updateVisibility();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                RestaurantClass newRestaurant = (RestaurantClass) data.getSerializableExtra("newRestaurant");
                if (newRestaurant != null) {
                    restaurantList.add(newRestaurant);

                    // Notifica o recyclerView que algo foi inserido
                    recyclerView.getAdapter().notifyItemInserted(restaurantList.size() - 1);

                    // Atualiza a visibilidade
                    updateVisibility();
                }
            }
        }
    }

    public void filterListBySpecialty(String specialty) {
        filteredList.clear();
        if (specialty == null) {
            // Mostrar a lista completa se specialty for nulo
            recyclerView.setAdapter(new CardAdapter(restaurantList));
            filteredList.addAll(restaurantList); // Adicionar todos os restaurantes à filteredList
        } else {
            // Filtrar os restaurantes pela especialidade selecionada
            for (RestaurantClass restaurant : restaurantList) {
                if (restaurant.getRestaurantSpecialty().equals(specialty)) {
                    filteredList.add(restaurant);
                }
            }
            recyclerView.setAdapter(new CardAdapter(filteredList));
        }

        recyclerView.getAdapter().notifyDataSetChanged();
        updateVisibility();
    }


    private void updateVisibility() {
        if (restaurantList.isEmpty() || filteredList.isEmpty()) {
            // Se a lista completa ou a lista filtrada estiver vazia, aparece a imagem
            imgDiv.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            // Se estiver com dados, aparece os cards
            imgDiv.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void sortList(ArrayList<RestaurantClass> list, final boolean ascending) {
        Collections.sort(list, (r1, r2) -> ascending ? r1.getRestaurantName().compareToIgnoreCase(r2.getRestaurantName())
                : r2.getRestaurantName().compareToIgnoreCase(r1.getRestaurantName()));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // Inicialmente, nenhum item é selecionado, então titleText.getText() pode ser vazio ou um valor padrão
            String currentTitle = titleText.getText().toString();

            if (itemId == R.id.navigation_sushi && !currentTitle.equals(getString(R.string.str_sushi))) {
                filterListBySpecialty("Sushi");
                titleText.setText(R.string.str_sushi);
            } else if (itemId == R.id.navigation_barbecue && !currentTitle.equals(getString(R.string.str_barbecue))) {
                filterListBySpecialty("Barbecue");
                titleText.setText(R.string.str_barbecue);
            } else if (itemId == R.id.navigation_fish && !currentTitle.equals(getString(R.string.str_fish))) {
                filterListBySpecialty("Fish");
                titleText.setText(R.string.str_fish);
            } else if (itemId == R.id.navigation_pizza && !currentTitle.equals(getString(R.string.str_pizza))) {
                filterListBySpecialty("Pizza");
                titleText.setText(R.string.str_pizza);
            } else if ((itemId == R.id.navigation_sushi || itemId == R.id.navigation_barbecue || itemId == R.id.navigation_fish || itemId == R.id.navigation_pizza)
                    && (currentTitle.equals(getString(R.string.str_sushi)) || currentTitle.equals(getString(R.string.str_barbecue)) ||
                    currentTitle.equals(getString(R.string.str_fish)) || currentTitle.equals(getString(R.string.str_pizza)))) {
                filterListBySpecialty(null);
            }

            return true;
        });
    }
}
