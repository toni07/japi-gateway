package com.cyranis.japi.dao;

import com.cyranis.japi.model.Webservice;
import com.cyranis.japi.util.JSONHelper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

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
	/**
	 *
	 * @return
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

	/**
	 *
	 * @param webservice
	 */
	public void add(Webservice webservice)
	{
		final StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO webservices (label, url_from, url_to, cache_time_in_ms, jar_file_path) VALUES (:label, :url_from, :url_to, :cache_time_in_ms, :jar_file_path)");
		final Map<String, Object> parametersMap = new HashMap<>(2);
		parametersMap.put("label", webservice.getLabel());
		parametersMap.put("url_from", webservice.getUrlFrom());
		parametersMap.put("url_to", webservice.getUrlTo());
		parametersMap.put("cache_time_in_ms", webservice.getCacheTimeInMs());
		parametersMap.put("jar_file_path", webservice.getJarFilePath());
		executeUpdate(sql, parametersMap);
	}

	/**
	 *
	 * @param id
	 */
	public void delete(int id)
	{
		final StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM webservices WHERE id_ws = :id_ws");
		final Map<String, Object> parametersMap = new HashMap<>(2);
		parametersMap.put("id_ws", id);
		executeUpdate(sql, parametersMap);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Webservice getById(int id)
	{
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM webservices WHERE id_ws = :id_ws");
		final Map<String, Object> parametersMap = new HashMap<>(2);
		parametersMap.put("id_ws", id);
		return buildObject(getUniqueResult(sql, parametersMap));
	}

	/**
	 *
	 * @param uri
	 * @return
	 */
	public Webservice getByUri(String uri)
	{
//		final StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM webservices WHERE url_from = :url_from");
//		final Map<String, Object> parametersMap = new HashMap<>(2);
//		parametersMap.put("url_from", uri);
//		return buildObject(getUniqueResult(sql, parametersMap));
        final JsonNode rootNode = JSONHelper.fileToJsonNode("configuration.json");
        final JsonNode apisNode = rootNode.get("apis");
        final Iterator<JsonNode> apiIterator = apisNode.iterator();
        while (apiIterator.hasNext()){
            final JsonNode apiNode = apiIterator.next();
            final String label = apiNode.get("label").asText();
            final String urlFrom = apiNode.get("url_from").asText();
            final String urlTo = apiNode.get("url_to").asText();
            final int cacheTimeInMs = apiNode.get("cache_time_in_ms").asInt();
            if(uri.equals(urlFrom)){
                return new Webservice(0, label, urlFrom, urlTo, cacheTimeInMs, "");
            }
        }
        return null;
    }

	/**
	 *
	 * @param dbResult
	 * @return
	 */
	private static Webservice buildObject(Map<String, Object> dbResult)
	{
		final Integer id = ((Number) dbResult.get("id_ws")).intValue();
		final String label = (String) dbResult.get("label");
		final String urlFrom = (String) dbResult.get("url_from");
		final String urlTo = (String) dbResult.get("url_to");
		final Integer cacheTimeInMs = ((Number) dbResult.get("cache_time_in_ms")).intValue();
		final String jarFilePath = (String) dbResult.get("jar_file_path");
		return new Webservice(id, label, urlFrom, urlTo, cacheTimeInMs, jarFilePath);
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
