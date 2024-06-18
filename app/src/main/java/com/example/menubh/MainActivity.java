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

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_sushi) {
                filterListBySpecialty("sushi");
                titleText.setText(R.string.str_sushi);
                return true;
            } else if (itemId == R.id.navigation_barbecue) {
                filterListBySpecialty("barbecue");
                titleText.setText(R.string.str_barbecue);
                return true;
            } else if (itemId == R.id.navigation_fish) {
                filterListBySpecialty("fish");
                titleText.setText(R.string.str_fish);
                return true;
            } else if (itemId == R.id.navigation_pizza) {
                filterListBySpecialty("pizza");
                titleText.setText(R.string.str_pizza);
                return true;
            }

            return false;
        });

        updateVisibility();
    }

    public void filterListBySpecialty(String specialty) {
        filteredList.clear();
        for (RestaurantClass restaurant : restaurantList) {
            if (restaurant.getRestaurantSpecialty().equals(specialty)) {
                filteredList.add(restaurant);
                recyclerView.setAdapter(new CardAdapter(filteredList));
            }
        }
        recyclerView.getAdapter().notifyDataSetChanged();
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

    private void updateVisibility() {
        if (restaurantList.isEmpty()) {
            // Se a lista está vazia, aparece a imagem
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
}
