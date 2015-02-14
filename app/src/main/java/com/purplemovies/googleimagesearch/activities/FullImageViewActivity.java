package com.purplemovies.googleimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.purplemovies.googleimagesearch.R;
import com.purplemovies.googleimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

public class FullImageViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        getSupportActionBar().hide();
        ImageResult result = (ImageResult) getIntent().getSerializableExtra("model");
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        Picasso.with(this).load(result.fullUrl).into(imageView);
    }
}
