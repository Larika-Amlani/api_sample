package com.a1.larika.api_sample.data;

import org.json.JSONObject;

/**
 * Created by Larika on 21-06-2017.
 */
public class units implements JSONPopulator {
    private String temp;

    public String getTemp() {
        return temp;
    }

    @Override
    public void populate(JSONObject data) {
temp=data.optString("temperature");
    }
}
