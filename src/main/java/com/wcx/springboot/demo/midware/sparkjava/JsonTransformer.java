package com.wcx.springboot.demo.midware.sparkjava;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Mapped routes that transform the output from the handle method.
 * This is done by extending the ResponseTransformer object and passing it to the mapping method.
 * CounterExample of a route transforming output to JSON using Gson:
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
