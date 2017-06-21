package com.a1.larika.api_sample.data;

import org.json.JSONObject;

/**
 * Created by Larika on 21-06-2017.
 */
public class channel implements JSONPopulator {
    private units unit;
private item items;

    public units getUnit() {
        return unit;
    }

    public item getItems() {
        return items;
    }

    @Override
    public void populate(JSONObject data) {
unit=new units();
        unit.populate(data.optJSONObject("units"));

        items=new item();
        items.populate(data.optJSONObject("item"));

    }
}
