package com.a1.larika.api_sample.data;

import org.json.JSONObject;

/**
 * Created by Larika on 21-06-2017.
 */
public class condition implements JSONPopulator {
    private int code;
    private int temp;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
code=data.optInt("code");
        temp=data.optInt("temp");
        description= data.optString("text");
    }
}
