package com.purplemovies.googleimagesearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageResult implements Serializable {
    public String fullUrl;
    public String thumbUrl;
    public String title;
    
    public ImageResult(JSONObject json) {
        try {
            fullUrl = json.getString("url");
            thumbUrl = json.getString("tbUrl");
            title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<ImageResult> fromJsonArray(JSONArray array) {
        ArrayList<ImageResult> results = new ArrayList<>();
        
        for (int i=0; i < array.length(); i++) {
            try {
                results.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
