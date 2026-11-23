package com.example.mid_exam_yasmeenaliabudib;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mid_exam_yasmeenaliabudib.databinding.ActivityArtworkDetailsBinding;

public class ArtworkDetailsActivity extends AppCompatActivity {

    public static ActivityArtworkDetailsBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        detailsBinding = ActivityArtworkDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_details), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE_KEY");
        String artist = intent.getStringExtra("ARTIST_KEY");
        String year = intent.getStringExtra("YEAR_KEY");


        SpannableStringBuilder builder = new SpannableStringBuilder();


        String titleLabel = "Title: ";
        builder.append(titleLabel);
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, titleLabel.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(title).append("\n\n");


        int artistStart = builder.length();
        String artistLabel = "Artist: ";
        builder.append(artistLabel);
        builder.setSpan(new StyleSpan(Typeface.BOLD), artistStart, artistStart + artistLabel.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(artist).append("\n\n");


        int yearStart = builder.length();
        String yearLabel = "Year: ";
        builder.append(yearLabel);
        builder.setSpan(new StyleSpan(Typeface.BOLD), yearStart, yearStart + yearLabel.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(year);

        detailsBinding.textDetails.setText(builder);

        detailsBinding.buttonBack.setOnClickListener(view -> finish());

        detailsBinding.buttonVisitWebsite.setOnClickListener(view -> {
            String url = "https://www.google.com/search?q=" + title;
            Uri uri = Uri.parse(url);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(webIntent);
        });
    }
}