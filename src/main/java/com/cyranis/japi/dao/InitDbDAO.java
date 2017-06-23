package com.cyranis.japi.dao;

import org.sql2o.Sql2o;

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
		new InitDbDAO().doSth();
	}

	public void doSth()
	{
		final StringBuilder sql = new StringBuilder("CREATE TABLE table1 ( user varchar(50) )");
		executeUpdate(sql, null);

		sql.setLength(0);
		sql.append("INSERT INTO table1 ( user ) VALUES ( 'Claudio' )");
		executeUpdate(sql, null);
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
