package com.example.menubh;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.menubh.components.utils.StarRatingHelper;

public class AddScreenActivity extends AppCompatActivity {

    private ImageButton[] stars = new ImageButton[5];

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

        stars[0] = findViewById(R.id.star1);
        stars[1] = findViewById(R.id.star2);
        stars[2] = findViewById(R.id.star3);
        stars[3] = findViewById(R.id.star4);
        stars[4] = findViewById(R.id.star5);

        for (ImageButton star : stars) {
            star.setOnClickListener(v -> {
                int selectedStar = StarRatingHelper.onStarClick((ImageButton) v, stars);
                // Return the value of selected stars
                if (selectedStar != -1) {
                    Toast.makeText(AddScreenActivity.this, "Selected star: " + selectedStar, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}