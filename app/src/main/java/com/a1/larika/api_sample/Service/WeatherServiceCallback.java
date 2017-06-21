package com.a1.larika.api_sample.Service;

import com.a1.larika.api_sample.data.channel;

/**
 * Created by Larika on 21-06-2017.
 */
public interface WeatherServiceCallback {
 void serviceSuccess(channel chann);
void serviceFailure(Exception exception);
}
