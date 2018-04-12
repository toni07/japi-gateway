package com.cyranis.japi.util;

import com.cyranis.japi.model.Webservice;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.concurrent.TimeUnit;

/**
 * @author aepardeau on 12/04/2018.
 */
public class CacheManager {

	/**
	 * **************************************************************************************
	 * attributes
	 * **************************************************************************************
	 */

	/**
	 * **************************************************************************************
	 * constructors
	 * **************************************************************************************
	 */

	/**
	 * **************************************************************************************
	 * methods
	 * **************************************************************************************
	 */
	public static void doSth()
	{
		final Cache<String, Webservice> cache = Caffeine.newBuilder()
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.maximumSize(10_000)
				.build();
		// Lookup an entry, or null if not found
		Webservice webservice = cache.getIfPresent("toto");
		// Lookup and compute an entry if absent, or null if not computable
//		webservice = cache.get("toto", k -> createExpensiveGraph(key));
		// Insert or update an entry
		cache.put("toto", new Webservice(0, "", "", "", 0, ""));
		// Remove an entry
		cache.invalidate("toto");

		//TODO: see if we can manage the cache entries' expiration time individually
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
