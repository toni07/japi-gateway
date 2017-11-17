package com.cyranis.japi;

import com.cyranis.japi.dao.WebServiceDAO;
import com.cyranis.japi.util.JsonTransformer;

import java.util.HashMap;
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
		externalStaticFileLocation("src/main/resources/public/");

		final WebServiceDAO webServiceDAO = new WebServiceDAO();
		/**
		 *
		 */
		path("/admin", () -> {
			before("/*", (q, a) -> System.out.println("in Admin GET method"));
			path("/ws", () -> {
				get("/list",    (request, res) ->
				{
					res.header("Content-Type", "application/json; charset=UTF-8");
					final Map<String, Object> objectMap = new HashMap<>(2);
					objectMap.put("result", true);
					objectMap.put("msg", "Ok!");
					objectMap.put("objects", webServiceDAO.list());
					System.out.println("objectMap: " + objectMap);
					return objectMap;
				}, new JsonTransformer());
				post("/",    (request, res) ->   {System.out.println("in Admin update WS method");return ""; });
				put("/",  (request, res) ->   {System.out.println("in Admin add WS method");return ""; });
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
			final String uri = request.uri();// the uri, e.g. "/foo/toto"
			final String[] urlSplit = uri.split("/");
			System.out.println("URL urlSplit[1]: " + urlSplit[1]);
			if("admin".equals(urlSplit[1])){
				System.out.println("Admin URL, skipping...");
			}
			else{
				final Map<String, Object> webService = webServiceDAO.getByUri(uri);
				if(null != webService){
					System.out.println("found corresponding WS!");
					final String requestMethod = request.requestMethod();// The HTTP method (GET, ..etc)
					final Map<String, String> cookies = request.cookies();// request cookies sent by the client
					final Set<String> headers = request.headers();// the HTTP header list
					final String header1 = request.headers("User-Agent");// value of BAR header
					final Map<String, String> params = request.params();// map with all parameters
				}
				else{
					System.err.println("no corresponding WS found for `url_from` " + uri);
				}
			}

			return "API Management";
		});

		exception(Exception.class, (exception, request, response) ->
		{
			System.err.println("error occured");
			exception.printStackTrace();
		});

	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
