package com.purplemovies.googleimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.purplemovies.googleimagesearch.R;
import com.purplemovies.googleimagesearch.models.AdvancedFilters;

public class SearchFilterActivity extends ActionBarActivity {

    private Spinner mSizeSpinner;
    private Spinner mColorSpinner;
    private Spinner mFileTypeSpinner;
    private Spinner mImageTypeSpinner;
    private EditText mSiteFilterEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        mSizeSpinner = (Spinner) findViewById(R.id.size_spinner);
        mColorSpinner = (Spinner) findViewById(R.id.color_spinner);
        mImageTypeSpinner = (Spinner) findViewById(R.id.image_type_spinner);
        mFileTypeSpinner = (Spinner) findViewById(R.id.file_type_spinner);
        mSiteFilterEditText = (EditText) findViewById(R.id.site_filter_edit_text);
        
        setSpinnerValue(mSizeSpinner, AdvancedFilters.getSize());
        setSpinnerValue(mColorSpinner, AdvancedFilters.getColor());
        setSpinnerValue(mImageTypeSpinner, AdvancedFilters.getImageType());
        setSpinnerValue(mFileTypeSpinner, AdvancedFilters.getFileType());
        mSiteFilterEditText.setText(AdvancedFilters.getSite());
        
        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String size = mSizeSpinner.getSelectedItem().toString();
                final String color = mColorSpinner.getSelectedItem().toString();
                final String imageType = mImageTypeSpinner.getSelectedItem().toString();
                final String fileType = mFileTypeSpinner.getSelectedItem().toString();
                final String site = mSiteFilterEditText.getText().toString();

                AdvancedFilters.setSize(size);
                AdvancedFilters.setColor(color);
                AdvancedFilters.setImageType(imageType);
                AdvancedFilters.setFileType(fileType);
                AdvancedFilters.setSite(site);
                finish();
            }
        });
    }
    
    private void setSpinnerValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                index = i;
                break;
            }
        }
        spinner.setSelection(index);
    }
}
