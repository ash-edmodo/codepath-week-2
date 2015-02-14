package com.purplemovies.googleimagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.purplemovies.googleimagesearch.EndlessScrollListener;
import com.purplemovies.googleimagesearch.R;
import com.purplemovies.googleimagesearch.adapters.ImageResultsAdapter;
import com.purplemovies.googleimagesearch.models.AdvancedFilters;
import com.purplemovies.googleimagesearch.models.ImageResult;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private EditText mQueryEditText;
    private GridView mResultsGridView;
    private AsyncHttpClient httpClient;
    private ImageResultsAdapter imageResultsAdapter;
    
    private ArrayList<ImageResult> imageResults;
    private String currentQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mQueryEditText = (EditText) findViewById(R.id.query_edit_text);
        mResultsGridView = (GridView) findViewById(R.id.results_grid_view);
        httpClient = new AsyncHttpClient();

        imageResults = new ArrayList<>();
        imageResultsAdapter = new ImageResultsAdapter(this, imageResults);
        mResultsGridView.setAdapter(imageResultsAdapter);

        mResultsGridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            protected void onLoadMore(int page, int totalItemCount) {
                customLoadMoreDataFromApi(page);
            }
        });
        
        mResultsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(MainActivity.this, FullImageViewActivity.class);
                ImageResult result = imageResults.get(position);
                intent.putExtra("model", result);
                startActivity(intent);
            }
        });
    }

    public void onSearchClick(View view) {
        currentQuery = mQueryEditText.getText().toString();
        imageResults.clear(); // Clear for new search.
        requestImagesPage(0);
    }

    private void requestImagesPage(int pageNumber) {
        String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + currentQuery +
                "&rsz=8&start=" + String.valueOf(pageNumber) + "&"
                + AdvancedFilters.getQueryString();
        httpClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray results;
                try {
                    results = response.getJSONObject("responseData").getJSONArray("results");
//                    imageResults.clear(); // Clear for new search.
                    imageResultsAdapter.addAll(ImageResult.fromJsonArray(results));
                    imageResultsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void customLoadMoreDataFromApi(int page) {
        Toast.makeText(this, "page " + String.valueOf(page), Toast.LENGTH_LONG).show();
        requestImagesPage(page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SearchFilterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
