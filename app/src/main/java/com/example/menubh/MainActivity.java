package com.example.menubh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menubh.components.classes.RestaurantClass;
import com.example.menubh.components.utils.BottomNavigationHelper;
import com.example.menubh.components.utils.CardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements BottomNavigationHelper.NavigationCallback {

    // Lista principal de restaurantes e lista filtrada (se houver filtro)
    private ArrayList<RestaurantClass> restaurantList = new ArrayList<>();
    private ArrayList<RestaurantClass> filteredList = new ArrayList<>();
    private boolean isFiltered = false;

    // Elementos da interface
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

        // Inicializa os elementos da interface
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

        // Configura os botões de ordenação
        ascendingButton.setOnClickListener(v -> sortList(true));
        descendingButton.setOnClickListener(v -> sortList(false));

        // Configura o botão de adicionar restaurante
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddScreenActivity.class);
            startActivityForResult(intent, 1);
        });

        // Configura a navegação inferior
        BottomNavigationHelper bottomNavigationHelper = new BottomNavigationHelper(this, bottomNavigationView, titleText, this);
        bottomNavigationHelper.setupBottomNavigationView();

        // Atualiza a visibilidade dos elementos
        updateVisibility();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Recebe o restaurante adicionado da tela de adição
            RestaurantClass newRestaurant = (RestaurantClass) data.getSerializableExtra("newRestaurant");
            if (newRestaurant != null) {
                // Adiciona o restaurante à lista e notifica o RecyclerView
                restaurantList.add(newRestaurant);
                recyclerView.getAdapter().notifyItemInserted(restaurantList.size() - 1);
                updateVisibility();
            }
        }
    }

    // Filtra a lista com base na especialidade do restaurante
    @Override
    public void filterListBySpecialty(String specialty) {

        filteredList.clear();
        if (specialty == null) {
            // Se a especialidade for nula, mostra todos os restaurantes
            recyclerView.setAdapter(new CardAdapter(restaurantList));
            filteredList.addAll(restaurantList);
            isFiltered = false;
        } else {
            // Filtra os restaurantes pela especialidade especificada
            for (RestaurantClass restaurant : restaurantList) {
                if (restaurant.getRestaurantSpecialty().equals(specialty)) {
                    filteredList.add(restaurant);
                }
            }
            recyclerView.setAdapter(new CardAdapter(filteredList));
            isFiltered = true;
        }
        recyclerView.getAdapter().notifyDataSetChanged();
        updateVisibility();
    }

    // Filtra a lista para mostrar apenas os restaurantes marcados como favoritos
    @Override
    public void filterFavorites() {
        filteredList.clear();
        for (RestaurantClass restaurant : restaurantList) {
            if (restaurant.getFavorite()) {
                filteredList.add(restaurant);
            }
        }
        recyclerView.setAdapter(new CardAdapter(filteredList));
        isFiltered = true;
        recyclerView.getAdapter().notifyDataSetChanged();
        updateVisibility();
    }

    // Atualiza a visibilidade dos elementos com base na lista de restaurantes e filtros
    private void updateVisibility() {
        boolean isEmpty = restaurantList.isEmpty() || (isFiltered && filteredList.isEmpty());
        imgDiv.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }

    // Ordena a lista de restaurantes (original ou filtrada) em ordem alfabética
    private void sortList(boolean ascending) {
        ArrayList<RestaurantClass> listToSort = isFiltered ? filteredList : restaurantList;
        Collections.sort(listToSort, (r1, r2) -> ascending ? r1.getRestaurantName().compareToIgnoreCase(r2.getRestaurantName())
                : r2.getRestaurantName().compareToIgnoreCase(r1.getRestaurantName()));
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}