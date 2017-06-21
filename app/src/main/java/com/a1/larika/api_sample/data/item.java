package com.a1.larika.api_sample.data;

import org.json.JSONObject;

/**
 * Created by Larika on 21-06-2017.
 */
public class item implements  JSONPopulator {
    private condition cond;

    public condition getCond() {
        return cond;
    }

    @Override

    public void populate(JSONObject data) {
cond=new condition();
        cond.populate(data.optJSONObject("condition"));
    }
}
