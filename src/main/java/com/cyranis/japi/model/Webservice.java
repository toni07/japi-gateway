package com.cyranis.japi.model;

import java.io.Serializable;

/**
 * @author aepardeau on 17/11/2017.
 */
public class Webservice implements Serializable {

	/**
	 * **************************************************************************************
	 * attributes
	 * **************************************************************************************
	 */
	private int id;
	private String label;
	private String urlFrom;
	private String urlTo;
	private Integer cacheTimeInMs;

	/**
	 * **************************************************************************************
	 * constructors
	 * **************************************************************************************
	 */
	public Webservice(int id, String label, String urlFrom, String urlTo, Integer cacheTimeInMs) {
		this.id = id;
		this.label = label;
		this.urlFrom = urlFrom;
		this.urlTo = urlTo;
		this.cacheTimeInMs = cacheTimeInMs;
	}

	/**
	 * **************************************************************************************
	 * methods
	 * **************************************************************************************
	 */

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getUrlFrom() {
		return urlFrom;
	}

	public String getUrlTo() {
		return urlTo;
	}

	public Integer getCacheTimeInMs() {
		return cacheTimeInMs;
	}
}
