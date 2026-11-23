package com.example.mid_exam_yasmeenaliabudib;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mid_exam_yasmeenaliabudib.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;
    private int currentIndex = 0;

    private final int[] images = {R.drawable.art1, R.drawable.art2, R.drawable.art3, R.drawable.art4, R.drawable.art5, R.drawable.art6};

    private final String[] titles = {
            "Sunflowers",
            "The Starry Night",
            "Water Lilies",
            "The Persistence of Memory",
            "Starry Night Over the Rhône",
            "Mona Lisa"
    };

    private final String[] artists = {
            "Vincent van Gogh",
            "Vincent van Gogh",
            "Claude Monet",
            "Salvador Dalí",
            "Vincent van Gogh",
            "Leonardo da Vinci"
    };

    private final String[] years = {
            "1888",
            "1889",
            "1906",
            "1931",
            "1888",
            "1503"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("CURRENT_INDEX", 0);
        }

        updateUI();

        mainBinding.buttonNext.setOnClickListener(view -> {
            if (currentIndex < images.length - 1) {
                currentIndex++;
                updateUI();
                if (currentIndex == images.length - 1) {
                    Toast.makeText(this, "This is the last artwork!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mainBinding.buttonPrevious.setOnClickListener(view -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateUI();

                if (currentIndex == 0) {
                    Toast.makeText(this, "This is the first artwork!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mainBinding.imageArtwork.setOnClickListener(view -> {
            Intent intent = new Intent(this, ArtworkDetailsActivity.class);
            intent.putExtra("TITLE_KEY", titles[currentIndex]);
            intent.putExtra("ARTIST_KEY", artists[currentIndex]);
            intent.putExtra("YEAR_KEY", years[currentIndex]);
            startActivity(intent);
        });
    }

    private void updateUI() {
        mainBinding.textCounter.setText((currentIndex + 1) + " / " + images.length);
        mainBinding.imageArtwork.setImageResource(images[currentIndex]);
        mainBinding.textTitle.setText(titles[currentIndex]);
        mainBinding.textArtistYear.setText(artists[currentIndex] + ", " + years[currentIndex]);

        boolean isFirst = (currentIndex == 0);
        boolean isLast = (currentIndex == images.length - 1);


        mainBinding.buttonPrevious.setEnabled(!isFirst);
        mainBinding.buttonNext.setEnabled(!isLast);


        if (isFirst) {

            mainBinding.buttonPrevious.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
            mainBinding.buttonPrevious.setTextColor(Color.parseColor("#999999"));
        } else {
            mainBinding.buttonPrevious.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8B5A2B")));
            mainBinding.buttonPrevious.setTextColor(Color.WHITE);
        }
        if (isLast) {

            mainBinding.buttonNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
            mainBinding.buttonNext.setTextColor(Color.parseColor("#999999"));
        } else {

            mainBinding.buttonNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8B5A2B")));
            mainBinding.buttonNext.setTextColor(Color.WHITE);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CURRENT_INDEX", currentIndex);
    }
}