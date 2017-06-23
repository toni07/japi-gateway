package com.cyranis.japi;

import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

/**
 * @author aepardeau on 22/06/2017.
 */
public class Launcher {

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

		port(Integer.valueOf(args[0]));

		/**
		 *
		 */
		path("/admin", () -> {
			before("/*", (q, a) -> System.out.println("in Admin GET method"));
			path("/email", () -> {
				post("/add",    (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
				put("/change",  (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
				delete("/remove",  (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
			});
			path("/username", () -> {
				post("/add",       (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
				put("/change",     (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
				delete("/remove",  (request, res) ->   {System.out.println("in Admin ADD method");return ""; });
			});
		});

		/**
		 *
		 */
		get("*", (request, res) ->
		{
			System.out.println("in GET method");
			final String requestMethod = request.requestMethod();// The HTTP method (GET, ..etc)
			final String uri = request.uri();// the uri, e.g. "/foo/toto"
			final String[] urlSplit = uri.split("/");
			if("admin".equals(urlSplit[1])){
				System.out.println("Admin URL, skipping...");
			}
			else{
				final Map<String, String> cookies = request.cookies();// request cookies sent by the client
				final Set<String> headers = request.headers();// the HTTP header list
				final String header1 = request.headers("User-Agent");// value of BAR header
				final Map<String, String> params = request.params();// map with all parameters
			}

			return "API Management";
		});

	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
