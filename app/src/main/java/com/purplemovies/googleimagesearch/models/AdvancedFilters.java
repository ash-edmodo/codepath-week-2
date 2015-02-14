package com.purplemovies.googleimagesearch.models;

import java.util.ArrayList;

public class AdvancedFilters {
    public static final String ANY = "any";

    static String sSize = ANY;
    static String sColor = ANY;
    static String sImageType = ANY;
    static String sFileType = ANY;
    static String sSite = ANY;

    public static String getSize() {
        return sSize;
    }

    public static String getColor() {
        return sColor;
    }

    public static String getFileType() {
        return sFileType;
    }

    public static String getSite() {
        return sSite;
    }

    public static String getImageType() {
        return sImageType;
    }

    public static void setImageType(String sImageType) {
        AdvancedFilters.sImageType = sImageType;
    }
    
    public static void setSize(String sSize) {
        AdvancedFilters.sSize = sSize;
    }

    public static void setColor(String sColor) {
        AdvancedFilters.sColor = sColor;
    }

    public static void setFileType(String sType) {
        AdvancedFilters.sFileType = sType;
    }

    public static void setSite(String sSite) {
        AdvancedFilters.sSite = sSite;
    }
    
    public static String getQueryString() {
        final ArrayList<String> strings = new ArrayList<>();
        
        strings.add(getQuery("imgsz", sSize));
        strings.add(getQuery("imgcolor", sColor));
        strings.add(getQuery("imgtype", sImageType));
        strings.add(getQuery("as_filetype", sFileType));
        strings.add(getQuery("as_sitesearch", sSite));

        String queryString = "";
        for (String s : strings) {
            if (!s.equals("")) {
                queryString += "&" + s;
            }
        }
        return queryString;
    }
    
    private static String getQuery(String name, String value) {
        if (value.equals(ANY)) {
            return "";
        }
        return name+"="+value.toLowerCase();
    }
}
