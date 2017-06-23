package com.cyranis.japi.dao;

import java.util.HashMap;
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
	public Map<String, Object> getByUri(String uri)
	{
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM webservices WHERE url_from = :url_from");
		final Map<String, Object> parametersMap = new HashMap<>(2);
		parametersMap.put("url_from", uri);
		return getUniqueResult(sql, parametersMap);
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
