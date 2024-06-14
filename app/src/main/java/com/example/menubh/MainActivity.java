package com.example.menubh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menubh.components.classes.RestaurantClass;
import com.example.menubh.components.utils.CardAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton AddButton;
    private ArrayList<RestaurantClass> restaurantList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinearLayout imgDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Responsividade
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa o layout
        imgDiv = findViewById(R.id.img_div);
        recyclerView = findViewById(R.id.recyclerView);

        // Configura o RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(restaurantList));

        AddButton = findViewById(R.id.addButton);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddScreenActivity.class);
                startActivityForResult(intent, 1);
            }
        });

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
}