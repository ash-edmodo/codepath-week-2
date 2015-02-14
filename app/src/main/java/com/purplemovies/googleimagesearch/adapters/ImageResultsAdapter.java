package com.purplemovies.googleimagesearch.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.purplemovies.googleimagesearch.R;
import com.purplemovies.googleimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

    public ImageResultsAdapter(Context context, List<ImageResult> objects) {
        super(context, R.layout.image_result_item, objects);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageResult = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_result_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text_view);
        
        imageView.setImageResource(0);
//        titleTextView.setText(imageResult.title);
        titleTextView.setText(Html.fromHtml(imageResult.title));
        Picasso.with(getContext()).load(imageResult.thumbUrl).into(imageView);
        
        return convertView;
    }
}
