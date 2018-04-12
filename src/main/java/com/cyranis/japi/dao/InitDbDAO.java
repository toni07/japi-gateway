package com.cyranis.japi.dao;

/**
 * @author aepardeau on 23/06/2017.
 */
public class InitDbDAO extends AbstractDAO{

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
	public static void main(String[] args) {
		new InitDbDAO().doInit();
	}

	public void doInit()
	{
		final StringBuilder sql = new StringBuilder("CREATE TABLE webservices (id_ws IDENTITY, label VARCHAR(255), url_from VARCHAR(255)");
		sql.append(", url_to VARCHAR(255), cache_time_in_ms INT, jar_file_path VARCHAR(511), running_processus_id int)");
//		executeUpdate(sql, null);

		sql.setLength(0);
		sql.append("INSERT INTO webservices (label, url_from, url_to, cache_time_in_ms) VALUES ( 'Test WebService', '/ws1', 'http://localhost:19080/test-ws', 0)");
		executeUpdate(sql, null);
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
