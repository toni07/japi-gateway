package com.cyranis.japi.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Map;

/**
 * @author aepardeau on 23/06/2017.
 */
public class AbstractDAO {

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
	private Sql2o buildSql2o()
	{
		return new Sql2o("jdbc:h2:file:./database/webservices", "sa", "sp");
	}

	/**
	 * Gets the result of a SELECT query using the Sql2O library
	 * @param sql
	 * @param parameterMap
	 * @return
	 */
	public List<Map<String, Object>> getListResult(StringBuilder sql, Map<String, Object> parameterMap)
	{
		final Sql2o sql2o = buildSql2o();
		try (Connection con = sql2o.open()) {
			final org.sql2o.Query query = con.createQuery(sql.toString(), true);
			if (null != parameterMap) {
				for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
					query.addParameter(entry.getKey(), entry.getValue());
				}
			}
			return query.executeAndFetchTable().asList();
		}
	}

	/**
	 * Gets the <b>first</b> result of a SELECT query using the Sql2O library
	 * @param sql
	 * @param parameterMap
	 * @return
	 */
	public Map<String, Object> getUniqueResult(StringBuilder sql, Map<String, Object> parameterMap) {

		final List<Map<String, Object>> resultList = getListResult(sql, parameterMap);
		if (null != resultList && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	/**
	 * Executes an INSERT query using the Sql2O library
	 * @param sql
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	protected Integer executeUpdate(StringBuilder sql, Map<String, Object> parameterMap)
	{
		final Sql2o sql2o = buildSql2o();
		try (Connection con = sql2o.open()) {
			final org.sql2o.Query query = con.createQuery(sql.toString(), true);
			if (null != parameterMap) {
				for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
					query.addParameter(entry.getKey(), entry.getValue());
				}
			}
			final Connection connection = query.executeUpdate();
			if (null != connection) {
				final Object newId = connection.getKey();
				if (null != newId) {
					return ((Number) newId).intValue();
				}
			}
			return null;
		}
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
