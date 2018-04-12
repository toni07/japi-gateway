package com.cyranis.japi.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * @author aepardeau on 14/10/2016.
 */
public class JsonTransformer implements ResponseTransformer {

	private Gson gson = new Gson();

	@Override
	public String render(Object model) {
		return gson.toJson(model);
	}

}