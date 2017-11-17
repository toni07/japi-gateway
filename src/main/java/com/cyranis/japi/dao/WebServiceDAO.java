package com.cyranis.japi.dao;

import com.cyranis.japi.model.Webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aepardeau on 23/06/2017.
 */
public class WebServiceDAO extends AbstractDAO{

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
	public List<Webservice> list()
	{
		final List<Webservice> result = new ArrayList<>();
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM webservices");
		final List<Map<String, Object>> listDbResult = getListResult(sql);
		if (null != listDbResult) {
			for (Map<String, Object> dbResult : listDbResult) {
				result.add(buildObject(dbResult));
			}
		}
		return result;
	}

	public Map<String, Object> getByUri(String uri)
	{
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM webservices WHERE url_from = :url_from");
		final Map<String, Object> parametersMap = new HashMap<>(2);
		parametersMap.put("url_from", uri);
		return getUniqueResult(sql, parametersMap);
	}

	private static Webservice buildObject(Map<String, Object> dbResult)
	{
		final String label = (String) dbResult.get("label");
		final String urlFrom = (String) dbResult.get("url_from");
		final String urlTo = (String) dbResult.get("url_to");
		final Integer cacheTimeInMs = ((Number) dbResult.get("cache_time_in_ms")).intValue();
		return new Webservice(label, urlFrom, urlTo, cacheTimeInMs);
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
